package com.minenick.sumupproject

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.minenick.sumupproject.adapter.RecyclerAdapter
import com.minenick.sumupproject.databinding.ActivityAuthBinding
import com.minenick.sumupproject.databinding.ActivityCardsBinding
import com.minenick.sumupproject.db.CardSQLiteHelper
import com.minenick.sumupproject.entities.Card

class CardsActivity : AppCompatActivity() {


    private var cards:MutableList<Card> = mutableListOf()
    private lateinit var binding:ActivityCardsBinding
    private lateinit var cardDBHelper:CardSQLiteHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle=intent.extras
        val email=bundle?.getString("email")
        cardDBHelper= CardSQLiteHelper(this)

        val cursor: Cursor =cardDBHelper.selectAll(email!!)
        show(cursor)
        initRecycle()
        binding.tvWarning.isVisible = cards.isEmpty()
        binding.btnAdd.setOnClickListener{startActivity(
            Intent(this,RegisterCardActivity::class.java).apply {
                putExtra("email", email)
            })
        }

    }



    private fun initRecycle() {
        binding.rvCard.layoutManager=LinearLayoutManager(this)
        val adapter= RecyclerAdapter(cards)
        binding.rvCard.adapter=adapter
    }
    private fun show(cursor:Cursor){
        if(cursor.moveToFirst()){
            do {
                cards.add(Card(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)))

            }while (cursor.moveToNext())
        }
    }

}