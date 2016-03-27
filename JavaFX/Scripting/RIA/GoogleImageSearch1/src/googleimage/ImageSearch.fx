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
 
package googleimage;

import javafx.scene.image.Image;

public class ImageSearch {

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:main
    def __layoutInfo_progressIndicator: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 30.0
        height: 30.0
    }
    public-read def progressIndicator: javafx.scene.control.ProgressIndicator = javafx.scene.control.ProgressIndicator {
        visible: bind httpDataSource.fetchingData
        layoutInfo: __layoutInfo_progressIndicator
    }
    
    def __layoutInfo_textbox: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 330.0
        height: 23.0
    }
    public-read def textbox: javafx.scene.control.TextBox = javafx.scene.control.TextBox {
        layoutInfo: __layoutInfo_textbox
        action: searchButtonAction
    }
    
    public-read def searchButton: javafx.scene.control.Button = javafx.scene.control.Button {
        layoutX: 415.0
        layoutY: 17.0
        text: "Search"
        action: searchButtonAction
    }
    
    def __layoutInfo_hbox2: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hpos: javafx.geometry.HPos.CENTER
    }
    public-read def hbox2: javafx.scene.layout.HBox = javafx.scene.layout.HBox {
        layoutInfo: __layoutInfo_hbox2
        content: [ progressIndicator, textbox, searchButton, ]
        spacing: 6.0
    }
    
    def __layoutInfo_titleLabel: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 188.0
        height: 15.0
        hpos: javafx.geometry.HPos.CENTER
    }
    public-read def titleLabel: javafx.scene.control.Label = javafx.scene.control.Label {
        layoutInfo: __layoutInfo_titleLabel
        text: bind httpDataSource.getDataSource("responseData/results").getRecordSet().currentString("contentNoFormatting")
        hpos: javafx.geometry.HPos.CENTER
    }
    
    def __layoutInfo_prevButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        vpos: javafx.geometry.VPos.CENTER
    }
    public-read def prevButton: javafx.scene.control.Button = javafx.scene.control.Button {
        disable: bind not httpDataSource.getDataSource("responseData/results").getRecordSet().hasPrev()
        layoutInfo: __layoutInfo_prevButton
        text: "<"
        action: prevButtonAction
    }
    
    def __layoutInfo_nextButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        vpos: javafx.geometry.VPos.CENTER
    }
    public-read def nextButton: javafx.scene.control.Button = javafx.scene.control.Button {
        disable: bind not httpDataSource.getDataSource("responseData/results").getRecordSet().hasNext()
        layoutInfo: __layoutInfo_nextButton
        text: ">"
        action: nextButtonAction
    }
    
    def __layoutInfo_detailsImageView: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hpos: javafx.geometry.HPos.CENTER
        vpos: javafx.geometry.VPos.CENTER
    }
    public-read def detailsImageView: javafx.scene.image.ImageView = javafx.scene.image.ImageView {
        opacity: 0.0
        layoutInfo: __layoutInfo_detailsImageView
        image: bind images[index]
        fitWidth: bind scene.width
        fitHeight: bind scene.height
        preserveRatio: true
    }
    
    def __layoutInfo_stack: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: bind scene.width
        height: bind scene.height
    }
    public-read def stack: javafx.scene.layout.Stack = javafx.scene.layout.Stack {
        visible: true
        layoutX: 0.0
        layoutY: 0.0
        layoutInfo: __layoutInfo_stack
        content: [ detailsImageView, ]
    }
    
    public-read def loadingImage: javafx.scene.image.Image = javafx.scene.image.Image {
        url: "{__DIR__}loading.png"
    }
    
    public-read def reflectionEffect: javafx.scene.effect.Reflection = javafx.scene.effect.Reflection {
        fraction: 0.5
    }
    
    def __layoutInfo_rightImageView: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hpos: javafx.geometry.HPos.CENTER
        vpos: javafx.geometry.VPos.CENTER
    }
    public-read def rightImageView: javafx.scene.image.ImageView = javafx.scene.image.ImageView {
        layoutInfo: __layoutInfo_rightImageView
        onMouseClicked: rightImageViewOnMouseClicked
        effect: reflectionEffect
        image: bind images[index+1]
        fitWidth: 80.0
        fitHeight: 80.0
        preserveRatio: true
    }
    
    def __layoutInfo_tile3: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 100.0
        height: 100.0
        hpos: javafx.geometry.HPos.CENTER
        vpos: javafx.geometry.VPos.CENTER
    }
    public-read def tile3: javafx.scene.layout.Tile = javafx.scene.layout.Tile {
        layoutInfo: __layoutInfo_tile3
        content: [ rightImageView, ]
        hgap: 6.0
        vgap: 6.0
        hpos: javafx.geometry.HPos.CENTER
        vpos: javafx.geometry.VPos.CENTER
    }
    
    def __layoutInfo_middleImageView: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hpos: javafx.geometry.HPos.CENTER
        vpos: javafx.geometry.VPos.CENTER
    }
    public-read def middleImageView: javafx.scene.image.ImageView = javafx.scene.image.ImageView {
        layoutInfo: __layoutInfo_middleImageView
        effect: reflectionEffect
        image: bind images[index]
        fitWidth: 150.0
        fitHeight: 150.0
        preserveRatio: true
    }
    
    def __layoutInfo_tile2: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 150.0
        height: 150.0
    }
    public-read def tile2: javafx.scene.layout.Tile = javafx.scene.layout.Tile {
        layoutInfo: __layoutInfo_tile2
        content: [ middleImageView, ]
        hgap: 6.0
        vgap: 6.0
        hpos: javafx.geometry.HPos.CENTER
        vpos: javafx.geometry.VPos.CENTER
    }
    
    def __layoutInfo_leftImageView: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hpos: javafx.geometry.HPos.CENTER
        vpos: javafx.geometry.VPos.CENTER
    }
    public-read def leftImageView: javafx.scene.image.ImageView = javafx.scene.image.ImageView {
        layoutInfo: __layoutInfo_leftImageView
        onMouseClicked: leftImageViewOnMouseClicked
        effect: reflectionEffect
        image: bind images[index-1]
        fitWidth: 80.0
        fitHeight: 80.0
        preserveRatio: true
    }
    
    def __layoutInfo_tile: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 100.0
        height: 100.0
        hpos: javafx.geometry.HPos.CENTER
        vpos: javafx.geometry.VPos.CENTER
    }
    public-read def tile: javafx.scene.layout.Tile = javafx.scene.layout.Tile {
        layoutInfo: __layoutInfo_tile
        content: [ leftImageView, ]
        hgap: 6.0
        vgap: 6.0
        hpos: javafx.geometry.HPos.CENTER
        vpos: javafx.geometry.VPos.CENTER
    }
    
    def __layoutInfo_hbox: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hpos: javafx.geometry.HPos.CENTER
    }
    public-read def hbox: javafx.scene.layout.HBox = javafx.scene.layout.HBox {
        layoutInfo: __layoutInfo_hbox
        effect: null
        content: [ prevButton, tile, tile2, tile3, nextButton, ]
        spacing: 6.0
    }
    
    def __layoutInfo_vbox: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 480.0
        height: 290.0
    }
    public-read def vbox: javafx.scene.layout.VBox = javafx.scene.layout.VBox {
        opacity: 1.0
        layoutY: 30.0
        layoutInfo: __layoutInfo_vbox
        content: [ hbox2, titleLabel, hbox, ]
        padding: javafx.geometry.Insets { left: 6.0, top: 0.0, right: 6.0, bottom: 0.0 }
        spacing: 6.0
        hpos: javafx.geometry.HPos.CENTER
        nodeHPos: javafx.geometry.HPos.CENTER
    }
    
    public-read def scene: javafx.scene.Scene = javafx.scene.Scene {
        width: 480.0
        height: 320.0
        content: getDesignRootNodes ()
    }
    
    public-read def httpDataSource: org.netbeans.javafx.datasrc.HttpDataSource = org.netbeans.javafx.datasrc.HttpDataSource {
        url: ""
        parser: org.netbeans.javafx.datasrc.Parsers.JSON_PARSER
    }
    
    public-read def currentState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
        names: [ "Preview", "Detail", ]
        actual: 0
        timelines: [
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            vbox.opacity => vbox.opacity tween javafx.animation.Interpolator.DISCRETE,
                            detailsImageView.opacity => detailsImageView.opacity tween javafx.animation.Interpolator.DISCRETE,
                            reflectionEffect.fraction => reflectionEffect.fraction tween javafx.animation.Interpolator.DISCRETE,
                        ]
                        action: function() {
                            middleImageView.onMouseClicked = middleImageViewOnMouseClickedAtPreview;
                            middleImageView.blocksMouse = true;
                            vbox.visible = true;
                            detailsImageView.onMouseClicked = null;
                            detailsImageView.blocksMouse = false;
                        }
                    }
                    javafx.animation.KeyFrame {
                        time: 1000ms
                        values: [
                            vbox.opacity => 1.0 tween javafx.animation.Interpolator.LINEAR,
                            detailsImageView.opacity => 0.0 tween javafx.animation.Interpolator.LINEAR,
                            reflectionEffect.fraction => 0.5 tween javafx.animation.Interpolator.LINEAR,
                        ]
                        action: function() {
                            detailsImageView.visible = false;
                        }
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            vbox.opacity => vbox.opacity tween javafx.animation.Interpolator.DISCRETE,
                            detailsImageView.opacity => detailsImageView.opacity tween javafx.animation.Interpolator.DISCRETE,
                            reflectionEffect.fraction => reflectionEffect.fraction tween javafx.animation.Interpolator.DISCRETE,
                        ]
                        action: function() {
                            middleImageView.onMouseClicked = null;
                            middleImageView.blocksMouse = false;
                            detailsImageView.visible = true;
                            detailsImageView.onMouseClicked = detailsImageViewOnMouseClickedAtDetail;
                            detailsImageView.blocksMouse = true;
                        }
                    }
                    javafx.animation.KeyFrame {
                        time: 1000ms
                        values: [
                            vbox.opacity => 0.0 tween javafx.animation.Interpolator.LINEAR,
                            detailsImageView.opacity => 1.0 tween javafx.animation.Interpolator.LINEAR,
                            reflectionEffect.fraction => 0.0 tween javafx.animation.Interpolator.LINEAR,
                        ]
                        action: function() {
                            vbox.visible = false;
                        }
                    }
                ]
            }
        ]
    }
    
    public function getDesignRootNodes (): javafx.scene.Node[] {
        [ vbox, stack, ]
    }
    
    public function getDesignScene (): javafx.scene.Scene {
        scene
    }
    // </editor-fold>//GEN-END:main

    function detailsImageViewOnMouseClickedAtDetail(event: javafx.scene.input.MouseEvent): Void {
        currentState.actual = 0;
    }

    function middleImageViewOnMouseClickedAtPreview(event: javafx.scene.input.MouseEvent): Void {
        currentState.actual = 1;
    }

    function leftImageViewOnMouseClicked(event: javafx.scene.input.MouseEvent): Void {
        httpDataSource.getDataSource("responseData/results").getRecordSet().prev();
    }

    function rightImageViewOnMouseClicked(event: javafx.scene.input.MouseEvent): Void {
        httpDataSource.getDataSource("responseData/results").getRecordSet().next();
    }

    function searchButtonAction(): Void {
        httpDataSource.url = "{URL}&q={textbox.text}";
        initialized = true;
    }

    def URL = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&rsz=large";
    
    function prevButtonAction(): Void {
        httpDataSource.getDataSource("responseData/results").getRecordSet().prev();
    }

    function nextButtonAction(): Void {
        httpDataSource.getDataSource("responseData/results").getRecordSet().next();
    }

    var images = bind if (initialized) then for(i in httpDataSource.getDataSource("responseData/results").getRecordSet().all()) Image {
            url: i.getString("url")
            backgroundLoading: true
            placeholder: loadingImage
    } else [];

    var index = bind httpDataSource.getDataSource("responseData/results").getRecordSet().currentIndex;

    var initialized = false;

}

function run (): Void {
    var design = ImageSearch {};

    javafx.stage.Stage {
        title: "Google Image Search"
        scene: design.getDesignScene ()
    }
}
