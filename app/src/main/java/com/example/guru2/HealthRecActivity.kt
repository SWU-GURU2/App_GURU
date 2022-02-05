package com.example.guru2

import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_health_rec.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HealthRecActivity : AppCompatActivity() {

    lateinit var calendarView : CalendarView
    lateinit var exec1 : TextView
    lateinit var exec2 : TextView
    lateinit var exec3 : TextView
    lateinit var exec4 : TextView
    lateinit var exec5 : TextView

    @RequiresApi(VERSION_CODES.O)
    //@RequiresApi(VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_rec)

        calendarView = findViewById<CalendarView>(R.id.calendarView)
        exec1 = findViewById<TextView>(R.id.exe1)
        exec2 = findViewById<TextView>(R.id.exe2)
        exec3 = findViewById<TextView>(R.id.exe3)
        exec4 = findViewById<TextView>(R.id.exe4)
        exec5 = findViewById<TextView>(R.id.exe5)

        var currentTime = LocalDateTime.now();
        val formatter = DateTimeFormatter.ISO_DATE
        val formatted = currentTime.format(formatter)
        val todayText = formatted.toString()

        calendarView.dateTextAppearance = R.style.TextAppearance_AppCompat_Large
        calendarView.weekDayTextAppearance = R.style.TextAppearance_AppCompat_Medium
        calendarView.minDate = SimpleDateFormat("yyyyMMdd").parse("20210101").time
        calendarView.maxDate - SimpleDateFormat("yyyyMMdd").parse("20221231").time

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            if (month < 9) {
                if (dayOfMonth < 10) {
                    today.text = "" + year + "-" + 0 + (month + 1) + "-" + 0 + dayOfMonth

                } else {
                    today.text = "" + year + "-" + 0 + (month + 1) + "-" + dayOfMonth
                }
            } else {
                if (dayOfMonth < 10) {
                    today.text = "" + year + "-" + (month + 1) + "-" + 0 + dayOfMonth
                } else {
                    today.text = "" + year + "-" + 0 + (month + 1) + "-" + dayOfMonth
                }
            }

            if (today.text == todayText) {

                var exercise_1 = intent.getStringExtra("가슴 운동").toFloat()
                var exercise_2 = intent.getStringExtra("팔 운동").toFloat()
                var exercise_3 = intent.getStringExtra("어깨ㆍ등 운동").toFloat()
                var exercise_4 = intent.getStringExtra("다리 운동").toFloat()
                var exercise_5 = intent.getStringExtra("복근 운동").toFloat()

                exec1.text = "가슴 운동 : " + exercise_1.toInt().toString() +"분"
                exec2.text = "팔 운동 : " + exercise_2.toInt().toString()+"분"
                exec3.text = "어께ㆍ등 운동 : " + exercise_3.toInt().toString()+"분"
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




                /* 원 그래프
                chart.setUsePercentValues(false)

                val entries = ArrayList<PieEntry>()
                entries.add(PieEntry(exercise_1, "가슴 운동"))
                entries.add(PieEntry(exercise_2, "팔 운동"))
                entries.add(PieEntry(exercise_3, "어깨ㆍ등 운동"))
                entries.add(PieEntry(exercise_4, "다리 운동"))
                entries.add(PieEntry(exercise_5, "복근 운동"))

                val colorsItem = ArrayList<Int>()
                for (c in ColorTemplate.VORDIPLOM_COLORS) colorsItem.add(c)
                for (c in ColorTemplate.JOYFUL_COLORS) colorsItem.add(c)
                for (c in COLORFUL_COLORS) colorsItem.add(c)
                for (c in ColorTemplate.LIBERTY_COLORS) colorsItem.add(c)
                for (c in ColorTemplate.PASTEL_COLORS) colorsItem.add(c)
                colorsItem.add(ColorTemplate.getHoloBlue())

                val pieDataSet = PieDataSet(entries, "")
                pieDataSet.apply {
                    colors = colorsItem
                    valueTextColor = Color.BLACK
                    valueTextSize = 12f
                }

                val pieData = PieData(pieDataSet)
                chart.apply {
                    data = pieData
                    description.isEnabled = false
                    isRotationEnabled = false
                    centerText = "운동 기록"
                    setEntryLabelColor(Color.BLACK)
                    animateY(1400, Easing.EaseInOutQuad)
                    animate()
                }*/

        }
    }
}