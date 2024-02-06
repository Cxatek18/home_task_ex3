package com.team.phonebook.domain.usecases

import com.team.phonebook.domain.PhoneNumber
import com.team.phonebook.domain.PhoneNumberRepository

class GetPhoneNumberUseCase(private val phoneNumberRepository: PhoneNumberRepository) {

    fun getPhoneNumber(phoneNumberId: Int): PhoneNumber {
        return phoneNumberRepository.getPhoneNumber(phoneNumberId)
    }
}