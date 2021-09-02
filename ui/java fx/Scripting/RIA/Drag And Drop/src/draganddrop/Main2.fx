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
package draganddrop;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.util.Math;

def _sceneWidth = 240;
def _sceneHeight = 320;
var width:Number = bind scene.width on replace {
    ball.layoutX = Math.max(0, Math.min(ball.layoutX, scene.width - movedImage.width));
};
var height:Number = bind scene.height  on replace {
    ball.layoutY = Math.max(0, Math.min(ball.layoutY, scene.height - movedImage.height));
};
var movedImage = Image {
    url: "{__DIR__}images/ball.png" 
};
var ball = ImageView {
    image: movedImage
};
var drag = DragBehavior {
    target: ball
    targetWidth: movedImage.width
    targetHeight: movedImage.height
    maxX: bind width
    maxY: bind height
};

var scene:Scene = Scene {
    width: _sceneWidth
    height: _sceneHeight
    content: [
       ball 
    ]
    fill: LinearGradient {
            startX: 0
            startY: 0
            endX: 0
            endY: 1
            proportional: true
            stops: [
                Stop {
                    offset: 0.0
                    color: Color.rgb(69, 47, 26)
                },
                Stop {
                    offset: 1.0
                    color: Color.rgb(219, 203, 166)
            }
        ]
    }
};

Stage {
    title: "Drag And Drop: v2"
    scene: scene
};

