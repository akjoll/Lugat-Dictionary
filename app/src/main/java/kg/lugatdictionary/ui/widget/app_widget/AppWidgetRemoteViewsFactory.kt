package kg.lugatdictionary.ui.widget.app_widget

import android.content.Context
import android.database.Cursor
import android.widget.RemoteViews
import android.widget.RemoteViewsService

class AppWidgetRemoteViewsFactory : RemoteViewsService.RemoteViewsFactory {

    private val context: Context? = null
    private val cursor: Cursor? = null

    override fun onCreate() {

    }

    override fun onDataSetChanged() {
        cursor?.close()
    }

    override fun onDestroy() {
        cursor?.close()
    }

    override fun getCount(): Int {
        return cursor?.count ?: 0
    }

    override fun getViewAt(position: Int): RemoteViews {
        TODO("Not yet implemented")
    }

    override fun getLoadingView(): RemoteViews {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }

    override fun hasStableIds(): Boolean {
        TODO("Not yet implemented")
    }
}