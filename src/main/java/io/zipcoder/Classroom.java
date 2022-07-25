package io.zipcoder;

import java.util.*;

public class Classroom {

    Student[] students;
    int maxNumberOfStudents;
    List<Student> studentList = new ArrayList<>();

    public Classroom(Student[] students){
        this.students = students;
    }

    public Classroom(int maxNumberOfStudents){
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    public Classroom(){
        students = new Student[30];
    }

    public Student[] getStudents(){
        students = studentList.toArray(students);
        return this.students;
    }

    public double getAverageExamScore(){
        int total = 0;
        double average = 0;
        for (int i = 0; i<students.length; i++){
            total += students[i].getAverageExamScore();
            average = (double)total/students.length;
        }return average;
    }

    public void addStudent(Student student){

        studentList.add(student);
    }

    public void removeStudent (String firstName, String lastName){
        //Student[] tempArray = new Student[students.length];
        List<Student> tempList = new ArrayList<>();
        int arrLength = students.length;
        for (int i = 0; i<students.length; i++) {
            if (!Objects.equals(students[i].getFirstName(), firstName) &&
                    !Objects.equals(students[i].getLastName(), lastName)){
                tempList.add(students[i]);
            }
        }
        //students = null;
        students = new Student[arrLength];
        students = tempList.toArray(students);
    }

    public Student[] getStudentsByScore(){
        boolean swapped = true;
        while (swapped){
            swapped = false;
            for (int i = 0; i < students.length-1; i++){
                if(students[i].getAverageExamScore()<students[i+1].getAverageExamScore()){
                    Student temp = students[i];
                    students[i] = students[i+1];
                    students[i+1] = temp;
                    swapped = true;
                }
            }
        } return students;
    }

    public Map<Student, String> getGradeBook(){
        double percentile;
        Map<Student, String> gradeBook = new HashMap<>();
        int totalGrades = getStudentsByScore().length;
        Student[] sortedStudent = this.getStudentsByScore();
        for (int i = 0; i<totalGrades; i++){
            percentile = ((i-totalGrades)*(-1))/(totalGrades)*100;
            if(percentile >= 90){
                gradeBook.put(sortedStudent[i], "A");
            } else if (percentile <= 89 && percentile >= 71){
                gradeBook.put(sortedStudent[i], "B");
            } else if (percentile <= 70 && percentile >= 50){
                gradeBook.put(sortedStudent[i], "C");
            } else if (percentile <= 49 && percentile >= 11){
                gradeBook.put(sortedStudent[i], "D");
            } else {
                gradeBook.put(sortedStudent[i], "F");
            }
        }return gradeBook;
    }

}
