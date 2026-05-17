import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class SchoolManagementSystem {

    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "123";

    // ================= STUDENT CLASS =================
    static class Student {
        int rollNo, marks;
        String name, fatherName, cnic, phone, email, cast, lastDegree;
        String department, subject;

        Student(int rollNo, String name, String fatherName, String cnic,
                String phone, String email, String cast, String lastDegree,
                String department, int marks) {

            this.rollNo = rollNo;
            this.name = name;
            this.fatherName = fatherName;
            this.cnic = cnic;
            this.phone = phone;
            this.email = email;
            this.cast = cast;
            this.lastDegree = lastDegree;
            this.department = department;
            this.marks = marks;
            this.subject = "Not Assigned";
        }
    }

    static ArrayList<Student> students = new ArrayList<>();

    static String[] courses = {
            "Computer Science",
            "Information Technology",
            "Business Administration",
            "Commerce",
            "Arts"
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SchoolManagementSystem::welcomeScreen);
    }

    // ================= WELCOME =================
    static void welcomeScreen() {
        JFrame frame = new JFrame();
        frame.setSize(500, 250);
        frame.setLayout(new BorderLayout());
        frame.setUndecorated(true);

        JLabel title = new JLabel("Bedal Bekus Model High School", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.BLUE);

        JLabel dev = new JLabel("Developed by Ahmed © 2026", JLabel.CENTER);

        frame.add(title, BorderLayout.CENTER);
        frame.add(dev, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Timer(2500, e -> {
            frame.dispose();
            mainMenu();
        }).start();
    }

    // ================= MAIN MENU =================
    static void mainMenu() {
        JFrame frame = new JFrame("Main Menu");
        frame.setSize(350, 300);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        JButton student = new JButton("Student Panel");
        JButton teacher = new JButton("Teacher Login");
        JButton about = new JButton("About");
        JButton exit = new JButton("Exit");

        frame.add(student);
        frame.add(teacher);
        frame.add(about);
        frame.add(exit);

        student.addActionListener(e -> studentPanel());
        teacher.addActionListener(e -> teacherLogin());
        about.addActionListener(e ->
                JOptionPane.showMessageDialog(frame,
                        "Java Swing Project\nSchool Management System"));
        exit.addActionListener(e -> System.exit(0));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // ================= TEACHER LOGIN =================
    static void teacherLogin() {
        JTextField user = new JTextField();
        JPasswordField pass = new JPasswordField();

        Object[] fields = {"Username:", user, "Password:", pass};

        int option = JOptionPane.showConfirmDialog(null, fields,
                "Teacher Login", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            if (user.getText().equals(ADMIN_USER)
                    && String.valueOf(pass.getPassword()).equals(ADMIN_PASS)) {
                teacherPanel();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Login");
            }
        }
    }

    // ================= STUDENT PANEL =================
    static void studentPanel() {
        JFrame frame = new JFrame("Student Panel");
        frame.setSize(400, 350);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        JButton add = new JButton("New Admission");
        JButton info = new JButton("View Personal Info");
        JButton result = new JButton("View Result");
        JButton back = new JButton("Back");

        frame.add(add);
        frame.add(info);
        frame.add(result);
        frame.add(back);

        add.addActionListener(e -> addStudent());
        info.addActionListener(e -> searchStudent());
        result.addActionListener(e -> viewResult());
        back.addActionListener(e -> frame.dispose());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // ================= TEACHER PANEL =================
    static void teacherPanel() {
        JFrame frame = new JFrame("Teacher Panel");
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(5, 1, 10, 10));

        JButton view = new JButton("View Students");
        JButton search = new JButton("Search Student");
        JButton marks = new JButton("Update Marks");
        JButton subject = new JButton("Assign Subject");
        JButton logout = new JButton("Logout");

        frame.add(view);
        frame.add(search);
        frame.add(marks);
        frame.add(subject);
        frame.add(logout);

        view.addActionListener(e -> viewStudents());
        search.addActionListener(e -> searchStudent());
        marks.addActionListener(e -> updateMarks());
        subject.addActionListener(e -> assignSubject());
        logout.addActionListener(e -> frame.dispose());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // ================= ADD STUDENT =================
    static void addStudent() {
        try {
            JTextField roll = new JTextField();
            JTextField name = new JTextField();
            JTextField fname = new JTextField();
            JTextField cnic = new JTextField();
            JTextField phone = new JTextField();
            JTextField email = new JTextField();
            JTextField cast = new JTextField();
            JTextField degree = new JTextField();
            JTextField marks = new JTextField();
            JComboBox<String> dept = new JComboBox<>(courses);

            Object[] fields = {
                    "Roll No:", roll,
                    "Student Name:", name,
                    "Father Name:", fname,
                    "CNIC:", cnic,
                    "Phone:", phone,
                    "Email:", email,
                    "Cast:", cast,
                    "Last Degree:", degree,
                    "Department:", dept,
                    "Marks:", marks
            };

            int option = JOptionPane.showConfirmDialog(null, fields,
                    "New Admission", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                students.add(new Student(
                        Integer.parseInt(roll.getText()),
                        name.getText(),
                        fname.getText(),
                        cnic.getText(),
                        phone.getText(),
                        email.getText(),
                        cast.getText(),
                        degree.getText(),
                        dept.getSelectedItem().toString(),
                        Integer.parseInt(marks.getText())
                ));
                JOptionPane.showMessageDialog(null, "Admission Successful");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid Input");
        }
    }

    // ================= VIEW STUDENTS =================
    static void viewStudents() {
        StringBuilder data = new StringBuilder();
        for (Student s : students) {
            data.append("Roll: ").append(s.rollNo)
                .append("\nName: ").append(s.name)
                .append("\nFather: ").append(s.fatherName)
                .append("\nCNIC: ").append(s.cnic)
                .append("\nPhone: ").append(s.phone)
                .append("\nEmail: ").append(s.email)
                .append("\nCast: ").append(s.cast)
                .append("\nLast Degree: ").append(s.lastDegree)
                .append("\nDepartment: ").append(s.department)
                .append("\nMarks: ").append(s.marks)
                .append("\nSubject: ").append(s.subject)
                .append("\n------------------------\n");
        }
        JOptionPane.showMessageDialog(null, data.length() == 0 ? "No Records" : data.toString());
    }

    static void searchStudent() {
        try {
            int r = Integer.parseInt(JOptionPane.showInputDialog("Enter Roll No"));
            for (Student s : students) {
                if (s.rollNo == r) {
                    JOptionPane.showMessageDialog(null,
                            "Name: " + s.name +
                            "\nFather: " + s.fatherName +
                            "\nCNIC: " + s.cnic +
                            "\nPhone: " + s.phone +
                            "\nEmail: " + s.email +
                            "\nDepartment: " + s.department);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Student Not Found");
        } catch (Exception ignored) {}
    }

    static void viewResult() {
        try {
            int r = Integer.parseInt(JOptionPane.showInputDialog("Enter Roll No"));
            for (Student s : students) {
                if (s.rollNo == r) {
                    char grade = (s.marks >= 80) ? 'A' :
                                 (s.marks >= 70) ? 'B' :
                                 (s.marks >= 60) ? 'C' :
                                 (s.marks >= 50) ? 'D' : 'F';

                    JOptionPane.showMessageDialog(null,
                            "Name: " + s.name +
                            "\nMarks: " + s.marks +
                            "\nGrade: " + grade);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Result Not Found");
        } catch (Exception ignored) {}
    }

    static void updateMarks() {
        try {
            int r = Integer.parseInt(JOptionPane.showInputDialog("Enter Roll No"));
            for (Student s : students) {
                if (s.rollNo == r) {
                    s.marks = Integer.parseInt(
                            JOptionPane.showInputDialog("Enter New Marks"));
                    JOptionPane.showMessageDialog(null, "Marks Updated");
                    return;
                }
            }
        } catch (Exception ignored) {}
    }

    static void assignSubject() {
        try {
            int r = Integer.parseInt(JOptionPane.showInputDialog("Enter Roll No"));
            for (Student s : students) {
                if (s.rollNo == r) {
                    s.subject = JOptionPane.showInputDialog("Enter Subject");
                    JOptionPane.showMessageDialog(null, "Subject Assigned");
                    return;
                }
            }
        } catch (Exception ignored) {}
    }
}
