package recipes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

/**
 * Сущностный класс для сохранения установок программы через механизмы сериализации.
 *
 * @author Denis
 */
public class ProgramSettings implements Serializable
{
   public ProgramSettings()
   {
   }

   public ProgramSettings(Point locationOnScreen, Dimension frameSize, Color defaultFontColor, String title)
   {
      this.locationOnScreen = locationOnScreen;
      this.frameSize = frameSize;
      this.defaultFontColor = defaultFontColor;
      this.title = title;
   }

   public Point getLocationOnScreen()
   {
      return locationOnScreen;
   }

   public void setLocationOnScreen(Point locationOnScreen)
   {
      this.locationOnScreen = locationOnScreen;
   }

   public Dimension getFrameSize()
   {
      return frameSize;
   }

   public void setFrameSize(Dimension frameSize)
   {
      this.frameSize = frameSize;
   }

   public Color getDefaultFontColor()
   {
      return defaultFontColor;
   }

   public void setDefaultFontColor(Color defaultFontColor)
   {
      this.defaultFontColor = defaultFontColor;
   }

   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   @Override
   public int hashCode()
   {
      int hash = 7;
      hash = 79 * hash + Objects.hashCode(this.locationOnScreen);
      hash = 79 * hash + Objects.hashCode(this.frameSize);
      hash = 79 * hash + Objects.hashCode(this.defaultFontColor);
      hash = 79 * hash + Objects.hashCode(this.title);
      return hash;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj == null)
      {
         return false;
      }
      if (getClass() != obj.getClass())
      {
         return false;
      }
      final ProgramSettings other = (ProgramSettings) obj;
      if (!Objects.equals(this.locationOnScreen, other.locationOnScreen))
      {
         return false;
      }
      if (!Objects.equals(this.frameSize, other.frameSize))
      {
         return false;
      }
      if (!Objects.equals(this.defaultFontColor, other.defaultFontColor))
      {
         return false;
      }
      if (!Objects.equals(this.title, other.title))
      {
         return false;
      }
      return true;
   }

   @Override
   public String toString()
   {
      return "ProgramSettings{" + "locationOnScreen=" + locationOnScreen + ", frameSize=" + frameSize + ", defaultFontColor=" + defaultFontColor + ", title=" + title + '}';
   }

   private Point locationOnScreen;
   private Dimension frameSize;
   private Color defaultFontColor;
   private String title;
   private static final long serialVersionUID = 1L;
}
