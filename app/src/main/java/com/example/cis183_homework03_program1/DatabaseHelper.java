package com.example.cis183_homework03_program1;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String database_name = "Students.db";
    private static final String students_table_name = "Students";
    private static final String majors_table_name = "Majors";

    public DatabaseHelper (Context c)
    {
        super(c, database_name, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //ANYTIME YOU MAKE EDITS HERE YOU MUST CHANGE THE VERSION NUMBER

        //Create major table in database
        //I think major table has to be first since students table has a foreign key that points to this table's primary key
        db.execSQL("CREATE TABLE " + majors_table_name + " (majorId integer primary key autoincrement not null, majorPrefix varchar(5), majorName varchar(50));");

        //Create student table in the database
        db.execSQL("CREATE TABLE " + students_table_name + " (username varchar(50) primary key, email varchar(50), fname varchar(50), lname varchar(50), age integer, gpa float, majorId integer, foreign key (majorId) references " + majors_table_name + " (majorId));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        //Drop old tables
        db.execSQL("DROP TABLE IF EXISTS " + majors_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + students_table_name + ";");

        //Call onCreate to make new tables
        onCreate(db);
    }

    public String getMajorsDbName()
    {
        return majors_table_name;
    }

    public String getStudentDbName()
    {
        return students_table_name;
    }

    public void initAllTables()
    {
        initMajors();
        initStudents();
    }

    public int countTableRecords(String tablename)
    {
        //Pull a readable version of the database
        SQLiteDatabase db = this.getReadableDatabase();

        //Count the number of entries for the table
        int numRows = (int) DatabaseUtils.queryNumEntries(db, tablename);

        //Close the database
        db.close();

        //Return the query data
        return numRows;
    }

    //Insert dummy data for Majors
    public void initMajors()
    {
        //Check if no data exists on the table for the database
        if (countTableRecords(majors_table_name) == 0)
        {
            //Pull a writeable version of the database
            SQLiteDatabase db = this.getWritableDatabase();

            //Insert the dummy data
            db.execSQL("INSERT INTO " + majors_table_name + "(majorPrefix, majorName) VALUES ('CIS', 'Computer Science');");
            db.execSQL("INSERT INTO " + majors_table_name + "(majorPrefix, majorName) VALUES ('BUS', 'Business');");
            db.execSQL("INSERT INTO " + majors_table_name + "(majorPrefix, majorName) VALUES ('CIS', 'Data Analytics');");
            db.execSQL("INSERT INTO " + majors_table_name + "(majorPrefix, majorName) VALUES ('ACCT', 'Accounting');");
            db.execSQL("INSERT INTO " + majors_table_name + "(majorPrefix, majorName) VALUES ('LAW', 'Paralegal');");
            db.execSQL("INSERT INTO " + majors_table_name + "(majorPrefix, majorName) VALUES ('CRJ', 'Criminal Justice');");

            //close the database
            db.close();
        }
    }

    //Insert dummy data for Students
    public void initStudents()
    {
        //Check if no data exists on the table for the database
        if (countTableRecords(students_table_name) == 0)
        {
            //Pull a writeable version of the database
            SQLiteDatabase db = this.getWritableDatabase();

            //Insert the dummy data
            db.execSQL("INSERT INTO " + majors_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('tmcbride2', 'tmcbride@my.monroeccc.edu', 'Tyler', 'McBride', '33', '3.1', '1');");

            //close the database
            db.close();
        }

    }

    //Check if the username exists
    public boolean usernameExists(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String checkUsername = "SELECT count(username) FROM " + students_table_name + "WHERE username = '" + username + "';";

        //Query
        Cursor cursor = db.rawQuery(checkUsername,null);

        //Move the cursor to the first returned value (first row)
        cursor.moveToFirst();

        //We are looking for the username column, which is 0 since it is in the first column
        //of the table
        int count = cursor.getInt(0);

        //Close the database
        db.close();

        //If the count is anything but 0 then the username exists....
        if (count != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
