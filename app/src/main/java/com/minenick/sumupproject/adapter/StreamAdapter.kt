package com.minenick.sumupproject.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minenick.sumupproject.R
import com.minenick.sumupproject.databinding.ItemStreamViewBinding
import com.minenick.sumupproject.entities.Stream
import com.squareup.picasso.Picasso
import java.util.*

class StreamAdapter(private val streamList:MutableList<Stream>,
                    val itemStreamListener: ClickListener):RecyclerView.Adapter<StreamAdapter.StreamHolder>() {

    private val items:MutableList<Stream> = streamList

    interface ClickListener{
        fun onDelete(idStream:Int)
    }

    inner class StreamHolder(view:View):RecyclerView.ViewHolder(view) {
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

            binding.btnDelete.setOnClickListener {
                itemStreamListener.onDelete(stream.idStream)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_stream_view,parent,false)
        return StreamHolder(v)
    }

    override fun onBindViewHolder(holder: StreamHolder, position: Int) {
        holder.render(items[position])

    }

    override fun getItemCount(): Int =items.size


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems:List<Stream>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}