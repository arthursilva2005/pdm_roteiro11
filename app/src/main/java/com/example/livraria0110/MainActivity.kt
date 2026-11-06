package com.example.livraria0110

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.livraria0110.core.navigation.BottomNavigation
import com.example.livraria0110.core.navigation.Navigation
import com.example.livraria0110.ui.theme.Livraria0110Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navigationController = rememberNavController()
            val navBackStackEntry by navigationController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            Livraria0110Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigation(navigationController)
                    },
                    floatingActionButton = {
                        if (currentRoute == "livros") {
                            FloatingActionButton(
                                onClick = {
                                    navigationController.navigate("livroForm")
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_add_24),
                                    contentDescription = "Adicionar Livro"
                                )
                            }
                        }

                        if (currentRoute == "autores") {
                            FloatingActionButton(
                                onClick = {
                                    navigationController.navigate("autorForm")
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_add_24),
                                    contentDescription = "Adicionar Autor"
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Navigation(
                        navigationController = navigationController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
