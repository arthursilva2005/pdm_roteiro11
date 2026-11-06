package com.example.livraria0110.core.di

import android.content.Context
import androidx.room.Room
import com.example.livraria0110.core.database.LivrariaDatabase
import com.example.livraria0110.feature.autores.data.local.AutorDao
import com.example.livraria0110.feature.livros.data.local.LivroDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): LivrariaDatabase =
        Room.databaseBuilder(
            context,
            LivrariaDatabase::class.java,
            "livraria_database"
        ).build()

    @Provides
    fun provideAutorDao(database: LivrariaDatabase): AutorDao =
        database.autorDao()

    @Provides
    fun provideLivroDao(database: LivrariaDatabase): LivroDao =
        database.livroDao()
}
