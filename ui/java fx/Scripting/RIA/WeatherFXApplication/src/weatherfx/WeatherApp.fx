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

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.transform.Transform;

import javafx.stage.Stage;

import java.lang.*;
import weatherfx.service.YahooWeatherService;

/**
 * Main class of the app
 * 
 * @author breh
 */

var weather1 = Weather{};
var weather2 = Weather{};
var weather3 = Weather{};

Stage {
    scene: Scene {
        fill: Color.BLACK
        content:[
            Group {
                transforms: Transform.translate(0,5);
                content: weather1
            },
            Group {
                transforms: Transform.translate(0,95);
                content: weather2
            },
            Group {
                transforms: Transform.translate(0,185);
                content: weather3
            }
        ]
    }
    title: "WeatherFX"
    resizable: false
    width: 260
    height: 310
    visible: true
};



function showWeather(weatherCode:String, weather:Weather):Void {
    // this has to use Threads as FX currently does not give us any reasonable options
     var ywsRunnable = Runnable {
            public override function run() {
                var yws = new YahooWeatherService(weatherCode, false);                
                var modelRunnable = Runnable {
                    public override function run() {
                        System.out.println("Loaded weather {weatherCode} ");
                        var wm = WeatherModel{};
                        wm.loadFromYWS(yws);                        
                        weather.weatherModel = wm;
                        
                    }
                }
                javax.swing.SwingUtilities.invokeLater(modelRunnable);
            }
     };        
    (new Thread(ywsRunnable)).start();
    
}

// show the weather information
showWeather("EZXX0012",weather1);
showWeather("FRXX0076",weather2);
showWeather("94303",weather3);

