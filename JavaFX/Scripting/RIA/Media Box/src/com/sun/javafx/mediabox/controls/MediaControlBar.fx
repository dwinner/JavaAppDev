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

import java.util.HashMap;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaError;
import  javafx.geometry.Bounds;
import com.sun.javafx.mediabox.MediaBox;
import com.sun.mediabox.controls.skin.Theme;

/**
 * @author baechul
 */

def impl_controls: HashMap = HashMap{}

/**
 * @treatasprivate implementation detail
 */
public function impl_registerControl(type: MediaControlType, ctr: Control) {
    if(type != null and ctr != null) {
        impl_controls.put(type, ctr);
    }
}

/**
 * A media control bar control. It is a compound control that contains other
 * controls such as
 * <ul>
 * <li>A play/pause button control</li>
 * <li>A media slider control</li>
 * <li>A media time information control</li>
 * <li>A speaker control</li>
 * <li>A volume control</li>
 * </ul>
 */
public class MediaControlBar extends Control {

    public var mediaView: MediaView;
    public var mediaPlayer: MediaPlayer on replace oldPlayer {
        MediaBox.impl_clearMediaPlayer(oldPlayer);
    }

    public var x: Number = 0;
    public var y: Number = 0;

    public var mediaBounds: Bounds;

    /**
     * @treatasprivate implementation detail
     */
    public bound function impl_getMediaBounds(): Bounds {
        if(mediaBounds == null) mediaView.layoutBounds else mediaBounds;
    }

    /**
     * Play the associated media.
     */
    public function play(): Void {
        var playControl = impl_controls.get(MediaControlType.PLAY_CONTROL);
        (playControl as PlayControl).play();
    }

    /**
     * Pause the associated media playing.
     */
    public function pause(): Void {
        var playControl = impl_controls.get(MediaControlType.PLAY_CONTROL);
        (playControl as PlayControl).pause();
    }

    public function toInitStatus() {
        var playControl = impl_controls.get(MediaControlType.PLAY_CONTROL);
        (playControl as PlayControl).toInitStatus();

        var bufferIndicator = impl_controls.get(MediaControlType.BUFFER_INDICATOR);
        (bufferIndicator as BufferIndicator).toInitStatus();

        var mediaSlider = impl_controls.get(MediaControlType.MEDIA_SLIDER);
        (mediaSlider as MediaSlider).toInitStatus();

        var mediaTime1 = impl_controls.get(MediaControlType.CURRENT_TIME);
        (mediaTime1 as MediaTime).toInitStatus();
    }

    public var mediaError: MediaError;

    public var show: Boolean = true;

    package override function createDefaultSkin():Skin {
        Theme.getTheme(MediaBox.theme).createMediaControlBarSkin();
    }
}
