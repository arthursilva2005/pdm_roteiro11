package com.example.livraria0110.feature.autores.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livraria0110.feature.autores.domain.model.Autor
import com.example.livraria0110.feature.autores.domain.usecase.ObservarAutoresUseCase
import com.example.livraria0110.feature.autores.domain.usecase.SalvarAutorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AutoresViewModel @Inject constructor(
    observarAutoresUseCase: ObservarAutoresUseCase,
    private val salvarAutorUseCase: SalvarAutorUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val autores: StateFlow<List<Autor>> =
        observarAutoresUseCase()
            .onEach { _isLoading.value = false }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun findById(id: UUID?): Autor? =
        id?.let { autorId ->
            autores.value.firstOrNull { it.id == autorId }
        }

    fun save(
        id: UUID?,
        nome: String,
        nacionalidade: String,
        ativo: Boolean,
        onSuccess: (() -> Unit)? = null,
        onError: ((String) -> Unit)? = null
    ) {
        viewModelScope.launch {
            val autor = Autor(
                id = id ?: UUID.randomUUID(),
                nome = nome.trim(),
                nacionalidade = nacionalidade.trim(),
                ativo = ativo
            )

            salvarAutorUseCase(autor).fold(
                onSuccess = { onSuccess?.invoke() },
                onFailure = {
                    onError?.invoke(it.message ?: "Erro ao salvar autor.")
                }
            )
        }
    }
}
