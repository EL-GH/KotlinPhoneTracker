package com.elgh.phonetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        btLogIn.setOnClickListener {
            val email = edEmailIn.text.toString()
            val pass = edPassIn.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()) {

            mAuth?.signInWithEmailAndPassword(email,pass)?.addOnCompleteListener{
                if(it.isSuccessful) {
                    val intentToLocation = Intent(this, UserLocation::class.java)
                    startActivity(intentToLocation)
                    }
                }
            }
            Toast.makeText(applicationContext, " Please enter correct email and password", Toast.LENGTH_LONG).show()

        }


    }
}