package com.example.minuta_nutricional.controlador.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.minuta_nutricional.modelos.Usuario
import com.example.minuta_nutricional.servicios.interfaces.LoginService
import com.example.minuta_nutricional.servicios.interfaces.LoginValidationResult
import com.example.minuta_nutricional.servicios.interfaces.RecuperarResult
import com.example.minuta_nutricional.servicios.interfaces.RegistroResult

class LoginViewModel(private val loginService: LoginService) {
    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    private val _exitoMessage = mutableStateOf<String?>(null)
    val exitoMessage: State<String?> = _exitoMessage

    var usuarioLogueado: Usuario? = null
        private set

    suspend fun onLoginClicked(usuario: String, password: String, onSuccessNavigate: () -> Unit) {

        _errorMessage.value = null

        when (val result = loginService.validarCredenciales(usuario,password)) {
            is LoginValidationResult.Valid -> {

                onSuccessNavigate()
            }

            is LoginValidationResult.Invalid -> {
                _errorMessage.value = result.errorLogin
            }
        }

    }

    suspend fun onRegistrarClicked(usuario: Usuario, onSuccessNavigate: () -> Unit) {
        _errorMessage.value = null

        when (val result = loginService.registrarNuevoUsuario(usuario)) {
            is RegistroResult.Exito -> {
                onSuccessNavigate()
            }
            is RegistroResult.Error -> {
                _errorMessage.value = result.errorRegistro
            }
        }
    }

    suspend fun onRecuperarClicked(correo: String, nuevaPass: String, onSuccess: () -> Unit) {
        _errorMessage.value = null
        _exitoMessage.value = null

        when (val result = loginService.recuperarPassword(correo, nuevaPass)) {
            is RecuperarResult.Exito -> {
                _exitoMessage.value = "Contraseña actualizada ya puedes iniciar sesión."
                onSuccess() // Callback para alertar a la UI
            }
            is RecuperarResult.Error -> {
                _errorMessage.value = result.errorRecuperar
            }
        }
    }
}