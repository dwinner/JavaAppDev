/* 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 * Copyright 2009 Sun Microsystems, Inc. All rights reserved. Use is subject to license terms. 
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
 *   * Neither the name of Sun Microsystems nor the names of its contributors 
 *     may be used to endorse or promote products derived from this software 
 *     without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package fliptransition;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.CustomNode;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Math;

/*
 * FlipView is a custom node which alternately displays two
 * child nodes, the front and the back, by flipping between
 * them with a 3-D transition.
 */

class FlipView extends CustomNode {
    // public variables for users of this class to set the front and back
    public var frontNode:Node;
    public var backNode:Node;

    var flipped = true;

    // the animation to flip between front and back
    var time = Math.PI/2;
    public var anim = Timeline {
        keyFrames: [
            at(0s) { time=> Math.PI/2 tween Interpolator.LINEAR},
            at(1s) { time=> -Math.PI/2 tween Interpolator.LINEAR},
            KeyFrame {
                time: 1.0s
                action:function() { 
                    flipped = not flipped;
                }
            }
        ]
    }
    
    override public function create():Node {
        return Group {
            content: [
                Group {
                    content: backNode
                    visible: bind time < 0
                    effect: bind getPT(time)
                },
                Group {
                    content: frontNode
                    visible: bind time > 0
                    effect: bind getPT(time)
                },
            ]
        }
    }
    /**
     * Returns the actual perspective transform.
     * Calcualtes the transform by stretching the front and back
     * edges according to a sine and cosine curve multiplied by
     * the constants: radius and back
     */
    function getPT(t:Number):PerspectiveTransform {
        var width = 200;
        var height = 200;
        var radius = width/2;
        var back = height/10;
        return PerspectiveTransform {
            ulx: radius - Math.sin(t)*radius
            uly: 0 - Math.cos(t)*back
            urx: radius + Math.sin(t)*radius
            ury: 0 + Math.cos(t)*back
            lrx: radius + Math.sin(t)*radius
            lry: height - Math.cos(t)*back
            llx: radius - Math.sin(t)*radius
            lly: height + Math.cos(t)*back
        }
    }
    public function doFlip():Void {
        flip.anim.rate = 1.0;
        flip.anim.time = 0s;
        flip.anim.play();
    }

    public function doRevFlip():Void {
        flip.anim.rate = -1.0;
        flip.anim.time = 1s;
        flip.anim.play();
    }
}

// create an instance of FlipView  using two pictures of the lion
var flip: FlipView = FlipView {
    translateX: 50
    translateY: 40 + 50
    backNode:ImageView { 
        image:Image { url: "{__DIR__}lion1.png"  }
    }
    frontNode:ImageView { 
        image:Image { url: "{__DIR__}lion2.png"  }
    }
   onKeyPressed: function(e: KeyEvent) {
        if((e.code == KeyCode.VK_LEFT) or (e.code == KeyCode.VK_TRACK_NEXT) or
           (e.code == KeyCode.VK_RIGHT) or (e.code == KeyCode.VK_TRACK_PREV)) {
            if (flip.flipped) { flip.doFlip(); }
                else flip.doRevFlip();
        }
    }
};

public function run() {
    Stage {
        title: "Flip Transition"
        scene: Scene {
            fill: Color.BLACK
            width: 300
            height: 340
            content: [
                flip,       //The flip transition
                Group {
                    translateX: 50
                    translateY: 5
                    content: [
                        Rectangle {
                        width: 200
                        height: 35
                        fill: Color.rgb(100, 100, 100)
                        stroke: Color.DARKGRAY
                        },
                        Text {
                            fill: Color.WHITE
                            content: "Click Here to Flip"
                            y: 25
                            x: 27
                            font:Font { size: 18 }
                        },
                        Rectangle {
                            width: 200
                            height: 35
                            fill: Color.rgb(200, 0, 0, 0.0)
                            onMousePressed:function(e:MouseEvent) {
                                if (flip.flipped) { flip.doFlip(); }
                                else flip.doRevFlip();
                            }
                        }
                    ]
                }
            ]
        }
     }
    flip.requestFocus();
}
