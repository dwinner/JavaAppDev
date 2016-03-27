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
package com.sun.javafx.mediabox;

import javafx.animation.*;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.geometry.BoundingBox;
import javafx.scene.layout.Resizable;
import com.sun.javafx.mediabox.controls.MediaControlBar;
import com.sun.javafx.mediabox.controls.MediaScreen;

/**
 * @author baechul
 */

/**
 * A theme of the MediaBox. The default theme is "paranara".
 */
public-read var theme:String = "paranara";

/**
 * This function will release media native resources. It should be called
 * before playing new media and before exiting from playing a media.
 * @treatasprivate implementation detail
 */
public function impl_clearMediaPlayer(mp: MediaPlayer) {
    if(mp.media != null) {
        mp.media = null;
    }
}

/**
 * A scene graph node that can display a media with a media control bar, buffer
 * indicator, etc. Only {@link mediaSource} is required. The MediaBox has
 * embedded MediaPlayer and MediaView.
 * <p>
 * Usage Example:
 @example

 var mediaBox: MediaBox = MediaBox {
    themeStr: theme             // default: "paranara"
    autoPlay: true              // default: true

    mediaSource: "a media url"

    width: 640         // default: 640
    height: 360        // default: 360

    showMediaInfo: true         // default: false
    mediaControlBarHeight: 25   // default: 25, possible values: 20~50

    onEndOfMedia: function() {
        //mediaBox.mediaSource = "2nd media url"
    }
}

Stage {
    title: "MediaBox Player"
    scene: Scene {
        content: mediaBox
    }
}

@endexample
 *
 */
public class MediaBox extends CustomNode, Resizable {

    var mediaPlayer:MediaPlayer = MediaPlayer {
        onEndOfMedia: function(): Void {
            if(onEndOfMedia != null) onEndOfMedia();
        }
    }

    /**
     * Defines the String which specifies the URI of the media. It must be
     * an absolute URI, such as "<code>file:///media.fxm</code>".
     * If it is relative to the codebase the <code>__DIR__</code> may be used,
     * as in "<code>{__DIR__}/media.fxm</code>". Supported protocols include
     * "file:", "http:", and "jar:". Currently, only audio in MP3, AU, and WAV
     * containers are supported from within jar files.
     */
    public var mediaSource:String on replace {
        impl_clearMediaPlayer(mediaPlayer);
        mediaScreen.toInitStatus();
        mediaControlBar.toInitStatus();

        mediaScreen.mediaInfo.show = false;
        mediaTitle = null;
        mediaDescription = null;

        mediaPlayer.media = Media {
            source: mediaSource
            onError: function(me: MediaError) {
                if(mediaPlayer.status == MediaPlayer.PAUSED) {
                    mediaPlayer.stop();
                    if(mediaControlBar == null or mediaScreen == null) {
                        FX.deferAction(
                            function () {
                                mediaControlBar.mediaError = me;
                                mediaScreen.mediaError = me;
                                return;
                            }
                        );
                    } else {
                        mediaControlBar.mediaError = me;
                        mediaScreen.mediaError = me;
                    }
                } else {
                    // if PLAYING/STALLED/BUFFERING status, just ignore.
                }
            }
        }

        if(autoPlay) {
            mediaControlBar.play();
        }
    }

    /**
     * A media title that can be seen from a media infomation panel. The media
     * infomation panel can be turned on or off by {@showMediaInfo}. If it is
     * on, the panel will be displayed when a mouse enters {@link MediaView}.
     *
     * @defaultvalue empty string
     */
    public var mediaTitle:String;

    /**
     * A media description that can be seen from a media information panel.
     * The media infomation panel can be turned on or off by {@showMediaInfo}.
     * If it is turned on, the panel will be displayed when a mouse enters
     * {@link MediaView}.
     *
     * @defaultvalue empty string
     */
    public var mediaDescription:String;

    /**
     * Invoked when the player reaches the end of media.
     */
    public-init var onEndOfMedia: function():Void;

    /**
     * To set the theme of MediaBox.
     */
    public var themeStr:String on replace {
        theme = themeStr;
    }

    /**
     * It conrols the visiability of media information panel that has {@link mediaTitle}
     * and {@mediaDescription}. If it is on, the panel will be displayed when
     * a mouse enters {@link MediaView} usually scrolling down from the top.
     *
     * @defaultvalue false
     */
    public var showMediaInfo:Boolean = false on replace {
        mediaScreen.mediaInfo.disable = not showMediaInfo;
    }

    /**
     * A width of {@link MediaBox}. Default value is 640.
     *
     * @defaultvalue 640
     */
    override public var width = 640;

    /**
     * A height of {@link MediaBox}. Default value is 360.
     *
     * @defaultvalue 360
     */
    override public var height= 360;

    /**
     * If {@code preserveRatio} is {@code true}, the media is scaled to
     * fit the node, but its aspect is preserved. Otherwise, the media is
     * scaled, but will be stretched or sheared in both dimension to fit its
     * node's dimensions.
     */
    public var preserveRatio:Boolean = true;

