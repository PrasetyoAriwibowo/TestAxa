package dependencies.android.testaxa.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dependencies.android.RetrofitClient
import dependencies.android.api_service.PlaceHolderService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context) =
        RetrofitClient.client(context)

    @Provides
    @Singleton
    fun providePlaceHolderService(retrofit: Retrofit) =
        retrofit.create(PlaceHolderService::class.java)
}