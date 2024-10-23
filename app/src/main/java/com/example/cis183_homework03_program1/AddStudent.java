package com.example.cis183_homework03_program1;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

    }
}