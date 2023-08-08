package com.example.businessbanking.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.businessbanking.MainActivity
import com.example.businessbanking.R
import com.example.businessbanking.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).hide()


        navigation()
    }

    private fun navigation() {

        val instructionLink = "https://ib.bakai.kg/Content/files/instructions.pdf"

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_otpFragment)
        }

        binding.btnCheckInstrLogin.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(instructionLink))
            startActivity(intent)
        }
    }
}