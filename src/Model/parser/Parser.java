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

        document();

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

    private void document() {
        modelName();
        modelFamily();
        modelType();
        modelDescription();
        inputs();
        outputs();
        model();
    }

    private void modelName() {
        if (lookahead.token == 7){//Model_Name
            nextToken();
            if(lookahead.token==1){//:
                nextToken();
                if(lookahead.token==36){
                    nextToken();
                }else{
                    throw new ParserException("Falta free text, "
                            + lookahead.sequence + " se encontro");
                }
            }else{
                throw new ParserException("Se espera :, "
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Model_Name, "
                    + lookahead.sequence + " se encontro");
        }
    }
    private void modelFamily() {
        if (lookahead.token == 8){//Model_Family
            nextToken();
            if(lookahead.token==1){//:
                nextToken();
                modelFamilyName();
            }else{
                throw new ParserException("Se espera :, "
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Model_Family, "
                    + lookahead.sequence + " se encontro");
        }
    }
    private void modelFamilyName() {
        if (lookahead.token==24||lookahead.token==25||lookahead.token==26){
            nextToken();
        }else{
            throw new ParserException("Falta CLASSIFIER o REGRESSION o CLUSTERING, "
                    + lookahead.sequence + " se encontro");
        }
    }
    private void modelType() {
        if (lookahead.token == 9){//Model_Type
            nextToken();
            if(lookahead.token==1){//:
                nextToken();
                modelTypeName();
            }else{
                throw new ParserException("Se espera :, "
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Model_Type, "
                    + lookahead.sequence + " se encontro");
        }
    }private void modelTypeName() {
        if(lookahead.token!=27&&lookahead.token!=28&&lookahead.token!=29&&lookahead.token!=30&&lookahead.token!=31){
            throw new ParserException("Falta ANN, Tree, RandomForrest, SVM o DecisionTree "
                    + lookahead.sequence + " se encontro");
        }else{
            nextToken();
        }
    }
    private void modelDescription() {
        if (lookahead.token == 10){//Model_Type
            nextToken();
            if(lookahead.token==1){//:
                nextToken();
                if(lookahead.token==36){
                    nextToken();
                }else{
                    throw new ParserException("Falta free text, "
                            + lookahead.sequence + " se encontro");
                }
            }else{
                throw new ParserException("Se espera :"
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Model_Type, "
                    + lookahead.sequence + " se encontro");
        }
    }private void inputs() {
        if (lookahead.token == 11){//Inputs
            nextToken();
            if(lookahead.token==2){//{
                nextToken();
                inputsList();
                if(lookahead.token!=3){
                    throw new ParserException("Se espera }, "
                            + lookahead.sequence + " se encontro");
                }
                nextToken();
            }else{
                throw new ParserException("Se espera {, "
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Inputs, "
                    + lookahead.sequence + " se encontro");
        }
    }private void inputsList() {
        input();
        a();
    }private void a() {
        if(lookahead.token == 12){
            input();
            a();
        }else{
        }
    }private void input() {
        if (lookahead.token == 12){//Input
            nextToken();
            if(lookahead.token==2){//{
                nextToken();
                inputName();
                inputType();
                if(lookahead.token!=3){
                    throw new ParserException("Se espera }, "
                            + lookahead.sequence + " se encontro");
                }nextToken();
            }else{
                throw new ParserException("Se espera {,"
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Input, "
                    + lookahead.sequence + " se encontro");
        }
    }private void inputName() {
        if (lookahead.token == 13){//Input_Name
            nextToken();
            if(lookahead.token==1){//:
                nextToken();
                if(lookahead.token==36){
                    nextToken();
                }else{
                    throw new ParserException("Falta free text, "
                            + lookahead.sequence + " se encontro");
                }
            }else{
                throw new ParserException("Se espera :, "
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Input_Name, "
                    + lookahead.sequence + " se encontro");
        }
    }private void inputType() {
        if (lookahead.token == 14){//Input_Type
            nextToken();
            if(lookahead.token==1){//:
                nextToken();
                putType();
            }else{
                throw new ParserException("Se espera :, "
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Input_Name, "
                    + lookahead.sequence + " se encontro");
        }
    }private void outputs() {
        if (lookahead.token == 15){//Outputs
            nextToken();
            if(lookahead.token==2){//{
                nextToken();
                outputsList();
                if(lookahead.token!=3){
                    throw new ParserException("Se espera }, "
                            + lookahead.sequence + " se encontro");
                }nextToken();
            }else{
                throw new ParserException("Se espera {, "
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Outputs, "
                    + lookahead.sequence + " se encontro");
        }
    }private void outputsList() {
        output();
        b();
    }
    private void b() {
        if(lookahead.token == 16){
            output();
            b();
        }else{
        }
    }
    private void output() {
        if (lookahead.token == 16){//Output
            nextToken();
            if(lookahead.token==2){//{
                nextToken();
                outputName();
                outputType();
                if(lookahead.token!=3){
                    throw new ParserException("Se espera },"
                            + lookahead.sequence + " se encontro");
                }nextToken();
            }else{
                throw new ParserException("Se espera {,"
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Input, "
                    + lookahead.sequence + " se encontro");
        }
    }
    private void outputName() {
        if (lookahead.token == 17){//Output_Name
            nextToken();
            if(lookahead.token==1){//:
                nextToken();
                if(lookahead.token==36){
                    nextToken();
                }else{
                    throw new ParserException("Falta free text, "
                            + lookahead.sequence + " se encontro");
                }
            }else{
                throw new ParserException("Se espera :,"
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Input_Name,  "
                    + lookahead.sequence + " se encontro");
        }
    }private void outputType() {
        if (lookahead.token == 18){//Output_Type
            nextToken();
            if(lookahead.token==1){//:
                nextToken();
                putType();
            }else{
                throw new ParserException("Se espera :,"
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Input_Name, "
                    + lookahead.sequence + " se encontro");
        }
    }private void model() {
        if (lookahead.token == 19){//Model
            nextToken();
            if(lookahead.token==2){//{
                nextToken();
                multipleLayers();
                if(lookahead.token!=3){
                    throw new ParserException("Se espera },"
                            + lookahead.sequence + " se encontro");
                }nextToken();
            }else{
                throw new ParserException("Se espera {, "
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Model, "
                    + lookahead.sequence + " se encontro");
        }
    }
    private void multipleLayers(){
        layers();
        m();
    }
    private void m(){
        if(lookahead.token == 20){//Layers
            layers();
            c();
        }else{
        }
    }
    private void layers(){
        if (lookahead.token == 20){//Layers
            nextToken();
            if(lookahead.token==2){//{
                nextToken();
                listLayers();
                if(lookahead.token!=3){
                    throw new ParserException("Se espera }, "
                            + lookahead.sequence + " se encontro");
                }nextToken();
            }else{
                throw new ParserException("Se espera {, "
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Layers, "
                    + lookahead.sequence + " se encontro");
        }
    }
    private void listLayers() {
        layer();
        c();
    }
    private void c() {
        if(lookahead.token == 21){
            layer();
            c();
        }else{
        }
    }
    private void layer() {
        if (lookahead.token == 21){//Layer
            nextToken();
            if(lookahead.token==2){//{
                nextToken();
                layerName();
                layerParams();
                if(lookahead.token!=3){
                    throw new ParserException("Se espera }, "
                            + lookahead.sequence + " se encontro");
                }nextToken();
            }else{
                throw new ParserException("Se espera {,"
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Layer, "
                    + lookahead.sequence + " se encontro");
        }
    }
    private void layerName() {
        if (lookahead.token == 22){//Layer_Name
            nextToken();
            if(lookahead.token==1){//:
                nextToken();
                if(lookahead.token==36){
                    nextToken();
                }else{
                    throw new ParserException("Falta free text,  "
                            + lookahead.sequence + " se encontro");
                }
            }else{
                throw new ParserException("Se espera :, "
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Layer_Name, "
                    + lookahead.sequence + " se encontro");
        }
    }
    private void layerParams() {
        if (lookahead.token == 23){//Layer_Params
            nextToken();
            if(lookahead.token==1){//:
                nextToken();
                if(lookahead.token==4){//[
                    nextToken();
                    paramNumbers();
                    if(lookahead.token!=5){//]
                        throw new ParserException("Se espera ], "
                                + lookahead.sequence + " se encontro");
                    }nextToken();
                } else{
                    throw new ParserException("Se espera ], "
                            + lookahead.sequence + " se encontro");
                }
            }else{
                throw new ParserException("Se espera :, "
                        + lookahead.sequence + " se encontro");
            }

        }else{
            throw new ParserException("Se espera el termino Layer_Params, "
                    + lookahead.sequence + " se encontro");
        }
    }
    private void paramNumbers() {
        if (lookahead.token == 35) {//numero
            nextToken();
            if (lookahead.token == 6){//,
                nextToken();
                d();
            }else{
                throw new ParserException("Se espera una , :"
                        + lookahead.sequence + " se encontro");
            }
        }else{
            throw new ParserException("Se espera un numero, "
                    + lookahead.sequence + " se encontro");
        }
    }
    private void d() {
        if(lookahead.token == 35){
            if (lookahead.token == 35) {//numero
                nextToken();
                e();
            }else{
                throw new ParserException("Se espera un numero, "
                        + lookahead.sequence + " se encontro");
            }
        }else{
        }
    }
    private void e() {
        if(lookahead.token == 6){
                if (lookahead.token == 6){//,
                    nextToken();
                    d();
                }else{
                    throw new ParserException("Se espera una , "
                            + lookahead.sequence + " se encontro");
                }
        }else{
        }
    }
    private void putType() {
        if(lookahead.token !=32 &&lookahead.token !=33&&lookahead.token !=34){
            throw new ParserException("Se espera el termino NUMBER o CATEGORICAL o BINARY, "
                    + lookahead.sequence + " se encontro");
        }else{
            nextToken();
        }
    }
}


