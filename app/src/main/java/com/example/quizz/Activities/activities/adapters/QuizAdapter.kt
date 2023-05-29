package com.example.quizz.Activities.activities.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizz.Activities.activities.QuestionActivity
import com.example.quizz.Activities.activities.model.Question
import com.example.quizz.Activities.activities.model.Quiz
import com.example.quizz.Activities.activities.utils.ColorPicker
import com.example.quizz.Activities.activities.utils.IconPicker
import com.example.quizz.R

class QuizAdapter(val context: Context, val quizzez: List<Quiz>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.quizz_item_rcview, parent, false)
        return QuizViewHolder(view)

    }

    override fun getItemCount(): Int {
        return quizzez.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.txtviewtitle.text = quizzez[position].title
        //card view ke back ground pe color lagane ke liye

        holder.cardcontainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.iconview.setImageResource(IconPicker.getIcon())

        //set click listener on item view (view show ho rhe h unpe click listener)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, quizzez[position].title, Toast.LENGTH_SHORT).show()
            val intent =Intent(context,QuestionActivity::class.java)
            intent.putExtra("DATE",quizzez[position].title)
            context.startActivity(intent)
        }
    }

    inner class QuizViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var txtviewtitle: TextView = itemview.findViewById(R.id.quizzTitle)
        var iconview: ImageView = itemview.findViewById(R.id.quizzIcon)
        var cardcontainer: CardView = itemview.findViewById(R.id.cardContainer)

    }
}