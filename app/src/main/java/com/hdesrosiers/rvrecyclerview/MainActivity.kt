package com.hdesrosiers.rvrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val exampleList = generateDummyList(50)
    private val adapter = ExampleAdapter(exampleList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int): ArrayList<ExampleItem> {
        val list = ArrayList<ExampleItem>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.the_beatles_sgt_peppers
                1 -> R.drawable.gorillaz_plastic_beach
                else -> R.drawable.u2_war
            }

            val item = ExampleItem(drawable, "Album Title", "Artist Name")
            list += item
        }

        return list
    }

    fun insertItem(view: View) {
        val index = Random.nextInt(8)
        val newItem = ExampleItem(R.drawable.u2_war, "Inserted at position $index", "Artist Name")
        exampleList.add(index, newItem)
//        adapter.notifyDataSetChanged() //old way, resets whole list, no animation
        adapter.notifyItemInserted(index) //might put in adapter for better encapsulation
    }

    fun removeItem(view: View) {
        val index = Random.nextInt(8)
        exampleList.removeAt(index)
//        adapter.notifyDataSetChanged() //old way, resets whole list, no animation
        adapter.notifyItemRemoved(index)
    }
}