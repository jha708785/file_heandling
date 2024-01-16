package com.file.exception;

@SuppressWarnings("serial")
public class FileExistException extends RuntimeException {

	public FileExistException(String mesage) {
		super(mesage);
	}
}
