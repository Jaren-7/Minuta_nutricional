package com.example.minuta_nutricional.controlador.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.minuta_nutricional.modelos.ItemMenu
import com.example.minuta_nutricional.servicios.interfaces.InicioService

class InicioViewModel(private val inicioService: InicioService) {

    private val _menus = mutableStateOf<List<ItemMenu>>(emptyList())
    val menus: State<List<ItemMenu>> = _menus

    suspend fun cargarMenus() {

        try {
            _menus.value = inicioService.obtenerMenus()

        } catch (e: Exception) {
            _menus.value = emptyList()
        }
    }
}
