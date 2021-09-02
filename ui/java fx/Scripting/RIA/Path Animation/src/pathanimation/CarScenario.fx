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
package pathanimation;

import javafx.animation.*;
import javafx.animation.transition.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

def S = Main.S;
def PADDING = 2;
def track_strokeWidth = 20 * S;
def line_strokewidth = 2;
package def THUMB_ICON = Main.makeImage("car.png");

package class CarScenario extends Scenario {
    def car = ImageView {
        smooth: true
        image: THUMB_ICON  // thumb image is reused here
        translateX: 16*S - THUMB_ICON.width / PADDING
        translateY: 89*S - THUMB_ICON.height / PADDING
        rotate: -70
    };
    def path = [
        MoveTo       {         x:  16*S         y:  89*S },
        LineTo       {         x:  46*S         y:   5*S },
        QuadCurveTo  {  controlX:  48*S  controlY:   0
                               x:  64*S         y:   1*S },
        LineTo       {         x:  73*S         y:   4*S },
        CubicCurveTo { controlX1: 114*S controlY1:  24*S
                       controlX2:  22*S controlY2: 102*S
                               x:  84*S         y: 131*S },
        LineTo       {         x: 107*S         y: 144*S },
        CubicCurveTo { controlX1: 120*S controlY1: 154*S
                       controlX2: 109*S controlY2: 177*S
                               x:  97*S         y: 172*S },
        LineTo       {         x:  50*S         y: 155*S },
        QuadCurveTo  {  controlX:   0    controlY: 132*S
                               x:   9*S         y: 108*S },
        LineTo       {         x:  16*S         y:  89*S },
        ClosePath {},
    ];
    def track = Path {
        stroke: Color.rgb(51,51,51)
        strokeWidth: track_strokeWidth
        elements: path
    };

    def line = Path {
        stroke: Color.WHITE
        strokeWidth: line_strokewidth
        strokeDashArray: [ 20, 15 ]
        elements: path
    };

    def anim = PathTransition {
        node: car
        path: AnimationPath.createFromPath(track)
        orientation: OrientationType.ORTHOGONAL_TO_TANGENT
        interpolator: Interpolator.LINEAR
        duration: 6s
        repeatCount: Timeline.INDEFINITE
    };

    init{
        children = [
            ImageView { image: Main.makeImage("car-bg.jpg") },
            Group {
                var capH = bind if (Main.S == 1.0) then 90 else 220;
                content: [track, line, car]
                layoutX: bind (Main.scene.width - track.boundsInLocal.width) / PADDING
                layoutY: bind (capH + Main.scene.height - track.boundsInLocal.height) / PADDING
            }
        ]
    }

    public override function play() {
        anim.play();
    }

    public override function pause() {
        anim.pause();
    }
}
