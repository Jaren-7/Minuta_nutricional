package com.example.minuta_nutricional.servicios.impl

import com.example.minuta_nutricional.modelos.usuarios
import com.example.minuta_nutricional.servicios.AuthSession
import com.example.minuta_nutricional.servicios.interfaces.LoginService
import com.example.minuta_nutricional.servicios.interfaces.LoginValidationResult

class LoginServiceImpl : LoginService {

    override fun validarCredenciales(usuario: String, password: String): LoginValidationResult {

        if (usuario.isBlank() || password.isBlank()) {
            return LoginValidationResult.Invalid("El usuario y la contraseña no pueden estar vacios")
        }

        val usuarioEncontrado = usuarios.find {
            it.usuario == usuario
        }

        if (usuarioEncontrado == null) {
            return LoginValidationResult.Invalid("El usuario no se encuentra registrado")
        }

        if (usuarioEncontrado.password != password) {
            return LoginValidationResult.Invalid("Contraseña incorrecta")
        }

        AuthSession.usuarioActual = usuarioEncontrado

        return LoginValidationResult.Valid
    }

}