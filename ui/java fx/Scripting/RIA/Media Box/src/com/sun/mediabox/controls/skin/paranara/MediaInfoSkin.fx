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

import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import com.sun.mediabox.controls.skin.*;
import com.sun.javafx.mediabox.controls.*;

/**
 * @author baechul
 */

public class MediaInfoSkin extends com.sun.mediabox.controls.skin.AbstractSkin {
    // control
    var mediaInfo: MediaInfo = bind control as MediaInfo;

    protected override function getMinWidth():Number { mediaInfo.width }
    protected override function getMinHeight():Number { mediaInfo.height }

    // skin node ///////////////////////////////////////////////////////////////
    protected override var frame = Rectangle {
        blocksMouse: true
        x: bind mediaInfo.x
        y: bind mediaInfo.y
        width: bind mediaInfo.width
        height: bind mediaInfo.height
        fill: Color.BLACK
        opacity: 0.5
    }

    var mediaTitle: Label = Label {
        text: bind mediaInfo.mediaTitle

        width: bind mediaInfo.width - 20
        translateX: bind mediaInfo.x + 22
        translateY: bind mediaInfo.y + 10
        font: Font{ name: "Arial Bold" size: 14 }
        textFill: Color.WHITE
        opacity: 0.9
    }

    var mediaDescription: Label = Label {
        translateX: bind mediaInfo.x + 22
        translateY: bind mediaInfo.y + 36
        width: bind mediaInfo.width-20

        text: bind "{mediaInfo.mediaDescription} {mediaInfo.mediaDurationStr}"
        font: Font{ name: "Arial" size: 14 }
        textFill: Color.WHITE
        opacity: 0.8
    }

    init {
        node = Group {
            content: bind [
                frame,
                mediaTitle,
                mediaDescription
            ]
        }
    }

    // animation and effects
    var fadeIn: Timeline;
    var fadeOut: Timeline;

    // skin update based on the control status.
    var show = bind mediaInfo.show on replace {
        if(not mediaInfo.disabled) {
            if((mediaInfo.mediaTitle != null and mediaInfo.mediaTitle != "") or
            (mediaInfo.mediaDescription != null and mediaInfo.mediaDescription != "")) {
                showWithFade(show);
            }
        }
    }

    function showWithFade(show: Boolean) {
        def tempMediaInfo = mediaInfo;
        if(show) {
            if(fadeIn != null and fadeIn.running) fadeIn.stop();
            fadeIn = Timeline {
                keyFrames: [
                    at(0ms) { 
                        tempMediaInfo.opacity => 0.0
                        },
                    at(500ms) { 
                        tempMediaInfo.opacity => 1.0 tween Interpolator.EASEBOTH
                        }
                    ]
                }
                fadeIn.play();
        } else {
            if(fadeOut != null and fadeOut.running) fadeOut.stop();
            fadeOut = Timeline {
                keyFrames: [
                    at(0ms) {
                        tempMediaInfo.opacity => 1.0
                        },
                    at(500ms) { 
                        tempMediaInfo.opacity => 0.0 tween Interpolator.EASEBOTH
                        }
                    ]
                }
                fadeOut.play();
        }
    }
}
