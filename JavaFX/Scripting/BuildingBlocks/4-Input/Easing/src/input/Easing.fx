/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER 
 * Copyright  2008, 2010 Oracle and/or its affiliates.  All rights reserved. 
 * Use is subject to license terms.
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
 *   * Neither the name of Oracle Corporation nor the names of its contributors 
 *     may be used to endorse or promote products derived from this software 
 *     without specific prior written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */

package input;

import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.CustomNode;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

import java.lang.Math;

/**
 * @author Michal Skvor
 */

var easing : Ball = Ball {};

Stage {
    scene : Scene {
        content : [
            Rectangle {
                width : 200, height : 200
                fill : Color{ red: 0.2, green : 0.2, blue : 0.2 }

                // When mouse is moved. Update position where ball should be moved
                onMouseMoved : function( e : MouseEvent ): Void {
                    easing.targetX = e.sceneX;
                    easing.targetY = e.sceneY;
                }
            },
            easing
        ]

    };

    visible : true
    title : "Easing"
    width : 200
    height : 232
}

// Visual representation of ball
class Ball extends CustomNode {
    // Ball positions
    var x : Number;
    var y : Number;

    public var targetX : Number = 100;
    public var targetY : Number = 100;

    // Easing coeficient
    var easing : Number = 0.05;

    init {
        // Start easing updater
        timer.play();
    }

    var timer : Timeline = Timeline {
        repeatCount: Timeline.INDEFINITE
        keyFrames :
            KeyFrame {
                time : 20ms
                action : function() {
                    var dx = targetX - x;
                    if( Math.abs( dx ) > 1 ) {
                        x += dx * easing;
                    }

                    var dy = targetY - y;
                    if( Math.abs( dy ) > 1 ) {
                        y += dy * easing;
                }
            }
        }
    };

    public override function create(): Node {
        return Circle {
            centerX : bind x
            centerY : bind y
            radius : 33 / 2
            fill : Color.WHITE
        };
    }
}