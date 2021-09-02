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

import org.netbeans.javafx.datasrc.MemoryRecordSet;

/**
 * A generic filter that filters given RecordSet producing a new RecordSet.
 
 * @author Maros Sandor
 */
public abstract class Filter {

    /**
     * A generic filter that filters given RecordSet producing a new RecordSet.
     */
    public abstract function filter(source : RecordSet) : RecordSet;
}

/**
 * Simplified XPath query engine.
 */
package class Expr extends Filter {

    public-init var expr : String;
    var components : String [];

    public override function filter(source : RecordSet) : RecordSet {
        components = split(expr, "/");
        var filtered = source;
        if (components[0] == "") {
            // query from the root; just check that the name of the root element matches the query
            if (source.all()[0].getString(".name") != components[1]) {
                return MemoryRecordSet {
                    records: [ ]
                }
            } else {
                delete components[0..1];
            }
        }

        var xmlSyntax = source.all()[0].getString(".name") != null;

        for (c in components) {
            var f : Filter;
            var component;

            // for non-XML Records treat element query as attribute query (no .elements field in Records)
            if (xmlSyntax or c.startsWith("@")) {
                component = c;
            } else {
                component = "@{c}";
            }

            var predicates = findPredicates(component);
            if (sizeof predicates > 0) {
                component = component.substring(0, component.indexOf('[')).trim();
            }

            if (component.startsWith("@")) {
                f = Field { name : component.substring(1) };
            } else if (component == "*") {
                f = AllElements;
            } else {
                f = Element { name : component };
            }
            filtered = f.filter(filtered);

            for (predicate in predicates) {
                f = filterForExpression(predicate);
                filtered = f.filter(filtered);
            }
        }
        filtered
    }
}

abstract class XmlElementFilter extends Filter {

    /**
     * Filters by (not) accepting child XML elements, calling accept() for each Record in the .elements RecordSet.
     */
    override public function filter(source : RecordSet) : RecordSet {
        var res : Record [];
        for (record in source.all()) {
            var children = record.getRecordSet(".elements");
            insert children.all()[child | accept(child)] into res;
        }
        MemoryRecordSet {
            records: res
        }
    }

    public abstract function accept(record : Record) : Boolean;
}

class Element extends XmlElementFilter {

    public-init var name : String;

    override public function accept(record : Record) : Boolean {
        name == record.getString(".name")
    }
}

def AllElements = XmlElementFilter {
    override public function accept(record : Record) : Boolean {
        true
    }
}

def Nothing = Filter {
    override public function filter(source : RecordSet) : RecordSet {
        MemoryRecordSet { }
    }
}

class Field extends Filter {

    public-init var name : String;

    public override function filter(source : RecordSet) : RecordSet {
        var res : Record [] = [];
        for (record in source.all()) {
            var field = record.get(name);
            if (field instanceof Record) {
                insert field as Record into res;
            } else if (field instanceof RecordSet) {
                insert (field as RecordSet).all() into res;
            } else if (field != null) {
                var map = new java.util.HashMap();
                map.put(name, field);
                var r = MapRecord {
                    fields: map
                }
                insert r into res;
            }
        }
        MemoryRecordSet {
            records : res
        }
    }
}

class IndexPredicate extends Filter {

    // 0-based index
    public-init var index : Integer;

    public override function filter(source : RecordSet) : RecordSet {
        MemoryRecordSet {
            records: [ source.all()[index] ]
        }
    }
}

function filterForExpression(expr : String) : Filter {
    try {
        var i = java.lang.Integer.parseInt(expr);
        return IndexPredicate { index : i }
    } catch (e : java.lang.NumberFormatException) {
        // not an index
    }
    Nothing
}


function findPredicates(input : String) : String[] {
    var predicates : String [] = [];

    var idx : Integer;
    var idx2 : Integer;
    while (true) {
        idx = input.indexOf('[', idx2);
        if (idx == -1) break;
        idx2 = input.indexOf(']', idx);
        var predicate = input.substring(idx + 1, idx2);
        insert predicate into predicates;
    }
    predicates
}

function split(string : String, splitter : String) : String[] {
    var rest = string;
    var parts : String[] = [];
    while (true) {
        def index = rest.indexOf(splitter);
        if (index < 0) {
            break;
        }
        insert rest.substring (0, index) into parts;
        rest = rest.substring(index + 1);
    }
    insert rest into parts;
    return parts;
}
