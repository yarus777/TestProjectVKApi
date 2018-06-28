package com.vk.api.activities;

import android.support.v7.app.AppCompatActivity;

import com.vk.api.AppInjector;
import com.vk.api.di.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {
    protected AppComponent getAppComponent() {
        return ((AppInjector)getApplication()).getInjector();
    }
}
