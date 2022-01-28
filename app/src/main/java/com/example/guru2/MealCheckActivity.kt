package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView

class MealCheckActivity : AppCompatActivity() {
    lateinit var calendarCheck: CalendarView
    lateinit var calCheckButton: Button
    lateinit var tvCalCheck: TextView

    var selectYear: Int = 0
    var selectMonth: Int = 0
    var selectDate: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_check)

        calendarCheck = findViewById(R.id.calendarCheck)
        calCheckButton = findViewById(R.id.calCheckButton)
        tvCalCheck = findViewById(R.id.tvCalCheck)

        calendarCheck.setOnDateChangeListener { view, year, month, date ->
            selectYear = year
            selectMonth = month + 1
            selectDate = date
            calCheckButton.setOnClickListener {
                if (selectMonth < 10) {
                    if (date < 10) {
                        tvCalCheck.text =
                            selectYear.toString() + "년" + " " + "0" + selectMonth.toString() + "월" + " " + "0" + selectDate.toString() + "일"
                    } else {
                        tvCalCheck.text =
                            selectYear.toString() + "년" + " " + "0" + selectMonth.toString() + "월" + " " + selectDate.toString() + "일"
                    }
                } else {
                    if (date < 10) {
                        tvCalCheck.text =
                            selectYear.toString() + "년" + " " + selectMonth.toString() + "월" + " " + "0" + selectDate.toString() + "일"
                    } else {
                        tvCalCheck.text =
                            selectYear.toString() + "년" + " " + selectMonth.toString() + "월" + " " + selectDate.toString() + "일"
                    }
                }
                tvCalCheck.visibility = View.VISIBLE
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.main -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.sport -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.water -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.mental -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.meal -> {
                val intent = Intent(this, MealWriteActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}