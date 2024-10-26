package com.example.cis183_homework03_program1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class UpdateStudent extends AppCompatActivity
{
    //Create Java variables
    static ArrayList<Major> listOfMajors = new ArrayList<>();
    TextView tv_j_updatestudent_username;
    EditText et_j_updatestudent_fname;
    EditText et_j_updatestudent_lname;
    EditText et_j_updatestudent_email;
    EditText et_j_updatestudent_age;
    EditText et_j_updatestudent_gpa;
    Spinner sp_j_updatestudent_major;
    ArrayAdapter<String> adapter;
    Button btn_j_updatestudent_update;
    BottomNavigationView bnv_j_updatestudent_bottomNav;
    DatabaseHelper dbHelper;
    Student student;
    Intent intent_j_updatestudent_StudentDetails;
    boolean usernameTaken = false;
    TextView tv_j_updatestudent_usernameExists;
    //TextView tv_j_updatestudent_fillFieldsError;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_student);

        //Connect JAVA to GUI
        tv_j_updatestudent_username = findViewById(R.id.tv_v_updatestudent_username);
        et_j_updatestudent_fname = findViewById(R.id.et_v_updatestudent_fname);
        et_j_updatestudent_lname = findViewById(R.id.et_v_updatestudent_lname);
        et_j_updatestudent_email = findViewById(R.id.et_v_updatestudent_email);
        et_j_updatestudent_age = findViewById(R.id.et_v_updatestudent_age);
        et_j_updatestudent_gpa = findViewById(R.id.et_v_updatestudent_gpa);
        sp_j_updatestudent_major = findViewById(R.id.sp_v_updatestudent_major);
        btn_j_updatestudent_update = findViewById(R.id.btn_v_updatestudent_update);
        bnv_j_updatestudent_bottomNav = findViewById(R.id.bnv_v_updatestudent_bottomNav);
        dbHelper = new DatabaseHelper(this);
        tv_j_updatestudent_usernameExists = findViewById(R.id.tv_v_updatestudent_usernameExists);
        //tv_j_updatestudent_fillFieldsError = findViewById(R.id.tv_v_updatestudent_fillFieldsError);

        //Grab the Student info
        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("student");
        if (student == null)
        {
            Log.e("UpdateStudent", "Student object is null");
        }
        else
        {
            Log.d("UpdateStudent", "Student retrieved: " + student.getUsername());
            fillStudentDataOnLoad();
        }

        //Fill the spinner
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dbHelper.getAllMajorNames());
        sp_j_updatestudent_major.setAdapter(adapter);

        //Set button to not enabled
        //btn_j_updatestudent_update.setEnabled(false);

        bottomNavListener();
        updateButtonClickListener();
    }

    private void bottomNavListener()
    {
        bnv_j_updatestudent_bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int navItem = item.getItemId();

                if (navItem == R.id.nav_home)
                {
                    Log.d("NAV ", "Home button clicked");
                    startActivity(new Intent(UpdateStudent.this,MainActivity.class));
                    return true;
                }
                else if (navItem == R.id.nav_addStudent)
                {
                    Log.d("NAV ", "Add Student button clicked");
                    startActivity(new Intent(UpdateStudent.this,AddStudent.class));
                    return true;
                }
                else if (navItem == R.id.nav_addMajor)
                {
                    Log.d("NAV ", "Add Major button clicked");
                    startActivity(new Intent(UpdateStudent.this,AddMajor.class));
                    return true;
                }
                else if (navItem == R.id.nav_searchStudents)
                {
                    Log.d("NAV ", "Search button clicked");
                    startActivity(new Intent(UpdateStudent.this,StudentSearch.class));
                    return true;
                }

                return false;
            }
        });
    }

    private void fillStudentDataOnLoad()
    {
        String username = student.getUsername();
        String fname = student.getfName();
        String lname = student.getlName();
        String email = student.getEmail();
        int age = student.getAge();;
        float gpa = student.getGpa();;
        //This pull majorId
        int majorId = student.getMajorId();
        //This uses majorId to search the majors table for a corresponding majorName
        String majorName = dbHelper.getMajorName(majorId);

        tv_j_updatestudent_username.setText(username);
        et_j_updatestudent_fname.setText(fname);
        et_j_updatestudent_lname.setText(lname);
        et_j_updatestudent_email.setText(email);
        et_j_updatestudent_age.setText(String.valueOf(age));
        et_j_updatestudent_gpa.setText(String.valueOf(gpa));
    }

    private void updateButtonClickListener()
    {
        btn_j_updatestudent_update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Grab info from textboxes
                String fname = et_j_updatestudent_fname.getText().toString();
                String lname = et_j_updatestudent_lname.getText().toString();
                String email = et_j_updatestudent_email.getText().toString();
                String ageCheck = et_j_updatestudent_age.getText().toString();
                String gpaCheck = et_j_updatestudent_gpa.getText().toString();
                String majorName = sp_j_updatestudent_major.getSelectedItem().toString();
                int age = 0;
                float gpa = 0f;

                //Convert the majorName to the corresponding majorId
                int majorId = dbHelper.getMajorId(majorName);

                //If any field is empty disable button
                if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || ageCheck.isEmpty() || gpaCheck.isEmpty() || majorName.isEmpty())
                {
                    Log.d("ERROR:", "A field is empty");
                    btn_j_updatestudent_update.setEnabled(false);
                    return;
                }
                else
                {
                    btn_j_updatestudent_update.setEnabled(true);
                }

                //Get the int number from string
                try
                {
                    age = Integer.parseInt(et_j_updatestudent_age.getText().toString());
                }
                catch (NumberFormatException e)
                {
                    Log.d("ERROR:", "Age must be a number");
                    return;
                }

                //Get the float number from string
                try
                {
                    gpa = Float.parseFloat(et_j_updatestudent_gpa.getText().toString());
                }
                catch (NumberFormatException e)
                {
                    Log.d("ERROR:", "GPA must be a number");
                    return;
                }

                //If all fields are filled out make a new Student
                if (!fname.isEmpty() && !lname.isEmpty() && !email.isEmpty() && !ageCheck.isEmpty() && !gpaCheck.isEmpty() && !majorName.isEmpty())
                {
                    //If all fields are filled out enable button
                    btn_j_updatestudent_update.setEnabled(true);

                    Student studentToChange = new Student();

                    studentToChange.setUsername(student.getUsername());
                    studentToChange.setEmail(email);
                    studentToChange.setfName(fname);
                    studentToChange.setlName(lname);
                    studentToChange.setAge(age);
                    studentToChange.setGpa(gpa);
                    studentToChange.setMajorId(majorId);

                    //Update the student in the db
                    dbHelper.updateStudentInDb(studentToChange);
                    //Update the student in the list
                    StudentList.getInstance().updateStudent(studentToChange);
                    Log.d("USER CHANGED: ", "" + studentToChange.getUsername() + " details changed");

                    startActivity(new Intent(UpdateStudent.this, MainActivity.class));
                }
            }
        });
    }
}