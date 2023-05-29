package com.example.quizz.Activities.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizz.Activities.activities.adapters.OptionAdapter
import com.example.quizz.Activities.activities.model.Question
import com.example.quizz.Activities.activities.model.Quiz
import com.example.quizz.databinding.ActivityQuestionBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding

    //option and question ke liye list or map ek ek krke dikhane ke liye

    var quizzez: MutableList<Quiz>? = null
    var questions: MutableMap<String, Question>? = null
    var index = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpFireStore()
        setUpEventListener()

    }

    private fun setUpEventListener() {
        binding.btnPrevious.setOnClickListener {
            index--;
            bindView()
        }

        binding.btnNext.setOnClickListener {
            index++;
            bindView()

        }
        binding.btnSubmit.setOnClickListener {
            //data hm intent ke through ek activity se dusri activity me le jate h wo sirf primitive data type ke liye use hota h..
            //per yanha pe data list ke form me h to hm phle data ko serialize krenge..
            //fir result activity pe jake deserilize karenge  with the hel of dependence.

            val intent=Intent(this,ResultActivity::class.java)
            val json:String = Gson().toJson(quizzez!![0])
            intent.putExtra("QUIZ",json)
            startActivity(intent )
        }
    }



    private fun setUpFireStore() {

        val fireStore = FirebaseFirestore.getInstance()
        val date: String? = intent.getStringExtra("DATE")
        if (date != null) {
            //query on fire store hm where clause bhi apply kr skte h to get matched data
            fireStore.collection("quizzez").whereEqualTo("title", date)
                .get()
                .addOnSuccessListener {
                    if (it != null && !it.isEmpty) {
                       // Log.d("DATA", it.toObjects(Quiz::class.java).toString())
                        quizzez = it.toObjects(Quiz::class.java)
                        questions = quizzez!![0].questions
                        bindView()

                    }
                }

        }

    }

    private fun bindView() {
        //suruat me button ko invisible

        binding.btnNext.visibility = View.GONE
        binding.btnPrevious.visibility = View.GONE
        binding.btnSubmit.visibility = View.GONE

        if (index == 1) {
            //first question
            binding.btnNext.visibility = View.VISIBLE
        } else if (index == questions!!.size) {
            //last question
            binding.btnSubmit.visibility = View.VISIBLE
            binding.btnPrevious.visibility = View.VISIBLE

        } else {
            //middle question

            binding.btnNext.visibility = View.VISIBLE
            binding.btnPrevious.visibility = View.VISIBLE
        }

        val question = questions!!["question$index"]

        question?.let {
            binding.description.text = it.description
            val optionAdapter = OptionAdapter(this, it)

            //orienteation adapter
            binding.optionList.layoutManager = LinearLayoutManager(this)
            //adapter pe optio adapter set kr h
            binding.optionList.adapter = optionAdapter
            binding.optionList.setHasFixedSize(true)
        }


    }
}