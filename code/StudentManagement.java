package dsa.src.code;

import java.util.*;
import java.io.*;

class Student {
    int id;
    String name;
    double marks;
    String ranking;

    // Constructor
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.ranking = determineRanking(marks);
    }

    // Method to determine the ranking based on marks
    private String determineRanking(double marks) {
        if (marks < 5.0) return "Fail";
        if (marks < 6.5) return "Medium";
        if (marks < 7.5) return "Good";
        if (marks < 9.0) return "Very Good";
        return "Excellent";
    }

    // Display student details
    public void displayStudent() {
        System.out.println("ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + ranking);
    }
}

// Custom exception for student data errors
class StudentException extends Exception {
    public StudentException(String message) {
        super(message);
    }
}

public class StudentManagement {
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Choose an option:");
                System.out.println("1. Add Student");
                System.out.println("2. Edit Student");
                System.out.println("3. Delete Student");
                System.out.println("4. View All Students");
                System.out.println("5. Search Student");
                System.out.println("6. Exit");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addStudent(scanner);
                        break;
                    case 2:
                        editStudent(scanner);
                        break;
                    case 3:
                        deleteStudent(scanner);
                        break;
                    case 4:
                        viewAllStudents();
                        break;
                    case 5:
                        searchStudent(scanner);
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the buffer
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Method to add a student
    private static void addStudent(Scanner scanner) throws StudentException {
        try {
            System.out.print("Enter Student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Student Marks: ");
            double marks = scanner.nextDouble();

            // Validate input
            if (marks < 0 || marks > 10) {
                throw new StudentException("Marks should be between 0 and 10.");
            }

            students.add(new Student(id, name, marks));
            System.out.println("Student added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for student details. Please try again.");
            scanner.nextLine(); // Clear the buffer
        } catch (StudentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to edit a student
    private static void editStudent(Scanner scanner) {
        try {
            System.out.print("Enter Student ID to edit: ");
            int id = scanner.nextInt();
            Optional<Student> student = students.stream()
                .filter(s -> s.id == id)
                .findFirst();

            if (student.isPresent()) {
                scanner.nextLine(); // Consume newline
                System.out.print("Enter new name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new marks: ");
                double marks = scanner.nextDouble();

                if (marks < 0 || marks > 10) {
                    throw new StudentException("Marks should be between 0 and 10.");
                }

                student.get().name = name;
                student.get().marks = marks;
                student.get().ranking = student.get().determineRanking(marks);
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data.");
            scanner.nextLine(); // Clear the buffer
        } catch (StudentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to delete a student
    private static void deleteStudent(Scanner scanner) {
        try {
            System.out.print("Enter Student ID to delete: ");
            int id = scanner.nextInt();
            boolean removed = students.removeIf(s -> s.id == id);

            if (removed) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    // Method to view all students
    private static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students) {
                student.displayStudent();
            }
        }
    }

    // Method to search for a student
    private static void searchStudent(Scanner scanner) {
        try {
            System.out.print("Enter Student ID to search: ");
            int id = scanner.nextInt();
            Optional<Student> student = students.stream()
                .filter(s -> s.id == id)
                .findFirst();

            if (student.isPresent()) {
                student.get().displayStudent();
            } else {
                System.out.println("Student not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid ID.");
            scanner.nextLine(); // Clear the buffer
        }
    }
}
