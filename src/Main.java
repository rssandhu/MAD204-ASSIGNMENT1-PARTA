/**
 * MAD204 – Assignment 1 PARTA
 * Name: Ramandeep Singh
 * Student ID: A00194321
 * Date of Submission: 05/10/2025
 *
 * Description:
 * Entry point for People Management System. Provides menu-driven interface
 * and manages all program features including add/list/search/remove people,
 * performance evaluation, recursion demos, and file persistence.
 */

import java.util.*;
import java.io.*;

public class Main {
    // List storing all people (students, professors, TAs)
    private static ArrayList<Person> people = new ArrayList<>();
    // Scanner for user input
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Loads people data from "people.txt" file.
     * Parses each line to create appropriate Person subclass instances.
     * Prints confirmation or error message.
     */
    private static void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("people.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                String type = arr[0];
                if (type.equals("Student")) {
                    people.add(new Student(
                            Integer.parseInt(arr[1]), arr[2], Integer.parseInt(arr[3]),
                            arr[4], Integer.parseInt(arr[5]), Double.parseDouble(arr[6])));
                } else if (type.equals("Professor")) {
                    people.add(new Professor(
                            Integer.parseInt(arr[1]), arr[2], Integer.parseInt(arr[3]),
                            arr[4], arr[5], Integer.parseInt(arr[6])));
                } else if (type.equals("TA")) {
                    people.add(new TeachingAssistant(
                            Integer.parseInt(arr[1]), arr[2], Integer.parseInt(arr[3]),
                            arr[4], Integer.parseInt(arr[5]), Double.parseDouble(arr[6]),
                            arr[7], Integer.parseInt(arr[8])));
                }
            }
            System.out.println("Loaded people from file.");
        } catch (IOException e) {
            System.out.println("No previous data found (people.txt).");
        }
    }

    /**
     * Saves entire 'people' list to "people.txt" file.
     * Writes each person's data as a comma-separated line depending on type.
     * Prints confirmation or error message.
     */
    private static void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("people.txt"))) {
            for (Person p : people) {
                if (p instanceof TeachingAssistant) {
                    TeachingAssistant ta = (TeachingAssistant) p;
                    pw.printf("TA,%d,%s,%d,%s,%d,%.2f,%s,%d\n",
                            ta.id, ta.name, ta.age, ta.program, ta.year, ta.gpa,
                            ta.getAssignedCourse(), ta.getTaDuties());
                } else if (p instanceof Student && !(p instanceof TeachingAssistant)) {
                    Student s = (Student) p;
                    pw.printf("Student,%d,%s,%d,%s,%d,%.2f\n",
                            s.id, s.name, s.age, s.program, s.year, s.gpa);
                } else if (p instanceof Professor) {
                    Professor f = (Professor) p;
                    pw.printf("Professor,%d,%s,%d,%s,%s,%d\n",
                            f.id, f.name, f.age, f.getDepartment(), f.getTitle(), f.getCoursesTaught());
                }
            }
            System.out.println("Saved all data!");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Adds a new Person based on user input.
     * Supported types: Student, Professor, TeachingAssistant.
     * Catches and reports input errors gracefully.
     */
    public static void addPerson() {
        try {
            System.out.println("Enter type: 1=Student, 2=Professor, 3=TeachingAssistant");
            int choice = Integer.parseInt(scanner.nextLine());
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                System.out.print("Program: ");
                String program = scanner.nextLine();
                System.out.print("Year: ");
                int year = Integer.parseInt(scanner.nextLine());
                System.out.print("GPA: ");
                double gpa = Double.parseDouble(scanner.nextLine());
                people.add(new Student(id, name, age, program, year, gpa));
            } else if (choice == 2) {
                System.out.print("Department: ");
                String dep = scanner.nextLine();
                System.out.print("Title: ");
                String title = scanner.nextLine();
                System.out.print("Courses taught: ");
                int c = Integer.parseInt(scanner.nextLine());
                people.add(new Professor(id, name, age, dep, title, c));
            } else if (choice == 3) {
                System.out.print("Program: ");
                String program = scanner.nextLine();
                System.out.print("Year: ");
                int year = Integer.parseInt(scanner.nextLine());
                System.out.print("GPA: ");
                double gpa = Double.parseDouble(scanner.nextLine());
                System.out.print("Assigned Course: ");
                String course = scanner.nextLine();
                System.out.print("TA Duties: ");
                int duties = Integer.parseInt(scanner.nextLine());
                people.add(new TeachingAssistant(id, name, age, program, year, gpa, course, duties));
            } else {
                System.out.println("Invalid type.");
            }
            System.out.println("Added!");
        } catch (Exception e) {
            System.out.println("Input error: " + e.getMessage());
        }
    }

    /**
     * Lists all people details to the console.
     * Prints message if list is empty.
     */
    public static void listPeople() {
        if (people.isEmpty()) {
            System.out.println("No people found.");
            return;
        }
        for (Person p : people) {
            System.out.println(p.toString());
        }
    }

    /**
     * Searches for a person by ID.
     * @param id - identifier to search for
     * @return Person if found; null otherwise
     */
    public static Person search(int id) {
        for (Person p : people) {
            if (p.id == id) return p;
        }
        return null;
    }

    /**
     * Searches for a person by name (case-insensitive).
     * @param name - name to search for
     * @return Person if found; null otherwise
     */
    public static Person search(String name) {
        for (Person p : people) {
            if (p.name.equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    /**
     * Removes a person by ID after user input.
     * Prints success or failure message.
     */
    public static void removePerson() {
        try {
            System.out.print("Enter ID to remove: ");
            int id = Integer.parseInt(scanner.nextLine());
            Person p = search(id);
            if (p != null) {
                people.remove(p);
                System.out.println("Removed.");
            } else {
                System.out.println("Not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Celebrates birthday for a person found by ID.
     * Increments age by 1 and prints confirmation.
     */
    public static void celebrateBirthday() {
        try {
            System.out.print("Enter ID to celebrate birthday: ");
            int id = Integer.parseInt(scanner.nextLine());
            Person p = search(id);
            if (p != null) p.celebrateBirthday();
            else System.out.println("Not found.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Displays performance evaluation for a person by ID.
     * Only works if person implements Evaluable interface.
     */
    public static void showEval() {
        try {
            System.out.print("Enter ID to evaluate: ");
            int id = Integer.parseInt(scanner.nextLine());
            Person p = search(id);
            if (p instanceof Evaluable) {
                System.out.println(((Evaluable) p).evaluatePerformance());
            } else {
                System.out.println("Not evaluable.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Recursive factorial calculation.
     * @param a non-negative integer
     * @return factorial of a
     * @throws IllegalArgumentException for negative input
     */
    public static int factorial(int a) {
        if (a < 0) throw new IllegalArgumentException("Factorial not defined for < 0");
        if (a == 0 || a == 1) return 1;
        return a * factorial(a - 1);
    }

    /**
     * Recursive countdown from n to 1, then prints "Go!"
     * @param n starting number of countdown (>0)
     */
    public static void countdown(int n) {
        if (n > 0) {
            System.out.print(n + " ");
            countdown(n - 1);
        } else System.out.println("Go!");
    }

    /**
     * Main method - program entry point.
     * Displays menu and reads user choices in a loop.
     * Invokes corresponding feature methods.
     */
    public static void main(String[] args) {
        loadFromFile();
        System.out.println("Demo: factorial(5)=" + factorial(5)); // Demo factorial calculation

        while (true) {
            System.out.println("\n=== People Management System ===");
            System.out.println("1. Add Person");
            System.out.println("2. List People");
            System.out.println("3. Search Person by ID");
            System.out.println("4. Search Person by Name");
            System.out.println("5. Remove Person");
            System.out.println("6. Celebrate Birthday");
            System.out.println("7. Show Performance Evaluation");
            System.out.println("8. Run Countdown (Recursion Demo)");
            System.out.println("9. Save & Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1: addPerson(); break;
                    case 2: listPeople(); break;
                    case 3:
                        System.out.print("ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Person p = search(id);
                        System.out.println(p != null ? p : "Not found.");
                        break;
                    case 4:
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        Person p2 = search(name);
                        System.out.println(p2 != null ? p2 : "Not found.");
                        break;
                    case 5: removePerson(); break;
                    case 6: celebrateBirthday(); break;
                    case 7: showEval(); break;
                    case 8:
                        System.out.print("Countdown from: ");
                        int n = Integer.parseInt(scanner.nextLine());
                        countdown(n);
                        break;
                    case 9:
                        saveToFile();
                        System.out.println("Thanks! Exiting.");
                        return;
                    default: System.out.println("Invalid menu option.");
                }
            } catch (Exception e) {
                System.out.println("Menu input error: " + e.getMessage());
            }
        }
    }
}
