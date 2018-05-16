package test.project.vkapi.activities.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import test.project.vkapi.R;
import test.project.vkapi.activities.BaseActivity;
import test.project.vkapi.activities.auth.OAuthActivity;
import test.project.vkapi.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int AUTH_CODE = 8237;

    private TextView status;

    //@Inject
    //MainActivityViewModel viewModel;

    ActivityMainBinding binding;

    @Inject
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        getAppComponent().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewmodel(mainViewModel);
        //binding.setMainViewModel(mainViewModel);
        mainViewModel.init();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        status = findViewById(R.id.login_status);
        //viewModel.setView(this);
    }

    public void startAuthActivity() {
        Intent intent = new Intent(this, OAuthActivity.class);
        startActivityForResult(intent, AUTH_CODE);
    }

    public void updateLoginStatus(boolean isLoggedIn) {
        status.setText(isLoggedIn ? "signed in" : "signed out");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTH_CODE) {
            if (resultCode == RESULT_OK) {
                String token = data.getStringExtra(OAuthActivity.TOKEN);
                //viewModel.login(token);
                mainViewModel.login(token);
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
            //viewModel.logout();
            mainViewModel.logout();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFeed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.d("feed", message);
    }

    public void setError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        Log.e("error", error);
    }
}
