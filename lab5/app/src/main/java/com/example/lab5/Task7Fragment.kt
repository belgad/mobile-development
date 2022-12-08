package com.example.lab5

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import androidx.core.content.edit
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.lab5.databinding.FragmentTask7Binding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashSet

class Task7Fragment : Fragment() {
    private lateinit var binding: FragmentTask7Binding
    private lateinit var data: HashSet<String>
    private var timer: Timer? = null
    private val dateFormat = SimpleDateFormat("dd.MM.yyyy hh:mm:ss", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTask7Binding.inflate(inflater)

        val settings = requireActivity().getPreferences(Context.MODE_PRIVATE)

        data = HashSet(settings.getStringSet("task7History", hashSetOf())!!)
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1)
        adapter.addAll(data)
        binding.task7Listview.adapter = adapter

        binding.task7Edittext.setText(settings.getString("task7Edittext", ""))
        binding.task7Checkbox.isChecked = settings.getBoolean("task7Checkbox", false)
        binding.task7Edittext.doOnTextChanged { text, _, _, _ ->
            timer?.cancel()
            timer = Timer()
            timer!!.schedule(object: TimerTask() {
                override fun run() {
                    val t: String = dateFormat.format(Calendar.getInstance().apply{add(Calendar.SECOND, -2)}.time) + " Text: " + text.toString()
                    data.add(t)
                    settings.edit {
                        putStringSet("task7History", data)
                        putString("task7Edittext", text.toString())
                        commit()
                    }
                    requireActivity().runOnUiThread {
                        adapter.add(t)
                    }
                }
            }, 2000)
        }
        binding.task7Checkbox.setOnClickListener {
            val t: String = dateFormat.format(Date()) + "  Checkbox " + (if ((it as CheckBox).isChecked) "checked" else "unchecked")
            data.add(t)
            settings.edit {
                putBoolean("task7Checkbox", (it as CheckBox).isChecked)
                putStringSet("task7History", data)
                commit()
            }
            adapter.add(t)
        }

        return binding.root
    }
}