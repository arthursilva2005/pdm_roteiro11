package com.example.livraria0110.feature.livros.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface LivroDao {

    @Transaction
    @Query("SELECT * FROM livros ORDER BY titulo")
    fun observarTodos(): Flow<List<LivroComAutorEntity>>

    @Transaction
    @Query("SELECT * FROM livros WHERE id = :id LIMIT 1")
    suspend fun buscarPorId(id: UUID): LivroComAutorEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvar(livro: LivroEntity)
}
