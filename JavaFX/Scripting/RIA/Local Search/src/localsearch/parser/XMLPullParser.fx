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


package localsearch.parser;

import java.io.InputStream;
import javafx.data.pull.Event;
import javafx.data.pull.PullParser;
import javafx.data.xml.QName;

import localsearch.model.Category;
import localsearch.model.Rating;
import localsearch.model.Restaurant;
import localsearch.Main;

/**
 * Sample FX Script Application for Yahoo!'s Local Search Service
 * This version uses the PullParser API to parse results in JSON.
 */
public function processResults(is:InputStream):Void {
    def parser = PullParser {
        documentType: PullParser.XML;
        input: is;
        onEvent: parseEventCallback
    };
    parser.parse();
    is.close();
}

def parseEventCallback = function(event: Event) {
    if (event.type == PullParser.START_ELEMENT) {
        processStartEvent(event)
    } else if (event.type == PullParser.END_ELEMENT) {
        processEndEvent(event)
    }
}

// temporary variables needed during processing
var result: Restaurant;
var category: Category;

function processStartEvent(event: Event) {
    if (event.qname.name == "Result" and event.level == 1) {
        result = Restaurant {
            id: event.getAttributeValue(QName{name:"id"}) as String;
        }
    } else if (event.qname.name == "Rating" and event.level == 2) {
        result.rating = Rating {}
    } else if (event.qname.name == "Category" and event.level == 3) {
        category = Category {}
        category.id = event.getAttributeValue(QName{name:"id"}) as String;
    }
}

function processEndEvent(event: Event) {
    if (event.qname.name == "Result" and event.level == 1) {
        insert result into Main.restaurants;
        if (Main.restaurants.size() == 1) {
            Main.showRestaurantDetails(0, true);
        }
    } else if (event.qname.name == "Title" and event.level == 2) {
        result.title = event.text
    } else if (event.qname.name == "Address" and event.level == 2) {
        result.address = event.text
    } else if (event.qname.name == "City" and event.level == 2) {
        result.city = event.text
    } else if (event.qname.name == "State" and event.level == 2) {
        result.state = event.text
    } else if (event.qname.name == "Phone" and event.level == 2) {
        result.phone = event.text
    } else if (event.qname.name == "Latitude" and event.level == 2) {
        result.latitude = event.text
    } else if (event.qname.name == "Longitude" and event.level == 2) {
        result.longitude = event.text
    } else if (event.qname.name == "AverageRating" and event.level == 3) {
        result.rating.averageRating = event.text
    } else if (event.qname.name == "TotalRatings" and event.level == 3) {
        result.rating.totalRatings = event.text
    } else if (event.qname.name == "TotalReviews" and event.level == 3) {
        result.rating.totalReviews = event.text
    } else if (event.qname.name == "LastReviewDate" and event.level == 3) {
        result.rating.lastReviewDate = event.text
    } else if (event.qname.name == "LastReviewIntro" and event.level == 3) {
        result.rating.lastReviewIntro = event.text
    } else if (event.qname.name == "Distance" and event.level == 2) {
        result.distance = event.text
    } else if (event.qname.name == "Url" and event.level == 2) {
        result.url = event.text
    } else if (event.qname.name == "ClickUrl" and event.level == 2) {
        result.clickUrl = event.text
    } else if (event.qname.name == "MapUrl" and event.level == 2) {
        result.mapUrl = event.text
    } else if (event.qname.name == "BusinessUrl" and event.level == 2) {
        result.businessUrl = event.text
    } else if (event.qname.name == "BusinessClickUrl" and event.level == 2) {
        result.businessClickUrl = event.text
    } else if (event.qname.name == "Category" and event.level == 3) {
        category.value = event.text;
        insert category into result.categories;
    }
}
