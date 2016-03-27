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
package interesting;

import java.io.InputStream;
import java.lang.Exception;
import javafx.util.Math;
import javafx.io.http.HttpRequest;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import interesting.model.Photo;
import interesting.parser.PhotoPullParser;
import interesting.view.FullImageView;
import interesting.view.ImageButton;
import interesting.view.ThumbImageView;
import interesting.view.ArrowImageButton;
import interesting.view.PageImageButton;
import interesting.view.ProgressBar;
import interesting.view.Layout;
import interesting.view.Background;
import interesting.Constants.*;


/**
 * Sample FX Script Application for viewing some of Flickr's Interesting Photos
 * Click on the photo to view larger photo.
 */

var stageDragInitialX:Number;
var stageDragInitialY:Number;

var layout = Layout.getLayout(dimension);
var sceneWidth  : Number = bind scene.width;
var sceneHeight : Number = bind scene.height on replace {
    if(sceneHeight > 0) {
        fullImageView.show = false;
        try {
            layout = Layout.getLayout("{sceneWidth as Integer}X{sceneHeight as Integer}");
            var lytW = Math.min(layout.width, layout.height);
            var lytH = Math.max(layout.width, layout.height);
            dimension = "{lytW}X{lytH}";
            updateThumbImageViews();
            updatePageButtons();
            updateImages();
            bgRect.requestFocus();
            
        }
        catch ( exception : Exception ) {
            alert("Error", "{exception}");
        }
    }
}

// Photo-Grid Page Index
var focusOnBack = false;
var focusOnNext = false;
public var focusOnGrid = false on replace {
    if(focusOnGrid) {
        focusOnBack = false;
        focusOnNext = false;
    }
};
var pageButtons: PageImageButton[]; // Page Buttons
var page:Integer = 0 on replace {
    for(pb in pageButtons) {
        pb.selected = false;
    }
}
var photos:Photo[];                             // Information about all interesting photos

function getImage(imgURL : String, dimension : String) {
    Image { url: "{__DIR__}images/{dimension}/{imgURL}" };
}

// Application Background
var bgRect : Background = Background {
    width: bind layout.width
    height: bind layout.height
    thumbBoundsX: bind layout.thumbGroupX
    thumbBoundsY: bind layout.thumbGroupY
    thumbBoundsW: bind layout.thumbGroupW
    thumbBoundsH: bind layout.thumbGroupH
    descTextY: bind layout.descTextY
    onKeyPressed:function(e:KeyEvent) {
        if(fullImageView.show) {
            if(e.code == KeyCode.VK_ENTER) {
                fullImageView.show = false; 
            }
        }
        else if(e.code == KeyCode.VK_LEFT) {
            onLeftArrowKey();
        }
        else if(e.code == KeyCode.VK_RIGHT) {
            onRightArrowKey();
        }
        else if(e.code == KeyCode.VK_UP) {
            onUpArrowKey();
        }
        else if(e.code == KeyCode.VK_DOWN) {
            onDownArrowKey();
        }
        else if(e.code == KeyCode.VK_ENTER) {
            if(focusOnGrid) {
                showFullImage(thumbSelIndex);
            }
            else if(focusOnNext) {
                onNextClick();
            }
            else if(focusOnBack) {
                onBackClick();
            }
        }
    }
    onMousePressed:function(e:MouseEvent):Void {
        bgRect.requestFocus();
    }
}

function onLeftArrowKey() {
    if(focusOnGrid) {
        if(thumbSelIndex > 0) { 
            thumbSelIndex--; return;
        }
        onBackClick();
    }
    else {
        focusOnBack = true;
        focusOnNext = false;
    }
}

function onUpArrowKey() {
        if(not focusOnGrid) { 
            focusOnGrid = true;
            thumbSelIndex = 0;
            return;
        }
        if(thumbSelIndex < thumbCols) { // On first row
            focusOnGrid = false;
            focusOnBack = true;
        }
        else { // On other rows
            thumbSelIndex = thumbSelIndex - thumbCols;
        }
}

function onDownArrowKey() {
    if(not focusOnGrid) {
        focusOnGrid = true; 
        thumbSelIndex = 0;
        return; 
    }
    
    if(thumbSelIndex >= (thumbCols * (thumbRows - ondown_padding))) { // On last row
        focusOnGrid = false;
        focusOnNext = true;
    }
    else {
        thumbSelIndex = thumbSelIndex + thumbCols;
    }
}

