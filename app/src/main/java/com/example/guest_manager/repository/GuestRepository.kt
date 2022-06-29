package com.example.guest_manager.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.guest_manager.constants.DataBaseConstants
import com.example.guest_manager.model.GuestModel

class GuestRepository private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)

    //Singleton -> Controlar o numero de instancia de uma classe
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            //impede com que o repositorio seja inicializado varias vezes
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest: GuestModel): Boolean {
        try {
            val db = guestDataBase.writableDatabase

            //como não aceita true ou false, retorna 1 ou 0.
            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            return true

        } catch (e: Exception) {
            return false
        }
    }

    fun update(guest: GuestModel): Boolean {
        try {
            val db = guestDataBase.writableDatabase
            //preenchimento de values
            val values = ContentValues()
            val presence = if (guest.presence) 1 else 0
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            val selection = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun delete(guestId: Int): Boolean {
        try {
            val db = guestDataBase.writableDatabase

            //criterio de seleção
            val selection = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val args = arrayOf(guestId.toString())

            //metodo de delete
            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            return true

        } catch (e: Exception) {
            return false
        }
    }

    @SuppressLint("Range")
    fun getAll(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase
            val tableName = DataBaseConstants.GUEST.TABLE_NAME

            val columnId = DataBaseConstants.GUEST.COLUMNS.ID
            val columnName = DataBaseConstants.GUEST.COLUMNS.NAME
            val columnPresence = DataBaseConstants.GUEST.COLUMNS.PRESENCE

            val projection = arrayOf(
                columnId,
                columnName,
                columnPresence
            )
            val cursor = db.query(
                tableName,
                projection, null, null, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    //INDEX da coluna 0 = id,   1 = name,  2 = presence
                    val id = cursor.getInt(cursor.getColumnIndex(columnId))
                    val name = cursor.getString(cursor.getColumnIndex(columnName))
                    val presence = cursor.getInt(cursor.getColumnIndex(columnPresence))

                    list.add(GuestModel(id, name, presence == 1))

                }
            }
            cursor.close()

        }catch (e: Exception){
            return list
        }
        return list

    }

    @SuppressLint("Range")
    fun get(id: Int): GuestModel? {
        var guest: GuestModel? = null

        try {
            val db = guestDataBase.readableDatabase
            val tableName = DataBaseConstants.GUEST.TABLE_NAME

            val columnId = DataBaseConstants.GUEST.COLUMNS.ID
            val columnName = DataBaseConstants.GUEST.COLUMNS.NAME
            val columnPresence = DataBaseConstants.GUEST.COLUMNS.PRESENCE

            val projection = arrayOf(
                columnId,
                columnName,
                columnPresence
            )

            val selection = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                tableName,
                projection, selection, args, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    //INDEX da coluna 0 = id,   1 = name,  2 = presence
                    val name = cursor.getString(cursor.getColumnIndex(columnName))
                    val presence = cursor.getInt(cursor.getColumnIndex(columnPresence))

                    guest = GuestModel(id, name, presence == 1)

                }
            }
            cursor.close()

        }catch (e: Exception){
            return guest
        }
        return guest

    }

    @SuppressLint("Range")
    fun getPresent(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase
            val tableName = DataBaseConstants.GUEST.TABLE_NAME

            val columnId = DataBaseConstants.GUEST.COLUMNS.ID
            val columnName = DataBaseConstants.GUEST.COLUMNS.NAME
            val columnPresence = DataBaseConstants.GUEST.COLUMNS.PRESENCE

            val projection = arrayOf(
                columnId,
                columnName,
                columnPresence
            )
            val selection = "$columnPresence = ?"
            val args = arrayOf("1")

            val cursor = db.query(
                tableName,
                projection, selection, args, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    //INDEX da coluna 0 = id,   1 = name,  2 = presence
                    val id = cursor.getInt(cursor.getColumnIndex(columnId))
                    val name = cursor.getString(cursor.getColumnIndex(columnName))
                    val presence = cursor.getInt(cursor.getColumnIndex(columnPresence))

                    list.add(GuestModel(id, name, presence == 1))

                }
            }
            cursor.close()

        }catch (e: Exception){
            return list
        }
        return list

    }

    @SuppressLint("Range")
    fun getAbsent(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase
            val tableName = DataBaseConstants.GUEST.TABLE_NAME

            val columnId = DataBaseConstants.GUEST.COLUMNS.ID
            val columnName = DataBaseConstants.GUEST.COLUMNS.NAME
            val columnPresence = DataBaseConstants.GUEST.COLUMNS.PRESENCE

            val projection = arrayOf(
                columnId,
                columnName,
                columnPresence
            )
            val selection = "$columnPresence = ?"
            val args = arrayOf("0")

            val cursor = db.query(
                tableName,
                projection, selection, args, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    //INDEX da coluna 0 = id,   1 = name,  2 = presence
                    val id = cursor.getInt(cursor.getColumnIndex(columnId))
                    val name = cursor.getString(cursor.getColumnIndex(columnName))
                    val presence = cursor.getInt(cursor.getColumnIndex(columnPresence))

                    list.add(GuestModel(id, name, presence == 1))

                }
            }
            cursor.close()

        }catch (e: Exception){
            return list
        }
        return list

    }
}