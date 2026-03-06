package com.joseruiz.formMaker.ui.screens

import com.joseruiz.formMaker.Screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController


@Preview
@Composable
fun MenuOpcionesPreview() {
    val navController = rememberNavController()
    MenuOpciones(navController)
}

@Composable
fun MenuOpciones(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                "Menú de Opciones",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )

            OutlinedCard {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Archivos de Código", style = MaterialTheme.typography.labelMedium)

                    TextButton(
                        onClick = {
                            navController.navigate(Screen.Editor.route)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(2.dp, Color.Black),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Black
                        )
                    ) { Text("Nuevo") }

                    TextButton(
                        onClick = {
                            // TODO: Abrir explorador y cargar archivo
                            navController.navigate(Screen.Editor.route)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(2.dp, Color.Black),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Black
                        )
                    ) { Text("Abrir") }

                    TextButton(
                        onClick = {
                            // TODO: Implementar API para guardar archivo
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(2.dp, Color.Black),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Black
                        )
                    ) { Text("Guardar") }

                }
            }

            OutlinedCard {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Archivos de Persistencia", style = MaterialTheme.typography.labelMedium)

                    TextButton(
                        onClick = {
                            // TODO: Implementar API para ver archivos de persistencia y cargar
                            navController.navigate(Screen.VisualizarForm.route)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(2.dp, Color.Black),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Black
                        )
                    ) { Text("Abrir") }

                    TextButton(
                        onClick = {
                            // TODO: implementar metodo para guardar archivo de persistencia y API
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(2.dp, Color.Black),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Black
                        )
                    ) { Text("Guardar") }

                }
            }

            TextButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(2.dp, Color.Black),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.Black
                )
            ) { Text("Finalizar") }

        }
    }
}
