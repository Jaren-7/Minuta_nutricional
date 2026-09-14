package com.example.minuta_nutricional.ui.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.minuta_nutricional.modelos.Receta
import com.example.minuta_nutricional.ui.utils.separarIngredientes
import com.example.minuta_nutricional.ui.utils.tieneIngredientesValidos

@Composable
fun RecetaCard(receta: Receta, realizada: Boolean, onRealizadaChange: (Boolean) -> Unit) {

    // Accesibilidad Visual: Se determina el color de la tarjeta dependiendo del estado en que se encuentre
    val colorTarjeta = if (realizada) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    // Tambien cambia el color del texto interno para mantener un contraste adecuado
    val colorTextoContenido = if (realizada) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorTarjeta
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = receta.dia,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineLarge,
                color = colorTextoContenido
            )
            Text(
                text = receta.nombre,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                color = colorTextoContenido
            )
            Text(
                text = receta.descripcion,
                style = MaterialTheme.typography.bodyLarge,
                color = colorTextoContenido
            )
            Spacer(modifier = Modifier.height(15.dp ))
            Text(
                text = receta.recomendacionNutricional,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.border(width = 2.dp, color = Color.DarkGray, shape = RectangleShape).padding(12.dp),
                color = colorTextoContenido
            )

            Spacer(modifier = Modifier.height(8.dp))

            // PROPIEDAD DE EXTENSION: Se evalua si hay texto en la propiedad ingredientes
            if (receta.tieneIngredientesValidos) {
                Spacer(modifier = Modifier.height(8.dp))

                // FUNCION DE EXTENSION: Separa el string por ";" y se obtiene una lista de string
                val listaDesglosada = receta.ingredientes.separarIngredientes()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = if (realizada) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Ingredientes:",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = colorTextoContenido
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    listaDesglosada.forEach { ingrediente ->
                        Text(
                            text = "👉 $ingrediente",
                            style = MaterialTheme.typography.bodyLarge,
                            color = colorTextoContenido
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = realizada,
                    onCheckedChange = onRealizadaChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary,
                        uncheckedColor = colorTextoContenido
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (realizada) "Receta realizada" else "Marcar como realizada",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (realizada) FontWeight.Bold else FontWeight.Normal,
                    color = colorTextoContenido

                )
            }
        }
    }
}