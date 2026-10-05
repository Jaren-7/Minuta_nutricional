package com.example.minuta_nutricional.repo.interfaces

import com.example.minuta_nutricional.modelos.Usuario

interface UsuarioRepository {
    suspend fun buscarUsuario(criterio:String): Usuario?
    suspend fun registrar(usuario: Usuario): Boolean
    suspend fun actualizarPassword(correo: String, nuevaPass: String): Boolean
}