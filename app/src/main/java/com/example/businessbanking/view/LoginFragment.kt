package com.example.businessbanking.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.businessbanking.MainActivity
import com.example.businessbanking.R
import com.example.businessbanking.databinding.FragmentLoginBinding
import com.example.businessbanking.presenter.LoginContract
import com.example.businessbanking.presenter.LoginPresenter

class LoginFragment : Fragment(), LoginContract.View {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var presenter: LoginContract.Presenter
    private lateinit var navController: NavController

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
        authSetup()
    }

    private fun authSetup() {
        navController = findNavController()

        presenter = LoginPresenter(this, navController)
        val localPresenter = presenter
        binding.btnLogin.setOnClickListener {
            val username = binding.editTextLogin.text.toString()
            val password = binding.editTextPassword.text.toString()
            localPresenter.performLogin(username, password)
        }
    }

    override fun onLoginSuccess(token: String) {
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_otpFragment)
        }
    }

    override fun onLoginError(error: String) {
        Toast.makeText(requireContext(), "Error occured", Toast.LENGTH_SHORT).show()
    }

    private fun navigation() {
        val instructionLink = "https://ib.bakai.kg/Content/files/instructions.pdf"

//        binding.btnLogin.setOnClickListener {
//            findNavController().navigate(R.id.action_loginFragment_to_otpFragment)
//        }

        binding.btnCheckInstrLogin.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(instructionLink))
            startActivity(intent)
        }
    }
}