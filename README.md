# Description
* The project is a Skapto Room Reservation Application, Which implements Java Swing for the GUI. The main goal of the application is to allow the user to reserve a romm within a specified block and select roommates (either by manually imputting the credentials of another user or randomly).

# The application consists of :
* A Login Page where the user inputs their username and student ID.
* A Room Selection panel where the user can choose the room type (double in apartment, double room and single room) and block (A, B, and C). Users have the ability to enter their preferred roommate's username or click the "Assign Random" button to assign a random roommate.
* A Confirmation Panel where the user can view their reservation details and either confirm or go back to the login page to make some changes.

# Room Reservation App
*  This class sets up the user interface and event handling for the application
# User 
* This class repesents a user in the system, with attributes like: username and ID. It is mainly used to store user information after a successful login.
# Authentication
* Connects to a databse and verifies the provided username 
# LoginPanel
* This class is esigned to allow the user to log in to the reservation system. The class extebds JPanel and and contains several GUI components , including JTExtField for the username and student ID, JLabel for error messages and JButton for submitting login credentials.
# Database
* The SQL database has a single table whcih contains usernames, id's and block of the SKAPTO building.

# How they relate to each other
* All classes in the program + the Database work together to provide a secure and efficient system that lets the user to reserve a room in SKAPTO and choose their roommate.
