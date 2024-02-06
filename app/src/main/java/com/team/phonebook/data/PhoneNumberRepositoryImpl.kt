package com.team.phonebook.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.team.phonebook.domain.PhoneNumber
import com.team.phonebook.domain.PhoneNumberRepository
import java.util.TreeSet
import kotlin.random.Random

object PhoneNumberRepositoryImpl : PhoneNumberRepository {

    private var autoIncrementId: Int = 1
    private val phoneNumberList: TreeSet<PhoneNumber> = sortedSetOf<PhoneNumber>(
        { o1, o2 -> o1.id.compareTo(o2.id) }
    )
    private val phoneNumberListLD = MutableLiveData<List<PhoneNumber>>()

    init {
        for (i in 1..100) {
            val randomCodeCity = Random.nextInt(100, 999)
            val randomCodePhone = Random.nextInt(100, 999)
            val randomEnding = Random.nextInt(10, 99)
            val item: PhoneNumber = PhoneNumber(
                "Name $i",
                "Surname $i",
                "+7$randomCodeCity$randomCodePhone$randomEnding$randomEnding",
                false
            )
            addPhoneNumber(item)
        }
    }

    override fun addPhoneNumber(phoneNumber: PhoneNumber) {
        if (phoneNumber.id == PhoneNumber.UNDEFINED_ID) {
            phoneNumber.id = autoIncrementId++
        }
        phoneNumberList.add(phoneNumber)
        updateList()
    }

    override fun deleteListPhoneNumber() {
        phoneNumberList.removeAll { it.isDelete }
        updateList()
    }

    override fun editPhoneNumber(phoneNumber: PhoneNumber) {
        val phoneNumberOld = getPhoneNumber(phoneNumber.id)
        phoneNumberList.remove(phoneNumberOld)
        addPhoneNumber(phoneNumber)
    }

    override fun getPhoneNumber(phoneNumberId: Int): PhoneNumber {
        return phoneNumberList.find { it.id == phoneNumberId }
            ?: throw RuntimeException("Element with id $phoneNumberId not found")
    }

    override fun editIsDeletePhoneNumber(phoneNumberId: Int) {
        val phoneNumber = getPhoneNumber(phoneNumberId)
        phoneNumber.isDelete = !phoneNumber.isDelete
        updateList()
    }

    override fun editIsNoDeletedPhoneNumber() {
        phoneNumberList.forEach {
            it.isDelete = false
        }
        updateList()
    }

    override fun getListPhoneNumber(): LiveData<List<PhoneNumber>> {
        return phoneNumberListLD
    }

    private fun updateList() {
        phoneNumberListLD.value = phoneNumberList.toList()
    }
}