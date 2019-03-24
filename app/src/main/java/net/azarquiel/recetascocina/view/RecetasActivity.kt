package net.azarquiel.recetascocina.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_recetas.*
import kotlinx.android.synthetic.main.content_recetas.*
import net.azarquiel.recetascocina.R
import net.azarquiel.recetascocina.adapter.CustomAdapterRecetas
import net.azarquiel.recetascocina.model.Categoria
import net.azarquiel.recetascocina.model.Receta
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import net.azarquiel.recetascocina.viewmodel.RecetaViewModel

class RecetasActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var categoria:Categoria
    private lateinit var adapter:CustomAdapterRecetas
    private lateinit var recetaViewModel: RecetaViewModel
    private lateinit var listaRecetas:ArrayList<Receta>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recetas)
        setSupportActionBar(toolbar)
        listaRecetas= ArrayList()
        categoria=intent.getSerializableExtra("categoriaPulsada") as Categoria
        title=categoria.nombre
        recetaViewModel = ViewModelProviders.of(this).get(RecetaViewModel::class.java)
        crearAdapter()
        recetaViewModel.recetasDeUnaCategoria(categoria.id!!).observe(this, Observer { recetas ->
            // Update the cached copy of the products in the adapter.
            recetas?.let {
                listaRecetas.addAll(it)
                adapter.setrecetas(listaRecetas)
            }
        })

    }

    private fun crearAdapter() {
        adapter= CustomAdapterRecetas(this, R.layout.rowrecetas)
        rvRecetas.layoutManager=LinearLayoutManager(this)
        rvRecetas.adapter=adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_categorias, menu)
        // ************* <Filtro> ************
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Search...")
        searchView.setOnQueryTextListener(this)
        // ************* </Filtro> ************

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        adapter.setrecetas(listaRecetas.filter { p -> p.titulo.toLowerCase().contains(query!!.toLowerCase()) })
        return false

    }

    fun pulsaRecetaDetallada(view:View){
        var receta=view.tag as Receta
        var intent= Intent(this,RecetaDetalladaActivity::class.java)
        intent.putExtra("recetapulsada",receta)
        startActivity(intent)
    }
}
