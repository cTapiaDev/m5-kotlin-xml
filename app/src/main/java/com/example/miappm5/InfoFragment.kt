package com.example.miappm5

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.miappm5.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(titulo: String, colorFondo: Int) = InfoFragment().apply {
            arguments = Bundle().apply {
                putString("ARG_TITLE", titulo)
                putInt("ARG_COLOR", colorFondo)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titulo = arguments?.getString("ARG_TITLE") ?: "Sin nombre"
        val color = arguments?.getInt("ARG_COLOR") ?: Color.WHITE

        binding.tvTitle.text = titulo
        view.setBackgroundColor(color)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}