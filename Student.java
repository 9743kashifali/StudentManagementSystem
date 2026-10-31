package project.model;

/**
 * Base Student class demonstrating Encapsulation.
 * All fields are private and accessed through getters/setters.
 */
public class Student {

    private int id;
    private String name;
    private int semester;
    private double gpa;

    // Constructor
    public Student(int id, String name, int semester, double gpa) {
        this.id       = id;
        this.name     = name;
        this.semester = semester;
        this.gpa      = gpa;
    }

    // ── Getters ──────────────────────────────────────────────────────────────
    public int    getId()       { return id; }
    public String getName()     { return name; }
    public int    getSemester() { return semester; }
    public double getGpa()      { return gpa; }

    // ── Setters ──────────────────────────────────────────────────────────────
    public void setId(int id)           { this.id = id; }
    public void setName(String name)    { this.name = name; }
    public void setSemester(int sem)    { this.semester = sem; }
    public void setGpa(double gpa)      { this.gpa = gpa; }

    /**
     * Polymorphic display method – overridden in sub-classes.
     */
    public void displayInfo() {
        System.out.println("=== Student Information ===");
        System.out.println("ID       : " + id);
        System.out.println("Name     : " + name);
        System.out.println("Semester : " + semester);
        System.out.printf ("GPA      : %.2f%n", gpa);
    }

    /**
     * Returns a formatted string summary (used by the GUI text area).
     */
    public String getSummary() {
        return String.format(
            "ID: %d%nName: %s%nSemester: %d%nGPA: %.2f%n",
            id, name, semester, gpa
        );
    }
}
