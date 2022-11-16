package com.example.lab5

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lab5.databinding.FragmentTask1Binding

class Task1Fragment : Fragment() {
    private lateinit var binding: FragmentTask1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTask1Binding.inflate(inflater)

        val data: Array<String> = arrayOf("#000000", "#ff0000", "#00ff00", "#0000ff", "#ffffff")
        val adapter = Task1ListViewAdapter(requireContext(), R.layout.fragment_task1_list_item, data)
        binding.task1Listview.adapter = adapter

        return binding.root
    }

    class Task1ListViewAdapter(context: Context, resource: Int, objects: Array<String>) :
        ArrayAdapter<String>(context, resource, objects) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = super.getView(position, convertView, parent) as TextView
            val color = Color.parseColor(view.text.toString())
            if (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Color.luminance(color) > 0.5
                } else {
                    true
                }
            ) {
                view.setTextColor(Color.BLACK)
            } else view.setTextColor(Color.WHITE)
            view.setBackgroundColor(color)
            return view
        }
    }
}