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

package particles;

import javafx.scene.Node;
import javafx.scene.CustomNode;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

import java.lang.Math;
import java.util.Random;
import java.lang.System;

/**
 * @author Michal Skvor
 */

var flock : Flock = Flock{};

Stage {
    scene : Scene {
        fill : Color.LIGHTGREY
        content : bind flock
    };

    visible : true
    title : "Flocks"
    width : 200
    height : 232
}

flock.start();

class Flock extends CustomNode {

    var _N : Integer = 50 on replace {
        for( i in [0.._N] ) {
            insert Boid {
                loc : Vector3D{ x : 50, y : 50 }
                maxspeed : 2.0
                maxforce : 0.05
            } into boids;
        }
    }
    var boids : Boid[];

    var ticker : Timeline = Timeline {
        repeatCount: Timeline.INDEFINITE
        keyFrames :
            KeyFrame {
                time : 20ms
                canSkip : true
                action : function() {
                    update();
                }
            }
    };

    public function update(): Void {
        for( i in [0.._N] ) {
            boids[i].run( boids );
        }
        boids[0].run( boids );
    }

    public function start(): Void {
        ticker.play();
    }

    public override function create(): Node {
        return {
            Group {
                content : bind boids
            }
        }
    }
}

class Boid extends CustomNode {
    public var loc : Vector3D;
    var vel : Vector3D;
    var acc : Vector3D;
    var r : Number;
    var maxforce : Number;
    var maxspeed : Number;

    var x : Number = bind loc.x;

    var width : Number = 200;
    var height : Number = 200;

    init {
        var random : Random = new Random();
        //loc = Vector3D { x : 50, y : 50, z : 0 };
        vel = Vector3D { x : random.nextDouble() * 2 - 1, y : random.nextDouble() * 2 - 1 };
        acc = Vector3D { x : 0, y : 0, z : 0 };
        r = 2.0;
        maxspeed = 2.0;
        maxforce = 0.05;
    }

    function run( boids : Boid[] ): Void {
        flock( boids );
        update();
        borders();
    }

    function flock( boids : Boid[] ): Void {
        var sep : Vector3D = separate( boids );
        var ali : Vector3D = align( boids );
        var coh : Vector3D = cohesion( boids );

        sep.mult( 2.0 );
        ali.mult( 2.0 );
        coh.mult( 2.0 );

        acc.add( sep );
        acc.add( ali );
        acc.add( coh );
    }

    function update(): Void {
        // Update velocity
        vel.add( acc );
        // Limit speed
        vel.limit( maxspeed );
        loc.add( vel );
        // Reset accelertion to 0 each cycle
        acc.x = 0; acc.y = 0; acc.z = 0;
    }

    function borders(): Void {
        if( loc.x < -r ) loc.x = width + r;
        if( loc.y < -r ) loc.y = height + r;
        if( loc.x > width + r ) loc.x = -r;
        if( loc.y > height + r ) loc.y = -r;
    }

    override function create(): Node {
        return Group {
            transforms : [
                javafx.scene.transform.Translate { x : bind loc.x, y : bind loc.y },
                javafx.scene.transform.Rotate { angle : bind Math.toDegrees( vel.heading2D()) + 90 }
            ]
            content : [
                Line {
                    startX : 0, startY : -3, endX : -2, endY : 3, stroke : Color.WHITE },
                Line {
                    startX : -2, startY : 3, endX : 2, endY : 3, stroke : Color.WHITE },
                Line {
                    startX : 2, startY : 3, endX : 0, endY : -3, stroke : Color.WHITE }
            ]
        }
    }

    function steer( target : Vector3D, slowdown ) {
        var steer : Vector3D;
        var desired : Vector3D = target.sub( target, loc );
        var d = desired.magnitude();
        if( d > 0 ) {
            desired.normalize();
            if( slowdown and d < 100.0 ) {
                desired.mult( maxspeed * ( d / 100.0 ));
            } else {
                desired.mult( maxspeed );
            }
            steer = target.sub( desired, vel );
            steer.limit( maxforce );
        } else {
            steer = Vector3D {};
        }
        return steer;
    }

    function separate( boids : Boid[] ): Vector3D {
        var desiredseparation = 25.0;
        var sum : Vector3D = Vector3D {};
        var count : Number = 0;

        for( i in [0..sizeof boids - 1] ) {
            var other : Boid = boids[i];
            var d : Number = loc.distance( loc, other.loc );
            if( d > 0 and d < desiredseparation ) {
                var diff: Vector3D = loc.sub( loc, other.loc );
                diff.normalize();
                diff.div( d );
                sum.add( diff );
                count++;
            }
        }
        if( count > 0 ) {
            sum.div( count );
        }
        return sum;
    }

    function align( boids : Boid[] ): Vector3D {
        var neighbordist = 50.0;
        var sum : Vector3D = Vector3D {};
        var count = 0;

        for( i in [0..sizeof boids - 1 ] ) {
            var other : Boid = boids[i];
            var d = loc.distance( loc, other.loc );
            if( d > 0 and d < neighbordist ) {
                sum.add( other.vel );
                count++;
            }
        }
        if( count > 0 ) {
            sum.div( count );
            sum.limit( maxforce );
        }
        return sum;
    }

    function cohesion( boids : Boid[] ): Vector3D {
        var neighbordist = 50.0;
        var sum : Vector3D = Vector3D {};
        var count = 0;

        for( i in [0..sizeof boids - 1] ) {
            var other : Boid = boids[i];
            var d = loc.distance( loc, other.loc );
            if( d > 0 and d < neighbordist ) {
                sum.add( other.loc );
                count++;
            }
        }
        if( count > 0 ) {
            sum.div( count );
            return steer( sum, false );
        }
        return sum;
    }
}

class Vector3D {

    public var x : Number = 0.0;
    public var y : Number = 0.0;
    public var z : Number = 0.0;

    function magnitude(): Number {
        return Math.sqrt( x*x + y*y + z*z );
    }

    function add( v : Vector3D ): Void {
        x += v.x;
        y += v.y;
        z += v.z;
    }

    function sub( v : Vector3D ): Void {
        x -= v.x;
        y -= v.y;
        z -= v.z;
    }

    function mult( n : Number ): Void {
        x *= n;
        y *= n;
        z *= n;
    }

    function div( n : Number ): Void {
        x /= n;
        y /= n;
        z /= n;
    }

    function normalize(): Void {
        var m = magnitude();
        if( m > 0 ) {
           div( m );
        }
    }

    function limit( max : Number ): Void {
        if( magnitude() > max ) {
          normalize();
          mult( max );
        }
    }

    function sub( v1 : Vector3D, v2 : Vector3D ) {
        return Vector3D {
            x : v1.x - v2.x, y : v1.y - v2.y, z : v1.z - v2.z
        };
    }

    function distance( v1 : Vector3D, v2 : Vector3D ): Number {
        var dx = v1.x - v2.x;
        var dy = v1.y - v2.y;
        var dz = v1.z - v2.z;

        return Math.sqrt( dx*dx + dy*dy + dz*dz );
    }

    function heading2D(): Number {
        return - Math.atan2( -y, x );
    }
}
