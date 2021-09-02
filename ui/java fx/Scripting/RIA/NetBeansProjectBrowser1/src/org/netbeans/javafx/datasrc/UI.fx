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
 
package org.netbeans.javafx.datasrc;

/**
 * @author Maros Sandor
 */

/**
 * All tree items created by createTreeItem() are instances of this class.
 */
public class DataTreeItem extends com.javafx.preview.control.TreeItem {

    /**
     * Suggested display name of this tree node, you can use this value during tree cell rendering.
     */
    public-read var displayName : String
}

/**
 * Creates a TreeItem instance suitable as a root for a TreeView. Returned TreeItems are all instances
 * of DataTreeItem which tries to guess the correct display name for tree nodes.
 *
 * @param a record set
 * @return an instance of DataTreeItem representing the root record set
 */
public bound function createTreeItem(rs : RecordSet, rootName : String) : com.javafx.preview.control.TreeItemBase {
    cti(rs, rootName)
}

function cti(rs : RecordSet, name : String) : DataTreeItem {
    DataTreeItem {
        displayName: name
        data : rs
        createChildren: ccrs
        isLeaf: never
    }
}

function cti(r : Record, name : String) : DataTreeItem {
    var rname = name;
    if (rname == null) {
        rname = r.getString("name");
        if (rname == null) {
            rname = r.getString(".name");
        }
    }
    DataTreeItem {
        displayName: rname
        data : r
        createChildren: ccr
        isLeaf: never
    }
}

function cti(o : Object, name : String) : DataTreeItem {
    DataTreeItem {
        displayName: "{name} : {o.toString()}"
        data : o
        isLeaf: always
    }
}

function ccrs(item : com.javafx.preview.control.TreeItemBase) : com.javafx.preview.control.TreeItemBase[] {
    var rs = item.data as RecordSet;
    var res : DataTreeItem[] = [];
    for (r in rs.all()) {
        var ti = cti(r, null);
        insert ti into res;
    }
    res
}

function ccr(item : com.javafx.preview.control.TreeItemBase) : com.javafx.preview.control.TreeItemBase[] {
    var r = item.data as Record;
    var res : DataTreeItem [] = [];
    for (fname in r.getFields()) {
        var f = r.get(fname);
        if (f instanceof RecordSet) {
            insert cti((f as RecordSet), fname) into res;
        } else if (f instanceof Record) {
            insert cti(f as Record, fname) into res;
        } else {
            insert cti(f, fname) into res;
        }
    }
    res
}

function never(item : com.javafx.preview.control.TreeItemBase) : Boolean {
    false
}

function always(item : com.javafx.preview.control.TreeItemBase) : Boolean {
    true
}
