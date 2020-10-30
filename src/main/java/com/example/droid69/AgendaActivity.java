package com.example.droid69;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;


@RequiresApi(api = Build.VERSION_CODES.O)
public class AgendaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    CheckBox[] checkBoxes = new CheckBox[6];
    TextView[] textViews = new TextView[5];
    TextView[] todos = new TextView[6];

    TextView textView, textViewProgress, tasksTotal, tasksWeek, tasksToday, textViewCustomize;

    ImageButton todo, achievementsButton, customizeButton;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    RelativeLayout relativeLayout;
    RecyclerView linearLayoutTasksList;

    ScrollView background,scrollViewRecycler;

    ArrayList<Task> tasksList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        loadData();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        linearLayoutTasksList = (RecyclerView)findViewById(R.id.linearLayoutTasksList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        linearLayoutTasksList.setLayoutManager(layoutManager);

        //tasksList = (ArrayList<Task>) getIntent().getExtras().getSerializable("list");

        linearLayoutTasksList.setAdapter(new TaskAdapter(tasksList));




        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        textView = (TextView) findViewById(R.id.todoText);
        textViewProgress = (TextView) findViewById(R.id.textViewProgress);
        tasksTotal = (TextView) findViewById(R.id.tasksTotal);
        tasksWeek = (TextView) findViewById(R.id.tasksWeek);
        tasksToday = (TextView) findViewById(R.id.tasksToday);
        textViewCustomize = (TextView) findViewById(R.id.textViewCustomize);

        textViews[0] = textView;
        textViews[1] = textViewProgress;
        textViews[2] = tasksTotal;
        textViews[3] = tasksWeek;
        textViews[4] = tasksToday;


        todo = (ImageButton) findViewById(R.id.toDoButton);
        achievementsButton = (ImageButton) findViewById(R.id.achievementsButton);
        customizeButton = (ImageButton) findViewById(R.id.customizeButton);

        todo.setBackgroundResource(R.drawable.round_grey);
        achievementsButton.setBackgroundResource(R.drawable.round_grey);
        customizeButton.setBackgroundResource(R.drawable.round_grey);

        background = (ScrollView) findViewById(R.id.background);

        Typeface font = ResourcesCompat.getFont(AgendaActivity.this, R.font.font1);   //1
        textViewCustomize.setTypeface(font);

        if (CustomizeActivity.package_background == 1) {
            background.setBackgroundResource(R.drawable.theme1_small);
            todo.setBackgroundResource(R.drawable.todo_card);
            achievementsButton.setBackgroundResource(R.drawable.todo_card);
            customizeButton.setBackgroundResource(R.drawable.todo_card);

        } else if (CustomizeActivity.package_background == 2) {
            background.setBackgroundResource(R.drawable.theme1dark);
            todo.setBackgroundResource(R.drawable.theme1darktodo);
            achievementsButton.setBackgroundResource(R.drawable.theme1darktodo);
            customizeButton.setBackgroundResource(R.drawable.theme1darktodo);
            textViewCustomize.setTextColor(Color.WHITE);
            for (int i=0;i<textViews.length;i++){
                textViews[i].setTextColor(Color.WHITE);
            }
        } else if (CustomizeActivity.package_background == 3) {
            background.setBackgroundResource(R.drawable.theme1red);
            todo.setBackgroundResource(R.drawable.theme1redtodo);
            achievementsButton.setBackgroundResource(R.drawable.theme1redtodo);
            customizeButton.setBackgroundResource(R.drawable.theme1redtodo);
        }

        if (CustomizeActivity.package_font == 1) {
            Typeface font1 = ResourcesCompat.getFont(this, R.font.font1);   //1
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTypeface(font1);
            }
        } else if (CustomizeActivity.package_font == 2) {
            Typeface font2 = ResourcesCompat.getFont(this, R.font.font2);   //2
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTypeface(font2);
            }
        } else if (CustomizeActivity.package_font == 3) {
            Typeface font3 = ResourcesCompat.getFont(this, R.font.font3);   //3
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTypeface(font3);
            }
        } else if (CustomizeActivity.package_font == 4) {
            Typeface font4 = ResourcesCompat.getFont(this, R.font.font4);   //4
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTypeface(font4);
            }
        } else if (CustomizeActivity.package_font == 5) {
            Typeface font5 = ResourcesCompat.getFont(this, R.font.font5);   //5
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTypeface(font5);
            }
        } else if (CustomizeActivity.package_font == 6) {
            Typeface font6 = ResourcesCompat.getFont(this, R.font.font6);   //6
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTypeface(font6);
            }
        }



        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(false);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgendaActivity.this, ToDoActivity.class);
                startActivity(i);
            }
        });

        achievementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgendaActivity.this, AchievementsActivity.class);
                startActivity(i);
            }
        });

        customizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgendaActivity.this, CustomizeActivity.class);
                startActivity(i);
            }
        });
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
                break;
            case R.id.nav_customize:
                Intent i3 = new Intent(AgendaActivity.this, CustomizeActivity.class);
                startActivity(i3);
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_achievements:
                Intent i1 = new Intent(AgendaActivity.this, AchievementsActivity.class);
                startActivity(i1);
                break;
            case R.id.nav_tasks:
                Intent i2 = new Intent(AgendaActivity.this, ToDoActivity.class);
                startActivity(i2);
                break;
            case R.id.nav_profile:
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(tasksList);
        editor.putString("task list",json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list",null);
        Type type = new TypeToken<ArrayList<Task>>() {}.getType();
        tasksList = gson.fromJson(json, type);

        if (tasksList == null){
            tasksList = new ArrayList<>();
        }
    }

}