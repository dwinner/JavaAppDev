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

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

import java.lang.Math;

/**
 * @author Michal Skvor
 */

var dots : Circle[];
// Actual angle
var theta : Number = 0.0;
// Amplitude of wave
var amplitude : Number = 75;
// Distance between circles
var xspacing : Number = 8;
var period : Number = 500;
var dx : Number = ( Math.PI * 2 / period ) * xspacing;
// Time variable
var time : Number = 0.0;

// Periodically update time variable
var timeline : Timeline = Timeline {
    repeatCount: Timeline.INDEFINITE
    keyFrames :
        KeyFrame {
            time : 16.6ms
            action: function() {
                time += 0.02;
            }
    }
};

// Fill up dots sequence to create visual representation of sine wave
var x = theta;
for( i in [0..25] ) {
    var xx = x;
    insert Circle {
        centerX : i * 8
        // y position of circle is binded to actual angle and shifted so
        // when time is changes the circle is moved
        centerY : bind 100 + Math.sin( xx + time ) * amplitude
        radius : 8
        fill : Color.WHITE
        opacity : 0.3
    } into dots;
    x += dx;
}

Stage {
    scene : Scene {
        fill : Color.BLACK
        content : dots
    }

    visible : true
    title : "Sine Wave"
    width : 200
    height : 232
}

// Start sine wave animation
timeline.play();
