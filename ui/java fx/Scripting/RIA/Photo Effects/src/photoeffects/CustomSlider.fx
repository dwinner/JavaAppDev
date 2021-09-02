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
package photoeffects;

import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.input.*;


/* This class implements a custom slider control using four images:
 * the min button
 * the max button
 * the background
 * the thumb
 *
 * many values are hard coded in this slider because it is only used in this
 * example. To make this slider reusable the constants would be replaced by
 * public-init variables that could be configured.
 */
public class CustomSlider extends CustomNode {

    public-init var minValue = 0.0;
    public-init var maxValue = 1.0;
    public-init var minButtonImage:Image = Image { url: "{__DIR__}images/bl.png" };
    public-init var maxButtonImage:Image = Image { url: "{__DIR__}images/bh.png" };
    
    var position = 20.0 on replace {
        if(position < 0 ) {
            position = 0;
        }
        if(position > 113-16) {
            position = 113-16;
        }
        value = position / (113.0-16.0) * (maxValue-minValue) + minValue;
    }

    public var value:Number = 0.0;

    override public function create():Node {
        return Group {
            content: [
                ImageView { x: 0 image: minButtonImage },
                ImageView {
                    x: 12
                    y: 2
                    image:Image { url: "{__DIR__}images/slider.png" }
                },
                ImageView {
                    x: 12 + 113
                    image: maxButtonImage
                },
                ImageView {
                    x: bind 12 + position
                    y: -4
                    image:Image { url: "{__DIR__}images/thumb.png" }
                },
                // A draggable area that is slightly bigger than the slider, so that it's easier to click on
                Rectangle {
                    fill: Color.TRANSPARENT
                    x: 12
                    y: -4
                    width: 113
                    height: 9 + 8 + 5
                    onMousePressed:function(e:MouseEvent) {
                        position = e.x - 20;
                    }
                    onMouseDragged: function(e:MouseEvent) {
                        position = e.x - 20;
                    }
                }
            ]
        }
    }
    
}

