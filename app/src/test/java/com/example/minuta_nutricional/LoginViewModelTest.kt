package com.example.minuta_nutricional.controlador.viewmodels

import com.example.minuta_nutricional.servicios.interfaces.LoginService
import com.example.minuta_nutricional.servicios.interfaces.LoginValidationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private lateinit var sut: LoginViewModel
    private lateinit var loginServiceMock: LoginService

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        loginServiceMock = mock(LoginService::class.java)

        sut = LoginViewModel(loginServiceMock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun al_iniciar_sesion_con_datos_correctos_debe_gatillar_el_callback_de_navegacion() = runTest {

        // ARRANGE
        val usuarioValido = "slobos"
        val claveValida = "admin"
        var navegacionGatillada = false

        `when`(loginServiceMock.validarCredenciales(usuarioValido, claveValida))
            .thenReturn(LoginValidationResult.Valid)

        // ACT
        sut.onLoginClicked(usuarioValido, claveValida, onSuccessNavigate = {
            navegacionGatillada = true
        })

        testDispatcher.scheduler.advanceUntilIdle()

        // ASSERT
        assertEquals(true, navegacionGatillada)
        assertNull(sut.errorMessage.value)
        verify(loginServiceMock).validarCredenciales(usuarioValido, claveValida)
        println("TEST EXITOSO: El LoginViewModel procesó las credenciales correctas y permitió la navegación de forma asíncrona.")

    }

    @Test
    fun al_iniciar_sesion_con_clave_incorrecta_debe_poblar_la_propiedad_de_error_para_la_UI() = runTest {

        // ARRANGE
        val usuario = "slobos"
        val claveErronea = "clave_falsa"
        val mensajeEsperado = "Contraseña incorrecta."

        `when`(loginServiceMock.validarCredenciales(usuario, claveErronea))
            .thenReturn(LoginValidationResult.Invalid(mensajeEsperado))

        // ACT
        sut.onLoginClicked(usuario, claveErronea, onSuccessNavigate = { })

        testDispatcher.scheduler.advanceUntilIdle()

        // ASSERT
        assertEquals(mensajeEsperado, sut.errorMessage.value)

        println("TEST EXITOSO: El LoginViewModel bloqueó el acceso e inyectó el mensaje de error de accesibilidad visual correctamente.")
    }
}
