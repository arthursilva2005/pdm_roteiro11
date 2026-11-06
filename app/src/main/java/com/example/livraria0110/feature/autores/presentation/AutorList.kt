package com.example.livraria0110.feature.autores.presentation

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
import com.example.livraria0110.feature.autores.domain.model.Autor
import java.util.UUID

@Composable
fun AutoresList(
    autoresViewModel: AutoresViewModel,
    onNavigateToForm: (UUID) -> Unit
) {
    val autores by autoresViewModel.autores.collectAsState()
    val isLoading by autoresViewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Autores",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Cadastre e edite os autores dos livros.",
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
            if (autores.isEmpty()) {
                Text("Nenhum autor cadastrado. Use o botão + para adicionar.")
                Spacer(Modifier.height(8.dp))
            }

            LazyColumn {
                items(autores, key = { it.id }) { autor ->
                    AutorItem(
                        autor = autor,
                        onClick = { onNavigateToForm(autor.id) }
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
private fun AutorItem(
    autor: Autor,
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
            text = autor.nome,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium
        )
        Text("Nacionalidade: ${autor.nacionalidade}")
        Text(if (autor.ativo) "Status: Ativo" else "Status: Inativo")
    }
}
