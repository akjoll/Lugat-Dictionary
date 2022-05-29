package kg.lugatdictionary.ui.favourite

import kg.lugatdictionary.domain.entities.Word

interface WordListeners {
    fun onWordClicked(word: Word)
    fun deleteWord(word: Word)
}