package com.example.quizz.Activities.activities.utils

object ColorPicker {
    val colors = arrayOf(
        "#3EB9DF",
        "#3685BC",
        "#D36280",
        "#E44F55",
        "#FA8056",
        "#818BCA",
        "#7D659F",
        "#51BAB3",
        "#4FB66C",
        "#E3AD17",
        "#627991",
        "#EF83AD",
        "#B5BFC6"
    )

    var currentcolorindex = 0

    fun getColor(): String {
        currentcolorindex = (currentcolorindex + 1) % colors.size
        return colors[currentcolorindex]
    }
}