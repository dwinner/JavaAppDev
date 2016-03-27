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
 
package netbeanslogo;

/**
 * @author mryzl
 */
public class Main {

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:main
    public-read def __fxd_graphics = javafx.fxd.FXDLoader.loadContent ("{__DIR__}nblogo.fxz");
    
    public-read def graphics: javafx.scene.Group = function (): javafx.scene.Group {
        def graphics = __fxd_graphics.getRoot ();
        graphics.layoutX = 59.0;
        graphics.layoutY = 32.0;
        graphics.effect = null;
        return graphics;
    } ();
    
    public-read def Net: javafx.scene.Group = function (): javafx.scene.Group {
        def Net = __fxd_graphics.getGroup ("Net");
        Net.visible = false;
        Net.opacity = 0.0;
        Net.rotationAxis = javafx.scene.transform.Rotate.X_AXIS;
        return Net;
    } ();
    
    public-read def Line: javafx.scene.shape.Line = function (): javafx.scene.shape.Line {
        def Line = __fxd_graphics.getObject ("Line") as javafx.scene.shape.Line;
        Line.visible = false;
        Line.rotationAxis = javafx.scene.transform.Rotate.Y_AXIS;
        return Line;
    } ();
    
    public-read def Beans: javafx.scene.Group = function (): javafx.scene.Group {
        def Beans = __fxd_graphics.getGroup ("Beans");
        Beans.visible = false;
        Beans.translateX = 200.0;
        Beans.rotationAxis = javafx.scene.transform.Rotate.Y_AXIS;
        return Beans;
    } ();
    
    public-read def Logo: javafx.scene.Group = function (): javafx.scene.Group {
        def Logo = __fxd_graphics.getGroup ("Logo");
        Logo.translateX = -200.0;
        Logo.rotate = -360.0;
        return Logo;
    } ();
    
