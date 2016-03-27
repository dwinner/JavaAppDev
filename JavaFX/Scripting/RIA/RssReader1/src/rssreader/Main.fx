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
 
package rssreader;

import org.netbeans.javafx.datasrc.*;

/**
 * @author Maros Sandor
 */
public class Main {

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:main
    public-read def label: javafx.scene.control.Label = javafx.scene.control.Label {
        layoutX: 8.0
        layoutY: 6.0
        text: "Feeds:"
    }
    
    def __layoutInfo_feeds: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 124.0
        height: 148.0
    }
    public-read def feeds: javafx.scene.control.ListView = javafx.scene.control.ListView {
        layoutX: 6.0
        layoutY: 21.0
        layoutInfo: __layoutInfo_feeds
        items: bind feedSources
        cellFactory: listCellFactory
    }
    
    def __layoutInfo_label2: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 335.0
    }
    public-read def label2: javafx.scene.control.Label = javafx.scene.control.Label {
        layoutX: 136.0
        layoutY: 6.0
        layoutInfo: __layoutInfo_label2
        text: bind (feeds.selectedItem as DataSource).getRecordSet().eval("channel/title").trim()
    }
    
    def __layoutInfo_topics: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 335.0
        height: 148.0
    }
    public-read def topics: javafx.scene.control.ListView = javafx.scene.control.ListView {
        layoutX: 136.0
        layoutY: 21.0
        layoutInfo: __layoutInfo_topics
        items: bind (feeds.selectedItem as DataSource).getRecordSet().filter("channel/item").all()
        cellFactory: listCellFactory2
    }
    
    public-read def label3: javafx.scene.control.Label = javafx.scene.control.Label {
        layoutX: 8.0
        layoutY: 175.0
        text: "Topic Description:"
    }
    
    def __layoutInfo_description: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 465.0
        height: 97.0
    }
    public-read def description: javafx.scene.control.TextBox = javafx.scene.control.TextBox {
        layoutX: 6.0
        layoutY: 190.0
        layoutInfo: __layoutInfo_description
        text: bind (topics.selectedItem as Record).getXmlElementText("description")
        editable: false
        multiline: true
    }
    
    public-read def label4: javafx.scene.control.Label = javafx.scene.control.Label {
        layoutX: 8.0
        layoutY: 295.0
        text: "URL:"
    }
    
    def __layoutInfo_hyperlink: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 408.0
    }
    public-read def hyperlink: javafx.scene.control.Hyperlink = javafx.scene.control.Hyperlink {
        layoutX: 37.0
        layoutY: 293.0
        layoutInfo: __layoutInfo_hyperlink
        text: bind (topics.selectedItem as Record).getXmlElementText("link")
    }
    
    public-read def rssIcon: javafx.scene.image.Image = javafx.scene.image.Image {
        url: "{__DIR__}resources/rss.png"
    }
    
    public-read def imageView3: javafx.scene.image.ImageView = javafx.scene.image.ImageView {
        layoutX: 451.0
        layoutY: 294.0
        image: rssIcon
    }
    
    public-read def scene: javafx.scene.Scene = javafx.scene.Scene {
        width: 480.0
        height: 320.0
        content: getDesignRootNodes ()
    }
    
    public-read def cnnFeed: org.netbeans.javafx.datasrc.HttpDataSource = org.netbeans.javafx.datasrc.HttpDataSource {
        url: "http://rss.cnn.com/rss/cnn_topstories.rss"
        parser: org.netbeans.javafx.datasrc.Parsers.XML_PARSER
    }
    
    public-read def reutersFeed: org.netbeans.javafx.datasrc.HttpDataSource = org.netbeans.javafx.datasrc.HttpDataSource {
        url: "http://feeds.reuters.com/reuters/topNews?format=xml"
        parser: org.netbeans.javafx.datasrc.Parsers.XML_PARSER
    }
    
    public-read def bbcFeed: org.netbeans.javafx.datasrc.HttpDataSource = org.netbeans.javafx.datasrc.HttpDataSource {
        url: "http://newsrss.bbc.co.uk/rss/newsonline_world_edition/front_page/rss.xml"
        parser: org.netbeans.javafx.datasrc.Parsers.XML_PARSER
    }
    
    public-read def currentState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
    }
    
    function listCellFactory(): javafx.scene.control.ListCell {
        var listCell: javafx.scene.control.ListCell;
        
        def imageView: javafx.scene.image.ImageView = javafx.scene.image.ImageView {
            opacity: 1.0
            image: bind getIcon(listCell.item as DataSource)
            preserveRatio: true
        }
        
        def feedLabel: javafx.scene.control.Label = javafx.scene.control.Label {
            visible: bind not listCell.empty
            graphic: imageView
        }
        
        listCell = javafx.scene.control.ListCell {
            node: feedLabel
        }
        
        return listCell
    }
    
    function listCellFactory2(): javafx.scene.control.ListCell {
        var listCell2: javafx.scene.control.ListCell;
        
        def topicLabel: javafx.scene.control.Label = javafx.scene.control.Label {
            visible: bind not listCell2.empty
            text: bind (listCell2.item as Record).getXmlElementText("title")
        }
        
        listCell2 = javafx.scene.control.ListCell {
            node: topicLabel
        }
        
        return listCell2
    }
    
    public function getDesignRootNodes (): javafx.scene.Node[] {
        [ label, feeds, label2, topics, label3, description, imageView3, label4, hyperlink, ]
    }
    
    public function getDesignScene (): javafx.scene.Scene {
        scene
    }
    // </editor-fold>//GEN-END:main

    bound function getIcon(feed : DataSource) : javafx.scene.image.Image {
        javafx.scene.image.Image {
            url: feed.getRecordSet().eval("channel/image/url")
            height: 18
            preserveRatio: true
        }
    }

    var feedSources = [ cnnFeed, bbcFeed, reutersFeed ];
}

function run (): Void {
    var design = Main {};

    javafx.stage.Stage {
        title: "RSS Reader"
        scene: design.getDesignScene ()
    }
}
