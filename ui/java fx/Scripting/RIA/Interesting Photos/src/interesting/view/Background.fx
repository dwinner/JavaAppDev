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

import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Container;

def arcwidthheight = 15;
def rect_padding = 2;
def fill_rect = 4 ;
def bindwidht = 2.0;
def bindheight = 4;
def bindheightH = 8;

// Background
public class Background extends Container {
    public var bgwidth: Number;
    public var bgheight: Number;
    public var thumbBoundsX : Number;
    public var thumbBoundsY : Number;
    public var thumbBoundsW : Number;
    public var thumbBoundsH : Number;
    public var descTextY: Number;
    var bgRect = Rectangle {
        x: 0
        y: 0
        width: bind width
        height: bind height
        fill:LinearGradient {
            startX: 0.0
            startY: 0.0
            endX: 1.0
            endY: 1.0
            proportional: true
            stops: [
                Stop {
                    offset: 0.0
                    color: Color.rgb(0, 95, 157)
                },
                Stop {
                    offset: 0.5
                    color: Color.BLACK
                },
                Stop {
                    offset: 1.0
                    color: Color.rgb(0, 95, 157)
                }
            ]
        }
    }
    
    var borderRect = Rectangle {
        x: bind thumbBoundsX - rect_padding
        y: bind thumbBoundsY - rect_padding
        width: bind (width - (bindwidht * (thumbBoundsX - rect_padding)))
        height: bind thumbBoundsH + bindheight
        strokeWidth: 1.0
        fill:LinearGradient {
            startX: 0.0
            startY: 0.0
            endX: 1.0
            endY: 1.0
            proportional: true
            stops: [
                Stop {
                    offset: 0.0
                    color: Color.rgb(0, 35, 51)
                },
                Stop {
                    offset: 1.0
                    color: Color.rgb(0, 25, 41)
                }
            ]
        }
        arcWidth: arcwidthheight
        arcHeight: arcwidthheight
        smooth: true
    }
    
    var bgFillRect = Rectangle {
        x: bind thumbBoundsX - fill_rect
        y: bind thumbBoundsY - fill_rect
        width: bind (width - (bindwidht * (thumbBoundsX - fill_rect)))
        height: bind thumbBoundsH + bindheightH
        fill:LinearGradient {
            startX: 0.0
            startY: 0.0
            endX: 1.0
            endY: 1.0
            proportional: true
            stops: [
                Stop {
                    offset: 0.0
                    color: Color.BLACK
                },
                Stop {
                    offset: 0.5
                    color: Color.rgb(0, 114, 171)
                },
                Stop {
                    offset: 1.0
                    color: Color.rgb(0, 35, 51)
                }
            ]
        }
        arcWidth: arcwidthheight
        arcHeight: arcwidthheight
    }

    init{
        children = [
            bgRect,
            bgFillRect,
            borderRect
        ]
    }
}

