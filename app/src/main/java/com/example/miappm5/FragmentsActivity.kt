package com.example.miappm5

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.miappm5.databinding.ActivityFragmentsBinding

class FragmentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProfile.setOnClickListener {
            showFragment(InfoFragment.newInstance("PERFIL DE USUARIO", Color.CYAN))
        }

        binding.btnSettings.setOnClickListener {
            showFragment(InfoFragment.newInstance("AJUSTES DEL SISTEMA", Color.LTGRAY))
        }

    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}