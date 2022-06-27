package com.example.guest_manager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.guest_manager.databinding.ActivityGuestFormBinding

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)

        setContentView(binding.root)


        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        val saveButton = binding.button
        saveButton.setOnClickListener(this)
        binding.radioPresent.isChecked = true

    }

    override fun onClick(v: View) {
        if(v.id == R.id.button){

        }
    }
}