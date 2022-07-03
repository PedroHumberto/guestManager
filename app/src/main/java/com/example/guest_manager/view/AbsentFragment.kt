package com.example.guest_manager.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guest_manager.constants.DataBaseConstants
import com.example.guest_manager.databinding.FragmentAbsentBinding
import com.example.guest_manager.view.adapter.GuestsAdapter
import com.example.guest_manager.view.listener.OnGuestListener
import com.example.guest_manager.viewmodel.AbsentViewModel
import com.example.guest_manager.viewmodel.AllGuestsViewModel


class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AbsentViewModel
    private val adapter = GuestsAdapter()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(AbsentViewModel::class.java)

        _binding = FragmentAbsentBinding.inflate(inflater, container, false)


        //Layout
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        //Adapter - Conecta com Layout
        binding.recyclerAllGuests.adapter = adapter

        //Implementar a interface e membros
        val listener = object : OnGuestListener {
            override fun onUpdate(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)


                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAbsent()
            }

            override fun onCall(phone: String) {
                val callIntent: Intent = Uri.parse("tel:${phone.toString()}").let { phoneNum ->
                    Intent(Intent.ACTION_DIAL, phoneNum)
                }
                startActivity(callIntent)
            }

        }




        adapter.attachListener(listener)


        viewModel.getAbsent()

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}