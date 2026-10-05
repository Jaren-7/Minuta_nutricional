package com.example.minuta_nutricional.repo.interfaces

import com.example.minuta_nutricional.modelos.ItemMenu

interface InicioRepository {
    suspend fun obtenerMenus(): List<ItemMenu>
}