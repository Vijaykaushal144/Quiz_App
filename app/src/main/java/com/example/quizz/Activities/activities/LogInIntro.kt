package com.example.quizz.Activities.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizz.databinding.ActivityLogInIntroBinding
import com.google.firebase.auth.FirebaseAuth

class LogInIntro : AppCompatActivity() {
    private lateinit var binding: ActivityLogInIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val auth = FirebaseAuth.getInstance()

        //firebase current user banata h

        if (auth.currentUser != null) {
            Toast.makeText(this, "You are already Logged In", Toast.LENGTH_SHORT).show()
            redirect("MAIN")

        }

        binding.btngetstarted.setOnClickListener {
            redirect("LOGIN")
        }
    }


    private fun redirect(name: String) {
        //for redicting to the activity

        val intent: Intent = when (name) {
            "LOGIN" -> Intent(this, LoginActivity::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("No Path Exist")
        }
        startActivity(intent)
        finish()
    }
}