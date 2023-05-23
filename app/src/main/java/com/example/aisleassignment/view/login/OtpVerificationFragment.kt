package com.example.aisleassignment.view.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
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
import com.example.aisleassignment.network.NetworkResult
import com.example.aisleassignment.view.user.UserActivity
import com.example.aisleassignment.viewmodel.LoginViewModel

class OtpVerificationFragment : Fragment() {

    private lateinit var dataBinding: FragmentOtpVerificationBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var countDownTimer: CountDownTimer
    private var timeLeftInMillis: Long = 60000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_otp_verification, container, false)

        initViewModel()
        initTimer()
        dataBinding.viewModel = viewModel
        return dataBinding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]

        viewModel.accessToken.observe(requireActivity()) { accessToken ->
            when (accessToken) {
                is NetworkResult.Success -> {
                    dataBinding.pbLoading.visibility = View.INVISIBLE
                    storeAccessToken(accessToken.data!!)
                    startUserActivity()
                }
                is NetworkResult.Loading -> {
                    dataBinding.pbLoading.visibility = View.VISIBLE
                }
                is NetworkResult.Failure -> {
                    dataBinding.pbLoading.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), "Entered OTP is invalid", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun storeAccessToken(accessToken: String) {
        val sharedPref =
            requireActivity().getSharedPreferences("shared_preference", Context.MODE_PRIVATE)
        sharedPref.edit().run {
            putString("accessToken", accessToken)
            apply()
        }
    }

    private fun startUserActivity() {
        startActivity(Intent(activity, UserActivity::class.java))
        activity?.finish()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countDownTimer.start()
        dataBinding.btnContinue.setOnClickListener {
            onContinueButtonClick()
        }

        dataBinding.ivEditPhoneNumber.setOnClickListener {
            findNavController().popBackStack()
        }

        dataBinding.tvTimer.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Otp sent again!",
                Toast.LENGTH_SHORT
            ).show()
            countDownTimer.start()
        }
    }

    private fun initTimer() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountdownText()
            }

            override fun onFinish() {
                dataBinding.tvTimer.text = getString(R.string.resend_otp)
            }
        }
    }

    private fun updateCountdownText() {
        val minutes = (timeLeftInMillis / 1000).toInt() / 60
        val seconds = (timeLeftInMillis / 1000).toInt() % 60
        val timeLeftFormatted = String.format("%02d:%02d", minutes, seconds)
        dataBinding.tvTimer.text = timeLeftFormatted
    }

    private fun onContinueButtonClick() {
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

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer.cancel()
    }
}