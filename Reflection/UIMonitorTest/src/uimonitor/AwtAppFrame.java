package uimonitor;

import java.awt.*;
import java.awt.event.*;

/**
 * Тестовое окно AWT.
 *
 * @version demo 22-10-2012
 * @author Denis
 */
public class AwtAppFrame extends Frame
{
   public AwtAppFrame() throws HeadlessException
   {
      setTitle("UI monitor: AWT");
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
      addWindowListener(new WindowAdapter()
      {
         @Override
         public void windowClosing(WindowEvent e)
         {
            AwtAppFrame.this.dispose();
         }

      });
      initComponents();
   }

   private void initComponents()
   {
      GridBagLayout layout = new GridBagLayout();
      setLayout(layout);

      simpleField = new TextField(20);
      pressButton = new Button("Press");

      sampleArea = new TextArea();
      sampleArea.setEditable(false);

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

      // Добавление обработчиков событий и посредников для мониторинга

      ActionListener actionListener = new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            sampleArea.setText("Button has clicked");
         }

      };
      pressButton.addActionListener(actionListener);
      DynamicProxyUtil.addUIMonitor(pressButton);

      TextListener textListener = new TextListener()
      {
         @Override
         public void textValueChanged(TextEvent e)
         {
            sampleArea.setText("Text changed: " + simpleField.getText().trim());
         }

      };
      simpleField.addTextListener(textListener);
      DynamicProxyUtil.addUIMonitor(simpleField);
   }

   public static final int DEFAULT_WIDTH = 480;
   public static final int DEFAULT_HEIGHT = 320;
   public static final int DEFAULT_UI_INSET = 10;
   private TextField simpleField;
   private Button pressButton;
   private TextArea sampleArea;
}
