package com.vk.api.activities;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vk.api.R;
import com.vk.api.fragments.BaseFragment;
import com.vk.api.fragments.about.AboutFragment;
import com.vk.api.fragments.feed.FeedFragment;
import com.vk.api.fragments.login.LoginFragment;
import com.vk.api.navigation.BackStack;

import javax.inject.Inject;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    private BackStack backStack;

    //@Inject
    //MainActivityViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backStack = new BackStack();
        getAppComponent().inject(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        setUpDrawer();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        final ImageView imageView = findViewById(R.id.userpic);
        final TextView userNameView = findViewById(R.id.username);

        /*mainViewModel.getImageUrl().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String url) {
                loadImage(imageView, url);
            }
        });

        mainViewModel.getUserName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String username) {
                userNameView.setText(username);
            }
        });*/
        goTo(new LoginFragment(), false);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_news) {
            goTo(new FeedFragment(), true);
        } else if (id == R.id.nav_about) {
            goTo(new AboutFragment(), true);
        } else if (id == R.id.nav_exit) {
            //mainViewModel.logout();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setUpDrawer() {
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }

    private void switchFragment(BaseFragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_fragment_layout, fragment, tag)
                .commit();
    }

    private void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(view);
    }

    public void setToolbarVisibility(boolean isVisible) {
        if (toolbar != null) {
            if (isVisible) {
                toolbar.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
            } else {
                toolbar.setVisibility(View.GONE);
            }
        }
    }

    public void setDrawerLocked(boolean isLocked) {
        if (drawer != null){
            if (isLocked) {
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            } else {
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        }
    }

    public void goTo(BaseFragment f, boolean isVisible) {
        setDrawerLocked(isVisible);
        setToolbarVisibility(isVisible);
        switchFragment(f, "");
        backStack.add(f);
    }

    public void goBack() {
        backStack.pop();
    }
}
