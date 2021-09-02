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

import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.effect.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.transform.*;
import javafx.scene.image.*;
import javafx.animation.*;

import java.lang.System;

/**
 * Main class for the business logic
 *
 */
public class Weather extends CustomNode {
    
    function rgba(r:Number, g:Number, b:Number, a:Number):Color {
        return Color {red: r/255, green: g/255, blue: b/255, opacity: a/255};
        }     
    
    function matrix(m00:Number,m01:Number,m02:Number,m10:Number,m11:Number,m12:Number) {
        return Affine{ mxx:m00 mxy:m01 myx:m02 myy:m10 tx:m11 ty:m12 };
        }    
        
    var outlineShape:Shape;
    
    function showWeather() {
        initializeWeatherScreen();
        showWeatherTimeline.play();
    }
    
    function initializeWeatherScreen():Void {
        loadTodayConditionImages();
        loadTomorrowConditionImages();
    }
    
    function loadTodayConditionImages() {
        if (todayConditionImages == null) {
            todayConditionImages = [
                ImageView{},
                ImageView{image: Image{url:"{__DIR__}imgs/sun.png"}},
                AnimatedImage.create("{__DIR__}imgs/cloud", "cloud_00", "png", 33),
                AnimatedImage.create("{__DIR__}imgs/rain", "rain_00", "png", 33),
                AnimatedImage.create("{__DIR__}imgs/snow", "snow_00", "png", 33),
                AnimatedImage.create("{__DIR__}imgs/lightning", "lightning_00", "png", 32),
                ImageView{image: Image{url:"{__DIR__}imgs/moon.png"}},
            ]
        }
    }
    
    
    function loadTomorrowConditionImages() {
        if (tomorrowConditionImages == null) {
            tomorrowConditionImages = [
                ImageView{},
                ImageView{image: Image{url:"{__DIR__}imgs/sun.png"}},
                AnimatedImage.create("{__DIR__}imgs/cloud", "cloud_00", "png", 33),
                AnimatedImage.create("{__DIR__}imgs/rain", "rain_00", "png", 33),
                AnimatedImage.create("{__DIR__}imgs/snow", "snow_00", "png", 33),
                AnimatedImage.create("{__DIR__}imgs/lightning", "lightning_00", "png", 32),
                ImageView{image: Image{url:"{__DIR__}imgs/moon.png"}},
            ]
        }
    }
    
    
    var todayConditionImages:Node[];
    var tomorrowConditionImages:Node[];
    
    
    function getConditionImageForCode(conditionImages:Node[],code:Integer):Node {
        return conditionImages[code];
        }

    // business logic
    public var weatherModel: WeatherModel = WeatherModel {} on replace {
        showWeather();
    }

    // animation support 
    var todayNodesOpacity: Number = 1.0;
    var tomorrowNodesOpacity: Number = bind 1.0 - todayNodesOpacity;
    
    var lowsTriangleTransform:TransformHelper = TransformHelper {};
    var highsTriangleTransform:TransformHelper = TransformHelper {
        scale: bind lowsTriangleTransform.scale
        };
    
