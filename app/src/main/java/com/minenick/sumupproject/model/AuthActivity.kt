package com.minenick.sumupproject.model

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.minenick.sumupproject.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Analytics y eventos
        val analytics=FirebaseAnalytics.getInstance(this)
        val bundle=Bundle()
        bundle.putString("message","Integracion con Firebase Completa")
        analytics.logEvent("InitScreen",bundle)

        //Registro
        binding.tvRegister.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        //Setup
        setup()

        //Mostrar Contraseña
        showPassword()
    }

    private fun showPassword() {
        binding.cbChange.setOnClickListener {
            if (binding.cbChange.isChecked) {
                binding.etPassword.inputType = TYPE_CLASS_TEXT
            } else {
                binding.etPassword.inputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD
            }
        }
    }
    private fun setup() {
        title="Autenticación"
        binding.btnLogIn.setOnClickListener {
            if (binding.etEmail.text.isNotBlank()&& binding.etPassword.text.isNotBlank()){

                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.etEmail.text.toString(),
                binding.etPassword.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        showHome(it.result?.user?.email?:"")
                    }else{
                        showAlert()
                    }

                }
            }else{
                Toast.makeText(this,"Por favor rellene los espacios en blanco",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun message(){

    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error en la autenticacion del usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }

    private fun showHome(email:String){
        val homeIntent = Intent(this, CardsActivity::class.java).apply{
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }
}
