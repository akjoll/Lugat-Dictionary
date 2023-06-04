package kg.lugatdictionary

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.opengl.Visibility
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import kg.lugatdictionary.data.memory.db.AppDatabase
import kg.lugatdictionary.data.memory.db.provideAppDatabase
import kg.lugatdictionary.data.memory.db.provideLugatDictDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Implementation of App Widget functionality.
 */
class LugatWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {

    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    Log.e("updateAppWidget", "invoked")
    val views = RemoteViews(context.packageName, R.layout.lugat_widget)
    val layoutViews = listOf(R.id.tv_word1, R.id.tv_explanation1,R.id.tv_word2, R.id.tv_explanation2, R.id.tv_word3, R.id.tv_explanation3, R.id.tv_word4, R.id.tv_explanation4)

    layoutViews.forEach {
        views.setViewVisibility(R.id.tv_word1, View.GONE)
        views.setTextViewText(it, "")
    }

    val database = provideAppDatabase(context)
    val dao = provideLugatDictDao(database)

    val widgets = dao.getWidgets()

    GlobalScope.launch {
        widgets.collect {
            it.forEachIndexed { index, widgetLocal ->
                when(index){
                    0 -> {
                        views.setViewVisibility(R.id.tv_word1, View.VISIBLE)
                        views.setViewVisibility(R.id.tv_explanation1, View.VISIBLE)
                        views.setTextViewText(R.id.tv_word1, widgetLocal.word)
                        views.setTextViewText(R.id.tv_explanation1, widgetLocal.explanation)
                    }
                    1 -> {
                        views.setViewVisibility(R.id.tv_word2, View.VISIBLE)
                        views.setViewVisibility(R.id.tv_explanation2, View.VISIBLE)
                        views.setTextViewText(R.id.tv_word2, widgetLocal.word)
                        views.setTextViewText(R.id.tv_explanation2, widgetLocal.explanation)
                    }
                    2 -> {
                        views.setViewVisibility(R.id.tv_word3, View.VISIBLE)
                        views.setViewVisibility(R.id.tv_explanation3, View.VISIBLE)
                        views.setTextViewText(R.id.tv_word3, widgetLocal.word)
                        views.setTextViewText(R.id.tv_explanation3, widgetLocal.explanation)
                    }
                    3 -> {
                        views.setViewVisibility(R.id.tv_word4, View.VISIBLE)
                        views.setViewVisibility(R.id.tv_explanation4, View.VISIBLE)
                        views.setTextViewText(R.id.tv_word4, widgetLocal.word)
                        views.setTextViewText(R.id.tv_explanation4, widgetLocal.explanation)
                    }
                }
            }
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }


    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
