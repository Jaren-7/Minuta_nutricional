package com.example.minuta_nutricional.ui.utils

import com.example.minuta_nutricional.modelos.Receta


// FUNCION DE EXTENSION: Toma el string de ingredientes y lo separa por ";" devolviendo una lista de string
fun String.separarIngredientes(): List<String> {
    if (this.isBlank())
        return emptyList()

    return this.split(";").map { it.trim() }.filter { it.isNotEmpty() }
}

// PROPIEDAD DE EXTENSION: Nos dice de forma rapida si una receta tiene ingredientes validos
val Receta.tieneIngredientesValidos: Boolean
    get() = this.ingredientes.isNotBlank() && this.ingredientes.contains(";")

// FUNCION DE ORDEN SUPERIOR + INLINE + GESTION DE EXCEPCIONES
// Intenta ejecutar un bloque de codigo visual; si falla por datos corruptos, lo gestiona sin colapsar la app
inline fun orquestarOperacionSegura(onError: (String) -> Unit, bloqueLogica: ()-> Unit) {
    try {
        bloqueLogica()
    } catch (e: Exception) {
        onError(e.message?: "Error desconocido al procesar recetas")
    }
}