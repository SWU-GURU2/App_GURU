package com.example.guru2

import android.app.DatePickerDialog
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.media.Image
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.io.FileInputStream
import java.util.*

//음수량 수정 화면
class WaterEdit : AppCompatActivity() {
    lateinit var WaterDBManger: WaterDBManger
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var cal_day: Button //날짜 선택
    lateinit var cal_view: TextView // 선택한 날짜
    lateinit var imageView: ImageView //첫문장 이모티콘
    lateinit var textView: TextView //첫문장
    lateinit var imageView2: ImageView //두번째문장 이모티콘
    lateinit var textView2: TextView //두번째 문장
    lateinit var water1: ImageView //컵(잔)
    lateinit var water2: ImageView
    lateinit var water3: ImageView
    lateinit var water4: ImageView
    lateinit var water5: ImageView
    lateinit var water6: ImageView
    lateinit var water7: ImageView
    lateinit var water8: ImageView
    lateinit var water_ml: EditText //ml 기록
    lateinit var textView3: TextView //ml 문장
    lateinit var textView4: TextView //음수량 - ml 관련안내 문장
    lateinit var resultbutton: Button // 기록하기 버튼

    lateinit var textView6 :TextView //나의 음수량 문장
    lateinit var water_editButton: Button //수정하기 버튼
    lateinit var cups_selected: TextView //잔 체크 수
    lateinit var water_ml_view: TextView //작성한 ml
    lateinit var cup_result: ImageView //음수량결과 잔
    lateinit var face_result: ImageView //음수량결과 표정
    lateinit var textView5:TextView // 음수량 - 잔 관련 안내 문장
    lateinit var water_view: TextView //음수량이 - 해요 문장
    lateinit var water_result: TextView //음수량 결과
    lateinit var savebutton: Button //저장버튼

    var selectYear: Int = 0
    var selectMonth: Int = 0
    var selectDate: Int = 0
    var watercount : Int = 0 //선택한 잔의 갯수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_edit)

        //데이터베이스 연결
        WaterDBManger = WaterDBManger(this, "waterDB", null, 1 )

        //UI값 생성
        cal_day = findViewById<Button>(R.id.cal_day)
        cal_view = findViewById<TextView>(R.id.cal_view)
        water1 = findViewById<ImageView>(R.id.water1)
        water2 = findViewById<ImageView>(R.id.water2)
        water3 = findViewById<ImageView>(R.id.water3)
        water4 = findViewById<ImageView>(R.id.water4)
        water5 = findViewById<ImageView>(R.id.water5)
        water6 = findViewById<ImageView>(R.id.water6)
        water7 = findViewById<ImageView>(R.id.water7)
        water8 = findViewById<ImageView>(R.id.water8)
        water_ml = findViewById<EditText>(R.id.water_ml)
        resultbutton = findViewById<Button>(R.id.resultbutton)
        imageView = findViewById<ImageView>(R.id.imageView)
        textView = findViewById<TextView>(R.id.textView)
        imageView2 = findViewById<ImageView>(R.id.imageView2)
        textView2 = findViewById<TextView>(R.id.textView2)
        textView3 = findViewById<TextView>(R.id.textView3)
        textView4 = findViewById<TextView>(R.id.textView4)

        textView6 = findViewById<TextView>(R.id.textView6)
        water_editButton= findViewById<Button>(R.id.water_editButton)
        cups_selected= findViewById<TextView>(R.id.cups_selected)
        water_ml_view= findViewById<TextView>(R.id.water_ml_view)
        cup_result= findViewById<ImageView>(R.id.cup_result)
        face_result= findViewById<ImageView>(R.id.face_result)
        textView5= findViewById<TextView>(R.id.textView5)
        water_view= findViewById<TextView>(R.id.water_view)
        water_result= findViewById<TextView>(R.id.water_result)

        savebutton = findViewById<Button>(R.id.savebutton)

        //날짜 선택
        cal_day.setOnClickListener {
//            var calendar = Calendar.getInstance()
//            var selectYear = calendar.get(Calendar.YEAR)
//            var selectMonth = calendar.get(Calendar.MONTH)
//            var selectDate = calendar.get(Calendar.DAY_OF_MONTH)
//
//            var listener = DatePickerDialog.OnDateSetListener { datePicker, selectYear, selectMonth, selectDate ->
//                cal_view.text = "${selectYear}년 ${selectMonth + 1}월 ${selectDate}일"
//                Toast.makeText(getApplicationContext(), "음수량 기록을 시작합니다", Toast.LENGTH_LONG).show();
//            }
            var calendar = Calendar.getInstance()
            var selectYear = calendar.get(Calendar.YEAR)
            var selectMonth = calendar.get(Calendar.MONTH)
            var selectDate = calendar.get(Calendar.DAY_OF_MONTH)

            var listener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                selectYear = year
                selectMonth = month + 1
                selectDate = date
                cal_view.text = "${selectYear}년 ${selectMonth + 1}월 ${selectDate}일"
                Toast.makeText(getApplicationContext(), "음수량 기록을 시작합니다", Toast.LENGTH_LONG).show();
            }
            var picker = DatePickerDialog(this, listener, selectYear, selectMonth, selectDate)
            picker.show()

