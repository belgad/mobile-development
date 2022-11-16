package com.example.lab5

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.content.edit
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.lab5.databinding.FragmentTask7Binding

class Task7Fragment : Fragment() {
    private lateinit var binding: FragmentTask7Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTask7Binding.inflate(inflater)

        val settings = requireActivity().getPreferences(Context.MODE_PRIVATE)
        binding.task7Edittext.setText(settings.getString("task7Edittext", ""))
        binding.task7Checkbox.isChecked = settings.getBoolean("task7Checkbox", false)
        binding.task7Edittext.doOnTextChanged { text, _, _, _ ->
            settings.edit {
                putString("task7Edittext", text.toString())
                apply()
            }
        }
//        binding.task7Checkbox.isClickable = false
//        binding.task7CheckboxLayout.setOnClickListener {
//            binding.task7Checkbox.toggle()
//            settings.edit {
//                putBoolean("task7Checkbox", binding.task7Checkbox.isChecked)
//                apply()
//            }
//        }
        binding.task7Checkbox.setOnClickListener {
            settings.edit {
                putBoolean("task7Checkbox", (it as CheckBox).isChecked)
                apply()
            }
        }

        return binding.root
    }
}