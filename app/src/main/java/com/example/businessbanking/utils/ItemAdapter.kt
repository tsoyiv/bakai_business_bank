package com.example.businessbanking.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.businessbanking.R
import com.example.businessbanking.models.Account
import java.text.SimpleDateFormat
import java.util.*

class AccountAdapter(private val accounts: List<Account>) :
    RecyclerView.Adapter<AccountAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val account = accounts[position]
        holder.bind(account)
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(account: Account) {
            itemView.findViewById<TextView>(R.id.txt_typeOfAccount).text = "Расчетные счета:"
            itemView.findViewById<TextView>(R.id.txt_currencyUSD).text = account.currency.currencyID
            itemView.findViewById<TextView>(R.id.txt_nameAccountUSD).text =
                "Банковские счета юридических лиц"
            itemView.findViewById<TextView>(R.id.txt_usdNumberAccount).text = account.accountNo
            itemView.findViewById<TextView>(R.id.txt_amountMoneyUsd).text =
                account.availableBalance.toString()

            itemView.findViewById<TextView>(R.id.txt_currencyKgs).text = "KGS"
            itemView.findViewById<TextView>(R.id.txt_nameAccountKGS).text =
                account.currency.currencyName
            itemView.findViewById<TextView>(R.id.txt_kgsNumberAccount).text = account.accountNo
            itemView.findViewById<TextView>(R.id.txt_amountMoneyKgs).text =
                account.availableBalance.toString()

            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

            val formattedOpenDate = dateFormat.parse(account.openDate.toString())?.let { formattedDate ->
                SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(formattedDate)
            }
            itemView.findViewById<TextView>(R.id.txt_openDate).text = formattedOpenDate

            val formattedEndDate = dateFormat.parse(account.endDate.toString())?.let { formattedDate ->
                SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(formattedDate)
            }
            itemView.findViewById<TextView>(R.id.txt_endDate).text = formattedEndDate
        }
    }
}
