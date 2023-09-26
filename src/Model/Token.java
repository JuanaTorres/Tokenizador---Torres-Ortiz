package Model;

public class Token {
    public static final int EPSILON = 0;
    public final int token;
    public final String sequence;
    public final int pos;

    public Token(int token, String sequence, int pos) {
        super();
        this.token = token;
        this.sequence = sequence;
        this.pos = pos;
    }
    
    public Token clone(){
        return new Token(this.token, this.sequence, this.pos);
    }

}
