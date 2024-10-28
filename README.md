Tyler McBride
Professor Moore
CIS-183-S1 Mobile App Development
October 14th, 2024

CIS183_Homework03_Program1
This Android app is a student enrollment application. It handles all of the student’s data, as well as data for the majors that the educational institution has. More students can be enrolled and more majors can be added.

Problems:
•	Initially I had to wrap my head around how a PK and a FK can be implemented to work with this project. The answer was easy, but not intuitive to how I normally think. I would think that a student has a major, but it really is that a major has many students. At least that is how I coded the db, majors first.
•	When pulling the majorName from the major’s table, through a check with the MajorId from the student’s table, I had a problem because I forgot that the returned table is 1x1. I was originally trying to pull data from (2) when it needed to be (0).
•	Wrapping my head around how to turn fill the spinner for AddStudent was a challenge. I think this is mainly due to overthinking it. I was unsure if the best method was to pull from the database or from the Major class. After some thinking I decided to pull from the db, as I think that will help me for when I start adding majors.
•	I made a function for and onClickListener, and forgot to put a setOnClickListener actually in it…trying to figure out why my intent switch was skipping an entire intent in between was baffling. It was a quick fix but it threw me for a loop for a little bit.
•	When passing the student from MainActivity to StudentDetails and then to UpdateStudent, I was getting a null reference error when trying to fill the Edit Text with the source data. I thought that there was a problem with the multiple passings of data or something. I chased this bug for a good hour or two…in the end it was an error on my part when copying/pasting findViewById code from one activity to the other to keep things simple.
•	I chased a bug where the listview wouldn’t show the whole ArrayList but the db was being added to correctly, for a very long time. I ended up putting my ArrayList in a StudentList class, thinking that my adapter wasn’t working correctly for some reason. Turns out my constraints weren’t set correctly on the lv_v and the last two students were appearing behind my bottom navigation, even though visually it looked correct.
•	I was unable to finish the search function of the program due to time constraints.
