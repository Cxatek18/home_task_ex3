package com.team.phonebook.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.team.phonebook.databinding.FragmentEditPhoneNumberBinding
import com.team.phonebook.presentation.view_models.EditPhoneNumberViewModel

class EditPhoneNumberFragment : Fragment() {

    private var phoneNumberId: Int = -1
    private var _binding: FragmentEditPhoneNumberBinding? = null
    private val binding: FragmentEditPhoneNumberBinding
        get() = _binding ?: throw RuntimeException("FragmentEditPhoneNumberBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().application)
        )[EditPhoneNumberViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditPhoneNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseArgs()
        viewModel.getPhoneNumber(phoneNumberId)
        observeViewModel()
        clickButtonEditPhoneNumber()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.oldPhoneNumber.observe(viewLifecycleOwner) {
            binding.etName.setText(it.name)
            binding.etSurname.setText(it.surname)
            binding.etNumberPhone.setText(it.numberPhone)
        }

        viewModel.isEditPhoneNumber.observe(viewLifecycleOwner) {
            if (it) {
                requireActivity().supportFragmentManager.popBackStack(
                    MainFragment.NAME_FRAGMENT, 0
                )
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                it,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun clickButtonEditPhoneNumber() {
        binding.btnEditNumberPhone.setOnClickListener {
            viewModel.editPhoneNumber(
                phoneNumberId,
                binding.etName.text.toString(),
                binding.etSurname.text.toString(),
                binding.etNumberPhone.text.toString()
            )
        }
    }

    private fun parseArgs() {
        val args = requireArguments()
        if (!args.containsKey(ID_PHONE_NUMBER)) {
            throw RuntimeException("No args - id_phone_number")
        }
        phoneNumberId = args.getInt(ID_PHONE_NUMBER)
    }

    companion object {

        private const val ID_PHONE_NUMBER = "id_phone_number"

        fun newInstance(phoneNumberId: Int): EditPhoneNumberFragment {
            return EditPhoneNumberFragment().apply {
                arguments = Bundle().apply {
                    putInt(ID_PHONE_NUMBER, phoneNumberId)
                }
            }
        }
    }
}