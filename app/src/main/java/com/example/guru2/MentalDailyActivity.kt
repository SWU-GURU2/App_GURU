package com.example.guru2

import android.Manifest
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.util.*

class MentalDailyActivity: AppCompatActivity() {
    lateinit var dailyText: EditText
    lateinit var dailyButton:Button
    lateinit var daydelButton: Button
    lateinit var doneButton:Button
    lateinit var fileName:String
    lateinit var dailycalendarView: CalendarView
    lateinit var feelingButton: Button
    lateinit var colorsButton: Button

    lateinit var mentalDB:myDbHelper
    lateinit var sqlitedb:SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mental_daily)
        //화면 이동
        feelingButton=findViewById(R.id.feelingButton)
        colorsButton=findViewById(R.id.colorsButton)
        val secondIntent= Intent(this,MentalMediActivity::class.java)
        feelingButton.setOnClickListener {
            startActivity(secondIntent)//화면 전환
        }
        val thirdIntent=Intent(this,MentalColorsActivity::class.java)
        colorsButton.setOnClickListener{
            startActivity(thirdIntent)
        }

        //달력
        ActivityCompat.requestPermissions(this, arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ), MODE_PRIVATE)
        dailycalendarView=findViewById(R.id.dailycalendarView)
        dailyText= findViewById(R.id.dailyText)
        dailyButton = findViewById(R.id.dailyButton)
        daydelButton=findViewById(R.id.daydelButton)
        doneButton=findViewById(R.id.doneButton)
        mentalDB = myDbHelper(this)

        val cal = Calendar.getInstance()
        val cYear = cal[Calendar.YEAR]
        val cMonth = cal[Calendar.MONTH]
        val cDate = cal[Calendar.DATE]

        fileName = cYear.toString() + "_" + (cMonth + 1).toString() + "_" + cDate.toString()
        dailyButton.isEnabled = true

        dailycalendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            fileName = year.toString() + "_" + (month + 1).toString() + "_" +
                    dayOfMonth.toString()
            val str = readDiary(fileName)
            dailyText.setText(str)
            dailyButton.isEnabled = true
            dailyText.visibility=View.VISIBLE
            dailyButton.visibility=View.VISIBLE
            daydelButton.visibility= View.VISIBLE
            doneButton.visibility=View.VISIBLE
        }
        //하루 일기 저장+ db저장
        dailyButton.setOnClickListener {
            val str =dailyText.text.toString()
            sqlitedb=  mentalDB.writableDatabase
            // 달력 일기 -빈내용 입력시
            if (str.length == 0 && dailyButton.text == "저장") {
                Toast.makeText(getApplicationContext(), "당신의 오늘 하루 이야기를 들려주세요", Toast.LENGTH_LONG).show()
                dailyButton.text = "저장"
                sqlitedb.execSQL("INSERT INTO myDiary VALUES(" + '"' + fileName + '"' + ", " +
                        '"' + str + '"' + ");")
            }
            // 달력 일기 -정상작동
            else if (dailyButton.text == "저장") {
                sqlitedb.execSQL("INSERT INTO myDiary VALUES(" + '"' + fileName + '"' + ", " +
                        '"' + str + '"' + ");")
                dailyButton.text = "수정"
                Toast.makeText(applicationContext, fileName + "이 저장됨", Toast.LENGTH_SHORT).show()
            }
            else{
                sqlitedb.execSQL("UPDATE myDiary SET content = \"$str\"")
                sqlitedb.close()
                Toast.makeText(applicationContext, fileName + "이 저장됨", Toast.LENGTH_SHORT).show()
            }
        }
        doneButton.setOnClickListener{
            dailyButton.visibility=View.INVISIBLE
            daydelButton.visibility=View.INVISIBLE
            doneButton.visibility=View.INVISIBLE
        }
        daydelButton.setOnClickListener{
            dailyText.visibility=View.VISIBLE
            dailyButton.visibility=View.VISIBLE
            daydelButton.visibility=View.VISIBLE
            doneButton.visibility=View.VISIBLE
            dailyText.setText("")
            sqlitedb= mentalDB.writableDatabase
            sqlitedb.execSQL("DELETE FROM myDiary WHERE diaryDate='"+fileName+
                    "';")
            Toast.makeText(applicationContext, fileName + "이 삭제됨", Toast.LENGTH_SHORT).show()
            sqlitedb.close()
        }
    }

    //db정의
    inner class myDbHelper(context: Context?) : SQLiteOpenHelper(context, "mentalDB", null, 1) {
        override fun onCreate(db: SQLiteDatabase?) {
            try {
                db!!.execSQL("CREATE TABLE myDiary (diaryDate char(10) primary key, content varchar(500));")
            } catch (e: Exception) {
                println(e)
            }
        }
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS myDiary")
            onCreate(db)
        }
    }
    fun readDiary(fName: String): String? {
        var diaryStr: String? = null
        sqlitedb =  mentalDB.readableDatabase
        val cursor = sqlitedb.rawQuery("SELECT * FROM myDiary WHERE diaryDate = \"$fName\";", null)
        while (cursor.moveToNext()) {
            diaryStr = cursor.getString(1)
        }
        if (diaryStr == null) {
            dailyButton.text = "저장"
        } else {
            dailyButton.text = "수정"
        }
        return diaryStr
    }

    //메뉴바 추가
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    //메뉴바 이동
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.main->{
                val mainIntent= Intent(this,MainActivity::class.java)
                startActivity(mainIntent)
            }
            R.id.sport->{
                val sportIntent=Intent(this,HealthListActivity::class.java)
                startActivity(sportIntent)
            }
            R.id.meal->{
                val mealIntent=Intent(this,MealWriteActivity::class.java)
                startActivity(mealIntent)
            }
            R.id.water->{
                val waterIntent=Intent(this,WaterCheck::class.java)
                startActivity(waterIntent)
            }
            R.id.mental->{
                val mentalIntent=Intent(this,MentalDailyActivity::class.java)
                startActivity(mentalIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
