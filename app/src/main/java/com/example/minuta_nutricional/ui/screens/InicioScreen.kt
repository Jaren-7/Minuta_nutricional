package com.example.minuta_nutricional.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(navController: NavController) {

    var menuAbierto by remember {
        mutableStateOf(false)
    }

    var drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    var scope = rememberCoroutineScope()


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
        ) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = "Inicio",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = "Revisa nuestra minuta nutricional semanal"
                )

                Button(
                    onClick = { navController.navigate("minuta")},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Ver minuta")
                }
            }

        }
    }
}
