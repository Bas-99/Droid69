package com.example.droid69;

import android.content.Context;
import android.graphics.Color;
import android.view.*;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LinearLayoutAdapter extends RecyclerView.Adapter<LinearLayoutAdapter.TaskView>  {

    ArrayList<Task> tasksList = new ArrayList<>();

    int mPosition;

    public LinearLayoutAdapter(ArrayList<Task> tasksList) {
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
    public void setPosition(int position){
        mPosition = position;
    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }



    public static class TaskView extends  RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {


        TextView textTask, textDate;
        CardView cardView;
        public TaskView(@NonNull View itemView) {

            super(itemView);

            textTask = (TextView) itemView.findViewById(R.id.checkBoxActiveTask);
            textDate = (TextView) itemView.findViewById(R.id.timerView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            cardView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            menu.add(getAdapterPosition(),101,0,"Edit Task");
            menu.add(getAdapterPosition(),102,1,"Delete Task");
        }
    }
    public void RemoveItem(int position){
        tasksList.remove(position);
        notifyDataSetChanged();
    }

}
