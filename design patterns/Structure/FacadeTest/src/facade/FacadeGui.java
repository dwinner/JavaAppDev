package facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 21.08.12
 * Time: 12:00
 * To change this template use File | Settings | File Templates.
 */
public class FacadeGui implements ActionListener, ItemListener
{
   public FacadeGui(InternationalizationWizard wizard)
   {
      nationalityFacade = wizard;
   }

   public void createGui()
   {
      mainFrame = new JFrame(nationalityFacade.getProperty(GUI_TITLE));
      Container content = mainFrame.getContentPane();
      content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

      displayPanel = new JPanel();
      displayPanel.setLayout(new GridLayout(3, 2));

      countryLabel = new JLabel(nationalityFacade.getProperty(COUNTRY_LABEL));
      countryChooser = new JComboBox<>(nationalityFacade.getNations());
      currencyLabel = new JLabel(nationalityFacade.getProperty(CURRENCY_LABEL));
      currencyTextField = new JTextField();
      phoneLabel = new JLabel(nationalityFacade.getProperty(PHONE_LABEL));
      phoneTextField = new JTextField();

      currencyTextField.setEditable(false);
      phoneTextField.setEditable(false);

      displayPanel.add(countryLabel);
      displayPanel.add(countryChooser);
      displayPanel.add(currencyLabel);
      displayPanel.add(currencyTextField);
      displayPanel.add(phoneLabel);
      displayPanel.add(phoneTextField);
      content.add(displayPanel);

      controlPanel = new JPanel();
      exit = new JButton(nationalityFacade.getProperty(EXIT_CAPTION));
      controlPanel.add(exit);
      content.add(controlPanel);

      exit.addActionListener(this);
      countryChooser.addItemListener(this);

      mainFrame.addWindowListener(new WindowAdapter()
      {
         @Override
         public void windowClosing(WindowEvent e)
         {
            exitApplication();
         }
      });
      mainFrame.pack();
      mainFrame.setVisible(true);
   }

   private void exitApplication()
   {
      System.exit(0);
   }

   private void updateGui()
   {
      nationalityFacade.setNation(countryChooser.getSelectedItem().toString());
      mainFrame.setTitle(nationalityFacade.getProperty(GUI_TITLE));
      countryLabel.setText(nationalityFacade.getProperty(COUNTRY_LABEL));
      currencyLabel.setText(nationalityFacade.getProperty(CURRENCY_LABEL));
      phoneLabel.setText(nationalityFacade.getProperty(PHONE_LABEL));
      exit.setText(nationalityFacade.getProperty(EXIT_CAPTION));

      currencyTextField.setText(nationalityFacade.getCurrencySymbol() + " "
         + nationalityFacade.getNumberFormat().format(5280.50));
      phoneTextField.setText(nationalityFacade.getPhonePrefix());

      mainFrame.invalidate();
      countryLabel.invalidate();
      currencyLabel.invalidate();
      phoneLabel.invalidate();
      exit.invalidate();
      mainFrame.invalidate();
   }

   @Override
   public void actionPerformed(ActionEvent actionEvent)
   {
      if (actionEvent.getSource() == exit)
         exitApplication();
   }

   @Override
   public void itemStateChanged(ItemEvent itemEvent)
   {
      if (itemEvent.getSource() == countryChooser)
         updateGui();
   }

   public void setNation(Nation nation)
   {
      countryChooser.setSelectedItem(nation);
   }

   private static final String GUI_TITLE = "title";
   private static final String EXIT_CAPTION = "exit";
   private static final String COUNTRY_LABEL = "country";
   private static final String CURRENCY_LABEL = "currency";
   private static final String PHONE_LABEL = "phone";

   private JFrame mainFrame;
   private JButton exit;
   private JComboBox<Nation> countryChooser;
   private JPanel controlPanel;
   private JPanel displayPanel;
   private JLabel countryLabel;
   private JLabel currencyLabel;
   private JLabel phoneLabel;
   private JTextField currencyTextField;
   private JTextField phoneTextField;
   private InternationalizationWizard nationalityFacade;

}
