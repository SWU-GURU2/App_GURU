package com.example.guru2

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.FileInputStream
import java.util.*

//음수량 수정 화면
class WaterEdit : AppCompatActivity() {
    //추후에 초기화 변수타입
    lateinit var cal_day: Button //날짜 선택
    lateinit var cal_view: TextView // 선택한 날짜
    lateinit var calendarView: CalendarView //달력
    lateinit var water1: ImageView //컵
    lateinit var water2: ImageView
    lateinit var water3: ImageView
    lateinit var water4: ImageView
    lateinit var water5: ImageView
    lateinit var water6: ImageView
    lateinit var water7: ImageView
    lateinit var water8: ImageView
    var watercount : Int = 0
    lateinit var water_ml: EditText //ml 기록
    lateinit var btn_intent: Button // resultbutton과 같은 기록하기 버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_edit)

        //UI값 생성
        cal_day = findViewById<Button>(R.id.cal_day)
        cal_view = findViewById<TextView>(R.id.cal_view)
        //calendarView = findViewById<CalendarView>(R.id.calendarView)
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

        //날짜 선택
        cal_day.setOnClickListener {
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            var listener = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                cal_view.text = "${i}년 ${i2 + 1}월 ${i3}일"
            }
            var picker = DatePickerDialog(this, listener, year, month, day)
            picker.show()
        }
        //컵 클릭 효과
        water1.setOnClickListener {
            water1.isSelected = water1.isSelected != true
            if(water1.isSelected){
                watercount++
            }
        }
        water2.setOnClickListener {
            water2.isSelected = water2.isSelected != true
            if(water2.isSelected){
                watercount++
            }
        }
        water3.setOnClickListener {
            water3.isSelected = water3.isSelected != true
            if(water3.isSelected){
                watercount++
            }
        }
        water4.setOnClickListener {
            water4.isSelected = water4.isSelected != true
            if(water4.isSelected){
                watercount++
            }
        }
        water5.setOnClickListener {
            water5.isSelected = water5.isSelected != true
            if(water5.isSelected){
                watercount++
            }
        }
        water6.setOnClickListener {
            water6.isSelected = water6.isSelected != true
            if(water6.isSelected){
                watercount++
            }
        }
        water7.setOnClickListener {
            water7.isSelected = water7.isSelected != true
            if(water7.isSelected){
                watercount++
            }
        }
        water8.setOnClickListener {
            water8.isSelected = water8.isSelected != true
            if(water8.isSelected){
                watercount++
            }
        }

        btn_intent.setOnClickListener {
            val intent2 = Intent(this, WaterCheck::class.java)
            intent2.putExtra("name", water_ml.text.toString())
            if (water_ml.text.toString().length == 0) {
                Toast.makeText(getApplicationContext(), "ml까지 작성해주세요. 한잔당 250ml입니다", Toast.LENGTH_LONG).show();
            } else {
                intent2.putExtra("w1", watercount.toString())
                startActivity(intent2)
            }
        }
    }
}


