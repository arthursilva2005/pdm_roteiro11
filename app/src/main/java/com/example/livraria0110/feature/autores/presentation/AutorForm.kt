package com.example.livraria0110.feature.autores.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.UUID

@Composable
fun AutorForm(
    id: UUID?,
    autoresViewModel: AutoresViewModel,
    onBack: () -> Unit
) {
    val autor = autoresViewModel.findById(id)

    var nomeInput by rememberSaveable {
        mutableStateOf(autor?.nome ?: "")
    }
    var nacionalidadeInput by rememberSaveable {
        mutableStateOf(autor?.nacionalidade ?: "")
    }
    var ativoInput by rememberSaveable {
        mutableStateOf(autor?.ativo ?: true)
    }
    var erro by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = if (autor == null) "Cadastro de Autor" else "Edição de Autor",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Preencha os dados do autor.",
            fontSize = 14.sp,
            color = Color(87, 83, 78)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nome") },
            value = nomeInput,
            onValueChange = { nomeInput = it }
        )

        Spacer(Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nacionalidade") },
            value = nacionalidadeInput,
            onValueChange = { nacionalidadeInput = it }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = ativoInput,
                onCheckedChange = { ativoInput = it }
            )
            Text("Ativo")
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
                enabled = nomeInput.isNotBlank() &&
                    nacionalidadeInput.isNotBlank(),
                onClick = {
                    autoresViewModel.save(
                        id = autor?.id,
                        nome = nomeInput,
                        nacionalidade = nacionalidadeInput,
                        ativo = ativoInput,
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
