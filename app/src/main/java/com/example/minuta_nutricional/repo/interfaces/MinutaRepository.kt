package com.example.minuta_nutricional.repo.interfaces

import com.example.minuta_nutricional.modelos.Receta

interface MinutaRepository {
    suspend fun obtenerRecetas(): List<Receta>
    suspend fun crearReceta(receta: Receta): Boolean
    suspend fun eliminarReceta(id:String): Boolean

    suspend fun actualizarReceta(receta: Receta): Boolean
}