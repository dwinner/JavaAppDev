package app;

import observertest.ObserverGui;

public class ObserverTest
{
   public static void main(String[] args)
   {
      System.out.println("Example for the Observer pattern");
      System.out.println("This demonstration uses a central observable");
      System.out.println(" object to send change notifications to several");
      System.out.println(" JPanels in a GUI. Each JPanel is an Observer,");
      System.out.println(" receiving notifcations when there has been some");
      System.out.println(" change in the shared Task that is being edited.");
      System.out.println();

      System.out.println("Creating the ObserverGui");
      ObserverGui application = new ObserverGui();
      application.createGui();
   }
}
