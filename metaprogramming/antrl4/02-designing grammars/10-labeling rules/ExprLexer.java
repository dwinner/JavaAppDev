// Generated from D:\Projects\others\javaAppDev\metaprogramming\antrl4\10-Labeling rule\Expr.g4 by ANTLR 4.x
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

public class ExprLexer extends Lexer {
	public static final int
		MULT=1, ADD=2, INT=3, WS=4;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"MULT", "ADD", "INT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'*'", "'+'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "MULT", "ADD", "INT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override
	@NotNull
	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ExprLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN);
	}

	@Override
	public String getGrammarFileName() { return "Expr.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	public static final String _serializedATN =
		"\3\uaf6f\u8320\u479d\ub75c\u4880\u1605\u191c\uab37\2\6\33\b\1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\3\3\3\3\4\6\4\21\n\4\r\4\16\4\22\3"+
		"\5\6\5\26\n\5\r\5\16\5\27\3\5\3\5\2\2\2\6\3\2\3\5\2\4\7\2\5\t\2\6\3\2"+
		"\4\3\2\62;\4\2\13\f\"\"\34\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\3\13\3\2\2\2\5\r\3\2\2\2\7\20\3\2\2\2\t\25\3\2\2\2\13\f\7,\2\2\f"+
		"\4\3\2\2\2\r\16\7-\2\2\16\6\3\2\2\2\17\21\t\2\2\2\20\17\3\2\2\2\21\22"+
		"\3\2\2\2\22\20\3\2\2\2\22\23\3\2\2\2\23\b\3\2\2\2\24\26\t\3\2\2\25\24"+
		"\3\2\2\2\26\27\3\2\2\2\27\25\3\2\2\2\27\30\3\2\2\2\30\31\3\2\2\2\31\32"+
		"\b\5\2\2\32\n\3\2\2\2\5\2\22\27\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
	}
}