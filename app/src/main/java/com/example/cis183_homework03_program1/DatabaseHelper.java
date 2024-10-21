package com.example.cis183_homework03_program1;

import android.content.Context;
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
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
}
