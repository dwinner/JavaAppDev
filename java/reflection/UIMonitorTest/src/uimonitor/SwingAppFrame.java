package uimonitor;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Тестовое окно Swing.
 *
 * @version demo 22-10-2012
 * @author Denis
 */
public class SwingAppFrame extends JFrame
{
   public SwingAppFrame()
   {
      setTitle("UI monitor: Swing");
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
      initComponents();
   }

   private void initComponents()
   {
      GridBagLayout layout = new GridBagLayout();
      setLayout(layout);

      simpleField = new JTextField(20);
      pressButton = new JButton("Press");

      sampleArea = new JTextArea();
      sampleArea.setEditable(false);
      sampleArea.setLineWrap(true);
      sampleArea.setBorder(BorderFactory.createEtchedBorder());

      add(simpleField, new GbcHelper(0, 0).
        setAnchor(GbcHelper.EAST).
        setInsets(DEFAULT_UI_INSET));
      add(pressButton, new GbcHelper(1, 0).
        setAnchor(GbcHelper.WEST).
        setInsets(DEFAULT_UI_INSET));
      add(sampleArea, new GbcHelper(0, 1, 2, 1).
        setFill(GbcHelper.BOTH).
        setWeight(100, 100).
        setInsets(DEFAULT_UI_INSET));

      // Добавление посредников к обработчикам событий

      ActionListener actionListener = new PressButtonListener();
      pressButton.addActionListener(actionListener);
      DynamicProxyUtil.addUIMonitor(pressButton);

      DocumentListener documentListener = new SimpleFieldChangeTracking();
      simpleField.getDocument().addDocumentListener(documentListener);
      DynamicProxyUtil.addUIMonitor(simpleField, documentListener);
   }

   private class PressButtonListener implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
         sampleArea.setText("Press button has clicked");
      }

   }

   private class SimpleFieldChangeTracking implements DocumentListener
   {
      @Override
      public void insertUpdate(DocumentEvent documentEvent)
      {
         sampleArea.setText("Insert action: " + simpleField.getText().trim());
      }

      @Override
      public void removeUpdate(DocumentEvent documentEvent)
      {
         sampleArea.setText("Remove action: " + simpleField.getText().trim());
      }

      @Override
      public void changedUpdate(DocumentEvent documentEvent)
      {
      }

   }

   public static final int DEFAULT_WIDTH = 480;
   public static final int DEFAULT_HEIGHT = 320;
   public static final int DEFAULT_UI_INSET = 10;
   private JTextField simpleField;
   private JButton pressButton;
   private JTextArea sampleArea;
}
