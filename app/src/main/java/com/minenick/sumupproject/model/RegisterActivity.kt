package com.minenick.sumupproject.model

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.minenick.sumupproject.databinding.ActivityRegisterBinding
import com.minenick.sumupproject.db.DataBaseSQLiteHelper

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var userDBHelper:DataBaseSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userDBHelper=DataBaseSQLiteHelper(this)


        binding.btnCancel.setOnClickListener { onBackPressed() }

        //Registro
        register()
    }

    private fun register() {
        title="Registro"
        binding.btnRegister.setOnClickListener {
            if (binding.etEmail.text.isNotBlank()&&binding.etPassword.text.isNotBlank()&&binding.etRePassword.text.isNotBlank()){
                if (binding.etPassword.text.toString()==binding.etRePassword.text.toString()){

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.etEmail.text.toString(),
                        binding.etPassword.text.toString()).addOnCompleteListener {
                            if(it.isSuccessful){
                                userDBHelper.addDataUser(binding.etEmail.text.toString(),binding.etPassword.text.toString())
                                startActivity(Intent(this, AuthActivity::class.java))
                            }else{
                                showAlert()
                            }
                    }
                }else{
                    Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"Por favor Rellene las casillas de registro",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error en el registro del usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog=builder.create()
        dialog.show()
    }
}