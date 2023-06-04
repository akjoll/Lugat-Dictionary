package kg.lugatdictionary.data.network.service

import kg.lugatdictionary.data.network.entities.WordResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * this service  for fetching response from backend
 */
interface MainService {

    @GET("project/")
    suspend fun getWords(): Response<List<WordResponse>>
}