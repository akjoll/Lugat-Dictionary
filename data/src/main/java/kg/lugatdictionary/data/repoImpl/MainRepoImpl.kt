package kg.lugatdictionary.data.repoImpl


import kg.lugatdictionary.data.memory.LugatSharedPreferences
import kg.lugatdictionary.data.network.service.MainService
import kg.lugatdictionary.domain.repository.MainRepo

class MainRepoImpl(
    private val service: MainService,
    private val sharedPref: LugatSharedPreferences
): MainRepo {

}