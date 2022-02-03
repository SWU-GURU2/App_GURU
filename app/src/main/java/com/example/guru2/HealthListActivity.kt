package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText

class HealthListActivity : AppCompatActivity() {
    lateinit var nextButton: Button
    lateinit var exercise_1 : EditText
    lateinit var exercise_2 : EditText
    lateinit var exercise_3 : EditText
    lateinit var exercise_4 : EditText
    lateinit var exercise_5 : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_list)
        nextButton = findViewById<Button>(R.id.nextButton)
        exercise_1 = findViewById<EditText>(R.id.exercise_1)
        exercise_2 = findViewById<EditText>(R.id.exercise_2)
        exercise_3 = findViewById<EditText>(R.id.exercise_3)
        exercise_4 = findViewById<EditText>(R.id.exercise_4)
        exercise_5 = findViewById<EditText>(R.id.exercise_5)

        nextButton.setOnClickListener{
            var intent = Intent(this,HealthCalActivity::class.java)
            intent.putExtra("가슴 운동",exercise_1.text.toString())
            intent.putExtra("팔 운동",exercise_2.text.toString())
            intent.putExtra("어깨ㆍ등 운동",exercise_3.text.toString())
            intent.putExtra("다리 운동",exercise_4.text.toString())
            intent.putExtra("복근 운동",exercise_5.text.toString())
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.health_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.main -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.sport -> {
                val intent = Intent(this, HealthListActivity::class.java)
                startActivity(intent)
                return true
            }
            /*R.id.water -> {
                val intent = Intent(this, ::class.java)
                startActivity(intent)
                return true
            }
            R.id.mental -> {
                val intent = Intent(this, ::class.java)
                startActivity(intent)
                return true
            }
            R.id.meal -> {
                val intent = Intent(this, ::class.java)
                startActivity(intent)
                return true
            }*/
            R.id.action_record -> {
                val intent = Intent(this, HealthRecordActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_cal ->{
                val intent = Intent(this, HealthCalActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}