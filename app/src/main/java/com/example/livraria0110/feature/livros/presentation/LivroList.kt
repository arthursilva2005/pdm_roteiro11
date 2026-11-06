package com.example.livraria0110.feature.livros.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livraria0110.feature.livros.domain.model.Livro
import java.util.UUID

@Composable
fun LivrosList(
    livrosViewModel: LivrosViewModel,
    onNavigateToForm: (UUID) -> Unit
) {
    val livros by livrosViewModel.livros.collectAsState()
    val isLoading by livrosViewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Livros",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Gerencie os livros cadastrados na livraria.",
            fontSize = 14.sp,
            color = Color(87, 83, 78)
        )

        Spacer(Modifier.height(16.dp))

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            if (livros.isEmpty()) {
                Text("Nenhum livro cadastrado. Use o botão + para adicionar.")
                Spacer(Modifier.height(8.dp))
            }

            LazyColumn {
                items(livros, key = { it.id }) { livro ->
                    LivroItem(
                        livro = livro,
                        onClick = { onNavigateToForm(livro.id) }
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
private fun LivroItem(
    livro: Livro,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(245, 245, 245),
                RoundedCornerShape(16.dp)
            )
            .border(
                1.dp,
                Color(212, 212, 212),
                RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(
            text = livro.titulo,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium
        )
        Text("Editora: ${livro.editora}")
        Text("Preço: R$ ${livro.preco}")
        Text("Autor: ${livro.autor?.nome ?: "Não informado"}")
        Text(if (livro.disponivel) "Disponível" else "Indisponível")
    }
}
