package Model.parser;

import Model.ParserException;
import Model.Token;


import java.util.LinkedList;
import java.util.List;

public class Parser {

    private LinkedList<Token> tokens;
    private Token lookahead;
    private String resultado;

    public String parse(List<Token> tokens) {
        resultado="";
        this.tokens = new LinkedList<>();
        for(int i = 0; i<tokens.size(); i++){
            this.tokens.add(tokens.get(i).clone());
        }
                
        lookahead = this.tokens.getFirst();

        ruleFile();

        if (lookahead.token != Token.EPSILON) {
            throw new ParserException("Unexpected symbol " + lookahead.sequence + "  found");
        }
        return resultado;
    }

    private void nextToken() {
        resultado+="\n"+("Proximo: " + tokens.getFirst().sequence);
        tokens.pop();

        if (tokens.isEmpty()) {
            lookahead = new Token(Token.EPSILON, "", -1);
        } else {
            lookahead = tokens.getFirst();
        }
    }

    private void ruleFile() {
        rules();
    }

    private void rules() {
        rule();
        a();
    }

    private void rule() {
        if (lookahead.token == 1) {//IF
            nextToken();
            if(lookahead.token == 4) {//[
                nextToken();
                expression();
                if (lookahead.token == 5) {
                    nextToken();//]
                    if (lookahead.token==6){//THEN
                        nextToken();
                        actions();
                    }else{
                        throw new ParserException("no hay THEN "
                                + lookahead.sequence + " found instead");
                    }
                }else{
                    throw new ParserException("Se espera] "
                            + lookahead.sequence + " found instead");
                }
            }else {
                throw new ParserException("se espera[ "
                        + lookahead.sequence + " found instead");
            }
        } else {
        }
    }

    private void a() {
        if(lookahead.token != Token.EPSILON){
            rules();
        }else{
        }
    }

    private void actions() {
        action();
        b();
    }

    private void b() {
        if (lookahead.token == 14){
            actions();
        }else{
        }
    }

    private void action() {
        if (lookahead.token == 14){
            nextToken();
            if(lookahead.token==2){
                nextToken();
                parameters();
                if(lookahead.token!=3){
                    throw new ParserException("2. Closing brackets expected and "
                            + lookahead.sequence + " found instead");
                }
                nextToken();
            }
        }else{
        }
    }

    private void parameters() {
        if(lookahead.token==13){
            nextToken();
            c();
        }
    }

    private void c() {
        if(lookahead.token==11){
            nextToken();
            parameters();
        }else{

        }
    }

    private void expression() {
        term();
        ep();
    }

    private void ep(){
        if(lookahead.token==16){
            nextToken();
            term();
            ep();
        }else{

        }
    }
    private void term(){
        factor();
        tp();
    }
    private void tp(){
        if(lookahead.token==15){
            nextToken();
            factor();
            tp();
        }else {

        }
    }
    private void factor(){
        if(lookahead.token==12){
            fp();
        }else{
        fp();
        }
    }
    private void fp(){
        if(lookahead.token==2){
            nextToken();
            expression();
            if(lookahead.token!=3){
                throw new ParserException("2. Closing brackets expected and "
                        + lookahead.sequence + " found instead");
            }nextToken();
        }else {
            simpleExp();
        }
    }
    private void simpleExp(){
        if(lookahead.token==14){
            nextToken();
            if(lookahead.token==7||lookahead.token==8||lookahead.token==9
                    ||lookahead.token==10){
                nextToken();
                if(lookahead.token==13){
                    nextToken();
                }else{
                    throw new ParserException("necesita un numero "
                            + lookahead.sequence + " found instead");
                }
            }else{
                throw new ParserException("no hay comparator "
                        + lookahead.sequence + " found instead");
            }
        }else {
            throw new ParserException("2. no hay nombre "
                    + lookahead.sequence + " found instead");
        }
    }
}


