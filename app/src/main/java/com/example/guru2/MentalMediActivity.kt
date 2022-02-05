package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import java.util.*
import kotlin.concurrent.timer

class MentalMediActivity : AppCompatActivity() {
    //변수 (타이머)
    private var time=0
    private var timerTask: Timer?=null
    private var isRunning=false
    private var lap=1

    //기분 버튼 변수
    lateinit var vhapButton: Button
    lateinit var hapButton: Button
    lateinit var soButton: Button
    lateinit var sadButton: Button
    lateinit var unhapButton: Button
    lateinit var surpriseButton:Button
    lateinit var refreshButton: Button

    //타이머 변수
    lateinit var meditateText:TextView
    lateinit var mediImage:ImageView
    lateinit var minTextView:TextView
    lateinit var secTextView: TextView
    lateinit var secEditText: EditText
    lateinit var mediSetButton:Button
    lateinit var mediStopButton:Button
    lateinit var mediIninButton:Button
    lateinit var cTextView: TextView
    lateinit var lineimg: ImageView
    lateinit var mediButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mental_medi)

        //버튼 연결
        vhapButton = findViewById(R.id.vhapButton)
        hapButton = findViewById(R.id.hapButton)
        soButton = findViewById(R.id.soButton)
        sadButton = findViewById(R.id.sadButton)
        unhapButton = findViewById(R.id.unhapButton)
        surpriseButton = findViewById(R.id.surpriseButton)
        refreshButton = findViewById(R.id.refreshButton)
        mediButton=findViewById(R.id.mediButton)

        //타이머 위젯 연결
        meditateText = findViewById(R.id.meditateText)
        mediImage = findViewById(R.id.mediImage)
        minTextView = findViewById(R.id.minTextView)
        secTextView = findViewById(R.id.secTextView)
        secEditText = findViewById(R.id.secEditText)
        cTextView = findViewById(R.id.cTextview)
        lineimg = findViewById(R.id.lineimg)
        mediSetButton = findViewById(R.id.mediSetButton)
        mediStopButton=findViewById(R.id.mediStopButton)
        mediIninButton=findViewById(R.id.mediInitButton)

        //기분-> 버튼 누르고 명상창 보이기
        refreshButton.setOnClickListener {
            meditateText.visibility = View.VISIBLE
            mediImage.visibility = View.VISIBLE
            minTextView.visibility = View.VISIBLE
            secTextView.visibility = View.VISIBLE
            secEditText.visibility = View.VISIBLE
            cTextView.visibility = View.VISIBLE
            lineimg.visibility = View.VISIBLE
            mediSetButton.visibility = View.VISIBLE
            mediStopButton.visibility=View.VISIBLE
            mediButton.visibility=View.VISIBLE
            mediIninButton.visibility=View.VISIBLE
        }
        //넘어가기-> colors로 넘어가기
        val colorIntent=Intent(this,MentalColorsActivity::class.java)
        mediButton.setOnClickListener{
            startActivity(colorIntent)
        }

        //타이머 설정
        mediSetButton.setOnClickListener {
            time = secEditText.text.toString().toInt() * 600
            start()
            refreshButton.visibility=View.INVISIBLE
        }
        mediStopButton.setOnClickListener{
            pause()
        }
        mediIninButton.setOnClickListener{
            reset()
        }
        //기분 나타내는 버튼 누르기 설정
        vhapButton.setOnClickListener(ButtonListener())
        hapButton.setOnClickListener(ButtonListener())
        surpriseButton.setOnClickListener(ButtonListener())
        soButton.setOnClickListener(ButtonListener())
        sadButton.setOnClickListener(ButtonListener())
        unhapButton.setOnClickListener(ButtonListener())
    }

    inner class ButtonListener:View.OnClickListener{
        override fun onClick(v: View?) {
            when(v?.id){
                R.id.vhapButton->{ val toast=Toast.makeText(applicationContext, "오늘의 마음을 비우시겠습니까?", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER,0,230)
                    toast.show() }
                R.id.hapButton->{ val toast=Toast.makeText(applicationContext, "오늘의 마음을 비우시겠습니까?", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER,0,230)
                    toast.show() }
                R.id.surpriseButton->{ val toast=Toast.makeText(applicationContext, "오늘의 마음을 비우시겠습니까?", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER,0,230)
                    toast.show() }
                R.id.soButton->{ val toast=Toast.makeText(applicationContext, "오늘의 마음을 비우시겠습니까?", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER,0,230)
                    toast.show() }
                R.id.sadButton->{ val toast=Toast.makeText(applicationContext, "오늘의 마음을 비우시겠습니까?", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER,0,230)
                    toast.show() }
                R.id.unhapButton->{ val toast=Toast.makeText(applicationContext, "오늘의 마음을 비우시겠습니까?", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER,0,230)
                    toast.show() }
            }
        }
    }

    private fun pause(){
        timerTask?.cancel()
    }
    //타이머 시간 분단위로 설정하기
    private fun start(){
        timerTask= timer(period=1000) {
            time--
            val min=time/600
            val sec = time%60

            if (min==0&&sec==0) timerTask?.cancel()
            runOnUiThread {
                minTextView.text="$min"
                secTextView.text = "$sec"
            }
        }
    }

    // 타이머 멈추는 시간 설정
    private fun reset(){
        timerTask?.cancel()
        time=0
        isRunning=false
        minTextView.text="00"
        secTextView.text="00"
        lap=1
    }

    //메뉴바 추가
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
                val waterIntent=Intent(this,WaterEdit::class.java)
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