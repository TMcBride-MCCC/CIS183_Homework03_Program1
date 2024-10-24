package com.example.cis183_homework03_program1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StudentSearch extends AppCompatActivity
{
    //Create Java variables
    EditText et_j_studentsearch_username;
    EditText et_j_studentsearch_fname;
    EditText et_j_studentsearch_lname;
    Spinner sp_j_studentsearch_major;
    RadioGroup rg_j_studentsearch_radioGroup;
    RadioButton rb_j_studentsearch_specificGPA;
    RadioButton rb_j_studentsearch_rangeGPA;
    TextView tv_j_studentsearch_specificGPA;
    TextView tv_j_studentsearch_minGPA;
    TextView tv_j_studentsearch_maxGPA;
    TextView tv_j_studentsearch_minMaxDash;
    EditText et_j_studentsearch_specificGPA;
    EditText et_j_studentsearch_minGPA;
    EditText et_j_studentsearch_maxGPA;
    Button btn_j_studentsearch_search;
    BottomNavigationView bnv_j_studentsearch_bottomNav;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_search);

        //Connect JAVA to GUI
        et_j_studentsearch_username = findViewById(R.id.et_v_studentsearch_username);
        et_j_studentsearch_fname = findViewById(R.id.et_v_studentsearch_fname);
        et_j_studentsearch_lname = findViewById(R.id.et_v_studentsearch_lname);
        sp_j_studentsearch_major = findViewById(R.id.sp_v_studentsearch_major);
        //Radio buttons
        rg_j_studentsearch_radioGroup = findViewById(R.id.rg_v_studentsearch_radioGroup);
        rb_j_studentsearch_specificGPA = findViewById(R.id.rb_v_studentsearch_specificGPA);
        rb_j_studentsearch_rangeGPA = findViewById(R.id.rb_v_studentsearch_rangeGPA);
        //Textviews
        tv_j_studentsearch_specificGPA = findViewById(R.id.tv_v_studentsearch_specificGPA);
        tv_j_studentsearch_minGPA = findViewById(R.id.tv_v_studentsearch_minGPA);
        tv_j_studentsearch_maxGPA = findViewById(R.id.tv_v_studentsearch_maxGPA);
        tv_j_studentsearch_minMaxDash = findViewById(R.id.tv_v_studentsearch_minMaxDash);
        //Edittexts
        et_j_studentsearch_specificGPA = findViewById(R.id.et_v_studentsearch_specificGPA);
        et_j_studentsearch_minGPA = findViewById(R.id.et_v_studentsearch_minGPA);
        et_j_studentsearch_maxGPA = findViewById(R.id.et_v_studentsearch_maxGPA);

        btn_j_studentsearch_search = findViewById(R.id.btn_v_studentsearch_search);
        bnv_j_studentsearch_bottomNav = findViewById(R.id.bnv_v_studentsearch_bottomNav);


        //Set the nav bar icon
        bnv_j_studentsearch_bottomNav.setSelectedItemId(R.id.nav_searchStudents);

        //Set the GPA tv to not visible
        tv_j_studentsearch_specificGPA.setVisibility(View.INVISIBLE);
        tv_j_studentsearch_minGPA.setVisibility(View.INVISIBLE);
        tv_j_studentsearch_maxGPA.setVisibility(View.INVISIBLE);
        tv_j_studentsearch_minMaxDash.setVisibility(View.INVISIBLE);

        //Set GPA et to not visible
        et_j_studentsearch_specificGPA.setVisibility(View.INVISIBLE);
        et_j_studentsearch_minGPA.setVisibility(View.INVISIBLE);
        et_j_studentsearch_maxGPA.setVisibility(View.INVISIBLE);

        bottomNavListener();
        radioButtonListener();

    }

    private void bottomNavListener()
    {
        bnv_j_studentsearch_bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int navItem = item.getItemId();

                if (navItem == R.id.nav_home)
                {
                    Log.d("NAV ", "Home button clicked");
                    startActivity(new Intent(StudentSearch.this,MainActivity.class));
                    return true;
                }
                else if (navItem == R.id.nav_addStudent)
                {
                    Log.d("NAV ", "Add Student button clicked");
                    startActivity(new Intent(StudentSearch.this,AddStudent.class));
                    return true;
                }
                else if (navItem == R.id.nav_addMajor)
                {
                    Log.d("NAV ", "Add Major button clicked");
                    startActivity(new Intent(StudentSearch.this,AddMajor.class));
                    return true;
                }
                else if (navItem == R.id.nav_searchStudents)
                {
                    Log.d("NAV ", "Search button clicked");
                    return true;
                }

                return false;
            }
        });
    }

    private void radioButtonListener()
    {
        rg_j_studentsearch_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                if (rb_j_studentsearch_specificGPA.isChecked())
                {
                    //Set the opposite to invisible
                    tv_j_studentsearch_minGPA.setVisibility(View.INVISIBLE);
                    tv_j_studentsearch_maxGPA.setVisibility(View.INVISIBLE);
                    et_j_studentsearch_minGPA.setVisibility(View.INVISIBLE);
                    et_j_studentsearch_maxGPA.setVisibility(View.INVISIBLE);
                    tv_j_studentsearch_minMaxDash.setVisibility(View.INVISIBLE);


                    //Set the corresponding items to visible
                    tv_j_studentsearch_specificGPA.setVisibility(View.VISIBLE);
                    et_j_studentsearch_specificGPA.setVisibility(View.VISIBLE);

                } else if (rb_j_studentsearch_rangeGPA.isChecked())
                {
                    //Set the opposite to invisible
                    tv_j_studentsearch_specificGPA.setVisibility(View.INVISIBLE);
                    et_j_studentsearch_specificGPA.setVisibility(View.INVISIBLE);

                    //Set the corresponding items to visible
                    tv_j_studentsearch_minGPA.setVisibility(View.VISIBLE);
                    tv_j_studentsearch_maxGPA.setVisibility(View.VISIBLE);
                    et_j_studentsearch_minGPA.setVisibility(View.VISIBLE);
                    et_j_studentsearch_maxGPA.setVisibility(View.VISIBLE);

                }
            }
        });
    }
}