package com.example.minuta_nutricional

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.minuta_nutricional.controlador.viewmodels.InicioViewModel
import com.example.minuta_nutricional.servicios.AuthSession
import com.example.minuta_nutricional.servicios.impl.LoginServiceImpl
import com.example.minuta_nutricional.controlador.viewmodels.LoginViewModel
import com.example.minuta_nutricional.controlador.viewmodels.MinutaViewModel
import com.example.minuta_nutricional.repo.impl.InicioRepositoryImpl
import com.example.minuta_nutricional.repo.impl.MinutaRepositoryImpl
import com.example.minuta_nutricional.repo.impl.UsuarioRepositoryImpl
import com.example.minuta_nutricional.servicios.impl.InicioServiceImpl
import com.example.minuta_nutricional.servicios.impl.MinutaServiceImpl
import com.example.minuta_nutricional.ui.screens.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navegacion() {

    val navController = rememberNavController()

    val context = LocalContext.current

    val loginViewModel = remember {
        val usuarioRepository = UsuarioRepositoryImpl(context)
        val loginService = LoginServiceImpl(usuarioRepository)
        LoginViewModel(loginService)
    }

    val inicioViewModel = remember {
        val inicioRepository = InicioRepositoryImpl(context)
        val inicioService = InicioServiceImpl(inicioRepository)
        InicioViewModel(inicioService)
    }

    val minutaViewModel = remember {
        val minutaRepository = MinutaRepositoryImpl(context)
        val minutaService = MinutaServiceImpl(minutaRepository)
        MinutaViewModel(minutaService)
    }


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
            RegistroScreen(navController, viewModel = loginViewModel)
        }

        composable("recuperar") {
            RecuperarPasswordScreen(navController, viewModel = loginViewModel)
        }

        composable(
            route = "minuta/{tipoComida}",
            arguments = listOf(navArgument("tipoComida") {type = NavType.StringType})
        )
        { backStackEntry ->

            val tipo = backStackEntry.arguments?.getString("tipoComida") ?: ""

            LaunchedEffect(tipo) {
                minutaViewModel.cargarRecetas()
            }

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
                LaunchedEffect(Unit) {
                    inicioViewModel.cargarMenus()
                }

                InicioScreen(navController = navController, viewModel = inicioViewModel)
            }
        }

        composable("crear_receta") {
            NuevaRecetaScreen(navController, viewModel = minutaViewModel)
        }
    }
}
