package com.example.todolist;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button addTask, viewTasks;
    private EditText taskName, description;
    private CheckBox taskCompleted;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        addTask = findViewById(R.id.addTask);
        viewTasks = findViewById(R.id.viewTasks);
        taskName = findViewById(R.id.taskName);
        description = findViewById(R.id.description);
        taskCompleted = findViewById(R.id.taskCompleted); // Field to store completion status

        // Initializing the DBHandler
        dbHandler = new DBHandler(MainActivity.this);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get user input
                String taskNameText = taskName.getText().toString();
                String descriptionText = description.getText().toString();

                // Validate input fields
                if (taskNameText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Fill Out The Task Name", Toast.LENGTH_SHORT).show();
                    return;
                }


                dbHandler.addNewTask(taskNameText, descriptionText, taskCompleted.isChecked());

                Toast.makeText(MainActivity.this, "Task has been added.", Toast.LENGTH_SHORT).show();
                taskName.setText("");
                description.setText("");
            }
        });

        viewTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewTasks.class);
                startActivity(i);
            }
        });
    }
}
