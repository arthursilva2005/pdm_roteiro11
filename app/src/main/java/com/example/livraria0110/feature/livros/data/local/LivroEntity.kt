package com.example.livraria0110.feature.livros.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.livraria0110.feature.autores.data.local.AutorEntity
import java.util.UUID

@Entity(
    tableName = "livros",
    foreignKeys = [
        ForeignKey(
            entity = AutorEntity::class,
            parentColumns = ["id"],
            childColumns = ["autorId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("autorId")]
)
data class LivroEntity(
    @PrimaryKey
    val id: UUID,
    val titulo: String,
    val editora: String,
    val preco: String,
    val autorId: UUID?,
    val disponivel: Boolean
)
