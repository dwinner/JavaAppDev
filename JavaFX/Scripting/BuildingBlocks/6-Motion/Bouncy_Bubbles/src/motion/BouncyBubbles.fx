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

package motion;

import javafx.scene.Node;
import javafx.scene.CustomNode;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

import java.lang.Math;
import java.util.Random;

/**
 * @author Michal Skvor
 */

var spring : Number = 0.05;
var gravity : Number = 0.05;

var bubbles : Bubble[];

var width : Number = 200;
var height : Number = 200;

var timer : Timeline = Timeline {
    repeatCount: Timeline.INDEFINITE
    keyFrames :
        KeyFrame {
            time : 16ms
            action : function() : Void {
                for( bubble in bubbles ) {
                    bubble.collide( bubbles, spring, width, height );
                    bubble.move( gravity, width, height );
                }
            }
        }
};
var rnd : Random = new Random();

for( i in [1..12] ) {
    insert Bubble {
        x : rnd.nextInt( width ), y : rnd.nextInt( height ), radius : rnd.nextInt( 10 ) + 10
        color : Color.WHITE, opacity : 0.8
    } into bubbles;
}

Stage {
    scene : Scene {
        fill : Color.GRAY
        content : bind bubbles
    };

    title : "Bouncy Bubbles"
    width : 200
    height : 232
}

timer.play();

class Bubble extends CustomNode {

    public var x : Number;
    public var y : Number;
    public var radius : Number;
    public var color : Color = Color.WHITE;

    public var vx : Number;
    public var vy : Number;

    public function collide( bubbles : Bubble[], spring : Number, width : Number, height : Number ): Void {
        for( bubble in bubbles ) {
            var dx : Number = bubble.x - x;
            var dy : Number = bubble.y - y;

            var distance : Number = Math.sqrt( dx * dx + dy * dy );
            var minDist : Number = bubble.radius + radius;

            if( distance < minDist ) {
                var angle : Number = Math.atan2( dy, dx );
                var tx : Number = x + Math.cos( angle ) * minDist;
                var ty : Number = y + Math.sin( angle ) * minDist;

                var ax : Number = ( tx - bubble.x ) * spring;
                var ay : Number = ( ty - bubble.y ) * spring;

                vx -= ax;
                vy -= ay;

                bubble.vx += ax;
                bubble.vy += ay;
            }
        }
    }

    public function move( gravity : Number, width : Number, height : Number ): Void {
        vy += gravity;
        x += vx;
        y += vy;

        if( x + radius > 200 ) {
            x = width - radius;
            vx *= - 0.9;
        } else if( x - radius < 0 ) {
            x = radius;
            vx *= 0.9;
        }
        if( y + radius > 200 ) {
            y = height - radius;
            vy *= -0.9;
        } else if( y - radius < 0 ) {
            y = radius;
            vy *= -0.9;
        }
    }

    public override function create(): Node {
        return Circle {
            centerX : bind x, centerY : bind y, radius : bind radius
            fill : bind color
        };
    }
}