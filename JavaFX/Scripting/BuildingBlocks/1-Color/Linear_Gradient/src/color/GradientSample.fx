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

package color;

import javafx.scene.Group;
import javafx.scene.CustomNode;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.lang.FX;

/**
 * @author Michal Skvor
 */

var B1 = Color.rgb( 190, 190, 190 );
var B2 = Color.rgb(  20,  20,  20 );

var C1 = Color.rgb( 255, 120,   0 );
var C2 = Color.rgb(  10,  45, 255 );
var C3 = Color.rgb(  10, 255,  15 );
var C4 = Color.rgb( 125,   2, 140 );
var C5 = Color.rgb( 255, 255,   0 );
var C6 = Color.rgb(  25, 255, 200 );

var Y_AXIS = false;
var X_AXIS = true;

// Frame representing view to our application
Stage {
    // Visual scene definition
    scene : Scene {
        content: [
            GradientBox { x :   0, y :   0, size : 200, c1 : B1, c2 : B2, axis : Y_AXIS },
            GradientBox { x :  25, y :  25, size :  75, c1 : C1, c2 : C2, axis : Y_AXIS },
            GradientBox { x : 100, y :  25, size :  75, c1 : C3, c2 : C4, axis : X_AXIS },
            GradientBox { x :  25, y : 100, size :  75, c1 : C2, c2 : C5, axis : X_AXIS },
            GradientBox { x : 100, y : 100, size :  75, c1 : C4, c2 : C6, axis : Y_AXIS }
       ]
    }

    width : 206                       // Width and height of the frame
    height : 230
    title : "Gradient Sample"         // Stage title
}

// Visual class which represents rectangle filled with color gradient
class GradientBox extends CustomNode {
    var x : Number;
    var y : Number;
    var size : Number;
    var c1 : Color;
    var c2 : Color;
    var axis : Boolean;

    var fill : LinearGradient;
    var node : Node;

    init {
        var xx : Number;
        var yy : Number;
        if( axis ) {
            xx = 1; yy = 0.1;
        } else {
            xx = 0.1; yy = 1;
        }
        fill = LinearGradient {
           startX : 0, startY : 0,
           endX : xx, endY : yy
           stops: [
               Stop { offset: 0, color: c1 },
               Stop { offset: 1, color: c2 }
           ]
        };
    }

    // create() function which returns our visual representation of gradient box
    override function create() : Node {
        return Group {
            content : Rectangle {
                x: bind x
                y: bind y
                width: bind size
                height: bind size
                fill: bind fill
            }
        };
    }
}
