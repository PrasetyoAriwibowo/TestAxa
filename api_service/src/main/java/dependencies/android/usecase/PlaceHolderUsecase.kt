package dependencies.android.usecase

import com.android.common.AppResponse
import com.android.entity.PlaceHolderResponseItem
import com.android.usecase.BaseUseCase
import dependencies.android.api_service.PlaceHolderService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class PlaceHolderUsecase(private val placeHolderService: PlaceHolderService) : BaseUseCase() {

    operator fun invoke(query: String?) = callbackFlow<AppResponse<List<PlaceHolderResponseItem>>> {
        launch {
            val data = placeHolderService.getPlaceHolder()
            if (data.isSuccessful) {
                data.body()?.let {
                    if (query == null) {
                        send(AppResponse.success(it))
                    } else {
                        send(AppResponse.success(it.filter { it.title.contains(query) }))
                    }
                } ?: kotlin.run {
                    send(AppResponse.error(Exception("Data Null")))

                    close()
                }
            } else {
                send(AppResponse.error(Exception("Data Null")))
                close()
            }
        }
        awaitClose()
    }
}