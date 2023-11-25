package Model;

import java.util.regex.Pattern;

public class Tokeninfo {

    public final Pattern regex;
    public final int token;

    public Tokeninfo(Pattern regex, int token) {
        super();
        this.regex = regex;
        this.token = token;
    } 
}
