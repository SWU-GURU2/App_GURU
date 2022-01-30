package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

//음수량 수정 화면
class WaterEdit : AppCompatActivity() {
    //추후에 초기화 변수타입
    lateinit var resultButton: Button //기록하기 버튼
    lateinit var water_ml:EditText //ml 기록
    lateinit var B:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_edit)

        resultButton = findViewById<Button>(R.id.resultButton)
        //water_ml = findViewById<EditText>(R.id.water_ml)

        resultButton.setOnClickListener {
            var intent2 = Intent(this, WaterCheck::class.java)
            //intent2.putExtra("ml", water_ml.text.toString())
            startActivity(intent2)
        }

        B=findViewById<Button>(R.id.B)

       B.setOnClickListener{
//            var water_ml = findViewById<EditText>(R.id.water_ml)
//            var water_ml_value: String? = water_ml.text.toString()
//            var intent_for_print: Intent = Intent(this, WaterCheck::class.java)
//            intent_for_print.putExtra("key1", water_ml_value)
//            startActivity(intent_for_print)
           // 우선 들고갈 메시지 변수에 담기
       val inputMsg = water_ml.text.toString()
       // 이동할 액티비티 경로 잡기
       val myIntent = Intent(this, WaterCheck::class.java)
       //가지고 갈 메시지를 putExtra에 담기
       myIntent.putExtra("message", inputMsg)
       // 이동하기
       startActivity(myIntent)
        }

    }
}