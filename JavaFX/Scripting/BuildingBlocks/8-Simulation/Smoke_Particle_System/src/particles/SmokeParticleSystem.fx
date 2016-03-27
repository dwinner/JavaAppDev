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

import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.CustomNode;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.transform.Translate;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

import java.lang.Math;
import java.util.Random;

/**
 * @author Michal Skvor
 */

Stage {
    scene : Scene {
        fill : Color.BLACK
        content : CustomCanvas {}
    }

    visible : true
    title : "Smoke Particle System"
    width : 200
    height : 232
}

class CustomCanvas extends CustomNode {

    var acc : Number;
    var timeline : Timeline;
    var parts : Particle[];
    var random : Random;;

    function update() : Void {
        insert Particle {
            x : 84
            y : 164
            vx : 0.3 * random.nextGaussian()
            vy : 0.3 * random.nextGaussian() - 1
            timer : 100
            acc : bind acc
        } into parts;
        var i = sizeof parts - 1;
        while( i >=0 ) {
            parts[i.intValue()].update();
            if( parts[i.intValue()].isdead()) {
                delete parts[i.intValue()];
            }
            i--;
        }
    }

    public override function create(): Node {
        random = new Random();
        timeline = Timeline {
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
        timeline.play();


        return Group {
            content : bind [
                Rectangle {
                    width : 200, height : 200
                    fill : Color.BLACK
                    blocksMouse : true

                    onMouseMoved : function( e : MouseEvent ): Void {
                        acc = ( e.sceneX - 100 ) / 1000;
                    }
                },
                Line {
                    startX : bind 100 + ( 500 * acc )
                    startY : 50
                    endX : 100
                    endY : 50
                    stroke : Color.WHITE
                },
                Line {
                    startX : bind 100 + ( 500 * acc )
                    startY : 50
                    endX : bind 100 + ( 500 * acc ) - 4 * acc / Math.abs( acc )
                    endY : 48
                    stroke : Color.WHITE
                },
                Line {
                    startX : bind 100 + ( 500 * acc )
                    startY : 50
                    endX : bind 100 + ( 500 * acc ) - 4 * acc / Math.abs( acc )
                    endY : 52
                    stroke : Color.WHITE
                },
                parts
            ]
        };
    }
}

class Particle extends CustomNode {
    var x : Number;
    var y : Number;
    var vx : Number;
    var vy : Number;
    var timer : Number;
    var acc : Number;

    override function create(): Node {
        return ImageView {
            transforms: [ Translate{ x : bind x, y : bind y } ]
            image : Image { url: "{__DIR__}texture.png" }
            opacity: bind timer / 100
        };
    }

    function update(): Void {
        timer -= 2.5;
        x += vx;
        y += vy;
        vx += acc;
    }

    function isdead(): Boolean {
       return timer <= 0;
    }
}
