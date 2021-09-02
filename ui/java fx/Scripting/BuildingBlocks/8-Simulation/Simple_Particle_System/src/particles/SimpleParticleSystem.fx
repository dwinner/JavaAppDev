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

package particles;

import javafx.scene.Node;
import javafx.scene.CustomNode;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

import java.util.Random;
import java.lang.System;

/**
 * @author Michal Skvor
 */

var parts : Particle[];
var random : Random = new Random();

var timeline : Timeline = Timeline {
    repeatCount: Timeline.INDEFINITE
    keyFrames :
        KeyFrame {
            time : 16.6ms
            canSkip : true
            action: function() {
                update();
            }
    }
};

function update() : Void {
    insert Particle {
       x : 100
       y : 100
       vx : 1 - 2 * random.nextFloat()
       vy : -2 * random.nextFloat()
       accx : 0
       accy : 0.05
       timer : 100
    } into parts;
    var i = sizeof parts - 1;
    while( i >= 0 ) {
       parts[i.intValue()].update();
       if (parts[i.intValue()].isdead()) {
           delete parts[i.intValue()];
       }
       i--;
    }
}

Stage {
    scene : Scene {
        fill : Color.BLACK
        content : bind parts
    }

    title : "Simple Particle System"
    width : 200
    height : 232
}

timeline.play();

class Particle extends CustomNode {
    var x : Number;
    var y : Number;
    var vx : Number;
    var vy : Number;
    var accx : Number;
    var accy : Number;
    var timer : Number;

    override function create(): Node {
       return Circle {
           centerX: bind x
           centerY: bind y
           radius: 5
           fill: Color.WHITE
           opacity: bind timer / 100
       };
    }

    function update(): Void {
       timer -= 1;
       x += vx;
       y += vy;
       vx += accx;
       vy += accy;
    }

    function isdead(): Boolean {
       return timer <= 0;
    }
}
