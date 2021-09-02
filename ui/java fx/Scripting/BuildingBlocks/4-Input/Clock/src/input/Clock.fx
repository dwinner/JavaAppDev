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

import javafx.scene.Node;
import javafx.scene.CustomNode;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import javafx.scene.transform.Rotate;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

import java.util.Calendar;
import java.lang.System;

/**
 * @author Michal Skvor
 */

var clockWork : ClockWork = ClockWork {};

Stage {
    scene : Scene {
        content : clockWork
        }

    width : 200
    height : 232
    title: "Clock"
}

clockWork.timer.play();

// Clas representing clock work
class ClockWork extends CustomNode {

    var seconds : Number;
    var minutes : Number;
    var hours : Number;

    init {
        // Initialization of values to actual time
        var calendar : Calendar = Calendar.getInstance();
        seconds = calendar.get( Calendar.SECOND );
        minutes = calendar.get( Calendar.MINUTE );
        hours = calendar.get( Calendar.HOUR_OF_DAY );
    }

    // Timeline responsible for automatic one second updates of clock hands
    public var timer : Timeline = Timeline {
        repeatCount : Timeline.INDEFINITE
        keyFrames :
            KeyFrame {
                time : 1s
                action : function() {
                    var calendar : Calendar = Calendar.getInstance();
                    seconds = calendar.get( Calendar.SECOND );
                    minutes = calendar.get( Calendar.MINUTE );
                    hours = calendar.get( Calendar.HOUR_OF_DAY );
                }
            },
    };

    // Visual representation of clocks
    public override function create(): Node {
        return Group {
            content : [
                Circle {
                    transforms : [ Translate { y : 100 }]
                    centerX : 100
                    radius : 80
                    fill : Color.GRAY
                },
                // Second hand
                Line {
                    transforms : [ Rotate { angle : bind seconds * 6, pivotX : 100, pivotY : 100 }]
                    startX : 100
                    startY : 30
                    endX : 100
                    endY : 100
                    stroke : Color.WHITE
                },
                // Minutes hand
                Line {
                    transforms : [ Rotate { angle : bind minutes * 6, pivotX : 100, pivotY : 100 }]
                    startX : 100
                    startY : 40
                    endX : 100
                    endY : 100
                    stroke : Color.WHITE
                    strokeWidth : 2
                },
                // Hours hand
                Line {
                    transforms : [ Rotate { angle : bind hours * 30, pivotX : 100, pivotY : 100 }]
                    startX : 100
                    startY : 50
                    endX : 100
                    endY : 100
                    stroke : Color.WHITE
                    strokeWidth : 4
                }
                ]
        };
    }
}