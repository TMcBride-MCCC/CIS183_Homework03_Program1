package com.example.cis183_homework03_program1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddMajor extends AppCompatActivity
{
    //Create JAVA variables
    EditText et_j_addmajor_majorPrefix;
    EditText et_j_addmajor_majorName;
    Button btn_j_addmajor_add;
    Button btn_j_addmajor_back;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_major);

        //Connect JAVA variable to GUI
        et_j_addmajor_majorPrefix = findViewById(R.id.et_v_addmajor_majorPrefix);
        et_j_addmajor_majorName = findViewById(R.id.et_v_addmajor_majorName);
        btn_j_addmajor_add = findViewById(R.id.btn_v_addmajor_add);
        btn_j_addmajor_back = findViewById(R.id.btn_v_addmajor_back); //Do I actually need a back button??????
    }
}