package com.team.phonebook.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.team.phonebook.data.PhoneNumberRepositoryImpl
import com.team.phonebook.domain.PhoneNumber
import com.team.phonebook.domain.usecases.DeleteListPhoneNumberUseCase
import com.team.phonebook.domain.usecases.EditIsDeletePhoneNumberUseCase
import com.team.phonebook.domain.usecases.EditIsNoDeletedPhoneNumberUseCase
import com.team.phonebook.domain.usecases.GetListPhoneNumberUseCase

class MainViewModel : ViewModel() {

    private val repository = PhoneNumberRepositoryImpl

    private val getListPhoneNumberUseCase = GetListPhoneNumberUseCase(repository)
    private val editIsDeletePhoneNumberUseCase = EditIsDeletePhoneNumberUseCase(repository)
    private val deleteListPhoneNumberUseCase = DeleteListPhoneNumberUseCase(repository)
    private val editIsNoDeletedPhoneNumberUseCase = EditIsNoDeletedPhoneNumberUseCase(repository)

    val listPhoneNumber: LiveData<List<PhoneNumber>>
        get() = getListPhoneNumberUseCase.getListPhoneNumber()

    fun editToDeletePhoneNumber(phoneNumberId: Int) {
        editIsDeletePhoneNumberUseCase.editIsDeletePhoneNumber(phoneNumberId)
    }

    fun deleteListPhoneNumber() {
        deleteListPhoneNumberUseCase.deleteListPhoneNumber()
    }

    fun editIsNoDeletedPhoneNumber() {
        editIsNoDeletedPhoneNumberUseCase.editIsNoDeletedPhoneNumber()
    }
}