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

import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaError;
import com.sun.javafx.mediabox.MediaBox;
import com.sun.mediabox.controls.skin.Theme;
import com.sun.mediabox.controls.MediaScreenBehavior;

/**
 * @author baechul
 */

public class MediaScreen extends Control {

    public var mediaPlayer: MediaPlayer on replace oldPlayer {
        MediaBox.impl_clearMediaPlayer(oldPlayer);
    }

    public-read var mediaView: MediaView = bind (skin.behavior as MediaScreenBehavior).mediaView;
    public-read var mediaInfo: MediaInfo = bind (skin.behavior as MediaScreenBehavior).mediaInfo;
    public-read var errorInfo: ErrorInfo = bind (skin.behavior as MediaScreenBehavior).errorInfo;

    public var mediaError: MediaError;

    public var preserveRatio: Boolean = true;

    public var showMediaInfo: Boolean = true;
    public var mediaTitle: String;
    public var mediaDescription: String;

    public var x: Number = 0;
    public var y: Number = 0;
    
    public override var width = 640 on replace {
        mediaView.fitWidth = width;
    }

    public override var height = 360 on replace {
        mediaView.fitHeight = height;
    }


    public function toInitStatus() {
        if(mediaPlayer != null) mediaPlayer.stop();
        mediaError = null;
        //preserveRatio = true;
        //showMediaInfo = true;
        //mediaTitle = null;
        //mediaDescription = null;
    }

    package override function createDefaultSkin():Skin {
        Theme.getTheme(MediaBox.theme).createMediaScreenSkin();
    }
}
