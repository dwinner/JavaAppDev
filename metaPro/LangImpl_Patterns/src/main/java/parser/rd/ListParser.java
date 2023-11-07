package parser.rd;

import lexer.Lexer;
import lexer.ListLexer;
import parser.exception.MismatchedTokenException;
import parser.exception.NoViableAltException;

/**
 * Excerpted from "Language Implementation Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book information.
 */

public class ListParser extends AbstractListParser {
    public ListParser(Lexer input) {
        super(input);
    }
    
    // list : '[' elements ']'
    public void list() throws NoViableAltException, MismatchedTokenException {
        match(ListLexer.LBRACK);
        elements();
        match(ListLexer.RBRACK);
    }

    // elements : element (',' element)*
    void elements() throws NoViableAltException, MismatchedTokenException {
        element();
        while (lookahead.getType() == ListLexer.COMMA) {
            match(ListLexer.COMMA);
            element();
        }
    }

    // element : NAME | list
    void element() throws NoViableAltException, MismatchedTokenException {
        if (lookahead.getType() == ListLexer.NAME ) {
            match(ListLexer.NAME);
        } else if (lookahead.getType() == ListLexer.LBRACK ) {
            list();
        } else {
            throw new NoViableAltException("Expected: NAME or list; Found: " + lookahead);
        }
    }
}