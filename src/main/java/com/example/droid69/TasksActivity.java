package com.example.droid69;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {

    RecyclerView recyclerTasks;

    ArrayList<Task> tasksList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        recyclerTasks = findViewById(R.id.recycler_tasks);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerTasks.setLayoutManager(layoutManager);

        tasksList = (ArrayList<Task>) getIntent().getExtras().getSerializable("list");

        recyclerTasks.setAdapter(new TaskAdapter(tasksList));

    }

}
