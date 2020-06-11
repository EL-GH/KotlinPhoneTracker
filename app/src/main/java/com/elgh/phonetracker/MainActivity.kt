package com.elgh.phonetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mAuth:FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            var intentToSignUp = Intent(this, SignUp::class.java)
            startActivity(intentToSignUp)
        }

        btLogin.setOnClickListener {
            var intentToLogin = Intent(this, Login ::class.java)
            startActivity(intentToLogin)
        }
    }

    override fun onStart(){
        super.onStart()

        if(mAuth?.currentUser == null){
          val intentToLogin = Intent(this, Login::class.java)
          startActivity(intentToLogin)

        }

    }

}