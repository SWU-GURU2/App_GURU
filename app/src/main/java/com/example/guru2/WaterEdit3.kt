package com.example.guru2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


//음수량 수정 화면
class WaterEdit3 : AppCompatActivity() {
    lateinit var calendarView: CalendarView
    lateinit var textView7: TextView
    lateinit var back_cal_day: Button
    lateinit var cal_day: Button
    lateinit var cal_view: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_edit3)

        calendarView.findViewById<CalendarView>(R.id.calendarView)
        textView7.findViewById<TextView>(R.id.textView7)
        back_cal_day.findViewById<Button>(R.id.back_cal_day)
        cal_day.findViewById<Button>(R.id.cal_day)
        cal_view.findViewById<TextView>(R.id.cal_view)

//        calendarView.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
//            override fun onSelectedDayChange(p0: CalendarView, p1: Int, p2: Int, p3: Int) {
//                cal_view.text  ="$p3.${p2 + 1}.$p1"
//            }
//        })

        cal_day.setOnClickListener {
            calendarView.visibility= View.INVISIBLE
            textView7.visibility=View.INVISIBLE
            cal_view.visibility=View.INVISIBLE
        }
        back_cal_day.setOnClickListener {
            calendarView.visibility= View.VISIBLE
            textView7.visibility=View.VISIBLE
            cal_view.visibility=View.INVISIBLE
        }
    }
}




