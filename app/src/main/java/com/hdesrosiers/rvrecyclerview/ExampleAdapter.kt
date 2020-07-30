package com.hdesrosiers.rvrecyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_item.view.*

class ExampleAdapter(
    private val exampleList: List<ExampleItem>, //the data
    private val listener: OnItemClickListener //the click contract
) :
    RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() { //the view holder

    //each ViewHolder object keeps 3 references to the 3 views inside the item layout
    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener { //inner for access to outer class properties (listener in OnClick)
        val imageView: ImageView =
            itemView.item_image_view //synthetic property: same as below but more concise
        val numberView: TextView = itemView.item_number_text
        val titleView: TextView = itemView.findViewById<TextView>(R.id.item_title_text_view)
        val descriptionView: TextView =
            itemView.findViewById<TextView>(R.id.item_description_text_view)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition //adapter built-in value
            if (position != RecyclerView.NO_POSITION) { //if click on deleted item during animation
                listener.onItemClick(position) //contract from outer class
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    //only called a few times (enough to fill the container plus a few extras above and below)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)
        return ExampleViewHolder(itemView) //returns inflated itemView layout
    }

    //called every time new content needs to be displayed in a (recycled) ViewHolder
    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position] //which item from data set
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.numberView.text = "${position + 1}"
        holder.titleView.text = currentItem.itemTitleText
        holder.descriptionView.text = currentItem.itemDescriptionText

//        if (position == 0) {
//            holder.titleView.setBackgroundColor(Color.DKGRAY)
//        }
    }

    override fun getItemCount() = exampleList.size

}