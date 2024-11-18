package com.studentapp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {

	private String name;
	private int age;
	private String studentId;
	private List<String> courses;

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getStudentId() {
		return studentId;
	}

	public List<String> getCourses() {
		return courses;
	}

	public Student(String name, int age, String studentId) {
		super();
		if(validateName(name) && validateAge(age) && validateStudentId(studentId)) {
		this.name = name;
		this.age = age;
		this.studentId = studentId;
		courses = new ArrayList<String>(); //Initialize List courses.
		}
	}
	
	private boolean validateStudentId(String studentId) {
		
		String studentIdRegEx = "S-[0-9]+$";//or "S-\\d+$" --]\\d is for digits
		Pattern studentIdPattern = Pattern.compile(studentIdRegEx);
		Matcher studentIdMatcher = studentIdPattern.matcher(studentId);
		if(studentIdMatcher.matches()) {
			return true;
		}else {
			System.err.println("Invalid studentid!! ");
			return false;
		}
		
	}

	public void enrollCourse(String course) {
		
		if(validateCourseName(course)) {
		if(!courses.contains(course)) {
			courses.add(course);
			System.out.println("Student enrolled for "+course+" successfully!");
		}else {
			System.err.println("Student has already enrolled for "+course+"!");
		}
	}
	}
	
	private boolean validateCourseName(String course) {
		
		if(course.equalsIgnoreCase("Java") || course.equalsIgnoreCase("DSA") || course.equalsIgnoreCase("DevOps")) {
			return true;
		}else {
			System.err.println("Invalid course name!! Please select valid course."+courses);
			return false;
		}
		
	
	}

	public void printStudentInfo() {
		
		System.out.println("========== Student Information ==========");
		System.out.println("Student Name: "+name);
		System.out.println("Student Age: "+age);
		System.out.println("Student Id: "+studentId);
		System.out.println("Enrolled for: "+courses);
		
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", studentId=" + studentId + ", courses=" + courses + "]";
	}
	
	//Validation method
	public boolean validateAge(int age) {
		
		if(age>=19 && age<=35) {
			
			return true;
		}else {
			System.err.println("Invalid age!!");
			return false;
		}
	}
	
	public boolean validateName(String name) {
		
		String nameRegEx = "^[a-zA-Z\\s]+$";
		Pattern namePattern = Pattern.compile(nameRegEx);
		Matcher matcher= namePattern.matcher(name);
		if(matcher.matches()) {
			return true;
		}else {
			System.err.println("Invalid name!!");
			return false;
		}
		
		
	}
	
	

}
