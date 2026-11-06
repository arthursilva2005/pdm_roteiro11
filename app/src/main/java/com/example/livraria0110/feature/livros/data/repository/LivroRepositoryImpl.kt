package com.example.livraria0110.feature.livros.data.repository

import com.example.livraria0110.feature.livros.data.local.LivroDao
import com.example.livraria0110.feature.livros.data.mapper.toDomain
import com.example.livraria0110.feature.livros.data.mapper.toEntity
import com.example.livraria0110.feature.livros.domain.model.Livro
import com.example.livraria0110.feature.livros.domain.repository.LivroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class LivroRepositoryImpl @Inject constructor(
    private val dao: LivroDao
) : LivroRepository {

    override fun observarTodos(): Flow<List<Livro>> =
        dao.observarTodos().map { lista ->
            lista.map { it.toDomain() }
        }

    override suspend fun buscarPorId(id: UUID): Livro? =
        dao.buscarPorId(id)?.toDomain()

    override suspend fun salvar(livro: Livro) {
        dao.salvar(livro.toEntity())
    }
}
