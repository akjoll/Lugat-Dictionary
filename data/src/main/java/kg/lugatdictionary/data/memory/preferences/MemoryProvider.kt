package kg.lugatdictionary.data.memory

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import kg.lugatdictionary.data.memory.preferences.LugatSharedPreferences

fun provideLugatSharedPref(app: Application): SharedPreferences =
    app.getSharedPreferences(
        LugatSharedPreferences.NAME,
        Context.MODE_PRIVATE
    )