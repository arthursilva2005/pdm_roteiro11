package com.example.livraria0110.core.di

import com.example.livraria0110.feature.autores.data.repository.AutorRepositoryImpl
import com.example.livraria0110.feature.autores.domain.repository.AutorRepository
import com.example.livraria0110.feature.livros.data.repository.LivroRepositoryImpl
import com.example.livraria0110.feature.livros.domain.repository.LivroRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAutorRepository(
        repository: AutorRepositoryImpl
    ): AutorRepository

    @Binds
    @Singleton
    abstract fun bindLivroRepository(
        repository: LivroRepositoryImpl
    ): LivroRepository
}
