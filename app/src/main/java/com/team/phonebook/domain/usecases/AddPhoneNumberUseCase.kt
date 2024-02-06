package com.team.phonebook.domain.usecases

import com.team.phonebook.domain.PhoneNumber
import com.team.phonebook.domain.PhoneNumberRepository

class AddPhoneNumberUseCase(private val phoneNumberRepository: PhoneNumberRepository) {

    fun addNumberPhone(phoneNumber: PhoneNumber) {
        phoneNumberRepository.addPhoneNumber(phoneNumber)
    }
}