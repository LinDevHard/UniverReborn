package com.lindevhard.android.univerrebornlite.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.api.News
import com.lindevhard.android.univerrebornlite.ui.fragment.NewsFragmentDirections
import com.lindevhard.android.univerrebornlite.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_news.view.*

class NewsAdapter(private val items: List<News>) :
        RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = parent.inflate(R.layout.list_news, false)
        return ViewHolder(inflateView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        private var newsId: Int = 0

        init {
            containerView.mainContainer.setOnClickListener {
                val action = NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(newsId)
                it.findNavController().navigate(action)
            }
        }


        fun bind(news: News) {
            containerView.date.text = news.date
            containerView.title.text = news.title
            newsId = news.id
        }

    }
}