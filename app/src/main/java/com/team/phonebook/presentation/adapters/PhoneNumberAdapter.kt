package com.team.phonebook.presentation.adapters

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.team.phonebook.databinding.PhoneNumberItemBinding
import com.team.phonebook.domain.PhoneNumber

class PhoneNumberAdapter : ListAdapter<PhoneNumber, PhoneNumberViewHolder>(
    PhoneNumberDiffCallback()
) {

    var onPhoneNumberLongClick: ((Int) -> Unit)? = null
    var setMarkCheckBox: ((Int) -> Unit)? = null
    var isClickMenuOptionsRemovePhoneNumber: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneNumberViewHolder {
        val binding = PhoneNumberItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PhoneNumberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhoneNumberViewHolder, position: Int) {
        val phoneNumber = getItem(position)
        with(holder.binding) {
            with(phoneNumber) {
                tvName.text = phoneNumber.name
                tvSurname.text = phoneNumber.surname
                tvNumberPhone.text = phoneNumber.numberPhone
                holder.itemView.setOnLongClickListener {
                    onPhoneNumberLongClick?.invoke(id)
                    true
                }
                checkboxIsDeleted.isChecked = phoneNumber.isDelete
                if (isClickMenuOptionsRemovePhoneNumber) {
                    checkboxIsDeleted.visibility = VISIBLE
                    checkboxIsDeleted.setOnClickListener {
                        setMarkCheckBox?.invoke(id)
                    }
                } else {
                    checkboxIsDeleted.visibility = GONE
                    checkboxIsDeleted.isChecked = false
                }
            }
        }
    }
}