package com.joseruiz.formMaker.compiler


import java.io.StringReader

public class Analyzer {

    fun Analizar(cadena: String): List<ErrorReport> {
        var listaErrores = ArrayList<ErrorReport>()

        val lexer = Lexer(StringReader(cadena))
        val parser = Parser(lexer)

        try {
            parser.parse()
            listaErrores.addAll(lexer.getLexicalErrors())
            listaErrores.addAll(parser.getSyntaxErrors())

            if (listaErrores.isEmpty()) {
                println("No hay errores")
            } else {
                println("Hay errores")
                return listaErrores
            }

        } catch (e: Exception) {
            listaErrores.add(ErrorReport("", 0, 0, "Desconocido", "Error inesperado"))
            return listaErrores
        }
        return listaErrores

    }

    fun Renderizar(){

    }

}