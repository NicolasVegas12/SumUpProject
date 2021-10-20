package com.minenick.sumupproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.minenick.sumupproject.R
import com.minenick.sumupproject.databinding.ItemListViewBinding
import com.minenick.sumupproject.entities.Card
import com.squareup.picasso.Picasso

class RecyclerAdapter (private val cardList:ArrayList<Card>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val binding=ItemListViewBinding.bind(itemView)
        fun render(card:Card){
            binding.tvNroCard.text=card.number
            binding.tvPropietario.text=card.owner
            binding.tvFecha.text="${card.date?.year} / ${card.date?.month}"
            Picasso.get().load(card.img).into(binding.ivCard)
            itemView.setOnClickListener {
                Toast.makeText(itemView.context,"Tarjeta elegida : ${card.number}",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_list_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.render(cardList[position])
    }

    override fun getItemCount(): Int {
        return cardList.size
    }
}