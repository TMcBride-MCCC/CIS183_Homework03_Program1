package com.example.cis183_homework03_program1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Student> listOfStudents;

    public HomeListAdapter(Context c, ArrayList<Student> ls)
    {
        context = c;
        listOfStudents = ls;
    }

    @Override
    public int getCount()
    {
        return listOfStudents.size();
    }

    @Override
    public Object getItem(int i)
    {
        return listOfStudents.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater mInflator = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflator.inflate(R.layout.home_listview_cell, null);
        }

        //Find GUI
        TextView lName = view.findViewById(R.id.tv_v_homelistview_lname);
        TextView fName = view.findViewById(R.id.tv_v_homelistview_fname);
        TextView username = view.findViewById(R.id.tv_v_homelistview_username);

        //Get the data from the list
        Student student = listOfStudents.get(i);

        //Set the GUI
        lName.setText(student.getlName() + ",");
        fName.setText(student.getfName());
        username.setText(student.getUsername());

        return view;
    }
}
