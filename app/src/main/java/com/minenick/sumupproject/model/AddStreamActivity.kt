package com.minenick.sumupproject.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.minenick.sumupproject.adapter.ModelStreamAdapter
import com.minenick.sumupproject.databinding.ActivityAddStreamBinding
import com.minenick.sumupproject.db.DataBaseSQLiteHelper
import com.minenick.sumupproject.entities.ModelStream

class AddStreamActivity : AppCompatActivity(),ModelStreamAdapter.onStreamClickListener {
    private lateinit var streamDBHelper: DataBaseSQLiteHelper
    private lateinit var binding:ActivityAddStreamBinding
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
    }

    private fun initRecycle() {
        binding.rvModel.layoutManager=LinearLayoutManager(this)
        val adapter=ModelStreamAdapter(modelStream,this )
        binding.rvModel.adapter=adapter

    }

    override fun onItemClick() {

    }
}