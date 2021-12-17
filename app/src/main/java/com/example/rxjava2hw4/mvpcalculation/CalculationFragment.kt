package com.example.rxjava2hw4.mvpcalculation

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rxjava2hw4.databinding.FragmentCalculationBinding
import com.example.rxjava2hw4.view.App
import com.example.rxjava2hw4.view.IBackButtonListener
import com.example.rxjava2hw4.view.IMainView
import com.github.terrakok.cicerone.Screen
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CalculationFragment : MvpAppCompatFragment(), IMainView, ICalculationView, IBackButtonListener {
    companion object : Screen {
        private const val ARG_RESULT = "arg_result"
        fun newInstance(number: String) = CalculationFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_RESULT, number)
            }
        }
    }
    private val numberUser: String by lazy {
        arguments?.getString(ARG_RESULT).orEmpty()
    }
    private val presenterCalculation: CalculationPresenter by moxyPresenter { CalculationPresenter(
        numberUser,
        router = App.instance.router
    ) }

    private var binding: FragmentCalculationBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentCalculationBinding.inflate(inflater, container, false).also {
            binding = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.buttonProceed.setOnClickListener{
            val result = binding?.etNumber?.text.toString()
            presenterCalculation.validateData(result)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun backPressed() = presenterCalculation.backPressed()

    override fun showResult(){
        binding?.tvResult?.text = numberUser as Editable
    }
}