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
 * A RecordSet is an ordered list of Records, much like a database table. It
 * allows random or sequential access to Records.
 *
 * @author Maros Sandor
 */
public abstract class RecordSet {

    var allRecords : Record [];

    public-read var currentIndex : Integer = 0;

    var recordsAccessed = false on replace oldvalue {
        if (oldvalue == false  and  recordsAccessed == true) {
            allRecords = fetchAllRecords();
        }
    }

    function accessWrapper(records : Record []) : Record [] {
        recordsAccessed = true;
        allRecords
    }

    /**
     * @return Record [] all records in this RecordSet
     */
    public bound function all() : Record [] {
        accessWrapper(allRecords)
    }

    /**
     * Move cursor to a new location.
     * @param index new cusor idex
     */
    public function setCurrentIndex(index : Integer) : Void {
        currentIndex = index;
    }

    /**
     * @return Record Record currently under cursor
     */
    public bound function current() : Record {
        all()[currentIndex]
    }

    /**
     * Convenience method that returns a field of the current Record.
     *
     * @param name of the field
     * @return a field in the current record or null if there is no such field
     */
    public bound function currentField(name : String) {
        all()[currentIndex].get(name)
    }

    /**
     * Convenience method that returns a String field of the current Record.
     *
     * @param name of the String field
     * @return a field in the current record as String or null if there is no such field
     */
    public bound function currentString(name : String) : String {
        safeToString (currentField (name))
    }

    bound function safeToString(value : Object) {
        if (value != null) then value.toString() else ""
    }

    /**
     * @return true if the cursor can be moved further by calling next() method, false otherwise
     */
    public bound function hasNext() {
        currentIndex < sizeof all() - 1;
    }

    /**
     * @return true if the cursor can be moved back by calling prev() method, false otherwise
     */
    public bound function hasPrev() {
        currentIndex > 0;
    }

    /**
     * Move the cursor to the next Record.
     */
    public function next() : Void {
        currentIndex++
    }

    /**
     * Move the cursor to the previous Record.
     */
    public function prev()  : Void {
        currentIndex--
    }

    /**
     * Explicitly close the RecordSet and free all resources associated with it. Consult
     * documentation of the DataSource used if this call is neccessary.
     */
    public function close() : Void {}

    override public function toString() : String {
        return "RecordSet(size={sizeof all()}) [\n".concat(recordsToString()).concat("]\n");
    }

    function recordsToString() : String {
        var result = "";
        for (rc in all()) {
            result = result.concat(rc.toString()).concat(",\n");
        }
        result
    }

    /**
     * Directly evaluates the given expression and returns a string value.
    */
    public function eval(expression : String) : String {
        var record = filter(expression).current();
        var fields = record.getFields();
        if (sizeof fields == 1) {
            record.getString(fields[0])
        } else {
            // XML element text
            record.getString(".text")
        }
    }

    /**
     * Filters the data by a simplified XPath expression.
     */
    public function filter(expression : String) : RecordSet {
        Filter.Expr { expr : expression }.filter(this)
    }

    /**
     * Filters the data with the given Filter.
     */
    public function filter(f : Filter) : RecordSet {
        f.filter(this)
    }

    protected abstract function fetchAllRecords() : Record [];

    /*
     * Prints content of this recordset to console for debugging purposes.
     */
    public function dump() : Void {
        dump("ROOT", "")
    }

    function dump(name: String, indent: String) : Void {
        println("{indent}{name} = RECORDSET [");
        var childIndent = indent.concat("  ");
        for (r in all()) {
            dump(r, null, childIndent);
        }
        println("{indent}]");
    }

    function dump(r: Record, name: String, indent: String) : Void {
        print("{indent}");
        if (name != null) {
            print("{name} = ");
        }
        println("RECORD \{");
        var childIndent = indent.concat("  ");
        for (fn in r.getFields()) {
            var fv = r.get(fn);
            dump(fn, fv, childIndent);
        }
        println("{indent}\}");
    }

    function dump(name: String, o: Object, indent: String) : Void {
        if (o instanceof RecordSet) {
            (o as RecordSet).dump(name, indent);
        } else if (o instanceof Record) {
            dump(o as Record, name, indent);
        } else {
            println("{indent}{name}: {o.toString()}");
        }
    }
}
