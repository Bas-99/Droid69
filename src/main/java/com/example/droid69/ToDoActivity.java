package com.example.droid69;

import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.content.res.TypedArrayUtils;
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
    ArrayList<Task> tasksList = new ArrayList<>();

    CheckBox checkBoxActive;
    EditText editText;
    TextView timerView;

    View checkBoxView;
    View activeTasks;

    ImageButton submitTasks;

    AppCompatSpinner spinnerDate;

    int checkBoxId;
    int counter =0;

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
        submitTasks = (ImageButton) findViewById(R.id.submitTasks);

        layoutList = findViewById(R.id.checkBoxContainer);

        addTask.setOnClickListener(this);
        submitTasks.setOnClickListener(this);

        dateList.add("Date");
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
        switch (view.getId()){
            case R.id.addTasks:
                addView();
                break;
            case R.id.submitTasks:

                if (checkIfValidAndRead()){

                    Intent intent = new Intent(ToDoActivity.this,AgendaActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list",tasksList);
                    intent.putExtras(bundle);
                    startActivity(intent);


                }

                break;
        }

    }

    private boolean checkIfValidAndRead() {
        tasksList.clear();
        boolean result = true;

        for (int i=0;i<layoutList.getChildCount();i++){

            checkBoxView = layoutList.getChildAt(i);

            editText = (EditText) checkBoxView.findViewById(R.id.checkBoxNewTask);
            spinnerDate = (AppCompatSpinner) checkBoxView.findViewById(R.id.spinner_date);

            Task task = new Task();

            if (!editText.getText().toString().equals("")){
                task.setTask(editText.getText().toString());
            }else{
                result = false;
                break;
            }

            if (spinnerDate.getSelectedItemPosition()!=0){
                task.setDate(dateList.get(spinnerDate.getSelectedItemPosition()));
            }else {
                result = false;
                break;
            }

            tasksList.add(task);
        }

        if (tasksList.size()==0){
            result = false;
            Toast.makeText(this,"Add Tasks First!",Toast.LENGTH_SHORT).show();

        }else if(!result){
            Toast.makeText(this,"Enter All Details Correctly!",Toast.LENGTH_SHORT).show();

        }

        return result;

    }


    private void addView() {
        checkBoxView = getLayoutInflater().inflate(R.layout.row_add_checkbox, null, false);
        activeTasks = getLayoutInflater().inflate(R.layout.row_add_active_task, null, false);

        spinnerDate = (AppCompatSpinner) checkBoxView.findViewById(R.id.spinner_date);

        ImageView imageClose = (ImageView) checkBoxView.findViewById(R.id.deleteTaskDelete);

        timerView = (TextView) activeTasks.findViewById(R.id.timerView);
        editText = (EditText) checkBoxView.findViewById(R.id.checkBoxNewTask);

        checkBoxActive = (CheckBox) activeTasks.findViewById(R.id.checkBoxActiveTask);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, dateList);
        spinnerDate.setAdapter(arrayAdapter);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeVi(checkBoxView);
            }
        });


        layoutList.addView(checkBoxView);

    }

    private void removeVi(View checkBoxView) {
        layoutList.removeView(checkBoxView);

    }



}