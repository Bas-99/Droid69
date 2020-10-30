package com.example.droid69;

import android.content.Intent;
import android.content.SharedPreferences;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ToDoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    LinearLayout layoutList, layoutTasks;
    RecyclerView recyclerView;

    ImageButton addTask;

    List<String> dateList = new ArrayList<>();
    ArrayList<Task> tasksList;

    CheckBox checkBoxActive;
    EditText editText;
    TextView timerView;

    View checkBoxView;
    View activeTasks;

    ImageButton submitTasks;

    Button saveData;

    AppCompatSpinner spinnerDate, spinnerColor;

    private LinearLayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        loadData();

        recyclerView = findViewById(R.id.recycler_todo);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setAdapter(new TaskAdapter(tasksList));

        recyclerView.setHasFixedSize(true);

        adapter = new LinearLayoutAdapter(tasksList);
        recyclerView.setAdapter(adapter);

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

        navigationView.setCheckedItem(R.id.nav_tasks);

        addTask = (ImageButton) findViewById(R.id.addTasks);
        submitTasks = (ImageButton) findViewById(R.id.submitTasks);

        layoutList = findViewById(R.id.checkBoxContainer);

        addTask.setOnClickListener(this);
        submitTasks.setOnClickListener(this);

        //dateList.clear();

        dateList.add("Date");
        dateList.add("Today");
        dateList.add("Next day");
        dateList.add("2 days");
        dateList.add("3 days");
        dateList.add("4 days");
        dateList.add("5 days");
        dateList.add("6 days");
        dateList.add("1 week");

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);
        switch (item.getItemId()) {
            case 101:
                //TODO: edit task code HERE
                return true;
            case 102:
                Snackbar.make(findViewById(R.id.rootId),"Deleted Task",Snackbar.LENGTH_LONG).show();
                adapter.RemoveItem(item.getGroupId());
                saveData();
                return true;
        }
        return true;
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

//                    Intent intent = new Intent(ToDoActivity.this,TasksActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("list",tasksList);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
                    recyclerView = findViewById(R.id.recycler_todo);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
                    recyclerView.setLayoutManager(layoutManager);

                    //tasksList = (ArrayList<Task>) getIntent().getExtras().getSerializable("list");

                    recyclerView.setAdapter(new LinearLayoutAdapter(tasksList));

                    saveData();
                    removeVi(checkBoxView);
                }

                break;
        }
    }

    private  void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(tasksList);
        editor.putString("task list",json);
        editor.apply();
    }

    private  void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list",null);
        Type type = new TypeToken<ArrayList<Task>>() {}.getType();
        tasksList = gson.fromJson(json, type);

        if (tasksList == null){
            tasksList = new ArrayList<>();
        }
    }

    private boolean checkIfValidAndRead() {
        //tasksList.clear();
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

        //ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, dateList);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.custom_spinner, dateList);
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