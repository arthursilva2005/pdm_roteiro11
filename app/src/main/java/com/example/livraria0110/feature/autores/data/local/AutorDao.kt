package com.example.livraria0110.feature.autores.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface AutorDao {

    @Query("SELECT * FROM autores ORDER BY nome")
    fun observarTodos(): Flow<List<AutorEntity>>

    @Query("SELECT * FROM autores WHERE id = :id LIMIT 1")
    suspend fun buscarPorId(id: UUID): AutorEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvar(autor: AutorEntity)
}
