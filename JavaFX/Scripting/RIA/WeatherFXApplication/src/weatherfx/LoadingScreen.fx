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

package weatherfx;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.CustomNode;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.ClosePath;
import javafx.scene.text.Text;
import javafx.scene.text.TextOrigin;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.transform.Affine;


/**
 * Class holding graphics data for the loading screen. Original imported from Adobe Illustrator
 * using SVG export and SVG -> JavaFX converter.
 * 
 * @author breh
 */


public class LoadingScreen extends CustomNode  {    

    
    function rgba(r:Number, g:Number, b:Number, a:Number):Color {
        return Color {red: r/255, green: g/255, blue: b/255, opacity: a/255};
    }     
    
    function matrix(m00:Number,m01:Number,m02:Number,m10:Number,m11:Number,m12:Number) {
        return Affine{ mxx:m00 mxy:m01 myx:m02 myy:m10 tx:m11 ty:m12};
    }
    

    function SVGID_1_(): LinearGradient {
         LinearGradient {
            startX: 117.1709
            endX: 117.1709
            startY: 77.7261
            endY: -6.5569
            stops: [
                Stop {
                    offset: 0.0
                    color: rgba(0x1A, 0x1A, 0x1A, 0xff)
                },
                Stop {
                    offset: 0.2234
                    color: rgba(0x0F, 0x0F, 0x0F, 0xff)
                },
                Stop {
                    offset: 0.6046
                    color: rgba(0x04, 0x04, 0x04, 0xff)
                },
                Stop {
                    offset: 1.0
                    color: rgba(0x00, 0x00, 0x00, 0xff)
                }
            ]
        }
    }
    
    override function create():Node {
        return Group {
            content:[
                Path {
                    elements: [
                        MoveTo {
                            x: 233.841
                            y: 75.336
                            absolute: true
                        },
                        CubicCurveTo {
                            controlX1: 0.0
                            controlY1: 3.907
                            controlX2: -1.516
                            controlY2: 7.076
                            x: -3.383
                            y: 7.076
                            //smooth: false
                            absolute: false
                        },
                        LineTo {
                            x: -226.571
                            absolute: false
                        },
                        CubicCurveTo {
                            controlX1: -1.871
                            controlY1: 0.0
                            controlX2: -3.387
                            controlY2: -3.169
                            x: -3.387
                            y: -7.076
                            //smooth: false
                            absolute: false
                        },
                        LineTo {
                            y: -67.76
                            absolute: false
                        },
                        CubicCurveTo {
                            controlX1: 0.5
                            controlY1: 3.667
                            controlX2: 2.016
                            controlY2: 0.5
                            x: 3.887
                            y: 0.5
                            //smooth: false
                            absolute: true
                        },
                        LineTo {
                            x: 226.571
                            absolute: false
                        },
                        CubicCurveTo {
                            controlX1: 1.867
                            controlY1: 0.0
                            controlX2: 3.383
                            controlY2: 3.167
                            x: 3.383
                            y: 7.076
                            //smooth: false
                            absolute: false
                        },
                        LineTo {
                            y: 67.76
                            absolute: false
                        },
                        ClosePath {},
                    ]
                    fill: SVGID_1_()
                    stroke: rgba(0x00, 0x00, 0x00, 0xff)
                },
                Text {
                    textOrigin: TextOrigin.BASELINE
                    content: "looking at the sky...."
                    font: Font.font("Arial",FontWeight.BOLD, FontPosture.REGULAR,12.0)
                    fill: rgba(0xFF, 0xFF, 0xFF, 0xff)
                    transforms: [
                        matrix(1.0, 0.0, 0.0, 1.0, 4.3374, 77.7271),
                    ]
                    x: 0.0
                    y: 0.0
                }              
            ]
         };
    
    }
}
