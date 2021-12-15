package com.example.rxjava2hw4.mvpcalculation

import android.widget.Toast
import com.example.rxjava2hw4.model.DataCalcRepository
import com.example.rxjava2hw4.view.App
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.subjects.BehaviorSubject
import moxy.MvpPresenter

class CalculationPresenter(
    private val dataCalcRepository: DataCalcRepository,
    private val router: Router,
) : MvpPresenter<ICalculationView>() {
    private val subject = BehaviorSubject.create<Unit>()
    private val context = App.instance.getContext()

   override fun onFirstViewAttach() {

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun validateData(number: String) {
        if (number.isNullOrEmpty()) {
            Toast.makeText(context, "Введите значение!!!", Toast.LENGTH_SHORT).show()
        } else {
            subject

        }
    }
}






