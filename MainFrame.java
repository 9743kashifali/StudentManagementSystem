package project.gui;

import project.exception.InvalidGPAException;
import project.model.GraduateStudent;
import project.model.Student;
import project.model.UndergraduateStudent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * MainFrame – the primary Swing GUI for the Student Management System.
 *
 * Demonstrates:
 *   • Swing  : JFrame, JLabel, JTextField, JButton, JComboBox, JTextArea,
 *              JScrollPane, JPanel, JOptionPane
 *   • AWT    : Label, TextField, Button (placed inside an AWT demo Panel)
 *   • Event Handling: ActionListener on Add / Clear / Display All buttons
 */
public class MainFrame extends JFrame {

    // ── Swing input controls ──────────────────────────────────────────────────
    private JTextField  tfId, tfName, tfSemester, tfGpa, tfExtra;
    private JComboBox<String> cbType;
    private JTextArea   taOutput;
    private JLabel      lblExtra;

    // ── AWT controls (demo panel) ─────────────────────────────────────────────
    private Label    awtLabel;
    private TextField awtTextField;
    private Button   awtButton;

    // ── Data store ────────────────────────────────────────────────────────────
    private final List<Student> students = new ArrayList<>();

    // ─────────────────────────────────────────────────────────────────────────
    public MainFrame() {
        super("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        initUI();
    }

    // ── UI Construction ───────────────────────────────────────────────────────
    private void initUI() {
        // Root panel with padding
        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(new EmptyBorder(12, 12, 12, 12));
        root.setBackground(new Color(240, 245, 255));
        setContentPane(root);

        root.add(buildTitlePanel(),  BorderLayout.NORTH);
        root.add(buildFormPanel(),   BorderLayout.CENTER);
        root.add(buildOutputPanel(), BorderLayout.SOUTH);
    }

    /** Decorative title banner */
    private JPanel buildTitlePanel() {
        JPanel p = new JPanel();
        p.setBackground(new Color(41, 98, 175));
        JLabel lbl = new JLabel("Student Management System", SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lbl.setForeground(Color.WHITE);
        lbl.setBorder(new EmptyBorder(10, 0, 10, 0));
        p.add(lbl);
        return p;
    }

    /** Form + AWT demo combined in CENTER */
    private JPanel buildFormPanel() {
        JPanel wrapper = new JPanel(new BorderLayout(8, 8));
        wrapper.setOpaque(false);
        wrapper.add(buildSwingFormPanel(), BorderLayout.CENTER);
        wrapper.add(buildAwtDemoPanel(),   BorderLayout.SOUTH);
        return wrapper;
    }

    /** Swing input form */
    private JPanel buildSwingFormPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(41, 98, 175), 1),
            "Student Registration (Swing)",
            TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 13),
            new Color(41, 98, 175)));
        p.setBorder(new TitledBorder(p.getBorder(), "Student Registration (Swing)"));

        GridBagConstraints lc = new GridBagConstraints();
        lc.insets  = new Insets(6, 10, 6, 6);
        lc.anchor  = GridBagConstraints.WEST;
        lc.fill    = GridBagConstraints.NONE;

        GridBagConstraints fc = new GridBagConstraints();
        fc.insets  = new Insets(6, 0, 6, 10);
        fc.fill    = GridBagConstraints.HORIZONTAL;
        fc.weightx = 1.0;

        // Row 0 – Student ID
        lc.gridx = 0; lc.gridy = 0; p.add(new JLabel("Student ID:"),  lc);
        fc.gridx = 1; fc.gridy = 0; tfId = new JTextField(18); p.add(tfId, fc);

        // Row 1 – Name
        lc.gridy = 1; p.add(new JLabel("Name:"), lc);
        fc.gridy = 1; tfName = new JTextField(18); p.add(tfName, fc);

        // Row 2 – Semester
        lc.gridy = 2; p.add(new JLabel("Semester:"), lc);
        fc.gridy = 2; tfSemester = new JTextField(18); p.add(tfSemester, fc);

        // Row 3 – GPA
        lc.gridy = 3; p.add(new JLabel("GPA (0.0 – 4.0):"), lc);
        fc.gridy = 3; tfGpa = new JTextField(18); p.add(tfGpa, fc);

        // Row 4 – Type selector
        lc.gridy = 4; p.add(new JLabel("Student Type:"), lc);
        fc.gridy = 4;
        cbType = new JComboBox<>(new String[]{"Undergraduate", "Graduate"});
        cbType.addActionListener(e -> updateExtraField());
        p.add(cbType, fc);

        // Row 5 – Dynamic extra field (Credit Hours / Research Area)
        lc.gridy = 5; lblExtra = new JLabel("Credit Hours:"); p.add(lblExtra, lc);
        fc.gridy = 5; tfExtra = new JTextField(18); p.add(tfExtra, fc);

        // Row 6 – Buttons
        fc.gridy   = 6; fc.gridx = 0;
        fc.gridwidth = 2; fc.insets = new Insets(10, 10, 10, 10);
        p.add(buildButtonPanel(), fc);

        return p;
    }

    private JPanel buildButtonPanel() {
        JPanel bp = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        bp.setOpaque(false);

        JButton btnAdd      = makeButton("Add Student",    new Color(41, 128, 65));
        JButton btnClear    = makeButton("Clear Fields",   new Color(155, 89, 57));
        JButton btnShowAll  = makeButton("Display All",    new Color(41, 98, 175));

        // ── Event Handling ────────────────────────────────────────────────────
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        btnShowAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });

        bp.add(btnAdd);
        bp.add(btnClear);
        bp.add(btnShowAll);
        return bp;
    }

    private JButton makeButton(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    /** AWT demo panel (Label, TextField, Button) */
    private Panel buildAwtDemoPanel() {
        Panel awtPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 10, 6));

        // AWT border via a titled Swing border isn't directly possible on
        // java.awt.Panel, so we wrap it in a Swing JPanel for the border.
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(180, 100, 0), 1),
            "AWT Controls Demo",
            TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 12),
            new Color(180, 100, 0)));

        awtLabel     = new Label("AWT Search ID:");
        awtTextField = new TextField(10);
        awtButton    = new Button("Search");

        awtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudentByAWT();
            }
        });

        awtPanel.add(awtLabel);
        awtPanel.add(awtTextField);
        awtPanel.add(awtButton);

        wrapper.add(awtPanel, BorderLayout.CENTER);
        // Return the Swing wrapper cast as Panel isn't possible; return JPanel
        // and caller accepts Component. Rebuild as JPanel approach:
        return awtPanel;   // direct AWT panel – layout manager handles it fine
    }

    /** Scrollable output text area */
    private JPanel buildOutputPanel() {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        p.setOpaque(false);
        p.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(41, 98, 175), 1),
            "Output",
            TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 13),
            new Color(41, 98, 175)));

        taOutput = new JTextArea(9, 60);
        taOutput.setEditable(false);
        taOutput.setFont(new Font("Consolas", Font.PLAIN, 13));
        taOutput.setBackground(new Color(245, 248, 255));
        taOutput.setLineWrap(true);
        taOutput.setWrapStyleWord(true);

        p.add(new JScrollPane(taOutput), BorderLayout.CENTER);
        return p;
    }

    // ── Logic ─────────────────────────────────────────────────────────────────

    /** Swap the extra-field label when student type changes */
    private void updateExtraField() {
        boolean isUG = cbType.getSelectedItem().equals("Undergraduate");
        lblExtra.setText(isUG ? "Credit Hours:" : "Research Area:");
        tfExtra.setText("");
    }

    /** Validate, create and store a student – demonstrates Exception Handling */
    private void addStudent() {
        try {
            // ── Parse inputs ──────────────────────────────────────────────────
            if (tfId.getText().trim().isEmpty() || tfName.getText().trim().isEmpty()
                    || tfSemester.getText().trim().isEmpty()
                    || tfGpa.getText().trim().isEmpty()
                    || tfExtra.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "All fields are required.", "Input Error",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            int    id       = Integer.parseInt(tfId.getText().trim());
            String name     = tfName.getText().trim();
            int    semester = Integer.parseInt(tfSemester.getText().trim());
            double gpa      = Double.parseDouble(tfGpa.getText().trim());

            // ── GPA validation via custom exception ───────────────────────────
            if (gpa < 0.0 || gpa > 4.0) {
                throw new InvalidGPAException("GPA must be between 0.0 and 4.0");
            }

            // ── Polymorphism: create correct sub-type ─────────────────────────
            Student s;
            if (cbType.getSelectedItem().equals("Undergraduate")) {
                int creditHours = Integer.parseInt(tfExtra.getText().trim());
                s = new UndergraduateStudent(id, name, semester, gpa, creditHours);
            } else {
                String researchArea = tfExtra.getText().trim();
                s = new GraduateStudent(id, name, semester, gpa, researchArea);
            }

            students.add(s);
            s.displayInfo();   // Console output (polymorphic call)

            // ── Display in GUI ────────────────────────────────────────────────
            taOutput.append("✔ Student Added Successfully\n");
            taOutput.append("─".repeat(35) + "\n");
            taOutput.append(s.getSummary());
            taOutput.append("\n");

            clearFields();

        } catch (InvalidGPAException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                "Invalid GPA", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "ID, Semester, GPA and Credit Hours must be numeric values.",
                "Format Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Show all stored students in the output area */
    private void displayAllStudents() {
        if (students.isEmpty()) {
            taOutput.append("No students have been added yet.\n");
            return;
        }
        taOutput.append("═".repeat(35) + "\n");
        taOutput.append("       ALL STUDENTS\n");
        taOutput.append("═".repeat(35) + "\n");
        for (Student s : students) {
            taOutput.append(s.getSummary());
            taOutput.append("─".repeat(35) + "\n");
        }
    }

    /** AWT search: find a student by ID */
    private void searchStudentByAWT() {
        String raw = awtTextField.getText().trim();
        if (raw.isEmpty()) return;
        try {
            int searchId = Integer.parseInt(raw);
            for (Student s : students) {
                if (s.getId() == searchId) {
                    taOutput.append("🔍 AWT Search Result:\n");
                    taOutput.append(s.getSummary());
                    taOutput.append("─".repeat(35) + "\n");
                    return;
                }
            }
            taOutput.append("No student found with ID: " + searchId + "\n");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid numeric ID.",
                "Search Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Reset all form fields */
    private void clearFields() {
        tfId.setText("");
        tfName.setText("");
        tfSemester.setText("");
        tfGpa.setText("");
        tfExtra.setText("");
        cbType.setSelectedIndex(0);
        updateExtraField();
    }
}
