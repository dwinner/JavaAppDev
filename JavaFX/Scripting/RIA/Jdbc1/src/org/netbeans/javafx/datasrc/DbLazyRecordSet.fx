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

import java.util.HashMap;

/**
 * Record set used by JDBC Data Source for lazy loading. It fetches records from the database
 * on demand as the cursor moves over the record set.
 * 
 * @author Maros Sandor
 */
package class DbLazyRecordSet extends RecordSet {

    public-init var recordDisplayName : function(record: Record) : String;

    public-init var resultSet : java.sql.ResultSet;

    public-init var connection : java.sql.Connection;

    var metaData : java.sql.ResultSetMetaData;

    var columnCount : Integer;

    var currentRecord : Record;

    var isFirst : Boolean;

    var isLast : Boolean;

    init {
        metaData = resultSet.getMetaData();
        columnCount = metaData.getColumnCount();
        if (resultSet.next()) {
            currentRecord = createRecord();
        }
        updateNavigation();
    }

    // at any time:
    // currentRecord holds the current record
    // resultset points to a record following currentrecord

    override public bound function current() : Record {
        currentRecord
    }

    override public bound function hasNext() {
        not isLast
    }

    override public bound function hasPrev() {
        not isFirst
    }

    function createRecord() : Record {
        var row : HashMap = HashMap {};
        for (i in [1..columnCount]) {
            var cval = resultSet.getObject(i);
            var cname = metaData.getColumnName(i);
            row.put(cname, cval);
        }
        MapRecord {
            fields : row
        }
    }

    function updateNavigation() : Void {
        isFirst = resultSet.isFirst();
        isLast = resultSet.isLast();
    }

    override public function next() : Void {
        if (resultSet.next()) {
            currentRecord = createRecord()
        } else {
            currentRecord = null
        }
        updateNavigation();
    }

    override public function prev()  : Void {
        if (resultSet.previous()) {
            currentRecord = createRecord()
        } else {
            currentRecord = null
        }
        updateNavigation();
    }

    override public function close() : Void {
        resultSet.close();
        connection.close();
    }

    override protected function fetchAllRecords() : Record [] {
        null
    }
}
