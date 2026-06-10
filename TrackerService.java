package service;

import model.Course;
import model.Grade;
import model.Student;

import java.util.*;

public class TrackerService {

    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Grade> grades = new ArrayList<>();

    private Map<Integer, Student> studentMap = new HashMap<>();

    public void addStudent(Student student) {
        students.add(student);
        studentMap.put(student.getId(), student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void assignGrade(int studentId, String courseCode, int score) {

        if (score < 0 || score > 100) {
            System.out.println("Score must be between 0 and 100!");
            return;
        }

        Student student = studentMap.get(studentId);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        Course selectedCourse = null;

        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse == null) {
            System.out.println("Course not found!");
            return;
        }

        grades.add(new Grade(student, selectedCourse, score));

        System.out.println("Grade added successfully.");
    }

    public double getStudentGpa(int studentId) {

        int sum = 0;
        int count = 0;

        for (Grade grade : grades) {
            if (grade.getStudent().getId() == studentId) {
                sum += grade.getScore();
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return (double) sum / count;
    }

    public List<Student> getStudentsAboveGpa(double threshold) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {

            if (getStudentGpa(student.getId()) > threshold) {
                result.add(student);
            }
        }

        return result;
    }

    public List<Student> getTop3Students() {

        List<Student> sortedStudents = new ArrayList<>(students);

        sortedStudents.sort((s1, s2) ->
                Double.compare(
                        getStudentGpa(s2.getId()),
                        getStudentGpa(s1.getId())
                ));

        return sortedStudents.subList(
                0,
                Math.min(3, sortedStudents.size())
        );
    }

    public void showCourseAnalytics() {

        for (Course course : courses) {

            int sum = 0;
            int count = 0;

            for (Grade grade : grades) {

                if (grade.getCourse().getCourseCode()
                        .equals(course.getCourseCode())) {

                    sum += grade.getScore();
                    count++;
                }
            }

            double average = count == 0 ? 0 : (double) sum / count;

            System.out.println(
                    course.getCourseName()
                            + " -> Average Score: "
                            + average
            );
        }
    }
}