function onRightArrowKey() {
    if(focusOnGrid) {
        if(thumbSelIndex < (sizeof thumbImageViews) - ondown_padding) {
            thumbSelIndex++; return;
        }
        onNextClick();
    }
    else {
        focusOnBack = false;
        focusOnNext = true;
    }
}

// Dispose Application
var closeButton:ImageView = ImageButton { 
    x: bind (layout.width - closeButton.image.width - padding_closebtnXY)
    y: padding_closebtnXY
    normalImage: bind getImage("close_n.png", dimension)
    hoverImage: bind getImage("close_h.png", dimension)
    visible: bind ("{__PROFILE__}" != "browser")
    onMousePressed: function(e) {
        javafx.lang.FX.exit();
    }
}

// Display next set of photos
var nextButton : ArrowImageButton = ArrowImageButton { 
    x: bind pageButtonGroup.translateX + layout.pageGroupW - nxtbtnX
    y: bind pageButtonGroup.translateY + layout.pageButtonH/padding_nxtbtn - nxtbtnY
    normalImage: bind getImage("next_n.png", dimension)
    hoverImage: bind getImage("next_h.png", dimension)
    active: bind focusOnNext
    visible: bind (not fullImageView.visible)
    onMousePressed: function(e) {
        nextButton.image = nextButton.hoverImage;
        onNextClick();
    }
}
function onNextClick() {
    if(sizeof photos <= 0) return;
    fullImageView.show = false;
    page++;
    if(page >= (sizeof pageButtons)) { page = 0; }
    thumbSelIndex = 0;
    updateImages();
}

// Display previous set of photos
var backButton : ArrowImageButton = ArrowImageButton { 
    x: bind pageButtonGroup.translateX - backbtnX + padding_backbtnX
    y: bind pageButtonGroup.translateY + layout.pageButtonH/padding_backbtnY - backbtnY/padding_backbtnY
    normalImage: bind getImage("back_n.png", dimension)
    hoverImage: bind getImage("back_h.png", dimension)
    active: bind focusOnBack
    visible: bind (not fullImageView.visible)
    onMousePressed: function(e) {
        backButton.image = backButton.hoverImage;
        onBackClick();
    }
}
function onBackClick() {
    if(sizeof photos <= 0) return;
    fullImageView.show = false;
    page--;
    if(page < 0) { page = (sizeof pageButtons) - 1; }
    thumbSelIndex = 8;
    updateImages();
}

public function setPage(index:Integer) {
    fullImageView.show = false;
    page = index;
    thumbSelIndex = 0;
    updateImages();
}

// Trim the string if length is greater than specified length
public function trimString(string:String, length:Integer) : String {
    if(string == null) return "";
    if(string.length() > length) { 
        return "{string.substring(0, length).trim()}..."; 
    }
    else {
        return string;
    }
}

// Photo Description and Page details
var desclabel: Label;
public var description = "Loading Photos...";
var thumbCols = bind layout.thumbCols;
var thumbRows = bind layout.thumbRows;

// Initialize Photo-Grid
var thumbImageViews: ThumbImageView[]; // Thumbnail images
public var thumbSelIndex = 0 on replace {
    for(tiv in thumbImageViews) {
        tiv.selected = false;
    }
    thumbImageViews[thumbSelIndex].selected = true;
}
var thumbImageViewGroup : VBox = VBox { 
    layoutX: bind (layout.width - layout.thumbGroupW)/thumpview_padding
    layoutY: bind (layout.height - layout.thumbGroupH)/thumpview_padding
    spacing: 1
};
function updateThumbImageViews() {
    // reset
    delete thumbImageViews;
    delete thumbImageViewGroup.content;
    for(row in [0..(thumbRows - 1)]) {
        var hBox = HBox { spacing: 1 };
        for(col in [0..(thumbCols - 1)]) {
            def thumbImageView = ThumbImageView { 
                width: layout.thumbSize
                height: layout.thumbSize
                index: (sizeof thumbImageViews)
            }
            insert thumbImageView into thumbImageViews;
            insert thumbImageView into hBox.content;
        }
        insert hBox into thumbImageViewGroup.content;
    }
    
    if(thumbSelIndex >= (sizeof thumbImageViews)) { thumbSelIndex = 0; }
    thumbImageViews[thumbSelIndex].selected = true;
}

