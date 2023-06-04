package kg.lugatdictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kg.lugatdictionary.R
import kg.lugatdictionary.databinding.ActivityMainBinding
import kg.lugatdictionary.ui.utils.Loadable
import kg.lugatdictionary.ui.utils.base.BaseActivity
import kg.lugatdictionary.ui.utils.extensions.fragmentsToHideBottomNavOn
import kg.lugatdictionary.vm.MainVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(), Loadable {

    private val viewModel: MainVM by viewModel()

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(
            R.id.fragment_container_view
        ) as NavHostFragment
    }

    private val listener =
        NavController.OnDestinationChangedListener { _, dest, _ ->
            binding.bottomNav.isVisible = !fragmentsToHideBottomNavOn.contains(dest.id)
        }

    override fun inflateBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation()
        initListeners()
        initObservers()
    }

    private fun initObservers() {

    }

    private fun initListeners() {

    }

    private fun setupNavigation() {
        NavigationUI.setupWithNavController(
            binding.bottomNav, navHostFragment.navController
        )
    }

    override fun onResume() {
        super.onResume()
        navHostFragment.navController.addOnDestinationChangedListener(
            listener
        )
    }

    override fun onPause() {
        navHostFragment.navController
            .removeOnDestinationChangedListener(
                listener
            )
        super.onPause()
    }

    override fun onLoadChanged(isLoading: Boolean) {
        binding.inclProgress.clProgressBar.isVisible = isLoading
    }


}