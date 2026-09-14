package com.example.minuta_nutricional.controlador.viewmodels

import androidx.lifecycle.ViewModel
import com.example.minuta_nutricional.servicios.MinutaTracker

class MinutaViewModel: ViewModel() {

    val recetasRealizadas = MinutaTracker.recetasRealizadas

    fun onRecetaRealizadasChanged(recetaId: String, nuevaRealizada: Boolean) {
        MinutaTracker.actualizarEstado(recetaId, nuevaRealizada)
    }
}