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
 
package mediaview;

public class MediaFragment extends Fragment {

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:main
    public-read def rectangle: javafx.scene.shape.Rectangle = javafx.scene.shape.Rectangle {
        layoutX: 6.0
        layoutY: 6.0
        fill: javafx.scene.paint.Color.web ("#000000", 0.5)
        stroke: javafx.scene.paint.Color.WHITE
        strokeWidth: 2.0
        width: 350.0
        height: 27.0
        arcWidth: 10.0
        arcHeight: 10.0
    }
    
    def __layoutInfo_statusLabel: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 338.0
    }
    public-read def statusLabel: javafx.scene.control.Label = javafx.scene.control.Label {
        layoutX: 10.0
        layoutY: 12.0
        layoutInfo: __layoutInfo_statusLabel
        text: bind statusLabelText
        hpos: javafx.geometry.HPos.CENTER
        textFill: javafx.scene.paint.Color.WHITE
    }
    
    public-read def playButton: javafx.scene.control.Button = javafx.scene.control.Button {
        layoutX: 71.0
        layoutY: 35.0
        text: "Play"
        action: playButtonAction
    }
    
    public-read def pauseButton: javafx.scene.control.Button = javafx.scene.control.Button {
        layoutX: 87.0
        layoutY: 17.0
        text: "Pause"
        action: pauseButtonAction
    }
    
    public-read def stopButton: javafx.scene.control.Button = javafx.scene.control.Button {
        layoutX: 58.0
        layoutY: 17.0
        text: "Stop"
        action: stopButtonAction
    }
    
    public-read def tile: javafx.scene.layout.Tile = javafx.scene.layout.Tile {
        layoutX: 94.0
        layoutY: 39.0
        content: [ playButton, pauseButton, stopButton, ]
        columns: 3
        rows: 1
        hgap: 6.0
        vgap: 6.0
    }
    
    def __layoutInfo_mediaErrorLabel: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 338.0
    }
    public-read def mediaErrorLabel: javafx.scene.control.Label = javafx.scene.control.Label {
        layoutX: 12.0
        layoutY: 100.0
        layoutInfo: __layoutInfo_mediaErrorLabel
        textWrap: true
        hpos: javafx.geometry.HPos.CENTER
    }
    
    public-read def mediaPlayer: javafx.scene.media.MediaPlayer = javafx.scene.media.MediaPlayer {
        media: null
        autoPlay: true
    }
    
    public-read def mediaView: javafx.scene.media.MediaView = javafx.scene.media.MediaView {
        mediaPlayer: mediaPlayer
        fitWidth: 362.0
        fitHeight: 281.0
    }
    
    def __layoutInfo_panel: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 362.0
        height: 281.0
    }
    public-read def panel: javafx.scene.layout.Panel = javafx.scene.layout.Panel {
        layoutInfo: __layoutInfo_panel
        content: [ mediaView, rectangle, statusLabel, tile, mediaErrorLabel, ]
    }
    
    public-read def currentState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
    }
    
    public function getDesignRootNodes (): javafx.scene.Node[] {
        [ panel, ]
    }
    // </editor-fold>//GEN-END:main

    var statusLabelText: String = bind "{if (mediaPlayer.currentTime >= 0ms) then mediaPlayer.currentTime else 0ms} of {mediaPlayer.media.duration} - {mediaPlayer.media.source}";

    function playButtonAction(): Void {
        mediaPlayer.play ();
    }

    function pauseButtonAction(): Void {
        mediaPlayer.pause();
    }

    function stopButtonAction(): Void {
       mediaPlayer.stop();
    }

    override public function getView () : javafx.scene.Node {
        panel
    }

    override public function isSupported (fileExtension: String) : Boolean {
        fileExtension == "mp3"  or  fileExtension == "wav"  or  fileExtension == "mpg"  or  fileExtension == "mpeg"  or  fileExtension == "avi"  or  fileExtension == "mov"  or  fileExtension == "qt"
    }

    override public function setPath (path: String) : Void {
        mediaPlayer.stop();
        mediaErrorLabel.visible = false;
        mediaPlayer.media = if (path == null) then null else
            javafx.scene.media.Media {
                source: new java.io.File (path).toURI().toString();
                onError: function (error: javafx.scene.media.MediaError):Void {
                    mediaErrorLabel.text = error.message;
                    mediaErrorLabel.visible = true;
                }
            };
    }

}
