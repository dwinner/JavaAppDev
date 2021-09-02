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

import javafx.scene.control.Behavior;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaPlayer;
import com.sun.javafx.mediabox.controls.*;

/**
 * @author baechul
 */

public class MediaSliderBehavior extends Behavior {

    // control
    var slider = bind skin.control as MediaSlider;
    var mediaPlayer: MediaPlayer = bind slider.mediaPlayer;

    // behaviors
    var skip: Boolean = false;
    public function updateMediaPlayerCurrentTime(progress: Number) {
        if(mediaPlayer != null and mediaPlayer.media != null) {
            if(isValidTime(mediaPlayer.media.duration)) {
                skip = true;
                mediaPlayer.currentTime = mediaPlayer.media.duration.mul(progress);
                skip = false;
            }
        }
    }

    var currentTime = bind mediaPlayer.currentTime on replace {
        if(mediaPlayer.media != null and not skip) {
            if(mediaPlayer.media.duration != 0s and
            isValidTime(mediaPlayer.media.duration) and
            isValidTime(mediaPlayer.currentTime)) {
                slider.progress = mediaPlayer.currentTime.toMillis()/mediaPlayer.media.duration.toMillis();
            }
        }
    }

    var bufferProgressTime = bind mediaPlayer.bufferProgressTime on replace {
        if(mediaPlayer.media != null) {
            if(mediaPlayer.media.duration != 0s and
            isValidTime(mediaPlayer.media.duration) and
            isValidTime(mediaPlayer.bufferProgressTime)) {
                slider.impl_setBufferProgress(mediaPlayer.bufferProgressTime.toMillis()/mediaPlayer.media.duration.toMillis());
            }
        }
    }

    function isValidTime(dur: Duration): Boolean {
        return dur.toMillis() != Double.NEGATIVE_INFINITY and
           dur.toMillis() != Double.POSITIVE_INFINITY;
    }
    
    // TODO
    public override function callActionForEvent(e:KeyEvent):Void {
    }
}
