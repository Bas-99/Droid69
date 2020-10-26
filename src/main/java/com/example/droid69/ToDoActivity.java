package com.example.droid69;

import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ToDoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    LinearLayout layoutList, layoutTasks;

    ImageButton addTask;

    List<String> dateList = new ArrayList<>();

    CheckBox checkBoxActive;
    EditText editText;

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

        addTask = (ImageButton) findViewById(R.id.addTasks);

        layoutList = findViewById(R.id.checkBoxContainer);
        layoutTasks = findViewById(R.id.layoutTasks);

        addTask.setOnClickListener(this);

        dateList.add("Today");
        dateList.add("Next day");
        dateList.add("2 days ahead");
        dateList.add("3 days ahead");
        dateList.add("4 days ahead");
        dateList.add("5 days ahead");
        dateList.add("6 days ahead");
        dateList.add("1 week ahead");

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
                Intent i1 = new Intent(ToDoActivity.this, AgendaActivity.class);
                startActivity(i1);
                break;
            case R.id.nav_customize:
                Intent i2 = new Intent(ToDoActivity.this, CustomizeActivity.class);
                startActivity(i2);
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_achievements:
                Intent i3 = new Intent(ToDoActivity.this, AchievementsActivity.class);
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

    @Override
    public void onClick(View view) {
        addView();
        addTask.setEnabled(false);

    }


    private void addView() {
        addTask.setEnabled(false);
        View checkBoxView = getLayoutInflater().inflate(R.layout.row_add_checkbox, null, false);
        View activeTasks = getLayoutInflater().inflate(R.layout.row_add_active_task,null,false);

        AppCompatSpinner spinnerDate = (AppCompatSpinner) checkBoxView.findViewById(R.id.spinner_date);

        ImageView imageClose = (ImageView) checkBoxView.findViewById(R.id.deleteTaskDelete);
        ImageView imageAdd = (ImageView)checkBoxView.findViewById(R.id.addTaskAdd);

        editText = (EditText) checkBoxView.findViewById(R.id.checkBoxNewTask);
        checkBoxActive = (CheckBox)activeTasks.findViewById(R.id.checkBoxActiveTask);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, dateList);
        spinnerDate.setAdapter(arrayAdapter);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeVi(checkBoxView);
            }
        });

        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask(checkBoxView,activeTasks);
            }
        });

        layoutList.addView(checkBoxView);

    }

    private void removeVi(View checkBoxView) {
        addTask.setEnabled(true);
        layoutList.removeView(checkBoxView);

    }

    private void addTask(View checkBoxView, View activeTasks) {
        addTask.setEnabled(true);
        layoutList.removeView(checkBoxView);
        checkBoxActive.setText(editText.getText().toString());
        layoutTasks.addView(activeTasks);
    }


}