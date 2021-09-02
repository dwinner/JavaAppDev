/*
 * Copyright (c) 2010, Oracle
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 *  * Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the distribution.
 *  * Neither the name of Oracle nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */
 
package twitter;

import javafx.io.http.URLConverter;
import javafx.data.Pair;

public class Twitter {

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:main
    public-read def label: javafx.scene.control.Label = javafx.scene.control.Label {
        layoutX: 28.0
        layoutY: 6.0
        text: "Tweets:"
    }
    
    public-read def listProgressIndicator: javafx.scene.control.ProgressIndicator = javafx.scene.control.ProgressIndicator {
        visible: bind httpDataSource.fetchingData
        layoutX: 310.0
        layoutY: 5.0
    }
    
    public-read def logoutButton: javafx.scene.control.Button = javafx.scene.control.Button {
        layoutX: 340.0
        layoutY: 4.0
        text: "Logout"
        action: logoutButtonAction
    }
    
    public-read def refreshButton: javafx.scene.control.Button = javafx.scene.control.Button {
        disable: bind httpDataSource.fetchingData
        layoutX: 407.0
        layoutY: 4.0
        text: "Refresh"
        action: refreshButtonAction
    }
    
    def __layoutInfo_listView: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 470.0
        height: 251.0
    }
    public-read def listView: javafx.scene.control.ListView = javafx.scene.control.ListView {
        layoutX: 6.0
        layoutY: 32.0
        layoutInfo: __layoutInfo_listView
        items: bind httpDataSource.getDataSource("status").getRecordSet().all()
        cellFactory: listCellFactory
    }
    
    def __layoutInfo_textbox: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 372.0
        height: 23.0
    }
    public-read def textbox: javafx.scene.control.TextBox = javafx.scene.control.TextBox {
        layoutX: 6.0
        layoutY: 290.0
        layoutInfo: __layoutInfo_textbox
    }
    
    public-read def tweetProgressIndicator: javafx.scene.control.ProgressIndicator = javafx.scene.control.ProgressIndicator {
        visible: bind progressIndicator2Visible
        layoutX: 382.0
        layoutY: 290.0
    }
    
    def __layoutInfo_tweetButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 56.0
        height: 26.0
    }
    public-read def tweetButton: javafx.scene.control.Button = javafx.scene.control.Button {
        disable: bind button2Disable
        layoutX: 415.0
        layoutY: 287.0
        layoutInfo: __layoutInfo_tweetButton
        text: "Tweet!"
        action: tweetButtonAction
    }
    
    public-read def label2: javafx.scene.control.Label = javafx.scene.control.Label {
        visible: true
        layoutX: 67.0
        layoutY: 70.0
        text: "Username:"
    }
    
    def __layoutInfo_tbUser: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 184.0
        height: 23.0
    }
    public-read def tbUser: javafx.scene.control.TextBox = javafx.scene.control.TextBox {
        visible: true
        layoutX: 135.0
        layoutY: 66.0
        layoutInfo: __layoutInfo_tbUser
        action: loginButtonAction
    }
    
    public-read def label3: javafx.scene.control.Label = javafx.scene.control.Label {
        visible: true
        layoutX: 67.0
        layoutY: 103.0
        text: "Password:"
    }
    
    def __layoutInfo_loginButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 62.0
        height: 23.0
    }
    public-read def loginButton: javafx.scene.control.Button = javafx.scene.control.Button {
        visible: true
        disable: bind tbUser.rawText == null or tbPassword.rawText == null
        layoutX: 257.0
        layoutY: 135.0
        layoutInfo: __layoutInfo_loginButton
        text: "Login"
        action: loginButtonAction
    }
    
    def __layoutInfo_tbPassword: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 184.0
        height: 21.0
    }
    public-read def tbPassword: javafx.scene.control.PasswordBox = javafx.scene.control.PasswordBox {
        layoutX: 135.0
        layoutY: 99.0
        layoutInfo: __layoutInfo_tbPassword
        action: loginButtonAction
    }
    
    public-read def image: javafx.scene.image.Image = javafx.scene.image.Image {
        url: "{__DIR__}twitter_48.png"
    }
    
    public-read def imageView: javafx.scene.image.ImageView = javafx.scene.image.ImageView {
        layoutX: 87.0
        layoutY: 8.0
        image: image
    }
    
    public-read def image2: javafx.scene.image.Image = javafx.scene.image.Image {
        url: "{__DIR__}twitter.png"
    }
    
    public-read def imageView2: javafx.scene.image.ImageView = javafx.scene.image.ImageView {
        layoutX: 135.0
        layoutY: 8.0
        image: image2
    }
    
    def __layoutInfo_panel2: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 402.0
        height: 243.0
    }
    public-read def panel2: javafx.scene.layout.Panel = javafx.scene.layout.Panel {
        visible: true
        layoutX: 41.0
        layoutY: 39.0
        layoutInfo: __layoutInfo_panel2
        content: [ imageView, imageView2, label2, tbUser, label3, loginButton, tbPassword, ]
    }
    
    public-read def image3: javafx.scene.image.Image = javafx.scene.image.Image {
        url: "{__DIR__}twitter_16.png"
    }
    
    public-read def imageView3: javafx.scene.image.ImageView = javafx.scene.image.ImageView {
        layoutX: 6.0
        layoutY: 5.0
        image: image3
    }
    
    def __layoutInfo_panel: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 480.0
        height: 320.0
    }
    public-read def panel: javafx.scene.layout.Panel = javafx.scene.layout.Panel {
        layoutX: 500.0
        layoutY: 0.0
        layoutInfo: __layoutInfo_panel
        content: [ imageView3, label, listProgressIndicator, logoutButton, refreshButton, listView, textbox, tweetProgressIndicator, tweetButton, ]
    }
    
    public-read def scene: javafx.scene.Scene = javafx.scene.Scene {
        width: 480.0
        height: 320.0
        content: getDesignRootNodes ()
    }
    
    public-read def httpDataSource: org.netbeans.javafx.datasrc.HttpDataSource = org.netbeans.javafx.datasrc.HttpDataSource {
        url: "http://twitter.com/statuses/home_timeline.xml"
        user: ""
        password: ""
        parser: org.netbeans.javafx.datasrc.Parsers.XML_PARSER
    }
    
    public-read def currentState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
        names: [ "login", "twitter", ]
        actual: 0
        timelines: [
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            label.layoutX => label.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            label.layoutY => label.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            listView.layoutX => listView.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            listView.layoutY => listView.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutX => panel.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            panel2.layoutX => panel2.layoutX tween javafx.animation.Interpolator.DISCRETE,
                        ]
                    }
                    javafx.animation.KeyFrame {
                        time: 800ms
                        values: [
                            label.layoutX => 28.0 tween javafx.animation.Interpolator.DISCRETE,
                            label.layoutY => 6.0 tween javafx.animation.Interpolator.DISCRETE,
                            listView.layoutX => 6.0 tween javafx.animation.Interpolator.DISCRETE,
                            listView.layoutY => 32.0 tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutX => 500.0 tween javafx.animation.Interpolator.EASEOUT,
                            panel2.layoutX => 41.0 tween javafx.animation.Interpolator.EASEIN,
                        ]
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            label.layoutX => label.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            label.layoutY => label.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            listView.layoutX => listView.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            listView.layoutY => listView.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutX => panel.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            panel2.layoutX => panel2.layoutX tween javafx.animation.Interpolator.DISCRETE,
                        ]
                    }
                    javafx.animation.KeyFrame {
                        time: 800ms
                        values: [
                            label.layoutX => 28.0 tween javafx.animation.Interpolator.DISCRETE,
                            label.layoutY => 6.0 tween javafx.animation.Interpolator.DISCRETE,
                            listView.layoutX => 6.0 tween javafx.animation.Interpolator.DISCRETE,
                            listView.layoutY => 32.0 tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutX => 0.0 tween javafx.animation.Interpolator.EASEIN,
                            panel2.layoutX => -420.0 tween javafx.animation.Interpolator.EASEOUT,
                        ]
                    }
                ]
            }
        ]
    }
    
    function listCellFactory(): javafx.scene.control.ListCell {
        var listCell: javafx.scene.control.ListCell;
        
        def listCellLabel: javafx.scene.control.Label = javafx.scene.control.Label {
            visible: bind not listCell.empty
            text: bind toString(listCell.item as org.netbeans.javafx.datasrc.Record)
        }
        
        listCell = javafx.scene.control.ListCell {
            node: listCellLabel
        }
        
        return listCell
    }
    
    public function getDesignRootNodes (): javafx.scene.Node[] {
        [ panel, panel2, ]
    }
    
    public function getDesignScene (): javafx.scene.Scene {
        scene
    }
    // </editor-fold>//GEN-END:main

    function toString(r: org.netbeans.javafx.datasrc.Record) : String {
        r.getXmlElementText("text")
    }

    function logoutButtonAction (): Void {
        tbPassword.text = "";
        refreshCredentials();
        currentState.previous();
    }

    function loginButtonAction (): Void {
        if (tbUser.text == null  or  tbPassword.text == null)
            return;
        refreshCredentials();
        currentState.next();
    }

    function refreshCredentials(): Void {
        httpDataSource.user = tbUser.text;
        httpDataSource.password = tbPassword.text;
        postDS.user = tbUser.text;
        postDS.password = tbPassword.text;
    }

    var button2Disable: Boolean = bind postDS.fetchingData;

    var progressIndicator2Visible: Boolean = bind postDS.fetchingData;

    var postDS = TwitterPoster {
        url: "http://twitter.com/statuses/update.xml"
        connectionMethod: "POST"
        parser: org.netbeans.javafx.datasrc.Parsers.XML_PARSER
        onDataFetched: function() {
            textbox.text = "";
            httpDataSource.refresh();
        }

    };

    function refreshButtonAction (): Void {
        httpDataSource.refresh();
    }

    function tweetButtonAction (): Void {
        var urlc = URLConverter {};
        var eparams = urlc.encodeParameters([
            Pair {
                name: "status"
                value: textbox.text
            }
        ]);
        postDS.requestParams  = eparams;
    }

}

class TwitterPoster extends org.netbeans.javafx.datasrc.HttpDataSource {

    public-init var onDataFetched: function() : Void;

    override protected function dataFetched(newData : org.netbeans.javafx.datasrc.RecordSet) {
        onDataFetched();
        super.dataFetched(newData);
    }
}

class DummyAuth extends java.net.Authenticator {

    var app : Twitter;

    override protected function getPasswordAuthentication() : java.net.PasswordAuthentication {
        app.currentState.actual = app.currentState.findIndex("login");
        null
    }
}

function run (): Void {
    
    var design = Twitter {};

    // This servers two purposes:
    // 1) Disable automatic login popup of the java platform
    // 2) Switch to the "login" state if login credentials are not valid (error response from server)
    java.net.Authenticator.setDefault(DummyAuth {
        app: design
    });

    javafx.stage.Stage {
        title: "Twitter"
        scene: design.getDesignScene ()
    }
}
