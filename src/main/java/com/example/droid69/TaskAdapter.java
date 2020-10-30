package com.example.droid69;

import android.content.Intent;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskView>  {

    ArrayList<Task> tasksList = new ArrayList<>();

    int mPosition;

    public TaskAdapter(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    @NonNull
    @Override
    public TaskView onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_screen_row,parent,false);

        return new TaskView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TaskView holder, int position) {

        Task task = tasksList.get(position);
        if (task.getDate().equals("Today")){
            holder.textDate.setText("0");
        } else if(task.getDate().equals("Next day")){
            holder.textDate.setText("1");
        }else if(task.getDate().equals("2 days")){
            holder.textDate.setText("2");
        }else if(task.getDate().equals("3 days")){
            holder.textDate.setText("3");
        }else if(task.getDate().equals("4 days")){
            holder.textDate.setText("4");
        }else if(task.getDate().equals("5 days")){
            holder.textDate.setText("5");
        }else if(task.getDate().equals("6 days")){
            holder.textDate.setText("6");
        }else if(task.getDate().equals("1 week")){
            holder.textDate.setText("7");
        }
        holder.textTask.setText(task.getTask());


    }
    public void setPosition(int position){
        mPosition = position;
    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }



    public static class TaskView extends  RecyclerView.ViewHolder{


        TextView textTask, textDate;
        CardView cardView;
        public TaskView(@NonNull View itemView) {

            super(itemView);

            textTask = (TextView) itemView.findViewById(R.id.textViewActiveTask);
            textDate = (TextView) itemView.findViewById(R.id.timerViewHomeScreen);
            cardView = (CardView) itemView.findViewById(R.id.cardViewHomeScreen);

        }

    }



}

