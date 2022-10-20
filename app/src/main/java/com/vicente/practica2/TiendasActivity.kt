package com.vicente.practica2.clases

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vicente.practica2.MainActivity
import com.vicente.practica2.R
import com.vicente.practica2.databinding.ActivityTiendasBinding
import com.vicente.practica2.databinding.ActivityTiendasBinding.inflate

class TiendasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTiendasBinding

    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityTiendasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var centroComercial1: CentroComercial? = null
        var centroComercial2: CentroComercial? = null
        var centroComercial3: CentroComercial? = null
        var centroComercial4: CentroComercial? = null



        var tienda1 = Tienda(1, "Zara", "Vender ropa.")
        var tienda2 = Tienda(2,"Jiafei Shop", "Vender productos del hogar.")
        var tienda3 = Tienda(3,"Fnac", "Vender tecnología, libros, música...")
        var tienda4 = Tienda(4,"Carl's Jr.", "Restaurante de comida rápida.")

        var tiendas = mutableListOf<Tienda>()
        tiendas.add(tienda1)
        tiendas.add(tienda2)
        tiendas.add(tienda3)
        tiendas.add(tienda4)

        centroComercial2 = CentroComercial(1,"C.C. Bonaire","Autovía del Este, Km. 345, 46960, Valencia", 4, tiendas)
        centroComercial1 = CentroComercial(2,"C.C. MN4","C. Alcalde José Puertes, 46910 Alfafar, Valencia", 4, tiendas)
        centroComercial3 = CentroComercial(3,"C.C Aqua","Carrer de Menorca, 19, 46023 València, Valencia",4, tiendas)
        centroComercial4 = CentroComercial(4,"C.C El Saler","Av. del Professor López Piñero, 16, 46013 València, Valencia",4, tiendas)

        var centrosComerciales = mutableListOf<CentroComercial>()

        centrosComerciales.add(centroComercial1)
        centrosComerciales.add(centroComercial2)
        centrosComerciales.add(centroComercial3)
        centrosComerciales.add(centroComercial4)

        val extra = intent.extras

        if (extra != null){
            val idcc= extra.getString("idcc")!!.toInt()

            for ((indexcc,cc) in centrosComerciales.withIndex()){
                if (cc.id == idcc){
                    binding.content2.tvTituloTiendas.text = (cc.nombre)

                    for ((index, tienda) in cc.lista_tiendas.withIndex()){
                        if (index == 0){
                            binding.content2.tvNomTienda1.text = (tienda.nombre)
                            binding.content2.tvDesTienda1.text = (tienda.descripcion)
                        }

                        if (index == 1){
                            binding.content2.tvNomTienda2.text = (tienda.nombre)
                            binding.content2.tvDesTienda2.text = (tienda.descripcion)
                        }

                        if (index == 2){
                            binding.content2.tvNomTienda3.text = (tienda.nombre)
                            binding.content2.tvDesTienda3.text = (tienda.descripcion)
                        }

                        if (index == 3){
                            binding.content2.tvNomTienda4.text = (tienda.nombre)
                            binding.content2.tvDesTienda4.text = (tienda.descripcion)
                        }
                    }

                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        mediaPlayer = MediaPlayer.create(this, R.raw.ai_2)
    }

    override fun onResume() {
        super.onResume()

        mediaPlayer?.seekTo(position)
        mediaPlayer?.start()
    }

    override fun onPause() {
        super.onPause()

        mediaPlayer?.pause()
        if (mediaPlayer != null) {
            position = mediaPlayer!!.currentPosition
        }
    }

    override fun onStop() {
        super.onStop()

        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val returnIntent = Intent()
        returnIntent.putExtra("musicPosition", position)
        setResult(RESULT_OK, returnIntent)
        finish()
    }
}