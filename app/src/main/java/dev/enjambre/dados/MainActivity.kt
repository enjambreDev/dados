package dev.enjambre.dados

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.enjambre.dados.ui.navigation.DadosNavHost
import dev.enjambre.dados.ui.screens.DadosScreen
import dev.enjambre.dados.ui.theme.DadosTheme
import dev.enjambre.dados.ui.screens.DadosViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        setContent {
            DadosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val dadosViewModel = DadosViewModel()
                    val navHostController: NavHostController = rememberNavController()
                    DadosNavHost(
                        navHostController =navHostController,
                        dadosViewModel = dadosViewModel,
                        vibrator = vibrator
                    )
                }
            }
        }
    }
}