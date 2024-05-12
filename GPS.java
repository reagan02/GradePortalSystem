/* 
Activity No.           Title: Mini - Project Phase 2: Implementation and Modularization
Mini- Project Title:  Grading Portal System
Developers: Geverola, Jeslito; Jaljis, Shekinah Abegain, G; Mabawad, Reogie Akero P
Date Accomplished: April 22, 2024
Course Incharged: Dony C. Dongiapon
*/

package gps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GPS {

    // Define constants for admin username and password
    private static final String admin = "Admin";
    private static final String adminpass = "AdminPass";

    // Define constants for teacher username and password
    private static final String teacher = "Teacher";
    private static final String teacherpass = "TeacherPass";

    // Define constants for student username and student ID
    private static final String student = "Shikay";
    private static final String student_ID = "2023-0804";

    // Initialize ArrayLists to store teacher and student credentials
    private static ArrayList<String> teacherUsername = new ArrayList<>();
    private static ArrayList<String> teacherPass = new ArrayList<>();
    private static ArrayList<String> studentUsername = new ArrayList<>();
    private static ArrayList<String> studentID = new ArrayList<>();

    // Main method to start the program
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        func(s);
    }

    // Method to display the main functionality options and handle user input
    public static void func(Scanner s) {
        // Display header
        header();
        // Display main menu
        menus();

        // Prompt user for choice
        System.out.print("Enter your choice: ");
        int choice = s.nextInt();

        // Switch statement to handle user choice
        switch (choice) {
            case 1:
                // Call login method if user chooses 1
                login(s);
                break;

            case 2:
                // Call register method if user chooses 2
                register(s);
                break;

            case 3:
                // Exit the system if user chooses 3
                System.out.println();
                System.out.println("Thank you for using Grading Portal System.");
                System.out.println();
                System.out.println("Exiting System!");
                System.exit(0);
                break;

            default:
                // If choice is invalid, display error message and restart func
                System.out.println("Invalid choice!");
                func(s);
                break;
        }
    }

    // Method to display header
    public static void header() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("\t\t\t Welcome to DOrSU's Grading Portal");
        System.out.println();
    }

    // Method to display main menu
    public static void menus() {
        String[] menu = { "Login", "Register", "Exit" };
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i]);
        }
        System.out.println();
    }

    // Method to handle user login
    public static void login(Scanner s) {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("\t\t\t\t\tLogin");
        System.out.print("Username: ");
        String username = s.next();

        // Check if username is admin
        if (username.equals(admin)) {
            System.out.print("Password: ");
            String password = s.next();

            // Check if password is correct for admin
            if (password.equals(adminpass)) {
                adminPortal(s);
            } else {
                System.out.println("Incorrect Password. Try again!");
                login(s);
            }
        } else if (username.equals(teacher)) {
            // Check if username is teacher
            System.out.print("Password: ");
            String password = s.next();
            // Check if password is correct for teacher
            if (password.equals(teacherpass)) {
                teacherPortal(s);
            } else {
                System.out.println("Incorrect Password. Try again!");
                login(s);
            }
        } else if (username.equals(student)) {
            // Check if username is student
            System.out.print("ID number : ");
            String IDnum = s.next();
            // Check if student ID is correct
            if (IDnum.equals(student_ID)) {
                studentPortal(s);
            } else {
                System.out.println("Incorrect ID Number. Try again!");
                login(s);
            }
        } else if (teacherUsername.contains(username)) {
            // Check if username is in teacherUsername ArrayList
            int index = teacherUsername.indexOf(username);
            System.out.print("Password: ");
            String password = s.next();
            // Check if password is correct for the corresponding teacher
            if (password.equals(teacherPass.get(index))) {
                teacherPortal(s);
            } else {
                System.out.println("Incorrect Password. Try again!");
                login(s);
            }
        } else if (studentUsername.contains(username)) {
            // Check if username is in studentUsername ArrayList
            int index = studentUsername.indexOf(username);
            System.out.print("ID No: ");
            String idNum = s.next();
            // Check if ID number is correct for the corresponding student
            if (idNum.equals(studentID.get(index))) {
                studentPortal(s);
            } else {
                System.out.println("Incorrect ID Number. Try again!");
                login(s);
            }
        } else {
            // If username does not exist, prompt user to try again
            System.out.println("Username does not exist. Please try again!.");
            func(s);
        }
    }

    // Method is responsible for initiating the Admin Portal interface
    public static void adminPortal(Scanner s) {
        // Printing a header for the Admin Portal
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("\t\t\t\t     Admin Portal");
        // Calling the adminMenu method to display the admin menu options
        adminMenu(s);
    }

    // This method presents the admin user with a menu of options (Add, Edit,
    // Logout) and prompts them to enter their choice.
    public static void adminMenu(Scanner s) {
        // Array containing options for the admin menu
        System.out.println("\t\t\t           Admin Main Menu");
        String[] adminMenu = { "Add", "Edit", "Logout" };
        System.out.println();

        // Looping through the admin menu options and printing them
        for (int i = 0; i < adminMenu.length; i++) {
            System.out.println((i + 1) + ". " + adminMenu[i]);
        }

        // Prompting the user to enter their choice
        System.out.print("Enter your choice: ");
        int choice = s.nextInt();

        // Switch statement to handle user choice
        switch (choice) {
            case 1:
                // If user chooses "Add", call adminAddMenu and adminAddChoice methods
                adminAddMenu(s);
                adminAddChoice(s);
                break;

            case 2:
                // If user chooses "Edit", call adminEditMenu and adminEditChoice methods
                adminEditMenu(s);
                adminEditChoice(s);
                break;

            case 3:
                // If user chooses "Logout", call logout method and pass scanner to func method
                logout();
                func(s);
                break;

            default:
                // If user enters an invalid choice
                System.out.println("Invalid choice");
                break;
        }
    }

    // This method is responsible for displaying the menu options available for
    // adding items in the admin interface.
    public static void adminAddMenu(Scanner s) {
        // Printing a header for the add menu
        System.out.println();
        System.out.println("You chose to add.");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Add Options:");
        System.out.println();
        // Array containing options for adding
        String[] addOption = { "Academic Year", "Faculty", "Course", "Semester",
                "Year Level", "Section", "Subject", "Student",
                "Instructor", "Schedule", "Room", "Back" };

        // Looping through the add options and printing them
        for (int i = 0; i < addOption.length; i++) {
            System.out.println((i + 1) + ". " + addOption[i]);
        }
    }

    // This method prompts the user to select a choice for adding items in the admin
    // interface.
    public static void adminAddChoice(Scanner s) {
        System.out.print("Select choice: ");
        int add = s.nextInt();
        System.out.println();

        switch (add) {
            case 1:
                // If user chooses "Academic Year", call aAcademicYear method and then go back
                // to admin menu
                System.out.println("---------------------------------------------------------------------------------");
                aAcademicYear(s);
                System.out.println();
                adminMenu(s);

                break;

            case 2:
                // If user chooses "Faculty", call aFaculty method and then go back to admin
                // menu
                System.out.println("---------------------------------------------------------------------------------");
                aFaculty(s);
                System.out.println();
                adminMenu(s);
                break;

            case 3:
                // If user chooses "Course", call aCourse method and then go back to admin menu
                aCourse();
                System.out.println();
                adminMenu(s);
                break;

            case 4:
                // If user chooses "Semester", call aSemester method and then go back to admin
                // menu
                aSemester();
                System.out.println();
                adminMenu(s);
                break;

            case 5:
                // If user chooses "Year Year", call aYearLevel method and then go back to admin
                // menu
                aYearLevel();
                System.out.println();
                adminMenu(s);
                break;

            case 6:
                // If user chooses "Section", call aSection method and then go back to admin
                // menu
                aSection();
                System.out.println();
                adminMenu(s);
                break;

            case 7:
                // If user chooses "Subject", call aSubject method and then go back to admin
                // menu
                aSubject();
                System.out.println();
                adminMenu(s);
                break;

            case 8:
                // If user chooses "Student", call aStudent method and then go back to admin
                // menu
                aStudent();
                System.out.println();
                adminMenu(s);
                break;

            case 9:
                // If user chooses "Instructor", call aInstructor method and then go back to
                // admin menu
                aInstructor();
                System.out.println();
                adminMenu(s);
                break;

            case 10:
                // If user chooses "Schedule", call aSchedule method and then go back to admin
                // menu
                aSchedule();
                System.out.println();
                adminMenu(s);
                break;

            case 11:
                // If user chooses "Room", call aRoom method and then go back to admin menu
                aRoom();
                System.out.println();
                adminMenu(s);
                break;

            case 12:
                // If user chooses "Back", call Back method and then go back to admin menu
                Back();
                System.out.println();
                adminMenu(s);
                break;

            default:
                // If user enters an invalid choice
                System.out.println("Invalid choice!");
                break;
        }

    }

    // This method is responsible for displaying the menu options available for
    // editing items in the admin interface.
    public static void adminEditMenu(Scanner s) {
        // Printing a header for the edit menu
        System.out.println();
        System.out.println("You chose to edit.");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println();
        // Array containing options for editing
        String[] editOption = { "Academic Year", "Faculty", "Course", "Semester",
                "Year Level", "Section", "Subject", "Student", "Instructor",
                "Schedule", "Room", "Back" };

        // Looping through the edit options and printing them
        for (int i = 0; i < editOption.length; i++) {
            System.out.println((i + 1) + ". " + editOption[i]);
        }
    }

    /*
     * This method prompts the user to select an edit option in the admin interface,
     * reads their input, and then handles their choice
     * accordingly using a switch statement.
     */
    public static void adminEditChoice(Scanner s) {
        // Prompting the user to select an edit option
        System.out.println();
        System.out.print("Select choice: ");
        int edit = s.nextInt();
        System.out.println();

        // Switch statement to handle user choice
        switch (edit) {
            case 1:
                // If user chooses "Academic Year", call eAcademicYear method and then go back
                // to admin menu
                eAcademicYear();
                System.out.println();
                adminMenu(s);
                break;

            case 2:
                // If user chooses "Faculty", call eFaculty method and then go back to admin
                // menu
                eFaculty();
                System.out.println();
                adminMenu(s);
                break;

            case 3:
                // If user chooses "Course", call eCourse method and then go back to admin menu
                eCourse();
                System.out.println();
                adminMenu(s);
                break;

            case 4:
                // If user chooses "Semester", call eSemester method and then go back to admin
                // menu
                eSemester();
                System.out.println();
                adminMenu(s);
                break;

            case 5:
                // If user chooses "Year Level, call eYearLevel method and then go back to admin
                // menu
                eYearLevel();
                System.out.println();
                adminMenu(s);
                break;

            case 6:
                // If user chooses "Section", call eSection method and then go back to admin
                // menu
                eSection();
                System.out.println();
                adminMenu(s);
                break;

            case 7:
                // If user chooses "Subject", call eSubject method and then go back to admin
                // menu
                eSubject();
                System.out.println();
                adminMenu(s);
                break;

            case 8:
                // If user chooses "Student", call eStudent method and then go back to admin
                // menu
                eStudent();
                System.out.println();
                adminMenu(s);
                break;

            case 9:
                // If user chooses "Instructor", call eInstructor method and then go back to
                // admin menu
                eInstructor();
                System.out.println();
                adminMenu(s);
                break;

            case 10:
                // If user chooses "Schedule", call eSchedule method and then go back to admin
                // menu
                eSchedule();
                System.out.println();
                adminMenu(s);
                break;

            case 11:
                // If user chooses "Room", call eRoom method and then go back to admin menu
                eRoom();
                System.out.println();
                adminMenu(s);
                break;

            case 12:
                // If user chooses "Back", call Back method and then go back to admin menu
                Back();
                System.out.println();
                adminMenu(s);
                break;

            default:
                // If user enters an invalid choice
                System.out.println("Invalid choice!");
                System.out.println();
                break;
        }

    }

    public static void aAcademicYear(Scanner s) {
        // Printing a success message for adding an Academic Year

        System.out.println();
        System.out.println("Add Academic Year");
        System.out.print("Enter Academic Year (YYYY-YYYY): ");
        String ay = s.next();
        if (ay.matches("20[2-4]\\d-20[2-4]\\d")) {
            // Display registration confirmation
            System.out.println();
            // Call the method to write the academic year to the file
            handleFileHandling(ay, "write", "academic_year.txt", "academic_year_temp.txt", "Academic Year");
            // Call the method to read the academic year list
            handleFileHandling(null, "read", "academic_year.txt", "academic_year_temp.txt", "Academic Year");

            System.out.println();
            System.out.print("Do you want to add academic year again? (y/n): ");
            String option = s.next().toLowerCase();

            // Validate user input
            while (!"y".equals(option) && !"n".equals(option)) {
                System.out.print("Invalid choice. Please enter 'y' or 'n': ");
                option = s.next();
                System.out.println();
                System.out.println("---------------------------------------------------------------------------------");
            }
            // If user chooses not to register again, set registration flag to true and
            // return to main menu
            if ("y".equals(option)) {
                System.out.println();
                boolean add = true;
                System.out.println();
                aAcademicYear(s);
            }

        } else {
            // Display message for invalid ID number format
            System.out.println();
            System.out.println("Invalid Academic Year format. Added failed.");
            aAcademicYear(s);
        }

    }

    public static void handleFileHandling(String input, String fileMethod, String fileName, String tempFileName,
            String description) {
        switch (fileMethod) {
            case "write":
                try {
                    File file = new File(fileName);

                    // If the file doesn't exist, create a new file
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    // Check if the academic year is already added
                    FileReader fileReader = new FileReader(fileName);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (line.equals(input)) {
                            System.out.println(description + " is already added");
                            bufferedReader.close();
                            return;
                        }
                    }
                    fileReader.close();
                    bufferedReader.close();
                    System.out.println(input + " added successfully!");
                    System.out.println(
                            "---------------------------------------------------------------------------------");
                    // If the academic year is not already added, add it to the file
                    FileWriter fileWriter = new FileWriter(fileName, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(input);
                    bufferedWriter.newLine(); // Write a new line to the file
                    bufferedWriter.close();

                } catch (IOException e) {
                    System.out.println("An error occurred while trying to add the " + description + ".");
                    e.printStackTrace();
                }

                break;
            case "read":
                try {
                    FileReader fileReader = new FileReader(fileName);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);
                    }

                    // Close the reader
                    bufferedReader.close();
                    fileReader.close();

                } catch (IOException e) {
                    System.out.println("An error occurred while trying to read the file.");
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter the " + description + " you want to edit:");
                    String oldString = scanner.nextLine();
                    // Prompt the user to enter
                    System.out.println("Enter the new " + description + ":");
                    String newString = scanner.nextLine();

                    FileReader fileReader = new FileReader(fileName);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    FileWriter fileWriter = new FileWriter(tempFileName, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (line.equals(oldString)) {
                            bufferedWriter.write(newString);
                        } else {
                            bufferedWriter.write(line);
                        }
                        bufferedWriter.newLine();
                    }

                    // Close the readers and writers
                    bufferedReader.close();
                    bufferedWriter.close();

                    // Delete the old file and rename the temp file
                    File oldFile = new File(fileName);
                    oldFile.delete();
                    File tempFile = new File(tempFileName);
                    tempFile.renameTo(oldFile);

                    System.out.println(description + " edited successfully!");
                    System.out.println();

                    System.out.println("Updated " + description + " List:");
                    // call the method to display the academic year list
                    handleFileHandling(input, "read", fileName, tempFileName, description);

                } catch (IOException e) {
                    System.out.println("An error occurred while trying to edit the " + description + "");
                    e.printStackTrace();
                }

                break;
            default:
                break;
        }

    }

    public static void aFaculty(Scanner s) {
        boolean add = true;
        while (add) {
            System.out.println();
            System.out.println("Add Faculty");
            System.out.print("Enter Faculty Name: ");
            String facultyName = s.nextLine();

            if (facultyName.matches("Faculty of [^\\d]+")) {
                handleFileHandling(facultyName, "write", "facultyFile", "tempFacultyFile", "Faculty");
                System.out.println();
                System.out.println();
                handleFileHandling(facultyName, "read", "facultyFile", "tempFacultyFile", "Faculty");
                // Display registration confirmation
                System.out.println();
                System.out.println("---------------------------------------------------------------------------------");

                // Ask user if they want to add another faculty
                System.out.println();
                System.out.print("Do you want to add another faculty? (y/n): ");
                String option = s.nextLine().toLowerCase();

                // Validate user input
                while (!option.equals("y") && !option.equals("n")) {
                    System.out.print("Invalid choice. Please enter 'y' or 'n': ");
                    option = s.nextLine().toLowerCase();
                }
                System.out.println(option);
                // If user chooses not to add another faculty, exit the loop
                if (option.equals("n")) {
                    add = false;
                    break;
                }

            } else {
                // Display message for invalid faculty name
                System.out.println();
                System.out.println("Invalid faculty name format. Please try again.");
                // aFaculty(s);
                continue;
            }

        }
    }

    public static void aCourse() {
        // Printing a success message for adding a Course
        System.out.println("Course added successfully!");
    }

    public static void aSemester() {
        // Printing a success message for adding a Semester
        System.out.println("Semester added successfully!");
    }

    public static void aYearLevel() {
        // Printing a success message for adding a Year Level
        System.out.println("Year Level added successfully!");
    }

    public static void aSection() {
        // Printing a success message for adding a Section
        System.out.println("Section added successfully!");
    }

    public static void aSubject() {
        // Printing a success message for adding a Subject
        System.out.println("Subject added successfully!");
    }

    public static void aStudent() {
        // Printing a success message for adding a Student
        System.out.println("Student added successfully!");
    }

    public static void aInstructor() {
        // Printing a success message for adding an Instructor
        System.out.println("Instructor added successfully!");
    }

    public static void aSchedule() {
        // Printing a success message for adding a Scheduke
        System.out.println("Schedule added successfully!");
    }

    public static void aRoom() {
        // Printing a success message for adding a Room
        System.out.println("Room added successfully!");
    }

    public static void Back() {
        // Prompt
        System.out.println("Return to Admin Main Menu!");

    }

    public static void eAcademicYear() {
        // call the method to display the academic year list
        handleFileHandling(null, "read", "academic_year.txt", "academic_year_temp.txt", "Academic Year");
        // call the method to edit the academic year
        handleFileHandling(null, "edit", "academic_year.txt", "academic_year_temp.txt", "Academic Year");

    }

    public static void eFaculty() {
        // call the method to display the faculty list
        handleFileHandling(null, "read", "facultyFile", "tempFacultyFile", "Faculty");
        // call the method to edit the faculty
        handleFileHandling(null, "edit", "facultyFile", "tempFacultyFile", "Faculty");
    }

    public static void eCourse() {
        // Printing a success message for editing a Course
        System.out.println("Course edit successfully!");
    }

    public static void eSemester() {
        // Printing a success message for editing a Semester
        System.out.println("Semester edit successfully!");
    }

    public static void eYearLevel() {
        // Printing a success message for editing a Year Level
        System.out.println("Year Level edit successfully!");
    }

    public static void eSection() {
        // Printing a success message for editing a Section
        System.out.println("Section edit successfully!");
    }

    public static void eSubject() {
        // Printing a success message for editing a Subject
        System.out.println("Subject edit successfully!");
    }

    public static void eStudent() {
        // Printing a success message for editing a Student
        System.out.println("Student edit successfully!");
    }

    public static void eInstructor() {
        // Printing a success message for editing an Instructor
        System.out.println("Instructor edit successfully!");
    }

    public static void eSchedule() {
        // Printing a success message for editing a Schedule
        System.out.println("Schedule edit successfully!");
    }

    public static void eRoom() {
        // Printing a success message for editing a Room
        System.out.println("Room edit successfully!");
    }

    /*
     * This method is responsible for displaying the header for the Teacher Portal
     * and then calling the teacherMenu method to display the menu options available
     * for teachers.
     */
    public static void teacherPortal(Scanner s) {
        // Printing a header for the Teacher Portal
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t    Teacher Portal");
        // Calling the teacherMenu method to display the teacher menu options
        teacherMenu(s);
    }

    public static void teacherMenu(Scanner s) {
        // Array containing options for the teacher menu
        String[] teacherMenu = { "View", "Edit", "Logout" };

        // Looping through the teacher menu options and printing them
        for (int i = 0; i < teacherMenu.length; i++) {
            System.out.println((i + 1) + ". " + teacherMenu[i]);
        }

        // Prompting the user to enter their choice
        System.out.print("Enter your choice: ");
        int choice = s.nextInt();

        // Switch statement to handle user choice
        switch (choice) {
            case 1:
                // If user chooses "View", call teacherViewMenu and teacherViewChoice methods
                teacherViewMenu(s);
                System.out.println();
                teacherViewChoice(s);
                break;

            case 2:
                // If user chooses "Edit", call teacherEditMenu and teacherEditChoice methods
                teacherEditMenu(s);
                System.out.println();
                teacherEditChoice(s);
                break;

            case 3:
                // If user chooses "Logout", call logout method and pass scanner to func method
                logout();
                System.out.println();
                func(s);
                break;

            default:
                // If user enters an invalid choice
                System.out.println("Invalid choice!");
                break;
        }
    }

    // This method displays the menu options for the teacher, such as "View",
    // "Edit", and "Logout"
    public static void teacherViewMenu(Scanner s) {
        // Printing a header for the view menu
        System.out.println();
        System.out.println("You chose to view.");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println();

        // Array containing options for viewing
        String[] viewOption = { "Subject", "Section", "Students", "Grades", "Back" };

        // Looping through the view options and printing them
        for (int i = 0; i < viewOption.length; i++) {
            System.out.println((i + 1) + ". " + viewOption[i]);
        }

        System.out.println();
    }

    // This method handles the user's choice for viewing options in the teacher
    // menu.
    public static void teacherViewChoice(Scanner s) {
        // Prompting the user to select a view option
        System.out.print("Select choice: ");
        int view = s.nextInt();
        System.out.println();

        // Switch statement to handle user choice
        switch (view) {
            case 1:
                // If user chooses "Subject", call vSubject method and then go back to teacher
                // menu
                vSubject();
                System.out.println();
                teacherMenu(s);
                break;

            case 2:
                // If user chooses "Section", call vSection method and then go back to teacher
                // menu
                vSection();
                System.out.println();
                teacherMenu(s);
                break;

            case 3:
                // If user chooses "Students", call vStudents method and then go back to teacher
                // menu
                vStudents();
                System.out.println();
                teacherMenu(s);
                break;

            case 4:
                // If user chooses "Grades", call vGrades method and then go back to teacher
                // menu
                vGrades();
                System.out.println();
                teacherMenu(s);
                break;

            case 5:
                // If user chooses "Back", call tBack method and then go back to teacher menu
                tBack();
                System.out.println();
                teacherMenu(s);
                break;

            default:
                // If user enters an invalid choice
                System.out.println();
                System.out.println("Invalid choice!");
                teacherMenu(s);
                break;
        }

    }

    /*
     * This method displays the edit menu for teachers.
     * It prints a header indicating that the user chose to edit,
     * followed by a list of editing options
     * (in this case, "Grades" and "Back").
     */
    public static void teacherEditMenu(Scanner s) {
        // Printing a header for the edit menu
        System.out.println();
        System.out.println("You chose to edit.");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println();

        // Array containing options for editing
        String editOption[] = { "Grades", "Back" };

        // Looping through the edit options and printing them
        for (int e = 0; e < editOption.length; e++) {
            System.out.println((e + 1) + ". " + editOption[e]);
        }

        System.out.println();
    }

    /*
     * This method handles the choice of editing options for teachers. It prompts
     * the teacher
     * to select an edit choice, then uses a switch statement to execute the
     * corresponding action
     * based on the selected choice. In this case, it calls methods like eGrades()
     * or tBack()
     * depending on the user's selection. After executing the chosen action,
     * it goes back to the teacher menu.
     */
    public static void teacherEditChoice(Scanner s) {
        // Prompting the teacher to select an edit choice
        System.out.print("Select choice: ");
        int edit = s.nextInt();
        System.out.println();

        // Switch statement to handle edit choices
        switch (edit) {
            // Cases for different types of items to edit
            case 1:
                // If user chooses "Grades", call eGrades method and then go back to teacher
                // menu
                eGrades();
                System.out.println();
                teacherMenu(s);
                break;

            case 2:
                // If user chooses "Back", call tBack method and then go back to teacher menu
                tBack();
                System.out.println();
                teacherMenu(s);
                break;

            default:
                // If user enters an invalid choice
                System.out.println();
                System.out.println("Invalid choice!");
                teacherMenu(s);
                break;
        }
    }

    // Methods for viewing Subject functionalities
    public static void vSubject() {
        // Methods for viewing Subject functionalities System.out.println("You viewed
        // subjects you handle.");
    }

    // Methods for viewing Section functionalities
    public static void vSection() {
        System.out.println("You viewed sections you handle.");
    }

    // Methods for viewing Students functionalities
    public static void vStudents() {
        System.out.println("You viewed students enrolled in your subject.");
    }

    // Methods for viewing Grades functionalities
    public static void vGrades() {
        System.out.println("You viewed the grades of your students.");
    }

    // Method for editing grades
    public static void eGrades() {
        System.out.println("Grades edited successfully!");
    }

    // Method for going back to teacher main menu
    public static void tBack() {
        System.out.println("Return to Teacher Main Menu");
    }

    public static void studentPortal(Scanner s) {
        // Printing a header for the Student Portal
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t    Student Portal");
        // Calling the studentMenu method to display the student menu options
        studentMenu(s);
    }

    /*
     * It displays a header indicating the student portal and then calls the
     * studentMenu method to show the menu options available for students.
     */
    public static void studentMenu(Scanner s) {
        // Define array for student menu options
        String studentOptions[] = { "View grades", "Logout" };

        // Display student menu options
        for (int n = 0; n < studentOptions.length; n++) {
            System.out.println((n + 1) + ". " + studentOptions[n]);
        }

        System.out.println();

        // Prompt the student to enter their choice
        System.out.print("Enter your choice: ");
        int choice = s.nextInt();

        // Switch statement to handle student's choice
        switch (choice) {
            case 1:
                // If student chooses "View grades", call studentView method
                studentView(s);
                System.out.println();
                break;

            case 2:
                // If student chooses "Logout", call logout method and pass scanner to func
                // method
                logout();
                System.out.println();
                func(s);
                break;

            default:
                // If student enters an invalid choice, display error message and go back to
                // student portal
                System.out.println("Invalid choice!");
                studentPortal(s);
                System.out.println();
                break;
        }
    }

    // This method displays a message confirming the successful viewing of a
    // student's grade and then returns the user to the student menu.
    public static void studentView(Scanner s) {
        // Print message indicating successful viewing of grades
        System.out.println();
        System.out.println("You successfully viewed your grade!");
        System.out.println();
        // Go back to the student menu
        studentPortal(s);
    }

    /*
     * This method handles the registration process for both teachers and students.
     * It prompts the user to choose
     * between registering as a teacher or a student, then guides them through the
     * registration process accordingly.
     */
    public static void register(Scanner s) {
        // Print header for registration section
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("\t\t\t\t\tRegister");
        System.out.println();

        boolean registration = false;
        // Loop for registration process
        while (!registration) {
            System.out.println();
            // Display registration options
            System.out.println("1. Teacher ");
            System.out.println("2. Student");
            System.out.println("3. Exit");
            System.out.println();
            // Prompt user to choose role for registration
            System.out.print("Register as: ");
            int role = s.nextInt();
            System.out.println("---------------------------------------------------------------------------------");
            // Process based on chosen role
            switch (role) {
                case 1:
                    // Teacher registration
                    teacherRegister(s);
                    break;

                case 2:
                    // Student registration
                    studentRegister(s);
                    break;

                case 3:
                    // Exit registration process and return to main menu
                    func(s);
                    break;

                default:
                    // Display message for invalid choice
                    System.out.println("Invalid choice!");
                    break;
            }

            // Prompt user to register again
            System.out.println();
            System.out.print("Do you want to register again? (y/n): ");
            String option = s.next();

            // Validate user input
            while (!"y".equals(option) && !"n".equals(option)) {
                System.out.print("Invalid choice. Please enter 'y' or 'n': ");
                option = s.next();
                System.out.println();

            }

            // If user chooses not to register again, set registration flag to true and
            // return to main menu
            if ("n".equals(option)) {
                registration = true;
                System.out.println();
                func(s);
            }
        }
    }

    /*
     * This method prints a registration success message along with the
     * name and username of the teacher who successfully registered.
     */
    public static void regsuccess(String tName, String tUsername) {
        // Print registration success message with teacher's name and username
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t   Registration successful!");
        System.out.println("Name: " + tName);
        System.out.println("Username: " + tUsername);
        System.out.println();
    }

    public static void teacherRegister(Scanner s) {
        // Prompt for teacher details
        System.out.print("Enter your name: ");
        String tName = s.next();
        s.nextLine();

        System.out.print("Enter your username: ");
        String tUsername = s.next();
        s.nextLine();

        System.out.print("Enter your password: ");
        String tPassword = s.next();

        // Add teacher details to respective lists
        teacherUsername.add(tUsername);
        teacherPass.add(tPassword);

        // Processing admin key for registration confirmation
        System.out.println();
        System.out.println("Processing....");
        System.out.println();

        boolean adminKeyCorrect = false;
        int attempts = 0;

        while (!adminKeyCorrect && attempts < 3) {
            // Prompt for admin key
            System.out.print("Enter the admin key: ");
            String adminKey = s.next();

            // Validate admin key
            if ("admin123".equals(adminKey)) {
                adminKeyCorrect = true;
            } else {
                // Display message for incorrect admin key
                System.out.println("Admin key is incorrect.");
                attempts++;
                System.out.println();
            }
        }

        // Display registration confirmation
        if (adminKeyCorrect) {
            regsuccess(tName, tUsername);
        } else {
            // Display message for unsuccessful registration
            System.out.println("Try again!");
            System.out.println();
        }
    }

    // This method facilitates the registration of a teacher by prompting for their
    // name, username, and password.
    public static void studentRegister(Scanner s) {
        // Prompt for student details
        System.out.print("Enter your name: ");
        String sName = s.next();
        s.nextLine();

        System.out.print("Enter your username: ");
        String sUsername = s.next();
        s.nextLine();

        System.out.print("Enter your ID number (nnnn-nnnn): ");
        String idNumber = s.next();
        s.nextLine();

        // Add student details to respective lists
        studentUsername.add(sUsername);
        studentID.add(idNumber);

        // Validate ID number format
        if (idNumber.matches("\\d{4}-\\d{4}")) {
            // Display registration confirmation
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("\t\tRegistration successful!");
            System.out.println("Name: " + sName);
            System.out.println("Username: " + sUsername);
            System.out.println("ID Number: " + idNumber);
            System.out.println();
        } else {
            // Display message for invalid ID number format
            System.out.println();
            System.out.println("Invalid ID number format. Registration failed.");
        }
    }

    // This method simply prints a message indicating that the logout process was
    // successful.
    public static void logout() {
        // Print message indicating successful logout
        System.out.println("Successfully logged out!");
    }
}
