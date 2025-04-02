package com.example.adminswadseva

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminswadseva.adapter.DeliveryAdapter
import com.example.adminswadseva.adapter.PendingOrderAdapter
import com.example.adminswadseva.databinding.ActivityPendingOrderBinding
import com.example.adminswadseva.databinding.PendingOrdersItemBinding

class PendingOrderActivity : AppCompatActivity() {
    private val binding: ActivityPendingOrderBinding by lazy {
        ActivityPendingOrderBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.backButton.setOnClickListener {
            finish()
        }

        val orderedCustomerName = arrayListOf(
            "Honey",
            "Sunidhi",
            "Suhani",
        )
        val orderedQuantity = arrayListOf(
            "8",
            "5",
            "3",
        )
        val orderedFoodImage = arrayListOf(R.drawable.momo,R.drawable.momo,R.drawable.momo)
        val adapter = PendingOrderAdapter(orderedCustomerName, orderedQuantity, orderedFoodImage, this
        )
        binding.pendingOrderRecyclerVIew.adapter = adapter
        binding.pendingOrderRecyclerVIew.layoutManager = LinearLayoutManager(this)


    }
}