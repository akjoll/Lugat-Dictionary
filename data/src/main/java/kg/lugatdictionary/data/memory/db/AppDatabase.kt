package kg.lugatdictionary.data.memory.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import kg.lugatdictionary.data.memory.db.daos.LugatDictDao
import kg.lugatdictionary.data.memory.db.entities.FavoriteLocal
import kg.lugatdictionary.data.memory.db.entities.HistoryLocal
import kg.lugatdictionary.data.memory.db.entities.WidgetLocal
import kg.lugatdictionary.data.memory.db.entities.WordLocal

@Database(entities = [FavoriteLocal::class, HistoryLocal::class, WidgetLocal::class, WordLocal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun lugatDao(): LugatDictDao
}