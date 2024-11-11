package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskRVAdapter extends RecyclerView.Adapter<TaskRVAdapter.ViewHolder> {

    private ArrayList<TasksModal> taskModalArrayList;
    private Context context;

    // Constructor
    public TaskRVAdapter(ArrayList<TasksModal> taskModalArrayList, Context context) {
        this.taskModalArrayList = taskModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TasksModal modal = taskModalArrayList.get(position);
        holder.taskNameTV.setText(modal.getTaskName());
        holder.taskDescTV.setText(modal.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, UpdateTask.class);

                i.putExtra("taskName", modal.getTaskName());
                i.putExtra("description", modal.getDescription());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView taskNameTV, taskDescTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskNameTV = itemView.findViewById(R.id.idTVTaskName);
            taskDescTV = itemView.findViewById(R.id.idTVTaskDescription);
        }
    }
}
