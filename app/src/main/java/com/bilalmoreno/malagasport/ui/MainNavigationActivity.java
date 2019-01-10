package com.bilalmoreno.malagasport.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.bilalmoreno.malagasport.MalagaSportApplication;
import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.db.model.Usuario;
import com.bilalmoreno.malagasport.ui.base.BaseFragment;
import com.bilalmoreno.malagasport.ui.installation.InstallationFragment;
import com.bilalmoreno.malagasport.ui.installation.InstallationListFragment;
import com.bilalmoreno.malagasport.ui.installation.ValoracionFragment;
import com.bilalmoreno.malagasport.ui.login.LoginActivity;
import com.bilalmoreno.malagasport.ui.workout.MachineListFragment;
import com.bilalmoreno.malagasport.ui.workout.WorkoutListFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MainNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseFragment.PrimaryActionButton, InstallationListFragment.OnInstallationShow, InstallationFragment.OnValorationShow, ValoracionFragment.OnValorationSavedChanges, MachineListFragment.OnMachineShow, WorkoutListFragment.OnWorkoutShow, OnMapReadyCallback {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private FloatingActionButton primaryActionButton;

    private InstallationListFragment installationListFragment;
    private InstallationFragment installationFragment;
    private ValoracionFragment valoracionFragment;
    private SettingsFragment settingsFragment;
    private WorkoutListFragment workoutListFragment;
    private MachineListFragment machineListFragment;
    private SupportMapFragment mapFragment;

    public void onPrimaryActionButtonHide() {
        primaryActionButton.hide();
    }

    @Override
    public void onPrimaryActionButtonShow(int iconResourceId, View.OnClickListener listener) {
        primaryActionButton.setImageResource(iconResourceId);
        primaryActionButton.setOnClickListener(listener);
        primaryActionButton.show();
    }


    public FloatingActionButton getPrimaryActionButton() {
        primaryActionButton.show();
        return primaryActionButton;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        primaryActionButton = findViewById(R.id.fab);
        primaryActionButton.hide();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navigation, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        installationListFragment = InstallationListFragment.getInstance(null, this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content, installationListFragment, InstallationListFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            settingsFragment = new SettingsFragment();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, settingsFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            return true;
        } else if (id == R.id.action_logout) {
            SharedPreferences loginData = getSharedPreferences(MalagaSportApplication.LOGIN_DATA_TAG, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = loginData.edit();
            editor.putString(MalagaSportApplication.USER_TAG, "");
            editor.putString(MalagaSportApplication.PASSWORD_TAG, "");
            editor.apply();

            MalagaSportApplication.setUserId("");

            startActivity(new Intent(MainNavigationActivity.this, LoginActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle menu_navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = null;

        if (id == R.id.map) {

            loadMap();

        } else if (id == R.id.installationList) {
            installationListFragment = (InstallationListFragment) getSupportFragmentManager().findFragmentByTag(InstallationListFragment.TAG);

            if (installationListFragment == null) {
                installationListFragment = InstallationListFragment.getInstance(null, this);
            }
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, installationListFragment, InstallationListFragment.TAG);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.workoutList) {
            workoutListFragment = (WorkoutListFragment) getSupportFragmentManager().findFragmentByTag(WorkoutListFragment.TAG);

            if (workoutListFragment == null) {
                workoutListFragment = WorkoutListFragment.getInstance(null, this);
            }
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, workoutListFragment, WorkoutListFragment.TAG);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.favs) {

        } else if (id == R.id.eventsList) {

        } else if (id == R.id.settings) {
            settingsFragment = (SettingsFragment) getSupportFragmentManager().findFragmentByTag(SettingsFragment.TAG);

            if (settingsFragment == null) {
                settingsFragment = new SettingsFragment();
            }

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, settingsFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.about) {
            intent = new Intent(MainNavigationActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadMap() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onValorationEdit(String userId, int installationId) {
        Bundle bundle = new Bundle();

        bundle.putInt(ValoracionFragment.ACTION_TAG, ValoracionFragment.VALORACION_EDIT);
        bundle.putInt(Installation.TAG, installationId);
        bundle.putString(Usuario.TAG, userId);

        valoracionFragment = ValoracionFragment.getInstance(bundle, this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, valoracionFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onValorationAdd(String userId, int installationId) {

        Bundle bundle = new Bundle();

        bundle.putInt(ValoracionFragment.ACTION_TAG, ValoracionFragment.VALORACION_ADD);
        bundle.putInt(Installation.TAG, installationId);
        bundle.putString(Usuario.TAG, userId);

        valoracionFragment = ValoracionFragment.getInstance(bundle, this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, valoracionFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onInstallationShow(int installationId) {
        Bundle bundle = new Bundle();
        bundle.putInt(Installation.TAG, installationId);

        installationFragment = InstallationFragment.getInstance(bundle, this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, installationFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onValorationSavedChanges() {
        onBackPressed();
    }

    @Override
    public void onMachineShow(int installationId) {
        Toast.makeText(this, String.valueOf(installationId), Toast.LENGTH_SHORT).show();
        //TODO mostrar info maquina (MachineFragment)
    }

    @Override
    public void onWorkoutMachinesShow(int installationId) {
        Bundle bundle = new Bundle();
        bundle.putInt(Installation.TAG, installationId);

        machineListFragment = MachineListFragment.getInstance(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, machineListFragment, MachineListFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (mapFragment == null) {
            loadMap();
        } else {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, mapFragment, "map");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
