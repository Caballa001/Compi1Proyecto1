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

HexCode = \#[0-9a-fA-F]{6}
RGBNumber = [0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]
RGBCode = \({RGBNumber},{RGBNumber},{RGBNumber}\)
HSLNumber = [0-9]|[1-9][0-9]|[12][0-9]{2}|3[0-5][0-9]|360
HSLNumber2 = [0-9]|[1-9][0-9]|100
HSLCode = \<{HSLNumber},{HSLNumber2},{HSLNumber2}\>

paramColor = \"color\"
paramBackground = \"background color\"
paramFont = \"font family\"
paramSize = \"text size\"
paramBorder = \"border\"

Integer = [0-9]+
Float = [0-9]+ \. [0-9]+

Smile = @\[:[\)]+\] | @\[:smile:\]
Sad = @\[:[\(]+\] | @\[:sad:\]
Serious = @\[:[\|]+\] | @\[:serious:\]
Heart = @\[[<]+[3]+\] | @\[:heart:\]
StarSimple = @\[:star:\]
StarCols = @\[:star:[0-9]+:\]
StarDash = @\[:star-[0-9]+:\]
Cat = @\[:cat:\] | @\[:\^\^:\]

Number = {Float} | {Integer}

lineComment = "$"[^\\n]*
blockComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
Comment = {lineComment} | {blockComment}

pokeApi = "who_is_that_pokemon"
idVar = [a-zA-Z_][a-zA-Z0-9_]*

%{
    /****************** codigo de usuario ***********************/

        StringBuffer string;
        ArrayList<Integer> RGBvalues = new ArrayList<>();
        ArrayList<Float> HSLvalues = new ArrayList<>();

        /* Codigo para devolver valores utiles al parser */

        private ArrayList<Integer> extractRBGvalues(String rgbText){
            RGBvalues.clear();
            String[] values = rgbText.substring(1, rgbText.length() - 1).split(",");
            for (String value : values) {
                RGBvalues.add(Integer.parseInt(value.trim()));
            }
            return new ArrayList<>(RGBvalues);
        }

        private ArrayList<Float> extractHSLvalues(String hslText){
            HSLvalues.clear();
            String[] values = hslText.substring(1, hslText.length() - 1).split(",");
            for (int i = 0; i < values.length; i++) {
                float val  = Float.parseFloat(values[i].trim());
                if (i == 0) {
                    HSLvalues.add(val % 360);
                } else {
                    float porcentaje = Math.max(0, Math.min(100, val)) / 100f;
                    HSLvalues.add(porcentaje);
                }
            }
            return new ArrayList<>(HSLvalues) ;
        }

        private String extractStars(String starText){
           String cleaned = starText.replaceAll("[^0-9]", "");
           Integer number =  Integer.parseInt(cleaned);
           String result = "";
           for (int i = 0; i < number; i++) {
                result += "\u2B50";
           }
            return result;
        }

        /* Codigo para el manejo de errores */

        private List<ErrorReport> errorList;

        public List<ErrorReport> getLexicalErrors(){
            return this.errorList;
        }

        /*-----------------------------------------------
              Codigo para el parser
        -------------------------------------------------*/
        private Symbol symbol(int type, Object valor){
            return new Symbol(type, yyline+1, yycolumn+1, valor);
        }

        private void error(String message){
            /* Lexema | linea | columna | tipo | desc */
             errorList.add(new ErrorReport(message, yyline+1, yycolumn+1, "Lexico", "Simbolo no reconocido"));
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

    "number"            { return symbol(sym.TYPENUMBER); }
    "string"            { return symbol(sym.TYPESTRING); }
    "special"           { return symbol(sym.TYPESPECIAL); }
    "="                { return symbol(sym.ASSIGN); }

    "{"                 { return symbol(sym.LBRACE); }
    "}"                 { return symbol(sym.RBRACE); }
    "["                 { return symbol(sym.LBRACKET); }
    "]"                 { return symbol(sym.RBRACKET); }
    ":"                 { return symbol(sym.COLON); }
    ";"                 { return symbol(sym.SEMICOLON); }
    ","                 { return symbol(sym.COMMA); }
    "."                 { return symbol(sym.DOT); }
    "?"                 { return symbol(sym.COMODIN); }

    "SECTION"           { return symbol(sym.SECTION); }
    "width"              { return symbol(sym.WIDTH); }
    "height"            { return symbol(sym.HEIGHT); }
    "label"             { return symbol(sym.LABEL); }
    "options"           { return symbol(sym.OPTIONS); }
    "content"           { return symbol(sym.CONTENT); }
    "pointX"            { return symbol(sym.POINTX); }
    "pointY"            { return symbol(sym.POINTY); }
    "orientation"       { return symbol(sym.ORIENTATION); }
    "VERTICAL"          { return symbol(sym.VERTICAL); }
    "HORIZONTAL"        { return symbol(sym.HORIZONTAL); }
    "elements"            { return symbol(sym.ELEMENTS); }
    "styles"              { return symbol(sym.STYLES); }
    "TABLE"               { return symbol(sym.TABLE); }
    "TEXT"                { return symbol(sym.TEXT); }
    "OPEN_QUESTION"         { return symbol(sym.OPEN_QUESTION); }
    "DROP_QUESTION"         { return symbol(sym.DROP_QUESTION); }
    "correct"             { return symbol(sym.CORRECT); }
    "SELECT_QUESTION"       { return symbol(sym.SELECT_QUESTION); }
    "MULTIPLE_QUESTION"     { return symbol(sym.MULTIPLE_QUESTION); }
    "draw"               { return symbol(sym.DRAW); }

    "IF"                { return symbol(sym.IF); }
    "ELSE"              { return symbol(sym.ELSE); }
    "WHILE"             { return symbol(sym.WHILE); }
    "DO"                { return symbol(sym.DO); }
    "FOR"               { return symbol(sym.FOR); }
    "in"                { return symbol(sym.IN); }
    "..."               { return symbol(sym.RANGE); }

    // Color cases
    "RED"               { return symbol(sym.RED); }
    "BLUE"              { return symbol(sym.BLUE); }
    "GREEN"             { return symbol(sym.GREEN); }
    "PURPLE"            { return symbol(sym.PURPLE); }
    "SKY"               { return symbol(sym.SKY); }
    "YELLOW"            { return symbol(sym.YELLOW); }
    "BLACK"             { return symbol(sym.BLACK); }
    "WHITE"             { return symbol(sym.WHITE); }

    {HexCode}          { return symbol(sym.HEX_COLOR, yytext()); }
    {RGBCode}          { return symbol(sym.RGB_COLOR, extractRBGvalues(yytext())); }
    {HSLCode}          { return symbol(sym.HSL_COLOR, extractHSLvalues(yytext())); }

    {Number}            {
                        String numText = yytext();
                        if (numText.contains(".")) {
                            return symbol(sym.NUMBER, Double.parseDouble(numText));
                         } else {
                            return symbol(sym.NUMBER, Integer.parseInt(numText));
                         }
                        }

    {paramColor}        { return symbol(sym.PARAMCOLOR); }
    {paramBackground}   { return symbol(sym.PARAMBACKGROUND); }
    {paramFont}         { return symbol(sym.PARAMFONT); }
    {paramSize}         { return symbol(sym.PARAMSIZE); }
    {paramBorder}       { return symbol(sym.PARAMBORDER); }

    "MONO"              { return symbol(sym.MONO); }
    "CURSIVE"           { return symbol(sym.CURSIVE); }
    "SANS_SERIF"        { return symbol(sym.SANS_SERIF); }

    "LINE"              { return symbol(sym.LINE); }
    "DOTTED"            { return symbol(sym.DOTTED); }
    "DOUBLE"            { return symbol(sym.DOUBLE); }

    "who_is_that_pokemon" { return symbol(sym.POKEAPI); }

    {idVar}             { return symbol(sym.ID, yytext()); }
    {Comment}           {}

    \"                  { string.setLength(0); yybegin(STRING); }

    {WhiteSpace}        { /* ignorar */ }

}

<STRING> {
    \"                  {
                            yybegin(YYINITIAL);
                            return symbol(sym.STRCHAIN, string.toString());

                        }

    {Smile}             { string.append( "\uD83D\uDE00" ); }
    {Sad}               { string.append( "\uD83D\uDE22"); }
    {Serious}           { string.append( "\uD83D\uDE10"); }
    {Heart}             { string.append( "\u2764\uFE0F" ); }
    {StarSimple}        { string.append( "\u2B50" ); }
    {StarCols}          { string.append( extractStars(yytext()) ); }
    {StarDash}          { string.append( extractStars(yytext()) ); }
    {Cat}               { string.append( "\uD83D\uDC31" ); }

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
