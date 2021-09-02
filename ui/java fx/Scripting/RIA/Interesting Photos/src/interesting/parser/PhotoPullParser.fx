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
package interesting.parser;

import java.io.InputStream;
import javafx.data.pull.Event;
import javafx.data.pull.PullParser;
import javafx.data.xml.QName;
import interesting.model.Photo;

public class PhotoPullParser {
    public var errorMessage = "";                       // Error Message (if any)
    var photos:Photo[];                                 // Information about all interesting photos
    public var onDone:function(data:Photo[]) = null;    // Completion callback that also delivers parsed photo metadata

    public function parse(input:InputStream) {
        // Parse the input data (Photo Metadata) and construct Photo instance
        def parser = PullParser { 
            input: input 
            onEvent: function(event: Event) {
                if (event.type == PullParser.START_ELEMENT) {
                    if(event.qname.name == "photo" and event.level == 2) {
                        def photo = Photo {
                            id:     event.getAttributeValue(QName{name:"id"}) as String;
                            owner:  event.getAttributeValue(QName{name:"owner"}) as String;
                            secret: event.getAttributeValue(QName{name:"secret"}) as String;
                            server: event.getAttributeValue(QName{name:"server"}) as String;
                            farm:   event.getAttributeValue(QName{name:"farm"}) as String;
                            title:  event.getAttributeValue(QName{name:"title"}) as String;
                        } 
                        insert photo into photos;
                    }
                    else if(event.qname.name == "err" and event.level == 1) {
                        errorMessage = event.getAttributeValue(QName{name:"msg"});
                    }
               }
               else if (event.type == PullParser.END_DOCUMENT) {
                   if (onDone != null) { onDone(photos); }
               }
           }
       }
       parser.parse();
    }
}