var pageButtonImage = bind getImage("tab_n.png", dimension);
var pageButtonHoverImage = bind getImage("tab_h.png", dimension);
var pageButtonSelectImage = bind getImage("tab_s.png", dimension);

function updatePageButtons() {
    delete pageButtons;
    for(row in [0..layout.pageCount]) {
        insert PageImageButton {
            normalImage: pageButtonImage
            hoverImage: pageButtonHoverImage
            selectImage: pageButtonSelectImage
            index: (sizeof pageButtons)
        } into pageButtons;
    }
    if(page >= (sizeof pageButtons)) {
        page = 0;
    }
}

function getPageButtonY(y : Number) : Number {
    var maxY = y + layout.thumbGroupH;
    var maxH = layout.height - maxY;
    return maxY + maxH/padding_getpgebtnY - layout.pageButtonH/padding_getpgebtnYX;
}


var pageButtonGroup : HBox = HBox {
    content: bind pageButtons
    spacing: padding_pagebtn_spacing
    translateX: bind (layout.width - layout.pageGroupW)/padding_pagebtn_transX
    translateY: bind getPageButtonY(layout.thumbGroupY);
}

// Initialize fullscreen ImageView
public var fullImageView = FullImageView {
    x: bind layout.thumbGroupX
    y: bind layout.thumbGroupY
    width: bind layout.thumbGroupW
    height: bind layout.thumbGroupH
    visible: false
}
var progressBar : ProgressBar = ProgressBar {
    layoutX: bind (layout.thumbGroupX + padding_progressbar_layoutX)
    layoutY: bind (layout.thumbGroupY + layout.thumbGroupH - padding_progressbar_layoutY)
    width: bind (layout.thumbGroupW - padding_progressbar_width)
    visible: bind progressTimeline.running

}
var progressTimeline:Timeline = Timeline {
    repeatCount: Timeline.INDEFINITE
    keyFrames: [ 
        KeyFrame {
            time: 100ms
            action: function() {
                progressBar.progress = getProgress();
                if(fullImageView.visible) {
                    progressBar.opacity = fullImageView.opacity;
                }
                else {
                    progressBar.opacity = 1.0;
                }
                if(progressBar.progress == 100.0) {
                    progressTimeline.stop();
                }
            }
        }
    ]
};

function getProgress() : Number {
    if(fullImageView.visible) {
        return fullImageView.image.progress;
    }
    else {
        var totalProgress = 0.0;
        for(i in [0..((sizeof thumbImageViews) - 1)]) {
            totalProgress = totalProgress + thumbImageViews[i].image.progress;
        }
        if(totalProgress == 0) { return 0; }
        return totalProgress/(sizeof thumbImageViews); 
    }
}

public function showFullImage(index:Integer) {
    var tiv = thumbImageViews[index];
    fullImageView.image = Image {
        url: "http://farm{tiv.photo.farm}.static.flickr.com/{tiv.photo.server}/{tiv.photo.id}_{tiv.photo.secret}_m.jpg"
        placeholder: tiv.image
        backgroundLoading: true
    };
    
    fullImageView.show = true;
    progressBar.progress = padding_progressbar;
    progressTimeline.playFromStart();
}

// Load image and data specified in given Photo object
function loadImage(photo: Photo, thumbImageView: ThumbImageView): Void { 
    thumbImageView.image = Image {
        url: "http://farm{photo.farm}.static.flickr.com/{photo.server}/{photo.id}_{photo.secret}_s.jpg";
        backgroundLoading: true
        placeholder: thumbImageView.image
    };
    thumbImageView.photo = photo;
}

// Update images displayed in Photo-Grid
function updateImages() {
    if(not loadComplete) { return; }
    progressBar.progress = padding_progressbar;
    progressTimeline.playFromStart();
    for(i in [0..(sizeof thumbImageViews) - 1]) {
        var photoIndex = (page * (sizeof thumbImageViews)) + i;
        loadImage(photos[photoIndex], thumbImageViews[i]);
    }
    
    description = trimString("{thumbImageViews[thumbSelIndex].photo.title}", padding_descrption);
    pageButtons[page].selected = true;
}


