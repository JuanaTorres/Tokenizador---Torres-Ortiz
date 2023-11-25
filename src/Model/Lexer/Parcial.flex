import java_cup.runtime.*;

%%
%class Lexer

%line
%column

%cup

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

Model_Name = "Model_Name"
Model_Type = "Model_Type"
FreeText = [^\n\r\t\f#]+
NUMBER = "NUMBER"
CATEGORICAL = "CATEGORICAL"
BINARY = "BINARY"

Inputs = "Inputs"
Input = "Input"
Input_Name = "Input_Name"
Input_Type = "Input_Type"

Outputs = "Outputs"
Output = "Output"
Output_Name = "Output_Name"
Output_Type = "Output_Type"

Model = "Model"
Layers = "Layers"
Layer = "Layer"
Layer_Name = "Layer_Name"
Layer_Params = "Layer_Params"

OpenBrace = "\{"
CloseBrace = "\}"
OpenPlaneBrace = "\["
ClosePlaneBrace = "\]"
TwoPoints = :
Comma = ,


%%

{Model_Name} { System.out.print(yytext()); return symbol(sym.Model_Name, yytext()); }
{Model_Type} { System.out.print(yytext()); return symbol(sym.Model_Type, yytext()); }
{FreeText} { System.out.print(yytext()); return symbol(sym.FreeText, yytext()); }
{NUMBER} { System.out.print(yytext()); return symbol(sym.NUMBER, yytext()); }
{CATEGORICAL} { System.out.print(yytext()); return symbol(sym.CATEGORICAL, yytext()); }
{BINARY} { System.out.print(yytext()); return symbol(sym.BINARY, yytext()); }

{Inputs} { System.out.print(yytext()); return symbol(sym.Inputs, yytext()); }
{Input_Name} { System.out.print(yytext()); return symbol(sym.Input_Name, yytext()); }
{Input_Type} { System.out.print(yytext()); return symbol(sym.Input_Type, yytext()); }

{Outputs} { System.out.print(yytext()); return symbol(sym.Outputs, yytext()); }
{Output_Name} { System.out.print(yytext()); return symbol(sym.Output_Name, yytext()); }
{Output_Type} { System.out.print(yytext()); return symbol(sym.Output_Type, yytext()); }

{Model} { System.out.print(yytext()); return symbol(sym.Model, yytext()); }
{Layers} { System.out.print(yytext()); return symbol(sym.Layers, yytext()); }
{Layer_Name} { System.out.print(yytext()); return symbol(sym.Layer_Name, yytext()); }
{Layer_Params} { System.out.print(yytext()); return symbol(sym.Layer_Params, yytext()); }

{OpenBrace} { System.out.print(yytext()); return symbol(sym.OpenBrace, yytext()); }
{CloseBrace} { System.out.print(yytext()); return symbol(sym.CloseBrace, yytext()); }
{OpenPlaneBrace} { System.out.print(yytext()); return symbol(sym.OpenPlaneBrace, yytext()); }
{ClosePlaneBrace} { System.out.print(yytext()); return symbol(sym.ClosePlaneBrace, yytext()); }
{TwoPoints} { System.out.print(yytext()); return symbol(sym.TwoPoints, yytext()); }
{Comma} { System.out.print(yytext()); return symbol(sym.Comma, yytext()); }

. { throw new Error("Illegal character <" + yytext() + ">"); }
