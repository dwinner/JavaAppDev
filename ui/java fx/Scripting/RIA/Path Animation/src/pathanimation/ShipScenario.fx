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
package def THUMB_ICON = Main.makeImage("boat-thumb.png");

package class ShipScenario extends Scenario {
    def ship: ImageView = ImageView {
        var img = Main.makeImage(__DIR__, "boat.png");
        smooth: true
        image: img
        translateX: 50*S - img.width / 2
        translateY: 34*S - img.height / 2
    };

    // Path the ship is animated along
    def path = Path {
        elements: [
            MoveTo       {         x:  70*S          y:  30*S },
            CubicCurveTo { controlX1: 120*S  controlY1:  10*S
                           controlX2: 120*S  controlY2:  50*S
                                   x: 170*S          y:  30*S },
            CubicCurveTo { controlX1: 220*S  controlY1:  10*S
                           controlX2: 255*S  controlY2:  70*S
                                   x: 295*S          y:  30*S },
            CubicCurveTo { controlX1: 335*S  controlY1:   0
                           controlX2: 355*S  controlY2:  50*S
                                   x: 405*S          y:  30*S },
        ]
    };

    // The blue sea
    def sea = Path {
        fill: Color.BLUE
        elements: [
            path.elements,
            CubicCurveTo { controlX1: 455*S  controlY1:  10*S
                           controlX2: 455*S  controlY2:  50*S
                                   x: 505*S          y:  30*S },
            CubicCurveTo { controlX1: 555*S  controlY1:  10*S
                           controlX2: 590*S  controlY2:  70*S
                                   x: 630*S          y:  30*S },
            VLineTo      {                           y: 120*S },
            HLineTo      {         x: -40*S                   },
            VLineTo      {                           y:  30*S },
            CubicCurveTo { controlX1:   0    controlY1:   0
                           controlX2:  20*S  controlY2:  50*S
                                   x:  70*S          y:  30*S },
            ClosePath {},
        ]
    };

    def group = Group {
        content: [sea, ship]
    };

    // Animates ship along the path
    def anim = PathTransition {
        path: AnimationPath.createFromPath(path)
        orientation: OrientationType.ORTHOGONAL_TO_TANGENT
        node: ship
        interpolator: Interpolator.LINEAR
        duration: 8s
        repeatCount: Timeline.INDEFINITE
    };

    // Animates the path itself
    def move = TranslateTransition {
        fromX: 0
        fromY: 0
        toX: -335*S
        toY: 0
        node: group
        interpolator: Interpolator.LINEAR
        duration: 8s
        repeatCount: Timeline.INDEFINITE
    };

    init {
        children = [
            ImageView { image: Main.makeImage(__DIR__, "boat-bg.jpg") },
            Group {
                content: group
                translateY: bind Main.scene.height * .75
            }
        ]
    }

    public override function play() {
        anim.play();
        move.play();
    }

    public override function pause() {
        anim.pause();
        move.pause();
    }
}
