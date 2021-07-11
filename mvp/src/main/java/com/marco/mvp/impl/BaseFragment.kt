package com.marco.mvp.impl

import androidx.fragment.app.Fragment
import com.marco.mvp.IMVPView
import com.marco.mvp.IPresenter

abstract class BaseFragment<out P : BasePresenter<BaseFragment<P>>> : IMVPView<P>, Fragment() {

    override val presenter: P

    init {
        presenter = createPresenter()
        presenter.view = this
    }

    fun createPresenter(): P = TODO()
}