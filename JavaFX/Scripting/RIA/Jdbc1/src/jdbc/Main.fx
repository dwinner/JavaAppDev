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
 
package jdbc;

import org.netbeans.javafx.datasrc.DbDataSource;

/**
 * @author Maros Sandor
 */
public class Main {

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:main
    public-read def label: javafx.scene.control.Label = javafx.scene.control.Label {
        layoutX: 6.0
        layoutY: 6.0
        text: "Address Book:"
    }
    
    def __layoutInfo_listView: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 469.0
        height: 263.0
    }
    public-read def listView: javafx.scene.control.ListView = javafx.scene.control.ListView {
        layoutX: 6.0
        layoutY: 21.0
        layoutInfo: __layoutInfo_listView
        items: bind sort(friendsDS.getRecordSet().all())
        cellFactory: listCellFactory
    }
    
    def __layoutInfo_editButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 101.0
        height: 23.0
    }
    public-read def editButton: javafx.scene.control.Button = javafx.scene.control.Button {
        disable: bind listView.selectedItem == null
        layoutX: 10.0
        layoutY: 288.0
        layoutInfo: __layoutInfo_editButton
        text: "View / Edit ..."
        action: editButtonAction
    }
    
    def __layoutInfo_saveButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 101.0
        height: 23.0
    }
    public-read def saveButton: javafx.scene.control.Button = javafx.scene.control.Button {
        visible: true
        layoutX: 373.0
        layoutY: 288.0
        layoutInfo: __layoutInfo_saveButton
        text: "Save & Close"
        action: saveButtonAction
    }
    
    def __layoutInfo_label3: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 69.0
        height: 17.0
    }
    public-read def label3: javafx.scene.control.Label = javafx.scene.control.Label {
        visible: true
        layoutX: 6.0
        layoutY: 43.0
        layoutInfo: __layoutInfo_label3
        text: "ID:"
    }
    
    def __layoutInfo_tbPID: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 160.0
        height: 23.0
    }
    public-read def tbPID: javafx.scene.control.TextBox = javafx.scene.control.TextBox {
        visible: true
        disable: true
        layoutX: 89.0
        layoutY: 39.0
        layoutInfo: __layoutInfo_tbPID
        editable: false
    }
    
    public-read def label4: javafx.scene.control.Label = javafx.scene.control.Label {
        visible: true
        layoutX: 6.0
        layoutY: 73.0
        text: "First Name:"
    }
    
    def __layoutInfo_tbFirstName: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 160.0
        height: 23.0
    }
    public-read def tbFirstName: javafx.scene.control.TextBox = javafx.scene.control.TextBox {
        visible: true
        layoutX: 89.0
        layoutY: 69.0
        layoutInfo: __layoutInfo_tbFirstName
    }
    
    def __layoutInfo_tbLastName: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 161.0
        height: 23.0
    }
    public-read def tbLastName: javafx.scene.control.TextBox = javafx.scene.control.TextBox {
        visible: true
        layoutX: 89.0
        layoutY: 99.0
        layoutInfo: __layoutInfo_tbLastName
    }
    
    def __layoutInfo_label5: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 70.0
        height: 20.0
    }
    public-read def label5: javafx.scene.control.Label = javafx.scene.control.Label {
        visible: true
        layoutX: 6.0
        layoutY: 103.0
        layoutInfo: __layoutInfo_label5
        text: "Last Name:"
    }
    
    public-read def label10: javafx.scene.control.Label = javafx.scene.control.Label {
        layoutX: 6.0
        layoutY: 133.0
        text: "Phone:"
    }
    
    def __layoutInfo_tbPhone: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 161.0
    }
    public-read def tbPhone: javafx.scene.control.TextBox = javafx.scene.control.TextBox {
        layoutX: 89.0
        layoutY: 129.0
        layoutInfo: __layoutInfo_tbPhone
    }
    
    public-read def label9: javafx.scene.control.Label = javafx.scene.control.Label {
        layoutX: 6.0
        layoutY: 163.0
        text: "Email:"
    }
    
    def __layoutInfo_tbEmail: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 161.0
    }
    public-read def tbEmail: javafx.scene.control.TextBox = javafx.scene.control.TextBox {
        layoutX: 89.0
        layoutY: 159.0
        layoutInfo: __layoutInfo_tbEmail
    }
    
    def __layoutInfo_closeButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 96.0
        height: 23.0
    }
    public-read def closeButton: javafx.scene.control.Button = javafx.scene.control.Button {
        visible: true
        layoutX: 6.0
        layoutY: 288.0
        layoutInfo: __layoutInfo_closeButton
        text: "Close"
        action: closeButtonAction
    }
    
    def __layoutInfo_deleteButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 98.0
        height: 23.0
    }
    public-read def deleteButton: javafx.scene.control.Button = javafx.scene.control.Button {
        visible: true
        layoutX: 126.0
        layoutY: 288.0
        layoutInfo: __layoutInfo_deleteButton
        text: "Delete..."
        action: deleteButtonAction
    }
    
    public-read def label7: javafx.scene.control.Label = javafx.scene.control.Label {
        visible: true
        layoutX: 114.0
        layoutY: 148.0
        text: "Name:"
    }
    
    public-read def cancelButton: javafx.scene.control.Button = javafx.scene.control.Button {
        visible: true
        layoutX: 288.0
        layoutY: 225.0
        text: "Cancel"
        action: cancelButtonAction
    }
    
    public-read def yesButton: javafx.scene.control.Button = javafx.scene.control.Button {
        visible: true
        layoutX: 114.0
        layoutY: 225.0
        text: "Yes, delete!"
        action: yesButtonAction
    }
    
    public-read def label6: javafx.scene.control.Label = javafx.scene.control.Label {
        visible: true
        layoutX: 114.0
        layoutY: 74.0
        text: "Are you sure you want tot delete this entry?"
    }
    
    def __layoutInfo_label8: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 16.0
        height: 18.0
    }
    public-read def label8: javafx.scene.control.Label = javafx.scene.control.Label {
        visible: true
        layoutX: 114.0
        layoutY: 118.0
        layoutInfo: __layoutInfo_label8
        text: "ID:"
    }
    
    def __layoutInfo_textbox: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 250.0
        height: 23.0
    }
    public-read def textbox: javafx.scene.control.TextBox = javafx.scene.control.TextBox {
        visible: true
        disable: true
        layoutX: 194.0
        layoutY: 118.0
        layoutInfo: __layoutInfo_textbox
        editable: false
    }
    
    def __layoutInfo_textbox2: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 250.0
        height: 23.0
    }
    public-read def textbox2: javafx.scene.control.TextBox = javafx.scene.control.TextBox {
        visible: true
        disable: true
        layoutX: 194.0
        layoutY: 144.0
        layoutInfo: __layoutInfo_textbox2
        editable: false
    }
    
    def __layoutInfo_panel2: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 480.0
        height: 320.0
    }
    public-read def panel2: javafx.scene.layout.Panel = javafx.scene.layout.Panel {
        visible: true
        layoutX: -500.0
        layoutY: 0.0
        layoutInfo: __layoutInfo_panel2
        content: [ label7, cancelButton, yesButton, label6, label8, textbox, textbox2, ]
    }
    
    public-read def createButton: javafx.scene.control.Button = javafx.scene.control.Button {
        visible: true
        layoutX: 376.0
        layoutY: 288.0
        text: "Create New..."
        action: createButtonAction
    }
    
    public-read def font: javafx.scene.text.Font = javafx.scene.text.Font {
        size: 16.0
        oblique: false
        embolden: false
    }
    
    public-read def label2: javafx.scene.control.Label = javafx.scene.control.Label {
        visible: true
        layoutX: 6.0
        layoutY: 12.0
        font: font
    }
    
    def __layoutInfo_panel: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 468.0
        height: 271.0
    }
    public-read def panel: javafx.scene.layout.Panel = javafx.scene.layout.Panel {
        visible: true
        layoutX: 5.0
        layoutY: -280.0
        layoutInfo: __layoutInfo_panel
        content: [ label2, label3, tbPID, label4, tbFirstName, tbLastName, label5, label10, tbPhone, label9, tbEmail, ]
    }
    
    public-read def scene: javafx.scene.Scene = javafx.scene.Scene {
        width: 480.0
        height: 320.0
        content: getDesignRootNodes ()
    }
    
    public-read def friendsDS: org.netbeans.javafx.datasrc.DbDataSource = org.netbeans.javafx.datasrc.DbDataSource {
        connectionString: "jdbc:derby:memory:friends;create=true"
        user: ""
        password: ""
        query: "select * from FRIEND"
    }
    
    public-read def currentState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
        names: [ "browse", "edit", "create", "deleteConfirm", ]
        actual: 0
        timelines: [
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            label.layoutX => label.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            listView.layoutX => listView.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            editButton.layoutX => editButton.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            saveButton.layoutY => saveButton.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            label2.layoutY => label2.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            label3.layoutX => label3.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            label3.layoutY => label3.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            label4.layoutX => label4.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            label4.layoutY => label4.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            tbFirstName.layoutX => tbFirstName.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            tbFirstName.layoutY => tbFirstName.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            tbLastName.layoutX => tbLastName.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            label5.layoutX => label5.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutX => panel.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutY => panel.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            closeButton.layoutY => closeButton.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            deleteButton.layoutY => deleteButton.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            panel2.layoutX => panel2.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            panel2.layoutY => panel2.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            createButton.layoutX => createButton.layoutX tween javafx.animation.Interpolator.DISCRETE,
                        ]
                    }
                    javafx.animation.KeyFrame {
                        time: 800ms
                        values: [
                            label.layoutX => 6.0 tween javafx.animation.Interpolator.EASEOUT,
                            listView.layoutX => 6.0 tween javafx.animation.Interpolator.EASEBOTH,
                            editButton.layoutX => 10.0 tween javafx.animation.Interpolator.EASEBOTH,
                            saveButton.layoutY => 330.0 tween javafx.animation.Interpolator.EASEBOTH,
                            label2.layoutY => 12.0 tween javafx.animation.Interpolator.DISCRETE,
                            label3.layoutX => 6.0 tween javafx.animation.Interpolator.DISCRETE,
                            label3.layoutY => 43.0 tween javafx.animation.Interpolator.DISCRETE,
                            label4.layoutX => 6.0 tween javafx.animation.Interpolator.DISCRETE,
                            label4.layoutY => 71.0 tween javafx.animation.Interpolator.DISCRETE,
                            tbFirstName.layoutX => 89.0 tween javafx.animation.Interpolator.DISCRETE,
                            tbFirstName.layoutY => 68.0 tween javafx.animation.Interpolator.DISCRETE,
                            tbLastName.layoutX => 89.0 tween javafx.animation.Interpolator.DISCRETE,
                            label5.layoutX => 6.0 tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutX => 5.0 tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutY => -280.0 tween javafx.animation.Interpolator.EASEBOTH,
                            closeButton.layoutY => 330.0 tween javafx.animation.Interpolator.EASEBOTH,
                            deleteButton.layoutY => 330.0 tween javafx.animation.Interpolator.EASEBOTH,
                            panel2.layoutX => -500.0 tween javafx.animation.Interpolator.EASEBOTH,
                            panel2.layoutY => 0.0 tween javafx.animation.Interpolator.DISCRETE,
                            createButton.layoutX => 376.0 tween javafx.animation.Interpolator.EASEBOTH,
                        ]
                        action: function() {
                            editButton.action = editButtonAction;
                            saveButton.visible = true;
                            label2.visible = true;
                            label3.visible = true;
                            tbPID.visible = true;
                            label4.visible = true;
                            tbFirstName.visible = true;
                            deleteButton.visible = true;
                        }
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            label.layoutX => label.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            listView.layoutX => listView.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            editButton.layoutX => editButton.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            saveButton.layoutY => saveButton.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            label2.layoutY => label2.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            label3.layoutX => label3.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            label3.layoutY => label3.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            label4.layoutX => label4.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            label4.layoutY => label4.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            tbFirstName.layoutX => tbFirstName.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            tbFirstName.layoutY => tbFirstName.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            tbLastName.layoutX => tbLastName.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            label5.layoutX => label5.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutX => panel.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutY => panel.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            closeButton.layoutY => closeButton.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            deleteButton.layoutY => deleteButton.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            panel2.layoutX => panel2.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            panel2.layoutY => panel2.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            createButton.layoutX => createButton.layoutX tween javafx.animation.Interpolator.DISCRETE,
                        ]
                    }
                    javafx.animation.KeyFrame {
                        time: 800ms
                        values: [
                            label.layoutX => -100.0 tween javafx.animation.Interpolator.EASEOUT,
                            listView.layoutX => 500.0 tween javafx.animation.Interpolator.EASEBOTH,
                            editButton.layoutX => -110.0 tween javafx.animation.Interpolator.EASEBOTH,
                            saveButton.layoutY => 288.0 tween javafx.animation.Interpolator.EASEBOTH,
                            label2.layoutY => 12.0 tween javafx.animation.Interpolator.DISCRETE,
                            label3.layoutX => 6.0 tween javafx.animation.Interpolator.DISCRETE,
                            label3.layoutY => 43.0 tween javafx.animation.Interpolator.DISCRETE,
                            label4.layoutX => 6.0 tween javafx.animation.Interpolator.DISCRETE,
                            label4.layoutY => 71.0 tween javafx.animation.Interpolator.DISCRETE,
                            tbFirstName.layoutX => 89.0 tween javafx.animation.Interpolator.DISCRETE,
                            tbFirstName.layoutY => 68.0 tween javafx.animation.Interpolator.DISCRETE,
                            tbLastName.layoutX => 89.0 tween javafx.animation.Interpolator.DISCRETE,
                            label5.layoutX => 6.0 tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutX => 5.0 tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutY => 6.0 tween javafx.animation.Interpolator.EASEBOTH,
                            closeButton.layoutY => 288.0 tween javafx.animation.Interpolator.EASEBOTH,
                            deleteButton.layoutY => 288.0 tween javafx.animation.Interpolator.EASEBOTH,
                            panel2.layoutX => -500.0 tween javafx.animation.Interpolator.EASEBOTH,
                            panel2.layoutY => 0.0 tween javafx.animation.Interpolator.DISCRETE,
                            createButton.layoutX => 490.0 tween javafx.animation.Interpolator.EASEBOTH,
                        ]
                        action: function() {
                            editButton.action = editButtonAction;
                            saveButton.visible = true;
                            label2.visible = true;
                            label3.visible = true;
                            tbPID.visible = true;
                            label4.visible = true;
                            tbFirstName.visible = true;
                            deleteButton.visible = true;
                        }
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            label.layoutX => label.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            listView.layoutX => listView.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            editButton.layoutX => editButton.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            saveButton.layoutY => saveButton.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            label2.layoutY => label2.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            label3.layoutX => label3.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            label3.layoutY => label3.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            label4.layoutX => label4.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            label4.layoutY => label4.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            tbFirstName.layoutX => tbFirstName.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            tbFirstName.layoutY => tbFirstName.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            tbLastName.layoutX => tbLastName.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            label5.layoutX => label5.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutX => panel.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutY => panel.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            closeButton.layoutY => closeButton.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            deleteButton.layoutY => deleteButton.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            panel2.layoutX => panel2.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            panel2.layoutY => panel2.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            createButton.layoutX => createButton.layoutX tween javafx.animation.Interpolator.DISCRETE,
                        ]
                    }
                    javafx.animation.KeyFrame {
                        time: 800ms
                        values: [
                            label.layoutX => -100.0 tween javafx.animation.Interpolator.EASEOUT,
                            listView.layoutX => 500.0 tween javafx.animation.Interpolator.EASEBOTH,
                            editButton.layoutX => -110.0 tween javafx.animation.Interpolator.EASEBOTH,
                            saveButton.layoutY => 288.0 tween javafx.animation.Interpolator.EASEBOTH,
                            label2.layoutY => 12.0 tween javafx.animation.Interpolator.DISCRETE,
                            label3.layoutX => 6.0 tween javafx.animation.Interpolator.DISCRETE,
                            label3.layoutY => 43.0 tween javafx.animation.Interpolator.DISCRETE,
                            label4.layoutX => 6.0 tween javafx.animation.Interpolator.DISCRETE,
                            label4.layoutY => 71.0 tween javafx.animation.Interpolator.DISCRETE,
                            tbFirstName.layoutX => 89.0 tween javafx.animation.Interpolator.DISCRETE,
                            tbFirstName.layoutY => 68.0 tween javafx.animation.Interpolator.DISCRETE,
                            tbLastName.layoutX => 89.0 tween javafx.animation.Interpolator.DISCRETE,
                            label5.layoutX => 6.0 tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutX => 5.0 tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutY => 6.0 tween javafx.animation.Interpolator.EASEBOTH,
                            closeButton.layoutY => 288.0 tween javafx.animation.Interpolator.EASEBOTH,
                            deleteButton.layoutY => 330.0 tween javafx.animation.Interpolator.EASEBOTH,
                            panel2.layoutX => -500.0 tween javafx.animation.Interpolator.EASEBOTH,
                            panel2.layoutY => 0.0 tween javafx.animation.Interpolator.DISCRETE,
                            createButton.layoutX => 490.0 tween javafx.animation.Interpolator.EASEBOTH,
                        ]
                        action: function() {
                            editButton.action = editButtonAction;
                            saveButton.visible = true;
                            label2.visible = true;
                            label3.visible = true;
                            tbPID.visible = true;
                            label4.visible = true;
                            tbFirstName.visible = true;
                            deleteButton.visible = false;
                        }
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            label.layoutX => label.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            listView.layoutX => listView.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            editButton.layoutX => editButton.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            saveButton.layoutY => saveButton.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            label2.layoutY => label2.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            label3.layoutX => label3.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            label3.layoutY => label3.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            label4.layoutX => label4.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            label4.layoutY => label4.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            tbFirstName.layoutX => tbFirstName.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            tbFirstName.layoutY => tbFirstName.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            tbLastName.layoutX => tbLastName.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            label5.layoutX => label5.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutX => panel.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutY => panel.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            closeButton.layoutY => closeButton.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            deleteButton.layoutY => deleteButton.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            panel2.layoutX => panel2.layoutX tween javafx.animation.Interpolator.DISCRETE,
                            panel2.layoutY => panel2.layoutY tween javafx.animation.Interpolator.DISCRETE,
                            createButton.layoutX => createButton.layoutX tween javafx.animation.Interpolator.DISCRETE,
                        ]
                    }
                    javafx.animation.KeyFrame {
                        time: 800ms
                        values: [
                            label.layoutX => -100.0 tween javafx.animation.Interpolator.EASEOUT,
                            listView.layoutX => 500.0 tween javafx.animation.Interpolator.EASEBOTH,
                            editButton.layoutX => -110.0 tween javafx.animation.Interpolator.EASEBOTH,
                            saveButton.layoutY => 330.0 tween javafx.animation.Interpolator.EASEBOTH,
                            label2.layoutY => 12.0 tween javafx.animation.Interpolator.DISCRETE,
                            label3.layoutX => 6.0 tween javafx.animation.Interpolator.DISCRETE,
                            label3.layoutY => 43.0 tween javafx.animation.Interpolator.DISCRETE,
                            label4.layoutX => 6.0 tween javafx.animation.Interpolator.DISCRETE,
                            label4.layoutY => 71.0 tween javafx.animation.Interpolator.DISCRETE,
                            tbFirstName.layoutX => 89.0 tween javafx.animation.Interpolator.DISCRETE,
                            tbFirstName.layoutY => 68.0 tween javafx.animation.Interpolator.DISCRETE,
                            tbLastName.layoutX => 89.0 tween javafx.animation.Interpolator.DISCRETE,
                            label5.layoutX => 6.0 tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutX => 5.0 tween javafx.animation.Interpolator.DISCRETE,
                            panel.layoutY => -280.0 tween javafx.animation.Interpolator.EASEBOTH,
                            closeButton.layoutY => 330.0 tween javafx.animation.Interpolator.EASEBOTH,
                            deleteButton.layoutY => 330.0 tween javafx.animation.Interpolator.EASEBOTH,
                            panel2.layoutX => 0.0 tween javafx.animation.Interpolator.EASEBOTH,
                            panel2.layoutY => 0.0 tween javafx.animation.Interpolator.DISCRETE,
                            createButton.layoutX => 490.0 tween javafx.animation.Interpolator.EASEBOTH,
                        ]
                        action: function() {
                            editButton.action = editButtonAction;
                            saveButton.visible = true;
                            label2.visible = true;
                            label3.visible = true;
                            tbPID.visible = true;
                            label4.visible = true;
                            tbFirstName.visible = true;
                            deleteButton.visible = true;
                        }
                    }
                ]
            }
        ]
    }
    
    function listCellFactory(): javafx.scene.control.ListCell {
        var listCell: javafx.scene.control.ListCell;
        
        def label11: javafx.scene.control.Label = javafx.scene.control.Label {
            visible: bind not listCell.empty
            text: bind toString(listCell.item)
        }
        
        listCell = javafx.scene.control.ListCell {
            node: label11
        }
        
        return listCell
    }
    
    public function getDesignRootNodes (): javafx.scene.Node[] {
        [ label, listView, editButton, saveButton, panel, closeButton, deleteButton, panel2, createButton, ]
    }
    
    public function getDesignScene (): javafx.scene.Scene {
        scene
    }
    // </editor-fold>//GEN-END:main

    function toString(o : Object) : String {
        var r = o as org.netbeans.javafx.datasrc.Record;
        "{r.getString("FIRSTNAME")} {r.getString("LASTNAME")}"
    }

    var currentID = bind (listView.selectedItem as org.netbeans.javafx.datasrc.Record).get("ID").toString();

    function yesButtonAction (): Void {
        friendsDS.remove("FRIEND", "ID={currentID}");
        currentState.actual = currentState.findIndex("browse");
        friendsDS.refresh();
    }

    function cancelButtonAction (): Void {
        currentState.actual = currentState.findIndex("edit");
    }

    function deleteButtonAction (): Void {
        textbox.text = currentID;
        textbox2.text = toString(listView.selectedItem);
        currentState.actual = currentState.findIndex("deleteConfirm");
    }

    function closeButtonAction (): Void {
        currentState.actual = currentState.findIndex("browse");
    }

    function saveButtonAction (): Void {
        saveValues();
        currentState.actual = currentState.findIndex("browse");
        friendsDS.refresh();
    }

    bound function sort(records : org.netbeans.javafx.datasrc.Record []) : org.netbeans.javafx.datasrc.Record [] {
        javafx.util.Sequences.sort(records, EntryComparator{}) as org.netbeans.javafx.datasrc.Record[]
    }

    function saveValues() : Void {
        var fields : javafx.data.Pair [] = [];
        insert javafx.data.Pair {
            name: "FIRSTNAME"
            value: "'{tbFirstName.text}'"
        } into fields;
        insert javafx.data.Pair {
            name: "LASTNAME"
            value: "'{tbLastName.text}'"
        } into fields;
        insert javafx.data.Pair {
            name: "PHONE"
            value: "'{tbPhone.text}'"
        } into fields;
        insert javafx.data.Pair {
            name: "EMAIL"
            value: "'{tbEmail.text}'"
        } into fields;

        if (tbPID.text != "") {
            friendsDS.update("FRIEND", fields , "ID={currentID}");
        } else {
            friendsDS.create("FRIEND", fields);
        }
    }

    function editButtonAction (): Void {
        label2.text = toString(listView.selectedItem);
        tbPID.text = currentID;
        tbFirstName.text = (listView.selectedItem as org.netbeans.javafx.datasrc.Record).get("FIRSTNAME").toString();
        tbLastName.text = (listView.selectedItem as org.netbeans.javafx.datasrc.Record).get("LASTNAME").toString();
        tbPhone.text = (listView.selectedItem as org.netbeans.javafx.datasrc.Record).get("PHONE").toString();
        tbEmail.text = (listView.selectedItem as org.netbeans.javafx.datasrc.Record).get("EMAIL").toString();
        currentState.next();
    }

    function createButtonAction (): Void {
        label2.text = "New Contact";
        tbPID.text = "";
        tbFirstName.text = "";
        tbLastName.text = "";
        tbPhone.text = "";
        tbEmail.text = "";
        currentState.actual = currentState.findIndex("create");
    }

    function entryRecordDisplayName (record:org.netbeans.javafx.datasrc.Record): String {
        record.getString("FIRSTNAME").concat(" ").concat(record.getString("LASTNAME"));
    }

}

