package com.elgh.phonetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.progressLogin
import kotlinx.android.synthetic.main.activity_sign_up.*

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
                progressLogin.visibility = View.VISIBLE
            mAuth?.signInWithEmailAndPassword(email,pass)?.addOnCompleteListener{
                if(it.isSuccessful) {
                    val intentToLocation = Intent(this, UserLocation::class.java)
                    progressLogin.visibility = View.GONE
                    startActivity(intentToLocation)
                    }
                else
                    Toast.makeText(applicationContext, it.exception.toString(), Toast.LENGTH_LONG).show()
                }
            }
            else
                Toast.makeText(applicationContext, " Please enter correct email and password", Toast.LENGTH_LONG).show()

        }


    }
}