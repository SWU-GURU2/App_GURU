package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class WaterCheck : AppCompatActivity() {
    lateinit var water_editButton: Button //음수량 수정하기 버튼
    lateinit var water_ml_view:TextView //ml 기록 보여줌

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_check)

        water_ml_view = findViewById<TextView>(R.id.water_ml_view)
        water_editButton = findViewById<Button>(R.id.water_editButton)

        // 액티비티에 들어오자마자 바로 첨부된 값을 받아서 텍스트뷰에 반영
        val intent4 = getIntent()
        val name: String = intent4.getStringExtra("name")
        water_ml_view.setText(name+" ml")

        //수정하기 버튼 클릭시 할 행동
        water_editButton.setOnClickListener {
            var intent3 = Intent(this, WaterEdit::class.java) //인텐트 생성
            startActivity(intent3)
        }
    }
}