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
    lateinit var water_ml: EditText //ml 기록
    lateinit var btn_intent: Button // resultbutton과 같은 기록하기 버튼

    //var dateString = ""

//    var selectYear: Int = 0
//    var selectMonth: Int = 0
//    var selectDate: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_edit)

        //UI값 생성
        cal_day = findViewById<Button>(R.id.cal_day)
        cal_view = findViewById<TextView>(R.id.cal_view)
        calendarView = findViewById<CalendarView>(R.id.calendarView)
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


//        calendarView.setOnDateChangeListener { view, year, month, date ->
//            selectYear = year
//            selectMonth = month + 1
//            selectDate = date
//            cal_day.setOnClickListener {
//                if (selectMonth < 10) {
//                    if (date < 10) {
//                        cal_view.text =
//                            selectYear.toString() + "년" + " " + "0" + selectMonth.toString() + "월" + " " + "0" + selectDate.toString() + "일"
//                    } else {
//                        cal_view.text =
//                            selectYear.toString() + "년" + " " + "0" + selectMonth.toString() + "월" + " " + selectDate.toString() + "일"
//                    }
//                } else {
//                    if (date < 10) {
//                        cal_view.text =
//                            selectYear.toString() + "년" + " " + selectMonth.toString() + "월" + " " + "0" + selectDate.toString() + "일"
//                    } else {
//                        cal_view.text =
//                            selectYear.toString() + "년" + " " + selectMonth.toString() + "월" + " " + selectDate.toString() + "일"
//                    }
//                }
//            }
//        }
//        calendarView.setOnDateChangeListener{ view, year, month, dayOfMonth ->
//            water1.visibility = View.VISIBLE
//            water2.visibility = View.VISIBLE
//            water3.visibility = View.VISIBLE
//            water4.visibility = View.VISIBLE
//            water5.visibility = View.VISIBLE
//            water6.visibility = View.VISIBLE
//            water7.visibility = View.VISIBLE
//            water8.visibility = View.VISIBLE
//            // 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.
//            cal_view.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
//            water_ml.setText("") // EditText에 공백값 넣기
//            //checkedDay(year, month, dayOfMonth) // checkedDay 메소드 호출
//        }

//        cal_day.setOnClickListener {
//            val today = GregorianCalendar()
//            val year: Int = today.get(Calendar.YEAR)
//            val month: Int = today.get(Calendar.MONTH)
//            val date: Int = today.get(Calendar.DATE)
//
//            val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
//                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//                    cal_view.setText("${year}년 ${month+1}월 ${dayOfMonth}일")
//                }
//            }, year, month, date)
//            dlg.show()
//        }
//        val c = Calendar.getInstance()
//        val year = c.get(Calendar.YEAR)
//        val month = c.get(Calendar.MONTH)
//        val day = c.get(Calendar.DAY_OF_MONTH)
//
//        cal_day.setOnClickListener {
//            //달력
//            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//                // Display Selected date in TextView
//                cal_view.setText("" + dayOfMonth + " " + month + ", " + year)
//            }, year, month, day)
//            dpd.show()
//        }

//        cal_day.setOnClickListener {
//            val cal = Calendar.getInstance()    //캘린더뷰 만들기
//            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
//                dateString = "${year}년 ${month+1}월 ${dayOfMonth}일"
//                cal_view.text = "날짜/시간 : "+dateString
//            }
//            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
//        }


            //컵 클릭 효과
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
        }

//    private fun checkedDay(cYear: Int, cMmonth: Int, cDay: Int) {
//        fname = "" + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt"
//// 저장할 파일 이름 설정. Ex) 2019-01-20.txt
//        var fis: FileInputStream? = null // FileStream fis 변수 설정
//
//        try {
//            fis = openFileInput(fname) // fname 파일 오픈!!
//
//            val fileData = ByteArray(fis.available()) // fileData에 파이트 형식
////으로 저장
//            fis.read(fileData) // fileData를 읽음
//            fis.close()
//
//            str = String(fileData) // str 변수에 fileData를 저장
//    }

    }


