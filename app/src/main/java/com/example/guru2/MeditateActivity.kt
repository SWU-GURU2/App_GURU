package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*
import kotlin.concurrent.timer

class MeditateActivity : AppCompatActivity() {
    //변수 선언
    private var time=0
    private var timerTask: Timer?=null
    private var isRunning=false
    private var lap=1


    lateinit var minTextView:TextView
    lateinit var secTextView: TextView
    lateinit var secEditText: EditText
    lateinit var setButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditate)

        minTextView=findViewById(R.id.minTextView)
        secTextView=findViewById(R.id.secTextView)
        secEditText=findViewById(R.id.secEditText)
        setButton=findViewById(R.id.setButton)

        setButton.setOnClickListener {
            time=secEditText.text.toString().toInt()*600
            start()
        }

    }
    private fun pause(){
        timerTask?.cancel()
    }
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

    private fun reset(){
        timerTask?.cancel()

        time=0
        isRunning=false
        minTextView.text="0"
        secTextView.text="0"

        lap=1
    }





    //메뉴바 추가
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.main->{
                val intent: Intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            R.id.mental->{
                val MentalIntent= Intent(this,MainActivity::class.java)
                startActivity((MentalIntent))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}