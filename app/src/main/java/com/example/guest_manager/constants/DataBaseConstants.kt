package com.example.guest_manager.constants

class DataBaseConstants private constructor(){

    object GUEST{
        const val ID = "guestId"
        const val TABLE_NAME = "Guest"
        const val PHONE = "phone"

        object COLUMNS{
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
            const val PHONE = "phone"
        }

    }
}