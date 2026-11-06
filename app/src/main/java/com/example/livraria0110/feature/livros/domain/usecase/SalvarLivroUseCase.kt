package com.example.livraria0110.feature.livros.domain.usecase

import com.example.livraria0110.feature.livros.domain.model.Livro
import com.example.livraria0110.feature.livros.domain.repository.LivroRepository
import java.math.BigDecimal
import javax.inject.Inject

class SalvarLivroUseCase @Inject constructor(
    private val repository: LivroRepository
) {
    suspend operator fun invoke(livro: Livro): Result<Unit> {
        if (livro.titulo.isBlank()) {
            return Result.failure(
                IllegalArgumentException("O título é obrigatório.")
            )
        }

        if (livro.editora.isBlank()) {
            return Result.failure(
                IllegalArgumentException("A editora é obrigatória.")
            )
        }

        if (livro.autor == null) {
            return Result.failure(
                IllegalArgumentException("Selecione um autor.")
            )
        }

        val precoNormalizado = livro.preco
            .replace(",", ".")
            .toBigDecimalOrNull()

        if (precoNormalizado == null || precoNormalizado <= BigDecimal.ZERO) {
            return Result.failure(
                IllegalArgumentException("Informe um preço válido.")
            )
        }

        return runCatching {
            repository.salvar(livro)
        }
    }
}
