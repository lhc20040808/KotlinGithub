package com.marco.mvp

interface IPresenter<out View : IMVPView<IPresenter<View>>> : ILifeCycle {
    val view: View
}

interface IMVPView<out Presenter : IPresenter<IMVPView<Presenter>>> : ILifeCycle {
    val presenter: Presenter
}