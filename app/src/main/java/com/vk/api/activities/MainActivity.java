package com.vk.api.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.vk.api.R;
import com.vk.api.databinding.ActivityMainBinding;
import com.vk.api.fragments.about.AboutFragment;
import com.vk.api.fragments.BaseFragment;
import com.vk.api.fragments.feed.FeedFragment;
import com.vk.api.fragments.feed.FeedItemClickListener;
import com.vk.api.fragments.feed.item.FeedItemFragment;
import com.vk.api.fragments.login.LoginFragment;
import com.vk.api.fragments.login.LoginListener;

import javax.inject.Inject;

import test.project.vkapi.core.feeds.models.Feed;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoginListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    @Inject
    MainActivityViewModel mainViewModel;

    public static final String TOKEN = "access_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppComponent().inject(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewmodel(mainViewModel);

        toolbar = binding.appBarMain.toolbar;
        setSupportActionBar(toolbar);

        drawer = binding.drawerLayout;
        setUpDrawer();

        navigationView = binding.navView;
        navigationView.setNavigationItemSelectedListener(this);

        mainViewModel.setListener(this);
        mainViewModel.init();
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
            onAuthorized();
        } else if (id == R.id.nav_about) {
            goTo(new AboutFragment());
        } else if (id == R.id.nav_exit) {
            mainViewModel.logout();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setUpDrawer() {
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void switchFragment(BaseFragment fragment, String tag, boolean addToStack) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_fragment_layout, fragment, tag)
                .commit();
    }

    private void showLoginFragment() {
        lockDrawer();
        setToolbarVisibility(false);
        goTo(new LoginFragment());
        //fragment.setListener(this);
    }

    private void lockDrawer() {
        if (drawer != null) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    private void unlockDrawer() {
        if (drawer != null) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
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

    @Override
    public void onTokenReceived(String token) {
        mainViewModel.login(token);
        onAuthorized();
    }

    @Override
    public void onNotAuthorized() {
        showLoginFragment();
    }

    @Override
    public void onAuthorized() {
        unlockDrawer();
        setToolbarVisibility(true);
        goTo(new FeedFragment());
        //fragment.setListener(this);
    }

    public void goTo(BaseFragment f) {
        // go to passed fragment
        switchFragment(f, "", true);
    }

    public void goBack() {

    }
}
