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

import javafx.animation.Timeline;
import javafx.animation.Interpolator;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.media.MediaPlayer;
import com.sun.javafx.mediabox.controls.MediaControlBar;
import com.sun.javafx.mediabox.controls.*;

/**
 * @author baechul
 */

public class MediaControlBarSkin extends com.sun.mediabox.controls.skin.AbstractSkin {
    // control
    var controlBar = bind control as MediaControlBar;

    protected override function getMinWidth():Number { controlBar.width }
    protected override function getMinHeight():Number { controlBar.height }

    var mediaError = bind controlBar.mediaError on replace {
        if(mediaError != null) {
            bufferIndicator.stop();
            controlBar.disable = true;
        }
    }

    var status = bind controlBar.mediaPlayer.status on replace {
        if(status != MediaPlayer.PAUSED) {
            controlBar.mediaError = null;
            controlBar.disable = false;
        }
    }

    // skin node ///////////////////////////////////////////////////////////////
    protected override var frame = Rectangle {
        x: bind controlBar.x
        y: bind controlBar.y
        width: bind controlBar.width
        height: bind controlBar.height

        fill: LinearGradient {
            startX: 0
            startY: 0
            endX: 0
            endY: 1.0
            stops: [
                Stop {
                    offset: 0.7
                    color: Color.rgb(50, 50, 50)
                },
                Stop {
                    offset: 0.8
                    color: Color.rgb(0, 0, 0)
                }
            ]
        }
        stroke: Color.rgb(182, 185, 188)
        strokeWidth: 1
        opacity: 0.6
        smooth: false
    }

    var paddingBegin = 3.0;
    var padding = 5.0;
    var margin = bind controlBar.height*0.1;
    var controlHeight = bind controlBar.height*0.8;

    // 1. play/pause control
    var playControl: PlayControl = PlayControl {
        mediaPlayer: bind controlBar.mediaPlayer

        x: bind controlBar.x+paddingBegin+margin
        y: bind controlBar.y+margin
        width: bind controlHeight
        height: bind controlHeight
    } on replace {
        MediaControlBar.impl_registerControl(MediaControlType.PLAY_CONTROL, playControl);
    }

    // 2 buffer indicator control
    var bufferIndicator: BufferIndicator = BufferIndicator {
        mediaView: bind controlBar.mediaView
        blocks: 16
        blockHeight: 10
        buffering: bind playControl.buffering and mediaError == null

        x: bind controlBar.impl_getMediaBounds().minX+(controlBar.impl_getMediaBounds().width-bufferIndicator.width)*0.5
        y: bind controlBar.impl_getMediaBounds().minY+(controlBar.impl_getMediaBounds().height-bufferIndicator.height)*0.5
        width: 86
        height: 86
    } on replace {
        MediaControlBar.impl_registerControl(MediaControlType.BUFFER_INDICATOR, bufferIndicator);
    }
    
    var divider1 = bind createDivider(
    controlBar.x+paddingBegin+controlBar.height+1, controlBar.y,
    controlBar.height);

    // 3. media slider control
    var mediaSlider: MediaSlider = MediaSlider {
        mediaPlayer: bind controlBar.mediaPlayer

        x: bind controlBar.x+(paddingBegin+margin)+controlHeight+margin+2+margin+padding
        y: bind controlBar.y+margin
        width: bind controlBar.width-(mediaSlider.x+180)
        height: bind controlHeight
    } on replace {
        MediaControlBar.impl_registerControl(MediaControlType.MEDIA_SLIDER, mediaSlider);
    }

    // 4. media time labels
    var mediaTime1: MediaTime = MediaTime {
        mediaPlayer: bind controlBar.mediaPlayer
        type: MediaTimeType.CURRENT

        x: bind mediaSlider.x + mediaSlider.width+padding
        y: bind controlBar.y+margin
        width: 80
        height: bind controlHeight
    } on replace {
        MediaControlBar.impl_registerControl(MediaControlType.CURRENT_TIME, mediaTime1);
    }

    /*var mediaTime2: MediaTime = MediaTime {
        mediaPlayer: bind controlBar.mediaPlayer
        type: MediaTimeType.REMAINING
    }*/

    var divider2 = bind createDivider(
    //volumeControl.x-speakerControl.width-(paddingBegin+margin)*2, controlBar.y,
    speakerControl.x-(padding+margin), controlBar.y,
    controlBar.height);

    // 5. speaker control
    var speakerControl: SpeakerControl = SpeakerControl {
        mediaPlayer: bind controlBar.mediaPlayer

        x: bind volumeControl.x-speakerControl.width-(padding)
        y: bind controlBar.y+margin
        width: bind controlHeight*0.4
        height: bind controlHeight
    }

    // 6. volume control
    var volumeControl: VolumeControl = VolumeControl {
        mediaPlayer: bind controlBar.mediaPlayer;
        value: 1.0

        x: bind controlBar.x+controlBar.width-(volumeControl.width+paddingBegin)
        y: bind controlBar.y+margin
        width: 60
        height: bind controlHeight
    }

