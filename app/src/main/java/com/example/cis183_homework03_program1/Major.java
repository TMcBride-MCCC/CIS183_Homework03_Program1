package com.example.cis183_homework03_program1;

import java.io.Serializable;

public class Major implements Serializable
{
    int majorId;
    String majorPrefix;
    String majorName;

    public Major()
    {

    }

    public Major(int id, String mp, String mn)
    {
        majorId = id;
        majorPrefix = mp;
        majorName = mn;
    }

    //====================================================
    //      GETTERS
    //====================================================

    public int getMajorId()
    {
        return majorId;
    }

    public String getMajorPrefix()
    {
        return majorPrefix;
    }

    public String getMajorName()
    {
        return majorName;
    }

    //====================================================
    //      SETTERS
    //====================================================

    public void setMajorId(int majorId)
    {
        this.majorId = majorId;
    }

    public void setMajorPrefix(String majorPrefix)
    {
        this.majorPrefix = majorPrefix;
    }

    public void setMajorName(String majorName)
    {
        this.majorName = majorName;
    }

}
