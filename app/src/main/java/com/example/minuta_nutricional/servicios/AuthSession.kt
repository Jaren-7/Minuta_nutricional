package com.example.minuta_nutricional.servicios

import com.example.minuta_nutricional.modelos.Usuario

object AuthSession {
    var usuarioActual: Usuario? = null

    fun estaActivo(): Boolean = usuarioActual != null

    fun limpiar() {
        usuarioActual = null
    }
}