/**
 * MAD204 – Assignment 1 PARTA
 * Name: Ramandeep Singh
 * Student ID: A00194321
 * Date of Submission: 05/10/2025
 *
 * Description:
 * TeachingAssistant class, extends Student for additional TA duties and
 * assigned course, with custom introduction and performance criteria.
 */

/**
 * Represents a teaching assistant with assigned course and TA duties.
 * @author Ramandeep Singh
 */
public class TeachingAssistant extends Student {
    private String assignedCourse;
    private int taDuties;

    /**
     * Constructor for TeachingAssistant.
     * @param id unique TA id
     * @param name TA name
     * @param age age
     * @param program academic program
     * @param year study year
     * @param gpa GPA value
     * @param assignedCourse assigned course for TA
     * @param taDuties duty count
     */
    public TeachingAssistant(int id, String name, int age, String program, int year, double gpa, String assignedCourse, int taDuties) {
        super(id, name, age, program, year, gpa);
        this.assignedCourse = assignedCourse;
        this.taDuties = taDuties;
    }

    /**
     * Prints an introduction for the teaching assistant.
     */
    @Override
    public void introduce() {
        System.out.println("I'm TA " + name + ", assigned to " + assignedCourse + ".");
    }

    /**
     * Returns performance as TA, based on GPA and duties.
     * @return TA performance string
     */
    @Override
    public String evaluatePerformance() {
        String result = (gpa >= 3.5 && taDuties >= 2) ? "Outstanding TA" : "Good TA";
        return name + " performance: " + result;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", TA [Course:%s, Duties:%d]", assignedCourse, taDuties);
    }

    /** @return assigned course */
    public String getAssignedCourse() { return assignedCourse; }
    /** @return TA duty count */
    public int getTaDuties() { return taDuties; }
}
