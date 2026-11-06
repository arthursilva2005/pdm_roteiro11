package com.example.livraria0110.feature.livros.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livraria0110.feature.autores.domain.model.Autor
import com.example.livraria0110.feature.livros.domain.model.Livro
import com.example.livraria0110.feature.livros.domain.usecase.ObservarLivrosUseCase
import com.example.livraria0110.feature.livros.domain.usecase.SalvarLivroUseCase
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
class LivrosViewModel @Inject constructor(
    observarLivrosUseCase: ObservarLivrosUseCase,
    private val salvarLivroUseCase: SalvarLivroUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val livros: StateFlow<List<Livro>> =
        observarLivrosUseCase()
            .onEach { _isLoading.value = false }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun findById(id: UUID?): Livro? =
        id?.let { livroId ->
            livros.value.firstOrNull { it.id == livroId }
        }

    fun save(
        id: UUID?,
        titulo: String,
        editora: String,
        preco: String,
        autor: Autor?,
        disponivel: Boolean,
        onSuccess: (() -> Unit)? = null,
        onError: ((String) -> Unit)? = null
    ) {
        viewModelScope.launch {
            val livro = Livro(
                id = id ?: UUID.randomUUID(),
                titulo = titulo.trim(),
                editora = editora.trim(),
                preco = preco.trim(),
                autor = autor,
                disponivel = disponivel
            )

            salvarLivroUseCase(livro).fold(
                onSuccess = { onSuccess?.invoke() },
                onFailure = {
                    onError?.invoke(it.message ?: "Erro ao salvar livro.")
                }
            )
        }
    }
}
