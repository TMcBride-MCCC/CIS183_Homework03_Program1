package com.example.cis183_homework03_program1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddMajor extends AppCompatActivity
{
    //Create JAVA variables
    EditText et_j_addmajor_majorPrefix;
    EditText et_j_addmajor_majorName;
    Button btn_j_addmajor_add;
    Button btn_j_addmajor_back;
    BottomNavigationView bnv_j_addmajor_bottomNav;


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
        bnv_j_addmajor_bottomNav = findViewById(R.id.bnv_v_addmajor_bottomNav);

        //Set the nav bar icon
        bnv_j_addmajor_bottomNav.setSelectedItemId(R.id.nav_addMajor);

        bottomNavListener();

    }

    private void bottomNavListener()
    {
        bnv_j_addmajor_bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int navItem = item.getItemId();

                if (navItem == R.id.nav_home)
                {
                    Log.d("NAV ", "Home button clicked");
                    startActivity(new Intent(AddMajor.this, MainActivity.class));
                } else if (navItem == R.id.nav_addStudent)
                {
                    Log.d("NAV ", "Add Student button clicked");
                    startActivity(new Intent(AddMajor.this, AddStudent.class));

                } else if (navItem == R.id.nav_addMajor)
                {
                    Log.d("NAV ", "Add Major button clicked");
                    startActivity(new Intent(AddMajor.this, AddMajor.class));

                } else if (navItem == R.id.nav_searchStudents)
                {
                    Log.d("NAV ", "Search button clicked");
                    startActivity(new Intent(AddMajor.this, StudentSearch.class));

                }

                return false;
            }
        });
    }
}