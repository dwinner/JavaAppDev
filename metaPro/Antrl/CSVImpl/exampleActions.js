"use strict"

var vesrion = 123;

function doesItBlend() {
    return true;
}

(function () {
    // GrammarProfile interface
    var directiveDot = true;
    var directiveDotOptional = false;

    return ({
        evaluateLexerPredicate: (lexer, ruleIndex, actionIndex, predicate) => eval(predicate),
        evaluateParserPredicate: (parser, ruleIndex, actionIndex, predicate) => eval(predicate)
    })
})();