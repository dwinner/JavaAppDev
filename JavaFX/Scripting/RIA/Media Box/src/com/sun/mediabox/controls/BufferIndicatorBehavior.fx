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
package com.sun.mediabox.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Behavior;
import javafx.scene.input.KeyEvent;
import com.sun.javafx.mediabox.controls.*;

/**
 * @author baechul
 */

def FRAME_RATE = 10;

public class BufferIndicatorBehavior extends Behavior {

    var bufferIndicator = bind skin.control as BufferIndicator;

    public var startBlock:Integer = 0;
    public var opacities: Number[];

    var t: Timeline;
    var blocks = bind bufferIndicator.blocks on replace {
        if(sizeof opacities > 0) delete opacities;
        var n = 1.0/blocks;
        for(i in [1..blocks]) {
            insert i*n into opacities;
        }
        opacities = reverse opacities;

        startBlock = 0;
        t = Timeline {
            repeatCount: Timeline.INDEFINITE;
            keyFrames: [
                KeyFrame {
                    time: (1000ms/FRAME_RATE);
                    action: function() {
                        startBlock++;
                        if(startBlock == blocks)
                            startBlock = 0;
                    }
                    canSkip: true;
                }
            ]
        }
    }

    public function play(): Void {
        bufferIndicator.visible = true; // didn't work.
        bufferIndicator.opacity = 1.0;
        bufferIndicator.mediaView.opacity = 0.3;
        if(not t.running) t.play();
    }

    public function stop(): Void {
        bufferIndicator.visible = false;
        bufferIndicator.opacity = 0.0;
        bufferIndicator.mediaView.opacity = 1.0;
        if(t.running) t.stop();
    }

    // TODO
    public override function callActionForEvent(e:KeyEvent):Void {
    }
}
