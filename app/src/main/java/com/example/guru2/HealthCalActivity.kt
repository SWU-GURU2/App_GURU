package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import kotlinx.android.synthetic.main.activity_health_cal.*
import kotlinx.android.synthetic.main.activity_health_list.*

class HealthCalActivity : AppCompatActivity() {
    lateinit var kcal : TextView
    lateinit var calendarButton : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_cal)

        var exercise_1 = intent.getStringExtra("가슴 운동").toFloat()
        var exercise_2 = intent.getStringExtra("팔 운동").toFloat()
        var exercise_3 = intent.getStringExtra("어깨ㆍ등 운동").toFloat()
        var exercise_4 = intent.getStringExtra("다리 운동").toFloat()
        var exercise_5 = intent.getStringExtra("복근 운동").toFloat()

        kcal = findViewById<TextView>(R.id.kcal)
        var totalcal = (exercise_1+exercise_2+exercise_3+exercise_4+exercise_5)*5
        kcal.text = totalcal.toInt().toString()

        calendarButton = findViewById<ImageButton>(R.id.calendarButton)
        calendarButton.setOnClickListener {
            val intent = Intent(this,HealthRecActivity::class.java)
            intent.putExtra("가슴 운동", exercise_1.toString())
            intent.putExtra("팔 운동", exercise_2.toString())
            intent.putExtra("어깨ㆍ등 운동", exercise_3.toString())
            intent.putExtra("다리 운동", exercise_4.toString())
            intent.putExtra("복근 운동", exercise_5.toString())
            startActivity(intent)
        }

        var barChart: BarChart = findViewById(R.id.barChart)

        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(1.2f,exercise_1*5))
        entries.add(BarEntry(2.2f,exercise_2*5))
        entries.add(BarEntry(3.2f,exercise_3*5))
        entries.add(BarEntry(4.2f,exercise_4*5))
        entries.add(BarEntry(5.2f,exercise_5*5))


        barChart.run {
            description.isEnabled = false
            setMaxVisibleValueCount(5)
            setPinchZoom(false)
            setDrawBarShadow(false)
            setDrawGridBackground(false)
            axisLeft.run {
                axisMaximum = 301f
                axisMinimum = 0f
                granularity = 100f
                setDrawLabels(true)
                setDrawGridLines(true)
                setDrawAxisLine(false)
                axisLineColor = ContextCompat.getColor(context,R.color.main_blue)
                gridColor = ContextCompat.getColor(context,R.color.design_default_color_on_secondary)
                textColor = ContextCompat.getColor(context,R.color.design_default_color_on_secondary)
                textSize = 13f
            }
            xAxis.run {
                position = XAxis.XAxisPosition.BOTTOM
                granularity = 1f
                setDrawAxisLine(true)
                setDrawGridLines(false)
                textColor = ContextCompat.getColor(context,R.color.design_default_color_on_secondary)
                textSize = 12f
                valueFormatter = MyXAxisFormatter()
            }
            axisRight.isEnabled = false
            setTouchEnabled(false)
            animateY(1000)
            legend.isEnabled = false
        }

        var set = BarDataSet(entries,"DataSet")
        set.color = ContextCompat.getColor(applicationContext!!,R.color.main_blue)

        val dataSet :ArrayList<IBarDataSet> = ArrayList()
        dataSet.add(set)
        val data = BarData(dataSet)
        data.barWidth = 0.3f
        barChart.run {
            this.data = data
            setFitBars(true)
            invalidate()
        }
    }

    inner class MyXAxisFormatter : ValueFormatter() {
        private val days = arrayOf("가슴운동","팔운동","어깨운동","다리운동","복근운동")
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return days.getOrNull(value.toInt()-1) ?: value.toString()
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
