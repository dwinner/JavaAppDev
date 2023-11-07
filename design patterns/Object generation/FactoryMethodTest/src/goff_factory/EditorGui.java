package goff_factory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 28.07.12
 * Time: 12:22
 * To change this template use File | Settings | File Templates.
 */
public class EditorGui implements ActionListener
{
   public EditorGui(ItemEditor edit)
   {
      editor = edit;
   }

   public void createGui()
   {
      mainFrame = new JFrame("Factory pattern Example");
      Container content = mainFrame.getContentPane();
      content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

      editorPanel = new JPanel();
      editorPanel.add(editor.getGUI());
      content.add(editorPanel);

      displayPanel = new JPanel();
      display = new JTextArea(10, 40);
      display.setEditable(false);
      displayPanel.add(display);
      content.add(displayPanel);

      controlPanel = new JPanel();
      update = new JButton("Update Item");
      exit = new JButton("Exit");
      controlPanel.add(update);
      controlPanel.add(exit);
      content.add(controlPanel);

      update.addActionListener(this);
      exit.addActionListener(this);

      mainFrame.addWindowListener(new WindowCloseManager());
      mainFrame.pack();
      mainFrame.setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent actionEvent)
   {
      Object originator = actionEvent.getSource();
      if (originator == update)
         updateItem();
      else if (originator == exit)
         exitApplication();
   }

   private void updateItem()
   {
      editor.commitChanges();
      display.setText(editor.toString());
   }

   private void exitApplication()
   {
      System.exit(0);
   }

   private JFrame mainFrame;
   private JTextArea display;
   private JButton update;
   private JButton exit;
   private JPanel controlPanel;
   private JPanel displayPanel;
   private JPanel editorPanel;
   private ItemEditor editor;

   private class WindowCloseManager extends WindowAdapter
   {
      @Override
      public void windowClosing(WindowEvent e)
      {
         exitApplication();
      }
   }
}
