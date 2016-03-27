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

import java.sql.DriverManager;
import java.util.HashMap;

/**
 * Datasource connecting to a database via JDBC.
 *
 * @author Maros Sandor
 */
public class DbDataSource extends DataSource {

    /**
     * Standard JDBC connection string (URL).
     */
    public var connectionString : String on replace {
        autorefresh()
    }

    /**
     * SQL query to use when fetching data. The query should comply with SQL syntax of calling database.
     */
    public var query : String on replace {
        autorefresh()
    }

    /**
     * Username for authentication purposes or null if authentication is not used or required.
     */
    public var user : String on replace {
        autorefresh()
    }

    /**
     * Password for authentication purposes or null if authentication is not used or required.
     */
    public var password : String on replace {
        autorefresh()
    }

    /**
     * Additional driver parameters or null. This property is currently not used by this data source.
     */
    public var driverParams : String on replace {
        autorefresh()
    }

    /**
     * Lazy loading is useful for large tables with lots of rows when fetching data at once would cause
     * performance issues. It is recommended to use the cursor functionality (next, previous, current) of RecordSet
     * in conjunction with lazy loading.
     */
    public-init var lazyLoading : Boolean = false;

    // ------------------------------------------------------------------

    var resultSet : java.sql.ResultSet;

    var connection : java.sql.Connection;

    override public function fetchData() : Void {
        if (lazyLoading) {
            executeQuery();
            dataFetched(DbLazyRecordSet {
                resultSet: resultSet
                connection: connection
            })
        }

        var result : Record [] = [];
        executeQuery();

        var data = resultSet.getMetaData();
        var cc = data.getColumnCount();

        while (resultSet.next()) {
            var row : HashMap = HashMap {};
            for (i in [1..cc]) {
                var cval = resultSet.getObject(i);
                var cname = data.getColumnName(i);
                row.put(cname, cval);
            }
            var record = MapRecord {
                fields : row
            };
            insert record into result;
        }

        disconnect();

        dataFetched(MemoryRecordSet {
            records: result
        })
    }

    public function execute(sql : String) : Void {
        connect();

        var statement = connection.prepareStatement(sql, java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY);
        statement.execute();
    }

    public function remove(table : String, whereClause : String) : Integer {
        connect();

        var sql = "DELETE FROM {table} WHERE {whereClause}";

        var statement = connection.prepareStatement(sql, java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY);
        statement.executeUpdate();
    }

    public function update(table : String, fields : javafx.data.Pair [], whereClause : String) : Integer {
        connect();

        var flds = "";
        for (field in fields) {
            flds = flds.concat("{field.name}={field.value}, ");
        }
        flds = flds.substring(0, flds.length() - 2);

        var sql = "UPDATE {table} SET {flds} WHERE {whereClause}";

        var statement = connection.prepareStatement(sql, java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY);
        statement.executeUpdate();
    }

    public function create(table : String, fields : javafx.data.Pair []) : Integer {
        connect();

        var columns = "";
        var values = "";
        for (field in fields) {
            columns = columns.concat("{field.name}, ");
            values = values.concat("{field.value}, ");
        }
        columns = columns.substring(0, columns.length() - 2);
        values = values.substring(0, values.length() - 2);

        var sql = "INSERT INTO {table} ({columns}) VALUES ({values})";

        var statement = connection.prepareStatement(sql, java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY);
        statement.executeUpdate();
    }

    function connect(){
        var properties : java.util.Properties = java.util.Properties {};

        properties.put("user", user);
        properties.put("password", password);

        connection = DriverManager.getConnection(connectionString, properties);
    }

    function executeQuery() : Void {
        connect();
        var statement = connection.prepareStatement(query, java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery();
    }

    function disconnect() : Void {
        resultSet.close();
        connection.close();

    }
}
