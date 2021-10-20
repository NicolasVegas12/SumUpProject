package com.minenick.sumupproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.minenick.sumupproject.databinding.ActivityAuthBinding

class CardsActivity : AppCompatActivity() {
    private val email:String?=intent.extras?.getString("email")
    private lateinit var binding:ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}