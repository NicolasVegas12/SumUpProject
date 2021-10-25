package com.minenick.sumupproject.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minenick.sumupproject.R
import com.minenick.sumupproject.databinding.ItemModelStreamViewBinding
import com.minenick.sumupproject.entities.ModelStream
import com.squareup.picasso.Picasso

class ModelStreamAdapter(private val modelList:MutableList<ModelStream>, private val itemStreamListener:onStreamClickListener):RecyclerView.Adapter<ModelStreamAdapter.ModelHolder>() {
    var row_index:Int=-1
    interface onStreamClickListener{
        fun onItemClick(stream:String,img:String)
    }
    inner class ModelHolder(val view: View):RecyclerView.ViewHolder(view){
        private val binding=ItemModelStreamViewBinding.bind(view)
        fun render(model:ModelStream){
            binding.tvModel.text=model.stream
            Picasso.get().load(model.img).into(binding.ivModel)
            view.setOnClickListener{
                view.setBackgroundColor(Color.parseColor("#FCEFCD"))
                itemStreamListener.onItemClick(model.stream,model.img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelStreamAdapter.ModelHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_model_stream_view,parent,false)
        return ModelHolder(v)
    }

    override fun onBindViewHolder(holder: ModelHolder, position: Int) {
        holder.render(modelList[position])
    }

    override fun getItemCount(): Int = modelList.size
}