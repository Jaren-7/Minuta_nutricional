package com.example.minuta_nutricional.ui.screens

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.minuta_nutricional.modelos.itemsMenu
import com.example.minuta_nutricional.servicios.AuthSession
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(navController: NavController) {

    val usuarioDatos = AuthSession.usuarioActual
    val nombreUsuario = usuarioDatos?.nombre?: "Usuario Invitado"
    val correoUsuario = usuarioDatos?.correo?: "sin.correo@correo.cl"

    val iniciales = nombreUsuario.split(" ")
        .mapNotNull { it.firstOrNull()?.toString() }
        .take(2)
        .joinToString("")
        .uppercase()

    var menuAbierto by remember {
        mutableStateOf(false)
    }

    var drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    var scope = rememberCoroutineScope()

    val hapticFeedback = LocalHapticFeedback.current


    ModalNavigationDrawer(
        drawerState = drawerState,

        drawerContent = {

            ModalDrawerSheet {
                Surface(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Menu",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(24.dp)
                    )
                }

                NavigationDrawerItem(
                    label = {
                        Text("Cerrar sesion")
                    },

                    selected = false,

                    onClick = {

                        //Alerta Hpatica: Confirmacion fisica de cierre de sesion
                        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)

                        AuthSession.limpiar()

                        scope.launch {
                            drawerState.close()
                        }

                        navController.navigate("login")
                    }
                )
            }
        }
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Pagina principal")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                // Alerta Haptica: Vibración ligera al abrir el menu lateral
                                hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)

                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Text( text = "≡",color = MaterialTheme.colorScheme.onPrimary)
                        }
                    },

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    actions = {
                        IconButton(
                            onClick = {
                                // Alerta Haptica: Confirmacion tactil al tocar los tres puntos
                                hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)

                                menuAbierto = true
                            }
                        ) {
                            Text(text = "⁝", color = MaterialTheme.colorScheme.onPrimary)
                        }

                        DropdownMenu(
                            expanded = menuAbierto,
                            onDismissRequest = {
                                menuAbierto = false
                            }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Mi perfil")},
                                onClick = {
                                    menuAbierto = false
                                }
                            )

                            DropdownMenuItem(
                                text = { Text("Configuracion")},
                                onClick = {
                                    menuAbierto = false
                                }
                            )

                            DropdownMenuItem(
                                text = { Text("Acerca de")},
                                onClick = {
                                    menuAbierto = false
                                }
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier.size(125.dp).clip(CircleShape).background(Color.DarkGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = iniciales,
                        color = Color.White,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Bienvenido $nombreUsuario",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = correoUsuario,
                    style = MaterialTheme.typography.titleMedium,
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = "Revisa y completa minuta semanal",
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.height(16.dp))

                /*
                Button(
                    onClick = { navController.navigate("minuta")},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Ver minuta")
                }

                PanelCard(
                    title = "Ver Minuta",
                    imagen = R.drawable.logo,
                    onClick = {
                        navController.navigate("minuta")
                    }
                )


                 */

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(itemsMenu) { item ->
                        Card(
                            onClick = {
                                // Alerta Haptica: Confirmacion fisica instantanea al presionar cualquier menu
                                hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                                navController.navigate(item.ruta)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        ) {
                            Column (
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(12.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                        Text(
                                            text = item.titulo,
                                            style = MaterialTheme.typography.headlineSmall,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = item.descripcion,
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = Color.DarkGray,
                                            maxLines = 2
                                        )
                                    }
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                        contentDescription = "Ir",
                                        modifier = Modifier.align(Alignment.End),
                                        tint = Color.Gray
                                    )
                                }
                        }

                    }
                }

            }

        }
    }
}
