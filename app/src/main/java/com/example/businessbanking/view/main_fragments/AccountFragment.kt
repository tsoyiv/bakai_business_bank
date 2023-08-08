package com.example.businessbanking.view.main_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.businessbanking.R
import com.example.businessbanking.databinding.FragmentAccountBinding
import com.example.businessbanking.models.RequestModel
import com.example.businessbanking.utils.ItemAdapter

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private val items = mutableListOf<RequestModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRV()
    }

    private fun setupRV() {
        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ItemAdapter(items)
        recyclerView.adapter = adapter
    }
}