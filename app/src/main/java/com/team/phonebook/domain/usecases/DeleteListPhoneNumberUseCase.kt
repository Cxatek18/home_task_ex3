package com.team.phonebook.domain.usecases

import com.team.phonebook.domain.PhoneNumber
import com.team.phonebook.domain.PhoneNumberRepository

class DeleteListPhoneNumberUseCase(private val phoneNumberRepository: PhoneNumberRepository) {

    fun deleteListPhoneNumber(){
        phoneNumberRepository.deleteListPhoneNumber()
    }
}