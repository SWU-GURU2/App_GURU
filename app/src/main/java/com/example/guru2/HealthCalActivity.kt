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
            setMaxVisibleValueCount(5) // 최대 보이는 그래프 개수를 5개로 지정
            setPinchZoom(false)
            setDrawBarShadow(false)
            setDrawGridBackground(false)
            axisLeft.run { //왼쪽 축. 즉 Y방향 축을 뜻한다.
                axisMaximum = 301f //50 위치에 선을 그리기 위해 51f로 맥시멈값 설정
                axisMinimum = 0f // 최소값 0
                granularity = 100f // 10 단위마다 선을 그리려고 설정.
                setDrawLabels(true)
                setDrawGridLines(true) //격자 라인 활용
                setDrawAxisLine(false) // 축 그리기 설정
                axisLineColor = ContextCompat.getColor(context,R.color.main_blue) // 축 색깔 설정
                gridColor = ContextCompat.getColor(context,R.color.design_default_color_on_secondary) // 축 아닌 격자 색깔 설정
                textColor = ContextCompat.getColor(context,R.color.design_default_color_on_secondary) // 라벨 텍스트 컬러 설정
                textSize = 13f //라벨 텍스트 크기
            }
            xAxis.run {
                position = XAxis.XAxisPosition.BOTTOM //X축을 아래에다가 둔다.
                granularity = 1f // 1 단위만큼 간격 두기
                setDrawAxisLine(true) // 축 그림
                setDrawGridLines(false) // 격자
                textColor = ContextCompat.getColor(context,R.color.design_default_color_on_secondary) //라벨 색상
                textSize = 12f // 텍스트 크기
                valueFormatter = MyXAxisFormatter() // X축 라벨값(밑에 표시되는 글자) 바꿔주기 위해 설정
            }
            axisRight.isEnabled = false // 오른쪽 Y축을 안보이게 해줌.
            setTouchEnabled(false) // 그래프 터치해도 아무 변화없게 막음
            animateY(1000) // 밑에서부터 올라오는 애니매이션 적용
            legend.isEnabled = false //차트 범례 설정
        }

        var set = BarDataSet(entries,"DataSet") // 데이터셋 초기화
        set.color = ContextCompat.getColor(applicationContext!!,R.color.main_blue) // 바 그래프 색 설정

        val dataSet :ArrayList<IBarDataSet> = ArrayList()
        dataSet.add(set)
        val data = BarData(dataSet)
        data.barWidth = 0.3f //막대 너비 설정
        barChart.run {
            this.data = data //차트의 데이터를 data로 설정해줌.
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

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.health_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.calendar -> {
                val intent = Intent(this, HealthRecActivity::class.java)
                intent.putExtra("가슴 운동", exercise_1.toString())
                intent.putExtra("팔 운동", exercise_2.toString())
                intent.putExtra("어깨ㆍ등 운동", exercise_3.toString())
                intent.putExtra("다리 운동", exercise_4.toString())
                intent.putExtra("복근 운동", exercise_5.toString())
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }*/

}