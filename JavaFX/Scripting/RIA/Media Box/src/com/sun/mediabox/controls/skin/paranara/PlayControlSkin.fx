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

public class PlayControlSkin extends com.sun.mediabox.controls.skin.AbstractSkin {
    // control
    var playControl = bind control as PlayControl;

    // variables
    var fill = ParanaraTheme.fillColor;

    protected override function getMinWidth():Number { playControl.width }
    protected override function getMinHeight():Number { playControl.height }

    // behavior link ///////////////////////////////////////////////////////////
    override var behavior = PlayControlBehavior {}
    var buttonBehavior = bind behavior as PlayControlBehavior;

    // skin update based on the control status.
    var selected = bind playControl.selected on replace {
        if(selected) {
            makePausableUI();
        } else {
            makePlayableUI();
        }
    }

    function makePausableUI():Void {
        playShape.visible= false;
        pauseShape.visible = true;
    }

    function makePlayableUI():Void {
        playShape.visible= true;
        pauseShape.visible = false;
    }
   
    // skin node ///////////////////////////////////////////////////////////////
    var pSize: Number = bind playControl.height*0.55;

    protected override var frame = Rectangle {
        x: bind playControl.x
        y: bind playControl.y
        width: bind playControl.width
        height: bind playControl.height
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
        centerX: bind playControl.x + playControl.width/2.0
        centerY: bind playControl.y + playControl.height/2.0
        radius: bind (if(playControl.width < playControl.height) playControl.width else playControl.height)*0.3
        effect: Shadow {
            color: ParanaraTheme.fillColor as Color
            radius: bind (if(playControl.width < playControl.height) playControl.width else playControl.height)*0.3
        }
        visible: false
    }

    var sx: Number = bind playControl.x + (playControl.width-pSize)/2.0;
    var sy: Number = bind playControl.y + (playControl.height-pSize)/2.0;

    var playShape: Polygon = Polygon {
        points: bind [
            sx, sy,
            sx, sy + pSize,
            sx + pSize, sy + pSize/2
        ]

        fill: bind if(not playControl.disabled and not playControl.impl_lock) fill else ParanaraTheme.disabledColor
        visible: true
    }

    var pauseShape: PauseShape = PauseShape {
        x: bind sx
        y: bind sy
        width: bind pSize
        height: bind pSize

        fill: bind if(not playControl.disabled and not playControl.impl_lock) fill else ParanaraTheme.disabledColor
        visible: false
    }

    init {
        node = Group {
            content: bind [
                frame,
                glowCircle,
                playShape,
                pauseShape
            ]
        }
    }
}

class PauseShape extends CustomNode {
    public var x: Number = 0;
    public var y: Number = 0;
    public var width: Number = 0;
    public var height: Number = 0;
    public var fill: Paint = ParanaraTheme.fillColor;

    // [0.05]+0.3+[0.3]+0.3+[0.05]
    public override function create(): Node {
        return Group {
            content: bind [
                Rectangle {
                    x: bind x
                    y: bind y
                    width: bind width
                    height: bind height
                    fill: Color.TRANSPARENT
                },
                Rectangle {
                    x: bind x + width*0.05
                    y: bind y
                    width: bind width*0.3
                    height: bind height
                    fill: bind fill
                },
                Rectangle {
                    x: bind x + width*0.65
                    y: bind y
                    width: bind width*0.3
                    height: bind height
                    fill: bind fill
                }
            ]
        }
    }
}
