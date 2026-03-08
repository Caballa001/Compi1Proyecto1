package com.joseruiz.formMaker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import com.joseruiz.formMaker.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.joseruiz.formMaker.ui.components.Visualizar
import com.joseruiz.formMaker.R

@Preview
@Composable
fun VisualizarFormPreview() {
    val navController = rememberNavController()
    VisualizarForm(navController)
}


@Composable
fun VisualizarForm(navController: NavController) {

    Scaffold(
        bottomBar = {
            NavigationBar() {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.Menu.route) },
                    icon = { Icon(
                        painter = painterResource(id = R.drawable.arrow_back_24px),
                        contentDescription = "Regresar"
                    ) },
                    label = { Text("Regresar") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* todo */ },
                    icon = { Icon(
                        painter = painterResource(id = R.drawable.send_24px),
                        contentDescription = "Enviar"
                    ) },
                    label = { Text("Enviar")}
                )
            }
            /*BottomAppBar() {
                Row(
                    Modifier.fillMaxWidth(), Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { /* todo */
                        navController.navigate(Screen.Editor.route)
                    }) {
                        Text("Editor")
                    }
                    Button(onClick = { /* todo */ }) {
                        Text("Enviar Respuestas")
                    }
                }
            }*/
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ){
            Visualizar(modifier = Modifier.weight(1f))
        }
    }
}