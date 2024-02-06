package com.team.phonebook.domain.usecases

import com.team.phonebook.domain.PhoneNumberRepository

class EditIsNoDeletedPhoneNumberUseCase(private val phoneNumberRepository: PhoneNumberRepository) {

    fun editIsNoDeletedPhoneNumber() {
        phoneNumberRepository.editIsNoDeletedPhoneNumber()
    }
}