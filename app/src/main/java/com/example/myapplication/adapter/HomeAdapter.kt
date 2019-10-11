package com.example.myapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Detail_Activity
import com.example.myapplication.R
import com.example.myapplication.model.Cardview

class HomeAdapter(internal var cards: List<Cardview>, var context: Context) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_cardview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cards = this.cards[position]
        holder.name.text = cards.title
        if (cards.img == null)
            holder.image.setImageResource(R.drawable.pizza)
        else
            Glide.with(context)
                .load(cards.img)
                .into(holder.image)
        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context,Detail_Activity::class.java));
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var name: TextView

        init {
            image = itemView.findViewById(R.id.image_Promotion)
            name = itemView.findViewById(R.id.tv_Title)
        }
    }

    public fun setdata(items: List<Cardview>) {
        cards = items
        notifyDataSetChanged()
    }

}