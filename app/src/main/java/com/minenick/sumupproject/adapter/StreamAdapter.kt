package com.minenick.sumupproject.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minenick.sumupproject.R
import com.minenick.sumupproject.databinding.ItemStreamViewBinding
import com.minenick.sumupproject.entities.Stream
import com.squareup.picasso.Picasso
import java.util.*

class StreamAdapter(val streamList:MutableList<Stream>):RecyclerView.Adapter<StreamAdapter.StreamHolder>() {

    class StreamHolder(view:View):RecyclerView.ViewHolder(view) {
        private val binding=ItemStreamViewBinding.bind(view)
        fun render(stream:Stream){

            val calendar=Calendar.getInstance().time
            fun time():Int{
                var month:Int = if (stream.fechaPago>=Calendar.DAY_OF_MONTH){
                    calendar.month+2
                }else {
                    calendar.month+1
                }
                if(month>12){
                    month-=12
                }
                return  month
            }
            binding.tvSteam.text=stream.stream
            binding.tvPrice.text="S/."+stream.precio.toString()
            binding.tvPayDay.text="${stream.fechaPago} / ${time()}"
            Picasso.get().load(stream.img).into(binding.ivStream)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_stream_view,parent,false)
        return StreamHolder(v)
    }

    override fun onBindViewHolder(holder: StreamHolder, position: Int) {
        holder.render(streamList[position])
    }

    override fun getItemCount(): Int =streamList.size

}