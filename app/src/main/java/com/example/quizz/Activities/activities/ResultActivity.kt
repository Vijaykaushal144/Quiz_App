package com.example.quizz.Activities.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.quizz.Activities.activities.model.Quiz
import com.example.quizz.databinding.ActivityResultBinding
import com.google.gson.Gson

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    lateinit var quiz: Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
    }

    private fun setUpViews() {

        //deserilize krenege jo data intenet ke through ayega usko

        val quizdata=intent.getStringExtra("QUIZ")
         quiz=Gson().fromJson<Quiz>(quizdata,Quiz::class.java)

        calculateResult()
        setAnswerView()

    }

    private fun setAnswerView() {
        val builder = StringBuilder("")
        for (entry in quiz.questions.entries) {
            val question = entry.value
            builder.append("<font color'#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#009688'>Answer: ${question.answer}</font><br/><br/>")
        }
        binding.txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);

    }

    private fun calculateResult() {
        var score=0
        for (entry in quiz.questions.entries)
        {
            val question=entry.value
            if(question.userAns==question.answer)
            {
                score+=10
            }
        }
        binding.txtScore.text="Your Score :$score"

    }
}