package com.vicente.practica2

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vicente.practica2.clases.CentroComercial
import com.vicente.practica2.clases.Tienda
import com.vicente.practica2.clases.TiendasActivity
import com.vicente.practica2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load("")
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(binding.content.imgCc1)

        Glide.with(this)
            .load("")
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(binding.content.imgCc2)

        Glide.with(this)
            .load("")
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(binding.content.imgCc3)

        Glide.with(this)
            .load("")
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(binding.content.imgCc4)

        var tiendas = prepararDatos()
        var centroComercial2 = CentroComercial(
            1,
            "C.C. Bonaire",
            "Autovía del Este, Km. 345, 46960, Valencia",
            4,
            tiendas
        )
        var centroComercial1 = CentroComercial(
            2,
            "C.C. MN4",
            "C. Alcalde José Puertes, 46910 Alfafar, Valencia",
            4,
            tiendas
        )
        var centroComercial3 = CentroComercial(
            3,
            "C.C Aqua",
            "Carrer de Menorca, 19, 46023 València, Valencia",
            4,
            tiendas
        )
        var centroComercial4 = CentroComercial(
            4,
            "C.C El Saler",
            "Av. del Professor López Piñero, 16, 46013 València, Valencia",
            4,
            tiendas
        )

        binding.content.idCc1.setOnClickListener {
            val intent = Intent(this, TiendasActivity::class.java)
            intent.putExtra("idcc", centroComercial1.id.toString())
            startActivity(intent)
        }

        binding.content.idCc2.setOnClickListener {
            val intent = Intent(this, TiendasActivity::class.java)
            intent.putExtra("idcc", centroComercial2.id.toString())
            startActivity(intent)
        }

        binding.content.idCc3.setOnClickListener {
            val intent = Intent(this, TiendasActivity::class.java)
            intent.putExtra("idcc", centroComercial3.id.toString())
            startActivity(intent)
        }

        binding.content.idCc4.setOnClickListener {
            val intent = Intent(this, TiendasActivity::class.java)
            intent.putExtra("idcc", centroComercial4.id.toString())
            startActivity(intent)
        }


    }

    fun prepararDatos(): MutableList<Tienda> {

        var tienda1 = Tienda(1, "Zara", "Vender ropa.")
        var tienda2 = Tienda(2, "Jiafei Shop", "Vender productos del hogar.")
        var tienda3 = Tienda(3, "Fnac", "Vender tecnología, libros, música...")
        var tienda4 = Tienda(4, "Carl's Jr.", "Restaurante de comida rápida.")

        var tiendas = mutableListOf<Tienda>()
        tiendas.add(tienda1)
        tiendas.add(tienda2)
        tiendas.add(tienda3)
        tiendas.add(tienda4)


        return tiendas

    }

    override fun onStart() {
        super.onStart()

        mediaPlayer = MediaPlayer.create(this, R.raw.ai_2)
    }

    override fun onResume() {
        super.onResume()

        val extras = intent.extras
        if (extras != null) {
            position = extras.getInt("musicPosition")
        }

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

}

