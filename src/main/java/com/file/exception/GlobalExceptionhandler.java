package com.file.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionhandler {

	@ExceptionHandler(MovieNotFoundException.class)
	public ProblemDetail handleMovieNotFoundException(MovieNotFoundException e) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
	}
	
	
	@ExceptionHandler(FileExistException.class)
	public ProblemDetail handlefileExistException(FileExistException e) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
	}
	

	@ExceptionHandler(FileNotFoundException.class)
	public ProblemDetail handlefilenotFoundException(FileNotFoundException e) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ProblemDetail allExceptionHandler(Exception e) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
	}
	
}
