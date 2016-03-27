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
 
package magiclabel;

import javafx.scene.paint.Color;

public class MagicLabel {
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:main
    public-read def changeColorButton: javafx.scene.control.Button = javafx.scene.control.Button {
        text: "Change Color"
        action: changeColorButtonAction
    }
    
    public-read def rotateButton: javafx.scene.control.Button = javafx.scene.control.Button {
        text: "Rotate"
        action: rotateButtonAction
    }
    
    public-read def trashButton: javafx.scene.control.Button = javafx.scene.control.Button {
        layoutX: 165.0
        layoutY: 0.0
        text: "Explode"
        action: trashButtonAction
    }
    
    def __layoutInfo_tile: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 320.0
    }
    public-read def tile: javafx.scene.layout.Tile = javafx.scene.layout.Tile {
        layoutY: 10.0
        layoutInfo: __layoutInfo_tile
        content: [ changeColorButton, rotateButton, trashButton, ]
        columns: 3
        hgap: 6.0
        vgap: 6.0
        hpos: javafx.geometry.HPos.CENTER
    }
    
    public-read def font: javafx.scene.text.Font = javafx.scene.text.Font {
        size: 20.0
    }
    
    public-read def redColor: javafx.scene.paint.Color = javafx.scene.paint.Color {
        red: 1.0
    }
    
    public-read def circle8: javafx.scene.shape.Circle = javafx.scene.shape.Circle {
        fill: redColor
        centerX: 104.0
        centerY: 178.0
        radius: 4.5
    }
    
    public-read def line12: javafx.scene.shape.Line = javafx.scene.shape.Line {
        stroke: redColor
        strokeWidth: 5.0
        startX: 23.0
        startY: 137.0
        endX: 95.0
        endY: 174.0
    }
    
    public-read def circle7: javafx.scene.shape.Circle = javafx.scene.shape.Circle {
        fill: redColor
        centerX: 167.0
        centerY: 127.0
        radius: 3.5
    }
    
    public-read def line11: javafx.scene.shape.Line = javafx.scene.shape.Line {
        stroke: redColor
        strokeWidth: 5.0
        startX: 112.0
        startY: 173.0
        endX: 160.0
        endY: 134.0
    }
    
    public-read def circle6: javafx.scene.shape.Circle = javafx.scene.shape.Circle {
        fill: redColor
        centerX: 75.0
        centerY: 86.0
        radius: 3.0
    }
    
    public-read def line10: javafx.scene.shape.Line = javafx.scene.shape.Line {
        stroke: redColor
        strokeWidth: 5.0
        startX: 83.0
        startY: 88.0
        endX: 159.0
        endY: 122.0
    }
    
    public-read def circle5: javafx.scene.shape.Circle = javafx.scene.shape.Circle {
        fill: redColor
        centerX: 16.0
        centerY: 134.0
        radius: 3.0
    }
    
    public-read def line9: javafx.scene.shape.Line = javafx.scene.shape.Line {
        stroke: redColor
        strokeWidth: 4.0
        startX: 21.0
        startY: 128.0
        endX: 69.0
        endY: 92.0
    }
    
    public-read def line8: javafx.scene.shape.Line = javafx.scene.shape.Line {
        stroke: redColor
        strokeWidth: 6.0
        startX: 96.0
        startY: 77.0
        endX: 102.0
        endY: 165.0
    }
    
    public-read def line7: javafx.scene.shape.Line = javafx.scene.shape.Line {
        stroke: redColor
        strokeWidth: 5.0
        startX: 169.0
        startY: 29.0
        endX: 167.0
        endY: 119.0
    }
    
    public-read def line6: javafx.scene.shape.Line = javafx.scene.shape.Line {
        stroke: redColor
        strokeWidth: 4.0
        startX: 68.0
        startY: 11.0
        endX: 73.0
        endY: 78.0
    }
    
    public-read def line5: javafx.scene.shape.Line = javafx.scene.shape.Line {
        stroke: redColor
        strokeWidth: 4.0
        startX: 5.0
        startY: 49.0
        endX: 13.0
        endY: 125.0
    }
    
    public-read def line4: javafx.scene.shape.Line = javafx.scene.shape.Line {
        stroke: redColor
        strokeWidth: 5.0
        startX: 103.0
        startY: 65.0
        endX: 162.0
        endY: 26.0
    }
    
    public-read def circle4: javafx.scene.shape.Circle = javafx.scene.shape.Circle {
        fill: redColor
        centerX: 96.0
        centerY: 69.0
        radius: 4.0
    }
    
    public-read def line3: javafx.scene.shape.Line = javafx.scene.shape.Line {
        stroke: redColor
        strokeWidth: 5.0
        startX: 11.0
        startY: 42.0
        endX: 88.0
        endY: 66.0
    }
    
    public-read def circle3: javafx.scene.shape.Circle = javafx.scene.shape.Circle {
        fill: redColor
        centerX: 169.0
        centerY: 21.0
        radius: 3.5
    }
    
    public-read def line2: javafx.scene.shape.Line = javafx.scene.shape.Line {
        stroke: redColor
        strokeWidth: 5.0
        startX: 75.0
        startY: 3.0
        endX: 159.0
        endY: 18.0
    }
    
    public-read def circle2: javafx.scene.shape.Circle = javafx.scene.shape.Circle {
        fill: redColor
        centerX: 67.0
        centerY: 3.0
        radius: 3.0
    }
    
    public-read def line: javafx.scene.shape.Line = javafx.scene.shape.Line {
        stroke: redColor
        strokeWidth: 4.0
        startX: 9.0
        startY: 34.0
        endX: 61.0
        endY: 6.0
    }
    
    public-read def circle: javafx.scene.shape.Circle = javafx.scene.shape.Circle {
        fill: redColor
        stroke: null
        strokeWidth: 1.0
        centerX: 3.0
        centerY: 40.0
        radius: 3.0
    }
    
    public-read def panel: javafx.scene.layout.Panel = javafx.scene.layout.Panel {
        content: [ circle, line, circle2, line2, circle3, line3, circle4, line4, line5, line6, line7, line8, line9, circle5, line10, circle6, line11, circle7, line12, circle8, ]
    }
    
    public-read def greenColor: javafx.scene.paint.Color = javafx.scene.paint.Color {
        green: 1.0
    }
    
    public-read def blueColor: javafx.scene.paint.Color = javafx.scene.paint.Color {
        blue: 1.0
    }
    
    public-read def image: javafx.scene.image.Image = javafx.scene.image.Image {
        url: "{__DIR__}netbeans_logo.png"
    }
    
    public-read def imageView: javafx.scene.image.ImageView = javafx.scene.image.ImageView {
        image: image
    }
    
    public-read def vbox: javafx.scene.layout.VBox = javafx.scene.layout.VBox {
        layoutX: 71.0
        layoutY: 54.0
        content: [ panel, imageView, ]
        spacing: 6.0
    }
    
    public-read def scene: javafx.scene.Scene = javafx.scene.Scene {
        width: 320.0
        height: 320.0
        content: getDesignRootNodes ()
    }
    
    public-read def colorState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
        names: [ "Red Color", "Green Color", "Blue Color", ]
        actual: 0
        timelines: [
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        action: function() {
                            circle.fill = redColor;
                            line.stroke = redColor;
                            circle2.fill = redColor;
                            line2.stroke = redColor;
                            circle3.fill = redColor;
                            line3.stroke = redColor;
                            circle4.fill = redColor;
                            line4.stroke = redColor;
                            line5.stroke = redColor;
                            line6.stroke = redColor;
                            line7.stroke = redColor;
                            line8.stroke = redColor;
                            line9.stroke = redColor;
                            circle5.fill = redColor;
                            line10.stroke = redColor;
                            circle6.fill = redColor;
                            line11.stroke = redColor;
                            circle7.fill = redColor;
                            line12.stroke = redColor;
                            circle8.fill = redColor;
                        }
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        action: function() {
                            circle.fill = greenColor;
                            line.stroke = greenColor;
                            circle2.fill = greenColor;
                            line2.stroke = greenColor;
                            circle3.fill = greenColor;
                            line3.stroke = greenColor;
                            circle4.fill = greenColor;
                            line4.stroke = greenColor;
                            line5.stroke = greenColor;
                            line6.stroke = greenColor;
                            line7.stroke = greenColor;
                            line8.stroke = greenColor;
                            line9.stroke = greenColor;
                            circle5.fill = greenColor;
                            line10.stroke = greenColor;
                            circle6.fill = greenColor;
                            line11.stroke = greenColor;
                            circle7.fill = greenColor;
                            line12.stroke = greenColor;
                            circle8.fill = greenColor;
                        }
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        action: function() {
                            circle.fill = blueColor;
                            line.stroke = blueColor;
                            circle2.fill = blueColor;
                            line2.stroke = blueColor;
                            circle3.fill = blueColor;
                            line3.stroke = blueColor;
                            circle4.fill = blueColor;
                            line4.stroke = blueColor;
                            line5.stroke = blueColor;
                            line6.stroke = blueColor;
                            line7.stroke = blueColor;
                            line8.stroke = blueColor;
                            line9.stroke = blueColor;
                            circle5.fill = blueColor;
                            line10.stroke = blueColor;
                            circle6.fill = blueColor;
                            line11.stroke = blueColor;
                            circle7.fill = blueColor;
                            line12.stroke = blueColor;
                            circle8.fill = blueColor;
                        }
                    }
                ]
            }
        ]
    }
    
    public-read def rotationState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
        names: [ "0", "90", "180", "270", ]
        actual: 0
        timelines: [
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            vbox.rotate => vbox.rotate tween javafx.animation.Interpolator.DISCRETE,
                        ]
                    }
                    javafx.animation.KeyFrame {
                        time: 500ms
                        values: [
                            vbox.rotate => 0.0 tween javafx.animation.Interpolator.LINEAR,
                        ]
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            vbox.rotate => vbox.rotate tween javafx.animation.Interpolator.DISCRETE,
                        ]
                    }
                    javafx.animation.KeyFrame {
                        time: 500ms
                        values: [
                            vbox.rotate => 90.0 tween javafx.animation.Interpolator.LINEAR,
                        ]
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            vbox.rotate => vbox.rotate tween javafx.animation.Interpolator.DISCRETE,
                        ]
                    }
                    javafx.animation.KeyFrame {
                        time: 500ms
                        values: [
                            vbox.rotate => 180.0 tween javafx.animation.Interpolator.LINEAR,
                        ]
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            vbox.rotate => vbox.rotate tween javafx.animation.Interpolator.DISCRETE,
                        ]
                    }
                    javafx.animation.KeyFrame {
                        time: 500ms
                        values: [
                            vbox.rotate => 270.0 tween javafx.animation.Interpolator.LINEAR,
                        ]
                    }
                ]
            }
        ]
    }
    
    public-read def cubeState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
        names: [ "Cube", "Exploded", ]
        actual: 0
        timelines: [
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            circle.translateX => circle.translateX tween javafx.animation.Interpolator.DISCRETE,
                            circle.translateY => circle.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line.translateX => line.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line.translateY => line.translateY tween javafx.animation.Interpolator.DISCRETE,
                            circle2.translateX => circle2.translateX tween javafx.animation.Interpolator.DISCRETE,
                            circle2.translateY => circle2.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line2.translateX => line2.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line2.translateY => line2.translateY tween javafx.animation.Interpolator.DISCRETE,
                            circle3.translateX => circle3.translateX tween javafx.animation.Interpolator.DISCRETE,
                            circle3.translateY => circle3.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line3.translateX => line3.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line3.translateY => line3.translateY tween javafx.animation.Interpolator.DISCRETE,
                            circle4.translateY => circle4.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line4.translateX => line4.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line4.translateY => line4.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line5.translateX => line5.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line5.translateY => line5.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line6.translateX => line6.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line6.translateY => line6.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line7.translateX => line7.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line7.translateY => line7.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line8.translateX => line8.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line8.translateY => line8.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line9.translateX => line9.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line9.translateY => line9.translateY tween javafx.animation.Interpolator.DISCRETE,
                            circle5.translateX => circle5.translateX tween javafx.animation.Interpolator.DISCRETE,
                            circle5.translateY => circle5.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line10.translateX => line10.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line10.translateY => line10.translateY tween javafx.animation.Interpolator.DISCRETE,
                            circle6.translateX => circle6.translateX tween javafx.animation.Interpolator.DISCRETE,
                            circle6.translateY => circle6.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line11.translateX => line11.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line11.translateY => line11.translateY tween javafx.animation.Interpolator.DISCRETE,
                            circle7.translateX => circle7.translateX tween javafx.animation.Interpolator.DISCRETE,
                            circle7.translateY => circle7.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line12.translateX => line12.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line12.translateY => line12.translateY tween javafx.animation.Interpolator.DISCRETE,
                            circle8.translateX => circle8.translateX tween javafx.animation.Interpolator.DISCRETE,
                            circle8.translateY => circle8.translateY tween javafx.animation.Interpolator.DISCRETE,
                        ]
                        action: function() {
                            trashButton.text = "Explode";
                            trashButton.action = trashButtonAction;
                        }
                    }
                    javafx.animation.KeyFrame {
                        time: 1200ms
                        values: [
                            circle.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle2.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle2.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line2.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line2.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle3.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle3.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line3.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line3.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle4.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line4.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line4.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line5.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line5.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line6.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line6.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line7.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line7.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line8.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line8.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line9.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line9.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle5.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle5.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line10.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line10.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle6.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle6.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line11.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line11.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle7.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle7.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line12.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            line12.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle8.translateX => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle8.translateY => 0.0 tween javafx.animation.Interpolator.EASEOUT,
                        ]
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            circle.translateX => circle.translateX tween javafx.animation.Interpolator.DISCRETE,
                            circle.translateY => circle.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line.translateX => line.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line.translateY => line.translateY tween javafx.animation.Interpolator.DISCRETE,
                            circle2.translateX => circle2.translateX tween javafx.animation.Interpolator.DISCRETE,
                            circle2.translateY => circle2.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line2.translateX => line2.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line2.translateY => line2.translateY tween javafx.animation.Interpolator.DISCRETE,
                            circle3.translateX => circle3.translateX tween javafx.animation.Interpolator.DISCRETE,
                            circle3.translateY => circle3.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line3.translateX => line3.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line3.translateY => line3.translateY tween javafx.animation.Interpolator.DISCRETE,
                            circle4.translateY => circle4.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line4.translateX => line4.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line4.translateY => line4.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line5.translateX => line5.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line5.translateY => line5.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line6.translateX => line6.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line6.translateY => line6.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line7.translateX => line7.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line7.translateY => line7.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line8.translateX => line8.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line8.translateY => line8.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line9.translateX => line9.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line9.translateY => line9.translateY tween javafx.animation.Interpolator.DISCRETE,
                            circle5.translateX => circle5.translateX tween javafx.animation.Interpolator.DISCRETE,
                            circle5.translateY => circle5.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line10.translateX => line10.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line10.translateY => line10.translateY tween javafx.animation.Interpolator.DISCRETE,
                            circle6.translateX => circle6.translateX tween javafx.animation.Interpolator.DISCRETE,
                            circle6.translateY => circle6.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line11.translateX => line11.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line11.translateY => line11.translateY tween javafx.animation.Interpolator.DISCRETE,
                            circle7.translateX => circle7.translateX tween javafx.animation.Interpolator.DISCRETE,
                            circle7.translateY => circle7.translateY tween javafx.animation.Interpolator.DISCRETE,
                            line12.translateX => line12.translateX tween javafx.animation.Interpolator.DISCRETE,
                            line12.translateY => line12.translateY tween javafx.animation.Interpolator.DISCRETE,
                            circle8.translateX => circle8.translateX tween javafx.animation.Interpolator.DISCRETE,
                            circle8.translateY => circle8.translateY tween javafx.animation.Interpolator.DISCRETE,
                        ]
                        action: function() {
                            trashButton.text = "Fix Cube";
                            trashButton.action = trashButtonAction;
                        }
                    }
                    javafx.animation.KeyFrame {
                        time: 300ms
                        values: [
                            circle.translateX => -200.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle.translateY => 10.0 tween javafx.animation.Interpolator.EASEOUT,
                            line.translateX => -230.0 tween javafx.animation.Interpolator.EASEOUT,
                            line.translateY => -230.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle2.translateX => -10.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle2.translateY => -200.0 tween javafx.animation.Interpolator.EASEOUT,
                            line2.translateX => 10.0 tween javafx.animation.Interpolator.EASEOUT,
                            line2.translateY => -230.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle3.translateX => 180.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle3.translateY => -180.0 tween javafx.animation.Interpolator.EASEOUT,
                            line3.translateX => -250.0 tween javafx.animation.Interpolator.EASEOUT,
                            line3.translateY => 10.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle4.translateY => 250.0 tween javafx.animation.Interpolator.EASEOUT,
                            line4.translateX => 250.0 tween javafx.animation.Interpolator.EASEOUT,
                            line4.translateY => 10.0 tween javafx.animation.Interpolator.EASEOUT,
                            line5.translateX => -230.0 tween javafx.animation.Interpolator.EASEOUT,
                            line5.translateY => -150.0 tween javafx.animation.Interpolator.EASEOUT,
                            line6.translateX => -50.0 tween javafx.animation.Interpolator.EASEOUT,
                            line6.translateY => -220.0 tween javafx.animation.Interpolator.EASEOUT,
                            line7.translateX => 250.0 tween javafx.animation.Interpolator.EASEOUT,
                            line7.translateY => -120.0 tween javafx.animation.Interpolator.EASEOUT,
                            line8.translateX => 50.0 tween javafx.animation.Interpolator.EASEOUT,
                            line8.translateY => 250.0 tween javafx.animation.Interpolator.EASEOUT,
                            line9.translateX => -230.0 tween javafx.animation.Interpolator.EASEOUT,
                            line9.translateY => 50.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle5.translateX => -200.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle5.translateY => 100.0 tween javafx.animation.Interpolator.EASEOUT,
                            line10.translateX => 300.0 tween javafx.animation.Interpolator.EASEOUT,
                            line10.translateY => 50.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle6.translateX => 30.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle6.translateY => -200.0 tween javafx.animation.Interpolator.EASEOUT,
                            line11.translateX => 200.0 tween javafx.animation.Interpolator.EASEOUT,
                            line11.translateY => 200.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle7.translateX => 250.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle7.translateY => 150.0 tween javafx.animation.Interpolator.EASEOUT,
                            line12.translateX => -120.0 tween javafx.animation.Interpolator.EASEOUT,
                            line12.translateY => 250.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle8.translateX => -40.0 tween javafx.animation.Interpolator.EASEOUT,
                            circle8.translateY => 250.0 tween javafx.animation.Interpolator.EASEOUT,
                        ]
                    }
                ]
            }
        ]
    }
    
    public function getDesignRootNodes (): javafx.scene.Node[] {
        [ tile, vbox, ]
    }
    
    public function getDesignScene (): javafx.scene.Scene {
        scene
    }
    // </editor-fold>//GEN-END:main

    function trashButtonAction(): Void {
        cubeState.nextWrapped();
    }

    function rotateButtonAction (): Void {
        if (rotationState.actual == 3) {
            vbox.rotate = vbox.rotate - 360;
        }
        rotationState.nextWrapped()
    }

    function changeColorButtonAction (): Void {
        colorState.nextWrapped()
    }

}

function run (): Void {
    var design = MagicLabel {};

    javafx.stage.Stage {
        title: "Magic Label"
        scene: design.getDesignScene ()
    }
}
