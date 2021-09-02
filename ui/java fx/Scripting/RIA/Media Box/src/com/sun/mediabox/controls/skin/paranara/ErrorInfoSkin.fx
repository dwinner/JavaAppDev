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

import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import com.sun.javafx.mediabox.controls.*;

/**
 * @author baechul
 */

public class ErrorInfoSkin extends com.sun.mediabox.controls.skin.AbstractSkin {
    // control
    var errorInfo: ErrorInfo = bind control as ErrorInfo;

    // skin update based on the control status.
    var show = bind errorInfo.show on replace {
        if(not errorInfo.disabled) {
            errorInfo.visible = show;
        }
    }

    // skin node ///////////////////////////////////////////////////////////////
    protected override var frame = Rectangle {
        blocksMouse: true
        x: bind errorInfo.x
        y: bind errorInfo.y
        width: bind errorInfo.layoutBounds.width
        height: bind errorInfo.layoutBounds.height
        fill: LinearGradient {
            startX: 0.0,
            startY: 0.0,
            endX: 0.0,
            endY: 1.0
            proportional: true
            stops: [
                Stop {
                    offset: 0.0
                    color: Color.rgb(79, 181, 255, 1.0);
                },
                Stop {
                    offset: 1.0
                    color: Color.rgb(210, 237, 255, 1.0);
                }
            ]
        }
    }

    var label: Label = Label {
        translateX: bind errorInfo.x
        translateY: bind errorInfo.y
        width: bind errorInfo.layoutBounds.width
        height: bind errorInfo.layoutBounds.height

        text: bind errorInfo.text
        font: Font{ size: 20 }
        hpos: HPos.CENTER
        vpos: VPos.CENTER
        textFill: Color.BLACK
    }

    init {
        node = Group {
            content: bind [
                frame,
                label
            ]
        }
    }
}
