package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    var auth: FirebaseAuth? = null
    private lateinit var googleSignInClient: GoogleSignInClient

    lateinit var waterButton: Button
    lateinit var food: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        auth = FirebaseAuth.getInstance()

        val google_sign_out_button = findViewById<Button>(R.id.google_sign_out_button)

        google_sign_out_button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            googleSignInClient?.signOut()
            var logoutIntent = Intent(this, LoginActivity::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(logoutIntent)
        }

        //음수량 수정 화면
         val waterButton = findViewById<Button>(R.id.waterButton)

//        food.setOnClickListener {
//            var intent = Intent(this, MealChoiceActivity::class.java) //인텐트 생성
//            startActivity(intent)
//        }
        //버튼 클릭시 할 행동
        waterButton.setOnClickListener {
            var intent = Intent(this, WaterEdit::class.java) //인텐트 생성
            startActivity(intent)
        }


//        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//            // 1. xml을 이용한 메뉴 만들기
//            menuInflater.inflate(R.menu.option_menu, menu)
//
//            // 2. 코드를 이용한 메뉴 만들기
//            menu?.add(Menu.NONE, Menu.FIRST + 1, Menu.NONE, "코드메뉴1")
//            menu?.add(Menu.NONE, Menu.FIRST + 2, Menu.NONE, "코드메뉴2")
//
//            var sub: Menu? = menu?.addSubMenu("메뉴3")
//            sub?.add(Menu.NONE, Menu.FIRST + 3, Menu.NONE, "코드메뉴3-1")
//            sub?.add(Menu.NONE, Menu.FIRST + 4, Menu.NONE, "코드메뉴3-2")
//
//            // true로 해야 메뉴가 나타난다.
//            return true
//            // false는 안나옴
//            return false
//        }
//
//        override fun onOptionsItemSelected(item: MenuItem): Boolean {
//            // 1. xml을 이용한 메뉴 만들기
//            when (item?.itemId) {
//                R.id.item1
//                -> tv1.text = "1"
//                R.id.Item2_1
//                -> tv1.text = "2-1"
//                R.id.Item2_2
//                -> tv1.text = "2-2"
//                R.id.item3
//                -> tv1.text = "3"
//            }
//
//            // 2. 코드를 이용한 메뉴 만들기
//            when (item?.itemId) {
//                Menu.FIRST + 1
//                -> tv1.text = "1"
//                Menu.FIRST + 2
//                -> tv1.text = "2"
//                Menu.FIRST + 3
//                -> tv1.text = "3-1"
//                Menu.FIRST + 4
//                -> tv1.text = "3-2"
//            }
//
//            return super.onOptionsItemSelected(item)
//        }

    }
}