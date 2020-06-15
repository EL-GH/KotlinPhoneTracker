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
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_user_location.*
import java.util.*

class UserLocation : AppCompatActivity() {

    lateinit var lm : LocationManager
    lateinit var loc : Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_location)

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION), 111)

        lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        var ll = object: LocationListener {
            override fun onLocationChanged(location: Location?) {
                reverseGeocode(location)
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onProviderDisabled(provider: String?) {
            }

        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 100.2f, ll)



        btMap.setOnClickListener{
            val intentMap = Intent(this, MapsActivity::class.java)
            startActivity(intentMap)
        }



    }

       private fun reverseGeocode(location: Location?) {
        var gc = Geocoder(this, Locale.getDefault())
        var addresses = gc.getFromLocation(loc.latitude, loc.longitude, 2)
        var address = addresses.get(0)
        textView5.setText("Current Location of Your Device is \n${address.getAddressLine(0)}\n${address.locality}")

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val m = menuInflater
        m.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addTracker -> {
                val intent = Intent(this, User::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}