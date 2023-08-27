package Model;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenize {

    private LinkedList<Tokeninfo> tokenInfos;
    private LinkedList<Token> tokens;

    public Tokenize() {
        super();
        tokenInfos = new LinkedList<Tokeninfo>();
        tokens = new LinkedList<Token>();
    }

    public void add(String regex, int token) {
        tokenInfos.add(new Tokeninfo(Pattern.compile("^(" + regex + ")"), token));
    }

    public void tokenize(String str) {
        String s = str.trim();
        int totalLength = s.length();
        tokens.clear();
        while (!s.equals("")) {
            //System.out.println(s);
            int remaining = s.length();
            boolean match = false;
            for (Tokeninfo info : tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;
                    String tok = m.group().trim();
                    s = m.replaceFirst("").trim();
                    tokens.add(new Token(info.token, tok, totalLength - remaining));
                    break;
                }
            }
            if (!match) {
                throw new Lexerexception("Unexpected character in input: " + s);
            }
        }
    }

    public LinkedList<Token> getTokens() {
        return tokens;
    }

}
