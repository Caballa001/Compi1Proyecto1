package com.joseruiz.formMaker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme

@Preview
@Composable
fun Visualizar(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),

        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedCard() {
            Text("Elementos de formulario", style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(16.dp))
            OutlinedCard() {
                Text("Aqui van los elementos generados", modifier = Modifier.padding(16.dp))

                // TODO: Llamar componente con elementos generados
            }
        }
    }
}
