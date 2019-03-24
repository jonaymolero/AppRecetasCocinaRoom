package net.azarquiel.recetascocina.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.widget.SearchView
import android.view.View

import kotlinx.android.synthetic.main.activity_categorias.*
import kotlinx.android.synthetic.main.content_categorias.*
import net.azarquiel.recetascocina.R
import net.azarquiel.recetascocina.adapter.CustomAdapterCategorias
import net.azarquiel.recetascocina.model.Categoria
import net.azarquiel.recetascocina.util.Util
import net.azarquiel.recetascocina.viewmodel.CategoriaViewModel

class CategoriasActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var adapter:CustomAdapterCategorias
    private lateinit var viewModelCategorias: CategoriaViewModel
    private lateinit var listaCategorias:ArrayList<Categoria>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorias)
        setSupportActionBar(toolbar)
        Util.inyecta(this)
        title="Categorias"
        listaCategorias=ArrayList()
        viewModelCategorias= ViewModelProviders.of(this).get(CategoriaViewModel::class.java)
        createAdapter()
        viewModelCategorias.allCategorias.observe(this, Observer { categorias ->
            // Update the cached copy of the products in the adapter.
            categorias?.let {
                listaCategorias.addAll(it)
                adapter.setCategorias(listaCategorias)
            }
        })
    }

    private fun createAdapter() {
        adapter= CustomAdapterCategorias(this,R.layout.rowcategorias)
        rvCategorias.layoutManager=LinearLayoutManager(this)
        rvCategorias.adapter=adapter
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
        adapter.setCategorias(listaCategorias.filter { p -> p.nombre.toLowerCase().contains(query!!.toLowerCase()) })
        return false

    }

    fun pulsarCategoria(view:View){
        var categoria=view.tag as Categoria
        var intent= Intent(this, RecetasActivity::class.java)
        intent.putExtra("categoriaPulsada",categoria)
        startActivity(intent)
    }
}
