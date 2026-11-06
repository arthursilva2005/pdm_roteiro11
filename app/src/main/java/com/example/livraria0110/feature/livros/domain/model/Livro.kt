package com.example.livraria0110.feature.livros.domain.model

import com.example.livraria0110.feature.autores.domain.model.Autor
import java.util.UUID

data class Livro(
    val id: UUID,
    val titulo: String,
    val editora: String,
    val preco: String,
    val autor: Autor?,
    val disponivel: Boolean
)
