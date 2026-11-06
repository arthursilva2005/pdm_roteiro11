package com.example.livraria0110.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.livraria0110.feature.autores.presentation.AutorForm
import com.example.livraria0110.feature.autores.presentation.AutoresList
import com.example.livraria0110.feature.autores.presentation.AutoresViewModel
import com.example.livraria0110.feature.livros.presentation.LivroForm
import com.example.livraria0110.feature.livros.presentation.LivrosList
import com.example.livraria0110.feature.livros.presentation.LivrosViewModel
import java.util.UUID

@Composable
fun Navigation(
    navigationController: NavHostController,
    modifier: Modifier = Modifier
) {
    val livrosViewModel: LivrosViewModel = hiltViewModel()
    val autoresViewModel: AutoresViewModel = hiltViewModel()

    NavHost(
        navController = navigationController,
        startDestination = "livros",
        modifier = modifier
    ) {
        composable("livros") {
            LivrosList(
                livrosViewModel = livrosViewModel,
                onNavigateToForm = { id ->
                    navigationController.navigate(
                        "livroForm?livroId=$id"
                    )
                }
            )
        }

        composable(
            route = "livroForm?livroId={livroId}",
            arguments = listOf(
                navArgument("livroId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val livroId = backStackEntry.arguments
                ?.getString("livroId")
                ?.let(UUID::fromString)

            LivroForm(
                id = livroId,
                livrosViewModel = livrosViewModel,
                autoresViewModel = autoresViewModel,
                onBack = {
                    navigationController.popBackStack()
                }
            )
        }

        composable("autores") {
            AutoresList(
                autoresViewModel = autoresViewModel,
                onNavigateToForm = { id ->
                    navigationController.navigate(
                        "autorForm?autorId=$id"
                    )
                }
            )
        }

        composable(
            route = "autorForm?autorId={autorId}",
            arguments = listOf(
                navArgument("autorId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val autorId = backStackEntry.arguments
                ?.getString("autorId")
                ?.let(UUID::fromString)

            AutorForm(
                id = autorId,
                autoresViewModel = autoresViewModel,
                onBack = {
                    navigationController.popBackStack()
                }
            )
        }
    }
}
