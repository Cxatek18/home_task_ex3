package com.team.phonebook.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.team.phonebook.databinding.FragmentAddPhoneNumberBinding
import com.team.phonebook.presentation.view_models.AddPhoneNumberViewModel

class AddPhoneNumberFragment : Fragment() {

    private var _binding: FragmentAddPhoneNumberBinding? = null
    private val binding: FragmentAddPhoneNumberBinding
        get() = _binding ?: throw RuntimeException("FragmentAddPhoneNumberBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().application)
        )[AddPhoneNumberViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPhoneNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNewPhoneNumber()
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun addNewPhoneNumber() {
        binding.btnAddNumberPhone.setOnClickListener {
            if (checkFieldAddNewPhoneNumber()) {
                val etNameText = binding.etName.text.toString()
                val etSurnameText = binding.etSurname.text.toString()
                val etPhoneNumberText = binding.etNumberPhone.text.toString()
                viewModel.addPhoneNumber(etNameText, etSurnameText, etPhoneNumberText)
            } else {
                Toast.makeText(
                    requireContext(),
                    TEXT_IS_EMPTY_ET,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.isAddNewPhoneNumber.observe(viewLifecycleOwner) {
            if (it) {
                requireActivity().supportFragmentManager.popBackStack(
                    MainFragment.NAME_FRAGMENT, 0
                )
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                it.toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun checkFieldAddNewPhoneNumber(): Boolean {
        val etNameText = binding.etName.text.toString()
        val etSurnameText = binding.etSurname.text.toString()
        val etPhoneNumberText = binding.etNumberPhone.text.toString()
        if (etNameText.isEmpty() || etSurnameText.isEmpty() || etPhoneNumberText.isEmpty()) {
            return false
        }
        return true
    }

    companion object {

        private const val TEXT_IS_EMPTY_ET = "Поля должны быть заполнены"

        fun newInstance(): AddPhoneNumberFragment {
            return AddPhoneNumberFragment()
        }
    }
}