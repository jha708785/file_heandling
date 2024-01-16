package com.file.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MovieServiceImpl implements MovieService {

	@Override
	public String uploadFile(String path, MultipartFile file) throws IOException {

		String filename = file.getOriginalFilename(); // get name of the file

		String filepath = path + File.separator + filename; // get the file path

		File file2 = new File(path); // create the file object
		if (!file2.exists()) {
			file2.mkdir();
		}

		Files.copy(file.getInputStream(), Paths.get(filepath), StandardCopyOption.REPLACE_EXISTING); // copy the file
																										// and upload
																										// tghe file

		return filename;
	}

	@Override
	public InputStream getResourceFile(String path, String filename) throws FileNotFoundException {

		String filepath = path + File.separator + filename;

		return new FileInputStream(filepath);
	}

}
