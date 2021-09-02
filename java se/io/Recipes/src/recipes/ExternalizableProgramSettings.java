package recipes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

/**
 * Явная сериализация объектов может быть более эффективна.
 *
 * @author Denis
 */
public class ExternalizableProgramSettings implements Externalizable
{
   public ExternalizableProgramSettings()
   {
   }

   public ExternalizableProgramSettings(Point locationOnScreen, Dimension frameSize, Color defaultFontColor,
                                        String title)
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
   public String toString()
   {
      return "ExternalizableProgramSettings{" + "locationOnScreen=" + locationOnScreen + ", frameSize=" + frameSize + ", defaultFontColor=" + defaultFontColor + ", title=" + title + '}';
   }

   @Override
   public int hashCode()
   {
      int hash = 5;
      hash = 83 * hash + Objects.hashCode(this.locationOnScreen);
      hash = 83 * hash + Objects.hashCode(this.frameSize);
      hash = 83 * hash + Objects.hashCode(this.defaultFontColor);
      hash = 83 * hash + Objects.hashCode(this.title);
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
      final ExternalizableProgramSettings other = (ExternalizableProgramSettings) obj;
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
   public void writeExternal(ObjectOutput out) throws IOException
   {
      out.writeInt(locationOnScreen.x);
      out.writeInt(locationOnScreen.y);
      out.writeInt(frameSize.width);
      out.writeInt(frameSize.height);
      out.writeInt(defaultFontColor.getRGB());
      out.writeUTF(title);
   }

   @Override
   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
   {
      locationOnScreen = new Point(in.readInt(), in.readInt());
      frameSize = new Dimension(in.readInt(), in.readInt());
      defaultFontColor = new Color(in.readInt());
      title = in.readUTF();
   }

   private Point locationOnScreen;
   private Dimension frameSize;
   private Color defaultFontColor;
   private String title;
}
