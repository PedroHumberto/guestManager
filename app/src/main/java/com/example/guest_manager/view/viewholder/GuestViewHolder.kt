package com.example.guest_manager.view.viewholder


import androidx.recyclerview.widget.RecyclerView
import com.example.guest_manager.databinding.RowGuestBinding
import com.example.guest_manager.model.GuestModel

//feita a troca para RowGuestBinding para poder usar o padrão View Holder
class GuestViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel){
        //elementos de interface
        bind.textName.text = guest.name
    }
}