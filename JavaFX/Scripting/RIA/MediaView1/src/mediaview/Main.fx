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

import org.netbeans.javafx.datasrc.*;

public class Main {

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:main
    def __layoutInfo_folderTextBox: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 468.0
        hgrow: javafx.scene.layout.Priority.ALWAYS
    }
    public-read def folderTextBox: javafx.scene.control.TextBox = javafx.scene.control.TextBox {
        layoutX: 6.0
        layoutY: 6.0
        layoutInfo: __layoutInfo_folderTextBox
        text: java.lang.System.getProperty ("user.home")
    }
    
    def __layoutInfo_listingListView: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 100.0
        height: 281.0
    }
    public-read def listingListView: javafx.scene.control.ListView = javafx.scene.control.ListView {
        layoutX: 6.0
        layoutY: 33.0
        layoutInfo: __layoutInfo_listingListView
        items: bind createFolderListing(fsDataSource.getRecordSet().all())
        cellFactory: listCellFactory
    }
    
    def __layoutInfo_viewStack: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 362.0
        height: 281.0
        minWidth: 362.0
        minHeight: 281.0
        maxWidth: 362.0
        maxHeight: 281.0
    }
    public-read def viewStack: javafx.scene.layout.Stack = javafx.scene.layout.Stack {
        layoutX: 112.0
        layoutY: 33.0
        layoutInfo: __layoutInfo_viewStack
    }
    
    public-read def linearGradient: javafx.scene.paint.LinearGradient = javafx.scene.paint.LinearGradient {
        endX: 0.0
        stops: [ javafx.scene.paint.Stop { offset: 0.0, color: javafx.scene.paint.Color.web ("#FFFFFF") }, javafx.scene.paint.Stop { offset: 0.2, color: javafx.scene.paint.Color.web ("#DDDDDD") }, javafx.scene.paint.Stop { offset: 1.0, color: javafx.scene.paint.Color.web ("#FFFFFF") }, ]
    }
    
    public-read def scene: javafx.scene.Scene = javafx.scene.Scene {
        width: 480.0
        height: 320.0
        content: getDesignRootNodes ()
        fill: linearGradient
    }
    
    public-read def fsDataSource: org.netbeans.javafx.datasrc.FilesystemDataSource = org.netbeans.javafx.datasrc.FilesystemDataSource {
        root: bind folderTextBox.text
    }
    
    public-read def currentState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
    }
    
    function listCellFactory(): javafx.scene.control.ListCell {
        var listCell: javafx.scene.control.ListCell;
        
        def label: javafx.scene.control.Label = javafx.scene.control.Label {
            visible: bind not listCell.empty
            text: bind "{(listCell.item as Item).name}"
        }
        
        listCell = javafx.scene.control.ListCell {
            node: label
        }
        
        return listCell
    }
    
    public function getDesignRootNodes (): javafx.scene.Node[] {
        [ folderTextBox, listingListView, viewStack, ]
    }
    
    public function getDesignScene (): javafx.scene.Scene {
        scene
    }
    // </editor-fold>//GEN-END:main

    def emptyFragment = EmptyFragment {};
    def fragments: Fragment[] = [ ImageFragment {}, MediaFragment {}, TextFragment {} ];

    function createFolderListing (records: Record[]): Item[] {
        var items: Item[] = [];

        def parent = new java.io.File(folderTextBox.text).getParent();
        if (parent != null) {
            insert Item {
                name: ".."
                path: parent
                isDirectory: true
            } into items;
        }

        for (record: Record in records) {
            if (record.get("isDirectory") == true) {
                insert Item {
                    name: record.getString("name")
                    path: record.getString("path")
                    isDirectory: true
                } into items;
            }
        }

        for (record: Record in records) {
            if (record.get("isDirectory") == false) {
                def ext = record.getString("extension").toLowerCase();
                for (fragment in fragments) {
                    if (fragment.isSupported(ext)) {
                        insert Item {
                            name: record.getString("name")
                            path: record.getString("path")
                            fragment: fragment
                        } into items;
                        break;
                    }
                }
            }
        }

        return items;
    }

    var selectedItem: Item = bind listingListView.selectedItem as Item on replace {
        var selectedFragment = selectedItem.fragment;
        if (selectedFragment == null)
            selectedFragment = emptyFragment;
        for (fragment in fragments) {
            def path = if (fragment == selectedFragment) then selectedItem.path else null;
            fragment.setPath(path);
        }
        viewStack.content = selectedFragment.getView();
        if (selectedItem.isDirectory) {
            folderTextBox.text = selectedItem.path;
        }
    }

}

class Item {
    public-init var name: String;
    public-init var isDirectory: Boolean = false;
    public-init var path: String;
    public-init var fragment: Fragment;
}

function run (): Void {
    var design = Main {};

    javafx.stage.Stage {
        title: "Media View"
        resizable: false
        scene: design.getDesignScene ()
    }
}
