/**
 * MAD204 – Assignment 1 PARTA
 * Name: Ramandeep Singh
 * Student ID: A00194321
 * Date of Submission: 05/10/2025
 *
 * Description:
 * Professor class for teaching staff, includes department, title,
 * courses taught, and performance evaluation method.
 */

/**
 * Represents a professor with department and teaching info.
 * @author Ramandeep Singh
 */
public class Professor extends Person implements Evaluable {
    private String department;
    private String title;
    private int coursesTaught;

    /**
     * Constructor for Professor class.
     * @param id unique professor id
     * @param name name
     * @param age age (>0)
     * @param department department name
     * @param title professor title
     * @param coursesTaught number of courses taught
     */
    public Professor(int id, String name, int age, String department, String title, int coursesTaught) {
        super(id, name, age);
        this.department = department;
        this.title = title;
        this.coursesTaught = coursesTaught;
    }

    /**
     * Prints an introduction for the professor.
     */
    @Override
    public void introduce() {
        System.out.println("Hello, I'm Prof. " + name + ", " + title + " of " + department + ".");
    }

    /**
     * Returns performance based on courses taught.
     * @return string evaluating performance
     */
    @Override
    public String evaluatePerformance() {
        return name + " performance: " + (coursesTaught >= 3 ? "Excellent" : (coursesTaught == 2 ? "Good" : "Average"));
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Professor [%s, %s, Courses:%d]", department, title, coursesTaught);
    }

    /** @return department name */
    public String getDepartment() { return department; }
    /** @return professor title */
    public String getTitle() { return title; }
    /** @return courses taught */
    public int getCoursesTaught() { return coursesTaught; }
}
