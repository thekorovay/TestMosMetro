package com.thekorovay.testmosmetro.presentation

import com.thekorovay.testmosmetro.model.domain_model.Tweet
import com.thekorovay.testmosmetro.ui.ViewState
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainActivityInterface: MvpView {
    @AddToEndSingle
    fun setViewState(state: ViewState, tweets: List<Tweet>? = null)
}