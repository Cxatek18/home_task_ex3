package com.team.phonebook.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.team.phonebook.R
import com.team.phonebook.databinding.FragmentMainBinding
import com.team.phonebook.presentation.adapters.PhoneNumberAdapter
import com.team.phonebook.presentation.view_models.MainViewModel

class MainFragment : Fragment() {

    private lateinit var phoneNumberAdapter: PhoneNumberAdapter
    private var isClickRemovePhoneNumbers = false

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().application)
        )[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btn_remove_phone_numbers -> {
                phoneNumberAdapter.isClickMenuOptionsRemovePhoneNumber = true
                isClickRemovePhoneNumbers = true
                binding.deleteButtonPhoneNumbers.visibility = View.VISIBLE
                phoneNumberAdapter.notifyDataSetChanged()
                true
            }

            R.id.btn_exit_mode_remove_phone_numbers -> {
                phoneNumberAdapter.isClickMenuOptionsRemovePhoneNumber = false
                isClickRemovePhoneNumbers = false
                binding.deleteButtonPhoneNumbers.visibility = View.GONE
                viewModel.editIsNoDeletedPhoneNumber()
                phoneNumberAdapter.notifyDataSetChanged()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val menuItemBtnRemove = menu.findItem(R.id.btn_remove_phone_numbers)
        val menuExitModeRemovePhoneNumbers = menu.findItem(R.id.btn_exit_mode_remove_phone_numbers)
        if (isClickRemovePhoneNumbers) {
            menuItemBtnRemove.isVisible = false
            menuExitModeRemovePhoneNumbers.isVisible = true
            requireActivity().invalidateOptionsMenu()
        } else {
            menuItemBtnRemove.isVisible = true
            menuExitModeRemovePhoneNumbers.isVisible = false
            requireActivity().invalidateOptionsMenu()
        }
        super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        phoneNumberAdapter = PhoneNumberAdapter()
        installationPhoneNumberListToAdapter(phoneNumberAdapter)
        binding.recyclerViewPhoneNumbers.adapter = phoneNumberAdapter
        clickAddNewPhoneNumber()
        onLongClickListenerPhoneNumberItem(phoneNumberAdapter)
        clickCheckBoxDeletedPhoneNumber(phoneNumberAdapter)
        onClickDeleteListPhoneNumbers(phoneNumberAdapter)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        requireActivity().finish()
    }

    private fun installationPhoneNumberListToAdapter(adapter: PhoneNumberAdapter) {
        viewModel.listPhoneNumber.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun clickAddNewPhoneNumber() {
        binding.addNumberPhoneButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, AddPhoneNumberFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun onLongClickListenerPhoneNumberItem(adapter: PhoneNumberAdapter) {
        adapter.onPhoneNumberLongClick = {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, EditPhoneNumberFragment.newInstance(it))
                .addToBackStack(null)
                .commit()
        }
    }

    private fun clickCheckBoxDeletedPhoneNumber(adapter: PhoneNumberAdapter) {
        adapter.setMarkCheckBox = {
            viewModel.editToDeletePhoneNumber(it)
        }
    }

    private fun onClickDeleteListPhoneNumbers(adapter: PhoneNumberAdapter) {
        binding.deleteButtonPhoneNumbers.setOnClickListener {
            viewModel.deleteListPhoneNumber()
        }
    }

    companion object {

        const val NAME_FRAGMENT = "main_fragment"

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}