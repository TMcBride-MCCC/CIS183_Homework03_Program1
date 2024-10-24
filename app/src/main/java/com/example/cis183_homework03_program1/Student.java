package com.example.cis183_homework03_program1;

import java.io.Serializable;

public class Student implements Serializable
{
    String username;
    String email;
    String fName;
    String lName;
    int age;
    float gpa;
    int majorId;

    public Student()
    {

    }

    public Student(String u, String e, String f, String l, int a, float g, int m)
    {
        username = u;
        email = e;
        fName = f;
        lName = l;
        age = a;
        gpa = g;
        majorId = m;
    }

    //====================================================
    //      GETTERS
    //====================================================

    public String getUsername()
    {
        return username;
    }

    public String getEmail()
    {
        return email;
    }

    public String getfName()
    {
        return fName;
    }

    public String getlName()
    {
        return lName;
    }

    public int getAge()
    {
        return age;
    }

    public float getGpa()
    {
        return gpa;
    }

    public int getMajorId()
    {
        return majorId;
    }

    //=======================================================
    //      SETTERS
    //=======================================================

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setfName(String fName)
    {
        this.fName = fName;
    }

    public void setlName(String lName)
    {
        this.lName = lName;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setGpa(float gpa)
    {
        this.gpa = gpa;
    }

    public void setMajorId(int majorId)
    {
        this.majorId = majorId;
    }
}
