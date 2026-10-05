package com.example.minuta_nutricional.servicios.interfaces

import com.example.minuta_nutricional.modelos.Usuario

sealed class LoginValidationResult {
    object Valid : LoginValidationResult()

    data class Invalid(val errorLogin: String) : LoginValidationResult()
}

sealed class RegistroResult {
    object Exito : RegistroResult()
    data class Error(val errorRegistro: String) : RegistroResult()
}

sealed class RecuperarResult {
    object Exito: RecuperarResult()
    data class Error(val errorRecuperar: String): RecuperarResult()
}

interface LoginService {

    suspend fun validarCredenciales(usuario: String,password: String) : LoginValidationResult

    suspend fun registrarNuevoUsuario(usuario: Usuario): RegistroResult

    suspend fun recuperarPassword(correo: String, nuevaPass: String): RecuperarResult
}