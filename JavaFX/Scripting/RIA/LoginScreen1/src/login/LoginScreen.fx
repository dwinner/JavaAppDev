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
 
package login;

public class LoginScreen {

    def PASSWORD = "passwd";
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:main
    public-read def label: javafx.scene.control.Label = javafx.scene.control.Label {
        text: "Login:"
    }
    
    def __layoutInfo_loginTextbox: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hfill: true
    }
    public-read def loginTextbox: javafx.scene.control.TextBox = javafx.scene.control.TextBox {
        layoutInfo: __layoutInfo_loginTextbox
    }
    
    public-read def label2: javafx.scene.control.Label = javafx.scene.control.Label {
        text: "Password:"
    }
    
    def __layoutInfo_passwordTextbox: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 200.0
        hfill: true
    }
    public-read def passwordTextbox: javafx.scene.control.PasswordBox = javafx.scene.control.PasswordBox {
        layoutInfo: __layoutInfo_passwordTextbox
    }
    
    public-read def loginButton: javafx.scene.control.Button = javafx.scene.control.Button {
        text: "Login"
        action: loginButtonAction
    }
    
    public-read def clearButton: javafx.scene.control.Button = javafx.scene.control.Button {
        text: "Clear"
        action: clearButtonAction
    }
    
    public-read def hbox: javafx.scene.layout.HBox = javafx.scene.layout.HBox {
        layoutX: 80.0
        layoutY: 0.0
        content: [ loginButton, clearButton, ]
        spacing: 10.0
    }
    
    public-read def label3: javafx.scene.control.Label = javafx.scene.control.Label {
        text: "Type login name and password and press Login."
    }
    
    public-read def label5: javafx.scene.control.Label = javafx.scene.control.Label {
        text: "Note: Enter \'{PASSWORD}\' as password for sucessful login."
        textFill: javafx.scene.paint.Color.GRAY
    }
    
    public-read def vbox: javafx.scene.layout.VBox = javafx.scene.layout.VBox {
        layoutX: 97.0
        layoutY: 90.0
        content: [ label, loginTextbox, label2, passwordTextbox, hbox, label3, label5, ]
        spacing: 5.0
    }
    
    def __layoutInfo_label4: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 430.0
        height: 16.0
    }
    public-read def label4: javafx.scene.control.Label = javafx.scene.control.Label {
        layoutX: 23.0
        layoutY: 60.0
        layoutInfo: __layoutInfo_label4
    }
    
    public-read def button3: javafx.scene.control.Button = javafx.scene.control.Button {
        visible: false
        layoutX: 337.0
        layoutY: 80.0
        text: "Back"
        action: backButtonAction
    }
    
    public-read def image: javafx.scene.image.Image = javafx.scene.image.Image {
        url: "{__DIR__}resources/ProjectKenaiLogo.png"
    }
    
    public-read def imageView: javafx.scene.image.ImageView = javafx.scene.image.ImageView {
        image: image
    }
    
    public-read def scene: javafx.scene.Scene = javafx.scene.Scene {
        width: 480.0
        height: 320.0
        content: getDesignRootNodes ()
    }
    
    public-read def currentState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
        names: [ "LoginScreen", "Success", "Failure", ]
        actual: 0
        timelines: [
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        action: function() {
                            vbox.visible = true;
                            vbox.disable = false;
                            label4.visible = false;
                            label4.id = "resultLabel";
                            label4.text = null;
                            button3.visible = false;
                        }
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        action: function() {
                            vbox.visible = false;
                            vbox.disable = false;
                            label4.visible = true;
                            label4.id = "";
                            label4.text = "Welcome {loginTextbox.text}. Login successful";
                            button3.visible = true;
                        }
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        action: function() {
                            vbox.visible = false;
                            vbox.disable = false;
                            label4.visible = true;
                            label4.id = "";
                            label4.text = "Login failed.";
                            button3.visible = true;
                        }
                    }
                ]
            }
        ]
    }
    
    public function getDesignRootNodes (): javafx.scene.Node[] {
        [ imageView, vbox, label4, button3, ]
    }
    
    public function getDesignScene (): javafx.scene.Scene {
        scene
    }
    // </editor-fold>//GEN-END:main

    function loginButtonAction (): Void {
        if (passwordTextbox.text == PASSWORD) {
            currentState.actual = currentState.findIndex ("Success");
        } else {
            currentState.actual = currentState.findIndex ("Failure");
        }
    }

    function clearButtonAction(): Void {
        loginTextbox.text = "";
        passwordTextbox.text = "";
    }

    function backButtonAction (): Void {
        currentState.actual = currentState.findIndex ("LoginScreen");
    }

}

function run (): Void {
    var design = LoginScreen {};

    javafx.stage.Stage {
        title: "Login Screen"
        scene: design.getDesignScene ()
    }
}
