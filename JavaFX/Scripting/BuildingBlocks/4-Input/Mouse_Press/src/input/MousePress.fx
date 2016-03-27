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
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.transform.Translate;
import javafx.scene.input.MouseButton;

/**
 * @author Michal Skvor
 */

// Mouse position
var mouseX : Number = 100;
var mouseY : Number = 100;

// Mouse buttons visual representation
var buttons : Rectangle[] = [
    Rectangle { x : 5, y : 5, width : 10, height : 20, fill : Color.WHITE },
    Rectangle { x : 20, y : 5, width : 10, height : 20, fill : Color.WHITE },
    Rectangle { x : 35, y : 5, width : 10, height : 20, fill : Color.WHITE }
];

Stage {
    scene : Scene {
        content : [
            Rectangle {
                width : 200, height : 200
                fill : Color.BLACK

                // On mouse button press update visual button state
                onMousePressed: function( e : MouseEvent ): Void {
                    if( e.button == MouseButton.PRIMARY ) {
                        buttons[0].fill = Color.BLACK;
                    } else if( e.button == MouseButton.SECONDARY ) {
                        buttons[2].fill = Color.BLACK;
                    } else if( e.button == MouseButton.MIDDLE ) {
                        buttons[1].fill = Color.BLACK;
                    }
                }

                // On mouse button release update visual button state
                onMouseReleased: function( e : MouseEvent ): Void {
                    if( e.button == MouseButton.PRIMARY ) {
                        buttons[0].fill = Color.WHITE;
                    } else if( e.button == MouseButton.SECONDARY ) {
                        buttons[2].fill = Color.WHITE;
                    } else if( e.button == MouseButton.MIDDLE ) {
                        buttons[1].fill = Color.WHITE;
                    }
                }

                // On mouse move update mouse position
                onMouseMoved: function( e : MouseEvent ): Void {
                    mouseX = e.sceneX;
                    mouseY = e.sceneY;
                }
            },
            Group {
                transforms : Translate { x : bind mouseX - 32/2, y : bind mouseY - 25 }
                content : [
                    Rectangle { width : 50, height : 70, fill : Color.LIGHTGREY },
                    buttons

                ]
            }
        ]
    }

    visible : true
    title : "Mouse Press"
    width : 200
    height : 232
}