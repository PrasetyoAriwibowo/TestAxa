package dependencies.android.testaxa.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.common.AppResponse
import com.android.common.BaseViewModel
import com.android.common.SuccessResponse
import com.android.entity.PlaceHolderResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import dependencies.android.usecase.PlaceHolderUsecase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceHolderViewModel @Inject constructor(
    application: Application,
    val placeHolderUsecase: PlaceHolderUsecase
) : BaseViewModel(application) {

    val dataPlaceHolder = MutableLiveData<AppResponse<List<PlaceHolderResponseItem>>>()

    fun getPlaceHolder(query: String? = null) {
        viewModelScope.launch {
            placeHolderUsecase.invoke(query).collect {
                dataPlaceHolder.postValue(it)
            }
        }
    }

    init {
        getPlaceHolder()
    }

    fun filter(q: String): List<PlaceHolderResponseItem> {
        return dataPlaceHolder.value?.let {
            if (it is SuccessResponse) {
                it.data?.orEmpty()?.filter {
                    it.title.contains(q, true)
                }
            } else {
                arrayListOf()
            }
        } ?: run { arrayListOf() }
    }
}

