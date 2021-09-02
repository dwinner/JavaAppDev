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
 * Abstract data source defining common interface to all data providers.
 *
 * @author Maros Sandor
 */
public abstract class DataSource {

    /**
     * Expression -> DataSource
     */
    var data : java.util.Map = new java.util.HashMap(1);

    var completeData : RecordSet;

    /**
     * Set autoRefresh to false if you want getRecordSet() calls to block, ie. if you need data immediately.
     * Set to true to let the DataSource automatically fetch data in the background on application startup and
     * with every change to any datasource property.
     */
    public var autoRefresh = true on replace {
        autorefresh()
    }

    /**
     * An indicator set to true whenever the DataSource is in the process of fetching data.
     */
    public-read protected var fetchingData = false;

    /**
     * Contains an Exception after an unsuccessful data fetch, null otherwise.
     */
    public-read protected var fetchError : java.lang.Exception;

    /**
     * A derived class overrides this to fetch data. When it succeeds, it calls the dataFetched() callback function.
     * When it fails, it calls the dataFetchError callback. Derived classes are free to fetch data asynchronously.
     */
    protected abstract function fetchData() : Void;

    function accessWrapper(records : RecordSet) : RecordSet {
        if (completeData == null and not fetchingData) {
            refreshSync();
        }
        completeData
    }

    function accessWrapper(records : RecordSet, expression : String) : DataSource {
        var result = data.get(expression) as DataSource;
        if (result != null) {
            return result;
        }

        if (completeData == null and not fetchingData) {
            refreshSync();
        }

        result = data.get(expression) as DataSource;
        if (result != null) {
            return result;
        }

        result = filterSources(expression);
        data.put(expression, result);
        result
    }

    function filterSources(expression : String) : DataSource {
        MemoryDataSource {
            completeData : completeData.filter(expression);
            fetchingData: bind DataSource.this.fetchingData
            fetchError: bind DataSource.this.fetchError
        }
    }

    /**
     * Callback function that data source classes call when they fail to fetch data in response to fetchData() call.
     * @param exception an exception that defined the failure
     */
    protected function dataFetchError(exception : java.lang.Exception) {
        fetchError = exception;
        fetchingData = false;
        data = new java.util.HashMap(0);
        completeData = MemoryRecordSet { records: [] };
    }

    /**
     * Callback function that data source classes call when they successfuly fetch data in response to fetchData() call.
     * @param newData a RecordSet containing fetched data
     */
    protected function dataFetched(newData : RecordSet) {
        fetchingData = false;
        data = new java.util.HashMap(1);
        completeData = newData;
    }

    /**
     * Retrieves data from this datasource. The exact format of data depends on the concrete DataSource implementation.
     * @return a RecordSet object holding data or null if data is still fetching or if an error occured while fetching data
     */
    public bound function getRecordSet() : RecordSet {
        accessWrapper(completeData)
    }

    /**
     * Filters data from this datasource according to the given expression. 
     * @param expression filter expression. Currently, only a simple "field" expressions are supported.
     * For example, from https://netbeans.org/api/projects.json you can get "projects", "href" and other first-level child data sources.
     * @return a DataSource that holds a subset of data of this datasource, filtered by the given expression
     */
    public bound function getDataSource(expression : String) : DataSource {
        accessWrapper(completeData, expression)
    }

    /**
     * Forces this data source to re-fetch all data.
     */
    public function refresh() : Void {
        if (not fetchingData) {
            fetchError = null;
            fetchingData = true;
            FX.deferAction(function() : Void {
                fetchData();
            });
        }
    }

    function refreshSync() : Void {
        if (not fetchingData) {
            fetchError = null;
            fetchingData = true;
            fetchData();
        }
    }

    /**
     * Derived class call this when their specific properties change. This class insures that refresh() is called
     * when autoRefresh property is set to true.
     */
    protected function autorefresh() : Void {
        if (autoRefresh) {
            refresh()
        }
    }
}
