package com.example.quizz.Activities.activities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quizz.Activities.activities.model.Question
import com.example.quizz.R


class OptionAdapter(val context: Context, val question: Question) :
    RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {

    //option ki list bna ke options name ki list me dal di

    private var options: List<String> =
        listOf(question.option1, question.option2, question.option3, question.option4)

    inner class OptionViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var optionview: TextView = itemview.findViewById(R.id.quizOption)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.option_item, parent, false)
        return OptionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionview.text = options[position]
        //click event on item views

        holder.itemView.setOnClickListener {

            //Toast.makeText(context, options[position], Toast.LENGTH_SHORT).show()
            //user ka ans userans name ke variable me store kr lennge

            question.userAns = options[position]
            notifyDataSetChanged()
        }

        if (question.userAns == options[position]) {
            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        }
        else {
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)

        }
    }
}