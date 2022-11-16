package com.example.lab5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import com.example.lab5.databinding.FragmentTask4Binding
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.net.URL

class Task4Fragment : Fragment() {
    private lateinit var binding: FragmentTask4Binding
    private var listData: ArrayList<HashMap<String, String>> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTask4Binding.inflate(inflater)

        val adapter = SimpleAdapter(requireContext(),
            listData,
            R.layout.fragment_task4_list_item,
            arrayOf("num", "name", "value"),
            intArrayOf(R.id.task4_list_item_curr_num, R.id.task4_list_item_curr_name, R.id.task4_list_item_curr_value)
        )
        binding.task4List.adapter = adapter

        with(Thread {
            val parser = XmlPullParserFactory.newInstance().newPullParser()
            try {
                parser.setInput(
                    URL("https://www.cbr.ru/scripts/XML_daily.asp").openStream(),
                    null
                )
                listData.clear()
                var currentNum = ""
                var currentName = ""
                var currentValue = ""
                var inside = false
                while (parser.eventType != XmlPullParser.END_DOCUMENT) {
                    if (parser.eventType == XmlPullParser.START_TAG) {
                        if (parser.name.equals("valute", true)) {
                            inside = true
                        } else if (parser.name.equals("nominal", true)) {
                            currentNum = parser.nextText()
                        } else if (inside and parser.name.equals("name", true)) {
                            currentName = parser.nextText()
                        } else if (inside and parser.name.equals("value", true)) {
                            currentValue = parser.nextText()
                        }
                    } else if (parser.eventType == XmlPullParser.END_TAG) {
                        if (parser.name.equals("valute", true) and inside) {
                            listData.add(hashMapOf("num" to currentNum, "name" to currentName, "value" to currentValue))
                            currentNum = ""
                            currentName = ""
                            currentValue = ""
                            inside = false
                        }
                    }
                    parser.next()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                listData.clear()
            }
            requireActivity().runOnUiThread {
                adapter.notifyDataSetChanged()
            }
        }) {
            start()
        }

        return binding.root
    }
}