package com.example.aisleassignment.view.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.aisleassignment.R
import com.example.aisleassignment.databinding.FragmentOtpVerificationBinding

class OtpVerificationFragment : Fragment() {
    private lateinit var dataBinding: FragmentOtpVerificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_otp_verification, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.btnContinue.setOnClickListener {
            //Todo: Start new activity
        }

        dataBinding.ivEditPhoneNumber.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}