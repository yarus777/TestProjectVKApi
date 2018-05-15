package test.project.vkapi.core;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import test.project.vkapi.core.api.VkApi;

@Module(includes = OkHttpClientModule.class)
public class RetrofitModule {

    @Provides
    public VkApi api(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://api.vk.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(VkApi.class);
    }
}
