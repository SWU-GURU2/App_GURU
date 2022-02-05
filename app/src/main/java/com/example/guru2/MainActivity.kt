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
    lateinit var mentalButton: Button
    lateinit var healthButton: Button //운동버튼

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

        //버튼 클릭-> 해당 화면 이동
        healthButton = findViewById<Button>(R.id.healthButton)
        healthButton.setOnClickListener{
            var healthIntent = Intent(this, HealthListActivity::class.java)
            startActivity(healthIntent)
        }

        mealButton = findViewById(R.id.mealButton)
        mealButton.setOnClickListener {
            val mealIntent = Intent(this, MealWriteActivity::class.java)
            startActivity(mealIntent)
        }
        waterButton = findViewById(R.id.waterButton)
        waterButton.setOnClickListener {
            var waterIntent = Intent(this, WaterEdit::class.java)
            startActivity(waterIntent)
        }
        mentalButton=findViewById(R.id.mentalButton)
        mentalButton.setOnClickListener{
            val mentalIntent=Intent(this,MentalDailyActivity::class.java)
            startActivity(mentalIntent)
        }


    }
}