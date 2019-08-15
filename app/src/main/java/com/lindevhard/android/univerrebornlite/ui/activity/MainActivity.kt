package com.lindevhard.android.univerrebornlite.ui.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.lindevhard.android.univerrebornlite.R
import com.lindevhard.android.univerrebornlite.ui.fragment.AuthFragmentDirections
import com.lindevhard.android.univerrebornlite.utils.viewModelProvider
import com.lindevhard.android.univerrebornlite.viewmodel.LauncherViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LauncherViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.host_fragment)

        viewModel = viewModelProvider(viewModelFactory)
        if (viewModel.checkLoggedStatus()) {
            navController.navigate(AuthFragmentDirections.actionAuthFragmentToAttendanceFragment3())
        }

        setupBottomNavMenu(navController)

        navController.addOnDestinationChangedListener { navController, destination, arguments ->
            when (destination.id) {
                R.id.authFragment -> hideBottomNavigation()
                R.id.newsDetailFragment -> hideBottomNavigation()
                R.id.umkdDetailFragment -> hideBottomNavigation()
                else -> showBottomNavigation()
            }
        }
    }

    private fun hideBottomNavigation() {
        with(bottom_nav) {
            if (visibility == View.VISIBLE) {
                animate()
                        .alpha(0f)
                        .withEndAction { visibility = View.GONE }
                        .duration = EXIT_DURATION
            }
        }
    }

    private fun showBottomNavigation() {
        with(bottom_nav) {
            visibility = View.VISIBLE
            animate()
                    .alpha(1f)
                    .duration = ENTER_DURATION
        }
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = bottom_nav
        bottomNav?.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    companion object {
        const val EXIT_DURATION = 350L
        const val ENTER_DURATION = 500L
    }
}
