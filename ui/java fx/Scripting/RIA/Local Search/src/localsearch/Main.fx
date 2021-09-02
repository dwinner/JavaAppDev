/* 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 * Copyright 2009 Sun Microsystems, Inc. All rights reserved. Use is subject to license terms. 
 * 
 * This file is available and licensed under the following license:
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 *   * Redistributions of source code must retain the above copyright notice, 
 *     this list of conditions and the following disclaimer.
 *
 *   * Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 *   * Neither the name of Sun Microsystems nor the names of its contributors 
 *     may be used to endorse or promote products derived from this software 
 *     without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package localsearch;

import java.io.InputStream;
import java.lang.Exception;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.io.http.HttpRequest;
import javafx.lang.FX;
import javafx.scene.control.TextBox;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.LayoutInfo;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.ScrollView;
import javafx.scene.layout.VBox;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;

import localsearch.model.Restaurant;
import localsearch.parser.JSONPullParser;
import localsearch.parser.XMLPullParser;
import localsearch.RequestHandler;
import localsearch.view.ImageButton;

import localsearch.Constants.*;

var _stylesheets = "{__DIR__}LocalSearch.css";
// Data type to be used in trasaction
def dataType = "xml"; // set to xml or json

var stageDragInitialX:Number;
var stageDragInitialY:Number;

// TODO: get an appid from http://developer.yahoo.com/search/
def appid = FX.getArgument("yahoo_appid");

// Application Bounds
var sceneWidth:Number = bind scene.width;
var sceneHeight:Number = bind scene.height;

// Restaurant Details Index
var index:Integer = 0;

// Information about all relevant restaurants
public var restaurants: Restaurant[];

// Search and load restaurant details
public function searchCoffeeShops(zipcode:String):Void {
    index = 0;
    delete restaurants;
    resetRestaurantDetails();
    // Perform some basic validation on zipcode
    if(not validateZipCode(zipcode)) { 
        alert("Error", "Zip Code {zipcode} is not valid. Enter valid zip code.");
        return; 
    }
    
    def query = "coffee";
    def results = 5;
    def location = "http://local.yahooapis.com/LocalSearchService/V3/localSearch?appid={appid}&query={query}&zip={zipcode}&results={results}&output={dataType}";
            
    var resultsProcessor:function(is:InputStream):Void;
    if("xml".equalsIgnoreCase(dataType)) {
        resultsProcessor = XMLPullParser.processResults;
    } else {
        resultsProcessor = JSONPullParser.processResults;
    }
        
    println("Loading {dataType} data from {location}...\"");
    alert("Please wait...", "Searching for Coffee Shops near zip code: {zipcode}...");

    try {
        var request = RequestHandler {
            location: location
            method: HttpRequest.GET
            processResults: resultsProcessor
        }
        request.start();
    } catch (e:Exception) {
        println("WARNING: {e}");
        alert("Error", "Could not search... Please try again later.");
    }
}

// Basic validation for Zip Code
function validateZipCode(zipcode:String): Boolean {
    
    // Zip Code Format -> 12345 or 12345-1234
    try {
        if(zipcode.length() == 5) {
            var zipCodeInt = java.lang.Integer.valueOf(zipcode).intValue();
            return (zipCodeInt > 0);
        } else if (zipcode.length() == 10) {
            var dashIndex = zipcode.indexOf("-");
            if (dashIndex != 5)
                return false;
            var firstPart = zipcode.substring(0, dashIndex);
            var zipCodeInt = java.lang.Integer.valueOf(firstPart).intValue();
            if (zipCodeInt <= 0) {
                return false;
            }
            var secondPart = zipcode.substring(0, dashIndex);
            zipCodeInt = java.lang.Integer.valueOf(secondPart).intValue();
            return (zipCodeInt > 0);
        }
        
    } catch (e:Exception) { }
    
    return false;
}
// Background Image
function getBGImage(width:Integer, height:Integer):Image {
    var url:String;
    if( width < height ) {
        url = "{__DIR__}images/background_240X320.png";
    } else {
        url = "{__DIR__}images/background_320X240.png";
    }
    return Image {
        url: url
    }
}

var bgImage:ImageView = ImageView { 
    fitWidth: bind stage.width
    fitHeight: bind stage.height
    image: getBGImage(sceneWidth, sceneHeight)
    onKeyPressed:function(e:KeyEvent) {
        if(e.code == KeyCode.VK_LEFT) {
            onBack();
        } else if(e.code == KeyCode.VK_RIGHT) {
            onNext();
        } else if(e.code == KeyCode.VK_DOWN) {
            zipCodeText.requestFocus();
        }
    }
    onMouseClicked:function(e:MouseEvent) {
        bgImage.requestFocus();
    }
}

// Display details of previous restaurant in list
var backButton = ImageButton { 
    x: _backBtnX
    y: bind (sceneHeight/2.0 + _BackNextBtnYPadding)
    normalImage: Image { url: "{__DIR__}images/arrow_left_normal.png" }
    overImage: Image { url: "{__DIR__}images/arrow_left_over.png" }
    onMouseClicked: function(e) {
        onBack();
    }
}

function onBack():Void {
    if ((sizeof restaurants) == 0) {
        return ;
    }
    index--;
    if (index < 0) {
        index = ((sizeof restaurants) - 1);
    }
    showRestaurantDetails(index, false);
}

// Display details of next restaurant in list
var nextButton = ImageButton {
    x: bind (sceneWidth - _nextBtnXPadding)
    y: bind (sceneHeight/2.0 + _BackNextBtnYPadding)
    normalImage: Image { url: "{__DIR__}images/arrow_right_normal.png" }
    overImage: Image { url: "{__DIR__}images/arrow_right_over.png" }
    onMouseClicked: function(e) {
        onNext();
    }
}

function onNext():Void {
    if ((sizeof restaurants) == 0) {
        return ;
    }
    index++;
    if (index >= (sizeof restaurants)) {
        index = 0;
    }
    showRestaurantDetails(index, true);
}

// Dispose Application
var closeButton = ImageButton { 
    x: bind (sceneWidth - _closeBtnXPadding)
    y: _closeBtnY
    normalImage: Image { url: "{__DIR__}images/x_normal.png" }
    overImage: Image { url: "{__DIR__}images/x_over.png" }
    visible: bind ("{__PROFILE__}" != "browser")
    onMouseClicked: function(e) {
        javafx.lang.FX.exit();
    }
}

// Reset Restaurant Details
public function resetRestaurantDetails():Void {
    shopName = "";
    address = "";
    city = "";
    phone = "";
    star.visible = false;
    comments = "";
    title = "Nearest Coffee Shops";
}

// Display details of restaurant at specified index in list
public function showRestaurantDetails(index:Integer, scrollLeft:Boolean):Void {
    if (index >= (sizeof restaurants)) {
        return ;
    }
    var scrollXVal = 1; // Scroll Right
    if(scrollLeft) { scrollXVal = -1; }
    shopDetailsX = 0;
    
    // Slide restaurant details animation
    var timeline:Timeline = Timeline {
       repeatCount:1
       autoReverse: false
       rate: 1.0
       keyFrames: [ 
            KeyFrame {
                time: 250ms
                values: [
                    shopDetailsX => scrollXVal * sceneWidth tween Interpolator.LINEAR
                ]
                action: function () {
                    shopDetailsX = scrollXVal * -sceneWidth;
                    var result = restaurants[index];
                    shopName = trimString(result.title, 25);
                    address = trimString(result.address, 30);
                    city = trimString("{result.city} {result.state}", 30);
                    phone = "{result.phone}";
                    setStars(result.rating.averageRating);
                    var lastReview = "{result.rating.lastReviewIntro}";
                    comments = lastReview; //trimString("{lastReview}", 300);
                    title = "Coffee Shops ({index + 1} of {sizeof restaurants})";
                    bgImage.requestFocus();
                }
          },
          KeyFrame {
              time: 250ms
              values: [
                shopDetailsX => scrollXVal * -sceneWidth tween Interpolator.DISCRETE
              ]
          },
          KeyFrame {
              time: 500ms
              values: [
                  shopDetailsX => 0 tween Interpolator.LINEAR
              ]
         }
     ]
  };
  timeline.playFromStart();    
}

// Trim the string if length is greater than specified length
function trimString(string:String, length:Integer):String {
    if (string == null)
    return "";
    if (string.length() > length) {
        return "{string.substring(0, length).trim()}...";
    } else {
        return string;
    }
}

// Star Rating Images
var defaultStarImage = Image {
    url: "{__DIR__}images/star0.png"
}

var star = ImageView { 
    x: _starX
    y: _starY
    image: defaultStarImage
    visible: false
}

// Convert specified star rating in string to
// Integer and display as many number of star images
function setStars(starCount:String):Void {
    var imageSuffix = "0";
    try {
        var starIntCount = java.lang.Float.valueOf(starCount).intValue();
        imageSuffix = "{starIntCount}";
        if(starIntCount > 5) {
            imageSuffix = "5"; 
        } else if(starCount.indexOf(".") > 0) {
            imageSuffix = "{imageSuffix}.5"; 
        }
    }
    catch (e:java.lang.Exception) {
    }
    
    star.image = Image {
        url: "{__DIR__}images/star{imageSuffix}.png"
        placeholder: defaultStarImage
    }
    star.visible = true;
}

// Application Title
var titleBar = Rectangle {
    width: bind sceneWidth
    height: _titleBarHeight
    fill: Color.TRANSPARENT
    visible: bind ("{__PROFILE__}" != "browser")
    onMousePressed: function(e) {
        stageDragInitialX = e.screenX - stage.x;
        stageDragInitialY = e.screenY - stage.y;
    }
     onMouseDragged: function(e) {
        stage.x = e.screenX - stageDragInitialX;
        stage.y = e.screenY - stageDragInitialY;
     }
}

var title = "Nearest Coffee Shops";
var titleText:Label = Label {
    layoutX: bind (sceneWidth - titleText.boundsInLocal.width)/2.0
    layoutY: _titleTextY
    font: Font { name:"sansserif", size: 14 }
    styleClass:"textColor"
    text: bind title
}
// Divider
var divider = Line {
    startX: _dividerStartX
    startY: _dividerStartEndY
    endX: bind sceneWidth
    endY: _dividerStartEndY
    stroke: Color.rgb(138, 110, 72)
}
// Restaurant Name
var shopName = "";
var shopNameText = Label {
    layoutX: _shopNameTextX
    layoutY: _shopNameTextY
    font: Font { name:"sansserif", size: 13 }
    styleClass:"textColor"
    text: bind shopName
}

// Street Address
var address = "";
var addressText = Label {
    layoutX: _addressTextX
    layoutY: _addressTextY
    font: Font { name:"sansserif", size: _fontSize }
    styleClass:"textColor"
    text: bind address
}

// City and State
var city = "";
var cityText = Label {
    layoutX: _cityTextX
    layoutY: _cityTextY
    font: Font { name:"sansserif", size: _fontSize }
    styleClass:"textColor"
    text: bind city
}

// Phone Number
var phone = "";
var phoneText = Label {
    layoutX: _phoneTextX
    layoutY: _phoneTextY
    font: Font { name:"sansserif", size: _fontSize }
    styleClass:"textColor"
    text: bind phone
}

// Latest review comments
var comments = "";
var commentsText = Label {
    layoutX: _commentsTextX
    font:Font {
        name:"sansserif",
        size: 11
    }
    styleClass:"textColor"
    text: bind comments
    textWrap: true
    width:bind (sceneWidth - _commentsWdthPadding)
};
var scrollText:ScrollView = ScrollView {
    layoutX: _scrollTextX
    layoutY: _scrollTextY
    node: commentsText
    width: bind (sceneWidth - _scrollTextWdth)
    height: bind (vbox.translateY - _scrollTextHgt)
    style: "-fx-background-color: transparent;"
}


// Shop Details Group
var shopDetailsDisplay = Group {
    content: bind [
        shopNameText,
        addressText,
        cityText,
        phoneText,
        scrollText,
        star
    ]
    translateX: bind shopDetailsX
}
var shopDetailsGroup = Group {
    content: [ shopDetailsDisplay ]
    clip: Rectangle {
        x: _shopDetailX
        y: _shopDetailY
        width: bind (sceneWidth - _shopDetailWdth)
        height: bind (sceneHeight - _shopDetailHgt)
    }
}
// ZipCode
var zipCodeLabel = Label {
    font: Font { name:"sansserif", size: _fontSize }
    styleClass:"textColor"
    text: "zip code:"
    layoutInfo: LayoutInfo { 
        vpos: javafx.geometry.VPos.CENTER
    }
}
var zipCodeText: TextBox = TextBox {
    blocksMouse: true
    columns: 7
    selectOnFocus: false
    text: "95054"
    action: function() {
        zipCodeText.commit();
        searchCoffeeShops(zipCodeText.text.trim());
    }
    onKeyPressed:function(e:KeyEvent) {
        if(e.code == KeyCode.VK_UP) {
            bgImage.requestFocus();
//        } else if(e.code == KeyCode.VK_RIGHT) {
        } else if(e.code == KeyCode.VK_DOWN) {
            if("{__PROFILE__}" == "mobile") {
                searchButton.requestFocus();
            }
        }
    }
}

// Search for restaurants with in range of specified ZipCode
var searchButton = ImageButton { 
    layoutY: _searchBtnY
    normalImage: Image { url: "{__DIR__}images/search_normal.png" };
    overImage: Image { url: "{__DIR__}images/search_over.png" };
    focusImage: Image { url: "{__DIR__}images/search_focus.png" };
    
    onMouseClicked: function(e) {
        zipCodeText.commit();
        searchCoffeeShops(zipCodeText.text.trim());
    }

    onKeyPressed:function(e:KeyEvent) {
        if(e.code == KeyCode.VK_ENTER) {
            zipCodeText.commit();
            searchCoffeeShops(zipCodeText.text.trim());
        } else if(e.code == KeyCode.VK_UP) {
            bgImage.requestFocus();
        } else if(e.code == KeyCode.VK_LEFT) {
            zipCodeText.requestFocus();
        }
    }
}
var zipSearchPanel:HBox = HBox {
    nodeVPos:VPos.CENTER
    content: [
        zipCodeLabel,
        zipCodeText,
        searchButton
    ]
    spacing: _searchSpacing
};

// Service Provider Information
var serviceProviderText:Label = Label {
    font: Font { name:"sansserif", size: 11 }
    textFill: Color.rgb(96, 78, 51)
    text: "Web Services by Yahoo!"
}

def vbox:VBox = VBox {
    translateX: bind (sceneWidth - zipSearchPanel.boundsInLocal.width) / 2.0
    translateY: bind (sceneHeight - vbox.boundsInLocal.height - 4)  
    content : [zipSearchPanel, serviceProviderText];
    nodeHPos: HPos.CENTER;
}

var scene:Scene = Scene {
    stylesheets: bind _stylesheets
    content: Group {
        content: bind [
            bgImage,
            titleBar,
            titleText,
            divider,
            shopDetailsGroup,
            backButton,
            nextButton,
            closeButton,
            vbox
        ]
        clip: Rectangle {
            width: bind sceneWidth
            height: bind sceneHeight
            arcWidth: _rectArcWidth
            arcHeight: _rectArcWidth
        }
    }
    fill: Color.TRANSPARENT
}

// Application User Interface
var stage:Stage = Stage {
    title: "Coffee Shop Search"
    resizable: false
    style: StageStyle.TRANSPARENT
    scene: bind scene
    width: if(__PROFILE__ == "tv") 640 else 240
    height: if( __PROFILE__ == "tv") 480 else 320
}

public function alert(alertTitle:String, msg:String): Void {
    println(msg);
    phone = alertTitle;
    comments = trimString(msg, 240);
}

function run() {
    searchCoffeeShops("95054");
}
