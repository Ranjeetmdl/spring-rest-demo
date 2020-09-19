package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	List<Student> theStudents;
	
	//add a @PostConstruct method to load the data...only once
	@PostConstruct
	public void loadData(){
		theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Ranjeet","Mandal"));
		theStudents.add(new Student("John","Doe"));
		theStudents.add(new Student("Mohan","Bhargav"));
	}
	
	//define the enpoint for "/students"-return list of Student
	@GetMapping("/students")
	public List<Student> getStudents(){
		return theStudents;
	}
	
	//define an endpoint for "/students/{studentId}"-return student based on the id(here based on index)
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId){
		
		//check the studentId against the list size
		if((studentId >= theStudents.size()) || (studentId < 0 )){
			throw new StudentNotFoundException("Student Not Found(with Id) :"+studentId);
		}
		return theStudents.get(studentId);
	}

}
