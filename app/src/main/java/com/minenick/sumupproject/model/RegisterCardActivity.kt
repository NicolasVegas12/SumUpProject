package com.minenick.sumupproject.model

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.minenick.sumupproject.databinding.ActivityRegisterCardBinding
import com.minenick.sumupproject.db.DataBaseSQLiteHelper
import com.minenick.sumupproject.entities.Card
import com.squareup.picasso.Picasso

class RegisterCardActivity : AppCompatActivity() {

    private var tarjetas:ArrayList<String> = arrayListOf(
        "https://www.viabcp.com/wcm/connect/1d3e0805-edee-4fc5-89c8-e0266a61bc84/Credimas+corregida.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE-1d3e0805-edee-4fc5-89c8-e0266a61bc84-nAzhk1k",
        "https://scotiabankfiles.azureedge.net/scotiabank-colombia/scotiabank-colpatria/tarjetas-de-credito/plastico-tarjeta-credito-metal-5.png",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdJ4Lfhnl6wYe4zE5oUQzBwK9xXoCMYg98nBJF_HorkMXtsk5LFjCGya2oZ6M8r3YHA0s&usqp=CAU",
        "https://scotiabankfiles.azureedge.net/scotiabank-colombia/scotiabank-colpatria/imagenes/tarjetas/Visa_Infinite_Lifemiles_600x380.png"
    )


    private lateinit var binding:ActivityRegisterCardBinding
    private lateinit var cardSQLHelper:DataBaseSQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityRegisterCardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle:Bundle?= intent.extras
        val email:String?=bundle?.getString("email")
        cardSQLHelper= DataBaseSQLiteHelper(this)


        var i = 0

        Picasso.get().load(tarjetas[i]).into(binding.ivCards)
        binding.btnNext.setOnClickListener{
            if(i==tarjetas.size-1){
                i=-1
            }
            i+=1
            Picasso.get().load(tarjetas[i]).into(binding.ivCards)
        }
        binding.btnBack.setOnClickListener{
            if(i==0){
                i=tarjetas.size
            }
            i-=1
            Picasso.get().load(tarjetas[i]).into(binding.ivCards)
        }
        binding.btnCancel.setOnClickListener { onBackPressed() }

        binding.btnSave.setOnClickListener {

            val number= binding.etNum1.text.toString()+" "+
                    binding.etNum2.text.toString()+" "+
                    binding.etNum3.text.toString()+" "+
                    binding.etNum4.text.toString()
            val name = binding.etPropietario.text.toString()
            val date=binding.etDate1.text.toString()+" / "+
                    binding.edDate2.text.toString()

            cardSQLHelper.addDataCard(email!!, Card(number,name,date,tarjetas[i]))


            startActivity(Intent(this, CardsActivity::class.java).apply {
                putExtra("email",email)
            })
        }

    }

}