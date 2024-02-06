package com.team.phonebook.domain

import androidx.lifecycle.LiveData

interface PhoneNumberRepository {
    fun addPhoneNumber(phoneNumber: PhoneNumber)
    fun deleteListPhoneNumber()
    fun editPhoneNumber(phoneNumber: PhoneNumber)
    fun editIsDeletePhoneNumber(phoneNumberId: Int)
    fun getListPhoneNumber(): LiveData<List<PhoneNumber>>
    fun getPhoneNumber(phoneNumberId: Int): PhoneNumber
    fun editIsNoDeletedPhoneNumber()
}