package parser.multi;

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

public class LAParser extends AbstractLAParser {
    public LAParser(Lexer input, int k) {
        super(input, k);
    }
    
    // list : '[' elements ']'
    public void list() throws MismatchedTokenException, NoViableAltException {
        match(ListLexer.LBRACK);
        elements();
        match(ListLexer.RBRACK);
    }

    // elements : element (',' element)*
    void elements() throws MismatchedTokenException, NoViableAltException {
        element();
        while (LA(1) == ListLexer.COMMA) {
            match(ListLexer.COMMA);
            element();
        }
    }

    // element : NAME '=' NAME | NAME | list
    void element() throws MismatchedTokenException, NoViableAltException {
        if (LA(1) == ListLexer.NAME && LA(2) == ListLexer.EQUALS) {
            match(ListLexer.NAME);
            match(ListLexer.EQUALS);
            match(ListLexer.NAME);
        } else if (LA(1) == ListLexer.NAME) {
            match(ListLexer.NAME);
        } else if (LA(1) == ListLexer.LBRACK) {
            list();
        } else {
            throw new NoViableAltException("Expected: name or list; Found: " + LT(1));
        }
    }
}