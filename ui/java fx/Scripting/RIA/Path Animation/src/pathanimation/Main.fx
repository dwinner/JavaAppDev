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
package pathanimation;

import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.scene.control.Label;

// Scale that applies to the whole scene
public def S = if (Screen.primary.bounds.height < 450) then 1.0 else 2.0;
public var scene: Scene;

def P = if (S == 1.0) then "small" else "large";
def PADDING = 2 ;
def STAGEWIDTH = 240 * S;
def STAGEHEIGHT =320 * S;
def SCENELAYOUTY = 30;
var labelchoose: Label;
var labelplay: Label;

public function makeImage(name: String) {
    makeImage(__DIR__, name);
}

public function makeImage(dir: String, name: String) {
    Image { url: "{dir}resources/{P}/{name}" };
}

var scenarios: Scenario[] = [ CarScenario {} ];
def currentScenario = bind scenarios[currentIndex];
public var currentIndex = 0 on replace oldIndex {
    if (currentIndex < 0 or currentIndex > 1) {
        currentIndex = oldIndex;
    } else {
        if (currentIndex >= sizeof scenarios) {
            // ShipScenario is created lazily
            var s = ShipScenario {};
            insert s into scenarios;
            insert s before scene.content[0];
        }

        scenarios[oldIndex].fadeOut();
        currentScenario.fadeIn();
    }
};

 function togglePlaying() {
    currentScenario.running = not currentScenario.running;
}

public function run() {
    // Node that handles keyboard input
    var keyHandler: Node;

    Stage {
        title: "Path Animation"
        resizable: false
        scene: scene = Scene {
            width: STAGEWIDTH
            height: STAGEHEIGHT

            var sbtn1: ScenarioButton;
            var sbtn2: ScenarioButton;
            var pbtn: Node;
            def TEXTLAYOUT = 10 ;
            var font = Font { name: "Arial" size: 12*S };
            content: [
                scenarios,
                sbtn1 = ScenarioButton {
                    index: 0
                    thumbIcon: CarScenario.THUMB_ICON
                    layoutX: 20*S
                    layoutY: SCENELAYOUTY*S
                },
                sbtn2 = ScenarioButton {
                    index: 1
                    thumbIcon: ShipScenario.THUMB_ICON
                    layoutX: bind 30*S + sbtn2.SIZE
                    layoutY: SCENELAYOUTY*S
                },
                // Buttons to switch scenarios
                labelchoose= Label {
                    layoutX: bind (sbtn1.boundsInParent.minX +
                                      sbtn2.boundsInParent.maxX -
                                      labelchoose.boundsInLocal.width) / PADDING
                    layoutY: TEXTLAYOUT*S
                    text: "Choose Your Vehicle"
                    font: font
                    textFill: Color.WHITE
                 },
                 Label {
                    layoutX: bind (sbtn1.boundsInParent.minX +
                                      sbtn2.boundsInParent.maxX -
                                      labelchoose.boundsInLocal.width) / PADDING
                    layoutY: TEXTLAYOUT*S
                    text: "Choose Your Vehicle"
                    font: font
                    textFill: Color.WHITE
                 },

                 // The Play button
                 labelplay =Label {
                    layoutX: bind (pbtn.boundsInParent.minX +
                                      pbtn.boundsInParent.maxX -
                                      labelplay.boundsInLocal.width) / PADDING
                    layoutY: TEXTLAYOUT*S
                    text: bind if (currentScenario.running) then "Stop" else "Play"
                    font: font
                    textFill: Color.WHITE
                 },
                 Label {
                    layoutX: bind (pbtn.boundsInParent.minX +
                                   pbtn.boundsInParent.maxX -
                                   labelplay.boundsInLocal.width) / PADDING
                    layoutY: TEXTLAYOUT*S
                    text: bind if (currentScenario.running) then "Stop" else "Play"
                    font: font
                    textFill: Color.WHITE
                 },
                 keyHandler = pbtn = ImageView {
                     var playImage = makeImage("play.png");
                     var pauseImage = makeImage("pause.png");
                     image: bind if (currentScenario.running) then pauseImage else playImage
                     layoutX: bind scene.width - playImage.width - 20*S
                     layoutY: SCENELAYOUTY*S
                     onMousePressed: function(ev: MouseEvent) {
                         togglePlaying();
                     }
                     onKeyPressed: function(ev: KeyEvent) {
                         if (ev.code == KeyCode.VK_LEFT) {
                             currentIndex--;
                         } else if (ev.code == KeyCode.VK_RIGHT) {
                             currentIndex++;
                         } else if (ev.code == KeyCode.VK_ENTER or
                                   ev.code == KeyCode.VK_SPACE) {
                                       togglePlaying();
                                   }
                     }
                }
            ]
        }
    }

    keyHandler.requestFocus()
}