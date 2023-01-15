package com.minenick.sumupproject.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.minenick.sumupproject.R
import com.minenick.sumupproject.databinding.ItemListViewBinding
import com.minenick.sumupproject.entities.Card
import com.squareup.picasso.Picasso

class RecyclerAdapter (private val cardList:MutableList<Card>,private val itemClickListener:onCardClickListener) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    private var items:MutableList<Card> = cardList
    interface onCardClickListener{
        fun onItemClick(number:String)
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val binding=ItemListViewBinding.bind(itemView)
        fun render(card:Card){
            binding.tvNroCard.text=card.number
            binding.tvPropietario.text=card.owner
            binding.tvFecha.text=card.date
            Picasso.get().load(card.img).into(binding.ivCard)
            itemView.setOnClickListener {
                itemClickListener.onItemClick(card.number.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_list_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.render(items[position])
    }

    override fun getItemCount(): Int = items.size



}