class EntryComparator extends java.util.Comparator {
    override public function compare(a : Object, b : Object) : Integer {
        def aa = a as org.netbeans.javafx.datasrc.Record;
        def bb = b as org.netbeans.javafx.datasrc.Record;
        aa.getString("LASTNAME").compareTo(bb.getString("LASTNAME"))
    }
}

function run (): Void {
    initSampleData();
    var design = Main {};

    javafx.stage.Stage {
        title: "Address Book"
        scene: design.getDesignScene ()
    }
}

function initSampleData() : Void {
    java.lang.Class.forName ("org.apache.derby.jdbc.EmbeddedDriver");
    var ds = org.netbeans.javafx.datasrc.DbDataSource {
        autoRefresh: false
        connectionString: "jdbc:derby:memory:friends;create=true"
    };

    try {
        ds.execute("create table APP.FRIEND ("
            "ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
            "LASTNAME VARCHAR(30),"
            "FIRSTNAME VARCHAR(30),"
            "PHONE VARCHAR(20),"
            "EMAIL VARCHAR(30)"
            ")");
    } catch (e : java.sql.SQLException) {
        javafx.stage.Alert.inform("SQL Error", "There was an error creating sample database. Make sure your project includes 'derby.jar' of JavaDB >= 10.5.2 on the classpath.");
        throw e;
    }

    ds.create("FRIEND", [ javafx.data.Pair { name: "LASTNAME" value: "'Smith'" }, javafx.data.Pair { name: "FIRSTNAME" value: "'John'" }, javafx.data.Pair { name: "PHONE" value: "'899264712'" }, javafx.data.Pair { name: "EMAIL" value: "'john.smith@barney.com'"} ]);
    ds.create("FRIEND", [ javafx.data.Pair { name: "LASTNAME" value: "'Nielsen'" }, javafx.data.Pair { name: "FIRSTNAME" value: "'Bjorn'" }, javafx.data.Pair { name: "PHONE" value: "'8816231'" }, javafx.data.Pair { name: "EMAIL" value: "'bj@hotmail.com'"} ]);
    ds.create("FRIEND", [ javafx.data.Pair { name: "LASTNAME" value: "'Brahmaputra'" }, javafx.data.Pair { name: "FIRSTNAME" value: "'Huhla'" }, javafx.data.Pair { name: "PHONE" value: "'447161144'" }, javafx.data.Pair { name: "EMAIL" value: "'putra@brahma.net'"} ]);
    ds.create("FRIEND", [ javafx.data.Pair { name: "LASTNAME" value: "'Schumacher'" }, javafx.data.Pair { name: "FIRSTNAME" value: "'Dieter'" }, javafx.data.Pair { name: "PHONE" value: "'63819821'" }, javafx.data.Pair { name: "EMAIL" value: "'d.macher@schu.de'"} ]);
    ds.create("FRIEND", [ javafx.data.Pair { name: "LASTNAME" value: "'Novak'" }, javafx.data.Pair { name: "FIRSTNAME" value: "'Jan'" }, javafx.data.Pair { name: "PHONE" value: "'590349377'" }, javafx.data.Pair { name: "EMAIL" value: "'novak221@seznam.cz'"} ]);
    ds.create("FRIEND", [ javafx.data.Pair { name: "LASTNAME" value: "'Fernandez'" }, javafx.data.Pair { name: "FIRSTNAME" value: "'Juan'" }, javafx.data.Pair { name: "PHONE" value: "'44991823'" }, javafx.data.Pair { name: "EMAIL" value: "'zorro42@gmail.com'"} ]);
    ds.create("FRIEND", [ javafx.data.Pair { name: "LASTNAME" value: "'Li'" }, javafx.data.Pair { name: "FIRSTNAME" value: "'Lei'" }, javafx.data.Pair { name: "PHONE" value: "'33997123'" }, javafx.data.Pair { name: "EMAIL" value: "'leililei@freenet.cn'"} ]);
    ds.create("FRIEND", [ javafx.data.Pair { name: "LASTNAME" value: "'Volkov'" }, javafx.data.Pair { name: "FIRSTNAME" value: "'Dmitriy'" }, javafx.data.Pair { name: "PHONE" value: "'590901231'" }, javafx.data.Pair { name: "EMAIL" value: "'cska@mockba.ru'"} ]);
}

