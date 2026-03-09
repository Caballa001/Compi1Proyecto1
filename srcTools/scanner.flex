/********************** paquetes y otros ***********************/
package com.joseruiz.formMaker.compiler;

import java_cup.runtime.*;
import java.util.*;

%% //separador de area

/********************* declaraciones de jflex ******************/
%public
%unicode
%class Lexer
%cup
%line
%column
%ignorecase


/********************** estados *********************************/
%state STRING

%init{
    /****************** dentro del constructor ******************/
    errorList = new ArrayList<>();
    string = new StringBuffer();

%init}

/********************** macros ***********************************/

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

HexDigit = \#[0-9a-fA-F]{6}
RGBDigit = \([0-9]{1,2}|1[0-9]{1,2}|2[0-4][0-9]|25[0-5],[0-9]{1,2}|1[0-9]{1,2}|2[0-4][0-9]|25[0-5],[0-9]{1,2}|1[0-9]{1,2}|2[0-4][0-9]|25[0-5]\)
HSLDigit = \<[0-9]{1,2}|100,[0-9]{1,2}|100,[0-9]{1,2}|100\>
ColorSpelled = "RED" | "BLUE" | "GREEN" | "PURPLE" | "SKY" | "YELLOW" | "BLACK" | "WHITE"
Color = {HexDigit} | {RGBDigit} | {HSLDigit} | {ColorSpelled}


//TODO : Revisar donde van, en macros o en tokens
paramColor = \"color\"
paramBackground = \"background color\"
paramFont = \"font family\"
paramSize = \"text size\"
paramBorder = \"border\"

Integer = [0-9]+
Float = [0-9]+ \. [0-9]+
Number = {Float} | {Integer}

Smile = @\[:[\)]+\] | @\[:smile:\]
Sad = @\[:[\(]+\] | @\[:sad:\]
Serious = @\[:[\|]+\] | @\[:serious:\]
Heart = @\[[<]+[3]+\] | @\[:heart:\]
Star = @\[:star:\] | @\[:star:{Integer}:\] | @\[:star-{Integer}:\]
Cat = @\[:cat:\] | @\[:\^\^:\]
Emote = {Smile} | {Sad} | {Serious} | {Heart} | {Star} | {Cat}



%{
    /****************** codigo de usuario ***********************/

    StringBuffer string;

    /* Codigo para el manejo de errores */

    private List<String> errorList;

    public List<String> getLexicalErrors(){
        return this.errorList;
    }

    /*-----------------------------------------------
          Codigo para el parser
    -------------------------------------------------*/
    private Symbol symbol(int type){
        return new Symbol(type, yyline+1, yycolumn+1);
    }

    private void error(String message){
        /* Lexema | linea | columna | tipo | desc */
         errorList.add(message, yyline+1, yycolumn+1, "Lexico", "Simbolo no reconocido");
    }

%}


%% //separador de area

/* Reglas Lexicas */

<YYINITIAL> {

    "+"                 { return symbol(sym.PLUS); }
    "-"                 { return symbol(sym.MINUS); }
    "*"                 { return symbol(sym.TIMES); }
    "/"                 { return symbol(sym.DIVIDE); }
    "^"                 { return symbol(sym.POWER); }
    "%"                 { return symbol(sym.MOD); }
    "("                 { return symbol(sym.LPAREN); }
    ")"                 { return symbol(sym.RPAREN); }

    ">"                 { return symbol(sym.GT); }
    "<"                 { return symbol(sym.LT); }
    ">="                { return symbol(sym.GTE); }
    "<="                { return symbol(sym.LTE); }
    "=="                { return symbol(sym.EQ); }
    "!!"                { return symbol(sym.NEQ); }

    "||"                { return symbol(sym.OR); }
    "&&"                { return symbol(sym.AND); }
    "~"                 { return symbol(sym.NOT); }

    "number"            { return symbol(sym.NUMBER); }
    "string"            { return symbol(sym.STRING); }
    "special"           { return symbol(sym.SPECIAL); }
    "="                { return symbol(sym.ASSIGN); }

    "{"                 { return symbol(sym.LBRACE); }
    "}"                 { return symbol(sym.RBRACE); }
    "["                 { return symbol(sym.LBRACKET); }
    "]"                 { return symbol(sym.RBRACKET); }
    ":"                 { return symbol(sym.COLON); }
    ","                 { return symbol(sym.COMMA); }
    "?"                 { return symbol(sym.COMODIN); }
    "$"                 { return symbol(sym.LCOMMENT); }
    "/*"                { return symbol(sym.BCOMMENTL); }
    "*/"                { return symbol(sym.BCOMMENTR); }

    "SECTION"           { return symbol(sym.SECTION); }
    "width"              { return symbol(sym.WIDTH); }
    "height"            { return symbol(sym.HEIGHT); }
    "pointX"            { return symbol(sym.POINTX); }
    "pointY"            { return symbol(sym.POINTY); }
    "orientation"       { return symbol(sym.ORIENTATION); }
    "VERTICAL"          { return symbol(sym.VERTICAL); }
    "HORIZONTAL"        { return symbol(sym.HORIZONTAL); }
    "elements"            { return symbol(sym.ELEMENTS); }
    "styles"              { return symbol(sym.STYLES); }


    \"                  { string.setLength(0); yybegin(STRING); }

    {WhiteSpace}        { /* ignorar */ }

}

<STRING> {
    \"                  {
                            yybegin(YYINITIAL);
                            return symbol(sym.COMPLEMENT);

                        }
    [^\n\r\"\\]+        { string.append( yytext() ); }
    \\t                 { string.append('\t'); }
    \\n                 { string.append('\n'); }

    \\r                 { string.append('\r'); }
    \\\"                { string.append('\"'); }
    \\                  { string.append('\\'); }
}

.          {  error(yytext()); }

<<EOF>>    {
                return symbol(sym.EOF);
           }

