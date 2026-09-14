package com.example.minuta_nutricional.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.minuta_nutricional.ui.components.RecetaCard
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.minuta_nutricional.modelos.recetas
import com.example.minuta_nutricional.controlador.viewmodels.MinutaViewModel
import com.example.minuta_nutricional.modelos.Receta
import com.example.minuta_nutricional.ui.utils.orquestarOperacionSegura

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinutaScreen(navController: NavController,viewModel: MinutaViewModel,tipoComidaFiltrada: String) {

    // Accesibilidad auditiva: motor de vibracion del telefono
    val hapticFeedback = LocalHapticFeedback.current

    val recetasRealizadas by viewModel.recetasRealizadas

    // Estado para capturar cualquier mensaje si falla la gestion de excepciones
    var errorDeCarga by remember { mutableStateOf<String?>(null) }

    // Estado para almacenar de forma persistente la lista resultante
    var recetasFiltradas by remember { mutableStateOf(listOf<Receta>()) }

    // FUNCION DE ORDEN SUPERIOR SE ENVIA UNA LAMBDA
    // Se invoca la funcion inline protectora pasandole un bloque entre llaves {}
    orquestarOperacionSegura(
        onError = {mensaje -> errorDeCarga = mensaje}
    ) {
        // LAMBDA + FILTER SOBRE COLECCIONES = FILTRO Y COLECCION FILTRADA
        recetasFiltradas = recetas.filter { receta ->
            receta.tipoComida.lowercase() == tipoComidaFiltrada.lowercase()
        }
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
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center
        ) {

            // Si el try-catch captura un error, se muestra en la pantalla
            errorDeCarga?.let { msg ->
                Text(text = "⚠ Aviso: $msg", color = Color.Red, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Text(
                text = "Recetas realizadas: ${recetasRealizadas.size} de ${recetas.size}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Button(
                onClick = { navController.popBackStack()},
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Volver")
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            LazyColumn {
                items(recetasFiltradas) { receta ->

                    val realizada = recetasRealizadas.contains(receta.id)

                    Column(modifier = Modifier.fillMaxWidth()) {
                        RecetaCard(
                            receta = receta,
                            realizada = realizada,
                            onRealizadaChange = { nuevaRealizada ->

                                // ALERTA HAPTICA: Emite una vibracion fisica sutil confirmando el toque
                                hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)

                                viewModel.onRecetaRealizadasChanged(receta.id,nuevaRealizada)
                            }
                        )

                    }

                }
            }
        }
    }


}