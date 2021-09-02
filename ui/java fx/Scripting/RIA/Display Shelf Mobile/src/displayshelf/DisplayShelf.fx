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
package displayshelf;

import javafx.animation.*;
import javafx.scene.*;
import javafx.util.*;

public class DisplayShelf extends CustomNode {
    //public var content:ShelfItem[];
    public var items:Node[];
    public-init var spacing:Number = 110;
    public-init var leftOffset:Number = -50;
    public-init var rightOffset:Number = 50;
    public-init var perspective = false;
    public-init var scaleSmall = 0.5;

    // Made changes to adjust the imageView size dynamically.
    public var imageWidth: Number on replace {
        spacing = imageWidth * scaleSmall * 0.7;
        leftOffset = -(imageWidth/2.0);
        rightOffset = imageWidth/2.0;
    };

    var left:Group = Group { };
    var center:Group = Group { };
    var right:Group = Group { };

    def minSize = bind Math.min(scene.width, scene.height);

    public var centerIndex = 0;

    override public function create():Node {
        var half = items.size()/2-1;

        left.content = items[0..half-2];
        center.content = items[half-1];
        right.content = items[half..items.size()-1];
        right.content = Sequences.<<reverse>>(right.content) as Node[];
        centerIndex = half-1;
        
        return Group {
            translateX: bind (scene.width - minSize) / 2
            content: [
                left,
                right,
                center
            ]
        }
    }

    /**
     * "Reparents" the node sequence newContent to its new parent Group
     * newParent, replacing any previous content,
     * after first removing them from their previous parent Group.
     */
    public function reparent(newContent:Node[], newParent:Group):Void {
        for (n in newContent) {
            if (n.parent instanceof Group) {
                delete n from (n.parent as Group).content;
            }
        }
        newParent.content = newContent;
    }

    public function shift(offset:Integer):Void {
        if(centerIndex <= 0 and offset > 0 ) {
            return;
        }
        if(centerIndex >= items.size()-1 and offset < 0) {
            return;
        }


        centerIndex -= offset;
        reparent(items[0..centerIndex-1], left);
        reparent([items[centerIndex]], center);
        reparent(Sequences.<<reverse>>(items[centerIndex+1..items.size()-1]) as Node[], right);
        doLayout();
    }

    public override function doLayout() {
        var endKeyframes:KeyFrame[];
        var duration = 0.5s;
        var centerOffset = (minSize - imageWidth)/2;

        for(n in left.content) {
            def it = n as Item;
            var newX = -left.content.size()*spacing +  spacing * indexof n + leftOffset;
            insert KeyFrame { time: duration values: [
                    it.translateX => newX + centerOffset,
                    it.scaleX => scaleSmall,
                    it.scaleY => scaleSmall,
                    it.angle => 45
                ] } into endKeyframes;
        }

        for(n in center.content) {
            def it = n as Item;
            insert KeyFrame { time: duration values: [
                    it.translateX => centerOffset,
                    it.scaleX => 1.0,
                    it.scaleY => 1.0,
                    it.angle => 90
                ] } into endKeyframes;
        }

        for(n in right.content) {
            def it = n as Item;
            var newX = right.content.size()*spacing -spacing * indexof n + rightOffset;
            insert KeyFrame { time: duration values: [
                    it.translateX => newX + centerOffset,
                    it.scaleX => scaleSmall,
                    it.scaleY => scaleSmall,
                    it.angle => 135
                ] } into endKeyframes;
        }

        var anim = Timeline {
            keyFrames: [endKeyframes]
        };
        anim.playFromStart();

    }

    public function shiftToCenter(item:Item):Void {
        for(n in left.content) {
            if(n == item) {
                var index = indexof n;
                var shiftAmount = left.content.size()-index;
                shift(shiftAmount);
                return;
            }
        }
        for(n in center.content) {
            if(n == item) {
                return;
            }
        }
        for(n in right.content) {
            if(n == item) {
                var index = indexof n;
                var shiftAmount = -(right.content.size()-index);
                shift(shiftAmount);
                return;
            }
        }
    }

}

