package dev.enjambre.dados.ui.theme

import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import dev.enjambre.dados.R
import dev.enjambre.dados.model.Dado
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DadosScreen(dadosViewModel: DadosViewModel, vibrator: Vibrator) {

    val d4State = dadosViewModel.d4State.collectAsState()
    val d6State = dadosViewModel.d6State.collectAsState()
    val d8State = dadosViewModel.d8State.collectAsState()
    val d10State = dadosViewModel.d10State.collectAsState()
    val d12State = dadosViewModel.d12State.collectAsState()
    val d20State = dadosViewModel.d20State.collectAsState()

    Scaffold(topBar = { DadosAppBar() }) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .padding(top = dimensionResource(R.dimen.big_padding))
            ) {
                DadoUI(dadosViewModel, d4State, modifier = Modifier.weight(1.0F), vibrator)
                DadoUI(dadosViewModel, d6State, modifier = Modifier.weight(1.0F), vibrator)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
            ) {
                DadoUI(dadosViewModel, d8State, modifier = Modifier.weight(1.0F), vibrator)
                DadoUI(dadosViewModel, d10State, modifier = Modifier.weight(1.0F), vibrator)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
            ) {
                DadoUI(dadosViewModel, d12State, modifier = Modifier.weight(1.0F), vibrator)
                DadoUI(dadosViewModel, d20State, modifier = Modifier.weight(1.0F), vibrator)
            }
        }
    }
}

@Composable
fun DadosAppBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.normal_padding))
    ) {
        Image(
            painter = painterResource(R.drawable.d4cel),
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
        Image(
            painter = painterResource(R.drawable.d6cel),
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
        Image(
            painter = painterResource(R.drawable.d8cel),
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.padding(start = 16.dp, end = 10.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Image(
            painter = painterResource(R.drawable.d10cel),
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
        Image(
            painter = painterResource(R.drawable.d12cel),
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
        Image(
            painter = painterResource(R.drawable.d20cel),
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
    }
}

@Composable
fun DadoUI(
    dadosViewModel: DadosViewModel,
    dadoState: State<Dado>,
    modifier: Modifier = Modifier,
    vibrator: Vibrator
) {

    val scope = rememberCoroutineScope()
    val dadoRotation = remember { androidx.compose.animation.core.Animatable(0f) }
    val dadoScale = remember { androidx.compose.animation.core.Animatable(1f) }

    val interactionSource = remember { MutableInteractionSource() }

    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clickable(interactionSource = interactionSource, indication = null) {

                    //Este if evita una nueva tirada antes de que acaben las animaciones de la anterior,
                    // porque el tamaño del drawable aumenta incrementalmente si permitimos varios
                    // toques antes de finalizar las animaciones.
                    if (dadoScale.value == 1.0f) {
                        dadosViewModel.tirarDado(dadoState.value.lados)

                        //Inicio animaciones
                        scope.launch {
                            dadoRotation.animateTo(
                                targetValue = dadoRotation.value.plus(
                                    Random(System.currentTimeMillis())
                                        .nextFloat()
                                        .times(360f)
                                        .plus(360f)
                                ),
                                animationSpec = tween(900, easing = LinearOutSlowInEasing)
                            )
                        }
                        scope.launch {
                            dadoScale.animateTo(
                                targetValue = dadoScale.value.plus(1f),
                                animationSpec = tween(150, easing = LinearOutSlowInEasing)
                            )
                            dadoScale.animateTo(
                                targetValue = dadoScale.value.minus(1f),
                                animationSpec = tween(150, easing = LinearOutSlowInEasing)
                            )
                            vibrator.vibrate(
                                VibrationEffect.createOneShot(
                                    50,
                                    VibrationEffect.DEFAULT_AMPLITUDE
                                )
                            )
                            dadoScale.animateTo(
                                targetValue = dadoScale.value.plus(0.2f),
                                animationSpec = tween(50, easing = LinearOutSlowInEasing)
                            )
                            dadoScale.animateTo(
                                targetValue = dadoScale.value.minus(0.2f),
                                animationSpec = tween(50, easing = LinearOutSlowInEasing)
                            )
                            vibrator.vibrate(
                                VibrationEffect.createOneShot(
                                    50,
                                    VibrationEffect.DEFAULT_AMPLITUDE
                                )
                            )
                        }
                        //fin animaciones

                    }
                }
                .rotate(dadoRotation.value)
                .scale(dadoScale.value)
        ) {
            Image(
                painter = painterResource(dadoState.value.drawableRes),
                contentDescription = stringResource(dadoState.value.contentDescription),
                modifier = modifier.align(Alignment.Center)
                    .padding(dimensionResource(R.dimen.normal_padding))
            )
            Text(
                text = dadoState.value.currentValue.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayLarge,
                textDecoration = if (dadoState.value.currentValue in listOf(
                        6,
                        9
                    )
                ) TextDecoration.Underline else TextDecoration.None,
                modifier = modifier
                    .align(Alignment.Center)
            )
        }
        Text(text = dadoState.value.lados.toString(), textAlign = TextAlign.Center, style = MaterialTheme.typography.labelMedium)
    }
}