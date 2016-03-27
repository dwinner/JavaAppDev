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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.lang.Math;
import java.lang.System;

/**
 * @author Michal Skvor
 */

var distance = 0;
// Sequence of painted circles
var circles : Circle[];

var width : Number = 100;
var height : Number = 100;

// Mouse position
var mouseX : Number = 0;
var mouseY : Number = 0;

var max_distance : Number = dist( 0, 0, width, height );

// Function which computes distance bwtween two points
function dist( x1 : Number, y1 : Number, x2 : Number, y2 : Number ): Number {
    return Math.sqrt(( x1 - x2 ) * ( x1 - x2 ) + ( y1 - y2 ) * ( y1 - y2 ));
}

// Generate matrix of 10x10 circles to paint them on the screen
for( x in [0..10] ) {
    for( y in [0..10] ) {
        var cx = x * 20 + 10;
        var cy = y * 20 + 10;
        insert Circle {
            centerX : cx
            centerY : cy
            // Bind the size of current circle to distance between its coordinates
            // and mouse position so when mouse is closer the circle is bigger
            radius : bind dist( mouseX, mouseY, cx, cy ) / max_distance * 20
            fill : Color.WHITE
        } into circles;
    }
}

Stage {
    scene : Scene {
        content : [
            Rectangle {
                width : 200, height : 200
                fill : Color.GRAY

                // When mouse is moved update the coordinate variables
                onMouseMoved : function( e : MouseEvent ): Void {
                    mouseX = e.sceneX;
                    mouseY = e.sceneY;
                }
            },
            circles
        ]
    };

    visible : true
    title : "Distance 2D"
    width : 200
    height : 232
}
