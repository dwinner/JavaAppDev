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
 
package googlesearch;

import javafx.io.http.URLConverter;

public class Main {

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:main
    public-read def label: javafx.scene.control.Label = javafx.scene.control.Label {
        text: "Search:"
    }
    
    def __layoutInfo_searchTextBox: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 265.0
    }
    public-read def searchTextBox: javafx.scene.control.TextBox = javafx.scene.control.TextBox {
        layoutInfo: __layoutInfo_searchTextBox
    }
    
    public-read def goButton: javafx.scene.control.Button = javafx.scene.control.Button {
        text: "Go"
        action: goButtonAction
    }
    
    public-read def hbox: javafx.scene.layout.HBox = javafx.scene.layout.HBox {
        layoutX: 65.0
        layoutY: 20.0
        content: [ label, searchTextBox, goButton, ]
        spacing: 6.0
        nodeVPos: javafx.geometry.VPos.CENTER
    }
    
    def __layoutInfo_listView: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 460.0
        height: 250.0
    }
    public-read def listView: javafx.scene.control.ListView = javafx.scene.control.ListView {
        visible: false
        opacity: 0.0
        layoutInfo: __layoutInfo_listView
        items: bind httpDataSource.getDataSource("responseData/results").getRecordSet().all()
        cellFactory: listCellFactory
    }
    
    public-read def titleLabel: javafx.scene.control.Label = javafx.scene.control.Label {
    }
    
    public-read def urlLabel: javafx.scene.control.Label = javafx.scene.control.Label {
    }
    
    def __layoutInfo_detailsBox: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 460.0
    }
    public-read def detailsBox: javafx.scene.layout.VBox = javafx.scene.layout.VBox {
        visible: false
        opacity: 0.0
        layoutInfo: __layoutInfo_detailsBox
        content: [ titleLabel, urlLabel, ]
        spacing: 6.0
    }
    
    def __layoutInfo_vbox: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 480.0
    }
    public-read def vbox: javafx.scene.layout.VBox = javafx.scene.layout.VBox {
        layoutY: 130.0
        layoutInfo: __layoutInfo_vbox
        content: [ hbox, listView, detailsBox, ]
        padding: javafx.geometry.Insets { left: 6.0, top: 0.0, right: 6.0, bottom: 0.0 }
        spacing: 20.0
        hpos: javafx.geometry.HPos.CENTER
        nodeHPos: javafx.geometry.HPos.CENTER
    }
    
    public-read def logoImage: javafx.scene.image.Image = javafx.scene.image.Image {
        url: "http://www.google.com/images/logo.gif"
    }
    
    public-read def logoImageView: javafx.scene.image.ImageView = javafx.scene.image.ImageView {
        layoutX: 97.0
        layoutY: 6.0
        image: logoImage
    }
    
    public-read def backgroundGradient: javafx.scene.paint.LinearGradient = javafx.scene.paint.LinearGradient {
        endX: 0.0
        stops: [ javafx.scene.paint.Stop { offset: 0.0, color: javafx.scene.paint.Color.web ("#FFFFFF") }, javafx.scene.paint.Stop { offset: 0.4, color: javafx.scene.paint.Color.web ("#FFFFFF") }, javafx.scene.paint.Stop { offset: 0.6, color: javafx.scene.paint.Color.web ("#CCCCCC") }, javafx.scene.paint.Stop { offset: 1.0, color: javafx.scene.paint.Color.web ("#FFFFFF") }, ]
    }
    
    public-read def scene: javafx.scene.Scene = javafx.scene.Scene {
        width: 480.0
        height: 320.0
        content: getDesignRootNodes ()
        fill: backgroundGradient
    }
    
    public-read def httpDataSource: org.netbeans.javafx.datasrc.HttpDataSource = org.netbeans.javafx.datasrc.HttpDataSource {
        url: ""
        parser: org.netbeans.javafx.datasrc.Parsers.JSON_PARSER
    }
    
    public-read def searchState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
        names: [ "HomePage", "Results", ]
        actual: 0
        timelines: [
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        action: function() {
                            logoImageView.opacity = 1.0;
                            logoImageView.layoutY = 6.0;
                            listView.visible = false;
                            listView.opacity = 0.0;
                            vbox.layoutX = 0.0;
                            vbox.layoutY = 130.0;
                        }
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            logoImageView.opacity => logoImageView.opacity tween javafx.animation.Interpolator.DISCRETE,
                            logoImageView.layoutY => logoImageView.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            listView.opacity => listView.opacity tween javafx.animation.Interpolator.DISCRETE,
                            vbox.layoutX => vbox.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            vbox.layoutY => vbox.layoutY tween javafx.animation.Interpolator.DISCRETE,
                        ]
                        action: function() {
                            listView.visible = true;
                        }
                    }
                    javafx.animation.KeyFrame {
                        time: 500ms
                        values: [
                            logoImageView.opacity => 1.0 tween javafx.animation.Interpolator.EASEBOTH,
                            logoImageView.layoutY => -124.0 tween javafx.animation.Interpolator.EASEBOTH,
                            listView.opacity => 1.0 tween javafx.animation.Interpolator.EASEBOTH,
                            vbox.layoutX => 0.0 tween javafx.animation.Interpolator.EASEBOTH,
                            vbox.layoutY => 10.0 tween javafx.animation.Interpolator.EASEBOTH,
                        ]
                    }
                ]
            }
        ]
    }
    
    public-read def detailsState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
        names: [ "No Details", "Details", ]
        actual: 0
        timelines: [
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            __layoutInfo_listView.height => __layoutInfo_listView.height tween javafx.animation.Interpolator.DISCRETE,
                            detailsBox.opacity => detailsBox.opacity tween javafx.animation.Interpolator.DISCRETE,
                        ]
                    }
                    javafx.animation.KeyFrame {
                        time: 500ms
                        values: [
                            __layoutInfo_listView.height => 250.0 tween javafx.animation.Interpolator.EASEBOTH,
                            detailsBox.opacity => 0.0 tween javafx.animation.Interpolator.EASEBOTH,
                        ]
                        action: function() {
                            detailsBox.visible = false;
                        }
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            __layoutInfo_listView.height => __layoutInfo_listView.height tween javafx.animation.Interpolator.DISCRETE,
                            detailsBox.opacity => detailsBox.opacity tween javafx.animation.Interpolator.DISCRETE,
                        ]
                        action: function() {
                            detailsBox.visible = true;
                        }
                    }
                    javafx.animation.KeyFrame {
                        time: 500ms
                        values: [
                            __layoutInfo_listView.height => 170.0 tween javafx.animation.Interpolator.EASEBOTH,
                            detailsBox.opacity => 1.0 tween javafx.animation.Interpolator.EASEBOTH,
                        ]
                    }
                ]
            }
        ]
    }
    
    function listCellFactory(): javafx.scene.control.ListCell {
        var listCell: javafx.scene.control.ListCell;
        
        def label2: javafx.scene.control.Label = javafx.scene.control.Label {
            visible: bind not listCell.empty
            text: bind (listCell.item as org.netbeans.javafx.datasrc.Record).getString("titleNoFormatting")
        }
        
        listCell = javafx.scene.control.ListCell {
            node: label2
        }
        
        return listCell
    }
    
    public function getDesignRootNodes (): javafx.scene.Node[] {
        [ logoImageView, vbox, ]
    }
    
    public function getDesignScene (): javafx.scene.Scene {
        scene
    }
    // </editor-fold>//GEN-END:main

    var selectedResult = bind listView.selectedItem as org.netbeans.javafx.datasrc.Record on replace {
        titleLabel.text = "Title: {selectedResult.getString ("titleNoFormatting")}";
        urlLabel.text = "URL: {new URLConverter ().decodeString(selectedResult.getString ("url"))}";
        detailsState.actual = if (selectedResult != null) then 1 else 0;
    }

    function goButtonAction(): Void {
        httpDataSource.url = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&rsz=large&q={new URLConverter ().encodeString(searchTextBox.text)}";
        searchState.actual = 1;
        listView.select(-1);
    }

}

function run (): Void {
    var design = Main {};

    javafx.stage.Stage {
        title: "Google Search"
        scene: design.getDesignScene ()
    }
}
