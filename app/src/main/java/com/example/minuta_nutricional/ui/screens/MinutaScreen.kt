package com.example.minuta_nutricional.ui.screens

import android.annotation.SuppressLint
import android.widget.RadioButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.minuta_nutricional.ui.components.RecetaCard
import com.example.minuta_nutricional.data.Receta
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinutaScreen(navController: NavController) {

    val recetas = listOf(
        Receta(
            dia = "Lunes",
            nombre = "Pollo con arroz",
            descripcion = "Pollo a la plancha acompañado de arroz y ensalada.",
            recomendacionNutricional = "Incluir verduras variadas y preferir agua como bebida."
        ),
        Receta(
            dia = "Martes",
            nombre = "Lentejas con verduras",
            descripcion = "Lentejas acompañadas de verduras frescas.",
            recomendacionNutricional = "Las legumbres aportan proteínas y fibra."
        ),
        Receta(
            dia = "Miércoles",
            nombre = "Pescado al horno",
            descripcion = "Pescado al horno acompañado de papas y ensalada.",
            recomendacionNutricional = "Preferir preparaciones al horno y acompañar con verduras."
        ),
        Receta(
            dia = "Jueves",
            nombre = "Ensalada con pollo",
            descripcion = "Ensalada variada con pollo a la plancha.",
            recomendacionNutricional = "Incorporar diferentes tipos de verduras."
        ),
        Receta(
            dia = "Viernes",
            nombre = "Tortilla de verduras",
            descripcion = "Tortilla preparada con verduras variadas.",
            recomendacionNutricional = "Acompañar con una porción de verduras frescas."
        )
    )

    var recetasRealizadas by remember {
        mutableStateOf(setOf<String>())
    }

    var tipoComida by remember {
        mutableStateOf("Almuerzo")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Minuta Nutricional Semanal")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(
                modifier = Modifier.height(100.dp)
            )

            Text(
                text = "Recetas realizadas: ${recetasRealizadas.size} de ${recetas.size}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            TextButton(
                onClick = { navController.popBackStack()},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver")
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Tipo de comida",
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = tipoComida == "Desayuno",
                    onClick = {
                        tipoComida = "Desayuno"
                    }
                )

                Text("Desayuno")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = tipoComida == "Almuerzo",
                    onClick = {
                        tipoComida = "Almuerzo"
                    }
                )

                Text("Almuerzo")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = tipoComida == "Cena",
                    onClick = {
                        tipoComida = "Cena"
                    }
                )

                Text("Cena")
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            LazyColumn {
                items(recetas) { receta ->

                    val realizada = recetasRealizadas.contains(receta.dia)

                    RecetaCard(
                        receta = receta,
                        realizada = realizada,
                        onRealizadaChange = { nuevaRealizada ->
                            recetasRealizadas =
                                if (nuevaRealizada) {
                                    recetasRealizadas + receta.dia
                                } else {
                                    recetasRealizadas - receta.dia
                                }
                        }
                    )

                }
            }
        }
    }


}