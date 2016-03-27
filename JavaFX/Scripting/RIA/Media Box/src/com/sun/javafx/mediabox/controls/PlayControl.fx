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

import javafx.lang.Duration;
import javafx.scene.control.*;
import javafx.scene.media.MediaPlayer;
import com.sun.mediabox.controls.PlayControlBehavior;
import com.sun.javafx.mediabox.MediaBox;
import com.sun.mediabox.controls.skin.Theme;

/**
 * @author baechul
 */

var MIN_BUFFER_BEGINNING: Duration = 10s;
var MIN_BUFFER: Duration = 5s;

public class PlayControl extends ToggleButton {

    public var mediaPlayer: MediaPlayer on replace oldPlayer {
        MediaBox.impl_clearMediaPlayer(oldPlayer);
        var onEndOfMedia: function() = mediaPlayer.onEndOfMedia;
        mediaPlayer.onEndOfMedia = function() {
            toInitStatus();
            onEndOfMedia();
        }
    }
    
    init {
        if ({__PROFILE__} == "desktop") {
            MIN_BUFFER_BEGINNING = 10s;
            MIN_BUFFER = 5s;
        } else {
            MIN_BUFFER_BEGINNING = 2s;
            MIN_BUFFER = 1s;
        }
    }

    public var x: Number = 0;
    public var y: Number = 0;
    public override var width = 18;
    public override var height = 18;

    // buffer size to fill in before play.
    public-init var bufferToPlayBegin: Duration = MIN_BUFFER_BEGINNING;
    public-init var bufferToPlay: Duration = MIN_BUFFER;
    public-read var buffering: Boolean = false;

    public override var disable = bind (mediaPlayer.media == null);

    public function play() {
        var playBehavior = skin.behavior as PlayControlBehavior;
        if(playBehavior.isPlayable() and not selected) {
            playBehavior.mouseClicked();
        }
    }

    public function pause() {
        var playBehavior = skin.behavior as PlayControlBehavior;
        if(playBehavior.isPlayable() and selected) {
            (skin.behavior as PlayControlBehavior).mouseClicked();
        }
    }

    /**
     * @treatasprivate implementation detail
     */
    public var impl_bufferToPlay: Duration = bufferToPlayBegin;

    /**
     * @treatasprivate implementation detail
     */
    public function impl_setBuffering(val: Boolean) {
        buffering = val;
    }

    /**
     * @treatasprivate implementation detail
     */
    public var impl_lock: Boolean = false;

    public function toInitStatus() {
        if(mediaPlayer != null) mediaPlayer.stop();
        var playBehavior = skin.behavior as PlayControlBehavior;

        impl_lock = false;
        selected = false;

        playBehavior.letPause = true;
        if(playBehavior.isPlayable()) {
            mediaPlayer.currentTime = 0s;
            selected = mediaPlayer.autoPlay;
        }
    }

    package override function createDefaultSkin():Skin {
        Theme.getTheme(MediaBox.theme).createPlayControlSkin();
    }
}
