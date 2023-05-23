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
import com.example.aisleassignment.databinding.FragmentOtpVerificationBinding
import com.example.aisleassignment.viewmodel.LoginViewModel

class OtpVerificationFragment : Fragment() {

    private lateinit var dataBinding: FragmentOtpVerificationBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_otp_verification, container, false)

        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        dataBinding.viewModel = viewModel
        viewModel.isOtpValid.observe(requireActivity()) {
            if(it) {
                //Todo: start next activity
            } else {
                Toast.makeText(requireContext(), "Entered OTP is invalid",Toast.LENGTH_SHORT).show()
            }
        }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.btnContinue.setOnClickListener {
            val otp = dataBinding.etOtp.text.toString()
            if (viewModel.isOtpFormatValid(otp)) {
                viewModel.verifyOtp()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Otp should have 4 digits",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        dataBinding.ivEditPhoneNumber.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}