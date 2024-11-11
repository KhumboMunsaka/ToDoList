package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateTask extends AppCompatActivity {

    // Variables for EditText, Button, DBHandler, and Strings for task data
    private EditText taskNameEdt, taskDescriptionEdt;
    private Button updateTaskBtn, deleteTaskBtn;
    private DBHandler dbHandler;
    private String taskName, taskDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        // Initialize EditText and Button views
        taskNameEdt = findViewById(R.id.idEdtTaskName);
        taskDescriptionEdt = findViewById(R.id.idEdtTaskDescription);
        updateTaskBtn = findViewById(R.id.idBtnUpdateTask);
        deleteTaskBtn = findViewById(R.id.deleteTask);

        // Initialize the DBHandler
        dbHandler = new DBHandler(UpdateTask.this);

        // Retrieve the task data passed from the adapter
        taskName = getIntent().getStringExtra("taskName");
        taskDescription = getIntent().getStringExtra("description");

        // Set retrieved data to the EditText fields
        taskNameEdt.setText(taskName);
        taskDescriptionEdt.setText(taskDescription);

        // Set click listener for the update button
        updateTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the DBHandler to update the task with new data
                dbHandler.updateTask(taskName,
                        taskNameEdt.getText().toString(),
                        taskDescriptionEdt.getText().toString());

                // Display a success message
                Toast.makeText(UpdateTask.this, "Task Updated.", Toast.LENGTH_SHORT).show();

                // Redirect back to the MainActivity
                Intent intent = new Intent(UpdateTask.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for the delete button
        deleteTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the DBHandler to delete the task
                dbHandler.deleteTask(taskName);

                // Display a success message
                Toast.makeText(UpdateTask.this, "Task Deleted.", Toast.LENGTH_SHORT).show();

                // Redirect back to the MainActivity
                Intent intent = new Intent(UpdateTask.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
