/**
 * MAD204 – Assignment 1 PARTA
 * Name: Ramandeep Singh
 * Student ID: A00194321
 * Date of Submission: 05/10/2025
 *
 * Description:
 * Student class, representing a student in the system, with program,
 * year, GPA, and implements Evaluable for performance checks.
 */

/**
 * Represents a student with academic info, extending Person.
 * @author Ramandeep Singh
 */
public class Student extends Person implements Evaluable {
    protected String program;
    protected int year;
    protected double gpa;

    /**
     * Detailed constructor for Student object.
     * @param id unique student ID
     * @param name student name
     * @param age student age (>0)
     * @param program study program
     * @param year study year
     * @param gpa overall GPA (0.0–4.0)
     */
    public Student(int id, String name, int age, String program, int year, double gpa) {
        super(id, name, age);
        this.program = program;
        this.year = year;
        setGpa(gpa); // Validates range
    }

    /**
     * Simpler constructor (overloaded) for Student.
     * @param id student id
     * @param name name
     * @param age age (>0)
     */
    public Student(int id, String name, int age) {
        this(id, name, age, "Unknown", 1, 0.0);
    }

    /**
     * Prints an introduction for the student.
     */
    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + name + ", a " + year + " year student in " + program + ".");
    }

    /**
     * Calculates letter grade based on GPA.
     * @return string indicating letter grade
     */
    @Override
    public String evaluatePerformance() {
        String grade;
        if (gpa >= 3.7) grade = "A";
        else if (gpa >= 3.0) grade = "B";
        else if (gpa >= 2.0) grade = "C";
        else grade = "D";
        return name + "'s letter grade: " + grade;
    }

    /**
     * Validates and sets student GPA
     * @param gpa GPA value (0.0–4.0)
     */
    public final void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) throw new IllegalArgumentException("GPA must be 0.0 - 4.0");
        this.gpa = gpa;
    }

    /**
     * Displays student info.
     * @return formatted string
     */
    @Override
    public String toString() {
        return super.toString() + String.format(", Student [%s, Year:%d, GPA:%.2f]", program, year, gpa);
    }
}
