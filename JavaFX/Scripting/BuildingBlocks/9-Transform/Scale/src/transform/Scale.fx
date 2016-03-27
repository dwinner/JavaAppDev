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

package transform;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Interpolator;

import java.lang.Math;

/**
 * @author Michal Skvor
 */

var a : Number = 0.0;
// Size variable counted from changes angle variable
var s : Number = bind Math.sin( a ) * 2;

// Change a variable from 0 to 2*PI in 5 seconds
var timeline : Timeline = Timeline {
    repeatCount: Timeline.INDEFINITE
    keyFrames : [
        KeyFrame {
            time : 0s
            values : {
                a => 0.0 tween Interpolator.LINEAR
            }
        },
        KeyFrame {
            time : 5s
            values : {
                a => Math.PI tween Interpolator.LINEAR
            }
        }
    ]
};

Stage {
    scene : Scene {
        fill : Color.GRAY
        content : [
            Rectangle {
                // Bind size of rectangle to s variable
                transforms : [
                    javafx.scene.transform.Translate { x : bind 100 - 40 * s / 2, y : bind 100 - 40 * s / 2 },
                    javafx.scene.transform.Scale { x : bind s, y : bind s }
                ]
                x : 0, y : 0
                width : 40, height : 40
                fill : Color.BLACK
            },
        ]
    }

    visible : true
    title : "Scale"
    width : 200
    height : 232
}

timeline.play();
