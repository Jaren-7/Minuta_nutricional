package com.example.minuta_nutricional.repo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbContext(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "MinutaNutricional.db"
        private const val DATABASE_VERSION = 2
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS usuarios (
                correo TEXT PRIMARY KEY,
                password TEXT NOT NULL,
                nombre TEXT NOT NULL,
                usuario TEXT NOT NULL
            )
        """.trimIndent()
        )

        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS recetas (
                id TEXT PRIMARY KEY,
                dia TEXT NOT NULL,
                nombre TEXT NOT NULL,
                descripcion TEXT NOT NULL,
                recomendacionNutricional TEXT NOT NULL,
                tipoComida TEXT NOT NULL,
                ingredientes TEXT NOT NULL,
                esPersonalizada INTEGER DEFAULT 0
            )
        """.trimIndent()
        )

        db.execSQL("""
            CREATE TABLE IF NOT EXISTS menus (
                titulo TEXT PRIMARY KEY,
                descripcion TEXT NOT NULL,
                ruta TEXT NOT NULL
            )
        """.trimIndent())

        db.execSQL("INSERT INTO usuarios (correo, password, nombre, usuario) VALUES ('usuario1@gmail.com', '1234', 'Juan Perez', 'jperez')")
        db.execSQL("INSERT INTO usuarios (correo, password, nombre, usuario) VALUES ('slobos@gmail.com', 'admin', 'Sebastian Lobos', 'slobos')")
        db.execSQL("INSERT INTO usuarios (correo, password, nombre, usuario) VALUES ('tmontana@gmail.com', '1234', 'Tony Montana','tmontana')")

        db.execSQL("INSERT INTO menus (titulo, descripcion, ruta) VALUES ('Desayuno', 'Recetas y opciones para comenzar el dia', 'minuta/desayuno')")
        db.execSQL("INSERT INTO menus (titulo, descripcion, ruta) VALUES ('Almuerzo', 'Recetas y opciones para tu almuerzo', 'minuta/almuerzo')")
        db.execSQL("INSERT INTO menus (titulo, descripcion, ruta) VALUES ('Cena', 'Recetas y opciones para terminar el dia', 'minuta/cena')")
        db.execSQL("INSERT INTO menus (titulo, descripcion, ruta) VALUES ('Mis Recetas', 'Tus preparaciones personalizadas guardadas', 'minuta/mis_recetas')")

        /**INYECCION DE RECETAS*/

        db.execSQL("INSERT INTO recetas (id, dia, nombre, descripcion, recomendacionNutricional, tipoComida, ingredientes) VALUES ('f4e3d2c1-b0a9-8f7e-6d5c-4b3a2f1e0d9c', 'Lunes', 'Pollo con arroz', 'Pollo a la plancha acompañado de arroz y ensalada.', 'Incluir verduras variadas y preferir agua como bebida.', 'almuerzo', 'Pechuga de pollo;Arroz;Lechuga;Tomate;Aceite;Sal')")
        db.execSQL("INSERT INTO recetas (id, dia, nombre, descripcion, recomendacionNutricional, tipoComida, ingredientes) VALUES ('9c8b7a6f-5e4d-3c2b-1a0f-9e8d7c6b5a4f', 'Martes', 'Lentejas con verduras', 'Lentejas acompañadas de verduras frescas.', 'Las legumbres aportan proteínas y fibra.', 'almuerzo', 'Lentejas;Zanahoria;Zapallo;Cebolla;Pimentón;Caldo de verduras;Sal')")
        db.execSQL("INSERT INTO recetas (id, dia, nombre, descripcion, recomendacionNutricional, tipoComida, ingredientes) VALUES ('1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', 'Miércoles', 'Pescado al horno', 'Pescado al horno acompañado de papas y ensalada.', 'Preferir preparaciones al horno y acompañar con verduras.', 'almuerzo', 'Filete de pescado;Papas;Cebolla;Limón;Aceite de oliva;Ensalada surtida')")
        db.execSQL("INSERT INTO recetas (id, dia, nombre, descripcion, recomendacionNutricional, tipoComida, ingredientes) VALUES ('7f6e5d4c-3b2a-1f0e-9d8c-7b6a5f4e3d2c', 'Jueves', 'Ensalada con pollo', 'Ensalada variada con pollo a la plancha.', 'Incorporar diferentes tipos de verduras.', 'almuerzo', 'Pechuga de pollo;Lechuga;Espinaca;Pepino;Tomate cherry;Aderezo ligero')")
        db.execSQL("INSERT INTO recetas (id, dia, nombre, descripcion, recomendacionNutricional, tipoComida, ingredientes) VALUES ('a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d', 'Viernes', 'Tortilla de verduras', 'Tortilla preparada con verduras variadas.', 'Acompañar con una porción de verduras frescas.', 'almuerzo', 'Huevos;Zanahoria;Espinaca;Cebolla;Zapallito italiano;Sal;Pimienta')")

        // --- Desayunos ---
        db.execSQL("INSERT INTO recetas (id, dia, nombre, descripcion, recomendacionNutricional, tipoComida, ingredientes) VALUES ('4f7b2c9a-8d1e-4321-a5b6-7c8d9e0f1a2b', 'Lunes', 'Panqueques de avena y plátano', 'Panqueques hechos con avena molida, huevo y plátano maduro, acompañados de fruta fresca.', 'Evitar agregar azúcares refinados y usar miel con moderación.', 'desayuno', 'Avena molida;Huevo;Plátano maduro;Leche;Frutillas;Arándanos')")
        db.execSQL("INSERT INTO recetas (id, dia, nombre, descripcion, recomendacionNutricional, tipoComida, ingredientes) VALUES ('3c2b1a0f-9e8d-7c6b-5a4f-3e2d1c0b9a8f', 'Martes', 'Tostadas con huevo y palta', 'Pan integral tostado con palta molida y dos huevos pocheados o revueltos.', 'Excelente fuente de grasas saludables y proteína para comenzar el día.', 'desayuno', 'Pan integral;Palta;Huevos;Sal;Pimienta')")
        db.execSQL("INSERT INTO recetas (id, dia, nombre, descripcion, recomendacionNutricional, tipoComida, ingredientes) VALUES ('8c7b6a5f-4e3d-2c1b-0a9f-8e7d6c5b4a3f', 'Miércoles', 'Yogur con granola y frutos rojos', 'Yogur natural sin azúcar acompañado de granola casera, frutillas y arándanos.', 'Preferir yogur alto en proteínas (tipo griego) para mayor saciedad.', 'desayuno', 'Yogur natural sin azúcar;Granola casera;Frutillas;Arándanos')")
        db.execSQL("INSERT INTO recetas (id, dia, nombre, descripcion, recomendacionNutricional, tipoComida, ingredientes) VALUES ('5d4c3b2a-1f0e-9d8c-7b6a-5f4e3d2c1b0a', 'Jueves', 'Porridge de avena con manzana', 'Avena cocida en leche descremada o bebida vegetal, sazonada con canela y trozos de manzana.', 'La canela ayuda a endulzar de forma natural sin aportar calorías.', 'desayuno', 'Avena en hojuelas;Leche descremada;Manzana;Canela en polvo')")
        db.execSQL("INSERT INTO recetas (id, dia, nombre, descripcion, recomendacionNutricional, tipoComida, ingredientes) VALUES ('e5f67a8b-9c0d-1e2f-3a4b-5c6d7a8b9c0d', 'Viernes', 'Omelette de espinaca y queso', 'Tortilla de huevos rellena con hojas de espinaca fresca y una lámina de queso bajo en grasa.', 'Acompañar con una taza de té verde o café sin azúcar.', 'desayuno', 'Huevos;Espinaca fresca;Queso bajo en grasa;Sal;Aceite de oliva')")

        // --- Cenas ---
        db.execSQL("INSERT INTO recetas (id, dia, nombre, descripcion, recomendacionNutricional, tipoComida, ingredientes) VALUES ('9d8c7b6a-5f4e-3d2c-1b0a-9f8e7d6c5b4a', 'Lunes', 'Pescado al horno con verduras', 'Filete de pescado (reineta o merluza) al horno con tomate, cebolla y pimentón.', 'Una opción ligera y de fácil digestión ideal para la última comida.', 'High protein cena', 'Filete de pescado;Tomate;Cebolla;Pimentón;Limón;Orégano;Sal')")
        db.execSQL("INSERT INTO recetas (id, dia, nombre, descripcion, recomendacionNutricional, tipoComida, ingredientes) VALUES ('7c6b5a4f-3e2d-1c0b-9a8f-8e7d6c5b4a3f', 'Martes', 'Ensalada tibia de pollo y quinoa', 'Quinoa cocida mezclada con pechuga de pollo en cubos, verduras salteadas y un toque de oliva.', 'Controlar la porción de quinoa para mantener la cena baja en carbohidratos.', 'cena', 'Quinoa;Pechuga de pollo;Zapallito italiano;Zanahoria;Aceite de oliva;Sal')")
        db.execSQL("INSERT INTO recetas (id, dia, nombre, descripcion, recomendacionNutricional, tipoComida, ingredientes) VALUES ('2a1f0e9d-8c7b-6a5f-4e3d-2c1b0a9f8e7d', 'Miércoles', 'Crema de zapallo y zanahoria', 'Sopa espesa casera de zapallo camote, zanahoria y jengibre, acompañada de crutones integrales.', 'Evitar agregar crema de leche; usar un chorrito de leche descremada para la textura.', 'cena', 'Zapallo camote;Zanahoria;Jengibre;Leche descremada;Crutones integrales;Sal')")
        db.execSQL("INSERT INTO recetas (id, dia, nombre, descripcion, recomendacionNutricional, tipoComida, ingredientes) VALUES ('6d5c4b3a-2f1e-0d9c-8b7a-6f5e4d3c2b1a', 'Jueves', 'Fajitas integrales de pavo', 'Tortilla integral rellena con tiras de pavo salteadas, lechuga, tomate y un toque de yogur ciboulette.', 'Preferir pechuga de pavo cocida en casa en lugar de embutidos procesados.', 'cena', 'Tortilla integral;Pechuga de pavo;Lechuga;Tomate;Yogur natural;Ciboulette')")
        db.execSQL("INSERT INTO recetas (id, dia, nombre, descripcion, recomendacionNutricional, tipoComida, ingredientes) VALUES ('1f0e9d8c-7b6a-5f4e-3d2c-1b0a9f8e7d6c', 'Viernes', 'Tortilla de zanahoria y coliflor', 'Zanahoria rallada y coliflor picada fina, unidas con huevo y horneadas hasta dorar.', 'Baja en calorías y rica en fibra, perfecta para antes de dormir.', 'cena', 'Zanahoria;Coliflor;Huevos;Sal;Pimienta')")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        db.execSQL("DROP TABLE IF EXISTS recetas")
        db.execSQL("DROP TABLE IF EXISTS menus")
        onCreate(db)
    }
}