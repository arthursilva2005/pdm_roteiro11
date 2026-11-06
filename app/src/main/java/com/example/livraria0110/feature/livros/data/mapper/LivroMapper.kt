package com.example.livraria0110.feature.livros.data.mapper

import com.example.livraria0110.feature.autores.data.mapper.toDomain
import com.example.livraria0110.feature.livros.data.local.LivroComAutorEntity
import com.example.livraria0110.feature.livros.data.local.LivroEntity
import com.example.livraria0110.feature.livros.domain.model.Livro

fun LivroComAutorEntity.toDomain(): Livro =
    Livro(
        id = livro.id,
        titulo = livro.titulo,
        editora = livro.editora,
        preco = livro.preco,
        autor = autor?.toDomain(),
        disponivel = livro.disponivel
    )

fun Livro.toEntity(): LivroEntity =
    LivroEntity(
        id = id,
        titulo = titulo,
        editora = editora,
        preco = preco,
        autorId = autor?.id,
        disponivel = disponivel
    )
