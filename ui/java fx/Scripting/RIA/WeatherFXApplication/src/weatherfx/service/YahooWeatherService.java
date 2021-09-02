/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2007 Sun Microsystems, Inc.
 */

package weatherfx.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * A connector to Yahoo Weather service. 
 *
 * @author breh
 */
public class YahooWeatherService {

    private static final String address = "http://weather.yahooapis.com/forecastrss?p=";
    private static final String addressC = "http://weather.yahooapis.com/forecastrss?u=c&p=";
    
    private static final String CITY_SF = "94102";    
    
    
    private String city;
    private String region;
    private String country;
            
    
    private int windSpeed;
    private int windDirection;
    
    private int conditionTemp;
    private String conditionString;
    private int conditionCode;
    private String conditionImageURL;
    
    private List<Forecast> forecast = new ArrayList<Forecast>();
    
    private static class Forecast {
        
        private String day;
        private int tempLows;
        private int tempHighs;
        
        private String conditionString;
        private int conditionCode;
        
    }
    
    
    
    public YahooWeatherService()  throws IOException  {
        this(CITY_SF, false);
    }
    
    public YahooWeatherService(String zipCode, boolean celsius) throws IOException {        
        InputStream is = getInputStreamFromURL(createWeatherServiceURL(zipCode, celsius));
        try {
            InputSource inputSource = new InputSource(is);
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(new YWSParser());
            reader.parse(inputSource);
        } catch (SAXException saxe) {
            throw new IOException(saxe.getMessage());
        } finally {
            if (is != null) {
                is.close();
            }
        }
        
    }
    
    
    private static final InputStream getInputStreamFromURL(URL url) throws IOException {
        URLConnection con = url.openConnection();
        con.connect();
        return con.getInputStream();        
    }
    
    private static URL createWeatherServiceURL(String ZIP, boolean celsius) throws IOException {
        String addr = celsius ? addressC : address;
        URL url = new URL(addr+ZIP);
        return url;
    }

    
    public String getCityName() {
        return city;
    }
    
    public String getRegionName() {
        return region;
    }
    
    
    public int getTemp() {
        return conditionTemp;
    }
    
    public int getLowsTemp(int forecastDay) {
        if (forecast.size() > forecastDay) {
            return forecast.get(forecastDay).tempLows;
        } else {
            return 0;
        }
    }
    
    public int getHighsTemp(int forecastDay) {
        if (forecast.size() > forecastDay) {
            return forecast.get(forecastDay).tempHighs;
        } else {
            return 0;
        }        
    }
    
    
    public int getWindSpeed() {
        return windSpeed;
    }
    
    
    public int getWindDirection() {
        return windDirection;
    }
    
    public String getConditionURLString() {
        //System.out.println("URL: "+conditionImageURL);
        return conditionImageURL;
    }
    
    public int getConditionCode() {
        return conditionCode;
    }
    
    
    public int getConditionCode(int forecastDay) {
        if (forecast.size() > forecastDay) {
            return forecast.get(forecastDay).conditionCode;
        } else {
            return 0;
        } 
    }
    

    private static final String LOCATION_EL = "location";
    private static final String LOCATION_CITY = "city";
    private static final String LOCATION_REGION = "region";
    private static final String LOCATION_COUNTRY = "country";
    
    private static final String WIND_EL = "wind";
    private static final String WIND_SPEED = "speed";
    private static final String WIND_DIRECTION = "direction";
    
    private static final String CONDITION_EL = "condition";
    private static final String CONDITION_CODE = "code";
    private static final String CONDITION_TEMP = "temp";
    private static final String CONDITION_DESCRIPTION = "text";
    private static final String CONDITION_IMG_EL = "description";
    
    private static final String FORECAST_EL = "forecast";
    private static final String FOREACAST_DAY = "day";
    private static final String FOREACAST_LOW = "low";
    private static final String FOREACAST_HIGH = "high";
    private static final String FOREACAST_CODE = "code";
    private static final String FOREACAST_DESCRIPTION = "text";
    
    private class YWSParser extends DefaultHandler {

        
        private int getSafeIntValue(Attributes attrs, String attrName) {
            try {
                return Integer.parseInt(attrs.getValue(attrName));
            } catch (Exception e) {
                return 0;
            }
        }
        
        private boolean inDescription = false;
        
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {            
            if (LOCATION_EL.equals(localName)) {
                city = attributes.getValue(LOCATION_CITY);
                region = attributes.getValue(LOCATION_REGION);
                country = attributes.getValue(LOCATION_COUNTRY);                
            }
            if (CONDITION_EL.equals(localName)) {
                conditionTemp = getSafeIntValue(attributes,CONDITION_TEMP);
                conditionCode = getSafeIntValue(attributes,CONDITION_CODE);
            }
            if (CONDITION_IMG_EL.equals(localName)) {
                inDescription = true;
            }
            if (WIND_EL.equals(localName)) {
                windDirection = getSafeIntValue(attributes,WIND_DIRECTION);
                windSpeed = getSafeIntValue(attributes,WIND_SPEED);
            }
            if (FORECAST_EL.equals(localName)) {
                Forecast f = new Forecast();
                f.day = attributes.getValue(FOREACAST_DAY);
                f.tempLows = getSafeIntValue(attributes,FOREACAST_LOW);
                f.tempHighs = getSafeIntValue(attributes,FOREACAST_HIGH);
                f.conditionCode = getSafeIntValue(attributes,FOREACAST_CODE);
                f.conditionString = attributes.getValue(FOREACAST_DESCRIPTION);
                forecast.add(f);
            }
            
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (CONDITION_IMG_EL.equals(localName)) {
                inDescription = false;
            }
        }
        

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (inDescription) {
                // read the array to cdata
                String s = new String(ch, start, length);                
                //System.out.println("S = "+s);
                // get img/src 
                int imgLocation = s.indexOf("img");
                if (imgLocation < 0) return;
                           
                s = s.substring(imgLocation);               
                int srcLocation = s.indexOf("src");
                if (srcLocation < 0) return;
                
                s = s.substring(srcLocation);
                int urlLocationBegin = s.indexOf('"');
                if (urlLocationBegin < 0) return;
                
                s = s.substring(urlLocationBegin+1);                
                int urlLocationEnd = s.indexOf('"');
                if (urlLocationEnd < 0) return;
                
                conditionImageURL = s.substring(0, urlLocationEnd);
            }            
        }
    
        
    
    }
    
    

}
