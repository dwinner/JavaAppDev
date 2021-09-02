/* 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 * Copyright 2009 Sun Microsystems, Inc. All rights reserved. Use is subject to license terms. 
 * 
 * This file is available and licensed under the following license:
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 *   * Redistributions of source code must retain the above copyright notice, 
 *     this list of conditions and the following disclaimer.
 *
 *   * Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 *   * Neither the name of Sun Microsystems nor the names of its contributors 
 *     may be used to endorse or promote products derived from this software 
 *     without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package localsearch.model;

// Hold data related to Restaurant
public class Restaurant {
    
    public var id:String;
    public var title:String;
    public var address:String;
    public var city:String;
    public var state:String;
    public var phone:String;
    public var latitude:String;
    public var longitude:String;
    public var rating:Rating;
    public var distance:String;
    public var url:String;
    public var clickUrl:String;
    public var mapUrl:String;
    public var businessUrl:String;
    public var businessClickUrl:String;
    public var categories:Category[];
    
    override function toString() {
        var sb = java.lang.StringBuffer {};
        sb.append("[");
        var size = categories.size();
        var i = 0;
        while (i < size) {
            sb.append(categories[i++]);
            if (i < size) {
                sb.append(", ")
            }
        }
        sb.append("]");
        return "\{id: \"{id}\", title: \"{title}\", "
        "address: \"{address}\", city: \"{city}\", "
        "state: \"{state}\", phone: \"{phone}\", "
        "latitude: \"{latitude}\", longitude: \"{longitude}\", "
        "rating: {rating}, distance: \"{distance}\", categories: {sb}\}"
    }
}
