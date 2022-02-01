package com.example.guru2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

//음수량 수정 화면
class WaterEdit : AppCompatActivity() {
    //추후에 초기화 변수타입
    lateinit var water_ml: EditText //ml 기록
    lateinit var btn_intent: Button // resultbutton과 같은 기록하기 버튼

    lateinit var water1: ImageView //컵
    lateinit var water2: ImageView
    lateinit var water3: ImageView
    lateinit var water4: ImageView
    lateinit var water5: ImageView
    lateinit var water6: ImageView
    lateinit var water7: ImageView
    lateinit var water8: ImageView
    lateinit var w_empty: ImageView
    lateinit var w_full: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_edit)

        water1 = findViewById<ImageView>(R.id.water1);
        water2 = findViewById<ImageView>(R.id.water2);
        water3 = findViewById<ImageView>(R.id.water3);
        water4 = findViewById<ImageView>(R.id.water4);
        water5 = findViewById<ImageView>(R.id.water5);
        water6 = findViewById<ImageView>(R.id.water6);
        water7 = findViewById<ImageView>(R.id.water7);
        water8 = findViewById<ImageView>(R.id.water8);

        water_ml = findViewById<EditText>(R.id.water_ml)
        btn_intent = findViewById<Button>(R.id.btn_intent)
        //resultButton = findViewById<Button>(R.id.resultButton)

        water1.setOnClickListener {
            water1.isSelected = water1.isSelected != true
        }
        water2.setOnClickListener {
            water2.isSelected = water2.isSelected != true
        }
        water3.setOnClickListener {
            water3.isSelected = water3.isSelected != true
        }
        water4.setOnClickListener {
            water4.isSelected = water4.isSelected != true
        }
        water5.setOnClickListener {
            water5.isSelected = water5.isSelected != true
        }
        water6.setOnClickListener {
            water6.isSelected = water6.isSelected != true
        }
        water7.setOnClickListener {
            water7.isSelected = water7.isSelected != true
        }
        water8.setOnClickListener {
            water8.isSelected = water8.isSelected != true
        }


        btn_intent.setOnClickListener {
            val intent2 = Intent(this, WaterCheck::class.java)
            intent2.putExtra("name", water_ml.text.toString())
            startActivity(intent2)
        }
//        resultButton.setOnClickListener {
//            var intent2 = Intent(this, WaterCheck::class.java)
//            startActivity(intent2)
//        }

    }
}