    var todayHighsTY:Number = 0.0;
    var todayLowsTY:Number = 0.0;
    var tomorrowHighsTY:Number = 0.0;
    var tomorrowLowsTY:Number = 0.0;
   
    
    var toTomorrowTimeline = Timeline {
        keyFrames: [
        KeyFrame {
            time: 0s
            values: [
                todayNodesOpacity => 1.0,
                lowsTriangleTransform.tx => 0.0,
                lowsTriangleTransform.ty => 0.0,
                highsTriangleTransform.tx => 0.0,                
                highsTriangleTransform.ty => 0.0,
                lowsTriangleTransform.scale => 1.0,
                todayLowsTY => 0.0,
                todayHighsTY => 0.0,
                tomorrowLowsTY => 70,
                tomorrowHighsTY => -70
            ]
            }, 
        KeyFrame {
            time: 200ms
            values: lowsTriangleTransform.scale => 1.0,
            },            
        KeyFrame {
            time: 500ms
            values: [
                tomorrowLowsTY => 70,
                tomorrowHighsTY => -70
            ]
            },
        KeyFrame {
            time: 1s
            values: [
                todayNodesOpacity => 0.0 tween Interpolator.EASEBOTH ,                
                lowsTriangleTransform.tx => 105.0 tween Interpolator.EASEBOTH,
                lowsTriangleTransform.ty => -7.0 tween Interpolator.EASEBOTH,
                lowsTriangleTransform.scale => 2.0  tween Interpolator.LINEAR,                
                highsTriangleTransform.tx => 10.0 tween Interpolator.EASEBOTH,
                highsTriangleTransform.ty => 11.0 tween Interpolator.EASEBOTH,
                todayLowsTY => 70.0 tween Interpolator.EASEBOTH,
                todayHighsTY => -70.0 tween Interpolator.EASEBOTH,
                tomorrowLowsTY => 0.0 tween Interpolator.EASEBOTH,
                tomorrowHighsTY => 0.0 tween Interpolator.EASEBOTH,
            ]
            }
        ]
        };

    
    var toTodayTimeline = Timeline {
        keyFrames: [
            KeyFrame {
                time: 0s
                values: [
                    todayNodesOpacity => 0.0,
                    lowsTriangleTransform.tx => 105.0,
                    lowsTriangleTransform.ty => -7.0,
                    highsTriangleTransform.tx => 10.0,
                    highsTriangleTransform.ty => 11.0,                    
                    lowsTriangleTransform.scale => 2.0,
                    todayLowsTY => 70.0,
                    todayHighsTY => -70.0,
                    tomorrowLowsTY => 0.0,
                    tomorrowHighsTY => 0.0,
                ]
                }, 
            KeyFrame {
                time: 500ms
                values: [
                    tomorrowLowsTY => 70 tween Interpolator.EASEBOTH,
                    tomorrowHighsTY => -70 tween Interpolator.EASEBOTH,
                ]
                },
            KeyFrame {
                time: 800ms
                values: lowsTriangleTransform.scale => 1.0 tween Interpolator.LINEAR,
                },
            KeyFrame {
                time: 1s
                values: [
                    todayNodesOpacity => 1.0 tween Interpolator.EASEBOTH,
                    lowsTriangleTransform.tx => 0.0 tween Interpolator.EASEBOTH, 
                    lowsTriangleTransform.ty => 0.0 tween Interpolator.EASEBOTH,
                    highsTriangleTransform.ty => 0.0 tween Interpolator.EASEBOTH,
                    highsTriangleTransform.tx => 0.0 tween Interpolator.EASEBOTH,                    
                    todayLowsTY => 0.0 tween Interpolator.EASEBOTH,
                    todayHighsTY => 0.0 tween Interpolator.EASEBOTH,                    
                ]
                }
            ]
        };    
    
    var weatherScreenOpacity:Number = 0.0;
    var loadingScreenOpacity:Number = bind 1.0 - weatherScreenOpacity;
    
    
    var showWeatherTimeline = Timeline {
        keyFrames: [
        KeyFrame {
            time: 0s
            values: weatherScreenOpacity => 0.0            
            }, 
        KeyFrame {
            time: 500ms
            values: weatherScreenOpacity => 1.0 tween Interpolator.EASEBOTH
            },
        ]
    };
    
    // interactive elements
    function createTodayClickableArea() {
        var today = Rectangle {
            //var: self
            x:0 y:129 width: 90 height:12
            fill: Color.WHITE
            opacity: 0.0
            onMousePressed: function (e) { 
                toTodayTimeline.play();
                }            
            };
        return today;
        }
    
    
    function createTomorrowClickableArea() {
        return Rectangle {
            //var: self
            x:90 y:129 width: 145 height:12 
            fill: Color.WHITE
            opacity: 0.0
            onMousePressed: function (e) {
                toTomorrowTimeline.play();
                }        
            };
        }
    

