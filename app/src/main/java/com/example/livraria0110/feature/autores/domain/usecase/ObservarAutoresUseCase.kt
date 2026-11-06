package com.example.livraria0110.feature.autores.domain.usecase

import com.example.livraria0110.feature.autores.domain.model.Autor
import com.example.livraria0110.feature.autores.domain.repository.AutorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObservarAutoresUseCase @Inject constructor(
    private val repository: AutorRepository
) {
    operator fun invoke(): Flow<List<Autor>> =
        repository.observarTodos()
}
