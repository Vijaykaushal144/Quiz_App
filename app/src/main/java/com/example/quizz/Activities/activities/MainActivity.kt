package com.example.quizz.Activities.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizz.Activities.activities.adapters.QuizAdapter
import com.example.quizz.Activities.activities.model.Quiz
import com.example.quizz.R
import com.example.quizz.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var adapter: QuizAdapter
    private lateinit var firestore: FirebaseFirestore
    private var quizlist = mutableListOf<Quiz>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //method call
        setupview()

    }


    fun setupview() {
        setUpFirestore()
        setUpDrawerlayout()
        setUpRecyclerview()
        setUpDatePicker()
    }

    private fun setUpDatePicker() {

        binding.btnDatePicker.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")

            datePicker.addOnPositiveButtonClickListener {
                Log.d("DATEPICKER", datePicker.headerText)
                //date ko title se match krwane ke liye date format krni padegi

                val dateFormat=SimpleDateFormat("dd-mm-yyyy")
                val date= dateFormat.format(Date(it))


                //main activity se jo date ayegi use hm question activity me bhej denge so
                // that apni quiz firestore se fetch hoke a ajeyegi

                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("DATE", date)
                startActivity(intent)
            }

            datePicker.addOnNegativeButtonClickListener {
                Log.d("DATEPICKER", datePicker.headerText)
            }

            datePicker.addOnCancelListener {
                Log.d("DATEPICKER", "dATE PICKER CANCELLED")
            }
        }

    }

    private fun setUpFirestore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzez")
        collectionReference.addSnapshotListener { value, error ->
            if (value == null || error != null) {
                Toast.makeText(this, "Error in fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA", value.toObjects(Quiz::class.java).toString())
            quizlist.clear()

            //data ko firestore se lake list me dala or adapter ko notify changes kr diya

            quizlist.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }

    }

    private fun setUpRecyclerview() {
        adapter = QuizAdapter(this, quizlist)
        binding.quizzRecyclerview.layoutManager = GridLayoutManager(this, 2)
        binding.quizzRecyclerview.adapter = adapter
    }

    private fun setUpDrawerlayout() {
        //activty pe theme lgi hoti h to ab hme btana hoga ki activity theme se nahi balki tool bar se ayegi
        setSupportActionBar(binding.appBar)

        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, binding.mainDrawer,
            R.string.app_name,
            R.string.app_name
        )
        actionBarDrawerToggle.syncState()
        binding.navigationView.setNavigationItemSelectedListener {
            val intent=Intent(this,ProfileActivity::class.java)
            startActivity(intent)
            binding.mainDrawer.closeDrawers()
            true
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}