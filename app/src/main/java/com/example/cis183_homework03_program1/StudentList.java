package com.example.cis183_homework03_program1;

import java.util.ArrayList;

public class StudentList
{
    private static StudentList studentList;
    private ArrayList<Student> students;

    private StudentList()
    {
        //Create the new ArrayList named students
        students = new ArrayList<>();
    }

    public static StudentList getInstance()
    {
        //If there is no instance of studentList then create one
        if (studentList == null)
        {
            studentList = new StudentList();
        }
        return studentList;
    }

    public ArrayList<Student> getStudents()
    {
        return students;
    }

    public void initStudentList(ArrayList<Student> students)
    {
        this.students = students;
    }

    public void updateStudent(Student studentToUpdate)
    {
        int indexOfStudent = students.indexOf(studentToUpdate);
        students.set(indexOfStudent, studentToUpdate);
    }
}
