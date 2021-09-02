/*
* This file is part of WebLookAndFeel library.
*
* WebLookAndFeel library is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* WebLookAndFeel library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.extended.button;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: mgarin Date: 16.09.11 Time: 17:09
 */

public class FloatSpring
{
    float position;

    float springK;

    float dampingK;

    float velocity;

    /**
     * Make a spring with given spring constant and damping constant
     *
     * @param springK  Spring constant, the higher this is the "tighter" the spring, and the more
     *                 force it will exert for a given extension
     * @param dampingK Damping constant, the higher this is the stronger the damping, and the more
     *                 "soggy" the movement.
     */
    public FloatSpring ( float springK, float dampingK )
    {
        super ();
        this.position = 0;
        this.springK = springK;
        this.dampingK = dampingK;
        this.velocity = 0;
    }

    /**
     * Create a critically damped spring (or near to critically damped) This spring will quickly
     * move to its target without overshooting
     *
     * @param springK The spring constant - the higher this is, the more quickly the spring will
     *                reach its target. A value of 100 gives a reasonable response in about a
     *                second, a higher value gives a faster response.
     */
    public FloatSpring ( float springK )
    {
        this ( springK, ( float ) ( 2 * Math.sqrt ( springK ) ) );
    }

    /**
     * Update the position of the spring. This updates the "position" as if there were a damped
     * spring stretched between the current position and the target position. That is, the spring
     * will tend to pull the position towards the target, and if the spring is damped the position
     * will eventually settle onto the target.
     *
     * @param target The target towards which the spring is pulling the position
     * @param time   The elapsed time in seconds
     */
    public void update ( float target, float time )
    {

        // Set v to target - position, this is the required movement
        float v = position - target;

        // Multiply displacement by spring constant to get spring force,
        // then subtract damping force
        v = v * -springK - velocity * dampingK;

        // v is now a force, so assuming unit mass is is also acceleration.
        // multiply by elapsed time to get velocity change
        velocity += v * time;

        // If velocity isn't valid, zero it
        if ( Float.isNaN ( velocity ) || Float.isInfinite ( velocity ) )
        {
            velocity = 0;
        }

        // Change the position at the new velocity, for elapsed time
        position += velocity * time;
    }

    /**
     * @return Damping constant, the higher this is the stronger the damping, and the more "soggy"
     *         the movement.
     */
    public float getDampingK ()
    {
        return dampingK;
    }

    /**
     * @param dampingK Damping constant, the higher this is the stronger the damping, and the more
     *                 "soggy" the movement.
     */
    public void setDampingK ( float dampingK )
    {
        this.dampingK = dampingK;
    }

    /**
     * @return The current position of the simulated spring end point, changes as simulation is
     *         updated
     */
    public float getPosition ()
    {
        return position;
    }

    /**
     * @param position A new position for simulated spring end point
     */
    public void setPosition ( float position )
    {
        this.position = position;
    }

    /**
     * @return The spring constant - the higher this is, the more quickly the spring will reach its
     *         target
     */
    public float getSpringK ()
    {
        return springK;
    }

    /**
     * @param springK The spring constant - the higher this is, the more quickly the spring will
     *                reach its target
     */
    public void setSpringK ( float springK )
    {
        this.springK = springK;
    }

    /**
     * @return The current velocity of the position
     */
    public float getVelocity ()
    {
        return velocity;
    }

    /**
     * @param velocity A new value for the current velocity of the position
     */
    public void setVelocity ( float velocity )
    {
        this.velocity = velocity;
    }

    private static Timer timer;

    public static void main ( String[] args )
    {
        final FloatSpring fs = new FloatSpring ( 5 );
        fs.setPosition ( 16 );
        timer = new Timer ( 1000 / 48, new ActionListener ()
        {
            private int timeLeft = 0;

            public void actionPerformed ( ActionEvent e )
            {
                timeLeft += 1000 / 48;
                fs.update ( 32, ( float ) timeLeft / 1000 );

                if ( Math.round ( fs.getPosition () ) == 32 )
                {
                    timer.stop ();
                    System.out.println ( "===== END =====" );
                    System.exit ( 0 );
                }
            }
        } );
        timer.start ();

        new JFrame ().setVisible ( true );
    }
}
