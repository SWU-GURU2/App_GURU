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

        //HealthList에서 인텐트 값 받아옴
        var exercise_1 = intent.getStringExtra("가슴 운동").toFloat()
        var exercise_2 = intent.getStringExtra("팔 운동").toFloat()
        var exercise_3 = intent.getStringExtra("어깨ㆍ등 운동").toFloat()
        var exercise_4 = intent.getStringExtra("다리 운동").toFloat()
        var exercise_5 = intent.getStringExtra("복근 운동").toFloat()

        //전체 칼로리 소모량 수식으로 계산 후 문자열로 전환
        kcal = findViewById<TextView>(R.id.kcal)
        var totalcal = (exercise_1+exercise_2+exercise_3+exercise_4+exercise_5)*5
        kcal.text = totalcal.toInt().toString()

        calendarButton = findViewById<ImageButton>(R.id.calendarButton)
        calendarButton.setOnClickListener {
            //운동기록의 인텐트 값 받아오기
            val intent = Intent(this,HealthRecActivity::class.java)
            intent.putExtra("가슴 운동", exercise_1.toString())
            intent.putExtra("팔 운동", exercise_2.toString())
            intent.putExtra("어깨ㆍ등 운동", exercise_3.toString())
            intent.putExtra("다리 운동", exercise_4.toString())
            intent.putExtra("복근 운동", exercise_5.toString())
            startActivity(intent)
        }

        //막대그래프 생성
        var barChart: BarChart = findViewById(R.id.barChart)

        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(1.2f,exercise_1*5))
        entries.add(BarEntry(2.2f,exercise_2*5))
        entries.add(BarEntry(3.2f,exercise_3*5))
        entries.add(BarEntry(4.2f,exercise_4*5))
        entries.add(BarEntry(5.2f,exercise_5*5))

        barChart.run {
            description.isEnabled = false
            setMaxVisibleValueCount(5) //최대로 보이는 그래프 개수 5개로 설정
            setPinchZoom(false)
            setDrawBarShadow(false)
            setDrawGridBackground(false) //격자 구조 x
            axisLeft.run {
                axisMaximum = 301f //최대 300까지 표시하기 위해 301을 max로 서정
                axisMinimum = 0f //min값 0으로 설정
                granularity = 100f //100단위로 선 표시
                setDrawLabels(true)
                setDrawGridLines(true) //격자라인허용
                setDrawAxisLine(false)
                axisLineColor = ContextCompat.getColor(context,R.color.main_blue)//축색상
                gridColor = ContextCompat.getColor(context,R.color.design_default_color_on_secondary)//격자색상
                textColor = ContextCompat.getColor(context,R.color.design_default_color_on_secondary)//라벨 텍스트 컬러
                textSize = 13f //라벨 텍스트 크기
            }
            xAxis.run {
                position = XAxis.XAxisPosition.BOTTOM
                granularity = 1f //1단위로 간격
                setDrawAxisLine(true)
                setDrawGridLines(false)
                textColor = ContextCompat.getColor(context,R.color.design_default_color_on_secondary)
                textSize = 11f//텍스트 크기
                valueFormatter = MyXAxisFormatter()
            }
            axisRight.isEnabled = false//y축 안보이게끔
            setTouchEnabled(false)
            animateY(1000)
            legend.isEnabled = false
        }

        var set = BarDataSet(entries,"DataSet") //데이터 초기화
        set.color = ContextCompat.getColor(applicationContext!!,R.color.main_blue)//바 그래프 색 설정

        val dataSet :ArrayList<IBarDataSet> = ArrayList()
        dataSet.add(set)
        val data = BarData(dataSet)
        data.barWidth = 0.3f//막대 너비 설정
        barChart.run {
            this.data = data//데이터를 설정
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
