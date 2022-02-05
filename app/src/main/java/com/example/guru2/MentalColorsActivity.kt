package com.example.guru2

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import java.util.*

class MentalColorsActivity : AppCompatActivity() {
    //변수 선언
    private var time=0
    private var timerTask: Timer?=null
    private var isRunning=false
    private var lap=1


    //노래 재생 버튼
    lateinit var playR:ImageButton
    lateinit var playO:ImageButton
    lateinit var playY:ImageButton
    lateinit var playG:ImageButton
    lateinit var playB:ImageButton
    lateinit var playP:ImageButton

    //노래 멈춤 버튼
    lateinit var stopR:ImageButton
    lateinit var stopO:ImageButton
    lateinit var stopY:ImageButton
    lateinit var stopG:ImageButton
    lateinit var stopB:ImageButton
    lateinit var stopP:ImageButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mental_colors)

        //노래-플레이어 연결
        var musicRed = MediaPlayer.create(this, R.raw.play1)
        var musicOrange=MediaPlayer.create(this,R.raw.play2)
        var musicYellow=MediaPlayer.create(this,R.raw.play3)
        var musicGreen=MediaPlayer.create(this,R.raw.play4)
        var musicBlue=MediaPlayer.create(this,R.raw.play5)
        var musicPurple=MediaPlayer.create(this,R.raw.play6)

        playR= findViewById(R.id.playR)
        playO= findViewById(R.id.playO)
        playY= findViewById(R.id.playY)
        playG= findViewById(R.id.playG)
        playB=findViewById(R.id.PlayB)
        playP= findViewById(R.id.playP)
        stopR = findViewById(R.id.stopR)
        stopO = findViewById(R.id.stopO)
        stopY = findViewById(R.id.stopY)
        stopG = findViewById(R.id.stopG)
        stopB= findViewById(R.id.stopB)
        stopP= findViewById(R.id.stopP)

        //노래재생연결
        playR.setOnClickListener {
            musicRed.start()
            playR.visibility = View.INVISIBLE
            stopR.visibility = View.VISIBLE
        }
        stopR.setOnClickListener {
            playR.visibility = View.VISIBLE
            stopR.visibility=View.INVISIBLE
            musicRed.pause()
            MediaPlayer.create(this, R.raw.play1)
        }
        playO.setOnClickListener {
            musicOrange.start()
            playO.visibility = View.INVISIBLE
            stopO.visibility = View.VISIBLE
        }
        stopO.setOnClickListener {
            playO.visibility = View.VISIBLE
            stopO.visibility=View.INVISIBLE
            musicOrange.pause()
            MediaPlayer.create(this, R.raw.play2)
        }
        playY.setOnClickListener {
            musicYellow.start()
            playY.visibility = View.INVISIBLE
            stopY.visibility = View.VISIBLE
        }
        stopY.setOnClickListener {
            playY.visibility = View.VISIBLE
            stopY.visibility=View.INVISIBLE
            musicYellow.pause()
            MediaPlayer.create(this, R.raw.play3)
        }
        playG.setOnClickListener {
            musicGreen.start()
            playG.visibility = View.INVISIBLE
            stopG.visibility = View.VISIBLE
        }
        stopG.setOnClickListener {
            playG.visibility = View.VISIBLE
            stopG.visibility=View.INVISIBLE
            musicGreen.pause()
            MediaPlayer.create(this, R.raw.play4)
        }
        playB.setOnClickListener {
            musicBlue.start()
            playB.visibility = View.INVISIBLE
            stopB.visibility = View.VISIBLE
        }
        stopB.setOnClickListener {
            playB.visibility = View.VISIBLE
            stopB.visibility=View.INVISIBLE
            musicBlue.pause()
            MediaPlayer.create(this, R.raw.play5)
        }
        playP.setOnClickListener {
            musicPurple.start()
            playP.visibility = View.INVISIBLE
            stopP.visibility = View.VISIBLE
        }
        stopP.setOnClickListener {
            playP.visibility = View.VISIBLE
            stopP.visibility=View.INVISIBLE
            musicPurple.pause()
            MediaPlayer.create(this, R.raw.play6)
        }

    }

    //메뉴바 추가
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    //메뉴바 이동
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.main->{
                val mainIntent= Intent(this,MainActivity::class.java)
                startActivity(mainIntent)
            }
            R.id.sport->{
                val sportIntent=Intent(this,HealthListActivity::class.java)
                startActivity(sportIntent)
            }
            R.id.water->{
                val waterIntent=Intent(this,WaterEdit::class.java)
                startActivity(waterIntent)
            }
            R.id.mental->{
                val mentalIntent=Intent(this,MentalDailyActivity::class.java)
                startActivity(mentalIntent)
            }
            R.id.meal->{
                val mealIntent=Intent(this,MealWriteActivity::class.java)
                startActivity(mealIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}