package com.example.livraria0110.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.livraria0110.feature.autores.data.local.AutorDao
import com.example.livraria0110.feature.autores.data.local.AutorEntity
import com.example.livraria0110.feature.livros.data.local.LivroDao
import com.example.livraria0110.feature.livros.data.local.LivroEntity

@Database(
    entities = [
        AutorEntity::class,
        LivroEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LivrariaDatabase : RoomDatabase() {

    abstract fun autorDao(): AutorDao
    abstract fun livroDao(): LivroDao
}
