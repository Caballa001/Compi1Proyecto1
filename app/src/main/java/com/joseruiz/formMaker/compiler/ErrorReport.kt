package com.joseruiz.formMaker.compiler

data class ErrorReport(
    var lexema: String,
    var linea: Int,
    var columna: Int,
    var tipo: String,
    var descripcion: String
)