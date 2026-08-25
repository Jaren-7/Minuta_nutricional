package com.example.minuta_nutricional

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.minuta_nutricional.ui.screens.LoginScreen
import com.example.minuta_nutricional.ui.screens.MinutaScreen
import com.example.minuta_nutricional.ui.screens.RegistroScreen
import com.example.minuta_nutricional.ui.screens.RecuperarPasswordScreen
import com.example.minuta_nutricional.ui.screens.InicioScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navegacion() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(navController)
        }

        composable("registro") {
            RegistroScreen(navController)
        }

        composable("recuperar") {
            RecuperarPasswordScreen(navController)
        }

        composable("minuta") {
            MinutaScreen(navController)
        }

        composable("inicio") {
            InicioScreen(navController)
        }
    }
}
