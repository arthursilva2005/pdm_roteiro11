package com.example.livraria0110.feature.autores.data.repository

import com.example.livraria0110.feature.autores.data.local.AutorDao
import com.example.livraria0110.feature.autores.data.mapper.toDomain
import com.example.livraria0110.feature.autores.data.mapper.toEntity
import com.example.livraria0110.feature.autores.domain.model.Autor
import com.example.livraria0110.feature.autores.domain.repository.AutorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class AutorRepositoryImpl @Inject constructor(
    private val dao: AutorDao
) : AutorRepository {

    override fun observarTodos(): Flow<List<Autor>> =
        dao.observarTodos().map { lista ->
            lista.map { it.toDomain() }
        }

    override suspend fun buscarPorId(id: UUID): Autor? =
        dao.buscarPorId(id)?.toDomain()

    override suspend fun salvar(autor: Autor) {
        dao.salvar(autor.toEntity())
    }
}
