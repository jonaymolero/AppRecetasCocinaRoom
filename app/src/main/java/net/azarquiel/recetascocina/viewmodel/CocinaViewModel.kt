package net.azarquiel.recetascocina.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import net.azarquiel.recetascocina.model.Categoria
import net.azarquiel.recetascocina.model.CategoriaRepository
import net.azarquiel.recetascocina.model.Receta
import net.azarquiel.recetascocina.model.RecetaRepository

class CategoriaViewModel (application: Application) : AndroidViewModel(application) {
    private var repository: CategoriaRepository = CategoriaRepository(application)

    var allCategorias: LiveData<List<Categoria>> = repository.allCategorias
}

class RecetaViewModel (application: Application) : AndroidViewModel(application) {
    private var repository:RecetaRepository= RecetaRepository(application)

    fun recetasDeUnaCategoria(categoria:Int): LiveData<List<Receta>> = repository.recetasDeUnaCategoria(categoria)
}