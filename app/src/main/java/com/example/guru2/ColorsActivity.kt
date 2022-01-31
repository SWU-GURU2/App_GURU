package com.example.guru2

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.SeekBar

class ColorsActivity : AppCompatActivity() {

    lateinit var btnRed:Button
    lateinit var btnOrange:Button
    lateinit var btnYellow:Button
    lateinit var btnGreen:Button
    lateinit var btnBlue:Button
    lateinit var btnPurple:Button
    lateinit var btnRpause:Button
    lateinit var btnOpause:Button
    lateinit var btnYpause:Button
    lateinit var btnGpause:Button
    lateinit var btnBpause:Button
    lateinit var btnPpause:Button
    //lateinit var seekBar: SeekBar //음량 설정


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colors)
        //플레이어 연결
        var mediaPlayer1=MediaPlayer.create(this,R.raw.play1)
        var mediaPlayer2=MediaPlayer.create(this,R.raw.play2)
        var mediaPlayer3=MediaPlayer.create(this,R.raw.play3)
        var mediaPlayer4=MediaPlayer.create(this,R.raw.play4)
        var mediaPlayer5=MediaPlayer.create(this,R.raw.play5)
        var mediaPlayer6=MediaPlayer.create(this,R.raw.play6)

        //서비스 위젯 연결
        btnRed=findViewById(R.id.btnRed)
        btnOrange=findViewById(R.id.btnOrange)
        btnYellow=findViewById(R.id.btnYellow)
        btnGreen=findViewById(R.id.btnGreen)
        btnBlue=findViewById(R.id.btnBlue)
        btnPurple=findViewById(R.id.btnPurple)
        btnRpause=findViewById(R.id.btnRpause)
        btnOpause=findViewById(R.id.btnOpause)
        btnYpause=findViewById(R.id.btnYpause)
        btnGpause=findViewById(R.id.btnGpause)
        btnBpause=findViewById(R.id.btnBpause)
        btnPpause=findViewById(R.id.btnPpause)



        //노래 재생 버튼
        btnRed.setOnClickListener {
            mediaPlayer1.start()
            Log.i("노래 재생","startService")
        }
        btnOrange.setOnClickListener {
            mediaPlayer2.start()
            Log.i("노래 재생","startService")
        }
        btnYellow.setOnClickListener {
            mediaPlayer3.start()
            Log.i("노래 재생","startService")
        }
        btnGreen.setOnClickListener {
            mediaPlayer4.start()
            Log.i("노래 재생","startService")
        }
        btnBlue.setOnClickListener {
            mediaPlayer5.start()
            Log.i("노래 재생","startService")
        }
        btnPurple.setOnClickListener {
            mediaPlayer6.start()
            Log.i("노래 재생","startService")
        }
        //노래 멈춤 버튼
        btnRpause.setOnClickListener {
            mediaPlayer1.pause()
            Log.i("노래 멈춤","stopService")
        }
        btnOpause.setOnClickListener {
            mediaPlayer2.pause()
            Log.i("노래 멈춤","stopService")
        }
        btnYpause.setOnClickListener {
            mediaPlayer3.pause()
            Log.i("노래 멈춤","stopService")
        }
        btnGpause.setOnClickListener {
            mediaPlayer4.pause()
            Log.i("노래 멈춤","stopService")
        }
        btnBpause.setOnClickListener {
            mediaPlayer5.pause()
            Log.i("노래 멈춤","stopService")
        }
        btnPpause.setOnClickListener {
            mediaPlayer6.pause()
            Log.i("노래 멈춤","stopService")
        }
    }




    //메뉴바 추가
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.main->{
                val intent:Intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            R.id.mental->{
                val MentalIntent=Intent(this,MainActivity::class.java)
                startActivity((MentalIntent))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}