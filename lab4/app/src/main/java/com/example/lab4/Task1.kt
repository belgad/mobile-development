package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.*

class Task1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task1_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.task1_fragment_container, Task1Fragment())
            }
        }
    }
}

class Task1Fragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.task1_fragment, container, false)

        val button_next = view.findViewById<Button>(R.id.task1_button_next)
        val button_back = view.findViewById<Button>(R.id.task1_button_back)
        val textview = view.findViewById<TextView>(R.id.task1_textview)

        textview.text = "Stack size: " + (parentFragmentManager.backStackEntryCount + 1)

        button_next.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.task1_fragment_container, Task1Fragment())
                addToBackStack(null)
            }
        }
        button_back.setOnClickListener {
            if (parentFragmentManager.backStackEntryCount == 0) {
                activity?.finish()
            }
            parentFragmentManager.popBackStack()
        }

        return view
    }
}