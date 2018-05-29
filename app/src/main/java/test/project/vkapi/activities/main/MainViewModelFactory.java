package test.project.vkapi.activities.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import test.project.vkapi.core.api.VkApi;
import test.project.vkapi.core.user.UserManager;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final VkApi api;
    private final UserManager userManager;

    @Inject
    public MainViewModelFactory(VkApi api, UserManager userManager) {
        this.api = api;
        this.userManager = userManager;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(api, userManager);
        }
        throw new RuntimeException();
    }
}
