import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

/** A Token that tracks the TokenSource name in each token. */
public class MyToken extends CommonToken {

    public String srcName;

    public MyToken(int type, String text) {
        super(type, text);
    }

    public MyToken(Pair<TokenSource, CharStream> source, int type, int channel, int start, int stop) {
        super(source, type, channel, start, stop);
    }

    @Override
    public String toString() {
        String t = super.toString();
        return srcName + ":" + t;
    }
}
