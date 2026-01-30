package com.example.miappm5

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miappm5.databinding.ActivityCicloVidaBinding

class CicloVidaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCicloVidaBinding
    private var eventCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCicloVidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState?.let { bundle ->
            val logsViejos = bundle.getString("SAVED_LOGS", "")
            eventCount = bundle.getInt("SAVED_COUNT", 0)
            binding.tvConsole.text = logsViejos
            updateConsole("DATOS RESTAURADOS POST-ROTACIÓN")
        }

        updateConsole("onCreate: Memoria asignada. La UI se está inflando.")

        binding.btnClear.setOnClickListener {
            binding.tvConsole.text = "Consola limpia."
            eventCount = 0
        }
    }

    private fun updateConsole(msg: String) {
        eventCount++
        val currentText = binding.tvConsole.text.toString()
        val log = "\n[$eventCount] $msg"
        binding.tvConsole.text = currentText + log

        binding.scrollView.post {
            binding.scrollView.fullScroll(View.FOCUS_DOWN)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val textActual = binding.tvConsole.text.toString()
        outState.putString("SAVED_LOGS", textActual)
        outState.putInt("SAVED_COUNT", eventCount)
    }

    override fun onStart() {
        super.onStart()
        updateConsole("onStart: La pantalla es visible.")
    }

    override fun onResume() {
        super.onResume()
        updateConsole("onResume: ¡LISTO! Puedes interactuar.")
    }

    override fun onPause() {
        super.onPause()
        updateConsole("onPause: Perdiendo el foco...")
    }

    override fun onStop() {
        super.onStop()
        updateConsole("onStop: App oculta. Nos vamos a segundo plano.")
    }

    override fun onRestart() {
        super.onRestart()
        updateConsole("onRestart: Volviendo de segundo plano.")
    }

    override fun onDestroy() {
        super.onDestroy()
        updateConsole("onDestroy: ¡DESTROOOY!")
        Log.d("LIFECYCLE", "onDestroy: Memoria liberada.")
    }
}