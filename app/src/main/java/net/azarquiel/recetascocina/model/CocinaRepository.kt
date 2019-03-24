package net.azarquiel.recetascocina.model

import android.app.Application
import android.arch.lifecycle.LiveData

class CategoriaRepository(application: Application) {

    val categoriaDao = RecetaDB.getDatabase(application)!!.categoriaDao()
    // select
    val allCategorias: LiveData<List<Categoria>> = categoriaDao.getAllCategorias()
}

class RecetaRepository(application: Application){
    val recetasDao=RecetaDB.getDatabase(application)!!.recetaDao()

    fun recetasDeUnaCategoria(categoria:Int): LiveData<List<Receta>> = recetasDao.getTitulosDeUnaCategoria(categoria)
}