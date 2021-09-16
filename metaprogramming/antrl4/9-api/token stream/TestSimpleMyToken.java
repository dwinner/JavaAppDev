import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.IOException;
import java.util.List;

public class TestSimpleMyToken {
	public static void main(String[] args) throws IOException {
		ANTLRInputStream input = new ANTLRFileStream(args[0]);
		SimpleLexer lexer = new SimpleLexer(input);
		MyTokenFactory factory = new MyTokenFactory(input);
		lexer.setTokenFactory(factory);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		// now, print all tokens
		tokens.fill();
		List<Token> alltokens = tokens.getTokens();
		for (Token t : alltokens) {
			System.out.println(t.toString());
		}

		// now parse
		SimpleParser parser = new SimpleParser(tokens);
		parser.setTokenFactory(factory);
		ParseTree t = parser.s();
		System.out.println(t.toStringTree(parser));
	}
}
