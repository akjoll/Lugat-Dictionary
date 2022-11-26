package kg.lugatdictionary.ui.launch

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import kg.lugatdictionary.databinding.ActivityLauncherBinding
import kg.lugatdictionary.ui.MainActivity
import kg.lugatdictionary.ui.utils.base.BaseActivity
import kg.lugatdictionary.vm.LaunchVM
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchActivity: BaseActivity<ActivityLauncherBinding>() {

    private val viewModel: LaunchVM by viewModel()

    override fun inflateBinding(inflater: LayoutInflater) = ActivityLauncherBinding.inflate(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startMainActivity()
        //initObservers()
        //initRequests()
    }

    private fun initRequests() = with(viewModel) {
        fetchWords()
    }

    private fun initObservers() = with(viewModel) {
        viewModelScope.launch {
            words.collect {
                saveWords(it)
            }
        }

        viewModelScope.launch {
            successWordUpdate.collect {
                startMainActivity()
            }
        }

        failureLD.observe(this@LaunchActivity, Observer {
            startMainActivity()
        })

    }

    private fun startMainActivity() {
        val intent = Intent(this@LaunchActivity, MainActivity::class.java)
        intent.addFlags(
            Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
        )
        startActivity(intent)
    }
}