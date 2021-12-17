package com.example.rxjava2hw4.mvpcalculation

import android.widget.Toast
import com.example.rxjava2hw4.view.App
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import moxy.MvpPresenter

class CalculationPresenter(
    private val number: String,
    private val router: Router,
) : MvpPresenter<ICalculationView>() {
    private val subject = PublishSubject.create<Unit>()
    private val context = App.instance.getContext()

    override fun onFirstViewAttach() {

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun validateData(number: String) {
        val result: Int = number.toInt()
        if (number.isNullOrEmpty()) {
            Toast.makeText(context, "Введите значение!!!", Toast.LENGTH_SHORT).show()
        } else {
            subject
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map {
                    result * result
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, {})
        }
    }
}






