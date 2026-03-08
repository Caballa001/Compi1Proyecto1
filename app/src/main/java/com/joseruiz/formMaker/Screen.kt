package com.joseruiz.formMaker

import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
sealed class Screen(val route: String) {
    object Menu : Screen("menu")
    object Editor : Screen("editor")
    object VisualizarForm : Screen("visualizar")
    object ListaDB : Screen("listaDB")
}

