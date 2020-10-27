package com.example.droid69;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskView> {

    ArrayList<Task> tasksList = new ArrayList<>();

    public TaskAdapter(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    @NonNull
    @Override
    public TaskView onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_task,parent,false);


        return new TaskView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TaskView holder, int position) {

        Task task = tasksList.get(position);
        holder.textTask.setText(task.getTask());
        holder.textDate.setText(task.getDate());
    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    public class TaskView extends  RecyclerView.ViewHolder{


        TextView textTask, textDate;
        public TaskView(@NonNull View itemView) {

            super(itemView);

            textTask = (TextView) itemView.findViewById(R.id.checkBoxActiveTask);
            textDate = (TextView) itemView.findViewById(R.id.timerView);

        }

    }

}
