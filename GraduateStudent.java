package project.model;

/**
 * GraduateStudent – extends Student (Inheritance).
 * Adds researchArea and overrides displayInfo() (Polymorphism).
 */
public class GraduateStudent extends Student {

    private String researchArea;

    public GraduateStudent(int id, String name, int semester,
                           double gpa, String researchArea) {
        super(id, name, semester, gpa);
        this.researchArea = researchArea;
    }

    // ── Getter / Setter ───────────────────────────────────────────────────────
    public String getResearchArea()              { return researchArea; }
    public void   setResearchArea(String area)   { this.researchArea = area; }

    // ── Polymorphism: overridden displayInfo() ────────────────────────────────
    @Override
    public void displayInfo() {
        System.out.println("=== Graduate Student ===");
        System.out.println("ID            : " + getId());
        System.out.println("Name          : " + getName());
        System.out.println("Semester      : " + getSemester());
        System.out.printf ("GPA           : %.2f%n", getGpa());
        System.out.println("Research Area : " + researchArea);
    }

    @Override
    public String getSummary() {
        return super.getSummary()
             + "Type          : Graduate\n"
             + "Research Area : " + researchArea + "\n";
    }
}
