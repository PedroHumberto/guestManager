package com.example.guest_manager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guest_manager.model.GuestModel
import com.example.guest_manager.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guests: LiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = _saveGuest

    fun save(guest: GuestModel){

        if (guest.id == 0){
           _saveGuest.value = repository.insert(guest)
        }else{
            _saveGuest.value = repository.update(guest)
        }


    }

    fun get(id: Int){
        guestModel.value = repository.get(id)
    }

}