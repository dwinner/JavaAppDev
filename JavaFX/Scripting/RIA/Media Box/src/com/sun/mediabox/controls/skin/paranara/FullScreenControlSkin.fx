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

import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import com.sun.mediabox.controls.*;
import com.sun.javafx.mediabox.controls.*;

/**
 * @author baechul
 */

public class FullScreenControlSkin extends com.sun.mediabox.controls.skin.AbstractSkin {
    // control
    var fullScreenControl = bind control as FullScreenControl;

    // misc variables
    var fill = ParanaraTheme.fillColor;

    protected override function getMinWidth():Number { fullScreenControl.width }
    protected override function getMinHeight():Number { fullScreenControl.height }

    // behavior link ///////////////////////////////////////////////////////////
    override var behavior = FullScreenControlBehavior {}
    var buttonBehavior = bind behavior as FullScreenControlBehavior;


    // skin node ///////////////////////////////////////////////////////////////
    var pSize: Number = bind fullScreenControl.height*0.65;

    protected override var frame = Rectangle {
        x: bind fullScreenControl.x
        y: bind fullScreenControl.y
        width: bind fullScreenControl.width
        height: bind fullScreenControl.height
        fill: Color.TRANSPARENT

        onMouseEntered: function(e) {
            fill = ParanaraTheme.fillColorOver;
        }

        onMouseExited: function(e) {
            fill = ParanaraTheme.fillColor;
        }

        onMouseClicked: function(e) {
            buttonBehavior.mouseClicked();
        }
    }

    init {        

        node = Group {
            content: bind [
                frame,
                Rectangle {
                    x: bind fullScreenControl.x + pSize*0.1
                    y: bind fullScreenControl.y + (fullScreenControl.height-pSize)/2.0 + pSize*0.2
                    width: bind fullScreenControl.width*0.7
                    height: bind pSize*0.66
                    arcWidth: bind 2
                    arcHeight: bind 2
                    strokeWidth: 2
                    stroke: bind fill 
                    fill: Color.TRANSPARENT
                }
                    
            ]
        }
    }
}
