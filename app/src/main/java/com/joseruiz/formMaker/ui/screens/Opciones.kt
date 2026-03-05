package com.joseruiz.formMaker.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable


@Preview
@Composable
fun MenuOpciones() {
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
                    Surface(
                        onClick = {},
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(2.dp, Color.Black),
                        color = Color.Transparent
                    )
                    {
                        TextButton(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = Color.Black
                            )
                        ) { Text("Abrir") }
                    }
                    Surface(
                        onClick = { },
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(2.dp, Color.Black),
                        color = Color.Transparent
                    ) {
                        TextButton(
                            onClick = { },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = Color.Black
                            )
                        ) { Text("Guardar") }
                    }
                }
            }

            OutlinedCard {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Archivos de Persistencia", style = MaterialTheme.typography.labelMedium)
                    Surface(
                        onClick = {},
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(2.dp, Color.Black),
                        color = Color.Transparent
                    )
                    {
                        TextButton(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = Color.Black
                            )
                        ) { Text("Abrir") }
                    }
                    Surface(
                        onClick = { },
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(2.dp, Color.Black),
                        color = Color.Transparent
                    ) {
                        TextButton(
                            onClick = { },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = Color.Black
                            )
                        ) { Text("Guardar") }
                    }
                }
            }

            Surface(
                onClick = {},
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(2.dp, Color.Black),
                color = Color.Transparent
            )
            {
                TextButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.Black
                    )
                ) { Text("Finalizar") }
            }
        }
    }
}
