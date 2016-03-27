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
package com.sun.javafx.mediabox.controls;

import javafx.scene.control.Labeled;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.control.Skin;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import com.sun.javafx.mediabox.MediaBox;
import com.sun.mediabox.controls.skin.Theme;
import com.sun.mediabox.controls.BufferIndicatorBehavior;

/**
 * @author baechul
 */

public class BufferIndicator extends Control, Labeled {
    
    public var x:Number = 0;
    public var y:Number = 0;
    public override var width = 86;
    public override var height = 86;
    
    // label related variables
    public override var text = "Loading...";
    public override var font = Font{ name: "Arial Bold" size: 12 }
    public override var hpos = HPos.CENTER;
    public override var vpos = VPos.CENTER;
    ///

    public var mediaView: Node;
    public var blocks = 16;
    public var blockHeight = 14;

    public var buffering: Boolean = false on replace {
        if(buffering) {
            play();
        } else {
            stop();
        }
    }

    public function play(): Void {
        (skin.behavior as BufferIndicatorBehavior).play();
    }

    public function stop(): Void {
        (skin.behavior as BufferIndicatorBehavior).stop();
    }

    public function toInitStatus() {
        stop();
        visible = false;
        opacity = 0.0;
    }

    init {
        visible = false;
        opacity = 0.0;
    }

    /* Disabling loading pane on TV until buffering issues are resolved */
    package override function createDefaultSkin():Skin {
        if ({__PROFILE__} == "desktop") {
            return Theme.getTheme(MediaBox.theme).createBufferIndicatorSkin();
        } else {
            return null;
        }
    }
}
