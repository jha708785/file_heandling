package com.file.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface MovieService {

	
	public String uploadFile(String path,MultipartFile file) throws IOException;
	
	public InputStream getResourceFile(String path,String filename)throws FileNotFoundException;
	
	
	
}
