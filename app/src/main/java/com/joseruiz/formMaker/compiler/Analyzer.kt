package com.joseruiz.formMaker.compiler

import java.io.BufferedReader
import java.io.FileReader

public class Analyzer {

    fun Analizar(cadena: String): List<ErrorReport> {
        var listaErrores = ArrayList<ErrorReport>()
        val reader = BufferedReader(FileReader(cadena))

        val lexer = Lexer(reader)
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

}