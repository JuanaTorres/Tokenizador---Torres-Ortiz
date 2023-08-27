package Model;

public class Token {
    
    public final int token;
    public final String lexeme;
    public final int pos;

    public Token(int token, String sequence, int pos) {
        super();
        this.token = token;
        this.lexeme = sequence;
        this.pos = pos;
    }
    
    public Token clone(){
        return new Token(this.token, this.lexeme, this.pos);
    }

}
