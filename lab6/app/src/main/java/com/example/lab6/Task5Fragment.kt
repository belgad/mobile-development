package com.example.lab6

import android.graphics.Color
import android.os.Bundle
import android.view.GestureDetector
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6.databinding.FragmentTask5Binding

class Task5Fragment : Fragment() {
    private lateinit var binding: FragmentTask5Binding
    private lateinit var data: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask5Binding.inflate(inflater)

        data = arrayOf("Item 1", "Item 2", "Item 3")
        binding.task5RecyclerView.adapter = CustomAdapter()

        binding.task5RecyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            private val gd = GestureDetector(requireContext(),
                object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent?): Boolean {
                    return true
                }
            })

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                rv.findChildViewUnder(e.x, e.y)?.let { cv ->
                    if (gd.onTouchEvent(e)) {
                        val position = rv.getChildAdapterPosition(cv)
                        val newButton = Button(requireContext())
                        newButton.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT).apply { gravity = Gravity.CENTER_HORIZONTAL }
                        newButton.text = data[position]
                        var color = Color.CYAN
                        try {
                            color = Color.parseColor("#" + binding.task5Edittext.text.toString())
                        } catch(_: IllegalArgumentException) {} finally {
                            newButton.setBackgroundColor(color)
                        }
                        newButton.setOnClickListener { btn ->
                            btn.visibility = View.GONE
                        }
                        binding.root.addView(newButton)
                    }
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })

        return binding.root
    }

    private inner class CustomAdapter : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById(android.R.id.text1)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(requireContext()).inflate(android.R.layout.simple_list_item_1, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = data[position]
        }

        override fun getItemCount(): Int = data.size
    }
}