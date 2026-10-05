package com.example.minuta_nutricional.servicios.impl

import com.example.minuta_nutricional.modelos.Receta
import com.example.minuta_nutricional.repo.interfaces.MinutaRepository
import com.example.minuta_nutricional.servicios.interfaces.CrearRecetaResult
import com.example.minuta_nutricional.servicios.interfaces.MinutaService

class MinutaServiceImpl(private val minutaRepository: MinutaRepository): MinutaService {

    override suspend fun listarRecetas(): List<Receta> {
        return minutaRepository.obtenerRecetas()
    }

    override suspend fun crearNuevaReceta(receta: Receta): CrearRecetaResult {

        if (receta.nombre.isBlank() || receta.descripcion.isBlank() ||
            receta.recomendacionNutricional.isBlank() || receta.ingredientes.isBlank()) {
            return CrearRecetaResult.Error("Todos los campos obligatorios deben estar completos.")
        }

        val result = minutaRepository.crearReceta(receta)

        return if (result) {
            CrearRecetaResult.Exito
        } else {
            CrearRecetaResult.Error("No se pudo escribir la receta en el almacenamiento local.")
        }
    }

    override suspend fun borrarRecetaPersonalizada(id: String): Boolean {
        return minutaRepository.eliminarReceta(id)
    }

    override suspend fun modificarReceta(receta: Receta): Boolean {
        if (receta.nombre.isBlank() || receta.ingredientes.isBlank()) return false
        return minutaRepository.actualizarReceta(receta)
    }

}