package com.example.cis183_homework03_program1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StudentDetails extends AppCompatActivity
{
    //Create Java variables
    TextView tv_j_details_username;
    TextView tv_j_details_name;
    TextView tv_j_details_email;
    TextView tv_j_details_age;
    TextView tv_j_details_gpa;
    TextView tv_j_details_major;
    Button btn_j_details_update;
    Button btn_j_details_back;
    BottomNavigationView bnv_j_details_bottomNav;

    //DatabaseHelper dbHelper;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_details);

        //Connect JAVA to GUI
        tv_j_details_username = findViewById(R.id.tv_v_details_username);
        tv_j_details_name = findViewById(R.id.tv_v_details_name);
        tv_j_details_email = findViewById(R.id.tv_v_details_email);
        tv_j_details_age = findViewById(R.id.tv_v_details_age);
        tv_j_details_gpa = findViewById(R.id.tv_v_details_gpa);
        tv_j_details_major = findViewById(R.id.tv_v_details_major);
        btn_j_details_update = findViewById(R.id.btn_v_details_update);
        btn_j_details_back = findViewById(R.id.btn_v_details_back);
        bnv_j_details_bottomNav = findViewById(R.id.bnv_v_details_bottomNav);

        //dbHelper = new DatabaseHelper(this);

        //Grab the Student info
        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("student");

        bottomNavListener();
        updateButtonOnClickListener();
        backButtonOnClickListener();

        //Fill in student info
        loadStudentData();

    }

    private void bottomNavListener()
    {
        bnv_j_details_bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int navItem = item.getItemId();

                if (navItem == R.id.nav_home)
                {
                    Log.d("NAV ", "Home button clicked");
                    startActivity(new Intent(StudentDetails.this,MainActivity.class));
                    return true;
                }
                else if (navItem == R.id.nav_addStudent)
                {
                    Log.d("NAV ", "Add Student button clicked");
                    startActivity(new Intent(StudentDetails.this,AddStudent.class));
                    return true;
                }
                else if (navItem == R.id.nav_addMajor)
                {
                    Log.d("NAV ", "Add Major button clicked");
                    startActivity(new Intent(StudentDetails.this,AddMajor.class));
                    return true;
                }
                else if (navItem == R.id.nav_searchStudents)
                {
                    Log.d("NAV ", "Search button clicked");
                    startActivity(new Intent(StudentDetails.this,StudentSearch.class));
                    return true;
                }

                return false;
            }
        });
    }

    private void updateButtonOnClickListener()
    {
        //Go to update page
    }

    private void backButtonOnClickListener()
    {
        btn_j_details_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(StudentDetails.this, MainActivity.class));
            }
        });
    }

    private void loadStudentData()
    {
        String username = student.getUsername();
        String fname = student.getfName();
        String lname = student.getlName();
        String email = student.getEmail();
        int age = student.getAge();;
        float gpa = student.getGpa();;
        int major = student.getMajorId();

        tv_j_details_username.setText(username);
        tv_j_details_name.setText(fname + " " + lname);
        tv_j_details_email.setText(email);
        tv_j_details_age.setText(String.valueOf(age));
        tv_j_details_gpa.setText(String.valueOf(gpa));
        tv_j_details_major.setText(String.valueOf(major));
    }


}