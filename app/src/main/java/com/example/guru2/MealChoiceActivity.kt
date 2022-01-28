package com.example.guru2

import android.content.DialogInterface
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

class MealChoiceActivity : AppCompatActivity() {

    lateinit var MealdbManager: MealDBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var layout: LinearLayout
    lateinit var calChoiceButton: Button
    lateinit var calendarChoice: CalendarView
    lateinit var scrollMeal: ScrollView
    lateinit var tvChoice: TextView

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

        calendarChoice.setOnDateChangeListener { view, year, month, date ->
            selectYear = year
            selectMonth = month + 1
            selectDate = date
            var alarm: String =
                selectYear.toString() + "년 " + selectMonth.toString() + "월 " + selectDate.toString() + "일 " + "기록을 확인합니다."
            calChoiceButton.setOnClickListener {
                Toast.makeText(applicationContext, alarm, Toast.LENGTH_SHORT).show()
                calChoiceButton.visibility = View.GONE
                calendarChoice.visibility = View.GONE
                tvChoice.visibility = View.GONE
                scrollMeal.visibility = ScrollView.VISIBLE
                layout.visibility = LinearLayout.VISIBLE

                var cursor: Cursor
                cursor = sqlitedb.rawQuery(
                    "SELECT * FROM meal WHERE year = " + selectYear + " AND month = " + selectMonth + " AND date = " + selectDate + ";", null)

                var num: Int = 0
                while (cursor.moveToNext()) {

                    var str_hour = cursor.getString(cursor.getColumnIndex("hour")).toString()
                    var str_minute = cursor.getString(cursor.getColumnIndex("minute")).toString()
                    var str_food = cursor.getString(cursor.getColumnIndex("food")).toString()
                    var str_kcal = cursor.getString(cursor.getColumnIndex("kcal")).toString()

                    var layout_item: LinearLayout = LinearLayout(this)
                    layout_item.orientation = LinearLayout.VERTICAL
                    layout_item.id = num

                    var tvTime: TextView = TextView(this)
                    tvTime.text = str_hour + "시" + " " + str_minute + "분"
                    tvTime.textSize = 25F
                    tvTime.setBackgroundColor(Color.CYAN)
                    tvTime.setTextColor(Color.WHITE)
                    layout_item.addView(tvTime)

                    var tvFood: TextView = TextView(this)
                    tvFood.text = "먹은 음식: " + str_food
                    tvFood.setTextColor(Color.BLACK)
                    layout_item.addView(tvFood)

                    var tvKcal: TextView = TextView(this)
                    tvKcal.text = "음식 칼로리: " + str_kcal + "Kcal"
                    tvKcal.setTextColor(Color.BLACK)
                    layout_item.addView(tvKcal)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
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
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.mental -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.meal -> {
                val intent = Intent(this, MealWriteActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}