package com.example.cis183_homework03_program1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    DatabaseHelper dbHelper;
    ListView lv_j_main_listOfStudents;
    static ArrayList<Major> listOfMajors = new ArrayList<>();
    HomeListAdapter adapter_home;
    BottomNavigationView bnv_j_main_bottomNav;
    Intent intent_j_main_StudentDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Connect JAVA to GUI
        lv_j_main_listOfStudents = findViewById(R.id.lv_v_main_students);
        bnv_j_main_bottomNav = findViewById(R.id.bnv_v_main_bottomNav);

        //Set the nav bar icon
        bnv_j_main_bottomNav.setSelectedItemId(R.id.nav_home);

        //Make a new instance of the database
        dbHelper = new DatabaseHelper(this);

        //Initialize dummy data
        dbHelper.initAllTables();

        //Grab student data from the dbHelper
        StudentList.getInstance().initStudentList(dbHelper.fillStudentArrayList());
        //Grab major data from the dbHelper
        listOfMajors = dbHelper.fillMajorArrayList();

        //Fill the lv
        fillListView();

        //Call function for navigation
        bottomNavListener();

        //Check that thew tables are filled
        checkTableCounts();

        //Listen for lv click
        lvOnItemClickListener();
    }

    private void checkTableCounts()
    {
        Log.d("MAJORS COUNT: ",dbHelper.countTableRecords(dbHelper.getMajorsDbName()) + "");
        Log.d("STUDENT COUNT: ",dbHelper.countTableRecords(dbHelper.getStudentDbName()) + "");
    }

    private void fillListView()
    {
        adapter_home = new HomeListAdapter(this, StudentList.getInstance().getStudents());
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
                    return true;
                }
                else if (navItem == R.id.nav_addStudent)
                {
                    Log.d("NAV ", "Add Student button clicked");
                    startActivity(new Intent(MainActivity.this,AddStudent.class));
                    return true;
                }
                else if (navItem == R.id.nav_addMajor)
                {
                    Log.d("NAV ", "Add Major button clicked");
                    startActivity(new Intent(MainActivity.this,AddMajor.class));
                    return true;
                }
                else if (navItem == R.id.nav_searchStudents)
                {
                    Log.d("NAV ", "Search button clicked");
                    startActivity(new Intent(MainActivity.this,StudentSearch.class));
                    return true;
                }

                return false;
            }
        });
    }

    private void lvOnItemClickListener()
    {
        lv_j_main_listOfStudents.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Student selectedStudent = StudentList.getInstance().getStudents().get(i);
                intent_j_main_StudentDetails = new Intent(MainActivity.this,StudentDetails.class);
                intent_j_main_StudentDetails.putExtra("sentFrom", "MainActivity");
                intent_j_main_StudentDetails.putExtra("studentFromMain", selectedStudent);
                startActivity(intent_j_main_StudentDetails);
                Log.d("Student: ", "Student: " + selectedStudent);
            }
        });

        lv_j_main_listOfStudents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                //Remove student from dbHelper
                Student selectedStudent = StudentList.getInstance().getStudents().get(i);
                dbHelper.deleteStudentFromDb(selectedStudent);

                //Remove student from list
                StudentList.getInstance().getStudents().remove(i);
                adapter_home.notifyDataSetChanged();

                return true;
            }
        });

    }
}