package com.horstmann.corejava;

import javax.swing.*;

/**
 * @version 1.00 2007-11-02
 * @author Cay Horstmann
 */
public abstract class ButtonFrame extends JFrame
{
   public static final int DEFAULT_WIDTH = 300;
   public static final int DEFAULT_HEIGHT = 200;
   protected JPanel panel;
   protected JButton yellowButton;
   protected JButton blueButton;
   protected JButton redButton;

   public ButtonFrame()
   {
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
      add(panel);

      yellowButton = new JButton("Yellow");
      blueButton = new JButton("Blue");
      redButton = new JButton("Red");

      panel.add(yellowButton);
      panel.add(blueButton);
      panel.add(redButton);

      addEventHandlers();
   }

   protected abstract void addEventHandlers();
}
