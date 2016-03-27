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

import javafx.animation.*;
import javafx.animation.transition.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.media.MediaPlayer;
import com.sun.javafx.mediabox.controls.*;

/**
 * @author baechul
 */

def MIN_BUFFER_BEGINNING: Duration = 2s;
def MIN_BUFFER: Duration = 1s;
def osname = FX.getProperty("javafx.os.name");

public class PlayControlBehavior extends Behavior {

    // control
    var playControl = bind skin.control as PlayControl;
    var mediaPlayer: MediaPlayer = bind playControl.mediaPlayer;

    /**
     * a flag to indicate if the play was paused by user or system
     * @treatasprivate implementation detail
     */
    public var letPause: Boolean = true;

    /**
     * A media play could be paused with various reasons. User may pause or an underlaying
     * media component make the pause when when the curren time hits the buffer time.
     * The following module will handle these cases appropriately.
     */
    var status = bind mediaPlayer.status on replace {
        if(status == MediaPlayer.PAUSED) {
            if(not letPause) {
                // when the curren time hits the buffer time.
                if(mediaPlayer.currentTime != 0s) {
                    //println("PAUSED(system, current/buffer): {mediaPlayer.currentTime.toSeconds()}/{mediaPlayer.bufferProgressTime.toSeconds()}");
                    // auto play when it's playable again.
                    playControl.impl_lock = true;
                    playControl.impl_setBuffering(true);
                    palyTimeline.play();
                }
            }
        }
    }

    // behaviors
    /**
     * Defines the key behavior of the speaker playControl. If disabled or locked,
     * it doesn't do anything nor change the toggle status.
     */
    public function mouseClicked() {
        //println("test mouse pressed");
        if(playControl.disabled or playControl.impl_lock) return;

        playControl.fire();
        if(playControl.selected) {
            PauseTransition {
                duration: 200ms
                action: function() {
                    letPause = false;
                    playAction();
                }
            }.play();
        } else {
            PauseTransition {
                duration: 200ms
                action: function() {
                    letPause = true;
                    pauseAction();
                }
            }.play();
        }
    }

    public function isPlayable(): Boolean {
        if( playControl.mediaPlayer == null or
            playControl.mediaPlayer.media == null or
            playControl.mediaPlayer.media.source == null or
            playControl.mediaPlayer.media.source.equals("") ) false else true;
    }


    function playAction() {
        if( not isPlayable() ) return;

        if(mediaPlayer.bufferProgressTime == 0s) {
            playControl.impl_setBuffering(true);
            playControl.impl_bufferToPlay = playControl.bufferToPlayBegin;
        }

        letPause = false;
        palyTimeline.play();
    }

    function pauseAction() {
        if( not isPlayable() ) return;
        letPause = true;
        mediaPlayer.pause();
    }

    /**
     * Media actual size and buffer has to be checked before playing the media.
     * Otherwise you may run into a black screen issue.
     */
    var palyTimeline: Timeline = Timeline {
        repeatCount: Timeline.INDEFINITE
        keyFrames: KeyFrame {
            time: 100ms
            action: function() {
                if( mediaPlayer.media.duration.gt(0s) and
                mediaPlayer.bufferProgressTime.sub(mediaPlayer.currentTime).gt(playControl.impl_bufferToPlay) ) {
                    playControl.impl_lock = false;
                    if(playControl.impl_bufferToPlay == playControl.bufferToPlayBegin) playControl.impl_bufferToPlay = playControl.bufferToPlay;
                    playControl.impl_setBuffering(false);
                    palyTimeline.stop();
                    mediaPlayer.play();
                } else {
                    playControl.impl_setBuffering(true);
                    // Temporary workaround to make buffer update(mac):
                    // issue: bufferProgressTime is not get updated.
                    // temporary hack: it was updated only when play() is called on mac.
                    if(osname == "Mac OS X" or {__PROFILE__} != "desktop") {
			if (not letPause) {
                           mediaPlayer.play();
                        }
                    }
                }
            }
        }
    }

    // TODO
    public override function callActionForEvent(e:KeyEvent):Void {
    }
}
