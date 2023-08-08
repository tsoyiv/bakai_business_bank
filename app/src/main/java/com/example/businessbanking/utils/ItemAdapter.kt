package com.example.businessbanking.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.businessbanking.R
import com.example.businessbanking.models.RequestModel
import kotlinx.android.synthetic.main.custom_item.view.*

class ItemAdapter (private val items: List<RequestModel>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: RequestModel) {
            itemView.txt_userAccount.text = item.userAccount
            itemView.txt_amountMoney.text = item.amountMoney
            itemView.txt_numbOfCards.text = item.numbOfCards
            itemView.amount_liked.text = item.amountLiked
        }
    }
}