    function createTextShadow():Effect {
        return DropShadow {offsetX:3 offsetY:3 radius: 18 color: Color.WHITE};
    }
    
    function createSmallStuffBlackShadow():Effect {
        return DropShadow {offsetX:3 offsetY:3 radius: 12 color: Color.GRAY};
    }
        
    function createMainTempShadow():Effect {
        return DropShadow {offsetX:3 offsetY:3 radius: 12};
    }        
    
    // graphics defintion
    override function create():Node {
        Group {
            clip: Rectangle {x:0 y:0 width: 245 height: 84}
            //clip: Clip { shape: outlineShape}    
            content: [
            Group {
                transforms: Transform.translate(10,1)
                opacity: bind loadingScreenOpacity
                content: LoadingScreen {}
                },
            Group { 
                var weekdaysFont = Font.font("Arial",FontWeight.BOLD, FontPosture.REGULAR,8.6149)
                opacity: bind weatherScreenOpacity
                //clip: Rectangle {x:0 y:58 width: 245 height: 83}
                transforms: Transform.translate(10,-57)
                content:[
                Path {
                    elements: [
                    MoveTo {
                        x: 233.84
                        y: 133.471
                        absolute: true
                        },
                    CubicCurveTo {
                        controlX1: 0.0
                        controlY1: 3.907
                        controlX2: -1.516
                        controlY2: 7.076
                        x: -3.383
                        y: 7.076
                        //smooth: false
                        absolute: false
                        },
                    LineTo {                    
                        x: -226.57
                        //x: 3.887
                        //y: 130 // 
                        absolute: false
                        },
                    CubicCurveTo {
                        controlX1: -1.871
                        controlY1: 0.0
                        controlX2: -3.387
                        controlY2: -3.169
                        x: -3.387
                        y: -7.076
                        //smooth: false
                        absolute: false
                        },
                    LineTo {
                        y: -67.76
                        absolute: false
                        },
                    CubicCurveTo {
                        controlX1: 0.0
                        controlY1: -3.908
                        controlX2: 1.516
                        controlY2: -7.076
                        x: 3.387
                        y: -7.076
                        //smooth: false
                        absolute: false
                        },
                    LineTo {
                        x: 226.57
                        absolute: false
                        },
                    CubicCurveTo {
                        controlX1: 1.867
                        controlY1: 0.0
                        controlX2: 3.383
                        controlY2: 3.167
                        x: 3.383
                        y: 7.076
                        //smooth: false
                        absolute: false
                        },
                    ClosePath {},
                    ]
                    fill: weatherGradient
                    stroke: rgba(0x00, 0x00, 0x00, 0xff)
                    },

                

                Text {
                    textOrigin: TextOrigin.BASELINE
                    content: 'TODAY'
                    font: weekdaysFont
                    fill: rgba(0xF2, 0xF2, 0xF2, 0xff)
                    transforms: [
                    matrix(1.0, 0.0, 0.0, 1.0, 6.6519, 137.2471),
                    ]
                    x: 0.0
                    y: 0.0
                    },        
                createTodayClickableArea(),

                Text {
                    textOrigin: TextOrigin.BASELINE
                    content: 'TOMORROW'
                    font: weekdaysFont
                    fill: rgba(0xF2, 0xF2, 0xF2, 0xff)
                    transforms: [
                    matrix(1.0, 0.0, 0.0, 1.0, 96.0806, 137.248),
                    ]
                    x: 0.0
                    y: 0.0
                    },
                createTomorrowClickableArea(), 

                // today condition
                Group { 
                    transforms: Transform.translate(0,58)
                    content: bind getConditionImageForCode(todayConditionImages, weatherModel.todayConditionCode)
                    opacity: bind todayNodesOpacity/2.0
                    },
                // tomorrow condition
                Group { 
                    transforms: Transform.translate(0,58)
                    content: bind getConditionImageForCode(tomorrowConditionImages, weatherModel.tomorrowConditionCode)
                    opacity: bind tomorrowNodesOpacity/2.0
                    },

                

                // wind information
                Group {
                    opacity: bind todayNodesOpacity
                    content: Group {
                        effect: createSmallStuffBlackShadow();
                        content: [
                        Group { // wind direction
                            transforms: Rotate {angle: bind weatherModel.windDirection + 90, pivotX: 192.0, pivotY: 98.0}
                            content: [
                            Path {
                                elements: [
                                MoveTo {
                                    x: 180.494
                                    y: 85.268
                                    absolute: true
                                    },
                                LineTo {
                                    x: 25.994
                                    y: 3.425
                                    absolute: false
                                    },
                                LineTo {
                                    x: -15.73
                                    y: 22.77
                                    absolute: false
                                    },
                                LineTo {
                                    x: 180.494
                                    y: 85.268
                                    absolute: true
                                    },
                                ClosePath {},
                                ]
                                fill: Color.BLACK
                                },
                            Text {            
                                textOrigin: TextOrigin.BASELINE
                                textAlignment: TextAlignment.CENTER
                                content: bind weatherModel.windInformation()
                                font: Font.font("Arial",FontWeight.REGULAR, FontPosture.REGULAR,13.5526)
                                transforms: [
                                //rotate (67,178,90)
                                //matrix(0.3866, 0.9865, -0.9074, 0.4203, 178.8594, 90.377),
                                //matrix(0.3866, 0.9865, -0.9074, 0.4203, 178.8594, 90.377),
                                Translate { x: 178, y : 83 },
                                Rotate { angle: 5.0 }
                                ]
                                }]
                            }]
                        }
                    },

                // outline
                Path {
                    elements: [
                    MoveTo {
                        x: 233.84
                        y: 133.471
                        absolute: true
                        },
                    CubicCurveTo {
                        controlX1: 0.0
                        controlY1: 3.907
                        controlX2: -1.516
                        controlY2: 7.076
                        x: -3.383
                        y: 7.076
                        //smooth: false
                        absolute: false
                        },
                    LineTo {
                        x: -226.57
                        absolute: false
                        },
                    CubicCurveTo {
                        controlX1: -1.871
                        controlY1: 0.0
                        controlX2: -3.387
                        controlY2: -3.169
                        x: -3.387
                        y: -7.076
                        //smooth: false
                        absolute: false
                        },
                    LineTo {
                        y: -67.76
                        absolute: false
                        },
                    CubicCurveTo {
                        controlX1: 0.0
                        controlY1: -3.908
                        controlX2: 1.516
                        controlY2: -7.076
                        x: 3.387
                        y: -7.076
                        //smooth: false
                        absolute: false
                        },
                    LineTo {
                        x: 226.57
                        absolute: false
                        },
                    CubicCurveTo {
                        controlX1: 1.867
                        controlY1: 0.0
                        controlX2: 3.383
                        controlY2: 3.167
                        x: 3.383
                        y: 7.076
                        //smooth: false
                        absolute: false
                        },
                    ClosePath {},
                    ]
                    stroke: rgba(0x00, 0x00, 0x00, 0xff)
                    },

                // temperature
                Group {
                    opacity: bind todayNodesOpacity
                    effect: createMainTempShadow();
                    content: [
                    Circle {
                        centerX: 148.475
                        centerY: 85.083
                        radius: 2.45
                        stroke: rgba(0xED, 0x22, 0x27, 0xff)
                        strokeWidth: 2.0
                        },
                    Text {
                        textOrigin: TextOrigin.BASELINE
                        content: bind "{%02.0f weatherModel.temperature}"
                        font: Font.font("Arial",FontWeight.BOLD, FontPosture.REGULAR,48.6668)
                        fill: rgba(0xED, 0x1E, 0x24, 0xff)
                        transforms: [
                        matrix(1.0, 0.0, 0.0, 1.0, 88.8457, 116.3926),
                        ]
                        x: 0.0
                        y: 0.0
                        },]
                    },

                
                Group {
                    effect: createSmallStuffBlackShadow();
                    content: 
                    Group {
                        transforms: [
                        Translate{ x: bind highsTriangleTransform.tx y: bind highsTriangleTransform.ty},
                        Translate { x:37 y:93},
                        Scale { x: bind highsTriangleTransform.scale y: bind highsTriangleTransform.scale},
                        Translate { x: -37 y: -93}
                        ]
                        content: [
                        // highs triangle

                        Path {
                            elements: [
                            MoveTo {
                                x: 27.552
                                y: 98.673
                                absolute: true
                                },
                            LineTo {
                                x: 8.012
                                y: -13.257
                                absolute: false
                                },
                            LineTo {
                                x: 8.021
                                y: 13.257
                                absolute: false
                                },
                            LineTo {
                                x: 27.552
                                y: 98.673
                                absolute: true
                                },
                            ClosePath {},
                            ]
                            fill: Color.BLACK
                            }
                        ]}},
                // lows triangle
                Group {
                    effect: createSmallStuffBlackShadow();
                    content:        
                    Group {
                        transforms: [
                        Translate {x: bind lowsTriangleTransform.tx y: bind lowsTriangleTransform.ty},
                        Translate {x: 37 y: 109},
                        Scale {x: bind lowsTriangleTransform.scale y: bind lowsTriangleTransform.scale},
                        Translate {x: -37 y: -109}
                        ]        
                        content: 
                        Path {                
                            elements: [
                            MoveTo {
                                x: 43.922
                                y: 101.64
                                absolute: true
                                },
                            LineTo {
                                x: -8.056
                                y: 13.942
                                absolute: false
                                },
                            LineTo {
                                x: -8.045
                                y: -13.942
                                absolute: false
                                },
                            LineTo {
                                x: 43.922
                                y: 101.64
                                absolute: true
                                },
                            ClosePath {},
                            ]
                            fill: Color.BLACK
                            }
                        }},
                // today highs
                Group {
                    transforms: Translate { x:0 y: bind todayHighsTY}
                    content: 
                    Text {
                        effect: createSmallStuffBlackShadow();
                        opacity: bind todayNodesOpacity
                        textOrigin: TextOrigin.BASELINE
                        content: bind "{%02.0f weatherModel.todayHighs}"
                        font: Font.font( "Arial",FontWeight.REGULAR, FontPosture.REGULAR, 18.3347 )
                        x: 46
                        y: 98.84
                        }
                    },
                // today lows
                Group {
                    transforms: Translate { x:0 y: bind todayLowsTY}
                    content:
                    Text {
                        effect: createSmallStuffBlackShadow();
                        opacity: bind todayNodesOpacity
                        textOrigin: TextOrigin.BASELINE
                        content: bind "{%02.0f weatherModel.todayLows}" 
                        font: Font.font("Arial",FontWeight.REGULAR, FontPosture.REGULAR,18.3347)
                        x: 46.2
                        y: 114.88
                        }
                    },
                // tomorrow highs
                Group {
                    transforms: Translate { x:0 y:bind tomorrowHighsTY}
                    content:
                    Text {
                        effect: createSmallStuffBlackShadow();
                        opacity: bind tomorrowNodesOpacity
                        textOrigin: TextOrigin.BASELINE
                        content:  bind "{%02.0f weatherModel.tomorrowHighs}" 
                        font: Font.font("Arial",FontWeight.REGULAR, FontPosture.REGULAR,43)
                        x: 65
                        y: 116
                        }
                    },
                // tomorrow lows
                Group {
                    transforms: Translate { x:0 y: bind tomorrowLowsTY}
                    content:
                    Text {
                        effect: createSmallStuffBlackShadow();
                        opacity: bind tomorrowNodesOpacity
                        textOrigin: TextOrigin.BASELINE
                        content: bind "{%02.0f weatherModel.tomorrowLows}" 
                        font: Font.font("Arial",FontWeight.REGULAR, FontPosture.REGULAR, 43)
                        x: 160
                        y: 116
                        }
                    },
                //]},

                // button lines
                Line {
                    opacity: bind tomorrowNodesOpacity
                    stroke: rgba(0x00, 0x00, 0x00, 0xff)
                    startX: 0
                    endX: 90.369            
                    startY: 128.248
                    endY: 128.248
                    },

                Line {
                    opacity: bind todayNodesOpacity
                    stroke: rgba(0x00, 0x00, 0x00, 0xff)
                    startX: 90.369
                    endX: 233.345
                    startY: 128.248
                    endY: 128.248
                    },

                Line {
                    stroke: rgba(0x00, 0x00, 0x00, 0xff)
                    startX: 90.369
                    endX: 90.369
                    startY: 140.546
                    endY: 128.248
                    },

                // city name
                Group {
                    effect: createTextShadow();
                    content: [
                    Text {
                        textOrigin: TextOrigin.BASELINE
                        content: bind weatherModel.cityName
                        font: Font.font("Arial",FontWeight.BOLD, FontPosture.REGULAR,15.0)
                        fill: rgba(0xF2, 0xF2, 0xF2, 0xff)
                        transforms: [
                        matrix(1.0, 0.0, 0.0, 1.0, 6.145, 74.0625),
                        ]
                        x: 0.0
                        y: 0.0
                        },
                    ]},
                ]}
            ]};         
        }
    
