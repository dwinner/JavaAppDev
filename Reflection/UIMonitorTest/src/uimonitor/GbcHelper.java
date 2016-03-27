package uimonitor;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GbcHelper extends GridBagConstraints
{
   public GbcHelper(int aGridX, int aGridY)
   {
      gridx = aGridX;
      gridy = aGridY;
   }

   public GbcHelper(int aGridx, int aGridY, int aGridWidth, int aGridHeight)
   {
      gridx = aGridx;
      gridy = aGridY;
      gridwidth = aGridWidth;
      gridheight = aGridHeight;
   }

   public GbcHelper setAnchor(int anAnchor)
   {
      anchor = anAnchor;
      return this;
   }

   public GbcHelper setFill(int aFill)
   {
      fill = aFill;
      return this;
   }

   public GbcHelper setWeight(double aWeightX, double aWeightY)
   {
      weightx = aWeightX;
      weighty = aWeightY;
      return this;
   }

   public GbcHelper setInsets(int aDistance)
   {
      insets = new Insets(aDistance, aDistance, aDistance, aDistance);
      return this;
   }

   public GbcHelper setInsets(int aTop, int aLeft, int aBottom, int aRight)
   {
      insets = new Insets(aTop, aLeft, aBottom, aRight);
      return this;
   }

   public GbcHelper setIpad(int anIpadX, int anIpadY)
   {
      ipadx = anIpadX;
      ipady = anIpadY;
      return this;
   }

}
