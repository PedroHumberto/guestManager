package com.example.guest_manager.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.guest_manager.R
import com.example.guest_manager.constants.DataBaseConstants
import com.example.guest_manager.databinding.ActivityGuestFormBinding
import com.example.guest_manager.model.GuestModel
import com.example.guest_manager.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    private var guestId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)

        setContentView(binding.root)


        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        val saveButton = binding.button
        saveButton.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        observe()
        loadData()

    }

    override fun onClick(v: View) {
        if (v.id == R.id.button) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked
            val phone = binding.editPhone.text.toString()

            val model = GuestModel(guestId, name, presence, phone)

            viewModel.save(model)

        }
    }

    private fun observe() {
        viewModel.guests.observe(this) {
            binding.editName.setText(it.name)
            binding.editPhone.setText(it.phone)

            if (it.presence) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        }

        //observer de atualização de convidados
        viewModel.saveGuest.observe(this) {
            if (it) {
                if (guestId == 0) {
                    Toast.makeText(applicationContext, "Guest Added Successful", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(applicationContext, "Updated Successful", Toast.LENGTH_SHORT)
                        .show()
                }
                finish()
            } else {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }
}