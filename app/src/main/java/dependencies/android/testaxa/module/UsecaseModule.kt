package dependencies.android.testaxa.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dependencies.android.api_service.PlaceHolderService
import dependencies.android.usecase.PlaceHolderUsecase

@Module
@InstallIn(SingletonComponent::class)
class UsecaseModule {

    @Provides
    fun providePlaceHolderUsecase(placeHolderService: PlaceHolderService) =
        PlaceHolderUsecase(placeHolderService)
}