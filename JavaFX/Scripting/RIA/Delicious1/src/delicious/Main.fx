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
 
package delicious;

public class Main {
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:main
    public-read def userTitle: javafx.scene.control.Label = javafx.scene.control.Label {
        text: "User"
    }
    
    def __layoutInfo_user: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 228.0
    }
    public-read def user: javafx.scene.control.TextBox = javafx.scene.control.TextBox {
        visible: true
        layoutInfo: __layoutInfo_user
        action: showButtonAction
    }
    
    public-read def tagTitle: javafx.scene.control.Label = javafx.scene.control.Label {
        text: "Tags (comma separated list)"
    }
    
    def __layoutInfo_tag: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 228.0
    }
    public-read def tag: javafx.scene.control.TextBox = javafx.scene.control.TextBox {
        layoutInfo: __layoutInfo_tag
        action: showButtonAction
    }
    
    def __layoutInfo_showButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hpos: javafx.geometry.HPos.RIGHT
    }
    public-read def showButton: javafx.scene.control.Button = javafx.scene.control.Button {
        visible: true
        layoutInfo: __layoutInfo_showButton
        text: "Show"
        action: showButtonAction
    }
    
    def __layoutInfo_vbox: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 228.0
    }
    public-read def vbox: javafx.scene.layout.VBox = javafx.scene.layout.VBox {
        layoutX: 6.0
        layoutY: 40.0
        layoutInfo: __layoutInfo_vbox
        content: [ userTitle, user, tagTitle, tag, showButton, ]
        spacing: 6.0
    }
    
    def __layoutInfo_listView: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 228.0
        height: 180.0
    }
    public-read def listView: javafx.scene.control.ListView = javafx.scene.control.ListView {
        visible: true
        layoutInfo: __layoutInfo_listView
        items: bind httpDataSource.getRecordSet().all()
        cellFactory: listCellFactory
    }
    
    def __layoutInfo_hyperlink: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
    }
    public-read def hyperlink: javafx.scene.control.Hyperlink = javafx.scene.control.Hyperlink {
        visible: true
        layoutInfo: __layoutInfo_hyperlink
        text: bind if (listView.selectedItem !=null) (listView.selectedItem as org.netbeans.javafx.datasrc.Record).getString("u") else "<Click on items to see the URL>"
    }
    
    public-read def backButton: javafx.scene.control.Button = javafx.scene.control.Button {
        visible: true
        text: "Back"
        action: backButtonAction
    }
    
    def __layoutInfo_progressIndicator: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 30.0
        height: 30.0
    }
    public-read def progressIndicator: javafx.scene.control.ProgressIndicator = javafx.scene.control.ProgressIndicator {
        visible: bind httpDataSource.fetchingData
        layoutInfo: __layoutInfo_progressIndicator
    }
    
    public-read def hbox: javafx.scene.layout.HBox = javafx.scene.layout.HBox {
        visible: true
        content: [ backButton, progressIndicator, ]
        spacing: 6.0
    }
    
    public-read def vbox2: javafx.scene.layout.VBox = javafx.scene.layout.VBox {
        visible: true
        layoutX: 6.0
        layoutY: 40.0
        translateX: 100.0
        translateY: 140.0
        scaleX: 0.1
        scaleY: 0.1
        content: [ listView, hyperlink, hbox, ]
        spacing: 6.0
    }
    
    public-read def image: javafx.scene.image.Image = javafx.scene.image.Image {
        url: "{__DIR__}resources/favicon16.png"
    }
    
    public-read def imageView: javafx.scene.image.ImageView = javafx.scene.image.ImageView {
        layoutX: 6.0
        layoutY: 6.0
        image: image
    }
    
    public-read def font: javafx.scene.text.Font = javafx.scene.text.Font {
        size: 16.0
    }
    
    public-read def label: javafx.scene.control.Label = javafx.scene.control.Label {
        layoutX: 28.0
        layoutY: 6.0
        text: "Delicious Browser"
        font: font
    }
    
    public-read def scene: javafx.scene.Scene = javafx.scene.Scene {
        width: 240.0
        height: 320.0
        content: getDesignRootNodes ()
    }
    
    public-read def httpDataSource: org.netbeans.javafx.datasrc.HttpDataSource = org.netbeans.javafx.datasrc.HttpDataSource {
        url: ""
        parser: org.netbeans.javafx.datasrc.Parsers.JSON_PARSER
    }
    
    public-read def currentState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
        names: [ "Search", "List", ]
        actual: 0
        timelines: [
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            vbox.translateX => vbox.translateX tween javafx.animation.Interpolator.DISCRETE,
                            vbox.translateY => vbox.translateY tween javafx.animation.Interpolator.DISCRETE,
                            vbox.scaleX => vbox.scaleX tween javafx.animation.Interpolator.DISCRETE,
                            vbox.scaleY => vbox.scaleY tween javafx.animation.Interpolator.DISCRETE,
                            vbox2.translateX => vbox2.translateX tween javafx.animation.Interpolator.DISCRETE,
                            vbox2.translateY => vbox2.translateY tween javafx.animation.Interpolator.DISCRETE,
                            vbox2.scaleX => vbox2.scaleX tween javafx.animation.Interpolator.DISCRETE,
                            vbox2.scaleY => vbox2.scaleY tween javafx.animation.Interpolator.DISCRETE,
                        ]
                        action: function() {
                            httpDataSource.autoRefresh = true;
                        }
                    }
                    javafx.animation.KeyFrame {
                        time: 800ms
                        values: [
                            vbox.translateX => 0.0 tween javafx.animation.Interpolator.EASEBOTH,
                            vbox.translateY => 0.0 tween javafx.animation.Interpolator.EASEBOTH,
                            vbox.scaleX => 1.0 tween javafx.animation.Interpolator.EASEBOTH,
                            vbox.scaleY => 1.0 tween javafx.animation.Interpolator.EASEBOTH,
                            vbox2.translateX => 100.0 tween javafx.animation.Interpolator.EASEBOTH,
                            vbox2.translateY => 140.0 tween javafx.animation.Interpolator.EASEBOTH,
                            vbox2.scaleX => 0.1 tween javafx.animation.Interpolator.EASEBOTH,
                            vbox2.scaleY => 0.1 tween javafx.animation.Interpolator.EASEBOTH,
                        ]
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            vbox.translateX => vbox.translateX tween javafx.animation.Interpolator.DISCRETE,
                            vbox.translateY => vbox.translateY tween javafx.animation.Interpolator.DISCRETE,
                            vbox.scaleX => vbox.scaleX tween javafx.animation.Interpolator.DISCRETE,
                            vbox.scaleY => vbox.scaleY tween javafx.animation.Interpolator.DISCRETE,
                            vbox2.translateX => vbox2.translateX tween javafx.animation.Interpolator.DISCRETE,
                            vbox2.translateY => vbox2.translateY tween javafx.animation.Interpolator.DISCRETE,
                            vbox2.scaleX => vbox2.scaleX tween javafx.animation.Interpolator.DISCRETE,
                            vbox2.scaleY => vbox2.scaleY tween javafx.animation.Interpolator.DISCRETE,
                        ]
                        action: function() {
                            __layoutInfo_hyperlink.width = 228.0;
                            httpDataSource.autoRefresh = false;
                        }
                    }
                    javafx.animation.KeyFrame {
                        time: 800ms
                        values: [
                            vbox.translateX => -96.0 tween javafx.animation.Interpolator.EASEBOTH,
                            vbox.translateY => 200.0 tween javafx.animation.Interpolator.EASEBOTH,
                            vbox.scaleX => 0.1 tween javafx.animation.Interpolator.EASEBOTH,
                            vbox.scaleY => 0.1 tween javafx.animation.Interpolator.EASEBOTH,
                            vbox2.translateX => 0.0 tween javafx.animation.Interpolator.EASEBOTH,
                            vbox2.translateY => 0.0 tween javafx.animation.Interpolator.EASEBOTH,
                            vbox2.scaleX => 1.0 tween javafx.animation.Interpolator.EASEBOTH,
                            vbox2.scaleY => 1.0 tween javafx.animation.Interpolator.EASEBOTH,
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
            text: bind (listCell.item as org.netbeans.javafx.datasrc.Record).getString("d")
        }
        
        listCell = javafx.scene.control.ListCell {
            node: listCellLabel
        }
        
        return listCell
    }
    
    public function getDesignRootNodes (): javafx.scene.Node[] {
        [ imageView, label, vbox, vbox2, ]
    }
    
    public function getDesignScene (): javafx.scene.Scene {
        scene
    }
    // </editor-fold>//GEN-END:main

    def URL = "http://feeds.delicious.com/v2/json";

    function showButtonAction (): Void {
        def u = user.text.trim();
        // required by mobile profile
        def t = tag.text.trim().replace(','.charAt(0),'+'.charAt(0));
        var newURL = URL;
        if (u != "") {
            if (t != "") {
                newURL = "{URL}/{u}/{t}"
            } else {
                newURL = "{URL}/{u}"
            }
        } else {
            if (t != "") {
                newURL = "{URL}/tag/{t}"
            }
        }
        listView.select(-1);
        httpDataSource.url = newURL;
        currentState.next();
    }

    function backButtonAction (): Void {
        currentState.previous();
    }

}

function run (): Void {
    var design = Main {};

    javafx.stage.Stage {
        title: "Delicious"
        scene: design.getDesignScene ()
    }
}
