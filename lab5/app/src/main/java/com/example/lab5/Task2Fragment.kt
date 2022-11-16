package com.example.lab5

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lab5.databinding.FragmentTask2Binding

class Task2Fragment : Fragment() {
    private lateinit var binding: FragmentTask2Binding
    private var listViewData: ArrayList<Int> = arrayListOf()
    private var counter: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTask2Binding.inflate(inflater)

        val adapter = Task2ListViewAdapter(requireContext(), android.R.layout.simple_list_item_1, listViewData)
        binding.task2Listview.adapter = adapter
        binding.task2Listview.setOnItemClickListener { _, _, position, _ ->
            listViewData.remove(adapter.getItem(position))
            adapter.notifyDataSetChanged()
        }
        binding.task2Button.setOnClickListener {
            listViewData.add(++counter)
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }

    class Task2ListViewAdapter(context: Context, resource: Int, objects: ArrayList<Int>) :
        ArrayAdapter<Int>(context, resource, objects) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = super.getView(position, convertView, parent) as TextView
            view.text = "Item #${getItem(position)}"
            return view
        }
    }
}