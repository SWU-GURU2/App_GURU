package com.example.guru2

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*

class WaterList : AppCompatActivity() {
    lateinit var WaterDBManger: WaterDBManger
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var layout: LinearLayout
    lateinit var cal_day: Button
    lateinit var calendarChoice: CalendarView
    lateinit var scrollWater: ScrollView
    lateinit var bgChoice:ImageView
    var selectYear: Int = 0
    var selectMonth: Int = 0
    var selectDate: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_list)

        WaterDBManger = WaterDBManger(this, "waterlist", null, 1)
        sqlitedb = WaterDBManger.readableDatabase

        layout = findViewById(R.id.waterlist)
        scrollWater = findViewById(R.id.scrollWater)
        cal_day = findViewById(R.id.cal_day)
        calendarChoice = findViewById(R.id.calendarChoice)
        bgChoice = findViewById(R.id.bgChoice)

        calendarChoice.setOnDateChangeListener { view, year, month, date ->
            selectYear = year
            selectMonth = month + 1
            selectDate = date

//            var selectYear = intent.getStringExtra("year")
//            var selectMonth = intent.getStringExtra("month")
//            var selectDate = intent.getStringExtra("date")
            //var str_waterml = intent.getStringExtra("water")

            cal_day.setOnClickListener {
                var alarm: String = selectYear.toString() + "년 " + selectMonth.toString() + "월 " + selectDate.toString() + "일 " + "기록을 확인합니다."
                Toast.makeText(applicationContext, alarm, Toast.LENGTH_SHORT).show()
            }

//            cal_day.setOnClickListener {
//                Toast.makeText(applicationContext, alarm, Toast.LENGTH_SHORT).show()
//
//                bgChoice.visibility = View.VISIBLE
//                cal_day.visibility = View.GONE
//                calendarChoice.visibility = View.GONE
//                scrollWater.visibility = ScrollView.VISIBLE
//                layout.visibility = LinearLayout.VISIBLE
//
//                var cursor: Cursor
//                cursor = sqlitedb.rawQuery(
//                        "SELECT * FROM waterlist WHERE year = " + selectYear + " AND month = " + selectMonth + " AND date = " + selectDate + ";",
//                        null
//                )
//
//                var num: Int = 0
//                while (cursor.moveToNext()) {
//                    var str_year = cursor.getString(cursor.getColumnIndex("year")).toString()
//                    var str_month = cursor.getString(cursor.getColumnIndex("month")).toString()
//                    var str_date = cursor.getString(cursor.getColumnIndex("date")).toString()
//                    var str_waterml = cursor.getString(cursor.getColumnIndex("water")).toString()
//
//                    var layout_item: LinearLayout = LinearLayout(this)
//                    layout_item.orientation = LinearLayout.VERTICAL
//                    layout_item.id = num
//
//                    var tvCalView: TextView = TextView(this)
//                    tvCalView.text = str_year + "년" + str_month + "월" + str_date + "일"
//                    tvCalView.textSize = 25F
//                    tvCalView.setBackgroundColor(Color.WHITE)
//                    tvCalView.setTextColor(Color.BLACK)
//                    layout_item.addView(tvCalView)
//
//                    var tvWater: TextView = TextView(this)
//                    tvWater.text = str_waterml
//                    tvWater.setBackgroundColor(Color.WHITE)
//                    tvWater.setTextColor(Color.BLACK)
//                    layout_item.addView(tvWater)
//
//                    layout_item.setOnClickListener {
//                        val intent = Intent(this, WaterEdit::class.java)
//                        intent.putExtra("intent_year", selectYear.toString())
//                        intent.putExtra("intent_month", selectMonth.toString())
//                        intent.putExtra("intent_date", selectDate.toString())
////                        intent.putExtra("intent_year", str_year.toString())
////                        intent.putExtra("intent_month", str_month.toString())
////                        intent.putExtra("intent_date", str_date.toString())
//                        intent.putExtra("intent_ml", str_waterml)
//                        startActivity(intent)
//                    }
//                    layout.addView(layout_item)
//                    num++
//                }
//                cursor.close()
//                sqlitedb.close()
//                WaterDBManger.close()
//            }
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
                val mintent = Intent(this, MainActivity::class.java)
                startActivity(mintent)
                return true
            }
            R.id.sport -> {
                val mintent = Intent(this, MainActivity::class.java)
                startActivity(mintent)
                return true
            }
            R.id.water -> {
                val mintent = Intent(this, WaterEdit3::class.java)
                startActivity(mintent)
                return true
            }
            R.id.mental -> {
                val mintent = Intent(this, MainActivity::class.java)
                startActivity(mintent)
                return true
            }
            R.id.meal -> {
                val mintent = Intent(this, MainActivity::class.java)
                startActivity(mintent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}