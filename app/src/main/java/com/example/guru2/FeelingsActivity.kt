package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class FeelingsActivity : AppCompatActivity() {
    //변수 선언
    lateinit var vhapButton: Button
    lateinit var hapButton: Button
    lateinit var soButton: Button
    lateinit var sadButton: Button
    lateinit var unhapButton: Button
    lateinit var surpriseButton:Button
    lateinit var refreshButton: Button
    lateinit var feelingsTextview:TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feelings)
       //버튼 연결
        vhapButton=findViewById(R.id.vhapButton)
        hapButton=findViewById(R.id.hapButton)
        soButton=findViewById(R.id.soButton)
        sadButton=findViewById(R.id.sadButton)
        unhapButton=findViewById(R.id.unhapButton)
        surpriseButton=findViewById(R.id.surpriseButton)
        refreshButton=findViewById(R.id.refreshButton)
       feelingsTextview=findViewById(R.id.feelingsTextview)


        //화면 이동(감정-> 명상창으로)
        val secondIntent= Intent(this,MeditateActivity::class.java)
        refreshButton.setOnClickListener {  // 버튼 클릭시 할 행동
            startActivity(secondIntent)//화면 전환
        }


        //toast메세지 띄우기+ 텍스트 메시지 띄우기
         vhapButton.setOnClickListener{
             Toast.makeText(applicationContext,"오늘하루 마음을 비우시겠습니까?", Toast.LENGTH_SHORT).show()
             feelingsTextview.text="내일 하루도 오늘과 같길"
         }
        hapButton.setOnClickListener{
            Toast.makeText(applicationContext,"오늘하루 마음을 비우시겠습니까?", Toast.LENGTH_SHORT).show()
            feelingsTextview.text="내일도 좋은 하루 보내길"
        }
        surpriseButton.setOnClickListener {
            Toast.makeText(applicationContext,"오늘하루 마음을 비우시겠습니까?", Toast.LENGTH_SHORT).show()
            feelingsTextview.text="내일 하루는 안정되길"
        }
        soButton.setOnClickListener{
            Toast.makeText(applicationContext,"오늘하루 마음을 비우시겠습니까?", Toast.LENGTH_SHORT).show()
            feelingsTextview.text="내일은 즐거운 일 가득하길"
        }
        sadButton.setOnClickListener{
            Toast.makeText(applicationContext,"내일 하루는 조금 쉬어가길", Toast.LENGTH_SHORT).show()
            feelingsTextview.text="내일 하루는 조금 쉬어가길"
        }
        unhapButton.setOnClickListener{
            Toast.makeText(applicationContext,"오늘하루 마음을 비우시겠습니까?", Toast.LENGTH_SHORT).show()
            feelingsTextview.text="내일 하루는 편안하길"
        }

    }

    //메뉴바 추가
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    //메뉴바 이동 하기
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.main->{
                val intent:Intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            R.id.mental->{
                val MentalIntent=Intent(this,DayActivity::class.java)
                startActivity((MentalIntent))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}