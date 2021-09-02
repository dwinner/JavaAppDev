/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER 
 * Copyright  2008, 2010 Oracle and/or its affiliates.  All rights reserved. 
 * Use is subject to license terms.
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
 *   * Neither the name of Oracle Corporation nor the names of its contributors 
 *     may be used to endorse or promote products derived from this software 
 *     without specific prior written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */

package weatherfx;

import weatherfx.service.YahooWeatherService;
import java.lang.System;

/**
 *  Holds model data for the weather widget
 * 
 * @author breh
 */

public class WeatherModel {
    
    public var UNKNOWN:Integer = 0;
    public var CLEAR:Integer = 1;
    public var CLOUDS:Integer = 2;
    public var RAIN:Integer = 3;
    public var SNOW:Integer = 4;
    public var THUNDER:Integer = 5;
    public var MOON:Integer = 6;

    public var cityName:String;
    public var temperature:Number;
    public var windSpeed:Number;
    public var windDirection:Number;

    public var todayLows:Number;
    public var todayHighs:Number;
    public var todayConditionCode: Integer;

    public var tomorrowLows:Number;
    public var tomorrowHighs:Number;
    public var tomorrowConditionCode: Integer;

    function translateConditionCode(code:Integer):Integer {
        return 
            if ((code >= 0) and ( code < 5))  then
                THUNDER
            else if ((code >= 5) and (code < 9)) then
                SNOW
            else if ((code >= 9) and (code < 13)) then
                RAIN
            else if ((code >= 13) and (code < 19)) then
                SNOW
            else if ((code >= 19) and (code < 26)) then
                CLEAR
            else if ((code >= 26) and (code < 31)) then
                CLOUDS
            else if (code == 31) then
                MOON
            else if (code == 32) then
                CLEAR
            else if (code == 33) then
                MOON
            else if (code == 34) then
                CLEAR
            else if (code == 35) then
                RAIN
            else if (code == 36) then
                CLEAR
            else if ((code >= 37) and (code < 40)) then
                THUNDER
            else if ((code >= 41) and (code < 44)) then
                SNOW
            else if (code == 44) then 
                CLOUDS
            else if (code == 45) then 
                THUNDER
            else if (code == 46) then 
                SNOW
            else if (code == 47) then 
                THUNDER
            else UNKNOWN
        ;
    }
    
    public function loadFromYWS(yws:YahooWeatherService) {
        cityName = yws.getCityName();
        temperature = yws.getTemp();

        windSpeed = yws.getWindSpeed();
        windDirection = yws.getWindDirection(); 
        todayConditionCode = translateConditionCode(yws.getConditionCode());
        todayLows = yws.getLowsTemp(0);
        todayHighs = yws.getHighsTemp(0);

        tomorrowConditionCode = translateConditionCode(yws.getConditionCode(1));    
        tomorrowLows = yws.getLowsTemp(1);
        tomorrowHighs = yws.getHighsTemp(1);
    }
    
    
    public function windInformation(): String {
        return "{translateDirectionToString(windDirection)} {%02.0f windSpeed}";
    }
    
    public function translateDirectionToString(dir:Number):String {
        var windDirs = ["N", "NE", "E", "SE", "S", "SW", "W", "NW", "N"];
        var tmpdir = dir mod 360;
        var i = ((tmpdir + 22.5 ) / 45).intValue();
        return windDirs[i];
    }
    
}



