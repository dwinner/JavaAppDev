import org.antlr.v4.runtime.*;

public class TestBail {
    public static class BailSimpleLexer extends SimpleLexer {
        public BailSimpleLexer(CharStream input) { super(input); }
        public void recover(LexerNoViableAltException e) {
            throw new RuntimeException(e); // Bail out
        }
    }

    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        BailSimpleLexer lexer = new BailSimpleLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleParser parser = new SimpleParser(tokens);
        parser.setErrorHandler(new BailErrorStrategy());
        parser.prog();
    }
}
