package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "taskdb";
    private static final int DB_VERSION = 1;

    // Table and column names
    private static final String TABLE_NAME = "tasks";
    private static final String ID_COL = "id";
    private static final String TASK_NAME_COL = "taskName";
    private static final String DESCRIPTION_COL = "description";
    private static final String COMPLETED_COL = "completed";

    // Constructor
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TASK_NAME_COL + " TEXT, "
                + DESCRIPTION_COL + " TEXT, "
                + COMPLETED_COL + " INTEGER DEFAULT 0)"; // 0 = incomplete, 1 = complete

        db.execSQL(query);
    }

    public void addNewTask(String taskName, String description, boolean completed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TASK_NAME_COL, taskName);
        values.put(DESCRIPTION_COL, description);
        values.put(COMPLETED_COL, completed ? 1 : 0);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //  reading all the tasks
    public ArrayList<TasksModal> readTasks() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorTasks = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<TasksModal> tasksModalArrayList = new ArrayList<>();

        if (cursorTasks.moveToFirst()) {
            do {
                // Retrieve data from the cursor and add it to the ArrayList
                TasksModal task = new TasksModal(
                        cursorTasks.getString(1),
                        cursorTasks.getString(2),
                        cursorTasks.getInt(3) == 1
                );
                task.setId(cursorTasks.getInt(0));
                tasksModalArrayList.add(task);
            } while (cursorTasks.moveToNext());
        }

        cursorTasks.close();
        return tasksModalArrayList;
    }

    public void updateTask(String originalTaskName, String newTaskName, String newDescription) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TASK_NAME_COL, newTaskName);
        values.put(DESCRIPTION_COL, newDescription);

        db.update(TABLE_NAME, values, TASK_NAME_COL + "=?", new String[]{originalTaskName});
        db.close();
    }

    // Method to delete a task
    public void deleteTask(String taskName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, TASK_NAME_COL + "=?", new String[]{taskName});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if it exists and recreate it
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
