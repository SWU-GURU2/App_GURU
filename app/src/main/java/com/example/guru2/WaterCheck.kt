package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class WaterCheck : AppCompatActivity() {
    lateinit var water_editButton: Button //음수량 수정하기 버튼
    lateinit var water_ml_view:TextView //ml 기록 보여줌
    var watercount: Int = 0
    lateinit var resultTextView:TextView
    lateinit var face: ImageView
    lateinit var cup:ImageView
    lateinit var cups:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_check)

        water_ml_view = findViewById<TextView>(R.id.water_ml_view)
        water_editButton = findViewById<Button>(R.id.water_editButton)
        resultTextView = findViewById<TextView>(R.id.resultTextView)
        face = findViewById<ImageView>(R.id.face)
        cup = findViewById<ImageView>(R.id.cup)
        cups = findViewById<TextView>(R.id.cups)

        // 액티비티에 들어오자마자 바로 첨부된 값을 받아서 텍스트뷰에 반영
        val intent4 = getIntent()
        val name: String = intent4.getStringExtra("name")
        water_ml_view.setText(name+" ml")

        val intent7 = getIntent()
        val w1: String = intent7.getStringExtra("w1")
        cups.setText(w1+" 잔")
        //음수량 확인
        //var water1 = intent.getStringExtra("w1").toInt()
//        val watercount = intent.getStringExtra("w1").toInt()
//
//        //음수량 적당한지 확인-> text 출력
//        when{
//            watercount >= 1 -> resultTextView.text = "적당"
//        }
//        //음수량 적당한지 확인-> image 출력
//        when {
//            watercount >= 1 ->
//                face.setImageResource(R.drawable.smile2)
//        }
        
        when {
//            name.toInt() <= 600
                    w1.toInt() <= 3  -> {
                face.setImageResource(R.drawable.sad)
                cup.setImageResource(R.drawable.w_low)
                resultTextView.text = "부족"
            }
            w1.toInt() <= 6 -> {
                face.setImageResource(R.drawable.smile2)
                cup.setImageResource(R.drawable.w_medium)
                resultTextView.text = "적당"
            }
            else -> {
                face.setImageResource(R.drawable.happy)
                cup.setImageResource(R.drawable.w_full)
                resultTextView.text = "충분"
            }
        }

        //수정하기 버튼 클릭시 할 행동
        water_editButton.setOnClickListener {
            var intent3 = Intent(this, WaterEdit::class.java) //인텐트 생성
            startActivity(intent3)
        }
    }
}