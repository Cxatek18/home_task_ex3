package com.team.phonebook.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.team.phonebook.domain.PhoneNumber

class PhoneNumberDiffCallback: DiffUtil.ItemCallback<PhoneNumber>() {

    override fun areItemsTheSame(oldItem: PhoneNumber, newItem: PhoneNumber): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PhoneNumber, newItem: PhoneNumber): Boolean {
        return oldItem == newItem
    }
}