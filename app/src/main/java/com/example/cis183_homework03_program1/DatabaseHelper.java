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
    private static final String database_name = "Students.dbHelper";
    private static final String students_table_name = "Students";
    private static final String majors_table_name = "Majors";

    public DatabaseHelper (Context c)
    {
        super(c, database_name, null, 7);
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
        Log.d("initALLTables: ","Total majors in db: " +countTableRecords(majors_table_name));
        initStudents();
        Log.d("initALLTables: ","Total students in db: " +countTableRecords(students_table_name));
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
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('mphillips', 'mphillips@my.monroeccc.edu', 'Mara', 'Phillips', '33', '3.5', '5');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('jbob', 'jbob@my.monroeccc.edu', 'Jim', 'Bob', '21', '2', '3');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('jjohnson', 'jJohnson@my.monroeccc.edu', 'John', 'Johnson', '22', '2.9', '4');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('jaddison', 'jaddison@my.monroeccc.edu', 'Jordan', 'Addison', '22', '3.9', '4');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('bmurphy', 'bmurphy@my.monroeccc.edu', 'Byron', 'Murphy Jr.', '26', '1.2', '5');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('hphillips', 'hphillips@my.monroeccc.edu', 'Harrison', 'Phillips', '28', '2.9', '6');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('cjham', 'cjham@my.monroeccc.edu', 'CJ', 'Ham', '31', '2.8', '3');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('ipace', 'ipace@my.monroeccc.edu', 'Ivan', 'Pace', '23', '3', '6');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('avanginkel', 'avanginkel@my.monroeccc.edu', 'Andrew', 'Van Ginkel', '29', '4', '1');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('sdarnold', 'sdarnold@my.monroeccc.edu', 'Sam', 'Darnold', '27', '4', '3');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('nmullens', 'nmullens@my.monroeccc.edu', 'Nick', 'Mullens', '29', '1.4', '4');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('jjefferson', 'jjefferson@my.monroeccc.edu', 'Justin', 'Jefferson', '25', '3.3', '4');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('jnailor', 'jnailor@my.monroeccc.edu', 'Jalen', 'Nailor', '25', '3.5', '2');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('bpowell', 'bpowell@my.monroeccc.edu', 'Brandon', 'Powell', '29', '2.9', '1');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('jbrooks', 'jbrooks@my.monroeccc.edu', 'Jalen', 'Brooks', '26', '1', '2');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('seedylamb', 'clamb@my.monroeccc.edu', 'CeeDee', 'Lamb', '29', '1', '3');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('lschoonmaker', 'lschoonmaker@my.monroeccc.edu', 'Luke', 'Schoonmaker', '26', '1', '6');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('tsteele', 'tsteele@my.monroeccc.edu', 'Terence', 'Steele', '29', '1', '3');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, email, fname, lname, age, gpa, MajorId) VALUES ('dprescott', 'dprescott@my.monroeccc.edu', 'Dak', 'Prescott', '33', '1', '1');");

            //close the database
            db.close();
        }
    }

    //Check if the username exists
    public boolean usernameExists(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String checkUsername = "SELECT count(username) FROM " + students_table_name + " WHERE username = '" + username + "';";

        //Query
        Cursor cursor = db.rawQuery(checkUsername,null);

        //Move the cursor to the first returned value (first row)
        cursor.moveToFirst();

        //We are looking for the username column, which is 0 since it is in the first column
        //of the table
        int count = cursor.getInt(0);

        //Close the cursor
        cursor.close();
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

            //Close the cursor
            cursor.close();
            //Close the db
            db.close();
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

                Log.d("fillStudentArrayList", "Student " + (i + 1) + ": " + student.getUsername());

                //Move the cursor
                cursor.moveToNext();
            }

            //Close the database
            db.close();
        }
        Log.d("fillStudentArrayList", "Total students filled: " + listOfStudents.size());
        return listOfStudents;
    }

    public ArrayList<Major> fillMajorArrayList()
    {
        //Make a new arraylist
        ArrayList<Major> listOfMajors = new ArrayList<>();

        //Get a readable database copy
        SQLiteDatabase db = this.getReadableDatabase();

        //Query
        String selectQuery = "SELECT * FROM " + majors_table_name;
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor !=null)
        {
            //Move the cursor
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++)
            {
                //Make a new major memory chunk
                Major major = new Major();

                //Set the info
                major.setMajorId(cursor.getInt(0));
                major.setMajorPrefix(cursor.getString(1));
                major.setMajorName(cursor.getString(2));

                //Add the major
                listOfMajors.add(major);

                //Move the cursor
                cursor.moveToNext();
            }
        }
        //Close the database
        db.close();

        return listOfMajors;
    }

    public ArrayList<String> getAllMajorNames()
    {
        ArrayList<String> listOfMajorNames = new ArrayList<>();

        //Get a readable database copy
        SQLiteDatabase db = this.getReadableDatabase();

        //Query
        String selectQuery = "SELECT majorName FROM " + majors_table_name;
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor !=null)
        {
            //Move the cursor
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++)
            {
                listOfMajorNames.add(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        //Close the database
        db.close();

        return listOfMajorNames;
    }

    public boolean majorIdExists(int majorId)
    {
        //Get a readable dbHelper version
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

        //Close the cursor
        cursor.close();
        //Close the dbHelper
        db.close();

        //If the count is anything other than zero then the major exists
        //If the count is 0 then something is wrong with either the dbHelper, adding after load, or our query use
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

            //Get a readable dbHelper version
            SQLiteDatabase db = this.getReadableDatabase();

            //Run query
            Cursor cursor = db.rawQuery(selectstatement, null);

            if (cursor != null)
            {
                //Move the cursor to the first row
                //there should only be one row returned since we are looking by the MajorId, which is autoincrement
                cursor.moveToFirst();
                //The table returned is 1x1 so there is only one spot with data in it at (0)
                majorName = cursor.getString(0);
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

    public int getMajorId(String majorName)
    {
        int majorId = 0;

        //SQL statement to get the majorId from a passed majorName
        String selectstatement = "SELECT majorId FROM " + majors_table_name + " WHERE majorName = '" + majorName + "';";

        //Get a readable dbHelper version
        SQLiteDatabase db = this.getReadableDatabase();

        //Run query
        Cursor cursor = db.rawQuery(selectstatement, null);

        //Move the cursor to the first row
        //there should only be one row returned since we are looking by the MajorName
        cursor.moveToFirst();

        if (cursor != null && cursor.moveToFirst())
        {
            //The table returned is 1x1 so there is only one spot with data in it at (0)
            majorId = cursor.getInt(0);
        }
        else
        {
            Log.d("ERROR: ", "Could not find a majorName matching that majorId");
        }

        //Close the db
        db.close();

        return majorId;
    }

    public void addStudentToDb(Student s)
    {
        //Get a writeable dbHelper version
        SQLiteDatabase db = this.getWritableDatabase();

        //SQL INSERT statement
        db.execSQL("INSERT INTO " + students_table_name + " (username, email, fname, lname, age, gpa, majorId) VALUES ('" + s.getUsername() + "','" + s.getEmail() + "','" + s.getfName() + "','" + s.getlName() + "','" + s.getAge() + "','" + s.getGpa() + "','" + s.getMajorId() + "');");

        //Close the dbHelper
        db.close();
    }

    public void updateStudentInDb(Student s)
    {
        //Get a writeable dbHelper version
        SQLiteDatabase db = this.getWritableDatabase();

        //SQL INSERT statement
        db.execSQL("UPDATE " + students_table_name + " SET email = '" + s.getEmail() + "', fname = '" + s.getfName() + "', lname = '" + s.getlName() + "', age = '" + s.getAge() + "', gpa = '" + s.getGpa() + "', majorId = '" + s.getMajorId() + "' WHERE username = '" + s.getUsername() + "';");
        //Close the dbHelper
        db.close();
    }

    public void deleteStudentFromDb(Student s)
    {
        //Get a writeable dbHelper version
        SQLiteDatabase db = this.getWritableDatabase();

        //SQL DELETE statement
        db.execSQL("DELETE FROM " + students_table_name + " WHERE username = '" + s.getUsername() + "';");
        //Close the dbHelper
        db.close();
    }

    //Check if the username exists
    public boolean majorExists(String majorName)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String checkMajor = "SELECT count(majorName) FROM " + majors_table_name + " WHERE majorName = '" + majorName + "';";

        //Query
        Cursor cursor = db.rawQuery(checkMajor,null);

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

    public void addMajorToDb(Major m)
    {
        //Get a writeable dbHelper version
        SQLiteDatabase db = this.getWritableDatabase();

        //SQL INSERT statement
        db.execSQL("INSERT INTO " + majors_table_name + " (majorPrefix, majorName) VALUES ('" + m.getMajorPrefix() + "','" + m.getMajorName() + "');");

        //Close the dbHelper
        db.close();
    }
}
