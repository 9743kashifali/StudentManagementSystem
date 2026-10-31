package project.model;

/**
 * UndergraduateStudent – extends Student (Inheritance).
 * Adds creditHours and overrides displayInfo() (Polymorphism).
 */
public class UndergraduateStudent extends Student {

    private int creditHours;

    public UndergraduateStudent(int id, String name, int semester,
                                double gpa, int creditHours) {
        super(id, name, semester, gpa);
        this.creditHours = creditHours;
    }

    // ── Getter / Setter ───────────────────────────────────────────────────────
    public int getCreditHours()              { return creditHours; }
    public void setCreditHours(int hours)    { this.creditHours = hours; }

    // ── Polymorphism: overridden displayInfo() ────────────────────────────────
    @Override
    public void displayInfo() {
        System.out.println("=== Undergraduate Student ===");
        System.out.println("ID           : " + getId());
        System.out.println("Name         : " + getName());
        System.out.println("Semester     : " + getSemester());
        System.out.printf ("GPA          : %.2f%n", getGpa());
        System.out.println("Credit Hours : " + creditHours);
    }

    @Override
    public String getSummary() {
        return super.getSummary()
             + "Type         : Undergraduate\n"
             + "Credit Hours : " + creditHours + "\n";
    }
}
