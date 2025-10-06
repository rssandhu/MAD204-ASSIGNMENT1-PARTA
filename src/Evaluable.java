/**
 * MAD204 – Assignment 1 PARTA
 * Name: Ramandeep Singh
 * Student ID: A00194321
 * Date of Submission: 05/10/2025
 *
 * Description:
 * Interface for evaluating performance of students, professors, and TAs.
 */

/**
 * Contract for performance evaluation in the system.
 * @author Ramandeep Singh
 */
public interface Evaluable {
    /**
     * Evaluate a person's performance in context.
     * @return performance summary as string
     */
    String evaluatePerformance();
}
