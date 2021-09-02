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
import com.sun.mediabox.controls.MediaTimeBehavior;
import com.sun.javafx.mediabox.controls.*;

/**
 * @author baechul
 */

public class MediaTimeSkin extends com.sun.mediabox.controls.skin.AbstractSkin {
    // control
    var mediaTime = bind control as MediaTime;

    // misc variables
    
    protected override function getMinWidth():Number { mediaTime.width }
    protected override function getMinHeight():Number { mediaTime.height }

    // behavior link
    override var behavior = MediaTimeBehavior {}

    // skin update based on the control status.

    // skin node ///////////////////////////////////////////////////////////////

    var label: Label = Label {
        translateX: bind mediaTime.x
        translateY: bind mediaTime.y
        width: bind mediaTime.width
        height: bind mediaTime.height
        hpos: bind mediaTime.hpos
        vpos: bind mediaTime.vpos
        font: bind mediaTime.font
        text: bind mediaTime.text
        textFill: Color.WHITE
    }

    init {
        node = Group {
            content: bind [ frame, label ]
        }
    }
}
