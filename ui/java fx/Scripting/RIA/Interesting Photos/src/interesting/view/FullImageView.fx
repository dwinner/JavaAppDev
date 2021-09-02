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
package interesting.view;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Interpolator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.layout.Container;

// To display enlarged image
public class FullImageView extends Container {
    public var x = 21.0;
    public var y = 75.0;
    public var imgwidth = 201.0;
    public var imgheight = 201.0;
    def widthheight = 2;
    def rect = 1;
    public var image : Image;
    def imageView = ImageView {
        image: bind image
        smooth: true
        preserveRatio: false
    }
    
    var bgRect = Rectangle {
        blocksMouse: true
        x: bind (x - rect)
        y: bind (y - rect)
        width: bind (imgwidth + widthheight)
        height: bind (imgheight + widthheight)
        fill: Color.BLACK
        onMousePressed: function(e:MouseEvent) {
            show = false;
        }
    }

    var imageHeight: Number = bind image.height on replace {
        if(image.height > 0.0) {
            preserveAspectRatio();
        }
    }
    
    var useEffects: Boolean = "{__PROFILE__}" != "mobile";
            
    public var show = false on replace {
        imageView.translateX = x;
        imageView.translateY = y;
        imageView.fitWidth = imgwidth;
        imageView.fitHeight = imgheight;
        if(useEffects) { 
            fader();
        } else {
            visible = show;
        }
    }
    
    // Handles animation effect
    var timelineRate = 1.0;
    var timeline:Timeline = Timeline {
        rate: bind timelineRate with inverse
        keyFrames: [ 
            KeyFrame {
                time: 2s
                values: [
                    opacity => 1.0 tween Interpolator.LINEAR
                ]
                canSkip: true
            }
        ]
    };
    var timelineRunning = bind timeline.running on replace {
        if(not timelineRunning) {
            visible = show;
        }
    }
    
    function preserveAspectRatio() {
        var scale = java.lang.Math.min(imgwidth/image.width, imgheight/image.height);
        var finalW = image.width * scale;
        var finalH = image.height * scale;
        var finalX = x + ((imgwidth - finalW)/2.0);
        var finalY = y + ((imgheight - finalH)/2.0);
        var parTimeline:Timeline = Timeline {
            keyFrames: [ 
                KeyFrame {
                    time: 500ms
                    values: [ 
                        imageView.translateX => finalX tween Interpolator.LINEAR,
                        imageView.translateY => finalY tween Interpolator.LINEAR,
                        imageView.fitWidth => finalW tween Interpolator.LINEAR,
                        imageView.fitHeight => finalH tween Interpolator.LINEAR
                    ]
                }
            ]
        };
        
        if(useEffects) { 
            parTimeline.play();
        } else {
            imageView.translateX = finalX;
            imageView.translateY = finalY;
            imageView.fitWidth = finalW;
            imageView.fitHeight = finalH;
        }
    }
    
    function fader() {
        if(show) {
            timeline.time = 0s;
            timelineRate = 1.0;
            opacity = 0.0;
            visible = true;
        } else {
            timeline.time = 2s;
            timelineRate = -2.0;
            opacity = 1.0;
        }
        timeline.play();
    }

    init {
        children = [
            bgRect,
            imageView
        ]
    }
}
