package com.example.cis183_homework03_program1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddStudent extends AppCompatActivity
{
    //Create Java variables
    EditText et_j_addstudent_username;
    EditText et_j_addstudent_fname;
    EditText et_j_addstudent_lname;
    EditText et_j_addstudent_email;
    EditText et_j_addstudent_age;
    EditText et_j_addstudent_gpa;
    Spinner sp_j_addstudent_major;
    BottomNavigationView bnv_j_addstudent_bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);

        //Connect JAVA to GUI
        et_j_addstudent_username = findViewById(R.id.et_v_addstudent_username);
        et_j_addstudent_fname = findViewById(R.id.et_v_addstudent_fname);
        et_j_addstudent_lname = findViewById(R.id.et_v_addstudent_lname);
        et_j_addstudent_email = findViewById(R.id.et_v_addstudent_email);
        et_j_addstudent_age = findViewById(R.id.et_v_addstudent_age);
        et_j_addstudent_gpa = findViewById(R.id.et_v_addstudent_gpa);
        sp_j_addstudent_major = findViewById(R.id.sp_v_addstudent_major);
        bnv_j_addstudent_bottomNav = findViewById(R.id.bnv_v_addstudent_bottomNav);

        //Set the nav bar icon
        bnv_j_addstudent_bottomNav.setSelectedItemId(R.id.nav_addStudent);

        bottomNavListener();

    }

    private void bottomNavListener()
    {
        bnv_j_addstudent_bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int navItem = item.getItemId();

                if (navItem == R.id.nav_home)
                {
                    Log.d("NAV ", "Home button clicked");
                    startActivity(new Intent(AddStudent.this,MainActivity.class));
                }
                else if (navItem == R.id.nav_addStudent)
                {
                    Log.d("NAV ", "Add Student button clicked");
                    startActivity(new Intent(AddStudent.this,AddStudent.class));

                }
                else if (navItem == R.id.nav_addMajor)
                {
                    Log.d("NAV ", "Add Major button clicked");
                    startActivity(new Intent(AddStudent.this,AddMajor.class));

                }
                else if (navItem == R.id.nav_searchStudents)
                {
                    Log.d("NAV ", "Search button clicked");
                    startActivity(new Intent(AddStudent.this,StudentSearch.class));

                }

                return false;
            }
        });
    }
}