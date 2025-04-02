package com.example.adminswadseva

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.adminswadseva.databinding.ActivityAddItemBinding
import com.example.adminswadseva.model.AllMenu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class AddItemActivity : AppCompatActivity() {
    // Food Item Details
    private lateinit var foodName: String
    private lateinit var foodPrice: String
    private lateinit var foodDescription: String
    private lateinit var foodIngredient: String
    private var foodImageUri: Uri? = null

    //Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    private val binding: ActivityAddItemBinding by lazy {
        ActivityAddItemBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //initialize firebase
        auth = FirebaseAuth.getInstance()
        //initialize Firebase database instance
        database = FirebaseDatabase.getInstance()

        binding.AddItemButton.setOnClickListener {
            //get data from fields
            foodName = binding.enterFoodName.text.toString().trim()
            foodPrice = binding.enterFoodPrice.text.toString().trim()
            foodDescription = binding.description.text.toString().trim()
            foodIngredient = binding.igredient.text.toString().trim()

            if(!(foodName.isBlank() ||
                foodPrice.isBlank() ||
                foodDescription.isBlank() ||
                foodIngredient.isBlank())) {

                uploadData()
                Toast.makeText(this, "Item Add Successfully", Toast.LENGTH_SHORT).show()
                finish()
            }else {
                Toast.makeText(this, "Fill all the details", Toast.LENGTH_SHORT).show()
            }
        }

        binding.selectImage.setOnClickListener {
            pickImage.launch("image/*")
        }


        binding.backButton.setOnClickListener {
            finish()
        }

    }

    private fun uploadData() {
        //get a reference to the "menu" node in the database
        val menuRef = database.getReference("menu")

        // Genrate a unique key for the new menu item
        val newItemkey = menuRef.push().key

        if(foodImageUri != null) {
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("menu_images/${newItemkey}.jpg")
            val uploadTask = imageRef.putFile(foodImageUri!!)

            uploadTask.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener {
                    downloadUrl->
                    //create a new menu item
                    val newItem = AllMenu(
                        foodName = foodName,
                        foodPrice = foodPrice,
                        foodDescription = foodDescription,
                        foodIngredient = foodIngredient,
                        foodImage = downloadUrl.toString()
                    )
                    newItemkey?.let {
                        key->
                        menuRef.child(key).setValue(newItem).addOnSuccessListener {
                            Toast.makeText(this, "Data Uploaded Successfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                            .addOnFailureListener {
                                Toast.makeText(this, "Data Upload Failed", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    }

                }

            }
                .addOnFailureListener {
                    Toast.makeText(this, "Image Upload Failed", Toast.LENGTH_SHORT)
                        .show()
                }
        }
        else {
                Toast.makeText(this, "Please select an Image", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) {uri ->
        if (uri != null) {
            binding.selectedImage.setImageURI(uri)
            foodImageUri=uri
        }

    }
}