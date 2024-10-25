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
    DatabaseHelper dbHelper;
    boolean usernameTaken = false;
    TextView tv_j_addstudent_usernameExists;
    TextView tv_j_addstudent_fillFieldsError;

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
        dbHelper = new DatabaseHelper(this);
        tv_j_addstudent_usernameExists = findViewById(R.id.tv_v_addstudent_usernameExists);
        tv_j_addstudent_fillFieldsError = findViewById(R.id.tv_v_addstudent_fillFieldsError);

        //Set the nav bar icon
        bnv_j_addstudent_bottomNav.setSelectedItemId(R.id.nav_addStudent);

        //Fill the spinner
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dbHelper.getAllMajorNames());
        sp_j_addstudent_major.setAdapter(adapter);

        //Set button to not enabled
        btn_j_addstudent_enroll.setEnabled(false);

        bottomNavListener();
        checkIfUsernameExists();
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

    public void checkIfUsernameExists()
    {
        et_j_addstudent_username.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                usernameTaken = dbHelper.usernameExists(et_j_addstudent_username.getText().toString());

                if (!usernameTaken)
                {
                    btn_j_addstudent_enroll.setEnabled(true);
                    tv_j_addstudent_usernameExists.setText("");
                }
                else
                {
                    btn_j_addstudent_enroll.setEnabled(false);
                    tv_j_addstudent_usernameExists.setText("Error: Username taken");
                    tv_j_addstudent_usernameExists.setTextColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

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
                //Grab info from textboxes
                String username = et_j_addstudent_username.getText().toString();
                String fname = et_j_addstudent_fname.getText().toString();
                String lname = et_j_addstudent_lname.getText().toString();
                String email = et_j_addstudent_email.getText().toString();
                String ageCheck = et_j_addstudent_age.getText().toString();
                String gpaCheck = et_j_addstudent_gpa.getText().toString();
                String majorName = sp_j_addstudent_major.getSelectedItem().toString();
                int age = 0;
                float gpa = 0f;

                //Convert the majorName to the corresponding majorId
                int majorId = dbHelper.getMajorId(majorName);

                //If any field is empty disable button
                if (username.isEmpty() || fname.isEmpty() || lname.isEmpty() || email.isEmpty() || ageCheck.isEmpty() || gpaCheck.isEmpty() || majorName.isEmpty())
                {
                    Log.d("ERROR:", "A field is empty");
                    btn_j_addstudent_enroll.setEnabled(false);
                    return;
                }
                else
                {
                    btn_j_addstudent_enroll.setEnabled(true);
                }

                //Get the int number from string
                try
                {
                    age = Integer.parseInt(et_j_addstudent_age.getText().toString());
                }
                catch (NumberFormatException e)
                {
                    Log.d("ERROR:", "Age must be a number");
                    return;
                }

                //Get the float number from string
                try
                {
                    gpa = Float.parseFloat(et_j_addstudent_gpa.getText().toString());
                }
                catch (NumberFormatException e)
                {
                    Log.d("ERROR:", "GPA must be a number");
                    return;
                }

                //If all fields are filled out make a new Student
                if (!username.isEmpty() && !fname.isEmpty() && !lname.isEmpty() && !email.isEmpty() && !ageCheck.isEmpty() && !gpaCheck.isEmpty() && !majorName.isEmpty())
                {
                    //If all fields are filled out enable button
                    btn_j_addstudent_enroll.setEnabled(true);

                    Student studentToAdd = new Student();

                    studentToAdd.setUsername(username);
                    studentToAdd.setEmail(email);
                    studentToAdd.setfName(fname);
                    studentToAdd.setlName(lname);
                    studentToAdd.setAge(age);
                    studentToAdd.setGpa(gpa);
                    studentToAdd.setMajorId(majorId);

                    dbHelper.addStudentToDb(studentToAdd);
                    Log.d("USER ADDED: ", "" + studentToAdd.getUsername() + " was added to the db");

                    //Clear all et
                    et_j_addstudent_username.getText().clear();
                    et_j_addstudent_fname.getText().clear();
                    et_j_addstudent_lname.getText().clear();
                    et_j_addstudent_email.getText().clear();
                    et_j_addstudent_age.getText().clear();
                    et_j_addstudent_gpa.getText().clear();
                }
            }
        });
    }
}