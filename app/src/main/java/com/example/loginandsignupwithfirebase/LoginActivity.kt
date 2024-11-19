package com.example.loginandsignupwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginandsignupwithfirebase.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    private val binding:ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()

        //Check if user already Logged in
        val currentUser:FirebaseUser?=auth.currentUser
        if(currentUser!=null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //initialize Firebase Auth
        auth =FirebaseAuth.getInstance()


        binding.LoginButton.setOnClickListener{
            val userName=binding.userName.text.toString()
            val password=binding.password.text.toString()

            if(userName.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please Fill All The Details", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(userName,password)
                    .addOnCompleteListener { tast ->
                        if (tast.isSuccessful) {
                            Toast.makeText(this, "Sign-In Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                        else{
                            Toast.makeText(this, "Sign-In Failed: ${tast.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        binding.signUpButton.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }

    }
}