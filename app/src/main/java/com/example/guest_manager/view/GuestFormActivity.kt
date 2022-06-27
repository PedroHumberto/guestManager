package com.example.guest_manager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.guest_manager.GuestModel
import com.example.guest_manager.R
import com.example.guest_manager.databinding.ActivityGuestFormBinding
import com.example.guest_manager.viewmodel.GuestFormViewModel

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
           // view.save(GuestModel(10, "Pedro Cardoso", false)
        }
    }
}