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
package displayshelf;

import javafx.animation.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.scene.input.*;

// width and height are updated when the screen is rotated
var width:Number = bind scene.width;
var height:Number = bind scene.height;

/* The center image will have the size of imageWidth x imageWidth, which will
 * be adjusted as followed per device screen resolution.
 */
var imageWidth: Number = bind if(width > height) height * 0.6 else width * 0.6 on replace {
    //println("imageWidth: {imageWidth}");
}

var images = [
    "DSC_0026_2.jpg",
    "DSC_0040_2.jpg",
    "DSC_0068_2.jpg",
    "DSC_0083_2.jpg",
    "DSC_0094_2.jpg",
    "DSC_0129_2.jpg",
    "DSC_0152_2.jpg",
    "DSC_0162_2.jpg",
    "DSC_0172_2.jpg",
    "DSC_0178_2.jpg",
    "DSC_0199_2.jpg",
    "DSC_0277_2.jpg",
    "DSC_0290_2.jpg",
    "DSC_0425_2.jpg"
    ];

var half = images.size()/2;

var shelf:DisplayShelf;
shelf = DisplayShelf {
    imageWidth: bind imageWidth
    scaleSmall: 0.7
    items: bind for(i in images) {
        var item:Item = Item {
            imageWidth: bind imageWidth
            angle: 45
            blocksMouse: true
            position: indexof i - half

            // load a full size image and adjust it with ImageView fitXXX based
            // on various screen resolutions.
            image:Image { url: "{__DIR__}photos/{i}" }
            shelf: bind shelf
            translateY: bind (height-imageWidth)/2;
        };
        item;
    }
    onKeyPressed:function(e:KeyEvent):Void {
        if(e.code == KeyCode.VK_LEFT or e.code == KeyCode.VK_SOFTKEY_0) {
            shelf.shift(1);
        }
        if(e.code == KeyCode.VK_RIGHT or e.code == KeyCode.VK_SOFTKEY_1) {
            shelf.shift(-1);
        }
    }
    visible: false
}

var scene:Scene = Scene {

    // MOBL-59: As of b04, the gradient works on the device but not working on the
    // emulator yet.
    fill: LinearGradient {
        startX: 0 startY: 0
        endX: 0 endY: 1
        proportional: true
        stops: [
            Stop { offset: 0.0 color: Color.rgb(150,150,150) },
            Stop { offset: 0.3 color: Color.rgb(0,0,0)},
            Stop { offset: 0.7 color: Color.rgb(0,0,0)},
            Stop { offset: 1.0 color: Color.rgb(150,150,150)},
        ]
    }
    content: [
        shelf
    ]
};

Stage {
    title: "Display Shelf Mobile"
    visible: true
    resizable: true
    scene: scene
    width: 320
    height: 240
}

var t: Timeline = Timeline {
    repeatCount: Timeline.INDEFINITE
    keyFrames: KeyFrame {
        time: 100ms
        action: function() {
            if(scene.width != 0 and scene.height != 0) {
                shelf.visible = true;
                shelf.doLayout();
                t.stop();
                shelf.requestFocus();
            }
        }
    }
}
t.play();
