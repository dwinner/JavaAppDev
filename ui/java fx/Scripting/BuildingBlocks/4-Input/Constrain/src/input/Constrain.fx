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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.transform.Translate;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

import java.lang.Math;

/**
 * @author Michal Skvor
 */

// Size of the eased object
var esize : Number = 25;

// Automatic position of mouse
var mouseX : Number = 100;
var mouseY : Number = 100;

var mx : Number = 100;
var my : Number = 100;

// Easing speed
var easing : Number = 0.05;

// Easing updater. It updates position of cursor to mouse speed
var timer : Timeline = Timeline {
    repeatCount: Timeline.INDEFINITE
    keyFrames :
        KeyFrame {
            time : 16ms
            action : function() {
                if( Math.abs( mouseX - mx ) > 0.1 ) {
                    mx = mx + (mouseX - mx ) * easing;
                }
                if( Math.abs( mouseY - my ) > 0.1 ) {
                    my = my + ( mouseY - my ) * easing;
                }
            }
        }
};

Stage {
    scene : Scene {
        content : [
            Rectangle {
                width : 200, height : 200
                fill : Color.BLACK

                // When mouse is moved, the new target position is updated
                onMouseMoved : function( e : MouseEvent ): Void {
                    mouseX = e.sceneX;
                    if( mouseX < 100 - esize ) { mouseX = 100 - esize };
                    if( mouseX > 100 + esize ) { mouseX = 100 + esize };

                    mouseY = e.sceneY;
                    if( mouseY < 100 - esize ) { mouseY = 100 - esize };
                    if( mouseY > 100 + esize ) { mouseY = 100 + esize };
                }
            },
            Rectangle {
                x : 50, y : 50
                width : 100, height : 100
                fill : Color.GRAY
            },
            Circle {
                transforms : Translate { x : bind mx, y : bind my }
                radius : esize,
                fill : Color.WHITE
            }
        ]
    }

    visible : true
    title : "Constrain"
    width : 200
    height : 232
}

// Start easing updater
timer.play();
