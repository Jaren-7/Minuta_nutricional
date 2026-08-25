package com.example.minuta_nutricional.ui.screens

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.minuta_nutricional.data.usuarios

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LoginScreen(navController: NavController) {

    var usuario by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var mensajeError by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Minuta Nutricional")

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = usuario,
            onValueChange = {
                usuario = it
            },
            label = {
                Text("Usuario")
            },
            modifier = Modifier.fillMaxWidth()
        )

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
            modifier = Modifier.fillMaxWidth()
        )

        if (mensajeError.isNotEmpty()) {
            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = mensajeError,
                color = MaterialTheme.colorScheme.error,
                fontSize = 20.sp
            )
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {

                val usuarioEncontrado = usuarios.find {
                    it.usuario == usuario &&
                    it.password == password
                }

                if (usuarioEncontrado != null) {
                    val tono = ToneGenerator(
                        AudioManager.STREAM_NOTIFICATION,
                        120
                    )

                    tono.startTone(
                        ToneGenerator.TONE_PROP_ACK,
                        300
                    )

                    navController.navigate("inicio")
                } else {
                    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            300,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                    mensajeError = "*Usuario o contraseña incorrectos"
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        TextButton(
            onClick = { navController.navigate("registro")},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Crear una cuenta")
        }

        TextButton(
            onClick = { navController.navigate("recuperar")},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Recuperar contraseña")
        }
    }

}