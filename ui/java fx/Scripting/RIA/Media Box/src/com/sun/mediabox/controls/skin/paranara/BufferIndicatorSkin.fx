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
package com.sun.mediabox.controls.skin.paranara;

import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.ShapeSubtract;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Arc;
import com.sun.mediabox.controls.*;
import com.sun.javafx.mediabox.controls.*;

/**
 * @author baechul
 */

public class BufferIndicatorSkin extends com.sun.mediabox.controls.skin.AbstractSkin {
    // control
    var bufferIndicator = bind control as BufferIndicator;

    protected override function getMinWidth():Number { bufferIndicator.width }
    protected override function getMinHeight():Number { bufferIndicator.height }

    // behavior link ///////////////////////////////////////////////////////////
    override var behavior = BufferIndicatorBehavior {}
    var bufferBehavior = bind behavior as BufferIndicatorBehavior;

    // skin node ///////////////////////////////////////////////////////////////
    protected override var frame = Rectangle {
        x: bind bufferIndicator.x
        y: bind bufferIndicator.y
        width: bind bufferIndicator.width
        height: bind bufferIndicator.height
        fill: Color.TRANSPARENT
        smooth: false
    }

    var backColor = Color.BLACK;
    var fillColor = Color.rgb(10, 138, 232);
    var labelColor = Color.WHITE;

    var radius: Number = bind bufferIndicator.width*0.5;
    var gapBetweenArcs = 5;
    var backArcs: Shape[];
    var bandArcs: Shape[];

    var blocks = bind bufferIndicator.blocks on replace {
        if(blocks > 0) update();
    }

    var bandThick: Number = bind bufferIndicator.blockHeight on replace {
        if(bandThick > 0) update();
    }
    
    var dummy1 = bind bufferIndicator.x on replace {
        if(bufferIndicator.x > 0) update();
    }

    var dummy2 = bind bufferIndicator.y on replace {
        if(bufferIndicator.y > 0) update();
    }

    function update() {
        backArcs = createBand(bufferIndicator.blocks, gapBetweenArcs, backColor, false);
        bandArcs = createBand(bufferIndicator.blocks, gapBetweenArcs, fillColor, true);
        label = createLabel();
    }

    function createBand(numArcs:Number, gap:Number, fillColor:Paint, linearOpacity:Boolean):Shape[] {
        if(numArcs <= 0) null;

        var arcs: Shape[];
        var start = 0.0;
        var length = (360 - numArcs * gap)/numArcs;

        for(i in [0..(numArcs-1)]) {
            start = (length + gap) * i;
            insert createBandArc(i, bufferIndicator.x+radius, bufferIndicator.y+radius, radius, start, length, fillColor, linearOpacity) into arcs;
        }
        return arcs;
    }

    function createBandArc(index:Integer, centerX:Number, centerY:Number, radius:Number,
    start:Number, length:Number, fillColor:Paint, linearOpacity:Boolean):Shape {

        var outer = Arc {
            centerX: centerX centerY: centerY
            radiusX: radius radiusY: radius
            startAngle: start
            length: length
            type: ArcType.ROUND
        }

        var inner = Arc {
            centerX: centerX centerY: centerY
            radiusX: radius-bandThick radiusY: radius-bandThick
            startAngle: start
            length: length
            type: ArcType.ROUND
        }

        ShapeSubtract {
            a: outer
            b: inner
            fill: fillColor
            opacity: bind if(linearOpacity) bufferBehavior.opacities[(bufferBehavior.startBlock+index) mod bufferIndicator.blocks] else 1.0
        }
    }

    var label: Label;

    function createLabel() {
        Label {
            translateX: bind bufferIndicator.x
            translateY: bind bufferIndicator.y+radius*2
            width: radius*2
            height: 30

            hpos: bufferIndicator.hpos
            vpos: bufferIndicator.vpos
            font: bufferIndicator.font
            text: bufferIndicator.text
            textFill: labelColor
        }
    }
    
    init {
        node = Group {
            content: bind [
                frame,
                backArcs,
                bandArcs,
                label
            ]
        }
    }
}
