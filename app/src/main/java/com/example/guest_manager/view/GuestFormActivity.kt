package com.example.guest_manager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.guest_manager.R
import com.example.guest_manager.constants.DataBaseConstants
import com.example.guest_manager.databinding.ActivityGuestFormBinding
import com.example.guest_manager.model.GuestModel
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

        loadData()

    }

    override fun onClick(v: View) {
        if(v.id == R.id.button){
           val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            val model = GuestModel(0, name, presence)

            viewModel.insert(model)
        }
    }
    private fun loadData(){
        val bundle = intent.extras
        if(bundle != null){
            val guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }
}