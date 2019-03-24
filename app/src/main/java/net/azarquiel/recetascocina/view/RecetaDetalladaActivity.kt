package net.azarquiel.recetascocina.view

import android.app.AlertDialog
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_receta_detallada.*
import kotlinx.android.synthetic.main.content_receta_detallada.*
import kotlinx.android.synthetic.main.dialogo_imagen.view.*
import net.azarquiel.recetascocina.R
import net.azarquiel.recetascocina.databinding.ActivityRecetaDetalladaBinding
import net.azarquiel.recetascocina.model.Receta

class RecetaDetalladaActivity : AppCompatActivity() {

    private lateinit var binging: ActivityRecetaDetalladaBinding
    private lateinit var receta: Receta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_receta_detallada)
        setSupportActionBar(toolbar)
        binging=DataBindingUtil.setContentView(this,R.layout.activity_receta_detallada)
        cvImagen.setCardBackgroundColor(Color.argb(200,200,200,255))
        cvInformacionReceta.setCardBackgroundColor(Color.argb(200,234,234,234))
        receta=intent.getSerializableExtra("recetapulsada") as Receta
        binging.receta=receta
        cvImagen.setOnClickListener{pulsarImagen()}
    }

    private fun pulsarImagen() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val loginLayout = inflater.inflate(R.layout.dialogo_imagen, null)
        Picasso.with(this).load("http://www.mor.ninja/mobil/app_resim/yemek/yemek_es/b/${receta.foto}").into(loginLayout.ivFotoPlato)
        if(receta.foto.equals("")){
            loginLayout.ivFotoPlato.setImageResource(R.drawable.imagenotfound)
        }
        builder.setView(loginLayout)
        builder.show()
    }

}
