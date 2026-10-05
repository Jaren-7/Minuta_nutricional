package com.example.minuta_nutricional.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.minuta_nutricional.controlador.viewmodels.MinutaViewModel
import com.example.minuta_nutricional.modelos.Receta
import kotlinx.coroutines.launch
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevaRecetaScreen(
    navController: NavController,
    viewModel: MinutaViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var recomendacion by remember { mutableStateOf("") }
    var ingredientesTexto by remember { mutableStateOf("") }
    var diaSeleccionado by remember { mutableStateOf("Lunes") }
    var tipoComidaSeleccionado by remember { mutableStateOf("almuerzo") }

    val mensajeError by viewModel.errorMessage

    val scope = rememberCoroutineScope()
    val hapticFeedback = LocalHapticFeedback.current

    LaunchedEffect(Unit) {
        viewModel.recetaEditar?.let { receta ->
            nombre = receta.nombre
            descripcion = receta.descripcion
            recomendacion = receta.recomendacionNutricional
            ingredientesTexto = receta.ingredientes
            diaSeleccionado = receta.dia
            tipoComidaSeleccionado = receta.tipoComida
        }
    }

    LaunchedEffect(mensajeError) {
        if (mensajeError != null) {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = if (viewModel.recetaEditar != null) "Editar Receta Personalizada" else "Nueva Receta Personalizada", fontWeight = FontWeight.Bold) },
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
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre de la preparación") },
                isError = mensajeError != null && nombre.isBlank(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción o preparación") },
                isError = mensajeError != null && descripcion.isBlank(),
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = ingredientesTexto,
                onValueChange = { ingredientesTexto = it },
                label = { Text("Ingredientes (Separados por punto y coma ';')") },
                placeholder = { Text("Ej: Arroz;Palta;Tomate;Pollo") },
                isError = mensajeError != null && ingredientesTexto.isBlank(),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Recuerda usar ';' entre cada ingrediente para desglosarlo en la lista.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = recomendacion,
                onValueChange = { recomendacion = it },
                label = { Text("Sugerencia o recomendación nutricional") },
                isError = mensajeError != null && recomendacion.isBlank(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = diaSeleccionado,
                onValueChange = { diaSeleccionado = it },
                label = { Text("Día de la semana (Ej: Lunes)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = tipoComidaSeleccionado,
                onValueChange = { tipoComidaSeleccionado = it },
                label = { Text("Tipo de comida (desayuno / almuerzo / cena)") },
                modifier = Modifier.fillMaxWidth()
            )

            mensajeError?.let { error ->
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "⚠️", fontSize = 22.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)

                    scope.launch {
                        val idFinal = viewModel.recetaEditar?.id ?: UUID.randomUUID().toString()

                        val recetaNueva = Receta(
                            id = idFinal,
                            dia = diaSeleccionado.trim(),
                            nombre = nombre.trim(),
                            descripcion = descripcion.trim(),
                            recomendacionNutricional = recomendacion.trim(),
                            tipoComida = tipoComidaSeleccionado.trim().lowercase(),
                            ingredientes = ingredientesTexto.trim(),
                            esPersonalizada = true
                        )

                        viewModel.guardarReceta(recetaNueva) {
                            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                            navController.popBackStack()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = if (viewModel.recetaEditar != null) "Actualizar Receta" else "Guardar Receta")
            }

            TextButton(
                onClick = {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    viewModel.recetaEditar = null
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancelar")
            }
        }
    }
}
