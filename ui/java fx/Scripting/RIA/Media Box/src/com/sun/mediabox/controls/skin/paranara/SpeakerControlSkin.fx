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

import javafx.scene.*;
import javafx.scene.effect.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import com.sun.mediabox.controls.*;
import com.sun.javafx.mediabox.controls.*;

/**
 * @author baechul
 */

public class SpeakerControlSkin extends com.sun.mediabox.controls.skin.AbstractSkin {
    // control
    var speakerControl = bind control as SpeakerControl;

    // misc variables
    var fill = ParanaraTheme.fillColor;

    protected override function getMinWidth():Number { speakerControl.width }
    protected override function getMinHeight():Number { speakerControl.height }

    // behavior link ///////////////////////////////////////////////////////////
    override var behavior = SpeakerControlBehavior {}
    var buttonBehavior = bind behavior as SpeakerControlBehavior;

    // skin update based on the control status.
    var muteOpacity = 0.0;
    var selected = bind speakerControl.selected on replace {
        if (selected) {
            muteOpacity = 1.0;
        } else {
            muteOpacity = 0.0;
        }
    }

    // skin node ///////////////////////////////////////////////////////////////
    var pSize: Number = bind speakerControl.height*0.65;

    protected override var frame = Rectangle {
        x: bind speakerControl.x
        y: bind speakerControl.y
        width: bind speakerControl.width
        height: bind speakerControl.height
        fill: Color.TRANSPARENT

        onMouseEntered: function(e) {
            fill = ParanaraTheme.fillColorOver;
            glowCircle.visible = true;
        }

        onMouseExited: function(e) {
            fill = ParanaraTheme.fillColor;
            glowCircle.visible = false;
        }

        onMouseClicked: function(e) {
            buttonBehavior.mouseClicked();
        }
    }

    var glowCircle = Circle {
        centerX: bind speakerControl.x + speakerControl.width*0.5
        centerY: bind speakerControl.y + speakerControl.height*0.5
        radius: bind pSize*0.5
        effect: Shadow {
            color: Color.rgb(220, 220, 220)
            radius: bind pSize*0.6
        }
        visible: false
    }

    init {
        var sy: Number = bind speakerControl.y + (speakerControl.height-pSize)/2.0;

        node = Group {
            content: bind [
                frame,
                glowCircle,
                Rectangle {
                    x: bind speakerControl.x
                    y: bind sy + pSize*0.2
                    width: bind speakerControl.width*0.5
                    height: bind pSize*0.6
                    fill: bind fill
                },
                Polygon {
                    points: bind [
                        speakerControl.x + speakerControl.width*0.5, sy + pSize*0.2,
                        speakerControl.x + speakerControl.width, sy,
                        speakerControl.x + speakerControl.width, sy + pSize,
                        speakerControl.x + speakerControl.width*0.5, sy + pSize*0.8
                    ]
                    fill: bind fill
                },

                // mute line
                Line {
                    startX: bind speakerControl.x + speakerControl.width*0.5
                    startY: bind sy - (speakerControl.height/10 as Integer)
                    endX: bind speakerControl.x + speakerControl.width*0.5
                    endY: bind sy + pSize + (speakerControl.height/10 as Integer)
                    strokeWidth: bind speakerControl.height/10 as Integer
                    stroke: bind if(not speakerControl.disabled) fill else ParanaraTheme.disabledColor
                    opacity: bind muteOpacity
                    rotate: -45
                }
            ]
        }
    }
}
