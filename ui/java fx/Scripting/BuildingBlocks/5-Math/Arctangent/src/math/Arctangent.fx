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

package math;

import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.CustomNode;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.transform.Translate;
import javafx.scene.transform.Rotate;

import java.lang.Math;

/**
 * @author Michal Skvor
 */

// Sequence of all painted eyes
var eyes : Eye[] = [
    Eye { x : 50, y : 16, size : 40 },
    Eye { x : 64, y : 85, size : 20 },
    Eye { x : 90, y : 200, size : 60 },
    Eye { x : 150, y : 44, size : 20 },
    Eye { x : 175, y : 120,  size : 40 }
];

Stage {
    scene : Scene {
        content : [
            Rectangle {
                width : 200, height : 200
                fill : Color.LIGHTGREY

                onMouseMoved : function( e : MouseEvent ) {
                    for( eye in eyes ) {
                        eye.mouse = e;
                    }
                }
            },
            eyes
        ]
    }

    visible : true
    title : "Arctangent"
    width : 200
    height : 232
}

// Visual representation of eye
class Eye extends CustomNode {

    // Eye position
    public var x : Number;
    public var y : Number;

    // Radius of Eye
    public var size : Number;

    public var mouse : MouseEvent;
    var angle : Number = bind Math.toDegrees( Math.atan2( y - mouse.sceneY, x - mouse.sceneX )) + 180;

    public override function create(): Node {
        return Group {
            transforms : Translate { x : bind x, y : bind y }
            content : [
                // Eye circle
                Circle {
                    radius : bind size
                    fill : Color.WHITE
                },
                // Eye pupil
                Circle {
                    transforms : Rotate { angle : bind angle } // Pupil is moved in mouse direction
                    centerX : bind size / 2, centerY : 0, radius : bind size / 2
                    fill : Color.rgb( 153, 153, 153 )
                }
            ]
        };
    }
}
