package com.example.guru2

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_health_list.*

class HealthListActivity : AppCompatActivity() {

    lateinit var dbHealthManager: DBHealthManager
    lateinit var sqlitehealthdb : SQLiteDatabase
    lateinit var nextButton: Button
    lateinit var exercise_1: EditText
    lateinit var exercise_2: EditText
    lateinit var exercise_3: EditText
    lateinit var exercise_4: EditText
    lateinit var exercise_5: EditText

    @RequiresApi(VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_list)

        nextButton = findViewById<Button>(R.id.nextButton)
        exercise_1 = findViewById<EditText>(R.id.exercise_1)
        exercise_2 = findViewById<EditText>(R.id.exercise_2)
        exercise_3 = findViewById<EditText>(R.id.exercise_3)
        exercise_4 = findViewById<EditText>(R.id.exercise_4)
        exercise_5 = findViewById<EditText>(R.id.exercise_5)

        dbHealthManager  = DBHealthManager(this,"todayHealth",null,1)

        nextButton.setOnClickListener {
            sqlitehealthdb = dbHealthManager.writableDatabase
            sqlitehealthdb.execSQL("INSERT INTO todayHealth VALUES ('"+exercise_1+"', '"+exercise_2+
                    "', '"+ exercise_3+"', '"+exercise_4+"', '"+exercise_5+"')")
            sqlitehealthdb.close()

            var intent = Intent(this, HealthCalActivity::class.java)
            intent.putExtra("가슴 운동", exercise_1.text.toString())
            intent.putExtra("팔 운동", exercise_2.text.toString())
            intent.putExtra("어깨ㆍ등 운동", exercise_3.text.toString())
            intent.putExtra("다리 운동", exercise_4.text.toString())
            intent.putExtra("복근 운동", exercise_5.text.toString())
            startActivity(intent)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    //메뉴바 이동
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.main->{
                val mainIntent= Intent(this,MainActivity::class.java)
                startActivity(mainIntent)
            }
            R.id.sport->{
                val sportIntent=Intent(this,HealthListActivity::class.java)
                startActivity(sportIntent)
            }
            R.id.meal->{
                val mealIntent=Intent(this,MealWriteActivity::class.java)
                startActivity(mealIntent)
            }
            R.id.water->{
                val waterIntent=Intent(this,WaterCheck::class.java)
                startActivity(waterIntent)
            }
            R.id.mental->{
                val mentalIntent=Intent(this,MentalDailyActivity::class.java)
                startActivity(mentalIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
