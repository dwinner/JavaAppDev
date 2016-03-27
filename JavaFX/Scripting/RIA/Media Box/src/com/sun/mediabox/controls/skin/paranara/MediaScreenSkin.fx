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
package com.sun.mediabox.controls.skin.paranara;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.input.MouseEvent;
import com.sun.mediabox.controls.MediaScreenBehavior;
import com.sun.javafx.mediabox.controls.*;

/**
 * @author baechul
 */

public class MediaScreenSkin extends com.sun.mediabox.controls.skin.AbstractSkin {
    // control
    var mediaScreen = bind control as MediaScreen;

    // behavior link
    override var behavior = MediaScreenBehavior {}
    var screenBehavior = bind behavior as MediaScreenBehavior;

    protected override function getMinWidth():Number { mediaScreen.width }
    protected override function getMinHeight():Number { mediaScreen.height }

    var status = bind mediaScreen.mediaPlayer.status on replace {
        if(status == MediaPlayer.PLAYING) {
            mediaScreen.mediaError = null;
        }
    }

    // skin node ///////////////////////////////////////////////////////////////
    // a black screen at the bottom.
    protected override var frame = Rectangle {
        x: bind mediaScreen.x
        y: bind mediaScreen.y
        width: bind mediaScreen.width
        height: bind mediaScreen.height
        fill: Color.BLACK

        onMouseEntered: function(me:MouseEvent) {
            if(mediaScreen.showMediaInfo) {
                //println("show media info");
                mediaInfo.show = true;
            }
        }
        onMouseExited: function(me:MouseEvent) {
            if(mediaScreen.showMediaInfo) {
                //println("hide media info");
                mediaInfo.show = false;
            }
        }
    }

    /**
     * Putting bind to MediaView would be worse.
     * var mediaView: MediaView = bind MediaView {
     */
    var mediaView: MediaView = MediaView {
        mediaPlayer: bind mediaScreen.mediaPlayer
        preserveRatio: bind mediaScreen.preserveRatio

        /**
         * Please see next section for performance tunning. A lot of bindings
         * could be a cause of slow rendering.
         */
        //translateX: bind mediaScreen.x
        //translateY: bind mediaScreen.y+(mediaScreen.height-mediaView.layoutBounds.height)*0.5

        /**
         * Binding both width/height will make mediaView resizing really slow.
         */
        //fitWidth: bind mediaScreen.width
        //fitHeight: mediaScreen.height
    } on replace {
        screenBehavior.mediaView = mediaView;
    }


    var wh = bind mediaScreen.width+mediaScreen.height on replace {
        FX.deferAction(function() {
            mediaView.fitWidth = mediaScreen.width;
            mediaView.layoutX = mediaScreen.x+(mediaScreen.width-mediaView.layoutBounds.width)/2;
            mediaView.layoutY = mediaScreen.y+(mediaScreen.height-mediaView.layoutBounds.height)/2;
            return;
        })
    }

    var mediaInfo: MediaInfo = bind MediaInfo {
        x: bind mediaScreen.x
        y: bind mediaScreen.y
        width: bind mediaScreen.width
        height: 80

        show: false
        mediaTitle: bind mediaScreen.mediaTitle
        mediaDescription: bind mediaScreen.mediaDescription
        mediaDurationStr: bind toMinSec(mediaScreen.mediaPlayer.media.duration)
    } on replace {
        screenBehavior.mediaInfo = mediaInfo;
    }

    var errorInfo = bind ErrorInfo {
        x: bind mediaScreen.x
        y: bind mediaScreen.y
        width: bind mediaScreen.width
        height: bind mediaScreen.height

        blocksMouse: true
        mediaError: bind mediaScreen.mediaError
    } on replace {
        screenBehavior.errorInfo = errorInfo;
    }

    function toMinSec(dur: Duration): String {
        "{%02d (dur.toMinutes() mod 60) as Integer}:{%02d (dur.toSeconds() mod 60) as Integer}";
    }

    init {
        node = Group {
            content: bind [
                frame,
                mediaView,
                mediaInfo,
                errorInfo
            ]
        }
    }
}
