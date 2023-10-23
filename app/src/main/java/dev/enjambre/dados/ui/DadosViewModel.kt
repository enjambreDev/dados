package dev.enjambre.dados.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import dev.enjambre.dados.R
import dev.enjambre.dados.model.Dado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class DadosViewModel: ViewModel() {

    val _d4State = MutableStateFlow<Dado>(Dado(R.drawable.d4cel, R.string.d4, 4))
    val d4State: StateFlow<Dado> = _d4State

    val _d6State = MutableStateFlow<Dado>(Dado(R.drawable.d6cel, R.string.d6, 6))
    val d6State: StateFlow<Dado> = _d6State

    val _d8State = MutableStateFlow<Dado>(Dado(R.drawable.d8cel, R.string.d8, 8))
    val d8State: StateFlow<Dado> = _d8State

    val _d10State = MutableStateFlow<Dado>(Dado(R.drawable.d10cel, R.string.d10, 10))
    val d10State: StateFlow<Dado> = _d10State

    val _d12State = MutableStateFlow<Dado>(Dado(R.drawable.d12cel, R.string.d12, 12))
    val d12State: StateFlow<Dado> = _d12State

    val _d20State = MutableStateFlow<Dado>(Dado(R.drawable.d20cel, R.string.d20, 20))
    val d20State: StateFlow<Dado> = _d20State

    fun tirarDado(lados: Int) {
        val dadoState: MutableStateFlow<Dado> = when (lados) {
            4 -> _d4State
            6 -> _d6State
            8 -> _d8State
            10 -> _d10State
            12 -> _d12State
            else -> _d20State
        }
        dadoState.update { it.copy(currentValue = randomResult(lados)) }
    }

    /*
   Using this custom function instead of the one provided by the language because there is a subtle
   bug which makes the sequence exactly the same everytime. IE, if we use the language function and
   throw the 6 sides dice 10 times in a row, then we exit the app, open it again and trhow again the
   6 sides dice 10 times, the results will be exactly the same and in the same order than the last
   time.
   */
    private fun randomResult(lados: Int): Int {
        val seed = System.currentTimeMillis()
        val random = Random(seed)
        return random.nextInt(lados) + 1
    }
}