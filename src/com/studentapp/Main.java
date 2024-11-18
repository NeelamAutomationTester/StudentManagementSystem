package com.studentapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.studentapp.Student;

public class Main {

	private static List<Student> studentList = new ArrayList<Student>();

	public static void main(String[] args) {
		System.out.println("********** Student Management System **********");
		
		while (true) {
			// Read input from terminal
			System.out.println("********** Welcome **********");
			Scanner scan = new Scanner(System.in);
			System.out.println("Select option:");
			System.out.println("1.Register a student");
			System.out.println("2.Find student with student id");
			System.out.println("3.List all student information");
			System.out.println("4.List student information in sorted order");
			System.out.println("5.Exit");

			int option = scan.nextInt();

			switch (option) {
			case 1:
				enrollStudent(scan);
				break;
			case 2:findStudentById(scan);
				break;
			case 3:printAllStudentInformation();
				break;
			case 4:sortByName();
				break;
			case 5:exit();
				break;
			default:
				System.out.println("Invalid option selected!!");
			}
		}
	

	}

	private static void exit() {
		System.out.println("Good Bye!!");
		System.exit(0);
		
	}

	private static void findStudentById(Scanner scan1) {
		
		System.out.println("Enter studentId:");
		String studentId = scan1.next();
		Student studentFound = null;
		try {
			studentFound = studentList.stream().filter(student -> student.getStudentId().equalsIgnoreCase(studentId)).findFirst()
					.orElseThrow(() -> new RuntimeException("No Data Found!!"));

		} catch (RuntimeException e) {
			System.err.println("Student with studentId " + studentId + " not found!!");
		}
		studentFound.printStudentInfo();
	}



	private static void printAllStudentInformation() {
		if(studentList.size()>0) {
		System.out.println("----------------------------- Printing All Student Information -----------------------------");
		for(Student student:studentList) {
			student.printStudentInfo();
		}
		System.out.println("----------------------------- ********************************* -----------------------------");
		}else {
			System.err.println("Student list is empty..No student record found!!");
		}
		
	}

	private static void enrollStudent(Scanner scan1) {

		System.out.println("Enter student name:");
		String name = scan1.next();

		System.out.println("Enter student age:");
		int age = scan1.nextInt();

		System.out.println("Enter student id:");
		String studentId = scan1.next();

		Student newStudent = new Student(name, age, studentId);
		studentList.add(newStudent);

		while (true) {
			System.out.println("Enter the course name to be enrolled:...Type'done' to exit!");
			String course = scan1.next();
			if (course.equalsIgnoreCase("done"))
				break;
			newStudent.enrollCourse(course);
		}
		newStudent.printStudentInfo();
	}

	private static void sortByName() {
		Comparator<Student> studentNameComparator = new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {

				return o1.getName().compareTo(o2.getName());// compares lexographically meaning alphabetically.
			}
		};
		/*
		 * The above line 47-54 can be written using lambda expressions as follows:
		 * 
		 * Comparator<Student> studentNameComparator = (o1,o2) ->
		 * o1.getName().compareTo(o2.getName());
		 */

		Collections.sort(studentList, studentNameComparator);
		//System.out.println(studentList);
		printAllStudentInformation();

	}

	public static Student findStudentById(String studentId) {

		Student result = null;
		try {
			result = studentList.stream().filter(x -> x.getStudentId().equalsIgnoreCase(studentId)).findFirst()
					.orElseThrow(() -> new RuntimeException("No Data Found!!"));

		} catch (RuntimeException e) {
			System.err.println("Student with studentId " + studentId + " not found!!");
		}
		return result;
	}

}
