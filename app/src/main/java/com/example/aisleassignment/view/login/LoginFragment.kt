package com.example.aisleassignment.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aisleassignment.R
import com.example.aisleassignment.databinding.FragmentLoginBinding
import com.example.aisleassignment.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var dataBinding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        viewModel.isNumberRegistered.observe(this) {
            if (it) {
                findNavController().navigate(R.id.action_loginFragment_to_otpVerificationFragment)
            } else {
                Toast.makeText(requireContext(), "This number is not registered",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.btnContinue.setOnClickListener {
            val phoneNumber = dataBinding.etPhoneNumber.text.toString()
            val countryCode = dataBinding.tvCountryCode.text.toString()

            if(viewModel.isValidPhoneNumber(countryCode,phoneNumber)) {
                viewModel.loginWithPhoneNumber()
            } else{
                Toast.makeText(
                    requireContext(),
                    "Phone Number should have 10 digits",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}