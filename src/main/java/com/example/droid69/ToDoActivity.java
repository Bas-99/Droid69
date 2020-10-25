package com.example.droid69;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import org.jetbrains.annotations.NotNull;

public class ToDoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(false);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent i1 = new Intent(ToDoActivity.this,AgendaActivity.class);
                startActivity(i1);
                break;
            case R.id.nav_customize:
                Intent i2 = new Intent(ToDoActivity.this,CustomizeActivity.class);
                startActivity(i2);
                break;
            case R.id.nav_share:
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_achievements:
                Intent i3 = new Intent(ToDoActivity.this,AchievementsActivity.class);
                startActivity(i3);
                break;
            case R.id.nav_tasks:
                break;
            case R.id.nav_profile:
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}