            water1.visibility = View.VISIBLE
            water2.visibility = View.VISIBLE
            water3.visibility = View.VISIBLE
            water4.visibility = View.VISIBLE
            water5.visibility = View.VISIBLE
            water6.visibility = View.VISIBLE
            water7.visibility = View.VISIBLE
            water8.visibility = View.VISIBLE
            water_ml.visibility = View.VISIBLE
            resultbutton.visibility = View.VISIBLE

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

            resultbutton.setOnClickListener {
                var str_watercount: String = watercount.toString()
                var str_waterml: String = water_ml.text.toString()
                cups_selected.text = (watercount.toString() + "잔")
                water_ml_view.text = (str_waterml + "ml")

                when {
                    str_watercount.toInt() <= 3 -> {
                        face_result.setImageResource(R.drawable.sad)
                        cup_result.setImageResource(R.drawable.w_low)
                        water_result.text = "부족"
                    }
                    str_watercount.toInt() <= 6 -> {
                        face_result.setImageResource(R.drawable.smile2)
                        cup_result.setImageResource(R.drawable.w_medium)
                        water_result.text = "적당"
                    }
                    else -> {
                        face_result.setImageResource(R.drawable.happy)
                        cup_result.setImageResource(R.drawable.w_full)
                        water_result.text = "충분"
                    }
                }
                //ml를 입력하지 않았을 경우 메세지 출력
                if (water_ml.text.toString().length == 0) {
                    //Toast.makeText(getApplicationContext(), "ml까지 작성해주세요. 한잔당 200ml입니다", Toast.LENGTH_LONG).show();
                }
                //모든 정보 입력시
                else {
                    Toast.makeText(applicationContext, "기록 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    water1.visibility = View.INVISIBLE
                    water2.visibility = View.INVISIBLE
                    water3.visibility = View.INVISIBLE
                    water4.visibility = View.INVISIBLE
                    water5.visibility = View.INVISIBLE
                    water6.visibility = View.INVISIBLE
                    water7.visibility = View.INVISIBLE
                    water8.visibility = View.INVISIBLE
                    water_ml.visibility = View.INVISIBLE
                    resultbutton.visibility = View.INVISIBLE
                    cal_day.visibility = View.INVISIBLE
                    textView.visibility = View.INVISIBLE
                    textView2.visibility = View.INVISIBLE
                    textView3.visibility = View.INVISIBLE
                    textView4.visibility = View.INVISIBLE
                    imageView2.visibility = View.INVISIBLE

                    textView6.visibility = View.VISIBLE
                    water_editButton.visibility = View.VISIBLE
                    cups_selected.visibility = View.VISIBLE
                    water_ml_view.visibility = View.VISIBLE
                    cup_result.visibility = View.VISIBLE
                    face_result.visibility = View.VISIBLE
                    textView5.visibility = View.VISIBLE
                    water_view.visibility = View.VISIBLE
                    water_result.visibility = View.VISIBLE
                    savebutton.visibility = View.VISIBLE

                }
            }
            water_editButton.setOnClickListener{
                water1.visibility = View.VISIBLE
                water2.visibility = View.VISIBLE
                water3.visibility = View.VISIBLE
                water4.visibility = View.VISIBLE
                water5.visibility = View.VISIBLE
                water6.visibility = View.VISIBLE
                water7.visibility = View.VISIBLE
                water8.visibility = View.VISIBLE
                water_ml.visibility = View.VISIBLE
                resultbutton.visibility = View.VISIBLE
                cal_day.visibility = View.VISIBLE
                textView.visibility = View.VISIBLE
                textView2.visibility = View.VISIBLE
                textView3.visibility = View.VISIBLE
                textView4.visibility = View.VISIBLE


                textView6.visibility = View.INVISIBLE
                water_editButton.visibility = View.INVISIBLE
                cups_selected.visibility = View.INVISIBLE
                water_ml_view.visibility = View.INVISIBLE
                cup_result.visibility = View.INVISIBLE
                face_result.visibility = View.INVISIBLE
                textView5.visibility = View.INVISIBLE
                water_view.visibility = View.INVISIBLE
                water_result.visibility = View.INVISIBLE
                savebutton.visibility = View.INVISIBLE
            }
        }
        savebutton.setOnClickListener{
            var str_year: String = selectYear.toString()
            var str_month: String = selectMonth.toString()
            var str_date: String = selectDate.toString()
            var str_waterml: String = water_ml.text.toString()

            sqlitedb = WaterDBManger.writableDatabase
            sqlitedb.execSQL("INSERT INTO waterlist VALUES ("+str_year+","+ str_month+","+ str_date+","+ str_waterml+")")
            sqlitedb.close()

            Toast.makeText(applicationContext, "저장되었습니다.", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, WaterList::class.java)
//            startActivity(intent)
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


