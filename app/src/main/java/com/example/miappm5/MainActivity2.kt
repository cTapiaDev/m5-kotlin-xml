package com.example.miappm5

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miappm5.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    data class Empleado(val nombre: String, val area: String, val sueldo: Double)

    private val empleados = listOf(
        Empleado("Luis", "Diseño", 1500.0),
        Empleado("Marta", "Desarrollo", 2500.0),
        Empleado("Juan", "RRHH", 1800.0),
        Empleado("Fernanda", "Desarrollo", 3000.0),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            val area = binding.etSearch.text.toString()
            filtrarYMostrar(area)
        }
    }

    private fun filtrarYMostrar(area: String) {
        val filtrados = empleados.filter {
            it.area.contains(area, ignoreCase = true)
        }

        if (filtrados.isEmpty()) {
            binding.tvResult.text = "No hay empleados en esa area"
            return
        }

        val totalSueldos = filtrados.sumOf { it.sueldo }

        val nombres = filtrados.joinToString(", ") { it.nombre }

        binding.tvResult.text = "Empleados: $nombres \nGasto Total: $totalSueldos"
    }
}