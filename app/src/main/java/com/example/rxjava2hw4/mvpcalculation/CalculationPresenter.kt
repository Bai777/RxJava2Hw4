package com.example.rxjava2hw4.mvpcalculation

import android.widget.Toast
import com.example.rxjava2hw4.view.App
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import moxy.MvpPresenter

class CalculationPresenter(
    private val router: Router,
) : MvpPresenter<ICalculationView>() {
    private val subject = PublishSubject.create<String>()
    private val context = App.instance.getContext()

    override fun onFirstViewAttach() {
        observeChanges()
    }

    private fun updateContent(value: String) {
        subject.onNext(value)
    }

    private fun observeChanges() {
        subject
            .map { getSquare(it) }
            .observeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({viewState.showResult(it)}, {
                Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
            })
    }

    private fun getSquare(value: String): String{
        val userNumber = value.toInt()
        val result = userNumber * userNumber
        return result.toString()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun validateData(number: String) {
        if (number.isNullOrEmpty()) {
            Toast.makeText(context, "Введите значение!!!", Toast.LENGTH_SHORT).show()
        } else {
            updateContent(number)
        }
    }
}






