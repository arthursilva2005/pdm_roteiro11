package com.example.livraria0110.feature.autores.domain.repository

import com.example.livraria0110.feature.autores.domain.model.Autor
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface AutorRepository {
    fun observarTodos(): Flow<List<Autor>>
    suspend fun buscarPorId(id: UUID): Autor?
    suspend fun salvar(autor: Autor)
}
