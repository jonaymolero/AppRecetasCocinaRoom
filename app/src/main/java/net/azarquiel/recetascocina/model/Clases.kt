package net.azarquiel.recetascocina.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "categoria")
data class Categoria(@PrimaryKey(autoGenerate = true)
                     @ColumnInfo(name = "id") // nombre en tabla
                     var id: Int?,          // atributo en entity
                     @ColumnInfo(name = "nombre")
                     var nombre:String):Serializable

@Entity(tableName = "receta",
        foreignKeys = arrayOf(ForeignKey(entity = Categoria::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("categoria"))))
data class Receta(@PrimaryKey(autoGenerate = true)
                  @ColumnInfo(name = "id")
                  var id: Int?,
                  @ColumnInfo(name = "categoria")
                  var categoria: Int,
                  @ColumnInfo(name = "titulo")
                  var titulo: String,
                  @ColumnInfo(name = "foto")
                  var foto: String,
                  @ColumnInfo(name = "pasos")
                  var pasos: String,
                  @ColumnInfo(name = "ingredientes")
                  var ingredientes: String,
                  @ColumnInfo(name = "comensales")
                  var comensales: String,
                  @ColumnInfo(name = "dificultad")
                  var dificultad: String,
                  @ColumnInfo(name = "precio")
                  var precio: String,
                  @ColumnInfo(name = "tiempo")
                  var tiempo: String):Serializable