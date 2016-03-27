package resources;

import javafx.scene.image.Image;

/**
 * Класс для загрузки ресурсов приложения.
 *
 * @author Denis
 */
public class ResourceLoader
{
   /**
    * Класс для загрузки иконок.
    */
   public static class IconLoader
   {
      public static final Image OPEN =
        new Image(IconLoader.class.getResource("icons/Open.png").toString());
      public static final Image SAVE =
        new Image(IconLoader.class.getResource("icons/Save.png").toString());
      public static final Image EXIT =
        new Image(IconLoader.class.getResource("icons/Exit.png").toString());
      public static final Image TURN_OFF =
        new Image(IconLoader.class.getResource("icons/TurnOff.png").toString());

      private IconLoader()
      {
      }
   }

   private ResourceLoader()
   {
   }
}
