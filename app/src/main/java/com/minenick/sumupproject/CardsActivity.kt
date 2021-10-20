package com.minenick.sumupproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.minenick.sumupproject.adapter.RecyclerAdapter
import com.minenick.sumupproject.databinding.ActivityAuthBinding
import com.minenick.sumupproject.databinding.ActivityCardsBinding

class CardsActivity : AppCompatActivity() {
    private val email:String?=intent.extras?.getString("email")
    private lateinit var db:FirebaseFirestore
    private lateinit var binding:ActivityCardsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycle()

    }

    private fun initRecycle() {
        binding.rvCard.layoutManager=LinearLayoutManager(this)
        val adapter= RecyclerAdapter()
        binding.rvCard.adapter=adapter
    }
}