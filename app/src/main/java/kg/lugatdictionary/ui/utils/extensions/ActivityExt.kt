package kg.lugatdictionary.ui.utils.extensions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import kg.lugatdictionary.R

val fragmentsToHideBottomNavOn = hashSetOf(
    -1
)

fun Activity.safeOpenAppInPlayMarket(onException: (Exception) -> Unit) {
    try {
        openAppInPlayMarket()
    } catch (e: Exception) {
        onException(e)
    }
}

private fun Activity.openAppInPlayMarket() {
    startActivity(
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://play.google.com/store/apps/details?id=kg.lugatdictionary")
        )
    )
}

fun Activity.snackbar(
    message: String,
    view: View = window.decorView.rootView,
    duration: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar.make(
        view,
        message,
        duration
    ).show()
}

fun Activity.snackbar(
    @StringRes message: Int,
    view: View = window.decorView.rootView,
    duration: Int = Snackbar.LENGTH_SHORT
) {
    snackbar(getString(message), view, duration)
}