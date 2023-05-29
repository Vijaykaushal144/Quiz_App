package com.example.quizz.Activities.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizz.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()


        binding.btnSignUp.setOnClickListener {
            signupuser()
        }


        binding.btnlogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


    }


    private fun signupuser() {
        val email = binding.etEmailAddress.text.toString()
        val pass = binding.etPassword.text.toString()
        val confPass = binding.etConfirmPassword.text.toString()

        //some validation for email and password check

        if (email.isBlank() || pass.isBlank() || confPass.isBlank()) {
            Toast.makeText(this, "Email And Password can't Blank", Toast.LENGTH_SHORT).show()
            return;

        }
        if (pass != confPass) {
            Toast.makeText(this, "Password and Confirm Password Does Not match", Toast.LENGTH_SHORT)
                .show()
            return;
        }


        firebaseAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    //Toast.makeText(this,"Login SuccessFully!",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this, "Error Creating User", Toast.LENGTH_SHORT).show()
                }
            }
    }
}