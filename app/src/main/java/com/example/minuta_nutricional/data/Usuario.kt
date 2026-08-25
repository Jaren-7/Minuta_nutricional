package com.example.minuta_nutricional.data

data class Usuario (
    val usuario: String,
    val correo: String,
    val password: String
)

val usuarios = listOf(

        Usuario(
            usuario = "usuario1",
            correo = "juan@gmail.com",
            password = "1234"
        ),

        Usuario(
            usuario = "usuario2",
            correo = "maria@gmail.com",
            password = "1234"
        ),

        Usuario(
            usuario = "usuario3",
            correo = "pedro@gmail.com",
            password = "1234"
        ),

        Usuario(
            usuario = "usuario4",
            correo = "ana@gmail.com",
            password = "1234"
        ),

        Usuario(
            usuario = "usuario5",
            correo = "carlos@gmail.com",
            password = "1234"
        )
    )