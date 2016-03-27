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

import javafx.scene.control.*;
import javafx.scene.paint.*;
import com.sun.mediabox.controls.skin.*;


/**
 * @author baechul
 */

public def disabledColor: Color = Color.rgb(200, 200, 200); // LIGHTGRAY;
public def disabledColor2: Color = Color.rgb(59, 61, 63);   // DARKGRAY;

public def fillColor: Paint = Color.rgb(220, 220, 220);
public def fillColorOver: Paint = bind LinearGradient {
    startX: 0.0
    startY: 0.0
    endX: 0.0
    endY: 1.0
    proportional: true
    stops: [
        Stop {
            offset: 0.0
            color: Color.rgb(36, 227, 253)
        },
        Stop {
            offset: 1.0
            color: Color.rgb(31, 101, 195)
        }
    ]
};

public def fillVolumeBarColor = bind LinearGradient {
    startX: 0.0
    startY: 6.5
    endX: 0.0
    endY: 18.5
    proportional: false
    stops: [
        Stop {
            offset: 0.0
            color: Color.rgb(36, 227, 253)
        },
        Stop {
            offset: 1.0
            color: Color.rgb(31, 101, 195)
        }
    ]
}


public class ParanaraTheme extends Theme {
    
    public override function createMediaControlBarSkin():Skin {
        MediaControlBarSkin{}
    }

    public override function createPlayControlSkin():Skin {
        PlayControlSkin{};
    }

    public override function createSpeakerControlSkin():Skin {
        SpeakerControlSkin{}
    }

    public override function createMediaSliderSkin():Skin {
        MediaSliderSkin{}
    }

    public override function createMediaTimeSkin():Skin {
        MediaTimeSkin{}
    }

    public override function createVolumeControlSkin():Skin {
        VolumeControlSkin{}
    }

     public override function createFullScreenControlSkin():Skin {
        FullScreenControlSkin{}
    }

    public override function createMediaInfoSkin(): Skin {
        MediaInfoSkin{}
    }

    public override function createBufferIndicatorSkin(): Skin {
        BufferIndicatorSkin{}
    }

    public override function createMediaScreenSkin(): Skin {
        MediaScreenSkin{}
    }

    public override function createErrorInfoSkin(): Skin {
        ErrorInfoSkin{}
    }

}
