package parser.rd;

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

public abstract class AbstractListParser {
    protected Lexer input;     // from where do we get tokens?
    protected Token lookahead; // the current lookahead token

    protected AbstractListParser(Lexer input) {
        this.input = input;
        consume();
    }

    public void consume() {
        lookahead = input.nextToken();
    }

    public void match(int token) throws MismatchedTokenException {
        if (lookahead.getType() == token ) {
            consume();
        }
        else {
            throw new MismatchedTokenException(
                    "Expected: " + input.getTokenName(token) +
                            "; Found: " + lookahead);
        }
    }
}