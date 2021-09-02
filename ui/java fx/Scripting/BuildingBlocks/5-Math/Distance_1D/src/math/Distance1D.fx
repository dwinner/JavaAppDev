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
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

/**
 * @author Michal Skvor
 */

// Sequence of all visualized boxes
var boxes : Box[] = [
    Box {
        xpos : 134, ypos : 0, thickness : 36, direction : 1.0, div : 64,
        color : Color.rgb( 102, 102, 102 )
    },
    Box {
        xpos : 44, ypos : 0, thickness : 8, direction : 1.0, div : 16,
        color : Color.rgb( 204, 204, 204 )
    },
    Box {
        xpos : 58, ypos : 100, thickness : 36, direction : -1.0, div : 64,
        color : Color.rgb( 102, 102, 102 )
    },
    Box {
        xpos : 120, ypos : 100, thickness : 8, direction : -1.0, div : 16,
        color : Color.rgb( 204, 204, 204 )
    }
];

var mx : Number = 40;

// Timeline which updates periodically all boxes on the screen
var timer : Timeline = Timeline {
    repeatCount: Timeline.INDEFINITE
    keyFrames :
        KeyFrame {
            time : 16ms
            action : function() {
                for( box in boxes ) {
                    box.update( mx );
                }
            }
        }
};

Stage {
    scene : Scene {
        fill : Color.BLACK
        content : [
            Rectangle {
                width : 200, height : 200
                fill : Color.BLACK

                // When mouse is moved, update speed coeficient
                onMouseMoved : function( e : MouseEvent ): Void {
                    mx = e.x * 0.4 - 200 / 5.0;
                }
            },
            boxes
        ]
    }

    visible : true
    title : "Distance 1D"
    width : 200
    height : 232
}

timer.play();

// Representation of box
class Box extends CustomNode {

    // Size of the box
    public var thickness : Number;
    // Box position
    public var ypos : Number;
    public var xpos : Number;
    public var div : Number;
    // Moving direction of box
    public var direction : Number;
    // Color of box
    public var color : Color;

    // Function when called updates box's position 
    public function update( mx : Number ): Void {
        xpos += direction * mx / div;

        if( xpos > 200 ) { xpos = -thickness; }
        if( xpos < -thickness ) { xpos = 200; }
    }

    public override function create(): Node {
        return Rectangle {
            x : bind xpos, y : bind ypos,
            width : bind thickness, height : 100
            fill : bind color
        };
    }
}

