package com.thekorovay.testmosmetro.presentation

import android.content.Intent
import androidx.core.net.toUri
import com.thekorovay.testmosmetro.model.domain_model.Tweet
import com.thekorovay.testmosmetro.model.network.getServerApi
import com.thekorovay.testmosmetro.ui.ViewState
import moxy.InjectViewState
import moxy.MvpPresenter
import kotlinx.coroutines.*
import java.lang.Exception

@InjectViewState
class MainActivityPresenter: MvpPresenter<MainActivityInterface>() {
    private val tweetUrl = "https://mobile.twitter.com/MetroOperativno/status/"

    private val api = getServerApi()
    private var job: Job? = null

    override fun onFirstViewAttach() {
        loadTweets()
    }

    fun loadTweets() {
        if (job != null && job!!.isActive) {
            return
        }

        viewState.setViewState(ViewState.LOADING)

        job = Job()

        CoroutineScope(Dispatchers.IO + job!!).launch {
            try {
                api.requestTwitterFeed().run {
                    if (success) {
                        viewState.setViewState(ViewState.SUCCESS, tweets)
                    } else {
                        viewState.setViewState(ViewState.ERROR)
                    }
                }
            } catch (e: Exception) {
                job = null
                viewState.setViewState(ViewState.ERROR)
            }
        }
    }

    fun getIntentForTweet(tweet: Tweet) = Intent(Intent.ACTION_VIEW, "$tweetUrl${tweet.id}".toUri())
}