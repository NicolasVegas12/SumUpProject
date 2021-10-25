package com.minenick.sumupproject.model

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.minenick.sumupproject.adapter.StreamAdapter
import com.minenick.sumupproject.databinding.ActivityStreamBinding
import com.minenick.sumupproject.db.DataBaseSQLiteHelper
import com.minenick.sumupproject.entities.Stream

class StreamActivity : AppCompatActivity() {

    private lateinit var binding:ActivityStreamBinding
    private lateinit var streamDBHelper:DataBaseSQLiteHelper
    private var streamList:MutableList<Stream> = mutableListOf(
        Stream("Spotify",12,15.6f,"https://cdn.smehost.net/sonymusiccommx-mxprod/wp-content/uploads/2019/08/SPOTIFY-PNG.png")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStreamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle=intent.extras
        val number=bundle?.getString("number")
        streamDBHelper= DataBaseSQLiteHelper(this)

        val cursor: Cursor =streamDBHelper.selectAllStream(number!!)
        initRecycle()
        showStream(cursor)
        binding.btnAddStream.setOnClickListener{
            startActivity(Intent(this,AddStreamActivity::class.java).apply{
                putExtra("number",number)
            })
        }


    }

    private fun showStream(cursor:Cursor) {
        if(cursor.moveToFirst()){
            do{
                streamList.add(Stream(cursor.getString(1),
                cursor.getInt(2),
                cursor.getFloat(3),
                cursor.getString(4)))
            }while(cursor.moveToNext())
        }
    }

    private fun initRecycle() {
        binding.rvStream.layoutManager=LinearLayoutManager(this)
        val adapter= StreamAdapter(streamList)
        binding.rvStream.adapter=adapter
    }
}