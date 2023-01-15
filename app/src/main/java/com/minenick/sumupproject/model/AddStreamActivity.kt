package com.minenick.sumupproject.model

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.minenick.sumupproject.adapter.ModelStreamAdapter
import com.minenick.sumupproject.databinding.ActivityAddStreamBinding
import com.minenick.sumupproject.db.DataBaseSQLiteHelper
import com.minenick.sumupproject.entities.ModelStream
import com.minenick.sumupproject.entities.Stream

class AddStreamActivity : AppCompatActivity(),ModelStreamAdapter.onStreamClickListener {
    private lateinit var streamDBHelper: DataBaseSQLiteHelper
    private lateinit var binding:ActivityAddStreamBinding
    private var streamx:String=""
    private var imgx: String=""
    private var modelStream:MutableList<ModelStream> = mutableListOf(
        ModelStream("Spotify","https://cdn.smehost.net/sonymusiccommx-mxprod/wp-content/uploads/2019/08/SPOTIFY-PNG.png"),
        ModelStream("Youtube Premium", "https://iconape.com/wp-content/png_logo_vector/youtube-premium-logo.png"),
        ModelStream("Disney+","https://logodownload.org/wp-content/uploads/2020/11/disney-plus-logo.png")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddStreamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle=intent.extras
        val number=bundle?.getString("number")
        streamDBHelper= DataBaseSQLiteHelper(this)
        initRecycle()

        binding.btnSave2.setOnClickListener {
            if (streamx.isNotBlank() && imgx.isNotBlank()){
                val idStream = streamDBHelper.selectAllStream(number!!).count +1
                streamDBHelper.addDataStream(number, Stream(idStream,streamx,binding.etDiaPago.text.toString().toInt(),binding.etPrecio.text.toString().toFloat(),imgx))
                onBackPressed()
            }

        }
        binding.btnCancel2.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initRecycle() {
        binding.rvModel.layoutManager=LinearLayoutManager(this)
        val adapter=ModelStreamAdapter(modelStream,this )
        binding.rvModel.adapter=adapter

    }

    override fun onItemClick(stream:String,img:String) {
        streamx=stream
        imgx=img
    }
}