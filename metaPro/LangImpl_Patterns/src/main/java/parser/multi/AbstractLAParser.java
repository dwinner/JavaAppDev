package parser.multi;

import lexer.Lexer;
import lexer.Token;
import parser.exception.MismatchedTokenException;

/**
 * Excerpted from "Language Implementation Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book information.
 */

public abstract class AbstractLAParser {
    Lexer input;
    Token[] buffer;     // circular buffer
    int laCapacity;
    int pos = 0;        // position in @buffer

    public AbstractLAParser(Lexer input, int laCapacity) {
        this.input = input;
        this.laCapacity = laCapacity;
        buffer = new Token[laCapacity];
        for (int i = 1; i<= laCapacity; i++) {
            consume();
        }
    }

    public void consume() {
        buffer[pos] = input.nextToken();
        pos = (pos+1) % laCapacity;
    }

    public Token LT(int la) {
        return buffer[(pos + la - 1) % laCapacity];
    }

    public int LA(int la) {
        return LT(la).getType();
    }

    public void match(int token) throws MismatchedTokenException {
        if ( LA(1) == token ) {
            consume();
        } else {
            throw new MismatchedTokenException(
                    "Expected: " + input.getTokenName(token) +
                    "; Found: " + LT(1));
        }
    }
}