    var divider3 = bind createDivider(
    //volumeControl.x-speakerControl.width-(paddingBegin+margin)*2, controlBar.y,
    speakerControl.x-(2*padding+margin) + volumeControl.width, controlBar.y,
    controlBar.height);

//      7. fullScreen control
    var fullScreenControl: FullScreenControl = FullScreenControl {
        mediaPlayer: bind controlBar.mediaPlayer;

        x: bind controlBar.x+controlBar.width-(fullScreenControl.width +paddingBegin)
        y: bind controlBar.y+margin
        width: bind controlHeight
        height: bind controlHeight
    }

    bound function createDivider(sx:Number, sy:Number, ht:Number):Group {
        Group {
            content: bind [
                Line {
                    startX: bind sx
                    startY: bind sy
                    endX: bind sx
                    endY: bind sy+(ht-1)
                    stroke: Color.rgb(43, 40, 41)
                    strokeWidth: 1
                },
                Line {
                    startX: bind sx+1
                    startY: bind sy
                    endX: bind sx+1
                    endY: bind sy+(ht-1)
                    stroke: Color.rgb(93, 94, 95)
                    strokeWidth: 1
                }
            ]
        }
    }

    init {
        node = Group {
            content: bind [
                frame,
                playControl,
                bufferIndicator,
                divider1,
                mediaSlider,
                mediaTime1,
                divider2,
                speakerControl,
                volumeControl,
                divider3,
                fullScreenControl,
            ]
        }
    }

    // animation and effects

    var fadeIn: Timeline;
    var fadeOut: Timeline;

    var show = bind controlBar.show on replace {
        if(not controlBar.disabled) {
            showWithFade(show);
        }
    }

    function showWithFade(show: Boolean) {
        def tempFrame = frame;
        def tempPlayControl = playControl;
        def tempMediaSlider = mediaSlider;
        def tempMediaTime1 = mediaTime1;
        def tempSpeakerControl = speakerControl;
        def tempVolumeControl = volumeControl;
        def tempFullScreenControl = fullScreenControl;
        def tempDivider1 = divider1;
        def tempDivider2 = divider2;
        def tempDivider3 = divider3;
        if(show) {
            if(fadeIn != null and fadeIn.running) fadeIn.stop();
            fadeIn = Timeline {
                keyFrames: [
                    at(0ms) {
                        tempFrame.opacity => 0.0;
                        tempPlayControl.opacity => 0.0;
                        tempMediaSlider.opacity => 0.0;
                        tempMediaTime1.opacity => 0.0;
                        tempSpeakerControl.opacity => 0.0;
                        tempVolumeControl.opacity => 0.0;
                        tempFullScreenControl.opacity => 0.0;
                        tempDivider1.opacity => 0.0;
                        tempDivider2.opacity => 0.0;
                        tempDivider3.opacity => 0.0;
                    },
                    at(500ms) { 
                        tempFrame.opacity => 1.0 tween Interpolator.EASEBOTH;
                        tempPlayControl.opacity => 1.0 tween Interpolator.EASEBOTH;
                        tempMediaSlider.opacity => 1.0 tween Interpolator.EASEBOTH;
                        tempMediaTime1.opacity => 1.0 tween Interpolator.EASEBOTH;
                        tempSpeakerControl.opacity => 1.0 tween Interpolator.EASEBOTH;
                        tempVolumeControl.opacity => 1.0 tween Interpolator.EASEBOTH;
                        tempFullScreenControl.opacity => 1.0 tween Interpolator.EASEBOTH;
                        tempDivider1.opacity => 1.0 tween Interpolator.EASEBOTH;
                        tempDivider2.opacity => 1.0 tween Interpolator.EASEBOTH;
                        tempDivider3.opacity => 1.0 tween Interpolator.EASEBOTH;
                    }
                ]
            }
            fadeIn.play();
        } else {
            if(fadeOut != null and fadeOut.running) fadeOut.stop();
            fadeOut = Timeline {
                keyFrames: [
                    at(0ms) {
                        tempFrame.opacity => 1.0;
                        tempPlayControl.opacity => 1.0;
                        tempMediaSlider.opacity => 1.0;
                        tempMediaTime1.opacity => 1.0;
                        tempSpeakerControl.opacity => 1.0;
                        tempVolumeControl.opacity => 1.0;
                        tempFullScreenControl.opacity => 1.0;
                        tempDivider1.opacity => 1.0;
                        tempDivider2.opacity => 1.0;
                        tempDivider3.opacity => 1.0;
                    },
                    at(500ms) {
                        tempFrame.opacity => 0.0 tween Interpolator.EASEBOTH;
                        tempPlayControl.opacity => 0.0 tween Interpolator.EASEBOTH;
                        tempMediaSlider.opacity => 0.0 tween Interpolator.EASEBOTH;
                        tempMediaTime1.opacity => 0.0 tween Interpolator.EASEBOTH;
                        tempSpeakerControl.opacity => 0.0 tween Interpolator.EASEBOTH;
                        tempVolumeControl.opacity => 0.0 tween Interpolator.EASEBOTH;
                        tempFullScreenControl.opacity => 0.0 tween Interpolator.EASEBOTH;
                        tempDivider1.opacity => 0.0 tween Interpolator.EASEBOTH;
                        tempDivider2.opacity => 0.0 tween Interpolator.EASEBOTH;
                        tempDivider3.opacity => 0.0 tween Interpolator.EASEBOTH;
                    }
                ]
            }
            fadeOut.play();
        }
    }
}
