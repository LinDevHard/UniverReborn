package com.lindevhard.android.univerrebornlite.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.utils.toSpanned
import com.lindevhard.android.univerrebornlite.utils.viewModelProvider
import com.lindevhard.android.univerrebornlite.viewmodel.NewsDetailViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_news_detail.*
import javax.inject.Inject

class NewsDetailFragment : DaggerFragment() {
    private val args: NewsDetailFragmentArgs by navArgs()


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewsDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)
        val newsId: Int = args.newsId

        Log.d("NewsDetailFragment", args.toString())
        viewModel.loadNewsById(newsId)
        viewModel.news.observe(this, Observer { news ->
            if (news != null && news.body.isNotEmpty()) {
                collapsingToolbar.title = news.title
                toolbar_date.text = news.date
                title.text = news.title
                date.text = news.date
                baseContent.text = news.body.toSpanned()
            }
        })
    }
}