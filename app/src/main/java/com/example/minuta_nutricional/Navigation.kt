package com.example.minuta_nutricional

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.minuta_nutricional.servicios.AuthSession
import com.example.minuta_nutricional.servicios.impl.LoginServiceImpl
import com.example.minuta_nutricional.controlador.viewmodels.LoginViewModel
import com.example.minuta_nutricional.controlador.viewmodels.MinutaViewModel
import com.example.minuta_nutricional.ui.screens.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navegacion() {

    val navController = rememberNavController()

    val loginService = LoginServiceImpl()
    val loginViewModel = remember { LoginViewModel(loginService) }

    val minutaViewModel = remember { MinutaViewModel() }

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        composable("splash") {
            SplashScreen(navController)
        }

        composable("login") {
            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = {
                    navController.navigate("inicio") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegistro = {
                    navController.navigate("registro")
                },
                onNavigateToRecuperar= {
                    navController.navigate("recuperar")
                }
            )
        }

        composable("registro") {
            RegistroScreen(navController)
        }

        composable("recuperar") {
            RecuperarPasswordScreen(navController)
        }

        composable(
            route = "minuta/{tipoComida}",
            arguments = listOf(navArgument("tipoComida") {type = NavType.StringType})
        )
        { backStackEntry ->

            val tipo = backStackEntry.arguments?.getString("tipoComida") ?: ""

            MinutaScreen(
                navController,
                tipoComidaFiltrada = tipo,
                viewModel = minutaViewModel,
                )
        }

        composable("inicio") {
            if (!AuthSession.estaActivo()) {
                LaunchedEffect(Unit) {
                    navController.navigate("login") {
                        popUpTo("inicio") { inclusive = true }
                    }
                }
            } else {

                InicioScreen(navController)
            }
        }
    }
}
