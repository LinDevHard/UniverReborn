package com.lindevhard.android.univerrebornlite.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.ui.adapter.NewsAdapter
import com.lindevhard.android.univerrebornlite.utils.viewModelProvider
import com.lindevhard.android.univerrebornlite.viewmodel.NewsViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.appbar_simple.*
import kotlinx.android.synthetic.main.fragment_news.*

import javax.inject.Inject

class NewsFragment : DaggerFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)
        recycleView.layoutManager = LinearLayoutManager(this.context)
        toolbar_title.text = "Новости"
        viewModel.news.observe(this, Observer { news ->
            swipe_refresher.isRefreshing = false
            recycleView.adapter = NewsAdapter(news)
        })

        swipe_refresher.setOnRefreshListener {
            viewModel.getNews()
        }
    }
}