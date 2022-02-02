package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_health_list.*

class HealthListActivity : AppCompatActivity() {
    lateinit var nextButton: Button
    lateinit var exercise_1: EditText
    lateinit var exercise_2: EditText
    lateinit var exercise_3: EditText
    lateinit var exercise_4: EditText
    lateinit var exercise_5: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_list)
        nextButton = findViewById<Button>(R.id.nextButton)
        exercise_1 = findViewById<EditText>(R.id.exercise_1)
        exercise_2 = findViewById<EditText>(R.id.exercise_2)
        exercise_3 = findViewById<EditText>(R.id.exercise_3)
        exercise_4 = findViewById<EditText>(R.id.exercise_4)
        exercise_5 = findViewById<EditText>(R.id.exercise_5)
        //loadData()

        nextButton.setOnClickListener {
            /*saveData(exercise_1.text.toString().toInt(),
                    exercise_2.text.toString().toInt(),
                    exercise_3.text.toString().toInt(),
                    exercise_4.text.toString().toInt(),
                    exercise_5.text.toString().toInt()
            )*/

            var intent = Intent(this, HealthCalActivity::class.java)
            intent.putExtra("가슴 운동", exercise_1.text.toString())
            intent.putExtra("팔 운동", exercise_2.text.toString())
            intent.putExtra("어깨ㆍ등 운동", exercise_3.text.toString())
            intent.putExtra("다리 운동", exercise_4.text.toString())
            intent.putExtra("복근 운동", exercise_5.text.toString())
            startActivity(intent)
        }
    }

    private fun saveData(exercise1: Int, exercise2: Int, exercise3: Int, exercise4: Int, exercise5: Int) {
        var pref = this.getPreferences(0)
        var editor = pref.edit()

        editor.putInt("KEY_EXERCISE1",exercise_1.toString().toInt()).apply()
        editor.putInt("KEY_EXERCISE2",exercise_2.toString().toInt()).apply()
        editor.putInt("KEY_EXERCISE3",exercise_3.toString().toInt()).apply()
        editor.putInt("KEY_EXERCISE4",exercise_4.toString().toInt()).apply()
        editor.putInt("KEY_EXERCISE5",exercise_5.toString().toInt()).apply()
    }

    private fun loadData(){
        var pref = this.getPreferences(0)
        var exercise1 = pref.getInt("KEY_EXERCISE1",0)
        var exercise2 = pref.getInt("KEY_EXERCISE2",0)
        var exercise3 = pref.getInt("KEY_EXERCISE3",0)
        var exercise4 = pref.getInt("KEY_EXERCISE4",0)
        var exercise5 = pref.getInt("KEY_EXERCISE5",0)

        if(exercise1 != 0 && exercise2 != 0 && exercise3 !=0&& exercise4 !=0&& exercise5!=0){
            exercise_1.setText((exercise1.toString()))
            exercise_2.setText((exercise2.toString()))
            exercise_3.setText((exercise3.toString()))
            exercise_4.setText((exercise4.toString()))
            exercise_5.setText((exercise5.toString()))
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
            }
            */
            R.id.calender ->{
                //val intent = Intent(this, HealthRecActivity::class.java)
                var intent = Intent(this,HealthRecActivity::class.java)
                intent.putExtra("가슴 운동",exercise_1.text.toString())
                intent.putExtra("팔 운동",exercise_2.text.toString())
                intent.putExtra("어깨ㆍ등 운동",exercise_3.text.toString())
                intent.putExtra("다리 운동",exercise_4.text.toString())
                intent.putExtra("복근 운동",exercise_5.text.toString())
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}