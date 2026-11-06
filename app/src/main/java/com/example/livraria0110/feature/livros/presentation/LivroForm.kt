package com.example.livraria0110.feature.livros.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livraria0110.feature.autores.domain.model.Autor
import com.example.livraria0110.feature.autores.presentation.AutoresViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LivroForm(
    id: UUID?,
    livrosViewModel: LivrosViewModel,
    autoresViewModel: AutoresViewModel,
    onBack: () -> Unit
) {
    val livro = livrosViewModel.findById(id)

    var tituloInput by rememberSaveable {
        mutableStateOf(livro?.titulo ?: "")
    }
    var editoraInput by rememberSaveable {
        mutableStateOf(livro?.editora ?: "")
    }
    var precoInput by rememberSaveable {
        mutableStateOf(livro?.preco ?: "")
    }
    var disponivelInput by rememberSaveable {
        mutableStateOf(livro?.disponivel ?: true)
    }
    var selectedAutor by remember {
        mutableStateOf<Autor?>(livro?.autor)
    }
    var showSheet by rememberSaveable {
        mutableStateOf(false)
    }
    var query by rememberSaveable {
        mutableStateOf("")
    }
    var erro by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    val autores by autoresViewModel.autores.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = if (livro == null) "Cadastro de Livro" else "Editar Livro",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Preencha os dados do livro abaixo.",
            fontSize = 14.sp,
            color = Color(87, 83, 78)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = tituloInput,
            onValueChange = { tituloInput = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Título") }
        )

        Spacer(Modifier.height(8.dp))

        TextField(
            value = editoraInput,
            onValueChange = { editoraInput = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Editora") }
        )

        Spacer(Modifier.height(8.dp))

        TextField(
            value = precoInput,
            onValueChange = { precoInput = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Preço") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )

        Spacer(Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showSheet = true }
        ) {
            TextField(
                value = selectedAutor?.nome ?: "",
                onValueChange = {},
                readOnly = true,
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Autor") },
                placeholder = { Text("Selecione um autor") }
            )
        }

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showSheet = false
                }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = query,
                        onValueChange = { query = it },
                        label = { Text("Buscar autor") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    val filtrados = autores.filter {
                        it.nome.contains(query, ignoreCase = true)
                    }

                    LazyColumn(
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        items(filtrados, key = { it.id }) { autor ->
                            Text(
                                text = autor.nome,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedAutor = autor
                                        showSheet = false
                                    }
                                    .padding(vertical = 12.dp)
                            )
                        }
                    }
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = disponivelInput,
                onCheckedChange = { disponivelInput = it }
            )
            Text("Disponível")
        }

        erro?.let {
            Text(
                text = it,
                color = Color.Red
            )
            Spacer(Modifier.height(8.dp))
        }

        Row {
            Button(
                enabled = tituloInput.isNotBlank() &&
                    editoraInput.isNotBlank() &&
                    precoInput.isNotBlank() &&
                    selectedAutor != null,
                onClick = {
                    livrosViewModel.save(
                        id = livro?.id,
                        titulo = tituloInput,
                        editora = editoraInput,
                        preco = precoInput,
                        autor = selectedAutor,
                        disponivel = disponivelInput,
                        onSuccess = onBack,
                        onError = { erro = it }
                    )
                }
            ) {
                Text("Salvar")
            }

            Spacer(Modifier.width(8.dp))

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(229, 229, 229),
                    contentColor = Color(23, 23, 23)
                ),
                onClick = onBack
            ) {
                Text("Cancelar")
            }
        }
    }
}
