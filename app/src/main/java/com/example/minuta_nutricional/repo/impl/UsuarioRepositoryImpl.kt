package com.example.minuta_nutricional.repo.impl

import android.content.Context
import com.example.minuta_nutricional.modelos.Usuario
import com.example.minuta_nutricional.repo.DbContext
import com.example.minuta_nutricional.repo.interfaces.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsuarioRepositoryImpl(context: Context): UsuarioRepository {
    private val dbContext = DbContext(context)

    override suspend fun buscarUsuario(criterio: String): Usuario? = withContext(Dispatchers.IO){
        val db = dbContext.readableDatabase
        var resultado: Usuario? = null

        val query = "SELECT correo, usuario, nombre, password FROM usuarios WHERE LOWER(usuario) = LOWER(?) OR LOWER(correo) = LOWER(?)"
        val cursor = db.rawQuery(query, arrayOf(criterio,criterio))

        if (cursor.moveToFirst()) {
            resultado = Usuario(
                correo = cursor.getString(cursor.getColumnIndexOrThrow("correo")),
                usuario = cursor.getString(cursor.getColumnIndexOrThrow("usuario")),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                password = cursor.getString(cursor.getColumnIndexOrThrow("password"))
            )
        }
        cursor.close()
        return@withContext resultado
    }

    override suspend fun registrar(usuario: Usuario): Boolean = withContext(Dispatchers.IO){
        val db = dbContext.writableDatabase
        val values = android.content.ContentValues().apply {
            put("correo",usuario.correo)
            put("usuario",usuario.usuario)
            put("nombre",usuario.nombre)
            put("password",usuario.password)
        }

        val resultado = db.insert("usuarios", null,values)
        return@withContext resultado != -1L
    }

    override suspend fun actualizarPassword(correo: String, nuevaPass: String): Boolean = withContext(Dispatchers.IO) {
        val db = dbContext.writableDatabase
        val values = android.content.ContentValues().apply {
            put("password", nuevaPass)
        }
        val filasAfectadas = db.update("usuarios", values, "LOWER(correo) = LOWER(?)", arrayOf(correo))
        return@withContext filasAfectadas > 0
    }
}