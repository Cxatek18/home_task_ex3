package com.team.phonebook.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.team.phonebook.data.PhoneNumberRepositoryImpl
import com.team.phonebook.domain.PhoneNumber
import com.team.phonebook.domain.usecases.AddPhoneNumberUseCase

class AddPhoneNumberViewModel : ViewModel() {

    private val repository = PhoneNumberRepositoryImpl
    private val addPhoneNumberUseCase = AddPhoneNumberUseCase(repository)

    private var _isAddNewPhoneNumber = MutableLiveData<Boolean>()
    val isAddNewPhoneNumber: LiveData<Boolean>
        get() = _isAddNewPhoneNumber


    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun addPhoneNumber(name: String, surname: String, phone: String) {
        try {
            val phoneNumber = PhoneNumber(
                name,
                surname,
                phone,
                false
            )
            addPhoneNumberUseCase.addNumberPhone(phoneNumber)
            _isAddNewPhoneNumber.value = true
        } catch (e: Exception) {
            _error.value = TEXT_ERROR
        }
    }

    companion object {
        private const val TEXT_ERROR = "Произошла ошибка при создании контакта"
    }
}