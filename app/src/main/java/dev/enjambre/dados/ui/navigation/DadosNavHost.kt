package dev.enjambre.dados.ui.navigation

import android.os.Vibrator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import dev.enjambre.dados.ui.screens.DadosDestination
import dev.enjambre.dados.ui.screens.DadosScreen
import dev.enjambre.dados.ui.screens.DadosViewModel
import dev.enjambre.dados.ui.screens.InformationDestination
import dev.enjambre.dados.ui.screens.InformationScreen

@Composable
fun DadosNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    dadosViewModel: DadosViewModel,
    vibrator: Vibrator
) {
    NavHost(
        navController = navHostController,
        startDestination = DadosDestination.route
    ) {
        composable(
            route = DadosDestination.route
        ) {
            DadosScreen(
                dadosViewModel = dadosViewModel,
                vibrator = vibrator,
                navigateToInfo = { navHostController.navigate(InformationDestination.route) })
        }
        composable(
            route = InformationDestination.route
        ) {
            InformationScreen()
        }
    }
}