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

import javafx.scene.control.*;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import com.sun.mediabox.controls.*;
import com.sun.javafx.mediabox.controls.*;

/**
 * @author baechul
 */

public class MediaSliderSkin extends com.sun.mediabox.controls.skin.AbstractSkin {
    // control
    var slider = bind control as MediaSlider;

    // misc variables
    var buttonImageOff = Image{ url: "{__DIR__}resources/ThumbMouseOff.png" }
    var buttonImageOver = Image{ url: "{__DIR__}resources/ThumbMouseOver.png" }
    var buttonImage = buttonImageOff;

    protected override function getMinWidth():Number { slider.width }
    protected override function getMinHeight():Number { slider.height }

    // behavior link
    override var behavior = MediaSliderBehavior {}
    var sliderBehavior = bind behavior as MediaSliderBehavior;

    // skin update based on the control status.

    // skin node ///////////////////////////////////////////////////////////////
    protected override var frame = Rectangle {
        x: bind slider.x
        y: bind slider.y
        width: bind slider.width
        height: bind slider.height
        
        fill: Color.TRANSPARENT
        smooth: false
        opacity: 0.5
    }

    public var buttonHeight: Number = 24;
    public var buttonWidth: Number = 16 on replace { 
        if(buttonHeight == 0)
        buttonHeight = buttonWidth;
    }

    var barThick: Number = 8;
    var barLength: Number = bind slider.width;
    var barStartOffset: Number = 2;
    var startPoint: Number = bind slider.x + barStartOffset;
    var endPoint: Number = bind startPoint + barLength;

    var bar: Rectangle = Rectangle {
        smooth: false
        x: bind startPoint
        y: bind slider.y+(slider.height-barThick)*0.5
        width: bind barLength
        height: bind barThick

        stroke: bind LinearGradient {
            startX: 0.0
            startY: slider.y+(slider.height-barThick)*0.5
            endX: 0.0
            endY: slider.y+(slider.height-barThick)*0.5+barThick
            proportional: false
            stops: [
                Stop { offset: 0.0 color: Color.rgb(27, 26, 25) },
                Stop { offset: 1.0 color: Color.rgb(125, 124, 123) }
            ]
        }
        strokeWidth: 1
        // proportional: true didn't work when y doesn't start from 0
        fill: bind LinearGradient {
            startX: 0.0
            startY: slider.y+(slider.height-barThick)*0.5
            endX: 0.0
            endY: slider.y+(slider.height-barThick)*0.5+barThick
            proportional: false
            stops: [
                Stop { offset: 0.0 color: Color.rgb(124, 124, 124) },
                Stop { offset: 0.4 color: Color.rgb(5, 5, 5) },
                Stop { offset: 0.6 color: Color.rgb(5, 5, 5) },
                Stop { offset: 1.0 color: Color.rgb(124, 124, 124) }
            ]
        }

        onMouseClicked: function(me : MouseEvent) {
            if(me.x > startPoint and me.x < endPoint) {
                slider.progress = (me.x - startPoint) / barLength;
            }
        }

        onMouseEntered: function(me : MouseEvent) {
            if(not slider.disabled)
                bar.cursor = Cursor.HAND;
        }

        onMouseExited: function(me : MouseEvent) {
            bar.cursor = Cursor.DEFAULT;
        }
    }

    var progressBar: Rectangle = Rectangle {
        smooth: false
        x: bind startPoint
        y: bind slider.y+(slider.height-barThick)*0.5+2
        width: bind barLength*slider.bufferProgress
        height: bind barThick-3
        fill: bind LinearGradient {
            startX: 0.0
            startY: slider.y+(slider.height-barThick)*0.5+2
            endX: 0.0
            endY: slider.y+(slider.height-barThick)*0.5+1+(barThick-3)
            proportional: false
            stops: [
                Stop {
                    offset: 0.0
                    color: Color.rgb(183, 181, 181)
                },
                Stop {
                    offset: 1.0
                    color: Color.rgb(68, 68, 68)
                }
            ]
        }
        visible: bind not slider.disabled
    }

    var currentBar: Rectangle = Rectangle {
        smooth: false
        x: bind startPoint
        y: bind slider.y+(slider.height-barThick)*0.5+1
        width: bind barLength*slider.progress
        height: bind barThick

        strokeWidth: 1
        fill: bind LinearGradient {
            startX: 0.0
            startY: 0.0
            endX: 1.0
            endY: 0.0
            stops: [
                Stop { offset: 0.0 color: Color.rgb(7, 96, 255) },
                Stop { offset: 1.0 color: Color.rgb(45, 240, 141) }
            ]
        }
    }

    var button: Node = ImageView {
        image: bind buttonImage
        translateX: bind (startPoint+barLength*slider.progress)-buttonWidth*0.5
        translateY: bind {
            var bh = if(buttonHeight >= slider.height) slider.height else buttonHeight;
            slider.y+(slider.height-bh)*0.5+1;
        }
        fitWidth: bind buttonWidth
        fitHeight: bind if(buttonHeight >= slider.height) slider.height else buttonHeight

        onMouseDragged: function(me:MouseEvent) {
            if( button.localToParent(me.x, me.y).x > startPoint and
            button.localToParent(me.x, me.y).x < endPoint) {
                slider.progress = (button.localToParent(me.x, me.y).x - startPoint)/barLength;
            }
        }
        onMouseEntered: function(me:MouseEvent) {
            buttonImage = buttonImageOver;
        }
        onMouseExited: function(me:MouseEvent) {
            buttonImage = buttonImageOff;
        }

        visible: bind not slider.disabled
    }

    init {
        node = Group {
            content: bind [ 
                frame,
                bar,
                progressBar,
                currentBar,
                button
            ]
        }
    }
}
