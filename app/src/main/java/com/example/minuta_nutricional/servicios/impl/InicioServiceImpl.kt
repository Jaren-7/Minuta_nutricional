package com.example.minuta_nutricional.servicios.impl

import com.example.minuta_nutricional.modelos.ItemMenu
import com.example.minuta_nutricional.repo.interfaces.InicioRepository
import com.example.minuta_nutricional.servicios.interfaces.InicioService

class InicioServiceImpl(private val inicioRepository: InicioRepository): InicioService {

    override suspend fun obtenerMenus(): List<ItemMenu> {
        return inicioRepository.obtenerMenus()
    }
}