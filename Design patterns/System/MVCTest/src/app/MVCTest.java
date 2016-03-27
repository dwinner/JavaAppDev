package app;

import mvctest.ContactDisplayView;
import mvctest.ContactEditView;
import mvctest.ContactModel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MVCTest
{
   public static void main(String[] args)
   {
      System.out.println("Example for the MVC pattern");
      System.out.println();
      System.out.println("In this example, a Contact is divided into");
      System.out.println(" Model, View and Controller components.");
      System.out.println();
      System.out.println("To illustrate the flexibility of MVC, the same");
      System.out.println(" Model will be used to provide information");
      System.out.println(" to two View components.");
      System.out.println();
      System.out.println("One view, ContactEditView, will provide a Contact");
      System.out.println(" editor window and will be paired with a controller");
      System.out.println(" called ContactEditController.");
      System.out.println();
      System.out.println("The other view, ContactDisplayView, will provide a");
      System.out.println(" display window which will reflect the changes made");
      System.out.println(" in the editor window. This view does not support");
      System.out.println(" user interaction, and so does not provide a controller.");
      System.out.println();

      System.out.println("Creating ContactModel");
      ContactModel model = new ContactModel();

      System.out.println("Creating ContactEditView and ContactEditController");
      ContactEditView editorView = new ContactEditView(model);
      model.addContactView(editorView);
      createGui(editorView, "Contact Edit Window");

      System.out.println("Creating ContactDisplayView");
      ContactDisplayView displayView = new ContactDisplayView();
      model.addContactView(displayView);
      createGui(displayView, "Contact Display Window");
   }

   private static void createGui(JPanel contents, String title)
   {
      JFrame applicationFrame = new JFrame(title);
      applicationFrame.getContentPane().add(contents);
      applicationFrame.addWindowListener(new WindowCloseManager());
      applicationFrame.pack();
      applicationFrame.setVisible(true);
   }

   private static class WindowCloseManager extends WindowAdapter
   {
      @Override
      public void windowClosing(WindowEvent evt)
      {
         System.exit(0);
      }
   }
}