    /**
     * A preferred height of media control bar. Default value is 25 pixels. The
     * possible value range is from 20 to 50 pixels.
     *
     * @defaultvalue 25
     */
    public var mediaControlBarHeight = 25 on replace {
        if(mediaControlBarHeight < 20) {
            mediaControlBarHeight = 20;
        } else if(mediaControlBarHeight > 50) {
            mediaControlBarHeight = 50;
        }
    }

    /**
     * If autoPlay is true, playing will start as soon as possible.
     *
     * @defaultvalue true
     */
    public var autoPlay:Boolean = true;

    

    var mediaScreen:MediaScreen = MediaScreen {
        mediaPlayer: bind mediaPlayer
        mediaTitle: bind mediaTitle
        mediaDescription: bind mediaDescription
        showMediaInfo: bind showMediaInfo
        preserveRatio: bind preserveRatio

        x: 0//bind translateX
        y: 0//translateY
        width: bind width
        height: bind height
    }

    public var mediaControlBar:MediaControlBar = MediaControlBar {
        mediaPlayer: bind mediaPlayer
        mediaView: bind mediaScreen.mediaView
        mediaBounds: bind BoundingBox {
            minX: 0
            minY: 0
            width: width
            height: height
        }
        show: false
        blocksMouse: true

        x: 1
        y: bind (height - mediaControlBarHeight)
        width: bind width - 3
        height: mediaControlBarHeight - 2
    }

    /**
     * Play the associated media.
     */
    public function play(): Void {
        mediaControlBar.play();
    }

    /**
     * Pause the associated media playing.
     */
    public function pause(): Void {
        mediaControlBar.pause();
    }

    /**
     * Reflects the current status of underlaying MediaPlayer.
     * The status is one of <code>PAUSED, PLAYING, BUFFERING, STALLED</code>.
     * The values are defined at {@link javafx.media.MediaPlayer}.
     */
    public-read var status:Integer = bind mediaPlayer.status;

    // to capture user-defined input events
    var glassPane1:Rectangle = Rectangle {
        width: bind width
        height: bind height
        fill: Color.TRANSPARENT

        onKeyPressed: bind onKeyPressed
        onKeyReleased: bind onKeyReleased
        onKeyTyped: bind onKeyTyped

        onMouseClicked: bind onMouseClicked
        onMouseDragged: bind onMouseDragged
        onMouseEntered: bind onMouseEntered
        onMouseExited: bind onMouseExited
        onMouseMoved: bind onMouseMoved
        onMousePressed: bind onMousePressed
        onMouseReleased: bind onMouseReleased
        onMouseWheelMoved: bind onMouseWheelMoved
    }

    /**
     * a private glassPane to handle the default behavior of controlbar visibility.
     */
    var glassPane2: Rectangle = Rectangle {
        width: bind width
        height: bind height
        fill: Color.TRANSPARENT

        onMouseEntered:function(me:MouseEvent) {
            //println("mouse entered");
            mediaControlBar.show = true;
        }
        onMouseExited:function(me:MouseEvent) {
            //println("mouse exited");
            mediaControlBar.show = false;
        }

    }

    override function create(): Group {
        Group {
            content: bind [
                mediaScreen,
                glassPane1,
                mediaControlBar,
                glassPane2
            ]
        }
    }

    postinit {
        /**
         * Temporary workaround to make buffer update(windows):
         * buffer progress time is not updated while playing until the
         * current time hits the buffer time. A workaround was to setting
         * any other attributes of Player. Then the buffer progress time will
         * be updated. This work around works only for windows.
         */
        Timeline {
            repeatCount: Timeline.INDEFINITE
            keyFrames:KeyFrame {
                time: 100ms
                action: function() {
                    var saved = mediaPlayer.balance;
                    mediaPlayer.balance = 0.1;
                    mediaPlayer.balance = saved;
                }
            }
        }.play();

        if(autoPlay == true and mediaSource != null) {
            mediaControlBar.play();
        }

        // cleanup when the app is closed.
        scene.stage.onClose = function() {
            impl_clearMediaPlayer(mediaPlayer);
        }

        glassPane2.requestFocus();
    }

    var minWidth = 80;
    var minHeight= 50;

    override function getPrefWidth(fitHeight: Float) {
        if ( (mediaPlayer != null) and (mediaPlayer.media != null) and (mediaPlayer.media.width > minWidth) ) {
            if (preserveRatio and (mediaPlayer.media.height > 0 )) {
                return fitHeight * (mediaPlayer.media.width / mediaPlayer.media.height);
            } else {
                return mediaPlayer.media.width;
            }
        } else {
            return minWidth;
        }

    }

    override function getPrefHeight(fitWidth: Float) {
        if ( (mediaPlayer != null) and (mediaPlayer.media != null) and (mediaPlayer.media.height > minHeight) ) {
            if (preserveRatio and (mediaPlayer.media.width > 0 )) {
                return fitWidth * (mediaPlayer.media.height / mediaPlayer.media.width);
            } else {
                return mediaPlayer.media.height;
            }
        } else {
            return minHeight;
        }
    }
}
