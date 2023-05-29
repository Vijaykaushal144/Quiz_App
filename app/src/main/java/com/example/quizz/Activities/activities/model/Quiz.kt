package com.example.quizz.Activities.activities.model

data class Quiz(
    var id:String="",
    var title:String="",
    var questions: MutableMap<String,Question> = mutableMapOf()
)
