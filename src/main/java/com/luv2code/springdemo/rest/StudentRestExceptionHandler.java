package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {
	
	/*add an exception Handler using @ExceptionHandler
	 * -Exception Handler will return a ResponseEntity
	 * -ResponseEntity is a wrapper for HTTP response object
	 * -ResponseEntity provides fine-grained control to specify:
	 *    HTTP Status Code, Http Header, Response Body etc.. 
	 */
	
	/*@ExceptionHandler : it tells spring f/w that this method is actual exception handler &
	 * ResponseEntity<StudentErrorResponse>: response type which would be sent to REST client would be 'StudentErrorResponse' &
	 * handleException(StudentNotFoundException exc) :this handler will handle/catch the exception of type 'StudentNotFoundException'
	 * 
	 */
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
		
		//create a StudentErrorResponse
		StudentErrorResponse error = new StudentErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		//return ResponseEntity
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	//add another exception handler ...to catch any other type of exception
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
		
		       //create a StudentErrorResponse
				StudentErrorResponse error = new StudentErrorResponse();
				error.setStatus(HttpStatus.BAD_REQUEST.value());
				error.setMessage(exc.getMessage());
				error.setTimeStamp(System.currentTimeMillis());
				
				//return ResponseEntity
				return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
	}

}
