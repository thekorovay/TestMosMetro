package com.thekorovay.testmosmetro.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.thekorovay.testmosmetro.R
import com.thekorovay.testmosmetro.databinding.ActivityMainBinding
import com.thekorovay.testmosmetro.model.domain_model.Tweet
import com.thekorovay.testmosmetro.presentation.MainActivityInterface
import com.thekorovay.testmosmetro.presentation.MainActivityPresenter
import com.thekorovay.testmosmetro.ui.recycler_view.RecyclerViewTwitterAdapter
import com.thekorovay.testmosmetro.ui.recycler_view.TwitterListItem
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainActivityInterface {
    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RecyclerViewTwitterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvAdapter = RecyclerViewTwitterAdapter { tweet -> viewTweet(tweet)}
        val layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(this, layoutManager.orientation)

        binding.rvTweets.apply {
            setLayoutManager(layoutManager)
            adapter = rvAdapter
            addItemDecoration(divider)
        }

        binding.btnReload.setOnClickListener { presenter.loadTweets() }
    }

    override fun setViewState(state: ViewState, tweets: List<Tweet>?) {
        runOnUiThread {
            when (state) {
                ViewState.LOADING -> setLoading(true)
                ViewState.SUCCESS -> setTweets(true, tweets)
                ViewState.ERROR -> setErrorVisible(true, R.string.err_msg_unable_load_tweets)
            }
        }
    }

    private fun setErrorVisible(isError: Boolean, textResource: Int?) {
        if (isError) {
            setLoading(false)
            setTweets(false)
        }

        textResource?.let { binding.tvErrorMessage.setText(it) }

        val visibility = if (isError) View.VISIBLE else View.GONE
        binding.tvErrorMessage.visibility = visibility
        binding.btnReload.visibility = visibility
    }

    private fun setTweets(isVisible: Boolean, tweets: List<Tweet>? = null) {
        if (isVisible) {
            setLoading(false)
            setErrorVisible(false, null)
        }

        when {
            isVisible && !tweets.isNullOrEmpty() -> {
                binding.rvTweets.visibility = View.VISIBLE

                val list = mutableListOf<TwitterListItem>().apply {
                    add(TwitterListItem.FirstItem)
                    addAll(tweets.map { tweet ->
                        if (tweet.imageUrl != null) {
                            TwitterListItem.TweetItemWithImage(tweet)
                        } else {
                            TwitterListItem.TweetItem(tweet)
                        }
                    })
                }
                rvAdapter.submitList(list)
            }
            isVisible && tweets.isNullOrEmpty() -> {
                setErrorVisible(true, R.string.err_msg_no_tweets)
            }
            else -> {
                binding.rvTweets.visibility = View.GONE
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            setErrorVisible(false, null)
            setTweets(false)
        }

        binding.pbLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun viewTweet(tweet: Tweet) {
        val tweetIntent = presenter.getIntentForTweet(tweet)
        startActivity(tweetIntent)
    }
}