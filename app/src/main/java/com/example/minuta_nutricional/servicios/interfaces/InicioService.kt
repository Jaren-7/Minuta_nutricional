package com.example.minuta_nutricional.servicios.interfaces

import com.example.minuta_nutricional.modelos.ItemMenu

interface InicioService {
    suspend fun obtenerMenus(): List<ItemMenu>
}