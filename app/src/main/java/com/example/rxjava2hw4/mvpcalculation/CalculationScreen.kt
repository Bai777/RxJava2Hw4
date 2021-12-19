package com.example.rxjava2hw4.mvpcalculation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class CalculationScreen() : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        CalculationFragment.newInstance()
}