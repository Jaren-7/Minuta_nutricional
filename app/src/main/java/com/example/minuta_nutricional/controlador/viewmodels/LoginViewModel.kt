package com.example.minuta_nutricional.controlador.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.minuta_nutricional.modelos.Usuario
import com.example.minuta_nutricional.servicios.interfaces.LoginService
import com.example.minuta_nutricional.servicios.interfaces.LoginValidationResult

class LoginViewModel(private  val loginService: LoginService) {
    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    var usuarioLogueado: Usuario? = null
        private set

    fun onLoginClicked(usuario: String, password: String, onSuccessNavigate: () -> Unit) {

        _errorMessage.value = null

        when (val result = loginService.validarCredenciales(usuario,password)) {
            is LoginValidationResult.Valid -> {

                onSuccessNavigate()
            }

            is LoginValidationResult.Invalid -> {
                _errorMessage.value = result.errorMessage
            }
        }
    }
}