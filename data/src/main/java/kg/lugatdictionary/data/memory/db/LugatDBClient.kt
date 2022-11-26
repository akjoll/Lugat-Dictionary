package kg.lugatdictionary.data.memory.db

import android.app.Application
import android.content.Context
import androidx.room.Room
import kg.lugatdictionary.data.memory.db.daos.LugatDictDao
import kg.lugatdictionary.data.utils.LugatDatabaseName

fun provideAppDatabase(context: Context) = Room
    .databaseBuilder(context, AppDatabase::class.java, LugatDatabaseName)
    .createFromAsset("lugatDB.db")
    .build()

fun provideLugatDictDao(appDatabase: AppDatabase): LugatDictDao = appDatabase.lugatDao()