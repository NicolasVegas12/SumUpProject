package com.minenick.sumupproject.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.minenick.sumupproject.R
import com.minenick.sumupproject.adapter.StreamAdapter
import com.minenick.sumupproject.databinding.ActivityStreamBinding
import com.minenick.sumupproject.db.StreamSQLiteHelper
import com.minenick.sumupproject.entities.Stream

class StreamActivity : AppCompatActivity() {

    private lateinit var binding:ActivityStreamBinding
    private lateinit var streamDBHelper:StreamSQLiteHelper
    private var streamList:MutableList<Stream> = mutableListOf(
        Stream("Spotify",12,15.6f,"https://cdn.smehost.net/sonymusiccommx-mxprod/wp-content/uploads/2019/08/SPOTIFY-PNG.png")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStreamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        streamDBHelper=StreamSQLiteHelper(this)
        initRecycle()

    }

    private fun initRecycle() {
        binding.rvStream.layoutManager=LinearLayoutManager(this)
        val adapter= StreamAdapter(streamList)
        binding.rvStream.adapter=adapter
    }
}