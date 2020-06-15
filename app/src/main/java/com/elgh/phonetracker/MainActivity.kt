package com.elgh.phonetracker

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user_location.*
import java.util.*
import java.util.jar.Manifest

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