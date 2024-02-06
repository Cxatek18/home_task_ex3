package com.team.phonebook.domain.usecases

import com.team.phonebook.domain.PhoneNumber
import com.team.phonebook.domain.PhoneNumberRepository

class EditPhoneNumberUseCase(private val phoneNumberRepository: PhoneNumberRepository) {

    fun editPhoneNumber(phoneNumber: PhoneNumber){
        phoneNumberRepository.editPhoneNumber(phoneNumber)
    }
}