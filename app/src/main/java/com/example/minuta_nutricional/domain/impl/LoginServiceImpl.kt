package com.example.minuta_nutricional.domain.impl

import android.media.AudioManager
import android.media.ToneGenerator
import com.example.minuta_nutricional.data.usuarios
import com.example.minuta_nutricional.domain.interfaces.LoginService
import com.example.minuta_nutricional.domain.interfaces.LoginValidationResult

class LoginServiceImpl : LoginService {

    override fun validarCredenciales(usuario: String, password: String): LoginValidationResult {

        val usuarioEncontrado = usuarios.find {
            it.usuario == usuario &&
            it.password == password
        }

        return LoginValidationResult.Valid
    }

}