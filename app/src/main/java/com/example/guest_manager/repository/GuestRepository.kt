package com.example.guest_manager.repository

import android.content.Context

class GuestRepository private constructor(context: Context){

    private val guestDataBase = GuestDataBase(context)

    //Singleton -> Controlar o numero de instancia de uma classe
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            //impede com que o repositorio seja inicializado varias vezes
            if(!Companion::repository.isInitialized){
                repository = GuestRepository(context)
            }
            return repository
        }
    }
    fun save() {


    }

}