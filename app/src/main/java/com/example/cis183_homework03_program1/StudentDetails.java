package com.example.cis183_homework03_program1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StudentDetails extends AppCompatActivity
{
    TextView tv_j_details_username;
    TextView tv_j_details_name;
    TextView tv_j_details_email;
    TextView tv_j_details_age;
    TextView tv_j_details_gpa;
    TextView tv_j_details_major;
    Button btn_j_details_update;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_details);

        tv_j_details_username = findViewById(R.id.tv_v_details_username);
        tv_j_details_name = findViewById(R.id.tv_v_details_name);
        tv_j_details_email = findViewById(R.id.tv_v_details_email);
        tv_j_details_age = findViewById(R.id.tv_v_details_age);
        tv_j_details_gpa = findViewById(R.id.tv_v_details_gpa);
        tv_j_details_major = findViewById(R.id.tv_v_details_major);
        btn_j_details_update = findViewById(R.id.btn_v_details_update);

        updateButtonOnClickListener();

    }

    private void updateButtonOnClickListener()
    {
        //Go to update page
    }


}