package com.example.guest_manager.constants

class DataBaseConstants private constructor(){

    object GUEST{
        const val ID = "guestId"
        const val TABLE_NAME = "Guest"

        object COLUMNS{
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }

    }
}