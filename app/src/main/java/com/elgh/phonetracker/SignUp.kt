package com.elgh.phonetracker

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.type.Date
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.text.SimpleDateFormat

class SignUp : AppCompatActivity() {


    private val mAuth : FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val firestoreInstance: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    private val currentUserDocRef: DocumentReference
    get() = firestoreInstance.document("Users/${mAuth.currentUser?.uid.toString()}")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btRegister.setOnClickListener {
            val email = edEmail.text.toString()
            val password = edPass.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()) {
                progressSignUp.visibility = View.VISIBLE
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

                    val newUser = User(email,"","")
                    currentUserDocRef.set(newUser) // Ajouter l'email de l'utilisateur dans le Firestore

                    if(it.isSuccessful) {
                        Toast.makeText(applicationContext, "Successful Registration", Toast.LENGTH_LONG).show()
                    progressSignUp.visibility = View.GONE
                        }
                    else
                        Toast.makeText(applicationContext, it.exception.toString(), Toast.LENGTH_LONG).show()

                }
            }
             else
                Toast.makeText(applicationContext, "Please enter email and password", Toast.LENGTH_LONG).show()


        }
    }

}


