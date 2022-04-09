package kg.lugatdictionary.data.memory

import android.content.SharedPreferences


class LugatSharedPreferences(
    sharedPreferences: SharedPreferences
) : SharedPreferences by sharedPreferences  {

    companion object {
        const val NAME = "kg.lugatdictionary"
    }

    internal fun setString(pair: Pair<String, String>) {
        edit().putString(pair.first, pair.second)
            .apply()
    }


}