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
 
package projectbrowser;

public class Main {
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:main
    def __layoutInfo_projectsListView: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 240.0
        height: 190.0
    }
    public-read def projectsListView: javafx.scene.control.ListView = javafx.scene.control.ListView {
        visible: true
        layoutInfo: __layoutInfo_projectsListView
        items: bind httpDataSource.getDataSource("projects").getRecordSet().all()
        cellFactory: listCellFactory
    }
    
    def __layoutInfo_projectsCountLabel: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 170.0
    }
    public-read def projectsCountLabel: javafx.scene.control.Label = javafx.scene.control.Label {
        visible: true
        layoutX: 20.0
        layoutY: 208.0
        layoutInfo: __layoutInfo_projectsCountLabel
        text: bind formatProjectCount()
    }
    
    public-read def progressIndicator: javafx.scene.control.ProgressIndicator = javafx.scene.control.ProgressIndicator {
        visible: bind httpDataSource.fetchingData
        layoutX: 200.0
        layoutY: 200.0
    }
    
    def __layoutInfo_prevPageButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hfill: true
    }
    public-read def prevPageButton: javafx.scene.control.Button = javafx.scene.control.Button {
        disable: bind httpDataSource.getRecordSet().currentField("prev") == null
        layoutInfo: __layoutInfo_prevPageButton
        text: "Prev Pg"
        action: prevPageButtonAction
    }
    
    def __layoutInfo_nextPageButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hfill: true
    }
    public-read def nextPageButton: javafx.scene.control.Button = javafx.scene.control.Button {
        disable: bind httpDataSource.getRecordSet().currentField("next") == null
        layoutInfo: __layoutInfo_nextPageButton
        text: "Next Pg"
        action: nextPageButtonAction
    }
    
    def __layoutInfo_detailsButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hfill: true
    }
    public-read def detailsButton: javafx.scene.control.Button = javafx.scene.control.Button {
        disable: bind projectsListView.selectedItem == null
        layoutInfo: __layoutInfo_detailsButton
        text: "Details"
        action: detailsButtonAction
    }
    
    public-read def tile: javafx.scene.layout.Tile = javafx.scene.layout.Tile {
        disable: bind httpDataSource.fetchingData
        layoutX: 20.0
        layoutY: 240.0
        content: [ prevPageButton, nextPageButton, detailsButton, ]
        columns: 3
        hgap: 6.0
        vgap: 6.0
    }
    
    public-read def listPanel: javafx.scene.layout.Panel = javafx.scene.layout.Panel {
        visible: true
        layoutY: 20.0
        content: [ projectsListView, projectsCountLabel, progressIndicator, tile, ]
    }
    
    public-read def displayNameValueLabel: javafx.scene.control.Label = javafx.scene.control.Label {
        text: bind "{(projectsListView.selectedItem as org.netbeans.javafx.datasrc.Record).getString("display_name")}"
    }
    
    public-read def nameValueLabel: javafx.scene.control.Label = javafx.scene.control.Label {
        text: bind "{(projectsListView.selectedItem as org.netbeans.javafx.datasrc.Record).getString("name")}"
    }
    
    public-read def hrefValueLabel: javafx.scene.control.Label = javafx.scene.control.Label {
        text: bind "{(projectsListView.selectedItem as org.netbeans.javafx.datasrc.Record).getString("href")}"
    }
    
    public-read def tagsValueLabel: javafx.scene.control.Label = javafx.scene.control.Label {
        text: bind "{(projectsListView.selectedItem as org.netbeans.javafx.datasrc.Record).getString("tags")}"
    }
    
    def __layoutInfo_cancelButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hfill: true
    }
    public-read def cancelButton: javafx.scene.control.Button = javafx.scene.control.Button {
        visible: true
        layoutInfo: __layoutInfo_cancelButton
        text: "Cancel"
        action: cancelButtonAction
    }
    
    def __layoutInfo_prevButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hfill: true
    }
    public-read def prevButton: javafx.scene.control.Button = javafx.scene.control.Button {
        visible: true
        disable: bind projectsListView.selectedIndex <= 0
        layoutInfo: __layoutInfo_prevButton
        text: "Prev"
        action: function ():Void { projectsListView.selectPreviousRow(); }
    }
    
    def __layoutInfo_nextButton: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hfill: true
    }
    public-read def nextButton: javafx.scene.control.Button = javafx.scene.control.Button {
        visible: true
        disable: bind projectsListView.selectedIndex >= sizeof projectsListView.items - 1
        layoutInfo: __layoutInfo_nextButton
        text: "Next"
        action: function ():Void { projectsListView.selectNextRow(); }
    }
    
    public-read def tile2: javafx.scene.layout.Tile = javafx.scene.layout.Tile {
        visible: true
        layoutX: 20.0
        layoutY: 240.0
        content: [ cancelButton, prevButton, nextButton, ]
        columns: 3
        hgap: 6.0
        vgap: 6.0
    }
    
    public-read def logoImageView: javafx.scene.image.ImageView = javafx.scene.image.ImageView {
        layoutX: 170.0
        layoutY: 40.0
        image: bind javafx.scene.image.Image { url: httpDataSource.getDataSource("projects").getRecordSet().currentString("image") backgroundLoading: true }
        fitWidth: 64.0
        fitHeight: 64.0
        preserveRatio: true
    }
    
    public-read def font: javafx.scene.text.Font = javafx.scene.text.Font {
        name: ""
        size: 14.0
    }
    
    public-read def tagsTitleLabel: javafx.scene.control.Label = javafx.scene.control.Label {
        text: "Tags:"
        font: font
    }
    
    public-read def hrefTitleLabel: javafx.scene.control.Label = javafx.scene.control.Label {
        text: "URL:"
        font: font
    }
    
    public-read def nameTitleLabel: javafx.scene.control.Label = javafx.scene.control.Label {
        text: "Name:"
        font: font
    }
    
    public-read def displayNameTitleLabel: javafx.scene.control.Label = javafx.scene.control.Label {
        text: "Display Name:"
        font: font
    }
    
    def __layoutInfo_detailsForm: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 240.0
    }
    public-read def detailsForm: javafx.scene.layout.VBox = javafx.scene.layout.VBox {
        visible: true
        layoutInfo: __layoutInfo_detailsForm
        content: [ displayNameTitleLabel, displayNameValueLabel, nameTitleLabel, nameValueLabel, hrefTitleLabel, hrefValueLabel, tagsTitleLabel, tagsValueLabel, ]
        spacing: 6.0
    }
    
    public-read def detailsPanel: javafx.scene.layout.Panel = javafx.scene.layout.Panel {
        visible: true
        layoutY: 20.0
        translateX: 260.0
        content: [ detailsForm, tile2, logoImageView, ]
    }
    
    def __layoutInfo_titleLabel: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 240.0
    }
    public-read def titleLabel: javafx.scene.control.Label = javafx.scene.control.Label {
        layoutInfo: __layoutInfo_titleLabel
        text: "NetBeans.org project list"
        font: font
        hpos: javafx.geometry.HPos.CENTER
    }
    
    public-read def scene: javafx.scene.Scene = javafx.scene.Scene {
        width: 240.0
        height: 320.0
        content: getDesignRootNodes ()
    }
    
    public-read def httpDataSource: org.netbeans.javafx.datasrc.HttpDataSource = org.netbeans.javafx.datasrc.HttpDataSource {
        url: "http://netbeans.org/api/projects.json"
        parser: org.netbeans.javafx.datasrc.Parsers.JSON_PARSER
    }
    
    public-read def currentState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
        names: [ "List", "Detail", ]
        actual: 0
        timelines: [
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            listPanel.translateX => listPanel.translateX tween javafx.animation.Interpolator.DISCRETE,
                            detailsPanel.translateX => detailsPanel.translateX tween javafx.animation.Interpolator.DISCRETE,
                        ]
                        action: function() {
                            titleLabel.text = "NetBeans.org project list";
                        }
                    }
                    javafx.animation.KeyFrame {
                        time: 800ms
                        values: [
                            listPanel.translateX => 0.0 tween javafx.animation.Interpolator.EASEBOTH,
                            detailsPanel.translateX => 260.0 tween javafx.animation.Interpolator.EASEBOTH,
                        ]
                    }
                ]
            }
            javafx.animation.Timeline {
                keyFrames: [
                    javafx.animation.KeyFrame {
                        time: 0ms
                        values: [
                            listPanel.translateX => listPanel.translateX tween javafx.animation.Interpolator.DISCRETE,
                            detailsPanel.translateX => detailsPanel.translateX tween javafx.animation.Interpolator.DISCRETE,
                        ]
                        action: function() {
                            titleLabel.text = "Project details";
                        }
                    }
                    javafx.animation.KeyFrame {
                        time: 500ms
                        values: [
                            listPanel.translateX => -260.0 tween javafx.animation.Interpolator.EASEBOTH,
                            detailsPanel.translateX => 0.0 tween javafx.animation.Interpolator.EASEBOTH,
                        ]
                    }
                ]
            }
        ]
    }
    
    function listCellFactory(): javafx.scene.control.ListCell {
        var listCell: javafx.scene.control.ListCell;
        
        def listCellLabel: javafx.scene.control.Label = javafx.scene.control.Label {
            visible: bind not listCell.empty
            text: bind (listCell.item as org.netbeans.javafx.datasrc.Record).getString("name")
        }
        
        listCell = javafx.scene.control.ListCell {
            node: listCellLabel
        }
        
        return listCell
    }
    
    public function getDesignRootNodes (): javafx.scene.Node[] {
        [ titleLabel, listPanel, detailsPanel, ]
    }
    
    public function getDesignScene (): javafx.scene.Scene {
        scene
    }
    // </editor-fold>//GEN-END:main

    var np  = 0;

    bound function formatProjectCount() {
        if ((httpDataSource.getRecordSet().currentField("total") != null) and ((sizeof projectsListView.items) > 1))
            "Projects {np + 1} - {np + sizeof projectsListView.items} of {httpDataSource.getRecordSet().currentField("total")}"
        else
            ""
    }

    function prevPageButtonAction (): Void {
        var i = sizeof projectsListView.items;
        httpDataSource.url = httpDataSource.getRecordSet().current().getString("prev");
        np -= i;
    }

    function nextPageButtonAction (): Void {
        var i = sizeof projectsListView.items;
        httpDataSource.url = httpDataSource.getRecordSet().current().getString("next");
        np += i
    }

    function detailsButtonAction (): Void {
        currentState.next();
    }

    function cancelButtonAction (): Void {
        currentState.previous();
    }

}

function run (): Void {
    var design = Main {};

    javafx.stage.Stage {
        title: "NetBeans Project Browser"
        scene: design.getDesignScene ()
    }
}
