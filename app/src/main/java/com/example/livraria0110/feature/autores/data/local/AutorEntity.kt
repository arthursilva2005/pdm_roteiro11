package com.example.livraria0110.feature.autores.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "autores")
data class AutorEntity(
    @PrimaryKey
    val id: UUID,
    val nome: String,
    val nacionalidade: String,
    val ativo: Boolean
)
