package com.example.guru2

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

//음수량 수정 화면
class WaterEdit : AppCompatActivity() {
    //추후에 초기화 변수타입
    lateinit var resultButton: Button //기록하기 버튼
    lateinit var water_ml: EditText //ml 기록
    lateinit var B: Button

    lateinit var b1:Button
    lateinit var water1: ImageView
    lateinit var w_empty: ImageView
    lateinit var w_full: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_edit)

        resultButton = findViewById<Button>(R.id.resultButton)
        water1 = findViewById<ImageView>(R.id.water1);
        water_ml = findViewById<EditText>(R.id.water_ml)

        resultButton.setOnClickListener {
            var intent2 = Intent(this, WaterCheck::class.java)
            //intent2.putExtra("ml", water_ml.text.toString())
            startActivity(intent2)
        }

        b1.setOnClickListener {
            water1.setImageResource(R.drawable.w_full);

        }

    }

    fun printMessage(view: View) {
        var water_ml: EditText = findViewById(R.id.water_ml)
        var edit_text_value: String? = water_ml.text.toString()
        var intent_for_print: Intent = Intent(this, WaterCheck::class.java)
        intent_for_print.putExtra("key1", edit_text_value)
        startActivity(intent_for_print)
    }

}