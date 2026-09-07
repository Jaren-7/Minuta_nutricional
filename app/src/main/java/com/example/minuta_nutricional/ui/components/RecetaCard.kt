package com.example.minuta_nutricional.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.minuta_nutricional.data.Receta

@Composable
fun RecetaCard(receta: Receta, realizada: Boolean, onRealizadaChange: (Boolean) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = receta.dia,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = receta.nombre,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = receta.descripcion,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = receta.recomendacionNutricional,
                style = MaterialTheme.typography.bodyMedium
            )

            Checkbox(
                checked = realizada,
                onCheckedChange = onRealizadaChange
            )
        }
    }
}