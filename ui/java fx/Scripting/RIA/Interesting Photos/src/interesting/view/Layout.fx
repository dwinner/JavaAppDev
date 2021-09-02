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

package interesting.view;

import javafx.scene.text.Font;

//Layout details for various screen dimensions

public class Layout {
    public var width:Integer = 240;
    public var height:Integer = 320;
    public var descTextY:Number = 36;
    public var titleFont:Font = Font {
        name: "Bitstream Vera Sans Bold"
        size: 14
    };
    public var descFont:Font = Font {
        name: "Bitstream Vera Sans Bold"
        size: 11
    };
    public var thumbSize:Number = 64;
    public var thumbCols:Integer = 3;
    public var thumbRows:Integer = 3;
    public var thumbGroupX:Number;
    public var thumbGroupY:Number;
    public var thumbGroupW:Number;
    public var thumbGroupH:Number;
    public var pageCount:Integer = 6;
    public var pageButtonW:Number = 26;
    public var pageButtonH:Number = 19;
    public var pageGroupW:Number;
    public var imageCount:Integer = 65;
}

def layoutgp = 2;
def layoutgpXY = 2.0;
def pagegp = 1;
def pagegpcount = 7;
def layout = [
    // Layout Bounds - [480 X 800] - Portrait
    Layout {
        width: 480
        height: 800
        descTextY: 30
        titleFont:Font {
            name: "Bitstream Vera Sans Bold"
            size: 20
        }
        descFont:Font {
            name: "Bitstream Vera Sans Bold"
            size: 17
        }
        thumbSize: 120
        thumbCols: 3
        thumbRows: 5
        pageCount: 10
        pageButtonW: 40
        pageButtonH: 30
        imageCount: 170
    },
    // Layout Bounds - [800 X 480] - Landscape
    Layout {
        width: 800
        height: 480
        descTextY: 38
        titleFont:Font {
            name: "Bitstream Vera Sans Bold"
            size: 20
        }
        descFont:Font {
            name: "Bitstream Vera Sans Bold"
            size: 17
        }
        thumbSize: 150
        thumbCols: 4
        thumbRows: 2
        pageCount: 18
        pageButtonW: 40
        pageButtonH: 30
        imageCount: 170
    },
    // Layout Bounds - [240 X 320] - Portrait
    Layout {
        width: 240
        height: 320
        descTextY: 20
        titleFont:Font {
            name: "Bitstream Vera Sans Bold"
            size: 14
        }
        descFont:Font {
            name: "Bitstream Vera Sans Bold"
            size: 11
        }
        thumbSize: 65
        thumbCols: 3
        thumbRows: 3
        pageCount: 8
        pageButtonW: 26
        pageButtonH: 19
        imageCount: 90
    },
    // Layout Bounds - [320 X 240] - Landscape
    Layout {
        width: 320
        height: 240
        descTextY: 13
        titleFont:Font {
            name: "Bitstream Vera Sans Bold"
            size: 14
        }
        descFont:Font {
            name: "Bitstream Vera Sans Bold"
            size: 11
        }
        thumbSize: 65
        thumbCols: 4
        thumbRows: 2
        pageCount: 8
        pageButtonW: 26
        pageButtonH: 19
        imageCount: 90
    }
];

public function getLayout(sceneBounds:String):Layout {
    var newLayout:Layout;
    // Layout Bounds - [480 X 800] - Portrait
    if ("480X800".equals(sceneBounds)) {
        newLayout = layout[0];
    }
    // Layout Bounds - [800 X 480] - Landscape
    else if ("800X480".equals(sceneBounds)) {
        newLayout = layout[1];
    }
    // Layout Bounds - [240 X 320] - Portrait
    else if ("240X320".equals(sceneBounds)) {
        newLayout = layout[2];
    }
    // Layout Bounds - [320 X 240] - Landscape
    else if ("320X240".equals(sceneBounds)) {
        newLayout = layout[3];
    }
    // Layout Bounds - [240 X 320] - Portrait
    else {
        newLayout = layout[2];
    }

    // Initialize group width and height
    newLayout.thumbGroupW = (newLayout.thumbSize + layoutgp) * newLayout.thumbCols;
    newLayout.thumbGroupH = (newLayout.thumbSize + layoutgp) * newLayout.thumbRows;
    newLayout.thumbGroupX = (newLayout.width - newLayout.thumbGroupW)/layoutgpXY;
    newLayout.thumbGroupY = (newLayout.height - newLayout.thumbGroupH)/layoutgpXY;
    newLayout.pageGroupW = (newLayout.pageButtonW * (newLayout.pageCount + pagegp))
                            - (newLayout.pageCount * pagegpcount); // Minus spacing of HBox
    return newLayout;
}
