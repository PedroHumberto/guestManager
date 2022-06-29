package com.example.guest_manager.view.viewholder


import android.app.AlertDialog
import android.content.DialogInterface
import androidx.recyclerview.widget.RecyclerView
import com.example.guest_manager.databinding.RowGuestBinding
import com.example.guest_manager.model.GuestModel
import com.example.guest_manager.view.listener.OnGuestListener

//feita a troca para RowGuestBinding para poder usar o padrão View Holder
class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        //elementos de interface
        bind.textName.text = guest.name

        bind.btnDelete.setOnClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Removing Guest")
                .setMessage("You Really Wanna Remove?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes") { dialog, which ->
                    listener.onDelete(guest.id)
                }
                .create()
                .show()
        }

        bind.btnEdit.setOnClickListener{
            listener.onUpdate(guest.id)
        }
    }
}