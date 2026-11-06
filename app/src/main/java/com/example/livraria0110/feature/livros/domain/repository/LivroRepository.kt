package com.example.livraria0110.feature.livros.domain.repository

import com.example.livraria0110.feature.livros.domain.model.Livro
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface LivroRepository {
    fun observarTodos(): Flow<List<Livro>>
    suspend fun buscarPorId(id: UUID): Livro?
    suspend fun salvar(livro: Livro)
}
