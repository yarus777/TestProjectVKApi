package test.project.vkapi.activities.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;

import test.project.vkapi.R;
import test.project.vkapi.activities.BaseActivity;
import test.project.vkapi.activities.auth.OAuthActivity;
import test.project.vkapi.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity implements MainViewModel.Observer, NavigationView.OnNavigationItemSelectedListener {

    private static final int AUTH_CODE = 8237;


    @Inject
    MainViewModel mainViewModel;

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppComponent().inject(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewmodel(mainViewModel);
        mainViewModel.setObserver(this);
        mainViewModel.init();

        toolbar = binding.appBarMain.toolbar;
        setSupportActionBar(toolbar);
        drawer = binding.drawerLayout;
        setUpDrawer();

        navigationView = binding.navView;
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void startAuthActivity() {
        Intent intent = new Intent(this, OAuthActivity.class);
        startActivityForResult(intent, AUTH_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTH_CODE) {
            if (resultCode == RESULT_OK) {
                String token = data.getStringExtra(OAuthActivity.TOKEN);
                mainViewModel.login(token);
            }
        }
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

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_exit) {
            mainViewModel.logout();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onNotAuthorized() {
        startAuthActivity();
    }

    private void setUpDrawer() {
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

}
