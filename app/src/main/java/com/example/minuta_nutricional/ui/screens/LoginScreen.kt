package com.example.minuta_nutricional.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minuta_nutricional.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import com.example.minuta_nutricional.controlador.viewmodels.LoginViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit,
    onNavigateToRegistro: () -> Unit,
    onNavigateToRecuperar: () -> Unit

) {

    var usuario by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val mensajeError by viewModel.errorMessage

    // Administrador de vibracion haptica
    val hapticFeedback = LocalHapticFeedback.current

    // Alerta Haptica: Si hay un error, el telefono vibra de inmediato
    LaunchedEffect(mensajeError) {
        if (mensajeError != null) {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
       Image(
           painter = painterResource(id = R.drawable.logo),
           contentDescription = "Logo Minuta Nutricional",
           modifier = Modifier.size(200.dp)
       )

        Text(text = "Minuta Nutricional", style = MaterialTheme.typography.titleLarge,fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        // Alerta Visual: isError si falla el login el contorno cambia a rojo
        OutlinedTextField(
            value = usuario,
            onValueChange = {
                usuario = it
            },
            label = {
                Text("Usuario")
            },
            isError = mensajeError != null,
            modifier = Modifier.fillMaxWidth()
        )

        // Alerta Visual: isError tambien en la contraseña
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text("Contraseña")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            isError = mensajeError != null,
            modifier = Modifier.fillMaxWidth()
        )

        // Alerta Visual y de Icono
        mensajeError?.let { error ->
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "⚠", fontSize = 32.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {
                viewModel.onLoginClicked(usuario,password, onSuccessNavigate = onLoginSuccess)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        TextButton(
            onClick =  onNavigateToRegistro,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Crear una cuenta")
        }

        TextButton(
            onClick = onNavigateToRecuperar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Recuperar contraseña")
        }
    }

}