package com.example.adminswadseva

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminswadseva.adapter.DeliveryAdapter
import com.example.adminswadseva.databinding.ActivityOutForDeliveryBinding

class OutForDeliveryActivity : AppCompatActivity() {
    private val binding: ActivityOutForDeliveryBinding by lazy {
        ActivityOutForDeliveryBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        binding.backButton.setOnClickListener {
            finish()
        }

        val customerName = arrayListOf(
            "Honey",
            "Sunidhi",
            "Suhani"
        )
        val moneyStatus = arrayListOf(
            "received",
            "notReceived",
            "Pending"
        )
        val adapter = DeliveryAdapter(customerName,moneyStatus)
        binding.deliveryRecyclerView.adapter = adapter
        binding.deliveryRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}