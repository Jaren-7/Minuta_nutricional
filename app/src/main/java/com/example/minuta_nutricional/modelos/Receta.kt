package com.example.minuta_nutricional.modelos

import java.util.UUID

data class Receta (
    val id: String = UUID.randomUUID().toString(),
    val dia: String,
    val nombre: String,
    val descripcion: String,
    val ingredientes: String,
    val recomendacionNutricional: String,
    val tipoComida: String,
    val esPersonalizada: Boolean = false
)

data class ItemMenu(
    val titulo: String,
    val descripcion: String,
    val ruta: String
)
