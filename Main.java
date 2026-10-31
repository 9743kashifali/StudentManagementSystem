package project.app;

import project.gui.MainFrame;
import project.model.GraduateStudent;
import project.model.Student;
import project.model.UndergraduateStudent;

import javax.swing.*;

/**
 * Application entry point.
 *
 * Also demonstrates polymorphism via console output before the GUI launches.
 */
public class Main {

    public static void main(String[] args) {

        // ── Polymorphism demo (console) ───────────────────────────────────────
        Student s;

        s = new UndergraduateStudent(101, "Ali Khan", 5, 3.45, 90);
        s.displayInfo();   // calls UndergraduateStudent.displayInfo()
        System.out.println();

        s = new GraduateStudent(202, "Sara Ahmed", 3, 3.80, "Machine Learning");
        s.displayInfo();   // calls GraduateStudent.displayInfo()
        System.out.println();

        // ── Launch Swing GUI on the Event Dispatch Thread ─────────────────────
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
