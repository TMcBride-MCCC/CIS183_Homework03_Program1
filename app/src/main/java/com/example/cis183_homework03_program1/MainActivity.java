package com.example.cis183_homework03_program1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    DatabaseHelper dbHelper;
    ListView lv_j_main_listOfStudents;
    static ArrayList<Student> listOfStudents = new ArrayList<>();
    HomeListAdapter adapter_home;
    BottomNavigationView bnv_j_main_bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Connect JAVA to GUI
        lv_j_main_listOfStudents = findViewById(R.id.lv_v_main_students);
        bnv_j_main_bottomNav = findViewById(R.id.bottomNavigationView);

        //Make a new instance of the database
        dbHelper = new DatabaseHelper(this);

        //Initialize dummy data
        dbHelper.initAllTables();

        //Grab student data from the db
        listOfStudents = dbHelper.fillArrayList();
        //Fill the lv
        fillListView();
        //Call function for navigation
        bottomNavListener();
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

    private void bottomNavListener()
    {
        bnv_j_main_bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int navItem = item.getItemId();

                if (navItem == R.id.nav_home)
                {
                    Log.d("NAV ", "Home button clicked");
                }
                else if (navItem == R.id.nav_addStudent)
                {
                    Log.d("NAV ", "Add Student button clicked");
                    startActivity(new Intent(MainActivity.this,AddStudent.class));
                }
                else if (navItem == R.id.nav_addMajor)
                {
                    Log.d("NAV ", "Add Major button clicked");
                    startActivity(new Intent(MainActivity.this,AddMajor.class));
                }
                else if (navItem == R.id.nav_searchStudents)
                {
                    Log.d("NAV ", "Search button clicked");
                }

                return false;
            }
        });
    }
}