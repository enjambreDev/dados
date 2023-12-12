package dev.enjambre.dados.ui.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import dev.enjambre.dados.R
import dev.enjambre.dados.domain.openUrl
import dev.enjambre.dados.ui.navigation.NavigationDestination

object InformationDestination: NavigationDestination {
    override val route: String = "informacion"
    override val titleResource: Int = R.string.informacion

}

@Composable
fun InformationScreen() {
    val context: Context = LocalContext.current
    val url = stringResource(id = R.string.privacidad_url)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = stringResource(id = R.string.si_te_gusta))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(id = R.string.privacidad_titulo), style = MaterialTheme.typography.titleLarge, textDecoration = TextDecoration.Underline)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(id = R.string.privacidad_cuerpo))
        Column(modifier = Modifier.padding(vertical = 10.dp)) {
            Text(text = stringResource(id = R.string.recopilacion), modifier = Modifier.padding(vertical = 5.dp))
            Text(text = stringResource(id = R.string.conexion), modifier = Modifier.padding(vertical = 5.dp))
            Text(text = stringResource(id = R.string.publicidad), modifier = Modifier.padding(vertical = 5.dp))
        }
        Text(text = stringResource(id = R.string.contacto))
        Text(text = stringResource(id = R.string.aceptacion))
        Text(text = stringResource(id = R.string.actualizacion))
        Text(text = stringResource(id = R.string.privacidad_titulo), color = Color.Blue, textDecoration = TextDecoration.Underline, modifier = Modifier.clickable {
            openUrl(context, url)
        })
    }
}