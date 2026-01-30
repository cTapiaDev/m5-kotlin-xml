package com.example.miappm5

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miappm5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProcess.setOnClickListener {
            evaluarUsuario()
        }
    }

    fun obtenerPlanMembresia(edad: Int): String {
        return when {
            edad < 0 -> "Edad no válida"
            edad < 18 -> "Plan Juvenil (50% desc)"
            edad in 18..60 -> "Plan Estándar"
            else -> "Plan Adulto Mayor (Gratis)"
        }
    }

    private fun evaluarUsuario() {
        val nombre = binding.etPlainText.text.toString()
        val edadTexto = binding.etNumber.text.toString()

        val edad = edadTexto.toIntOrNull()

        if (nombre.isBlank() || edad == null) {
            binding.tvResult.text = "Por favor, completa los campos correctamente."
            return
        }

        val membresia = obtenerPlanMembresia(edad)

        binding.tvResult.text = "Hola $nombre, te corresponde: $membresia"

        aplicarEstiloPorEdad(edad)
    }

    private fun aplicarEstiloPorEdad(edad: Int) {
        val colorRes = when {
            edad < 0 -> R.color.color_error
            edad < 18 -> R.color.color_juvenil
            edad in 18..60 -> R.color.color_estandar
            else -> R.color.color_adulto
        }

        val colorFinal = ContextCompat.getColor(this, colorRes)
        binding.tvResult.setTextColor(colorFinal)

        if (edad > 60) {
            binding.tvResult.textSize = 24f
        } else {
            binding.tvResult.textSize = 18f
        }
    }
}