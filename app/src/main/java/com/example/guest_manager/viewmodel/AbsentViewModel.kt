package com.example.guest_manager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guest_manager.model.GuestModel
import com.example.guest_manager.repository.GuestRepository

class AbsentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GuestRepository =
        GuestRepository.getInstance(application.applicationContext)

    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAbsent(){
        listAllGuests.value = repository.getAbsent()
    }

    fun delete(id: Int){
        repository.delete(id)
    }
    fun get(id: Int){
        repository.get(id)
    }
}