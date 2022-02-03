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

// 메인 창

class MainActivity : AppCompatActivity() {

    var auth: FirebaseAuth? = null
    private lateinit var googleSignInClient: GoogleSignInClient
    lateinit var mealButton: Button

    lateinit var waterButton: Button

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


        mealButton = findViewById(R.id.mealButton)

        mealButton.setOnClickListener {
            val intent = Intent(this, MealWriteActivity::class.java)
            startActivity(intent)
        }

        //음수량 수정 화면
         val waterButton = findViewById<Button>(R.id.waterButton)

        //버튼 클릭시 할 행동
        waterButton.setOnClickListener {
            var intent = Intent(this, WaterEdit::class.java) //인텐트 생성
            startActivity(intent)
        }
    }
}