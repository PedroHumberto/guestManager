package com.example.guest_manager.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.guest_manager.constants.DataBaseConstants

class GuestDataBase(context: Context,)
    : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE "+ DataBaseConstants.GUEST.TABLE_NAME + "("+
                DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, " +
                DataBaseConstants.GUEST.COLUMNS.NAME + " text, " +
                DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer, " +
                DataBaseConstants.GUEST.COLUMNS.PHONE + " integer);")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}