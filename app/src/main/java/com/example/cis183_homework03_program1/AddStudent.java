package com.example.cis183_homework03_program1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    ArrayAdapter<String> adapter;
    Button btn_j_addstudent_enroll;
    BottomNavigationView bnv_j_addstudent_bottomNav;
    DatabaseHelper db;

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
        btn_j_addstudent_enroll = findViewById(R.id.btn_v_addstudent_enroll);
        bnv_j_addstudent_bottomNav = findViewById(R.id.bnv_v_addstudent_bottomNav);
        db = new DatabaseHelper(this);

        //Set the nav bar icon
        bnv_j_addstudent_bottomNav.setSelectedItemId(R.id.nav_addStudent);

        //Fill the spinner
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, db.getAllMajorNames()); //need to add db.getMajors
        sp_j_addstudent_major.setAdapter(adapter);

        bottomNavListener();
        enrollButtonClickListener();

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
                    return true;
                }
                else if (navItem == R.id.nav_addStudent)
                {
                    Log.d("NAV ", "Add Student button clicked");
                    return true;
                }
                else if (navItem == R.id.nav_addMajor)
                {
                    Log.d("NAV ", "Add Major button clicked");
                    startActivity(new Intent(AddStudent.this,AddMajor.class));
                    return true;
                }
                else if (navItem == R.id.nav_searchStudents)
                {
                    Log.d("NAV ", "Search button clicked");
                    startActivity(new Intent(AddStudent.this,StudentSearch.class));
                    return true;
                }

                return false;
            }
        });
    }

    private void enrollButtonClickListener()
    {
        btn_j_addstudent_enroll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Check if username exists(bool)

                //Grab info from textboxes
                String username = et_j_addstudent_username.getText().toString();
                String fname = et_j_addstudent_fname.getText().toString();
                String lname = et_j_addstudent_lname.getText().toString();
                String email = et_j_addstudent_email.getText().toString();
                int age = Integer.parseInt(et_j_addstudent_age.getText().toString());
                float gpa = Float.parseFloat(et_j_addstudent_gpa.getText().toString());
                String majorName = sp_j_addstudent_major.getTransitionName();


                //Convert majorName to MajorId

                //If username does not exists
                //if (!username.isEmpty() && !fname.isEmpty() && !lname.isEmpty() && !email.isEmpty() && !age.isEmpty && !gpa.isEmpty() && !majorName.isEmpty())
                //{

                //}

            }
        });
    }
}