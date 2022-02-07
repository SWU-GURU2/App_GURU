package com.example.guru2

import android.content.Intent
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CalendarView
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_health_rec.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HealthRecActivity : AppCompatActivity() {

    //변수 선언
    lateinit var calendarView : CalendarView
    lateinit var exec1 : TextView
    lateinit var exec2 : TextView
    lateinit var exec3 : TextView
    lateinit var exec4 : TextView
    lateinit var exec5 : TextView

    @RequiresApi(VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_rec)

        //위젯과 연결
        calendarView = findViewById<CalendarView>(R.id.calendarView)
        exec1 = findViewById<TextView>(R.id.exe1)
        exec2 = findViewById<TextView>(R.id.exe2)
        exec3 = findViewById<TextView>(R.id.exe3)
        exec4 = findViewById<TextView>(R.id.exe4)
        exec5 = findViewById<TextView>(R.id.exe5)

        //오늘 날짜 받아오기
        var currentTime = LocalDateTime.now();
        val formatter = DateTimeFormatter.ISO_DATE
        val formatted = currentTime.format(formatter)
        val todayText = formatted.toString()

        calendarView.dateTextAppearance = R.style.TextAppearance_AppCompat_Large
        calendarView.weekDayTextAppearance = R.style.TextAppearance_AppCompat_Medium
        calendarView.minDate = SimpleDateFormat("yyyyMMdd").parse("20210101").time //캘린더의 가장 최소 날짜설정
        calendarView.maxDate - SimpleDateFormat("yyyyMMdd").parse("20221231").time //캘린더의 가장 마지막 날짜 설정

        calendarView.setOnDateChangeListener { view, year, month, day ->
            if (month < 9) { //월이 한자리 숫자일 경우
                if (day < 10) { //일이 한자리 숫자일 경우
                    today.text = "" + year + "-" + 0 + (month + 1) + "-" + 0 + day

                } else { //일이 두자리 숫자일 경우
                    today.text = "" + year + "-" + 0 + (month + 1) + "-" + day
                }
            } else { //월이 두자리 수 일 경우
                if (day < 10) {//일이 한자리 숫자일 경우
                    today.text = "" + year + "-" + (month + 1) + "-" + 0 + day
                } else {//일이 두자리 숫자일 경우
                    today.text = "" + year + "-" + 0 + (month + 1) + "-" + day
                }
            }

            if (today.text == todayText) { //캘린더 클릭날짜가 오늘 날짜와 일치 할 경우

                var exercise_1 = intent.getStringExtra("가슴 운동").toFloat()
                var exercise_2 = intent.getStringExtra("팔 운동").toFloat()
                var exercise_3 = intent.getStringExtra("어깨ㆍ등 운동").toFloat()
                var exercise_4 = intent.getStringExtra("다리 운동").toFloat()
                var exercise_5 = intent.getStringExtra("복근 운동").toFloat()

                exec1.text = "가슴 운동 : " + exercise_1.toInt().toString() +"분"
                exec2.text = "팔 운동 : " + exercise_2.toInt().toString()+"분"
                exec3.text = "어깨ㆍ등 운동 : " + exercise_3.toInt().toString()+"분"
                exec4.text = "다리 운동 : " + exercise_4.toInt().toString()+"분"
                exec5.text = "복근 운동 : " + exercise_5.toInt().toString()+"분"
            }
            else{
                exec1.text = ""
                exec2.text = ""
                exec3.text = ""
                exec4.text = ""
                exec5.text = ""
            }
        }
    }
    //메뉴바 설정
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
                val sportIntent= Intent(this,HealthListActivity::class.java)
                startActivity(sportIntent)
            }
            R.id.meal->{
                val mealIntent= Intent(this,MealWriteActivity::class.java)
                startActivity(mealIntent)
            }
            R.id.water->{
                val waterIntent= Intent(this,WaterCheck::class.java)
                startActivity(waterIntent)
            }
            R.id.mental->{
                val mentalIntent= Intent(this,MentalDailyActivity::class.java)
                startActivity(mentalIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}