    var weatherGradient: LinearGradient =  LinearGradient {
        startX: 0.50
        startY:0.94 
        endX: 0.50 
        endY: 0.00 
        /*stops: [
        Stop {offset: 0.00 color: Color.rgb(0x10,0x72,0xba,1.0)},
        Stop {offset: 0.51 color: Color.rgb(0xaf,0xdb,0xe7,1.0)},
        Stop {offset: 0.92 color: Color.rgb(0x29,0x83,0xc1,1.0)}
        ]*/

        stops: [
            Stop {
                offset: 0.0
                color: rgba(0x10, 0x72, 0xBA, 0xff)
            },
            Stop {
                offset: 0.0835
                color: rgba(0x4F, 0x8C, 0xC5, 0xff)
            },
            Stop {
                offset: 0.1685
                color: rgba(0x70, 0xA5, 0xD1, 0xff)
            },
            Stop {
                offset: 0.254
                color: rgba(0x8B, 0xBB, 0xDA, 0xff)
            },
            Stop {
                offset: 0.3389
                color: rgba(0x9E, 0xCC, 0xE1, 0xff)
            },
            Stop {
                offset: 0.423
                color: rgba(0xAA, 0xD6, 0xE5, 0xff)
            },
            Stop {
                offset: 0.5055
                color: rgba(0xAF, 0xDB, 0xE7, 0xff)
            },
            Stop {
                offset: 0.5692
                color: rgba(0xA8, 0xD4, 0xE4, 0xff)
            },
            Stop {
                offset: 0.6617
                color: rgba(0x94, 0xC3, 0xDD, 0xff)
            },
            Stop {
                offset: 0.772
                color: rgba(0x76, 0xAA, 0xD3, 0xff)
            },
            Stop {
                offset: 0.8948
                color: rgba(0x4F, 0x8B, 0xC5, 0xff)
            },
            Stop {
                offset: 1.0
                color: rgba(0x10, 0x72, 0xBA, 0xff)
            },
        ]
    }
        
    
}
/*
var weatherModel = WeatherModel {
    cityName: "San Francisco"
    temperature: 45
    todayHighs: 94
    todayLows: 90
    windSpeed: 46
    windDirection: 259
    todayConditionCode: 1
    tomorrowConditionCode: 6
};

var weather = Weather{
    weatherModel: weatherModel
};
*/