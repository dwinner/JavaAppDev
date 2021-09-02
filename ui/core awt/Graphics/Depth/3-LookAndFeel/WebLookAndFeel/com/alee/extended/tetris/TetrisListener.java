package com.alee.extended.tetris;

/**
 * User: mgarin Date: 22.09.11 Time: 14:12
 */

public interface TetrisListener
{
    public void newGameStarted ();

    public void gamePaused ();

    public void gameUnpaused ();

    public void gameOver ();
}
