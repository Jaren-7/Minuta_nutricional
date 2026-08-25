package com.example.minuta_nutricional

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.minuta_nutricional.data.Planta

val plantas = listOf(
    Planta("Monstera", "Interior", 12990),
    Planta("Lavanda", "Exterior", 6990),
    Planta("Suculenta", "Interior", 3990)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MaterialTheme {
                Navegacion()
            }

        }
    }
}

@Composable
fun ViveroApp() {
    var pantalla by remember { mutableStateOf("inicio") }

    when(pantalla) {
        "inicio" -> PantallaInicio(
            onVerCatalogo = {
                pantalla = "catalogo"
            }
        )
        "catalogo" -> PantallaCatalogo(
            onVolver = {
                pantalla = "inicio"
            }
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInicio(onVerCatalogo: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Vivero Verde")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Vivero Verde",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Encuentra plantas para decorar tu hogar."
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Button(
                onClick = onVerCatalogo,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver catalogo")
            }
        }
    }
}

@Composable
fun PantallaCatalogo(onVolver: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp)
    ) {
        Text(
            text = "Catalogo de plantas",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        plantas.forEach { planta ->
            PlantaCard(planta)
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}

@Composable
fun PlantaCard(planta: Planta) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = planta.nombre,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "Tipo: ${planta.tipo}"
            )

            Text(
                text = "Precio: $${planta.precio}"
            )
        }
    }
}