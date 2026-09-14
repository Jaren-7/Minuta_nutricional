package com.example.minuta_nutricional.servicios

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

object MinutaTracker {

    private val _recetaRealizadas = mutableStateOf(setOf<String>())
    val recetasRealizadas: State<Set<String>> = _recetaRealizadas

    fun actualizarEstado(recetaId: String, realizada: Boolean) {
        _recetaRealizadas.value = if (realizada) {
            _recetaRealizadas.value + recetaId
        } else {
            _recetaRealizadas.value - recetaId
        }
    }

}