package com.example.minuta_nutricional.controlador.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.minuta_nutricional.modelos.Receta
import com.example.minuta_nutricional.servicios.MinutaTracker
import com.example.minuta_nutricional.servicios.interfaces.CrearRecetaResult
import com.example.minuta_nutricional.servicios.interfaces.MinutaService

class MinutaViewModel(private val minutaService: MinutaService) {

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage
    val recetasRealizadas = MinutaTracker.recetasRealizadas
    private val _recetas = mutableStateOf<List<Receta>>(emptyList())
    val recetas: State<List<Receta>> = _recetas

    var recetaEditar: Receta? = null

    suspend fun cargarRecetas() {
        try {
            _recetas.value = minutaService.listarRecetas()
        } catch (e: Exception) {
            _recetas.value = emptyList()
        }
    }

    fun onRecetaRealizadasChanged(recetaId: String, nuevaRealizada: Boolean) {
        MinutaTracker.actualizarEstado(recetaId, nuevaRealizada)
    }

    suspend fun guardarReceta(receta: Receta, onSuccessNavigate: () -> Unit) {
        _errorMessage.value = null

        if (recetaEditar != null) {
            val exito = minutaService.modificarReceta(receta)
            if (exito) {
                recetaEditar = null
                onSuccessNavigate()
            } else {
                _errorMessage.value = "No se pudo actualizar la receta en la base de datos."
            }
        }else {
            when (val result = minutaService.crearNuevaReceta(receta)) {
                is CrearRecetaResult.Exito -> {
                    onSuccessNavigate()
                }
                is CrearRecetaResult.Error -> {
                    _errorMessage.value = result.mensaje
                }
            }
        }
    }

    suspend fun borrarReceta(recetaId: String) {
        val exito = minutaService.borrarRecetaPersonalizada(recetaId)
        if (exito) {

            cargarRecetas()
        }
    }


    suspend fun editarReceta(recetaModificada: Receta, onSuccess: () -> Unit) {
        val exito = minutaService.modificarReceta(recetaModificada)
        if (exito) {
            cargarRecetas()
            onSuccess()
        }
    }


}