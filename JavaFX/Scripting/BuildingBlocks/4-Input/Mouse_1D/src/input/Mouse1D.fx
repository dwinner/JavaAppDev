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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * @author Michal Skvor
 */

var gx : Number = 10;
var gy : Number = 90;
var width : Number = 200;

var leftColor:Color = Color { red : 0.0, green : 0.4, blue : 0.6 };
var rightColor:Color = Color { red : 0.0, green : 0.2, blue : 0.4 };

Stage {
    scene : Scene {
        content : [
            Rectangle {
                width : 200, height : 200
                fill : Color.BLACK

                // On mouse move, update binded values of rectangle sizes
                onMouseMoved : function( e : MouseEvent ): Void {
                    var l = -0.002 * ( e.sceneX - 100 ) / 2 + 0.06;
                    var r = 0.002 * ( e.sceneX - 100 ) / 2 + 0.06;

                    leftColor = Color { red : 0.0, green : l + 0.4, blue : l + 0.6 };
                    rightColor = Color { red : 0.0, green : r + 0.4, blue : r + 0.6 };

                    gx = e.sceneX / 2;
                    gy = 100 - e.sceneX / 2;

                    if( gx < 10 ) {
                    gx = 10;
                    } else if ( gx > 90 ) {
                    gx = 90;
                    }

                    if( gy > 90 ) {
                    gy = 90;
                    } else if( gy < 10 ) {
                    gy = 10;
                    }
                }
            },
            // Left rectangle
            Rectangle {
                x : bind width / 4 - gx
                y : bind width / 2 - gx
                width : bind gx * 2
                height : bind gx * 2
                fill : bind leftColor
            },
            // Right rectangle
            Rectangle {
                x : bind width / 1.33 - gy
                y : bind width / 2 - gy
                width : bind gy * 2
                height : bind gy * 2
                fill : bind rightColor
            }
        ]
    };

    visible : true
    title : "Mouse 1D"
    width : 200
    height : 232
}