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

package game;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.Node;
import javafx.scene.CustomNode;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.transform.Translate;

/**
 * @author Michal Skvor
 */

var STICK : Integer = 1;
var BOUNCING : Integer = 2;
var state : Integer = STICK;

var paddle : Paddle = Paddle {};
var ball : Ball = Ball { x : paddle.x, y : paddle.y - 5 };

var bouncer : Timeline = Timeline {
    repeatCount: Timeline.INDEFINITE
    keyFrames :
        KeyFrame {
            time : 16ms
            action : function() {
                // Bounce from walls
                if( ball.x + ball.vx < ball.radius ) { ball.vx = -ball.vx; }
                if( ball.x + ball.vx > 200 - ball.radius ) { ball.vx = -ball.vx; }

                if( ball.y + ball.vy < ball.radius ) { ball.vy = -ball.vy; }
                if( ball.y + ball.vy > 200 + ball.radius ) {
                    state = STICK;
                    bouncer.stop();
                }

                // Bouncle from paddle
                if( ball.x + ball.vx >= paddle.x - paddle.width / 2 and ball.x + ball.vx <= paddle.x + paddle.width / 2 ) {
                    if( ball.y + ball.vy + ball.radius >= paddle.y and ball.y + ball.vy - ball.radius <= paddle.y + paddle.height ) {
                        ball.vy = -ball.vy;
                    }
                } else if( ball.y + ball.vy >= paddle.y and ball.y + ball.vy <= paddle.y + paddle.height ) {
                    if( ball.x + ball.vx + ball.radius >= paddle.x - paddle.width / 2 and ball.x + ball.vx - ball.radius <= paddle.x + paddle.width / 2 ) {
                        ball.vx = -ball.vx;
                    }
                }

                // Bounce from brick
                var i : Integer = 0;
                for( brick in bricks ) {
                    if( ball.x + ball.vx >= brick.x and ball.x + ball.vx <= brick.x + brick.width ) {
                        if( ball.y + ball.vy + ball.radius >= brick.y and ball.y + ball.vy - ball.radius <= brick.y + brick.height ) {
                            ball.vy = -ball.vy;
                            delete bricks[i];
                        }
                    } else if( ball.y + ball.vy >= brick.y and ball.y + ball.vy <= brick.y + brick.height ) {
                        if( ball.x + ball.vx + ball.radius >= brick.x and ball.x + ball.vx - ball.radius <= brick.x + brick.width ) {
                            ball.vx = -ball.vx;
                            delete bricks[i];
                        }
                    }
                    i++;
                }
                ball.x += ball.vx;
                ball.y += ball.vy;
            }
        }
};

// Sequence containing Bricks
var bricks : Brick[];
// Create bricks
for( j in [0..5] ) {
    for( i in [0..9] ) {
        insert Brick {
            x : i * 20, y : j * 10 + 30
        } into bricks;
    }
}

// Visual Stage
Stage {
    scene : Scene {
        content : bind [
            Rectangle {
                width : 201, height : 200
                fill : Color.LIGHTGREY

                // Handler for mouse movement
                onMouseMoved : function( e : MouseEvent ): Void {
                    if( e.sceneX < paddle.width / 2 ) { paddle.x = paddle.width / 2; }
                    else if( e.sceneX > 200 - paddle.width / 2 ) { paddle.x = 200 - paddle.width / 2; }
                    else { paddle.x = e.sceneX; }
                    if( state == STICK ) {
                        ball.x = paddle.x;
                        ball.y = paddle.y - ball.radius;
                    }
                }
                // Handler for mouse press
                onMousePressed : function( e : MouseEvent ): Void {
                    if( state == STICK ) {
                        state = BOUNCING;
                        bouncer.play();
                    }
                }
            },
            paddle, ball, bricks
        ]

    }

    width : 207
    height : 232
    title: "Bounce"
}

// Visual representation of Paddle
class Paddle extends CustomNode {

    // Position of paddle
    public var x : Number = 85;
    public var y : Number = 180;

    // Paddle dimensions
    public var width : Number = 30;
    public var height : Number = 10;

    public override function create(): Node {
        return Rectangle {
            transforms : Translate { x : bind x - width / 2, y : bind y }
            width : bind width, height : bind height,
            fill : Color.WHITE
            stroke : Color.BLUE, strokeWidth : 1
        };
    }
}

// Visua representation of Brick
class Brick extends CustomNode {
    // Position of Brick
    public var x : Number;
    public var y : Number;

    // Brick dimensions
    public var width : Number = 20;
    public var height : Number = 10;

    public override function create(): Node {
        return Rectangle {
            transforms : Translate { x : bind x, y : bind y }
            width : bind width, height : bind height
            fill : Color.GRAY
            stroke : Color.WHITE, strokeWidth : 1
        };
    }
}

// Visual representation of Ball
class Ball extends CustomNode {
    // Ball position
    public var x : Number = 100;
    public var y : Number = 100;
    // Ball radius
    public var radius : Number = 5;

    // Ball velocity vectors
    public var vx : Number = 1.0;
    public var vy : Number = -1.0;

    public override function create(): Node {
        return Circle {
            transforms : Translate { x : bind x, y : bind y }
            radius : bind radius
            fill : Color.RED
        }
    }
}