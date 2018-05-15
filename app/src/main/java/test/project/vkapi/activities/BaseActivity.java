package test.project.vkapi.activities;

import android.support.v7.app.AppCompatActivity;

import test.project.vkapi.AppInjector;
import test.project.vkapi.di.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {
    protected AppComponent getAppComponent() {
        return ((AppInjector)getApplication()).getInjector();
    }
}
