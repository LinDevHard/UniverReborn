package com.lindevhard.android.univerrebornlite.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.utils.viewModelProvider
import com.lindevhard.android.univerrebornlite.viewmodel.ProfileViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)
        viewModel.profile.observe(this, Observer { data ->
            swipe_refresher.isRefreshing = false

            mainName.text = data.univerData.student
            faculty.text = data.univerData.faculty
            speciality.text = data.univerData.speciality

            edu_form.text = data.univerData.eduForm
            edu_level.text = data.univerData.eduLevel
            lang_div.text = data.univerData.langDiv
            for_lang.text = data.univerData.forLang
            course_num.text = data.univerData.courseNum
            grant_date.text = data.univerData.grantDate
            grant_num.text = data.univerData.grantNum
            status.text = data.univerData.status
            stage.text = data.univerData.stage

            surname.text = data.personalData.surname
            name.text = data.personalData.name
            patronymic.text = data.personalData.patronymic
            gradeId.text = data.personalData.gradeBookNumber
            studentId.text = data.personalData.studentId
            sex.text = data.personalData.sex
        })

        swipe_refresher.setOnRefreshListener {
            viewModel.updateProfile()
        }
    }
}