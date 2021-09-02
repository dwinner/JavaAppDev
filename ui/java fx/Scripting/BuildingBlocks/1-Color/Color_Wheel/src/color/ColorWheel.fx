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

package color;

import javafx.scene.Group;
import javafx.scene.CustomNode;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.lang.FX;

/**
 * Application color wheel shows many centric circles each in
 * different color.
 *
 * @author Michal Skvor
 */

/**
 * Definition of visual node which represents the color wheel. Custom
 * node is base for all custom visual elements.
 */
class ColorWheelNode extends CustomNode {
    var segments : Number = 12;
    var steps : Number = 6;
    var radius : Number = 95;
    var valueShift : Number = 0;
    var stripes : Arc[];

    /** create() function which returns our visual representation of the
     *  color wheel */
    override function create() : Node {
        return Group {
            content : bind stripes      // Color arcs filled in init method()
        };
    }

    // Construnctor where we create the color wheel
    init {
        // Set radius
        var r = radius;
        // Define step size
        var rStep = radius / steps;
        var colors : Color[];
        for( i in [1..segments + 1] ) {
            insert Color.rgb( 255, 255, 0 ) into colors;
        }
        // Create color wheel
        for( i in [0..steps-1] ) {
            if( valueShift == 0 ) {
                colors[1] = Color.rgb( 255 - ( 255 / steps * i ), 255 - ( 255 / steps * i ), 0 );
                colors[2] = Color.rgb( 255 - ( 255 / steps ) * i, ( 255 / 1.5 ) - (( 255 / 1.5 ) / steps ) * i, 0 );
                colors[3] = Color.rgb( 255 - ( 255 / steps ) * i, ( 255 / 2 ) - ( ( 255 / 2 ) / steps ) * i, 0 );
                colors[4] = Color.rgb( 255 - ( 255 / steps ) * i, ( 255 / 2.5 ) - (( 255 / 2.5 ) / steps ) * i, 0 );
                colors[5] = Color.rgb( 255 - ( 255 / steps ) * i, 0, 0 );
                colors[6] = Color.rgb( 255 - ( 255 / steps ) * i, 0, ( 255 / 2 ) - (( 255 / 2 ) / steps ) * i );
                colors[7] = Color.rgb( 255 - ( 255 / steps ) * i, 0, 255 - ( 255 / steps ) * i );
                colors[8] = Color.rgb(( 255 / 2 ) - (( 255 / 2 ) / steps ) * i, 0, 255 - ( 255 / steps ) * i );
                colors[9] = Color.rgb( 0, 0, 255 - ( 255 / steps ) * i );
                colors[10] = Color.rgb( 0, 255 - ( 255 / steps ) * i, ( 255 / 2.5 ) - (( 255 / 2.5 ) / steps ) * i );
                colors[11] = Color.rgb( 0 , 255 - ( 255 / steps ) * i, 0 );
                colors[12] = Color.rgb(( 255 / 2 ) -(( 255 / 2 ) / steps ) * i, 255 - ( 255 / steps ) * i, 0 );
            } else if( valueShift == 1 ) {
                colors[1] = Color.rgb(( 255 / steps ) * i, ( 255 / steps ) * i, 0 );
                colors[2] = Color.rgb(( 255 / steps ) * i, (( 255 / 1.5 ) / steps ) * i, 0 );
                colors[3] = Color.rgb(( 255 / steps ) * i, (( 255 / 2 ) / steps ) * i, 0 );
                colors[4] = Color.rgb(( 255 / steps ) * i, (( 255 / 2.5 ) / steps ) * i, 0 );
                colors[5] = Color.rgb(( 255 / steps ) * i, 0, 0 );
                colors[6] = Color.rgb(( 255 / steps ) * i, 0, (( 255 / 2 ) / steps ) * i );
                colors[7] = Color.rgb(( 255 / steps ) * i, 0, ( 255 / steps ) * i );
                colors[8] = Color.rgb((( 255 / 2 ) / steps ) * i, 0, ( 255 / steps ) * i );
                colors[9] = Color.rgb( 0, 0, ( 255 / steps ) * i );
                colors[10] = Color.rgb( 0, ( 255 / steps ) * i, (( 255 / 2.5 ) / steps ) * i );
                colors[11] = Color.rgb( 0, ( 255 / steps ) * i, 0 );
                colors[12] = Color.rgb((( 255 / 2 ) / steps ) * i, ( 255 / steps ) * i, 0 );
            }
            // Generate Segments for all wheels
            for( j in [1..segments] ) {
                var c = colors[j.intValue()];
                insert Arc {
                    centerX : 100, centerY : 100
                    radiusX : r , radiusY : r
                    startAngle : 360 / segments * ( segments - j - 0.5)
                    length : 360 / segments
                    fill : c
                    type : ArcType.ROUND
                } into stripes;
            }
            r -= rStep;
        }
    }
}

// Frame representing view to our application
Stage {
    // Visual scene definition
    scene: Scene {
        fill : Color.GRAY           // Our scene will have gray background
        content : ColorWheelNode {}     // There will be only color wheel
                                    // content defined above
    }

    width : 206                     // Width and height of the frame
    height : 232
    title : "Color Wheel"           // Stage title
}
