package test.project.vkapi.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import test.project.vkapi.core.api.VkApi;
import test.project.vkapi.core.feeds.api.models.FeedConverterFactory;

@Module
public class RetrofitModule {
    @Provides
    @Singleton
    public VkApi provideApi() {
        return new Retrofit.Builder()
                .baseUrl("https://api.vk.com/")
                .addConverterFactory(new FeedConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createClient())
                .build()
                .create(VkApi.class);
    }

    private OkHttpClient createClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }
}
