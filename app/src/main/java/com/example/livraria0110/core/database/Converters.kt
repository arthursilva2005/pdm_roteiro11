package com.example.livraria0110.core.database

import androidx.room.TypeConverter
import java.util.UUID

class Converters {

    @TypeConverter
    fun uuidParaString(uuid: UUID?): String? = uuid?.toString()

    @TypeConverter
    fun stringParaUuid(valor: String?): UUID? =
        valor?.let(UUID::fromString)
}
