package com.example.livraria0110.feature.autores.domain.model

import java.util.UUID

data class Autor(
    val id: UUID,
    val nome: String,
    val nacionalidade: String,
    val ativo: Boolean
)
