package com.minenick.sumupproject.model

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.minenick.sumupproject.adapter.ModelStreamAdapter
import com.minenick.sumupproject.adapter.StreamAdapter
import com.minenick.sumupproject.databinding.ActivityStreamBinding
import com.minenick.sumupproject.db.DataBaseSQLiteHelper
import com.minenick.sumupproject.entities.Stream

class StreamActivity : AppCompatActivity(),StreamAdapter.ClickListener {

    private lateinit var binding:ActivityStreamBinding
    private lateinit var streamDBHelper:DataBaseSQLiteHelper
    private var streamList:MutableList<Stream> = mutableListOf()
    private var number:String = ""
    private val adapter by lazy{StreamAdapter(mutableListOf(),this)}



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityStreamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle=intent.extras
        number= bundle?.getString("number")!!
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

    override fun onResume() {
        val cursor: Cursor =streamDBHelper.selectAllStream(number!!)
        showStream(cursor)
        initRecycle()

        super.onResume()
    }

    private fun showStream(cursor:Cursor) {
        streamList.clear()
        if(cursor.moveToFirst()){
            do{
                //Log.d("Sentencia",Gson().toJson(cursor))
                streamList.add(Stream(cursor.getInt(0),
                    cursor.getString(1),
                cursor.getInt(2),
                cursor.getFloat(3),
                cursor.getString(4)))
            }while(cursor.moveToNext())
        }
    }

    private fun initRecycle() {
        binding.rvStream.layoutManager=LinearLayoutManager(this)
        val adapter= adapter
        binding.rvStream.adapter=adapter
        adapter.setItems(streamList)
    }

    override fun onDelete(idStream: Int) {
        //Toast.makeText(this, "$idStream", Toast.LENGTH_SHORT).show()
        streamDBHelper.deleteStream(idStream)
        val cursor: Cursor =streamDBHelper.selectAllStream(number!!)
        showStream(cursor)
        adapter.setItems(streamList)
    }
}