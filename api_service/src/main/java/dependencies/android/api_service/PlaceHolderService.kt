package dependencies.android.api_service

import com.android.entity.PlaceHolderResponse
import com.android.entity.PlaceHolderResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface PlaceHolderService {
    @GET("/photos")
    suspend fun getPlaceHolder(): Response<List<PlaceHolderResponseItem>>
}