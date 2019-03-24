package net.azarquiel.recetascocina.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
interface CategoriasDao{
    @Query("SELECT * from categoria order by id ASC")
    fun getAllCategorias(): LiveData<List<Categoria>>
}

@Dao
interface RecetasDao{
    @Query("SELECT * from receta where categoria=:categoria")
    fun getTitulosDeUnaCategoria(categoria:Int): LiveData<List<Receta>>

    @Query("SELECT * from receta where id=:id")
    fun getInformacionReceta(id:Int): Receta
}