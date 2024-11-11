package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewTasks extends AppCompatActivity {

    private ArrayList<TasksModal> tasksModalArrayList;
    private DBHandler dbHandler;
    private TaskRVAdapter tasksRVAdapter;
    private RecyclerView tasksRV;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);

        tasksModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewTasks.this);

        tasksModalArrayList = dbHandler.readTasks();

        tasksRVAdapter = new TaskRVAdapter(tasksModalArrayList, ViewTasks.this);
        tasksRV = findViewById(R.id.idRVTasks);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewTasks.this, RecyclerView.VERTICAL, false);
        tasksRV.setLayoutManager(linearLayoutManager);

        tasksRV.setAdapter(tasksRVAdapter);
        //button to go back to main activity
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate back to MainActivity
                //from Caleb Curry on youtube
                Intent intent = new Intent(ViewTasks.this, MainActivity.class);
                startActivity(intent);
//                finish();
            }
        });
    }



}
