import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.Serializable;
public class RouterGui implements ActionListener, Receiver{
    private static int instanceCount = 1;
    private RouterClient routerClient;
    private JFrame mainFrame;
    private JButton exit, clearDisplay, sendMessage;
    private JTextArea display;
    private JTextField inputTextField;
    private InputChannel inputChannel;
    
    public OutputChannel getOutputChannel(){
        return routerClient;
    }
    
    public RouterGui(InputChannel newInputChannel){
        inputChannel = newInputChannel;
        routerClient = new RouterClient(this);
    }
    
    public void createGui(){
        mainFrame = new JFrame("Demonstration for the Router pattern - GUI #" + instanceCount++);
        Container content = mainFrame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        
        JPanel displayPanel = new JPanel();
        display = new JTextArea(10, 40);
        JScrollPane displayArea = new JScrollPane(display);
        display.setEditable(false);
        displayPanel.add(displayArea);
        content.add(displayPanel);
        
        JPanel dataPanel = new JPanel();
        dataPanel.add(new JLabel("Message:"));
        inputTextField = new JTextField(30);
        dataPanel.add(inputTextField);
        content.add(dataPanel);
        
        JPanel controlPanel = new JPanel();
        sendMessage = new JButton("Send Message");
        clearDisplay = new JButton("Clear");
        exit = new JButton("Exit");
        controlPanel.add(sendMessage);
        controlPanel.add(clearDisplay);
        controlPanel.add(exit);
        content.add(controlPanel);
        
        sendMessage.addActionListener(this);
        clearDisplay.addActionListener(this);
        exit.addActionListener(this);
        inputTextField.addActionListener(this);
        
        mainFrame.addWindowListener(new WindowCloseManager());
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent evt){
        Object source = evt.getSource();
        if (source == sendMessage){ sendMessage(); }
        else if (source == inputTextField){ sendMessage(); }
        else if (source == clearDisplay){ clearDisplay(); }
        else if (source == exit){ exitApplication(); }
    }
    
    private class WindowCloseManager extends WindowAdapter{
        public void windowClosing(WindowEvent evt){
            exitApplication();
        }
    }
    
    private void exitApplication(){
        System.exit(0);
    }
    
    private void clearDisplay(){
        inputTextField.setText("");
        display.setText("");
    }
    
    private void sendMessage(){
        String data = inputTextField.getText();
        routerClient.sendMessageToRouter(new Message(inputChannel, data));
        inputTextField.setText("");
    }
    
    public void receiveMessage(Message message){
        display.append(message.getMessage() + "\n");
    }
}
