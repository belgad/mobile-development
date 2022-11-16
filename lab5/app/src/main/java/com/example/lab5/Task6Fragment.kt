package com.example.lab5

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.lab5.databinding.FragmentTask6Binding

class Task6Fragment : Fragment() {
    private lateinit var binding: FragmentTask6Binding
    private lateinit var data: HashSet<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTask6Binding.inflate(inflater)

        val settings = requireActivity().getPreferences(Context.MODE_PRIVATE)
        data = HashSet(settings.getStringSet("task6Data", hashSetOf())!!)

        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1)
        adapter.addAll(data)

        binding.task6Button.setOnClickListener {
            binding.task6Edittext.text.toString().let {
                if ((it != "") and (it !in data)) {
                    data.add(it)
                    adapter.add(it)
                    settings.edit {
                        putStringSet("task6Data", data)
                        commit()
                    }
                }
            }
            binding.task6Edittext.setText("")
            requireActivity().currentFocus?.let {
                val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(it.windowToken, 0)
                it.clearFocus()
            }
        }
        binding.task6Listview.setOnItemClickListener { _, _, position, _ ->
            adapter.getItem(position)?.let {
                data.remove(it)
                adapter.remove(it)
            }
            settings.edit {
                putStringSet("task6Data", data)
                commit()
            }
        }

        binding.task6Listview.adapter = adapter

        return binding.root
    }
}