    def __layoutInfo_indexPreviousButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hfill: true
    }
    public-read def indexPreviousButton: javafx.scene.control.Button = javafx.scene.control.Button {
        disable: bind currentState.isFirst()
        layoutInfo: __layoutInfo_indexPreviousButton
        text: "Previous"
        action: function ():Void { currentState.previous(); }
    }
    
    def __layoutInfo_indexNextButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hfill: true
    }
    public-read def indexNextButton: javafx.scene.control.Button = javafx.scene.control.Button {
        disable: bind currentState.isLast()
        layoutInfo: __layoutInfo_indexNextButton
        text: "Next"
        action: function ():Void { currentState.next(); }
    }
    
    public-read def tile: javafx.scene.layout.Tile = javafx.scene.layout.Tile {
        layoutX: 180.0
        layoutY: 211.0
        content: [ indexPreviousButton, indexNextButton, ]
        columns: 2
        hgap: 6.0
        vgap: 6.0
    }
    
    public-read def scene: javafx.scene.Scene = javafx.scene.Scene {
        width: 320.0
        height: 240.0
        content: getDesignRootNodes ()
    }
    
    public-read def reflectionEffect: javafx.scene.effect.Reflection = javafx.scene.effect.Reflection {
        fraction: 0.0
    }
    
    public-read def currentState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
        names: [ "Init", "Show", "Rotate", ]
        actual: 0
        timelines: [
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            Logo.translateX => Logo.translateX tween javafx.animation.Interpolator.DISCRETE,
                            Logo.translateY => Logo.translateY tween javafx.animation.Interpolator.DISCRETE,
                            Logo.rotate => Logo.rotate tween javafx.animation.Interpolator.DISCRETE,
                            Beans.translateX => Beans.translateX tween javafx.animation.Interpolator.DISCRETE,
                            Beans.translateY => Beans.translateY tween javafx.animation.Interpolator.DISCRETE,
                            Beans.rotate => Beans.rotate tween javafx.animation.Interpolator.DISCRETE,
                            Line.rotate => Line.rotate tween javafx.animation.Interpolator.DISCRETE,
                            Net.opacity => Net.opacity tween javafx.animation.Interpolator.DISCRETE,
                            Net.translateY => Net.translateY tween javafx.animation.Interpolator.DISCRETE,
                            Net.rotate => Net.rotate tween javafx.animation.Interpolator.DISCRETE,
                            graphics.layoutX => graphics.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            graphics.layoutY => graphics.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            reflectionEffect.fraction => reflectionEffect.fraction tween javafx.animation.Interpolator.DISCRETE,
                        ]
                    }
                    javafx.animation.KeyFrame {
                        time: 800ms
                        values: [
                            Logo.translateX => -200.0 tween javafx.animation.Interpolator.LINEAR,
                            Logo.translateY => 0.0 tween javafx.animation.Interpolator.LINEAR,
                            Logo.rotate => -360.0 tween javafx.animation.Interpolator.LINEAR,
                            Beans.translateX => 200.0 tween javafx.animation.Interpolator.LINEAR,
                            Beans.translateY => 0.0 tween javafx.animation.Interpolator.LINEAR,
                            Beans.rotate => 0.0 tween javafx.animation.Interpolator.LINEAR,
                            Line.rotate => 0.0 tween javafx.animation.Interpolator.LINEAR,
                            Net.opacity => 0.0 tween javafx.animation.Interpolator.LINEAR,
                            Net.translateY => 0.0 tween javafx.animation.Interpolator.LINEAR,
                            Net.rotate => 0.0 tween javafx.animation.Interpolator.LINEAR,
                            graphics.layoutX => 59.0 tween javafx.animation.Interpolator.LINEAR,
                            graphics.layoutY => 32.0 tween javafx.animation.Interpolator.LINEAR,
                            reflectionEffect.fraction => 0.0 tween javafx.animation.Interpolator.LINEAR,
                        ]
                        action: function() {
                            Beans.visible = true;
                            Line.visible = false;
                            Net.visible = true;
                            graphics.effect = null;
                        }
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            Logo.translateX => Logo.translateX tween javafx.animation.Interpolator.DISCRETE,
                            Logo.rotate => Logo.rotate tween javafx.animation.Interpolator.DISCRETE,
                            Beans.translateX => Beans.translateX tween javafx.animation.Interpolator.DISCRETE,
                            Net.opacity => Net.opacity tween javafx.animation.Interpolator.DISCRETE,
                        ]
                        action: function() {
                            Logo.translateY = 0.0;
                            Beans.visible = true;
                            Beans.translateY = 0.0;
                            Beans.rotate = 0.0;
                            Line.rotate = 0.0;
                            Net.visible = true;
                            Net.translateY = 0.0;
                            Net.rotate = 0.0;
                            graphics.layoutX = 59.0;
                            graphics.layoutY = 32.0;
                            graphics.effect = null;
                            reflectionEffect.fraction = 0.0;
                        }
                    }
                    javafx.animation.KeyFrame {
                        time: 800ms
                        values: [
                            Net.opacity => 1.0 tween javafx.animation.Interpolator.LINEAR,
                        ]
                    }
                    javafx.animation.KeyFrame {
                        time: 1100ms
                        values: [
                            Beans.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                        ]
                    }
                    javafx.animation.KeyFrame {
                        time: 1400ms
                        values: [
                            Logo.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            Logo.rotate => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                        ]
                    }
                    javafx.animation.KeyFrame {
                        time: 1500ms
                        action: function() {
                            Line.visible = true;
                        }
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            Logo.translateX => Logo.translateX tween javafx.animation.Interpolator.DISCRETE,
                            Logo.translateY => Logo.translateY tween javafx.animation.Interpolator.DISCRETE,
                            Beans.translateX => Beans.translateX tween javafx.animation.Interpolator.DISCRETE,
                            Beans.translateY => Beans.translateY tween javafx.animation.Interpolator.DISCRETE,
                            Beans.rotate => Beans.rotate tween javafx.animation.Interpolator.DISCRETE,
                            Line.rotate => Line.rotate tween javafx.animation.Interpolator.DISCRETE,
                            Net.opacity => Net.opacity tween javafx.animation.Interpolator.DISCRETE,
                            Net.translateY => Net.translateY tween javafx.animation.Interpolator.DISCRETE,
                            Net.rotate => Net.rotate tween javafx.animation.Interpolator.DISCRETE,
                            graphics.layoutX => graphics.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            graphics.layoutY => graphics.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            reflectionEffect.fraction => reflectionEffect.fraction tween javafx.animation.Interpolator.DISCRETE,
                        ]
                        action: function() {
                            Logo.rotate = 0.0;
                        }
                    }
                    javafx.animation.KeyFrame {
                        time: 100ms
                        action: function() {
                            graphics.effect = reflectionEffect;
                        }
                    }
                    javafx.animation.KeyFrame {
                        time: 1000ms
                        values: [
                            Logo.translateX => 0.0 tween javafx.animation.Interpolator.LINEAR,
                            Logo.translateY => 50.0 tween javafx.animation.Interpolator.LINEAR,
                            Beans.translateX => 0.0 tween javafx.animation.Interpolator.LINEAR,
                            Beans.translateY => 50.0 tween javafx.animation.Interpolator.LINEAR,
                            Beans.rotate => 360.0 tween javafx.animation.Interpolator.LINEAR,
                            Line.rotate => 90.0 tween javafx.animation.Interpolator.LINEAR,
                            Net.opacity => 1.0 tween javafx.animation.Interpolator.LINEAR,
                            Net.translateY => 50.0 tween javafx.animation.Interpolator.LINEAR,
                            Net.rotate => -360.0 tween javafx.animation.Interpolator.LINEAR,
                            graphics.layoutX => 59.0 tween javafx.animation.Interpolator.LINEAR,
                            graphics.layoutY => 32.0 tween javafx.animation.Interpolator.LINEAR,
                            reflectionEffect.fraction => 0.75 tween javafx.animation.Interpolator.LINEAR,
                        ]
                        action: function() {
                            Beans.visible = true;
                            Line.visible = true;
                            Net.visible = true;
                        }
                    }
                ]
            }
        ]
    }
    
    public function getDesignRootNodes (): javafx.scene.Node[] {
        [ graphics, tile, ]
    }
    
    public function getDesignScene (): javafx.scene.Scene {
        scene
    }
    // </editor-fold>//GEN-END:main

}

function run (): Void {
    var design = Main {};

    javafx.stage.Stage {
        title: "Main"
        scene: design.getDesignScene ()
    }
}
