package com.example.minuta_nutricional.servicios.impl

import com.example.minuta_nutricional.modelos.Usuario
import com.example.minuta_nutricional.repo.interfaces.UsuarioRepository
import com.example.minuta_nutricional.servicios.AuthSession
import com.example.minuta_nutricional.servicios.interfaces.LoginService
import com.example.minuta_nutricional.servicios.interfaces.LoginValidationResult
import com.example.minuta_nutricional.servicios.interfaces.RecuperarResult
import com.example.minuta_nutricional.servicios.interfaces.RegistroResult

class LoginServiceImpl(private val usuarioRepository: UsuarioRepository) : LoginService {

    override suspend fun validarCredenciales(usuario: String, password: String): LoginValidationResult {

        if (usuario.isBlank() || password.isBlank()) {
            return LoginValidationResult.Invalid("El usuario y la contraseña no pueden estar vacios")
        }

        val usuarioEncontrado = usuarioRepository.buscarUsuario(usuario)

        if (usuarioEncontrado == null) {
            return LoginValidationResult.Invalid("El usuario no se encuentra registrado")
        }

        if (usuarioEncontrado.password != password) {
            return LoginValidationResult.Invalid("Contraseña incorrecta")
        }

        AuthSession.usuarioActual = usuarioEncontrado

        return LoginValidationResult.Valid
    }

    override suspend fun registrarNuevoUsuario(usuario: Usuario): RegistroResult {

        if (usuario.usuario.isBlank() || usuario.correo.isBlank() || usuario.nombre.isBlank() || usuario.password.isBlank()) {
            return RegistroResult.Error("Todos los campos son obligatorios.")
        }

        val existeUsuario = usuarioRepository.buscarUsuario(usuario.usuario)
        if (existeUsuario != null) {
            return RegistroResult.Error("El nombre de usuario '${usuario.usuario}' ya se encuentra ocupado.")
        }

        val existeCorreo = usuarioRepository.buscarUsuario(usuario.correo)
        if (existeCorreo != null) {
            return RegistroResult.Error("El correo electrónico '${usuario.correo}' ya está asociado a una cuenta activa.")
        }

        val result = usuarioRepository.registrar(usuario)

        return if (result) {
            RegistroResult.Exito
        } else {
            RegistroResult.Error("No se pudo procesar el registro en la base de datos.")
        }
    }

    override suspend fun recuperarPassword(correo: String, nuevaPass: String): RecuperarResult {

        if (correo.isBlank() || nuevaPass.isBlank()) {
            return RecuperarResult.Error("Todos los campos son obligatorios.")
        }

        if (!correo.contains("@") || !correo.contains(".")) {
            return RecuperarResult.Error("Por favor, ingresa un formato de correo válido.")
        }

        val usuarioEncontrado = usuarioRepository.buscarUsuario(correo)

        if (usuarioEncontrado == null) {
            return RecuperarResult.Error("El correo electrónico ingresado no coincide con ninguna cuenta.")
        }

        val result = usuarioRepository.actualizarPassword(usuarioEncontrado.correo, nuevaPass)

        return if (result) {
            RecuperarResult.Exito
        } else {
            RecuperarResult.Error("No se pudo actualizar la contraseña en el almacenamiento local.")
        }
    }

}