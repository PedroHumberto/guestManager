package com.example.guest_manager.repository

import android.content.ContentValues
import android.content.Context
import com.example.guest_manager.model.GuestModel

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
    fun insert(guest: GuestModel): Boolean {
        try {
            val db = guestDataBase.writableDatabase

            //como não aceita true ou false, retorna 1 ou 0.
            val presence = if(guest.presence) 1 else 0

            val values = ContentValues()
            values.put("name", guest.name)
            values.put("presence", presence)
            db.insert("Guest", null, values)
            return true

        }catch (e: Exception){
            return false
        }
    }


}