package com.example.minuta_nutricional.repo.impl

import android.content.Context
import com.example.minuta_nutricional.modelos.Receta
import com.example.minuta_nutricional.repo.DbContext
import com.example.minuta_nutricional.repo.interfaces.MinutaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MinutaRepositoryImpl(context: Context): MinutaRepository {
    private val dbContext = DbContext(context)

    override suspend fun obtenerRecetas(): List<Receta> = withContext(Dispatchers.IO){
        val db = dbContext.readableDatabase
        val listaResultados = mutableListOf<Receta>()

        val query = "SELECT id,dia,nombre,descripcion,recomendacionNutricional,tipoComida,ingredientes,esPersonalizada FROM recetas"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val esPersonalizadaInt = cursor.getInt(cursor.getColumnIndexOrThrow("esPersonalizada"))
            listaResultados.add(
                Receta(
                    id = cursor.getString(cursor.getColumnIndexOrThrow("id")),
                    dia = cursor.getString(cursor.getColumnIndexOrThrow("dia")),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
                    recomendacionNutricional = cursor.getString(cursor.getColumnIndexOrThrow("recomendacionNutricional")),
                    tipoComida = cursor.getString(cursor.getColumnIndexOrThrow("tipoComida")),
                    ingredientes = cursor.getString(cursor.getColumnIndexOrThrow("ingredientes")),
                    esPersonalizada = (esPersonalizadaInt == 1)
                )
            )
        }
        cursor.close()
        return@withContext listaResultados
    }

    override suspend fun crearReceta(receta: Receta): Boolean = withContext(Dispatchers.IO) {
        val db = dbContext.writableDatabase
        val values = android.content.ContentValues().apply {
            put("id", receta.id)
            put("dia", receta.dia)
            put("nombre", receta.nombre)
            put("descripcion", receta.descripcion)
            put("recomendacionNutricional", receta.recomendacionNutricional)
            put("tipoComida", receta.tipoComida)
            put("ingredientes", receta.ingredientes)
            put("esPersonalizada", if (receta.esPersonalizada) 1 else 0) // 👈 Conversión a entero para SQLite
        }
        val resultado = db.insert("recetas", null, values)
        return@withContext resultado != -1L
    }

    override suspend fun eliminarReceta(id: String): Boolean = withContext(Dispatchers.IO) {
        val db = dbContext.writableDatabase
        val filasAfectadas = db.delete("recetas", "id = ?", arrayOf(id))
        return@withContext filasAfectadas > 0
    }

    override suspend fun actualizarReceta(receta: Receta): Boolean = withContext(Dispatchers.IO) {
        val db = dbContext.writableDatabase
        val values = android.content.ContentValues().apply {
            put("dia", receta.dia)
            put("nombre", receta.nombre)
            put("descripcion", receta.descripcion)
            put("recomendacionNutricional", receta.recomendacionNutricional)
            put("tipoComida", receta.tipoComida)
            put("ingredientes", receta.ingredientes)
        }

        val filasAfectadas = db.update("recetas", values, "id = ?", arrayOf(receta.id))
        return@withContext filasAfectadas > 0
    }

}