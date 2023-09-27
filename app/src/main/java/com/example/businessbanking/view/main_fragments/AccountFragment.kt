package com.example.businessbanking.view.main_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.businessbanking.api.RetrofitInstance
import com.example.businessbanking.databinding.FragmentAccountBinding
import com.example.businessbanking.models.Account
import com.example.businessbanking.models.Currency
import com.example.businessbanking.models.RequestModel
import com.example.businessbanking.models.User
import com.example.businessbanking.presenter.AccountContract
import com.example.businessbanking.presenter.AccountPresenter
import com.example.businessbanking.presenter.AccountRepository
import com.example.businessbanking.utils.AccountAdapter
import com.example.businessbanking.utils.Holder
import kotlinx.coroutines.launch
import java.util.*

class AccountFragment : Fragment(), AccountContract.View {

    private lateinit var binding: FragmentAccountBinding
    private val items = mutableListOf<RequestModel>()
    private lateinit var accountPresenter: AccountContract.Presenter
    private lateinit var accountAdapter: AccountAdapter
    private lateinit var recyclerView: RecyclerView

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
        apiCall()
    }

    private fun apiCall() {
        val api = RetrofitInstance.apiAccount
        val accountRepository = AccountRepository(api)
        accountPresenter = AccountPresenter(this, accountRepository)

        val token: String = Holder.access_token.toString()
            lifecycleScope.launch {
                accountPresenter.fetchAccounts(token)
            }
    }

    private fun setupRV() {
        recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun showAccounts(accounts: List<Account>) {
        accountAdapter = AccountAdapter(accounts)
        recyclerView.adapter = accountAdapter
    }

    override fun showError(error: String) {
        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
    }
}