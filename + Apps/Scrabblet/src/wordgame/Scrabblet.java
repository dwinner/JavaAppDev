package wordgame;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.awt.List;
import java.awt.TextComponent;
import java.awt.event.ActionEvent;

/**
 * Главный класс апплета.
 * @author dwinner@inbox.ru
 */
public class Scrabblet extends Applet implements ActionListener {
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Scrabblet.class.getName());
    
    private ServerConnection server;
    private String serverName;
    private Bag bag;
    private Board board;
    private boolean single = false;
    private boolean outturn;
    private boolean seen_pass = false;
    private Letter theirs[] = new Letter[7];
    private String name;
    private String others_name;
    
    private Panel topPanel;
    private Label prompt;
    private TextField namefield;
    private Button done;
    private TextField chat;
    private List idList;
    private Button challenge;
    private Canvas ican;
    
    @Override
    public void init() {
        setLayout(new BorderLayout());
        serverName = getCodeBase().getHost();
        if (serverName.isEmpty()) {
            serverName = "localhost";
        }
        ican = new IntroCanvas();
    }
    
    @Override
    public void start() {
        try {
            showStatus("Connecting to " + serverName);
            server = new ServerConnection(this, serverName);
            server.start();
            showStatus("Connected: " + serverName);
            if (name == null) {
                prompt = new Label("Enter your name here:");
                namefield = new TextField(20);
                namefield.addActionListener(this);
                topPanel = new Panel();
                topPanel.setBackground(new Color(255, 255, 200));
                topPanel.add(prompt);
                topPanel.add(namefield);
                add("North", topPanel);
                add("Center", ican);
            }
            else {
                if (chat != null) {
                    remove(chat);
                    remove(board);
                    remove(done);
                }
                nameEntered(name);
            }
            validate();
        }
        catch (Exception e) {
            single = true;
            start_Game((int) (0x7fffffff * Math.random()));
        }
    }
    
    @Override
    public void stop() {
        if (!single) {
            server.quit();
        }
    }
    
    @SuppressWarnings("unchecked")
    void add(String id, String hostname, String name) {
        delete(id); // в случае, когда имя уже есть в списке.
        idList.add("(" + id + ")  " + name + "@" + hostname);
        showStatus("Choose a player from the list");
    }
    
    void delete(String id) {
        for (int i = 0; i < idList.getItemCount(); i++) {
            String s = idList.getItem(i);
            s = s.substring(s.indexOf('(') + 1, s.indexOf(')'));
            if (s.equals(id)) {
                idList.remove(i);
                break;
            }
        }
        if (idList.getItemCount() == 0 && bag == null) {
            showStatus("Wait for other players to arrive.");
        }
    }
    
    private String getName(String id) {
        for (int i = 0; i < idList.getItemCount(); i++) {
            String s = idList.getItem(i);
            String id1 = s.substring(s.indexOf('(' + 1), s.indexOf(')'));
            if (id1.equals(id)) {
                return s.substring(s.indexOf(' ') + 3, s.indexOf('@'));
            }
        }
        return null;
    }
    
    // Нас вызвал на игру "id"
    void challenge(String id) {
        outturn = false;
        int seed = (int) (0x7fffffff * Math.random());
        others_name = getName(id);  // Кто это был?
        showStatus("challenged by " + others_name);
        
        // Сюда можно поместить запрос подтверждения...
        server.accept(id, seed);
        server.delete();
        start_Game(seed);
    }
    
    // Наш вызов был принят
    void accept(String id, int seed) {
        outturn = true;
        others_name = getName(id);
        server.delete();
        start_Game(seed);
    }
    
    void chat(String id, String s) {
        showStatus(others_name + ": " + s);
    }
    
    // Противник перемещает фишку и размещает ее в (x, y).
    void move(String letter, int x, int y) {
        for (int i = 0; i < 7; i++) {
            if (theirs[i] != null && theirs[i].getSymbol().equals(letter)) {
                Letter already = board.getLetter(x, y);
                if (already != null) {
                    board.moveLetter(already, 15, 15);  // На лоток
                }
                board.moveLetter(theirs[i], x, y);
                board.commitLetter(theirs[i]);
                theirs[i] = bag.takeOut();
                if (theirs[i] == null) {
                    showStatus("No more letters");
                }
                break;
            }
        }
        board.repaint();
    }
    
    void turn(int score, String words) {
        showStatus(others_name + " played: " + words + " worth " + score);
        done.setEnabled(true);
        board.othersTurn(score);
    }
    
    void quit(String id) {
        showStatus(others_name + " just quit.");
        remove(chat);
        remove(board);
        remove(done);
        nameEntered(name);
    }
    
    private void nameEntered(String s) {
        if (s.isEmpty()) {
            return;
        }
        name = s;
        if (ican != null) {
            remove(ican);
        }
        if (idList != null) {
            remove(idList);
        }
        if (challenge != null) {
            remove(challenge);
        }
        idList = new List(10, false);
        add("Center", idList);
        challenge = new Button("Challenge");
        challenge.addActionListener(this);
        add("North", challenge);
        validate();
        server.setName(name);
        showStatus("Wait for other players to arrive.");
        if (topPanel != null) {
            remove(topPanel);
        }
    }
    
    private void wepick() {
        for (int i = 0; i < 7; i++) {
            Letter l = bag.takeOut();
            board.addLetter(l);
        }
    }
    
    private void theypick() {
        for (int i = 0; i < 7; i++) {
            Letter l = bag.takeOut();
            theirs[i] = l;
        }
    }
    
    @SuppressWarnings("deprecation")
    private void start_Game(int seed) {
        if (single) {
            Frame popup = new Frame("Scrabblet");
            popup.setSize(400, 300);
            popup.add("Center", ican);
            popup.setResizable(false);
            popup.show();
            board = new Board();
            showStatus("no server found, playing solo");
            outturn = true;
        }
        else {
            remove(idList);
            remove(challenge);
            board = new Board(name, others_name);
            chat = new TextField();
            chat.addActionListener(this);
            add("North", chat);
            showStatus("playing against " + others_name);
        }
        
        add("Center", board);
        done = new Button("Done");
        done.addActionListener(this);
        add("South", done);
        validate();
        
        bag = new Bag(seed);
        if (outturn) {
            wepick();
            if (!single) {
                theypick();
            }
        }
        else {
            done.setEnabled(false);
            theypick();
            wepick();
        }
        board.repaint();
    }
    
    private void challenge_them() {
        String s = idList.getSelectedItem();
        if (s == null) {
            showStatus("Choose a player from the list then press Challenge");
        }
        else {
            remove(challenge);
            remove(idList);
            String destid = s.substring(s.indexOf('(') + 1, s.indexOf(')'));
            showStatus("challenging: " + destid);
            server.challenge(destid);
            validate();
        }
    }
    
    private void our_turn() {
        String word = board.findwords();
        if (word == null) {
            showStatus("Illegal letter positions");
        }
        else {
            if ("".equals(word)) {
                if (single) {
                    return;
                }
                if (seen_pass) {
                    done.setEnabled(false);
                    server.turn("pass", 0);
                    showStatus("You passed");
                    seen_pass = false;
                }
                else {
                    showStatus("Press done again to pass");
                    seen_pass = true;
                    return;
                }
            }
            else {
                seen_pass = false;
            }
            showStatus(word);
            board.commit(server);
            for (int i = 0; i < 7; i++) {
                if (board.getTray(i) == null) {
                    Letter l = bag.takeOut();
                    if (l == null) {
                        showStatus("No more letters");
                    }
                    else {
                        board.addLetter(l);
                    }
                }
            }
            if (!single) {
                done.setEnabled(false);
                server.turn(word, board.getTurnScore());
            }
            board.repaint();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == chat) {
            server.chat(chat.getText());
            chat.setText("");
        }
        else if (source == challenge) {
            challenge_them();
        }
        else if (source == done) {
            our_turn();
        }
        else if (source == namefield) {
            TextComponent tc = (TextComponent) source;
            nameEntered(tc.getText());
        }
    }
    
}
