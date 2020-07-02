# ProyectoDAI
Tiny app made as a final project for Informatics Applications Development course. The idea is to assign courses with classrooms and students.

## Stuff learned during the course
IAD course taught to use SQL and databases for queries and application development, VBA for Excel, and finally Android App development. All students possessed an advanced knowledge of the Java language by the time they took the course.

## What you will find on this project
This project consists of 5 simple activities that use SQL for queries of a database (which is stored locally in the user's device):

1. Main activity: the xml file for this activity is called ``activity_main.xml`` and it contains a very simple menu made out of buttons so the user can choose where to navigate. Possible navigation options include every other view on this list.

2. University activity: the xml file for this activity is called ``activity_univ.xml`` and it contains three fields along with three buttons: the fields allow the user to enter any data they have about the university (ID, name, state). The buttons allow the user to do one of three actions: add the university to the database, edit the university's information in the database, or look up the university in the database. In this last case, the user only needs to provide the ID of the university and select the button labeled "Consulta". The rest of the information for the university with that name will appear on their respective fields. Obviously, for the query and edit cases, the program will notify the user if the university was not found.

3. Major activity: the xml file for this activity is called ``avtivity_carrera.xml`` and it contains three fields and three buttons: the fields allow the user to enter any information they have about the major (ID, name, credits). The buttons allow the user to do one of three actions: add the major to the database, edit the selected major with the entered information, or look up the major in the database. Like in the previously described view, the user only needs to provide the ID of the major in question and the app will fill out the remaining fields once they select the button labeled "Consulta".

4. Student activity: the xml file for this activity is called ``activity_alum.xml`` and it contains the following fields: 
* Student ID 
* Student name
* Major name
* Major ID
* University name
* University ID
The functions of each button in this view are:
* "Consultar ID de la Carrera": look up the student's major's ID in the database.
* "Consultar ID de la Universidad": look up the student's university's ID in the database.
* "Alta": add student to the database.
* "Baja": remove student from the database.

5. Statistics activity: the xml file for this activity is called ``activity_est.xml`` and it contains an input field and the following buttons (and, therefore, possibilities):
* "Universidades por estado": will look up which universities in the existing database are located in the input state.
* "Universidades sin dicha carrera": will look up which universities in the existing database do NOT offer the input major.
* "Carreras por universidad": will look up which majors the input university offers.

## Extra thoughts, considerations, and possible improvements
This final project was basically a quick database management app to allow users to view, access, add, and modify data. However, real-time applications are usually far more complicated: they often incorporate a user system, where each user has different permissions, configurations, and preferences. Thus, it is great as a basic app to get familiar with the concept of databases in Android, but other features must be incorporated to ensure this app is useful in real life (e.g.: cloud connectivity, user system, map, etc.).
