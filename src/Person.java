/**
 * MAD204 – Assignment 1 PARTA
 * Name: Ramandeep Singh
 * Student ID: A00194321
 * Date of Submission: 05/10/2025
 *
 * Description:
 * Abstract base class for people (students, professors, TAs) in the
 * People Management System. Supports common fields and methods.
 */

/**
 * The Person class is used as a base for all people in the system.
 * @author Ramandeep Singh
 */
public abstract class Person {
    protected int id;
    protected String name;
    protected int age;

    /**
     * Person constructor sets required fields.
     * @param id unique identifier
     * @param name person's name
     * @param age person's age (must be > 0)
     */
    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        setAge(age);
    }

    /**
     * Abstract method for introducing a person.
     */
    public abstract void introduce();

    /**
     * Increases person's age by 1.
     */
    public void celebrateBirthday() {
        age++;
        System.out.println(name + " now turned " + age + "!");
    }

    /**
     * Ensures valid age (> 0)
     * @param age - positive age value
     */
    public final void setAge(int age) {
        if (age <= 0) throw new IllegalArgumentException("Age must be positive.");
        this.age = age;
    }

    /**
     * Converts person's data to a display string.
     * @return formatted info string
     */
    @Override
    public String toString() {
        return String.format("ID:%d, Name:%s, Age:%d", id, name, age);
    }
}
