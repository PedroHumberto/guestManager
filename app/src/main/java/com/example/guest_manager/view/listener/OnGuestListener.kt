package com.example.guest_manager.view.listener

interface OnGuestListener {
    fun onUpdate(id: Int)
    fun onDelete(id: Int)
    fun onCall(phone: Int)
}