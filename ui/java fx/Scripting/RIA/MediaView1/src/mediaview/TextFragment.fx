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

public class TextFragment extends Fragment {

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:main
    def __layoutInfo_pathLabel: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hfill: true
        hgrow: javafx.scene.layout.Priority.ALWAYS
    }
    public-read def pathLabel: javafx.scene.control.Label = javafx.scene.control.Label {
        layoutInfo: __layoutInfo_pathLabel
        text: "Label"
        hpos: javafx.geometry.HPos.CENTER
    }
    
    public-read def textLabel: javafx.scene.control.Label = javafx.scene.control.Label {
        textWrap: true
    }
    
    def __layoutInfo_scrollView: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        hfill: true
        vfill: true
        hgrow: javafx.scene.layout.Priority.ALWAYS
        vgrow: javafx.scene.layout.Priority.ALWAYS
    }
    public-read def scrollView: javafx.scene.control.ScrollView = javafx.scene.control.ScrollView {
        layoutInfo: __layoutInfo_scrollView
        node: textLabel
    }
    
    def __layoutInfo_vbox: javafx.scene.layout.LayoutInfo = javafx.scene.layout.LayoutInfo {
        width: 362.0
        height: 281.0
    }
    public-read def vbox: javafx.scene.layout.VBox = javafx.scene.layout.VBox {
        layoutInfo: __layoutInfo_vbox
        content: [ pathLabel, scrollView, ]
        spacing: 6.0
    }
    
    public-read def currentState: org.netbeans.javafx.design.DesignState = org.netbeans.javafx.design.DesignState {
    }
    
    public function getDesignRootNodes (): javafx.scene.Node[] {
        [ vbox, ]
    }
    // </editor-fold>//GEN-END:main

    override public function getView () : javafx.scene.Node {
        vbox
    }

    override public function isSupported (fileExtension: String) : Boolean {
        fileExtension == "txt"  or  fileExtension == "html"  or  fileExtension == "css"  or  fileExtension == "javafx"  or  fileExtension == "java"
    }

    override public function setPath (path: String) : Void {
        pathLabel.text = path;

        if (path == null) {
            textLabel.text = null;
        } else {
            def file = new java.io.File (path);
            if (file.length() > 1024*1024) {
                textLabel.text = "< the file is too long >";
            } else {
                def sb = new java.lang.StringBuffer ();
                def reader = new java.io.BufferedReader (new java.io.FileReader (path));
                try {
                    while (true) {
                        def line = reader.readLine();
                        if (line == null  and  not "".equals (line))
                            break;
                        sb.append (line).append ('\n');
                    }
                } catch (e: java.io.IOException) {
                    reader.close();
                }
                textLabel.text = sb.toString();
            }
        }
    }

}
