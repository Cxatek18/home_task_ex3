package com.team.phonebook.domain.usecases

import com.team.phonebook.domain.PhoneNumber
import com.team.phonebook.domain.PhoneNumberRepository

class EditIsDeletePhoneNumberUseCase(private val phoneNumberRepository: PhoneNumberRepository) {

    fun editIsDeletePhoneNumber(phoneNumberId: Int){
        phoneNumberRepository.editIsDeletePhoneNumber(phoneNumberId)
    }
}