package com.example.livraria0110.feature.livros.data.local

import androidx.room.Embedded
import androidx.room.Relation
import com.example.livraria0110.feature.autores.data.local.AutorEntity

data class LivroComAutorEntity(
    @Embedded
    val livro: LivroEntity,

    @Relation(
        parentColumn = "autorId",
        entityColumn = "id"
    )
    val autor: AutorEntity?
)
