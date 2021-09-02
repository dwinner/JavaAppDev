/*
 * Copyright (c) 2010, Oracle
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 *  * Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the distribution.
 *  * Neither the name of Oracle nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */
 
package mediaview;

public class ImageFragment extends Fragment {

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:main
    public-read def imageView: javafx.scene.image.ImageView = javafx.scene.image.ImageView {
        fitWidth: 362.0
        fitHeight: 281.0
        preserveRatio: true
    }
    
    public-read def rectangle: javafx.scene.shape.Rectangle = javafx.scene.shape.Rectangle {
        opacity: 0.0
        layoutX: 6.0
        layoutY: 6.0
        fill: javafx.scene.paint.Color.web ("#000000", 0.5)
        stroke: javafx.scene.paint.Color.WHITE
        strokeWidth: 2.0
        width: 350.0
        height: 27.0
        arcWidth: 10.0
        arcHeight: 10.0
    }
    
    def __layoutInfo_label: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 338.0
    }
    public-read def label: javafx.scene.control.Label = javafx.scene.control.Label {
        opacity: 0.0
        layoutX: 10.0
        layoutY: 12.0
        layoutInfo: __layoutInfo_label
        text: bind imageView.image.url
        hpos: javafx.geometry.HPos.CENTER
        textFill: javafx.scene.paint.Color.WHITE
    }
    
    public-read def panel: javafx.scene.layout.Panel = javafx.scene.layout.Panel {
        content: [ imageView, rectangle, label, ]
    }
    
    public-read def currentState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
        names: [ "HiddenInfo", "ShownInfo", ]
        onTransitionFinished: transitionFinished
        timelines: [
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        action: function() {
                            rectangle.opacity = 0.0;
                            label.opacity = 0.0;
                        }
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            rectangle.opacity => rectangle.opacity tween javafx.animation.Interpolator.DISCRETE,
                            label.opacity => label.opacity tween javafx.animation.Interpolator.DISCRETE,
                        ]
                    }
                    javafx.animation.KeyFrame {
                        time: 500ms
                        values: [
                            rectangle.opacity => 1.0 tween javafx.animation.Interpolator.LINEAR,
                            label.opacity => 1.0 tween javafx.animation.Interpolator.LINEAR,
                        ]
                    }
                ]
            }
        ]
    }
    
    public function getDesignRootNodes (): javafx.scene.Node[] {
        [ panel, ]
    }
    // </editor-fold>//GEN-END:main

    function transitionFinished(finishedState: Integer): Void {
        if (finishedState == currentState.actual  and  finishedState == 0) {
            currentState.actual = 1;
        }
     }

    override public function getView () : javafx.scene.Node {
        panel
    }

    override public function isSupported (fileExtension: String) : Boolean {
        fileExtension == "jpg"  or  fileExtension == "jpeg"  or fileExtension == "png"
    }

    override public function setPath (path: String) : Void {
        currentState.actual = 0;
        imageView.image = if (path == null) then null else
            javafx.scene.image.Image {
                backgroundLoading: true
                url: new java.io.File (path).toURL().toString();
            };
    }

}
