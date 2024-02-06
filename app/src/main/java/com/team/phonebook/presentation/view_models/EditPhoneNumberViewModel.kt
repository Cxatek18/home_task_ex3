package com.team.phonebook.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.team.phonebook.data.PhoneNumberRepositoryImpl
import com.team.phonebook.domain.PhoneNumber
import com.team.phonebook.domain.usecases.EditPhoneNumberUseCase
import com.team.phonebook.domain.usecases.GetPhoneNumberUseCase

class EditPhoneNumberViewModel : ViewModel() {

    private val repository = PhoneNumberRepositoryImpl
    private val editPhoneNumberUseCase = EditPhoneNumberUseCase(repository)
    private val getPhoneNumberUseCase = GetPhoneNumberUseCase(repository)

    private var _isEditPhoneNumber = MutableLiveData<Boolean>()
    val isEditPhoneNumber: LiveData<Boolean>
        get() = _isEditPhoneNumber

    private var _oldPhoneNumber = MutableLiveData<PhoneNumber>()
    val oldPhoneNumber: LiveData<PhoneNumber>
        get() = _oldPhoneNumber

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun editPhoneNumber(phoneNumberId: Int, name: String, surname: String, phone: String) {
        try {
            _oldPhoneNumber.value?.let {
                val item = it.copy(name = name, surname = surname, numberPhone = phone)
                editPhoneNumberUseCase.editPhoneNumber(item)
                _isEditPhoneNumber.value = true
            }
        } catch (e: Exception) {
            _error.value = TEXT_ERROR
            _isEditPhoneNumber.value = false
        }
    }

    fun getPhoneNumber(phoneNumberId: Int) {
        try {
            _oldPhoneNumber.value = getPhoneNumberUseCase.getPhoneNumber(phoneNumberId)
        } catch (e: Exception) {
            _error.value = TEXT_ERROR
        }

    }

    companion object {
        private const val TEXT_ERROR = "Произошла ошибка при обновлении контакта"
    }
}