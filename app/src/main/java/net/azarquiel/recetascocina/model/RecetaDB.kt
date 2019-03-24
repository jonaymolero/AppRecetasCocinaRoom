package net.azarquiel.recetascocina.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room
import android.content.Context

@Database(entities = [Categoria::class, Receta::class], version = 1)
abstract class RecetaDB: RoomDatabase() {
    abstract fun categoriaDao(): CategoriasDao
    abstract fun recetaDao():RecetasDao
    companion object {
        @Volatile
        private var INSTANCE: RecetaDB? = null

        fun getDatabase(context: Context): RecetaDB? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            RecetaDB::class.java, "recetas.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}