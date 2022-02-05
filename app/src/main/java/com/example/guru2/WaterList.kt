package com.example.guru2

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView

class WaterList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var WaterDBManger: WaterDBManger
        lateinit var sqlitedb: SQLiteDatabase
        lateinit var layout: LinearLayout

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_list)

        WaterDBManger = WaterDBManger(this, "waterDB", null, 1 )
        sqlitedb = WaterDBManger.readableDatabase

        layout = findViewById(R.id.waterlist)

        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM waterlist", null)

        var num: Int = 0
        while (cursor.moveToNext()){
            var str_year = cursor.getString(cursor.getColumnIndex("year")).toString()
            var str_month = cursor.getString(cursor.getColumnIndex("month")).toString()
            var str_date = cursor.getString(cursor.getColumnIndex("date")).toString()
            var str_waterml = cursor.getString(cursor.getColumnIndex("water")).toString()

            var layout_item: LinearLayout = LinearLayout(this)
            layout_item.orientation = LinearLayout.VERTICAL
            layout_item.id = num

            var tvCalView: TextView = TextView(this)
            tvCalView.text = str_year + "년" + str_month + "월" + str_date + "일"
            tvCalView.textSize = 25F
            tvCalView.setBackgroundColor(Color.WHITE)
            tvCalView.setTextColor(Color.BLACK)
            layout_item.addView(tvCalView)

            var tvWater: TextView = TextView(this)
            tvWater.text = str_waterml
            tvWater.textSize = 25F
            tvWater.setBackgroundColor(Color.WHITE)
            tvWater.setTextColor(Color.BLACK)
            layout_item.addView(tvWater)

            layout_item.setOnClickListener{
                val intent = Intent(this, WaterEdit::class.java)
                intent.putExtra("intent_year", str_year.toString())
                intent.putExtra("intent_month", str_month.toString())
                intent.putExtra("intent_date", str_date.toString())
                intent.putExtra("intent_ml", str_waterml)
                startActivity(intent)
            }
            layout.addView(layout_item)
            num++
        }
        cursor.close()
        sqlitedb.close()
        WaterDBManger.close()
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
                val mintent = Intent(this, WaterEdit::class.java)
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