package kg.lugatdictionary.ui.utils.extensions

import android.app.Activity
import android.content.res.Resources
import android.os.Build
import android.text.Editable
import android.text.Html
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import kg.lugatdictionary.R
import kotlinx.coroutines.*

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.setPaddings(
    left: Int = paddingLeft,
    top: Int = paddingTop,
    right: Int = paddingRight,
    bottom: Int = paddingBottom
) {
    setPadding(left, top, right, bottom)
}

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun TextView.hideKeyboard() {
    try {
        val inputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            windowToken,
            0
        )
    } catch (e: Exception) {
    }
}

fun TextView.canPlaceText(text: String): Boolean {
    val textWidth = paint.measureText(text)
    return textWidth >= measuredWidth
}

fun ImageView.downloadWithShimmer(
    res: String,
    @DrawableRes error: Int? = null,
    duration: Long = 900,
    baseAlpha: Float = 0.9f,
    highlightAlpha: Float = 0.2f
) {
    val color =
        ContextCompat.getColor(context, R.color.black)
    val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(
            getShimmer(
                duration,
                baseAlpha,
                highlightAlpha,
                color
            )
        )
    }
    Glide.with(this)
        .load(res)
        .placeholder(shimmerDrawable)
        //.error(error)
        .into(this)
}

fun getShimmer(
    duration: Long = 900,
    baseAlpha: Float = 0.9f,
    highlightAlpha: Float = 0.2f,
    @ColorInt color: Int
) = Shimmer.ColorHighlightBuilder()
    .setDuration(duration)
//    .setBaseAlpha(baseAlpha)
//    .setHighlightAlpha(highlightAlpha)
    .setHighlightColor(color)
    .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
    .setAutoStart(true)
    .build()

fun ViewGroup.inflate(
    @LayoutRes res: Int,
    attachToParent: Boolean = false
): View =
    LayoutInflater.from(context).inflate(res, this, attachToParent)

fun ImageView.setImage(url: String?) {
    Glide.with(this.context).load(url).into(this)
}

fun TextView.setTextWithBold(
    @StringRes firstRes: Int,
    @StringRes boldRes: Int
) {
    setTextWithBold(
        context.getString(firstRes),
        context.getString(boldRes)
    )
}

fun TextView.setTextWithBold(@StringRes firstRes: Int, bold: String) {
    setTextWithBold(
        context.getString(firstRes),
        bold
    )
}

fun TextView.setTextWithBold(first: String, @StringRes boldRes: Int) {
    setTextWithBold(
        first,
        context.getString(boldRes)
    )
}

fun TextView.setTextWithBold(first: String, bold: String) {
    text = SpannableStringBuilder()
        .append(first)
        .bold { append(bold) }
}

fun View.replace(newView: View) {
    try {
        val parent = parent as ViewGroup
        newView.id = id
        newView.layoutParams = layoutParams
        val index = parent.indexOfChild(this)
        parent.removeView(this)
        parent.addView(newView, index)
    } catch (e: Exception) {
    }
}

fun TextView.setHtml(html: String) {
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(html)
    }
}

inline fun <T, reified R : View> R.doAsync(
    crossinline backgroundTask: suspend () -> T?,
    crossinline onResult: R.(T?) -> Unit
) {
    val job = CoroutineScope(Dispatchers.IO)
    val attachListener = object : View.OnAttachStateChangeListener {
        override fun onViewAttachedToWindow(p0: View?) {}
        override fun onViewDetachedFromWindow(p0: View?) {
            job.cancel()
        }
    }
    addOnAttachStateChangeListener(attachListener)
    job.launch {
        val data = try {
            backgroundTask()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        if (isActive) {
            try {
                withContext(Dispatchers.Main) { onResult(data) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        removeOnAttachStateChangeListener(attachListener)
    }
}

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)