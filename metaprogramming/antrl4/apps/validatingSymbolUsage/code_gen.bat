antlr4 Cymbol.g4 -package com.appdev.grammar
javac Cymbol*.java CheckSymbols.java *Phase.java *Scope.java *Symbol.java
java CheckSymbols vars.cymbol
