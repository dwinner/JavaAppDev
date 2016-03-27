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
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import com.sun.mediabox.controls.*;
import com.sun.javafx.mediabox.controls.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

/**
 * @author baechul
 */

public class VolumeControlSkin extends com.sun.mediabox.controls.skin.AbstractSkin {
    // control
    var volumeControl = bind control as VolumeControl;

    // misc variables
    var barWidth: Number = 5;
    var elements: VolumeControlItem [];
    
    public var steps = bind volumeControl.tickMarks on replace {
        if(sizeof elements != steps and steps > 0) {
            enabledCnt = steps;
            barWidth = volumeControl.width/(steps*2);

            delete elements;
            elements = for (i in [0..steps]) createVolumeBar(i, i mod 2 > 0);
        }
    }

    var enabledCnt = steps;

    protected override function getMinWidth():Number { volumeControl.width }
    protected override function getMinHeight():Number { volumeControl.height }

    // behavior link
    override var behavior = VolumeControlBehavior {}
    var sliderBehavior = bind behavior as VolumeControlBehavior;

    // skin update based on the control status.
    var mute = bind volumeControl.mediaPlayer.mute on replace {
        volumeControl.disable = mute;
        for (i in [0..(enabledCnt - 1)]) elements[i].isOn = not volumeControl.disabled;
    }
    
    // skin node ///////////////////////////////////////////////////////////////
    var pSize: Number = bind volumeControl.height*0.65;

    protected override var frame = Rectangle {
        x: bind volumeControl.x
        y: bind volumeControl.y
        width: bind volumeControl.width
        height: bind volumeControl.height
        fill: Color.TRANSPARENT
    }

    var sy: Number = bind volumeControl.y + (volumeControl.height-pSize)/2.0;

    function createVolumeBar(i: Integer, visible: Boolean): VolumeControlItem {
        var bar : VolumeControlItem = VolumeControlItem {
            x: bind volumeControl.x + (i * barWidth)
            y: bind sy
            width: barWidth
            height: bind pSize
            isVisible: visible
            
            onMouseEntered: function(me:MouseEvent) {
                def tempbar = bar;
                if (tempbar.scaleUpTimeline == null) {
                    tempbar.scaleUpTimeline = Timeline {
                        keyFrames: [
                            at(0ms) { 
                                tempbar.scaleY => 1.0
                                },
                            at(0ms) { 
                                tempbar.translateY => 0
                                },
                            at(200ms) { 
                                tempbar.scaleY => 1.3
                                },
                            at(200ms) { 
                                tempbar.translateY => - pSize * 0.15
                                }
                            ]
                        }
                    }
                if (not volumeControl.disabled) tempbar.scaleUpTimeline.playFromStart();
            }

            onMouseExited: function(me:MouseEvent) {
                def tempbar = bar;
                if (tempbar.scaleDownTimeline == null) {
                    tempbar.scaleDownTimeline = Timeline {
                        keyFrames: [
                            at(0ms) { 
                                tempbar.scaleY => 1.3
                                },
                            at(0ms) { 
                                tempbar.translateY => - pSize * 0.15
                                },
                            at(200ms) { 
                                tempbar.scaleY => 1.0
                                },
                            at(200ms) { 
                                tempbar.translateY => 0
                                }
                            ]
                        }
                    }
                if (not volumeControl.disabled) tempbar.scaleDownTimeline.playFromStart();
            }

            onMouseClicked: function(me:MouseEvent) {
                if (volumeControl.disabled) return;

                // skin
                enabledCnt = i + 1;
                for (k in [0..i]) {
                    elements[k].isOn = true;
                }
                if (i < ((steps-1)*2 - 1)) {
                    for(k in [(i+1)..((steps-1)*2 - 1)]) {
                        elements[k].isOn = false;
                    }
                }

                // behavior
                sliderBehavior.setValue((i+1 as Number)/steps);
            }
            onKeyPressed:function(e:KeyEvent):Void {
                if(e.code == KeyCode.VK_UP)  {
                    //println("Up pressed!");
                    sliderBehavior.setValue(2);
                }
                else if (e.code == KeyCode.VK_DOWN){
                    //println("Down pressed!");
                    sliderBehavior.setValue(1)
                }
             }
        }
        return bar;
    }

    init {
        node = Group {
            content: bind [frame, elements]
        }
    }
}

class VolumeControlItem extends Rectangle  {
    public var scaleUpTimeline: Timeline;
    public var scaleDownTimeline: Timeline;

    var enabledColor: Paint = ParanaraTheme.fillVolumeBarColor;
    var disabledColor: Paint = ParanaraTheme.disabledColor2;

    public var isOn = true;
    public var isVisible = true;
    public override var fill = bind if (isOn and isVisible) enabledColor else disabledColor
}
