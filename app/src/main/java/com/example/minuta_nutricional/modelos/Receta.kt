package com.example.minuta_nutricional.modelos

import java.util.UUID

data class Receta (
    val id: String = UUID.randomUUID().toString(),
    val dia: String,
    val nombre: String,
    val descripcion: String,
    val ingredientes: String,
    val recomendacionNutricional: String,
    val tipoComida: String
)

data class ItemMenu(
    val titulo: String,
    val descripcion: String,
    val ruta: String
)

val itemsMenu = listOf(
    ItemMenu("Desayuno","Recetas y opciones para comenzar el dia","minuta/desayuno"),
    ItemMenu("Almuerzo","Recetas y opciones para tu almuerzo","minuta/almuerzo"),
    ItemMenu("Cena","Recetas y opciones para terminar el dia","minuta/cena")
)

val recetas = listOf(
    Receta(
        dia = "Lunes",
        nombre = "Pollo con arroz",
        descripcion = "Pollo a la plancha acompañado de arroz y ensalada.",
        ingredientes = "Pechuga de pollo;Arroz;Lechuga;Tomate;Aceite;Sal",
        recomendacionNutricional = "Incluir verduras variadas y preferir agua como bebida.",
        tipoComida = "almuerzo"
    ),
    Receta(
        dia = "Martes",
        nombre = "Lentejas con verduras",
        descripcion = "Lentejas acompañadas de verduras frescas.",
        ingredientes = "Lentejas;Zanahoria;Zapallo;Cebolla;Pimentón;Caldo de verduras;Sal",
        recomendacionNutricional = "Las legumbres aportan proteínas y fibra.",
        tipoComida = "almuerzo"
    ),
    Receta(
        dia = "Miércoles",
        nombre = "Pescado al horno",
        descripcion = "Pescado al horno acompañado de papas y ensalada.",
        ingredientes = "Filete de pescado;Papas;Cebolla;Limón;Aceite de oliva;Ensalada surtida",
        recomendacionNutricional = "Preferir preparaciones al horno y acompañar con verduras.",
        tipoComida = "almuerzo"
    ),
    Receta(
        dia = "Jueves",
        nombre = "Ensalada con pollo",
        descripcion = "Ensalada variada con pollo a la plancha.",
        ingredientes = "Pechuga de pollo;Lechuga;Espinaca;Pepino;Tomate cherry;Aderezo ligero",
        recomendacionNutricional = "Incorporar diferentes tipos de verduras.",
        tipoComida = "almuerzo"
    ),
    Receta(
        dia = "Viernes",
        nombre = "Tortilla de verduras",
        descripcion = "Tortilla preparada con verduras variadas.",
        ingredientes = "Huevos;Zanahoria;Espinaca;Cebolla;Zapallito italiano;Sal;Pimienta",
        recomendacionNutricional = "Acompañar con una porción de verduras frescas.",
        tipoComida = "almuerzo"
    ),
    Receta(
        dia = "Lunes",
        nombre = "Panqueques de avena y plátano",
        descripcion = "Panqueques hechos con avena molida, huevo y plátano maduro, acompañados de fruta fresca.",
        ingredientes = "Avena molida;Huevo;Plátano maduro;Leche;Frutillas;Arándanos",
        recomendacionNutricional = "Evitar agregar azúcares refinados y usar miel con moderación.",
        tipoComida = "desayuno"
    ),
    Receta(
        dia = "Martes",
        nombre = "Tostadas con huevo y palta",
        descripcion = "Pan integral tostado con palta molida y dos huevos pocheados o revueltos.",
        ingredientes = "Pan integral;Palta;Huevos;Sal;Pimienta",
        recomendacionNutricional = "Excelente fuente de grasas saludables y proteína para comenzar el día.",
        tipoComida = "desayuno"
    ),
    Receta(
        dia = "Miércoles",
        nombre = "Yogur con granola y frutos rojos",
        descripcion = "Yogur natural sin azúcar acompañado de granola casera, frutillas y arándanos.",
        ingredientes = "Yogur natural sin azúcar;Granola casera;Frutillas;Arándanos",
        recomendacionNutricional = "Preferir yogur alto en proteínas (tipo griego) para mayor saciedad.",
        tipoComida = "desayuno"
    ),
    Receta(
        dia = "Jueves",
        nombre = "Porridge de avena con manzana",
        descripcion = "Avena cocida en leche descremada o bebida vegetal, sazonada con canela y trozos de manzana.",
        ingredientes = "Avena en hojuelas;Leche descremada;Manzana;Canela en polvo",
        recomendacionNutricional = "La canela ayuda a endulzar de forma natural sin aportar calorías.",
        tipoComida = "desayuno"
    ),
    Receta(
        dia = "Viernes",
        nombre = "Omelette de espinaca y queso",
        descripcion = "Tortilla de huevos rellena con hojas de espinaca fresca y una lámina de queso bajo en grasa.",
        ingredientes = "Huevos;Espinaca fresca;Queso bajo en grasa;Sal;Aceite de oliva",
        recomendacionNutricional = "Acompañar con una taza de té verde o café sin azúcar.",
        tipoComida = "desayuno"
    ),
    Receta(
        dia = "Lunes",
        nombre = "Pescado al horno con verduras",
        descripcion = "Filete de pescado (reineta o merluza) al horno con tomate, cebolla y pimentón.",
        ingredientes = "Filete de pescado;Tomate;Cebolla;Pimentón;Limón;Orégano;Sal",
        recomendacionNutricional = "Una opción ligera y de fácil digestión ideal para la última comida.",
        tipoComida = "High protein cena"
    ),
    Receta(
        dia = "Martes",
        nombre = "Ensalada tibia de pollo y quinoa",
        descripcion = "Quinoa cocida mezclada con pechuga de pollo en cubos, verduras salteadas y un toque de oliva.",
        ingredientes = "Quinoa;Pechuga de pollo;Zapallito italiano;Zanahoria;Aceite de oliva;Sal",
        recomendacionNutricional = "Controlar la porción de quinoa para mantener la cena baja en carbohidratos.",
        tipoComida = "cena"
    ),
    Receta(
        dia = "Miércoles",
        nombre = "Crema de zapallo y zanahoria",
        descripcion = "Sopa espesa casera de zapallo camote, zanahoria y jengibre, acompañada de crutones integrales.",
        ingredientes = "Zapallo camote;Zanahoria;Jengibre;Leche descremada;Crutones integrales;Sal",
        recomendacionNutricional = "Evitar agregar crema de leche; usar un chorrito de leche descremada para la textura.",
        tipoComida = "cena"
    ),
    Receta(
        dia = "Jueves",
        nombre = "Fajitas integrales de pavo",
        descripcion = "Tortilla integral rellena con tiras de pavo salteadas, lechuga, tomate y un toque de yogur ciboulette.",
        ingredientes = "Tortilla integral;Pechuga de pavo;Lechuga;Tomate;Yogur natural;Ciboulette",
        recomendacionNutricional = "Preferir pechuga de pavo cocida en casa en lugar de embutidos procesados.",
        tipoComida = "cena"
    ),
    Receta(
        dia = "Viernes",
        nombre = "Tortilla de zanahoria y coliflor",
        descripcion = "Zanahoria rallada y coliflor picada fina, unidas con huevo y horneadas hasta dorar.",
        ingredientes = "Zanahoria;Coliflor;Huevos;Sal;Pimienta",
        recomendacionNutricional = "Baja en calorías y rica en fibra, perfecta para antes de dormir.",
        tipoComida = "cena"
    )
)
