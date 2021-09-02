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
package photoeffects;

import javafx.scene.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.shape.*;
import javafx.stage.*;


// the three data model variables
var brightness = 0.0;
var contrast = 1.0;
var saturation = 0.0;

var gap = 10;
var TEXT_COLOR = Color.GRAY;

var stageDragInitialX:Number;
var stageDragInitialY:Number;

var inBrowser = ("{__PROFILE__}" == "browser");
var draggable = AppletStageExtension.appletDragSupported;
var dragRect:Rectangle = Rectangle {
    x: 0
    y: 0
    width: 520
    height: 25
    fill: Color.TRANSPARENT
    onMousePressed:function(e) {
        stageDragInitialX = e.screenX - stage.x;
        stageDragInitialY = e.screenY - stage.y;
    }
     onMouseDragged: function(e) {
        stage.x = e.screenX - stageDragInitialX;
        stage.y = e.screenY - stageDragInitialY;
     }

};
var dragTextVisible = bind inBrowser and draggable and dragRect.hover;

var dragControl:Group = Group {
    content: [
        Text { 
            x: 360
            y: 17
            content: "Drag out of Browser"
            fill: TEXT_COLOR
            visible: bind dragTextVisible
        },
        ImageView {
            x: 500
            y: 8
            image:Image { url: "{__DIR__}images/close_rollover.png" }
            visible: bind not inBrowser
        },
        ImageView {
            x: 500
            y: 8
            image:Image { url: "{__DIR__}images/dragOut_rollover.png" }
            visible: bind inBrowser and draggable
        },
        Rectangle {
            x: 500
            y: 8
            width: 10
            height: 10
            fill: Color.TRANSPARENT
            onMouseClicked:function(e:MouseEvent):Void { 
                stage.close();
            }
        }
        ]
};

var g:Group = Group {
    content: [

        // the background and top header
        Rectangle {
            fill: Color.BLACK
            width: 520
            height: 520
        },
        Text {
            x: 10
            y: 17
            content: "PHOTO EFFECTS"
            fill: TEXT_COLOR
        },
        Text {
            x: 120
            y: 17
            content: "with JavaFX"
            fill: TEXT_COLOR
        },
        dragControl,
        Line {
            stroke: TEXT_COLOR
            startX: 0
            endX: 520
            startY: 30
            endY: 30
        },

        // create a header with three sliders bound to the data model variables
        Group {
            content: [
                ImageView { 
                    x: gap
                    y: 50
                    image:Image { url:"{__DIR__}images/headerM.png" }
                },
                CustomSlider {
                    translateX: 30
                    translateY: 90
                    value: bind brightness with inverse
                    minValue: -1.0
                    maxValue: 1.0
                    minButtonImage: Image { url: "{__DIR__}images/bl.png" }
                    maxButtonImage: Image { url: "{__DIR__}images/bh.png" }
                },
                Text {
                    translateX: 60
                    translateY: 120
                    content: "Brightness"
                    fill: TEXT_COLOR
                },
                CustomSlider {
                    translateX: 190
                    translateY: 90
                    value: bind contrast with inverse
                    minValue: 0.25
                    maxValue: 4
                    minButtonImage: Image { url: "{__DIR__}images/cl.png" }
                    maxButtonImage: Image { url: "{__DIR__}images/ch.png" }
                },
                Text {
                    translateX: 220
                    translateY: 120
                    content: "Contrast"
                    fill: TEXT_COLOR
                },
                CustomSlider {
                    translateX: 350
                    translateY: 90
                    value: bind saturation with inverse
                    minValue: -1.0
                    maxValue:  1.0
                    minButtonImage: Image { url: "{__DIR__}images/sl.png" }
                    maxButtonImage: Image { url: "{__DIR__}images/sh.png" }
                },
                Text {
                    translateX: 380
                    translateY: 120
                    content: "Saturation"
                    fill: TEXT_COLOR
                },
            ]
        },

        // the actual image to be adjusted
        ImageView {
            translateX: gap
            translateY: 180
            image: Image { url: "{__DIR__}images/giraffe.jpg" }

            // the color adjust effect, bound to the data model variables
            effect: ColorAdjust {
                brightness: bind brightness
                contrast: bind contrast
                saturation: bind saturation
            }
        },
        
    ]
};

// show it all on screen
var stage:Stage = Stage {
    style: StageStyle.UNDECORATED
    visible: true
    scene: Scene {
        content: g
    }
    extensions: [
        AppletStageExtension {
            shouldDragStart: function(e): Boolean {
                return inBrowser and e.primaryButtonDown and dragRect.hover;
            }
            onDragStarted: function() {
                inBrowser = false;
            }
            onAppletRestored: function() {
                inBrowser = true;
            }
            useDefaultClose: false
        }
    ]
}

insert dragRect before dragControl.content[0];      // insert dragRect here to avoid possible cycle during initialization
