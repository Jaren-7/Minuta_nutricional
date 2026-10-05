package com.example.minuta_nutricional.repo.impl

import android.content.Context
import com.example.minuta_nutricional.modelos.ItemMenu
import com.example.minuta_nutricional.repo.DbContext
import com.example.minuta_nutricional.repo.interfaces.InicioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InicioRepositoryImpl(context: Context) : InicioRepository {

    private val dbContext = DbContext(context)

    override suspend fun obtenerMenus(): List<ItemMenu> = withContext(Dispatchers.IO) {
        val db = dbContext.readableDatabase
        val listaResultados = mutableListOf<ItemMenu>()

        val query = "SELECT titulo, descripcion, ruta FROM menus"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            listaResultados.add(
                ItemMenu(
                    titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                    descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
                    ruta = cursor.getString(cursor.getColumnIndexOrThrow("ruta"))
                )
            )
        }
        cursor.close()

        return@withContext listaResultados
    }
}

