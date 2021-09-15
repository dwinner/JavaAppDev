package wordgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.logging.Logger;

/**
 * Игровая логика.
 * @author dwinner@inbox.ru
 */
class Board extends Canvas {
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Board.class.getName());
    
    private Letter board[][] = new Letter[15][15];
    private Letter tray[] = new Letter[7];
    private Point orig = new Point(0, 0);
    private Point here = new Point(0, 0);
    private String name;
    private String others_name = null;
    private int total_score = 0;
    private int turn_score = 0;
    private int others_score = 0;
    
    private Letter pick;    // перетаскиваемая буква
    private int dx, dy;     // смещение к вехрнему/левому углу выбора
    private int lw, lh;     // ширина и высота буквы
    private int tm, lm;     // верхний и левый край
    private int lt;         // толщина линии (между фишками)
    private int aw, ah;     // размер области буквы
    
    private Dimension offscreensize;
    private Image offscreen;
    private Graphics offGraphics;
    private Image offscreen2;
    private Graphics offGraphics2;
    
    private Color bg = new Color(175, 185, 175);
    private Color w3 = new Color(255, 50, 100);
    private Color w2 = new Color(255, 200, 200);
    private Color l3 = new Color(75, 75, 255);
    private Color l2 = new Color(150, 200, 255);   
    private Color tiles[][] = {
        {w3, bg, bg, l2, bg, bg, bg, w3},
        {bg, w2, bg, bg, bg, l3, bg, bg},
        {bg, bg, w2, bg, bg, bg, l2, bg},
        {l2, bg, bg, w2, bg, bg, bg, l2},
        {bg, bg, bg, bg, w2, bg, bg, bg},
        {bg, l3, bg, bg, bg, l3, bg, bg},
        {bg, bg, l2, bg, bg, bg, l2, bg},
        {w3, bg, bg, l2, bg, bg, bg, w2}
    };
    
    private int x0, y0, w0, h0;

    Board(String ourName, String othersName) {
        this.name = ourName;
        this.others_name = othersName;
        addMouseListener(new MyMouseAdapter());
        addMouseMotionListener(new MyMouseMotionAdapter());
    }

    Board() {
        this(null, null);
    }
    
    void othersTurn(int score) {
        others_score += score;
        paintScore();
        repaint();
    }
    
    int getTurnScore() {
        paintScore();
        return turn_score;
    }
    
    Letter getTray(int i) {
        return (i >= 0 && i < tray.length) ? tray[i] : null;
    }
    
    synchronized boolean addLetter(Letter l) {
        for (int i = 0; i < tray.length; i++) {
            if (tray[i] == null) {
                tray[i] = l;
                moveLetter(l, i, 15);
                return true;
            }
        }
        return false;
    }
    
    @SuppressWarnings("NestedAssignment")
    private boolean existingLetterAt(int x, int y) {
        Letter l = null;
        return (x >= 0 && y <= 14 && y >= 0 && y <= 14 && (l = board[y][x]) != null && l.recall() == null);
    }
    
    @SuppressWarnings("NestedAssignment")
    synchronized String findwords() {
        String res = "";
        turn_score = 0;
        
        int ntiles = 0;
        Letter[] atplay = new Letter[7];
        for (int i = 0; i < 7; i++) {
            if (tray[i] != null && tray[i].recall() != null) {
                atplay[ntiles] = tray[i];
                ntiles++;
            }
        }
        if (ntiles == 0) {
            return res;
        }
        
        // Если есть хоть одна фишка, назовем ее горизонтальной
        boolean horizontal = true;
        boolean vertical = false;
        if (ntiles > 1) {
            int x = atplay[0].x;
            int y = atplay[0].y;
            horizontal = (atplay[1].y == y);
            vertical = (atplay[1].x == x);
            if (!horizontal && !vertical) { // диагональ
                return null;
            }
            for (int i = 2; i < ntiles; i++) {
                if (horizontal && atplay[i].y != y || vertical && atplay[i].x != x) {
                    return null;
                }
            }
        }
        
        // Убедиться, что по крайней мере одна играющая фишка касается хотя бы
        // одной существующей фишки.
        boolean attached = false;
        for (int i = 0; i < ntiles; i++) {
            Point p = atplay[i].recall();
            int x = p.x;
            int y = p.y;
            if ((x == 7 && y == 7 && ntiles > 1) || 
                    existingLetterAt(x-1, y) || existingLetterAt(x+1, y) ||
                    existingLetterAt(x, y-1) || existingLetterAt(x, y+1))
            {
                attached = true;
                break;
            }
        }
        if (!attached) {
            return null;
        }
        
        // Здесь мы используем -1, что означает проверку сначала главного направления,
        // затем 0..ntiles проверяет слова, ортогональные ему.
        for (int i = -1; i < ntiles; i++) {
            Point p = atplay[i == -1 ? 0 : i].recall(); // Где она?
            int x = p.x;
            int y = p.y;
            
            int xinc, yinc;
            if (horizontal) {
                xinc = 1;
                yinc = 0;
            }
            else {
                xinc = 0;
                yinc = 1;
            }
            int mult = 1;
            
            String word = "";
            int word_score = 0;
            
            // Здесь мы возвращаемся к самой верхней/левой букве.
            while (x >= xinc && y >= yinc && board[y-yinc][x-xinc] != null) {
                x -= xinc;
                y -= yinc;
            }
            
            int n = 0;
            int letters_seen = 0;    // буквы, которые мы только что разыграли
            Letter l;
            while (x < 15 && y < 15 && (l = board[y][x]) != null) {
                word += l.getSymbol();
                int lscore = l.getPoints();
                if (l.recall() != null) {   // буква, разыгрываемая сейчас...
                    Color t = tiles[y < 8 ? y : 14-y][x < 8 ? x : 14-x];
                    if (t == w3) {
                        mult *= 3;
                    }
                    else if (t == w2) {
                        mult *= 2;
                    }
                    else if (t == l3) {
                        lscore *= 3;
                    }
                    else if (t == l2) {
                        lscore *= 2;
                    }
                    if (i == -1) {
                        letters_seen++;
                    }
                }
                word_score += lscore;
                n++;
                x += xinc;
                y += yinc;
            }
            word_score *= mult;
            
            // Здесь мы возвращаемся обратно к самой верхней/левой букве.
            while (x >= xinc && y >= yinc && board[y-yinc][x-xinc] != null) {
                x -= xinc;
                y -= yinc;
            }
            
            n = 0;
            letters_seen = 0;   // буквы, только что разыгранные
            l = null;
            while (x < 15 && y < 15 && (l = board[y][x]) != null) {
                word += l.getSymbol();
                int lscore = l.getPoints();
                if (l.recall() != null) {   // буквы, которые сейчас в игре...
                    Color t = tiles[y < 8 ? y : 14-y][x < 8 ? x : 14-x];
                    if (t == w3) {
                        mult *= 3;
                    }
                    else if (t == w2) {
                        mult *= 2;
                    }
                    else if (t == l3) {
                        lscore *= 3;
                    }
                    else if (t == l2) {
                        lscore *= 2;
                    }
                    if (i == -1) {
                        letters_seen++;
                    }
                }
                word_score += lscore;
                n++;
                x += xinc;
                y += yinc;
            }
            word_score *= mult;
            
            if (n < 2) {    // не считать одиночные буквы дважды
                continue;
            }
            
            turn_score += word_score;
            res += word + " ";
        }
        
        total_score += turn_score;
        return res;
    }
    
    @SuppressWarnings("NestedAssignment")
    synchronized void commit(ServerConnection s) {
        for (int i = 0; i < 7; i++) {
            Point p;
            if (tray[i] != null && (p = tray[i].recall()) != null) {
                if (s != null) {    // соединение с сервером существует
                    s.move(tray[i]);
                }
                commitLetter(tray[i]);    // помечает символ как "вне игры"
                tray[i] = null;
            }
        }
    }
    
    void commitLetter(Letter letter) {
        if (letter != null && letter.recall() != null) {
            letter.paint(offGraphics, Letter.DIM);
            letter.remember(null);  // помечает символ как "вне игры"
        }
    }
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    @Override
    public synchronized void paint(Graphics g) {
        Dimension d = checksize();
        Graphics gc = offGraphics2;
        if (pick != null) {
            gc = gc.create();
            gc.clipRect(x0, y0, w0, h0);
            g.clipRect(x0, y0, w0, h0);
        }
        gc.drawImage(offscreen, 0, 0, null);
        
        for (int i = 0; i < 7; i++) {
            Letter l = tray[i];
            if (l != null && l != pick) {
                l.paint(gc, Letter.NORMAL);
            }
        }
        if (pick != null) {
            pick.paint(gc, Letter.BRIGHT);
        }
        g.drawImage(offscreen2, 0, 0, null);
    }
    
    Letter LetterHit(int x, int y) {
        for (int i = 0; i < 7; i++) {
            if (tray[i] != null && tray[i].hit(x, y)) {
                return tray[i];
            }
        }
        return null;
    }
    
    private void unplay(Letter letter) {
        Point p = letter.recall();
        if (p != null) {
            board[p.y][p.x] = null;
            letter.remember(null);
        }
    }
    
    private void moveToTray(Letter l, int i) {
        int x = lm + (lw + lt) * i;
        int y = tm + ah - 2 * lt;
        l.move(x, y);
    }
    
    @SuppressWarnings("AssignmentToMethodParameter")
    private void dropOnTray(Letter l, int x) {
        unplay(l);  // удалить букву
        
        // найти, в каком слоте была эта буква
        int oldx = 0;
        for (int i = 0; i < 7; i++) {
            if (tray[i] == l) {
                oldx = i;
                break;
            }
        }
        
        // Если слот, на который мы опустили фишку, был пуст,
        // найти самый правый занятый слот.
        if (tray[x] == null) {
            for (int i = 6; i >= 0; i--) {
                if (tray[i] != null) {
                    x = i;
                    break;
                }
            }
        }
        
        // Если слот, на который мы опустили фишку, уже содержит игравшую
        // на поле фишку, просто обменяться со слотом.
        if (tray[x].recall() != null) {
            tray[oldx] = tray[x];
        }
        else {
            // мы просто переупорядочиваем фишку, которая уже в лотке
            if (oldx < x) { // перемещение влево
                for (int i = oldx; i < x; i++) {
                    tray[i] = tray[i+1];
                    if (tray[i].recall() == null) {
                        moveToTray(tray[i], i);
                    }
                }
            }
            else {  // перемещение вправо
                for (int i = oldx; i > x; i--) {
                    tray[i] = tray[i-1];
                    if (tray[i].recall() == null) {
                        moveToTray(tray[i], i);
                    }
                }
            }
        }
        
        tray[x] = l;
        moveToTray(l, x);
    }

    Letter getLetter(int x, int y) {
        return board[y][x];
    }

    @SuppressWarnings("AssignmentToMethodParameter")
    void moveLetter(Letter l, int x, int y) {
        if (y > 14 || x > 14 || y < 0 || x < 0) {
            // Если мы вне игрового поля
            if (x > 6) {
                x = 6;
            }
            if (x < 0) {
                x = 0;
            }
            dropOnTray(l, x);
        }
        else {
            if (board[y][x] != null) {
                x = orig.x;
                y = orig.y;
            }
            else {
                here.x = x;
                here.y = y;
                unplay(l);
                board[y][x] = l;
                l.remember(here);
                
                // вернуться к пикселям
                x = lm + (lw + lt) * x;
                y = tm + (lh + lt) * y;
            }
            l.move(x, y);
        }
    }

    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    private Dimension checksize() {
        Dimension d = getSize();
        int w = d.width;
        int h = d.height;
        
        if (w < 1 || h < 1) {
            return d;
        }
        
        if ((offscreen == null) || (w != offscreensize.width) || h != offscreensize.height) {
            System.out.println("updating board: " + w + " x " + h + "\r");
            
            offscreen = createImage(w, h);
            offscreensize = d;
            offGraphics = offscreen.getGraphics();
            offscreen2 = createImage(w, h);
            offGraphics2 = offscreen2.getGraphics();
            
            offGraphics.setColor(Color.white);
            offGraphics.fillRect(0, 0, w, h);
            
            /*
             * lt - толщина белых линий между фишками
             * gaps - сумма всех пробелов
             * lw, lh - размеры фишек
             * aw, ah - размеры всего игрового поля
             * lm, tm - левое и верхнее краевые поля для центральной области в апплете
             */
            lt = 1 + w / 400;
            int gaps = lt * 20;
            lw = (w - gaps) / 15;
            lh = (h - gaps - lt * 2) / 16;  // компенсация для высоты лотка
            aw = lw * 15 + gaps;
            ah = lh * 15 + gaps;
            lm = (w - aw) / 2 + lt;
            tm = (h - ah - (lt * 2 + lh)) / 2 + lt;
            
            offGraphics.setColor(Color.black);
            offGraphics.fillRect(lm, tm, aw - 2 * lt, ah - 2 * lt);
            lm += lt;
            tm += lt;
            offGraphics.setColor(Color.white);
            offGraphics.fillRect(lm, tm, aw - 4 * lt, ah - 4 * lt);
            lm += lt;
            tm += lt;
            int sfh = (lh > 30) ? lh / 4 : lh / 2;
            Font font = new Font("SansSerif", Font.PLAIN, sfh);
            offGraphics.setFont(font);
            for (int j = 0, y = tm; j < 15; j++, y += lh + lt) {
                for (int i = 0, x = lm; i < 15; i++, x += lw + lt) {
                    Color c = tiles[j < 8 ? j : 14-j][i < 8 ? i : 14-i];
                    offGraphics.setColor(c);
                    offGraphics.fillRect(x, y, lw, lh);
                    offGraphics.setColor(Color.black);
                    if (lh > 30) {
                        String td = (c == w2 || c == l2) ? "DOUBLE" :
                                    (c == w3 || c == l3) ? "TRIPLE" : null;
                        String wl = (c == l2 || c == l3) ? "LETTER" :
                                    (c == w2 || c == w3) ? "WORD" : null;
                        if (td != null) {
                            center(offGraphics, td, x, y + 2 + sfh, lw);
                            center(offGraphics, wl, x, y + 2 * (2 + sfh), lw);
                            center(offGraphics, "SCORE", x, y + 3 * (2 + sfh), lw);
                        }
                    }
                    else {
                        String td = (c == w2 || c == l2) ? "2" :
                                    (c == w3 || c == l3) ? "3" : null;
                        String wl = (c == l2 || c == l3) ? "L" :
                                    (c == w2 || c == w3) ? "W" : null;
                        if (td != null) {
                            center(offGraphics, td + wl, x, y + (lh - sfh) * 4 / 10 + sfh, lw);
                        }
                    }
                }
            }
            Color c = new Color(255, 255, 200);
            offGraphics.setColor(c);
            offGraphics.fillRect(lm, tm + ah - 3 * lt, 7 * (lw + lt), lh + 2 * lt);
            Letter.resize(lw, lh);
            
            // если мы уже имеем несколько букв, разместим их
            for (int i = 0; i < 7; i++) {
                if (tray[i] != null) {
                    moveToTray(tray[i], i);
                }
            }
            paintScore();
        }
        return d;
    }

    @SuppressWarnings("AssignmentToMethodParameter")
    private void center(Graphics g, String s, int x, int y, int w) {
        x += (w - g.getFontMetrics().stringWidth(s)) / 2;
        g.drawString(s, x, y);
    }
    
    private void paintScore() {
        int x = lm + (lw + lt) * 7 + lm;
        int y = tm + ah - 3 * lt;
        int h = lh + 2 * lt;
        Font font = new Font("TimesRoman", Font.PLAIN, h/2);
        offGraphics.setFont(font);
        FontMetrics fm = offGraphics.getFontMetrics();
        
        offGraphics.setColor(Color.white);
        offGraphics.fillRect(x, y, aw, h);
        offGraphics.setColor(Color.black);
        if (others_name == null) {
            int l_y0 = (h - fm.getHeight()) / 2 + fm.getAscent();
            offGraphics.drawString("Score: " + total_score, x, y + l_y0);
        }
        else {
            h /= 2;
            int l_y0 = (h - fm.getHeight()) / 2 + fm.getAscent();
            offGraphics.drawString(name + ": " + total_score, x, y + l_y0);
            offGraphics.drawString(others_name + ": " + others_score, x, y + h + l_y0);
        }
    }
    
    private void selectLetter(int x, int y) {
        pick = LetterHit(x, y);
        if (pick != null) {
            dx = pick.x - x;
            dy = pick.y - y;
            orig.x = pick.x;
            orig.y = pick.y;
        }
        repaint();
    }
    
    @SuppressWarnings("AssignmentToMethodParameter")
    private void dropLetter(int x, int y) {
        if (pick != null) {
            // определить центр фишки
            x += dx + lw / 2;
            y += dy + lh / 2;
            // определить индекс фишки
            x = (x - lm) / (lw + lt);
            y = (y - tm) / (lh + lt);
            
            moveLetter(pick, x, y);
            
            pick = null;
            repaint();
        }
    }
    
    private void dragLetter(int x, int y) {
        if (pick != null) {
            int ox = pick.x;
            int oy = pick.y;
            pick.move(x + dx, y + dy);
            x0 = Math.min(ox, pick.x);
            y0 = Math.min(ox, pick.x);
            w0 = pick.w + Math.abs(ox - pick.x);
            h0 = pick.h + Math.abs(oy - pick.y);
            paint(getGraphics());
        }
    }
    
    private class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent me) {
            selectLetter(me.getX(), me.getY());
        }
        @Override
        public void mouseReleased(MouseEvent me) {
            dropLetter(me.getX(), me.getY());
        }
    }

    private class MyMouseMotionAdapter extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent me) {
            dragLetter(me.getX(), me.getY());
        }
    }
    
}
