package com.joseruiz.formMaker

import android.os.Bundle
import android.view.Menu
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.joseruiz.formMaker.ui.screens.MenuOpciones
import com.joseruiz.formMaker.ui.theme.Compi1Proyecto1Theme

import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.joseruiz.formMaker.ui.screens.UIEditor
import com.joseruiz.formMaker.ui.screens.VisualizarForm
import com.joseruiz.formMaker.ui.screens.ListaDB

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.Menu.route) {
                composable(route = Screen.Menu.route) {
                    MenuOpciones(navController = navController)
                }
                composable(route = Screen.Editor.route) {
                    UIEditor(navController = navController)
                }
                composable(route = Screen.VisualizarForm.route) {
                    VisualizarForm(navController = navController)
                }
                composable(route = Screen.ListaDB.route){
                    ListaDB(navController = navController)
                }
            }

        }
    }
}

