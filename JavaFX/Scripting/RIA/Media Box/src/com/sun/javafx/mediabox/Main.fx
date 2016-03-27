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
package com.sun.javafx.mediabox;

import javafx.stage.*;
import javafx.scene.*;
import com.sun.javafx.mediabox.MediaBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author baechul
 */

var index = 0;
var media = [
    "http://sun.edgeboss.net/download/sun/media/1460825906/1460825906_2956241001_big-buck-bunny-640x360.flv",
    "http://sun.edgeboss.net/download/sun/media/1460825906/1460825906_11657401001_09b01828-11.flv",
    "http://sun.edgeboss.net/download/sun/media/1460825906/1460825906_11810873001_09c01923-00.flv"
];
var mediaTitles = [
    "Big Buck Bunny",
    "JavaFX",
    "JavaFX Mobile"
];
var mediaDescriptions = [
    "A well-tempered rabbit finds his day spoiled by the rude actions of the...",
    "Getting Started with JavaFX",
    "What is JavaFX Mobile?"
];
var theme = getFXArgString("theme", "paranara");
var mediaUrl = getFXArgString("mediaURL", media[0]);
var mediaViewWidth = getFXArgInt("mediaViewWidth", 640);
var mediaViewHeight = getFXArgInt("mediaViewHeight", 360);
var mediaTitle = getFXArgString("mediaTitle", "");
var mediaDescription = getFXArgString("mediaDescription", "");


var mediaBox:MediaBox = MediaBox {

    // set the current profile
 
    // media and play variables
    mediaSource:mediaUrl
    mediaTitle: mediaTitles[0]
    mediaDescription: mediaDescriptions[0]
    autoPlay: true             // default: false
    preserveRatio: true        // default: true
    
    // size and layout position
    width: bind mediaBox.scene.width
    //width: bind if({__PROFILE__} == "desktop") 640 else 1024;
    
    height: bind mediaBox.scene.height
    //height: bind if({__PROFILE__} == "desktop") 360 else 1024;
    layoutX: 0;
    layoutY: 0;

    // view
    themeStr: theme             // default: "paranara"
    mediaControlBarHeight: 25   // default: 25, possible values: 20~50
    showMediaInfo: true         // default: true

    // function variables
    onEndOfMedia: function() {
        index++;
        index = index mod sizeof media;
        mediaBox.mediaSource = media[index];
        mediaBox.mediaTitle = mediaTitles[index];
        mediaBox.mediaDescription = mediaDescriptions[index];
    }
    onMouseClicked: function(me) {
        index++;
        index = index mod sizeof media;
        mediaBox.mediaSource = media[index];
        mediaBox.mediaTitle = mediaTitles[index];
        mediaBox.mediaDescription = mediaDescriptions[index];
    }

     onKeyPressed:function(e:KeyEvent):Void {
        if((e.code == KeyCode.VK_RIGHT) or (e.code == KeyCode.VK_TRACK_NEXT)) {
            index++;
            index = index mod sizeof media;
            mediaBox.mediaSource = media[index];
            mediaBox.mediaTitle = mediaTitles[index];
            mediaBox.mediaDescription = mediaDescriptions[index];
            mediaBox.showMediaInfo = true;
        }
        else if((e.code == KeyCode.VK_LEFT) or (e.code == KeyCode.VK_TRACK_PREV)) {
            index--;
            if (index < 0) index = sizeof media - 1;
            index = index mod sizeof media;
            mediaBox.mediaSource = media[index];
            mediaBox.mediaTitle = mediaTitles[index];
            mediaBox.mediaDescription = mediaDescriptions[index];
        }
	else if (e.code == KeyCode.VK_POWER) { FX.exit(); }
        else if (e.code == KeyCode.VK_UP) {
            mediaBox.mediaControlBar.show = true;
            mediaBox.showMediaInfo = true;
        }
        else if (e.code == KeyCode.VK_DOWN) {
            mediaBox.mediaControlBar.show = false;
            mediaBox.showMediaInfo = false;
        }
	else if ((e.code == KeyCode.VK_BACK_SPACE) or
                 (e.code == KeyCode.VK_Q) or
                 (e.code == KeyCode.VK_POWER))
        {
            FX.exit();
        }
    }
}

Stage {
    title: "MediaBox Player"
    resizable: true

    scene:Scene {
        //width: getFXArgInt("mediaViewWidth", 640)
        width: getWidth()
        //height: getFXArgInt("mediaViewHeight", 360)
        height: getHeight()
        content: mediaBox
    }
}

mediaBox.requestFocus();

// helper functions

function getWidth(): Number {

        if ({__PROFILE__} == "desktop")
            return 640
        else
            return 1280
 }

function getHeight(): Number {

        if ({__PROFILE__} == "desktop")
            return 360
        else
            return 720
 }

function getFXArgString(arg:String, defaultValue:String): String {
    var val = FX.getArgument(arg);
    if (val == null) {
        return defaultValue;
    }
    return val as String;
}

function getFXArgInt(arg:String, defaultValue:Integer): Integer {
    var val = FX.getArgument(arg);
    if (val == null) {
        return defaultValue;
    }
    try {
        return Integer.parseInt(val as String);
    } catch (nfe: java.lang.NumberFormatException) {
        return defaultValue;
    }
}
