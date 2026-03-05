package com.joseruiz.formMaker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import com.joseruiz.formMaker.R



@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun UIEditor() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editor de Formulario") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_menu_24),
                            contentDescription = "Menu"
                        )
                    }
                },
                actions = {
                    IconButton( onClick = { /*TODO*/ }){
                        Icon(
                            painter = painterResource(id = R.drawable.warning_24px),
                            contentDescription = "Errores"
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar() {
                Row(
                    Modifier.fillMaxWidth(), Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { /* todo */ }) {
                        Text("Reemplazar")
                    }
                    Button(onClick = { /* todo */ }) {
                        Text("Añadir")
                    }
                    Button(onClick = { /* todo */ }) {
                        Text("Finalizar")
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize()

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Visualizar(modifier = Modifier.weight(1f))
            HorizontalDivider()
            CodeEditor(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun CodeEditor(modifier: Modifier = Modifier){
    var codeState by remember { mutableStateOf(TextFieldValue("")) }

    val cursorPosition = codeState.selection.start
    val textBeforeCursor = codeState.text.substring(0, cursorPosition)
    val filaCodigo = textBeforeCursor.count { it == '\n' } + 1
    val columnaCodigo = cursorPosition - textBeforeCursor.lastIndexOf('\n')


    OutlinedCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier

                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Editor de Código",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    "Fila: $filaCodigo Columna: $columnaCodigo",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }


            TextField(
                value = codeState,
                onValueChange = { codeState = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                placeholder = {
                    Text(
                        text = "/* Codigo aqui */",
                        color = Color.Gray,
                        style = TextStyle(fontFamily = FontFamily.Monospace)
                    )
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    color = Color.White
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF1E1E1E),
                    unfocusedContainerColor = Color(0xFF1E1E1E),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}
