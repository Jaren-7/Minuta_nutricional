package com.example.minuta_nutricional.servicios.interfaces

import com.example.minuta_nutricional.modelos.Receta

sealed class CrearRecetaResult {
    object Exito: CrearRecetaResult()
    data class Error(val mensaje: String) : CrearRecetaResult()
}

interface MinutaService {
    suspend fun listarRecetas(): List<Receta>

    suspend fun crearNuevaReceta(receta: Receta): CrearRecetaResult
    suspend fun borrarRecetaPersonalizada(id: String): Boolean

    suspend fun modificarReceta(receta: Receta): Boolean
}