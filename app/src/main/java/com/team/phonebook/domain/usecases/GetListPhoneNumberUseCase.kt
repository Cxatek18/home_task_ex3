package com.team.phonebook.domain.usecases

import androidx.lifecycle.LiveData
import com.team.phonebook.domain.PhoneNumber
import com.team.phonebook.domain.PhoneNumberRepository

class GetListPhoneNumberUseCase(private val phoneNumberRepository: PhoneNumberRepository) {

    fun getListPhoneNumber(): LiveData<List<PhoneNumber>> {
        return phoneNumberRepository.getListPhoneNumber()
    }
}