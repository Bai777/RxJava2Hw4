package com.example.rxjava2hw4.mvpcalculation

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ICalculationView: MvpView {
    fun showResult(result: Unit)
}