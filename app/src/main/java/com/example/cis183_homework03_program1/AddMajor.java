package com.example.cis183_homework03_program1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    TextView tv_j_addmajor_majorError;
    BottomNavigationView bnv_j_addmajor_bottomNav;
    DatabaseHelper dbHelper;
    boolean majorExists = false;


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
        tv_j_addmajor_majorError = findViewById(R.id.tv_v_addmajor_majorError);
        bnv_j_addmajor_bottomNav = findViewById(R.id.bnv_v_addmajor_bottomNav);
        dbHelper = new DatabaseHelper(this);

        //Set the nav bar icon
        bnv_j_addmajor_bottomNav.setSelectedItemId(R.id.nav_addMajor);

        bottomNavListener();
        checkIfMajorExists();
        addMajorButtonOnClickListener();

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
                    return true;
                } else if (navItem == R.id.nav_addStudent)
                {
                    Log.d("NAV ", "Add Student button clicked");
                    startActivity(new Intent(AddMajor.this, AddStudent.class));
                    return true;
                } else if (navItem == R.id.nav_addMajor)
                {
                    Log.d("NAV ", "Add Major button clicked");
                    return true;
                } else if (navItem == R.id.nav_searchStudents)
                {
                    Log.d("NAV ", "Search button clicked");
                    startActivity(new Intent(AddMajor.this, StudentSearch.class));
                    return true;
                }

                return false;
            }
        });
    }

    public void checkIfMajorExists()
    {
        et_j_addmajor_majorName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                majorExists = dbHelper.majorExists(et_j_addmajor_majorName.getText().toString());

                if (!majorExists)
                {
                    btn_j_addmajor_add.setEnabled(true);
                    tv_j_addmajor_majorError.setText("");
                }
                else
                {
                    btn_j_addmajor_add.setEnabled(false);
                    tv_j_addmajor_majorError.setText("Error: This is already a major. Please check the drop down again.");
                    tv_j_addmajor_majorError.setTextColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
    }

    private void addMajorButtonOnClickListener()
    {
        btn_j_addmajor_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Grab info from textboxes
                String majorPrefix = et_j_addmajor_majorPrefix.getText().toString();
                String majorName = et_j_addmajor_majorName.getText().toString();

/*                //If any field is empty disable button
                if (majorPrefix.isEmpty() || majorName.isEmpty())
                {
                    Log.d("ERROR:", "A field is empty");
                    btn_j_addmajor_add.setEnabled(false);
                }*/

                //If all fields are filled out make a new Student
                if (!majorPrefix.isEmpty() && !majorName.isEmpty())
                {
/*                    //If all fields are filled out enable button
                    btn_j_addmajor_add.setEnabled(true);*/

                    Major majorToAdd = new Major();

                    majorToAdd.setMajorPrefix(majorPrefix);
                    majorToAdd.setMajorName(majorName);

                    dbHelper.addMajorToDb(majorToAdd);
                    Log.d("Major ADDED: ", "" + majorToAdd.getMajorName() + " was added to the db");

                    //Clear all et
                    et_j_addmajor_majorPrefix.getText().clear();
                    et_j_addmajor_majorName.getText().clear();
                }
            }
        });
    }
}