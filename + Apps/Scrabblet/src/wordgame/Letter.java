package wordgame;

import java.awt.Point;
import java.util.logging.Logger;

/**
 *
 * @author dwinner@inbox.ru
 */
class Letter {
    
    private static final Logger LOG = Logger.getLogger(Letter.class.getName());

    static void resize(int lw, int lh) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    int x;
    int y;
    int w;
    int h;

    Object getSymbol() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    Point recall() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    int getPoints() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    void remember(Object object) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    boolean hit(int x, int y) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    void move(int x, int y) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
