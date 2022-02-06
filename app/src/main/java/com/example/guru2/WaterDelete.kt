package com.example.guru2

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class WaterDelete : AppCompatActivity() {
    lateinit var WaterDBManger: WaterDBManger
    lateinit var sqlitedb: SQLiteDatabase

    lateinit var str_year: String
    lateinit var str_month: String
    lateinit var str_date: String
    lateinit var str_waterml: String

    lateinit var watertext: TextView
    lateinit var deletebutton: Button
    lateinit var backbutton: Button
    lateinit var deletedate: TextView
    lateinit var deleteml: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_delete)

        watertext.findViewById<TextView>(R.id.watertext)
        deletebutton.findViewById<Button>(R.id.deletebutton)
        backbutton.findViewById<Button>(R.id.backbutton)
        deletedate.findViewById<TextView>(R.id.deletedate)
        deleteml.findViewById<TextView>(R.id.deleteml)

        WaterDBManger = WaterDBManger(this, "waterlist", null, 1 )
        sqlitedb = WaterDBManger.readableDatabase

//
//        val intent = intent
//        str_year = intent.getStringExtra("year").toString()
//        str_month = intent.getStringExtra("month").toString()
//        str_date = intent.getStringExtra("date").toString()
//        str_waterml = intent.getStringExtra("water").toString()
//
//        deletedate.text = str_year + "년 " + str_month + "월 " + str_date + "일 "
//        deleteml.text = str_waterml + "ml"
//
//        deletebutton.setOnClickListener {
//            sqlitedb.execSQL("DELETE FROM waterlist WHERE year = "+str_year + " AND month = " + str_month + " AND date = " + str_date + ";")
//            sqlitedb.close()
//            WaterDBManger.close()
//            Toast.makeText(applicationContext, "기록이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
//
//            val intent = Intent(this, WaterCheck::class.java)
//            startActivity(intent)
//        }
//
//        backbutton.setOnClickListener {
//            val intent = Intent(this, WaterCheck::class.java)
//            startActivity(intent)
//        }


    }
}