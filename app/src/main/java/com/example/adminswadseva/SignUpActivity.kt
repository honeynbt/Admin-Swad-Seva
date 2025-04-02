package com.example.adminswadseva

import android.accounts.Account
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminswadseva.databinding.ActivitySignUpBinding
import com.example.adminswadseva.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var email : String
    private lateinit var password : String
    private lateinit var userName : String
    private lateinit var nameOfResturent : String
    private lateinit var database : DatabaseReference
    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //initialization
        auth = Firebase.auth
        database = Firebase.database.reference



        binding.createButton.setOnClickListener {
            userName = binding.name.text.toString().trim()
            nameOfResturent = binding.resturentName.text.toString().trim()
            email = binding.emailOrPhone.text.toString().trim()
            password = binding.password.text.toString().trim()

            if(userName.isBlank() || nameOfResturent.isBlank() || email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show()
            }
            else {
                createAccount(email,password)
            }

        }
        binding.haveAccountButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        val locationList = arrayOf("Agra", "Ahmedabad", "Bengaluru", "Bhopal", "Chandigarh", "Chennai", "Coimbatore", "Delhi", "Guwahati", "Hyderabad", "Indore", "Jaipur", "Kanpur", "Kolkata", "Lucknow", "Meerut", "Mumbai", "Nagpur", "Patna", "Pune", "Ranchi", "Surat", "Thiruvananthapuram", "Vadodara", "Vijayawada", "Visakhapatnam")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,locationList)
        val autoCompleteTextView = binding.listOfLocation
        autoCompleteTextView.setAdapter(adapter)

    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {task ->
            if(task.isSuccessful) {
                Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                saveUserData()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this, "Account Creation Failed", Toast.LENGTH_SHORT).show()
                Log.d("Account", "createAccount: Failure", task.exception)
            }
        }
    }
//save data into database
    private fun saveUserData() {
        userName = binding.name.text.toString().trim()
        nameOfResturent = binding.resturentName.text.toString().trim()
        email = binding.emailOrPhone.text.toString().trim()
        password = binding.password.text.toString().trim()
        val user=UserModel(userName,nameOfResturent,email,password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
    //save user data firebase database
        database.child("user").child(userId).setValue(user)

    }
}