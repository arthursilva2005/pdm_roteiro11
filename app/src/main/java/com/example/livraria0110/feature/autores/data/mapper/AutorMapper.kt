package com.example.livraria0110.feature.autores.data.mapper

import com.example.livraria0110.feature.autores.data.local.AutorEntity
import com.example.livraria0110.feature.autores.domain.model.Autor

fun AutorEntity.toDomain(): Autor =
    Autor(
        id = id,
        nome = nome,
        nacionalidade = nacionalidade,
        ativo = ativo
    )

fun Autor.toEntity(): AutorEntity =
    AutorEntity(
        id = id,
        nome = nome,
        nacionalidade = nacionalidade,
        ativo = ativo
    )
