package com.example.livraria0110.feature.livros.domain.usecase

import com.example.livraria0110.feature.livros.domain.model.Livro
import com.example.livraria0110.feature.livros.domain.repository.LivroRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObservarLivrosUseCase @Inject constructor(
    private val repository: LivroRepository
) {
    operator fun invoke(): Flow<List<Livro>> =
        repository.observarTodos()
}
