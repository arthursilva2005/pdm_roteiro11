package com.example.livraria0110.feature.autores.domain.usecase

import com.example.livraria0110.feature.autores.domain.model.Autor
import com.example.livraria0110.feature.autores.domain.repository.AutorRepository
import javax.inject.Inject

class SalvarAutorUseCase @Inject constructor(
    private val repository: AutorRepository
) {
    suspend operator fun invoke(autor: Autor): Result<Unit> {
        if (autor.nome.isBlank()) {
            return Result.failure(
                IllegalArgumentException("O nome do autor é obrigatório.")
            )
        }

        if (autor.nacionalidade.isBlank()) {
            return Result.failure(
                IllegalArgumentException("A nacionalidade é obrigatória.")
            )
        }

        return runCatching {
            repository.salvar(autor)
        }
    }
}
