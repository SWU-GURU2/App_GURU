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
import org.w3c.dom.Text

//식단 기록 보는 화면

class MealChoiceActivity : AppCompatActivity() {
    lateinit var MealdbManager: MealDBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var layout: LinearLayout
    lateinit var calChoiceButton: Button
    lateinit var calendarChoice: CalendarView
    lateinit var scrollMeal: ScrollView
    lateinit var tvChoice: TextView
    lateinit var backChoice: ImageView
    lateinit var tvNotice: TextView
    lateinit var tvNotice2: TextView

    var selectYear: Int = 0
    var selectMonth: Int = 0
    var selectDate: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_choice)

        MealdbManager = MealDBManager(this, "mealDB", null, 1)
        sqlitedb = MealdbManager.readableDatabase

        layout = findViewById(R.id.checkMeal)
        scrollMeal = findViewById(R.id.scrollMeal)
        calChoiceButton = findViewById(R.id.calChoiceButton)
        calendarChoice = findViewById(R.id.calendarChoice)
        tvChoice = findViewById(R.id.tvChoice)
        backChoice = findViewById(R.id.backChoice)
        tvNotice = findViewById(R.id.tvNotice)
        tvNotice2 = findViewById(R.id.tvNotice2)

        // 캘린더뷰 누르고
        calendarChoice.setOnDateChangeListener { view, year, month, date ->
            selectYear = year
            selectMonth = month + 1
            selectDate = date
            var alarm: String =
                selectYear.toString() + "년 " + selectMonth.toString() + "월 " + selectDate.toString() + "일 " + "기록을 확인합니다."
            // 날짜 선택 버튼 누르고
            calChoiceButton.setOnClickListener {
                Toast.makeText(applicationContext, alarm, Toast.LENGTH_SHORT).show()
                calChoiceButton.visibility = View.GONE
                calendarChoice.visibility = View.GONE
                tvChoice.visibility = View.GONE
                scrollMeal.visibility = ScrollView.VISIBLE
                layout.visibility = LinearLayout.VISIBLE
                backChoice.visibility = ImageView.INVISIBLE
                tvNotice.visibility = View.GONE
                tvNotice2.visibility = View.GONE

                // DB에서 해당하는 날짜의 기록을 가져와서 레이아웃에 추가한다

                var cursor: Cursor
                cursor = sqlitedb.rawQuery(
                    "SELECT * FROM meal WHERE year = " + selectYear + " AND month = " + selectMonth + " AND date = " + selectDate + ";",
                    null
                )

                var num: Int = 0
                while (cursor.moveToNext()) {

                    var str_hour = cursor.getString(cursor.getColumnIndex("hour")).toString()
                    var str_minute = cursor.getString(cursor.getColumnIndex("minute")).toString()
                    var str_title = cursor.getString(cursor.getColumnIndex("title")).toString()
                    var str_food: String =
                        cursor.getString(cursor.getColumnIndex("food")).toString()
                    var str_kcal: String =
                        cursor.getString(cursor.getColumnIndex("kcal")).toString()

                    var layout_item: LinearLayout = LinearLayout(this)
                    layout_item.orientation = LinearLayout.VERTICAL
                    layout_item.id = num

                    var tvTitle: TextView = TextView(this)
                    tvTitle.text = str_title
                    tvTitle.textSize = 25F
                    tvTitle.setBackgroundColor(Color.WHITE)
                    tvTitle.setTextColor(Color.BLACK)
                    layout_item.addView(tvTitle)

                    var tvTime: TextView = TextView(this)
                    tvTime.text = "먹은 시간: " + str_hour + "시" + " " + str_minute + "분"
                    tvTime.setTextColor(Color.BLACK)
                    layout_item.addView(tvTime)

                    var tvFood: TextView = TextView(this)
                    tvFood.text = "먹은 음식: " + str_food
                    tvFood.setTextColor(Color.BLACK)
                    layout_item.addView(tvFood)

                    var tvKcal: TextView = TextView(this)
                    tvKcal.text = "음식 칼로리: " + str_kcal + "Kcal"
                    tvKcal.setTextColor(Color.BLACK)
                    layout_item.addView(tvKcal)

                    // 삭제하고 싶은 기록을 누르면 삭제 화면으로 이동
                    layout_item.setOnClickListener {
                        val intent = Intent(this, MealDelete::class.java)
                        intent.putExtra("intent_year", selectYear.toString())
                        intent.putExtra("intent_month", selectMonth.toString())
                        intent.putExtra("intent_date", selectDate.toString())
                        intent.putExtra("intent_hour", str_hour)
                        intent.putExtra("intent_minute", str_minute)
                        intent.putExtra("intent_title", str_title)
                        intent.putExtra("intent_food", str_food)
                        intent.putExtra("intent_kcal", str_kcal)
                        startActivity(intent)
                    }
                    layout.addView(layout_item)
                    num++
                }
                cursor.close()
                sqlitedb.close()
                MealdbManager.close()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    // 메뉴바 이동
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