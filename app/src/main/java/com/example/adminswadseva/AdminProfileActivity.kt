package com.example.adminswadseva

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.example.adminswadseva.databinding.ActivityAdminProfileBinding

class AdminProfileActivity : AppCompatActivity() {
    private val binding: ActivityAdminProfileBinding by lazy {
        ActivityAdminProfileBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.backButton.setOnClickListener {
            finish()
        }

        binding.name.isEnabled=false
        binding.email.isEnabled=false
        binding.address.isEnabled=false
        binding.phone.isEnabled=false
        binding.password.isEnabled=false

        binding.linearLayout.isInvisible=true
        binding.linearLayout2.isInvisible=true
        binding.linearLayout3.isInvisible=true
        binding.linearLayout4.isInvisible=true
        binding.linearLayout5.isInvisible=true

        var isEnable = false
        var isVisible = true
        binding.editbutton.setOnClickListener {
            isEnable =! isEnable
            binding.name.isEnabled=isEnable
            binding.email.isEnabled=isEnable
            binding.address.isEnabled=isEnable
            binding.phone.isEnabled=isEnable
            binding.password.isEnabled=isEnable

            isVisible =! isVisible

            binding.linearLayout.isInvisible=isVisible
            binding.linearLayout2.isInvisible=isVisible
            binding.linearLayout3.isInvisible=isVisible
            binding.linearLayout4.isInvisible=isVisible
            binding.linearLayout5.isInvisible=isVisible

            if(isEnable) {
                binding.name.requestFocus()
            }
        }

    }
}