package main;

import model.Course;
import model.Student;
import service.TrackerService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        TrackerService service = new TrackerService();

        while (true) {

            System.out.println("\n===== STUDENT TRACKER =====");

            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Assign Grade");
            System.out.println("4. Show Student GPA");
            System.out.println("5. Show Top 3 Students");
            System.out.println("6. Course Analytics");
            System.out.println("7. Exit");

            System.out.print("Choose: ");

            try {

                int choice =
                        Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:

                        System.out.print("Student ID: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Group: ");
                        String group = scanner.nextLine();

                        service.addStudent(
                                new Student(id, name, group)
                        );

                        break;

                    case 2:

                        System.out.print("Course Code: ");
                        String code = scanner.nextLine();

                        System.out.print("Course Name: ");
                        String courseName = scanner.nextLine();

                        service.addCourse(
                                new Course(code, courseName)
                        );

                        break;

                    case 3:

                        System.out.print("Student ID: ");
                        int studentId =
                                Integer.parseInt(scanner.nextLine());

                        System.out.print("Course Code: ");
                        String courseCode =
                                scanner.nextLine();

                        System.out.print("Score: ");
                        int score =
                                Integer.parseInt(scanner.nextLine());

                        service.assignGrade(
                                studentId,
                                courseCode,
                                score
                        );

                        break;

                    case 4:

                        System.out.print("Student ID: ");

                        int gpaId =
                                Integer.parseInt(scanner.nextLine());

                        System.out.println(
                                "GPA = "
                                        + service.getStudentGpa(gpaId)
                        );

                        break;

                    case 5:

                        System.out.println("Top 3 Students:");

                        for (Student student :
                                service.getTop3Students()) {

                            System.out.println(
                                    student.getName()
                                            + " GPA: "
                                            + service.getStudentGpa(
                                            student.getId()
                                    )
                            );
                        }

                        break;

                    case 6:

                        service.showCourseAnalytics();

                        break;

                    case 7:

                        System.out.println("Program finished.");
                        return;

                    default:

                        System.out.println("Invalid option.");
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number!"
                );
            }
        }
    }
}