// Application Title-Bar
var titleBar = Rectangle {
    width: bind layout.width
    height: bind layout.thumbGroupY
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
var stageContent:Node[];                       // Set stage content based on status of api-key

function initErrorUI() {
    desclabel = Label {
        layoutX: bind layout.thumbGroupX + initerrorui_padding
        layoutY: bind layout.thumbGroupY + initerrorui_padding
        font: bind layout.descFont
        text: "This application requires an api key from Flickr.\nGet yours from \nhttp://developer.yahoo.com/flickr"
        textFill: Color.WHITE
    }
    stageContent = [
        bgRect,
        titleBar,
        closeButton,
        desclabel
    ];
}

function initDefaultUI() {
    desclabel = Label {
        layoutX: bind layout.thumbGroupX
        layoutY: bind layout.descTextY - padding_label
        font: bind layout.descFont
        text: bind description
        textFill: Color.WHITE
        clip: Rectangle {
            layoutX: bind desclabel.layoutX - padding_rect_layoutY
            layoutY: bind (desclabel.layoutY - padding_rect_layoutY)
            width: bind (layout.width - padding_rectwidth)
            height: bind ( layout.thumbGroupY - desclabel.layoutY - padding_rectheight)
        }
    }
    
    stageContent = [
        bgRect, titleBar, nextButton, backButton, closeButton,
        desclabel, thumbImageViewGroup, pageButtonGroup,
        fullImageView, progressBar
    ];
    loadImageMetadata();
}

var loadComplete = false;
function loadImageMetadata() {
    println("Loading image metadata...");   
    description = "Loading Photos...";
    var httpRequestError: Boolean = false;
    // Submit HttpRequest
    var request: HttpRequest = HttpRequest {
        location: "http://api.flickr.com/services/rest/?method="
        "flickr.interestingness.getList&api_key={apiKey}&per_page={layout.imageCount}"
        method: HttpRequest.GET
       
        onRead: function(bytes: Long) {
            // The toread variable is non negative only if the server provides the content length
            def loadProgress = if (request.toread > 0) "{(bytes * 100 / request.toread)}%" else "";
            description = "Loading Photos... ({loadProgress})";
        }
        
        onException: function(exception: Exception) {
            exception.printStackTrace();
            alert("Error", "{exception}");
            httpRequestError = true;
        }
            
        onResponseCode: function(responseCode:Integer) {
            if (responseCode != 200) {
                println("failed, response: {responseCode} {request.responseMessage}");
            }
        }
                        
        onInput: function(input: java.io.InputStream) {
            try {
                var parser = PhotoPullParser {
                    onDone: function( data:Photo[] ) {
                        photos = data;
                        if(not httpRequestError) {
                            loadComplete = true;
                            updateImages(); 
                            focusOnGrid = true;
                        }
                    } 
                };
                parser.parse(input);
                if(parser.errorMessage.length() > 0) { 
                    alert("Error", parser.errorMessage);
                    httpRequestError = true; 
                }
                
            }
            finally {
                input.close();
            }
        }
    }
    request.start();
}

function alert(alertTitle:String, msg:String): Void {
    println(msg);
    description = trimString("{alertTitle}: {msg}", padding_alert);
}

function initUI() {    
    // If API-Key is empty, display error
    if (apiKey == null) {
        initErrorUI();
    }
    else {
        initDefaultUI();                        // Retrieve photo information and display
    }
}

var scene : Scene = Scene {
    width: stage_widht
    height: stage_height
    content: Group {
        content: bind stageContent
        clip: Rectangle {
            width: bind layout.width
            height: bind layout.height
            arcWidth: arcwidhtheight_padding
            arcHeight: arcwidhtheight_padding
        }
    }
    fill: Color.TRANSPARENT
};

// Application User Interface
var stage = Stage {
    title: "Interesting Photos"
    resizable: false
    width: stage_widht
    height: stage_height
    visible: false
    style: StageStyle.TRANSPARENT
    scene: bind scene
}

function run() {
    initUI();    
    stage.visible = true;
    bgRect.requestFocus();                      // Set focus to background by default
}
