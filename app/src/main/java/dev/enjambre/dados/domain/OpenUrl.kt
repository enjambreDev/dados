package dev.enjambre.dados.domain

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import dev.enjambre.dados.R

fun openUrl(context: Context, url: String) {
    val uri = Uri.parse(url)
    val intent = Intent()
    intent.apply {
        action = Intent.ACTION_VIEW
        setData(uri)
    }
    try {
        context.startActivity(intent)
    } catch (exception: Exception) {
        Toast.makeText(
            context,
            context.getString(R.string.could_not_find_app),
            Toast.LENGTH_LONG
        ).show()
    }
}