package com.example.cis183_homework03_program1;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    DatabaseHelper dbHelper;
    ListView lv_j_main_listOfStudents;
    static ArrayList<Student> listOfStudents = new ArrayList<>();
    HomeListAdapter adapter_home;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Connect JAVA to GUI
        lv_j_main_listOfStudents = findViewById(R.id.lv_v_main_students);

        //Make a new instance of the database
        dbHelper = new DatabaseHelper(this);

        //Initialize dummy data
        dbHelper.initAllTables();

        //Grab student data from the db
        listOfStudents = dbHelper.fillArrayList();
        //Fill the lv
        fillListView();
    }

    private void checkTableCounts()
    {
        Log.d("MAJORS COUNT: ",dbHelper.countTableRecords(dbHelper.getMajorsDbName()) + "");
        Log.d("MAJORS COUNT: ",dbHelper.countTableRecords(dbHelper.getStudentDbName()) + "");
    }


    private void fillListView()
    {
        adapter_home = new HomeListAdapter(this, listOfStudents);
        lv_j_main_listOfStudents.setAdapter(adapter_home);
    }
}