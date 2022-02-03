package com.example.guru2

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class WaterCheck : AppCompatActivity() {
    lateinit var dbManger: WaterDBManger
    lateinit var sqlitedb: SQLiteDatabase

    lateinit var water_editButton: Button //음수량 수정하기 버튼
    lateinit var water_ml_view:TextView //ml 기록 보여줌
    lateinit var resultTextView:TextView
    lateinit var face: ImageView
    lateinit var cup:ImageView
    lateinit var cups:TextView

    var selectYear: Int = 0
    var selectMonth: Int = 0
    var selectDate: Int = 0
    var cup_ml: Int = 0
    var cup_clicked: Int = 0

    var watercount: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_check)

        water_ml_view = findViewById<TextView>(R.id.water_ml_view)
        water_editButton = findViewById<Button>(R.id.water_editButton)
        resultTextView = findViewById<TextView>(R.id.resultTextView)
        face = findViewById<ImageView>(R.id.face)
        cup = findViewById<ImageView>(R.id.cup)
        cups = findViewById<TextView>(R.id.cups)


//        dbManger = WaterDBManger(this, "waterDB", null, 1 )
//        sqlitedb = dbManger.readableDatabase
//
//        var cursor: Cursor
//        cursor == sqlitedb.rawQuery("SELECT * From water WHERE year = " + selectYear + "," + selectMonth + ", " + selectDate + ";", null)
//
//        if(cursor.moveToNext()){
//
//        }

        // 액티비티에 들어오자마자 바로 첨부된 값을 받아서 텍스트뷰에 반영
        val intent4 = getIntent()
        val name: String = intent4.getStringExtra("name")
        water_ml_view.setText(name+" ml")

        //음수량 확인
        val intent7 = getIntent()
        val w1: String = intent7.getStringExtra("w1")
        cups.setText(w1+" 잔")

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

    //메뉴바 추가
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    //메뉴바 이동 하기
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.main -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.sport -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.water -> {
                val intent = Intent(this, WaterEdit::class.java)
                startActivity(intent)
                return true
            }
            R.id.mental -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.meal -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}