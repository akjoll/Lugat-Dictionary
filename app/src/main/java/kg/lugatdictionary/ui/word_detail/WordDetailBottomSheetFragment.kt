package kg.lugatdictionary.ui.word_detail

import android.view.LayoutInflater
import kg.lugatdictionary.R
import kg.lugatdictionary.databinding.BottomSheetWordDetailBinding
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.ui.utils.base.BaseBottomSheetVMFragment
import kg.lugatdictionary.ui.utils.extensions.updateAppWidget
import kg.lugatdictionary.ui.utils.extensions.withLifecycleScope
import kg.lugatdictionary.vm.WordDetailVM
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class WordDetailBottomSheetFragment(private val word: Word) : BaseBottomSheetVMFragment<BottomSheetWordDetailBinding, WordDetailVM>(){
    override val viewModel: WordDetailVM by viewModel{ parametersOf(word) }

    override fun inflateBinding(inflater: LayoutInflater) = BottomSheetWordDetailBinding.inflate(inflater)

    override fun init() {
        setUpUI()
        initListeners()
        initObservers()
        initRequests()
    }

    private fun initObservers() = with(viewModel) {
        withLifecycleScope(isFavorite){
            binding.ivFavorite.setImageResource(if (it) R.drawable.ic_star_pressed else R.drawable.ic_favourite_default )
        }

        withLifecycleScope(isWidget){
            binding.ivWidget.setImageResource(if (it) R.drawable.ic_widget_pressed_black else R.drawable.ic_widget_default )
        }
    }

    private fun initRequests() {
        viewModel.checkIsFavorite()
        viewModel.checkIsWidget()
    }

    private fun initListeners() = with(binding) {
        ivClose.setOnClickListener { dismiss() }

        ivFavorite.setOnClickListener {
            if (viewModel.isFavorite.value)
                viewModel.deleteFavorite()
            else
                viewModel.insertFavorite()
        }

        ivWidget.setOnClickListener {
            if (viewModel.isWidget.value)
                viewModel.deleteWidget()
            else
                viewModel.insertWidget()
        }
        updateAppWidget()
    }

    private fun setUpUI() = with(binding) {
        tvWord.text = word.word
        tvExplanation.text = word.explanation
    }
}