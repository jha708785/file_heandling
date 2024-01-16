package com.file.controller;

import org.springframework.stereotype.Component;

@Component
 public class Controller {

	public static final String ROOT_URL="/file";
	public static final String UPLOAD_FILE="/file_upload";
	public static final String GET_FILENAME="/filename/{filename}";
	
	public static final String SAVE_MOVIE="/add-movie";
	public static final String FIND_MOVIE="/movie/{movieId}";
	public static final String ALL_MOVIE="/all-movie";
	public static final String UPDATE_MOVIE="/update/{movieId}";
	public static final String DELETE_MOVIE="/delete/{movieId}";
	
	public static final String GET_ALL_MOVIE_WITHPAGE="/allmoviepage";
	public static final String GET_ALL_MOVIE_WITHPAGE_AND_SORT="/allmoviepagesort";
	
}
