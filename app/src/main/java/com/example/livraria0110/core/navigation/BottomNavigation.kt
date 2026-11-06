package com.example.livraria0110.core.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.livraria0110.R

@Composable
fun BottomNavigation(
    navigationController: NavHostController
) {
    val navBackStackEntry by navigationController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val screens = listOf(
        Screen("livros", "Livros", R.drawable.ic_book_24),
        Screen("autores", "Autores", R.drawable.ic_person_24)
    )

    if (currentRoute in screens.map { it.route }) {
        NavigationBar {
            screens.forEach { screen ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(screen.icon),
                            contentDescription = screen.title
                        )
                    },
                    label = { Text(screen.title) },
                    selected = currentRoute == screen.route,
                    onClick = {
                        navigationController.navigate(screen.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(
                                navigationController.graph.startDestinationId
                            ) {
                                saveState = true
                            }
                        }
                    }
                )
            }
        }
    }
}
