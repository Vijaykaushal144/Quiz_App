package com.example.quizz.Activities.activities.utils

import com.example.quizz.R
//these classes is used to change the color and icon
//make dynamic image and color
object IconPicker {
     val icons= arrayOf(
         R.drawable.img1,
         R.drawable.img2,
         R.drawable.img3,
         R.drawable.img4,
         R.drawable.img5,
         R.drawable.img6
     )
    var currenticon=0

    fun getIcon():Int{
        currenticon= (currenticon+1)% icons.size
        return icons[currenticon]
    }
}