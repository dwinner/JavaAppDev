package fxaudio.view.playtoolbar;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * Набор переключателей для группировки плей-листов.
 *
 * @author dwinner@inbpx.ru
 * @version 1.0.0 08-07-2012
 */
public class TrackGrouper
{
   protected final static ToggleGroup group = new ToggleGroup();
   protected final static RadioButton solidRadioButton = new RadioButton();
   protected final static RadioButton artistRadioButton = new RadioButton();
   protected final static RadioButton folderRadioButton = new RadioButton();

   static 
   {
      solidRadioButton.setText("Solid");     // TODO: Locate
      artistRadioButton.setText("Artist");   // TODO: Locate
      folderRadioButton.setText("Folder");   // TODO: Locate

      solidRadioButton.setToggleGroup(group);
      artistRadioButton.setToggleGroup(group);
      folderRadioButton.setToggleGroup(group);
   }

   private TrackGrouper()
   {
   }

   public static ToggleGroup getGroup()
   {
      return group;
   }

   public static TrackGrouper getInstance()
   {
      return TrackGrouperHolder.INSTANCE;
   }

   private static class TrackGrouperHolder
   {
      private static final TrackGrouper INSTANCE = new TrackGrouper();

      private TrackGrouperHolder()
      {
      }
   }

}
