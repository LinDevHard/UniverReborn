package com.lindevhard.android.univerrebornlite.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.ui.adapter.UmkdFilesAdapter
import com.lindevhard.android.univerrebornlite.utils.viewModelProvider
import com.lindevhard.android.univerrebornlite.viewmodel.UmkdDetailViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.appbar_with_session.*
import kotlinx.android.synthetic.main.fragment_umkd_detail.*
import javax.inject.Inject

class UmkdDetailFragment : DaggerFragment() {
    private val args: UmkdDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: UmkdDetailViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_umkd_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)
        recycleView.layoutManager = LinearLayoutManager(this.context)
        toolbar_title.setText(R.string.ukmd_title)
        val fileId: Long = args.fileId
        val title: String = args.title
        toolbar_title.text = title

        viewModel.findFilesById(fileId)
        viewModel.umkd.observe(this, Observer {
            recycleView.adapter = UmkdFilesAdapter(it) { file ->
                viewModel.getUmkdFile(file.fname, file.itemId, fileId)
            }
        })

    }
}
