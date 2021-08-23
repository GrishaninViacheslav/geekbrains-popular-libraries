package geekbrains.slava_5655380

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import geekbrains.slava_5655380.domain.models.githubusers.IDataSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiHolder {

    val api: IDataSource by lazy {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IDataSource::class.java)
    }

}