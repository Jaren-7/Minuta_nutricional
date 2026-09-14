package com.example.minuta_nutricional.modelos

data class Usuario (
    val usuario: String,
    val nombre: String,
    val correo: String,
    val password: String
)

val usuarios = listOf(

        Usuario(
            usuario = "usuario1",
            nombre = "Sebastian Lobos",
            correo = "seba@gmail.com",
            password = "1234"
        ),

        Usuario(
            usuario = "usuario2",
            nombre = "Maria Diaz",
            correo = "maria@gmail.com",
            password = "1234"
        ),

        Usuario(
            usuario = "usuario3",
            nombre = "Pedro Lopez",
            correo = "pedro@gmail.com",
            password = "1234"
        ),

        Usuario(
            usuario = "usuario4",
            nombre = "Ana Muñoz",
            correo = "ana@gmail.com",
            password = "1234"
        ),

        Usuario(
            usuario = "usuario5",
            nombre = "Carlos Basaez",
            correo = "carlos@gmail.com",
            password = "1234"
        )
    )