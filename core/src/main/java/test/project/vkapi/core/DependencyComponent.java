package test.project.vkapi.core;


import dagger.Component;
import test.project.vkapi.core.api.VkApi;

@Component(modules = RetrofitModule.class)
public interface DependencyComponent {
    VkApi getRetrofit();
}
