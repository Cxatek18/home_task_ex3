package com.team.phonebook.domain

data class PhoneNumber(
    val name: String,
    val surname: String,
    val numberPhone: String,
    var isDelete: Boolean,
    var id: Int = UNDEFINED_ID
) {
    companion object{
        const val UNDEFINED_ID = -1
    }
}
