package com.example.guru2

import android.graphics.Color
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Build.VERSION_CODES.JELLY_BEAN
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS
import kotlinx.android.synthetic.main.activity_health_rec.*
import java.text.SimpleDateFormat

class HealthRecActivity : AppCompatActivity() {
    @RequiresApi(VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_rec)

        calendarView.dateTextAppearance = R.style.TextAppearance_AppCompat_Large
        calendarView.weekDayTextAppearance = R.style.TextAppearance_AppCompat_Medium
        calendarView.minDate = SimpleDateFormat("yyyyMMdd").parse("20210101").time
        calendarView.maxDate - SimpleDateFormat("yyyyMMdd").parse("20221231").time

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            if (month < 9) {
                if (dayOfMonth < 10) {
                    today.text = "" + year + "년 " + 0 + (month + 1) + "월 " + 0 + dayOfMonth + "일"

                } else {
                    today.text = "" + year + "년 " + 0 + (month + 1) + "월 " + dayOfMonth + "일"
                }
            } else {
                if (dayOfMonth < 10) {
                    today.text = "" + year + "년 " + (month + 1) + "월 " + 0 + dayOfMonth + "일"
                } else {
                    today.text = "" + year + "년 " + 0 + (month + 1) + "월 " + dayOfMonth + "일"
                }
            }

            var exercise_1 = intent.getStringExtra("가슴 운동").toFloat()
            var exercise_2 = intent.getStringExtra("팔 운동").toFloat()
            var exercise_3 = intent.getStringExtra("어깨ㆍ등 운동").toFloat()
            var exercise_4 = intent.getStringExtra("다리 운동").toFloat()
            var exercise_5 = intent.getStringExtra("복근 운동").toFloat()

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
            }
        }
    }
}