package com.example.cis183_homework03_program1;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('tmcbride2', 'tmcbride@my.monroeccc.edu', 'Tyler', 'McBride', '33', '3.1', '1');");

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

    //Pull Student Data
    public void getAllStudentDataGivenUsername(String username)
    {
        Student student = null;

        if (usernameExists(username))
        {
            student = new Student();

            //Query to get info
            String selectQuery = "SELECT * FROM " + students_table_name + " WHERE username = '" + username + "';";

            //pull a readable database
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(selectQuery,null);

            if (cursor != null)
            {
                //Move the cursor to the first row returned
                cursor.moveToFirst();

                //Set the info
                student.setUsername(cursor.getString(0));
                student.setEmail(cursor.getString(1));
                student.setfName(cursor.getString(2));
                student.setlName(cursor.getString(3));
                student.setAge(cursor.getInt(4));
                student.setGpa(cursor.getFloat(5));
                student.setMajorId(cursor.getInt(6));
            }
            else
            {
                Log.d("ERROR: ","The user does not exist");
            }
        }
    }

    public ArrayList<Student> fillStudentArrayList()
    {
        //Make a new arraylist
        ArrayList<Student> listOfStudents = new ArrayList<>();

        //Get a readable database copy
        SQLiteDatabase db = this.getReadableDatabase();

        //Query
        String selectQuery = "SELECT * FROM " + students_table_name;
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor !=null)
        {
            //Move the cursor
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++)
            {
                //Make a new student memory chunk
                Student student = new Student();

                //Set the info
                student.setUsername(cursor.getString(0));
                student.setEmail(cursor.getString(1));
                student.setfName(cursor.getString(2));
                student.setlName(cursor.getString(3));
                student.setAge(cursor.getInt(4));
                student.setGpa(cursor.getFloat(5));
                student.setMajorId(cursor.getInt(6));

                //Add the student
                listOfStudents.add(student);

                //Move the cursor
                cursor.moveToNext();
            }
        }
        //Close the database
        db.close();

        return listOfStudents;
    }

    public boolean majorIdExists(int majorId)
    {
        //Get a readable db version
        SQLiteDatabase db = this.getReadableDatabase();

        //Count the number of records that contain the majorId that is passed
        //We should only get 1 or 0 records since the majorIs is autoincrement
        String checkMajorId = "SELECT count(majorId) FROM " + majors_table_name + " WHERE majorId = '" + majorId + "';";

        //Run the query
        Cursor cursor = db.rawQuery(checkMajorId, null);

        //Move the cursor to the first row
        cursor.moveToFirst();

        //Get the count
        int count = cursor.getInt(0);

        //Close the db
        db.close();

        //If the count is anything other than zero then the major exists
        //If the count is 0 then something is wrong with either the db, adding after load, or our query use
        if (count != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getMajorName(int majorId)
    {
        String majorName = "";

        //If majorId from the students_table_name is found in majors_table_name
        //then get the name
        if (majorIdExists(majorId))
        {
            //SQL statement to get the majorName from a passed MajorID
            String selectstatement = "SELECT majorName FROM " + majors_table_name + " WHERE majorId = '" + majorId + "';";

            //Get a readable db version
            SQLiteDatabase db = this.getReadableDatabase();

            //Run query
            Cursor cursor = db.rawQuery(selectstatement, null);

            if (cursor != null)
            {
                //Move the cursor to the first row
                //there should only be one row returned since we are looking by the MajorId, which is autoincrement
                cursor.moveToFirst();
                //The table returned is 1x1 so there is only one spot with data in it at (0)
                majorName = cursor.getString(0).toString();
            }

            //Close the db
            db.close();
        }
        //If the majorId was not found in the table then there is no Major associated with it
        //Return an error message
        else
        {
            majorName = "Error: major not found";
            Log.d("ERROR: ", "There is no major matching this majorId: " + majorId);
        }

        return majorName;
    }

    public
}
