package com.hdesrosiers.rvrecyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExampleAdapter: RecyclerView.Adapter() {

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.item_image_view)
        val titleView = itemView.findViewById<TextView>(R.id.item_title_text_view)
        val descriptionView = itemView.findViewById<TextView>(R.id.item_description_text_view)
    }
}