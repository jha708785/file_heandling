package com.file.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.file.dto.AppConstrant;
import com.file.dto.MovieDto;
import com.file.dto.MoviePageResponse;
import com.file.service.MovieService;
import com.file.service.MoviesServises;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(Controller.ROOT_URL)
public class MovieController {

	private final MovieService m_service;

	@Autowired
	private MoviesServises servises;

	@Value("${project.poster}")
	private String path;

	public MovieController(MovieService m_service) {
		this.m_service = m_service;

	}

	@PostMapping(Controller.UPLOAD_FILE)
	public ResponseEntity<String> uploadFileHandler(@RequestPart MultipartFile file) throws IOException {
		String uploadFilename = m_service.uploadFile(path, file);

		return ResponseEntity.ok("file upload" + uploadFilename);
	}

	@GetMapping(Controller.GET_FILENAME)
	public void serverfileHandler(@PathVariable("filename") String filename, HttpServletResponse response)
			throws IOException {

		InputStream resourceFile = m_service.getResourceFile(path, filename);
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		StreamUtils.copy(resourceFile, response.getOutputStream());

	}

	@PostMapping(Controller.SAVE_MOVIE)
	public ResponseEntity<MovieDto> addMovieHandler(@RequestPart String movieDto, @RequestPart MultipartFile file)
			throws IOException {

		MovieDto dto = convertMovieDto(movieDto);
		return new ResponseEntity<>(servises.addmovie(dto, file), HttpStatus.CREATED);
	}

	@GetMapping(Controller.FIND_MOVIE)
	public ResponseEntity<MovieDto> findMovie(@PathVariable("movieId") Integer movieId) throws IOException {

		return ResponseEntity.ok(servises.getMovieById(movieId));

	}

	@GetMapping(Controller.ALL_MOVIE)
	public ResponseEntity<List<MovieDto>> findAllMovie() throws IOException {

		return ResponseEntity.ok(servises.getAllMovie());

	}

	@PutMapping(Controller.UPDATE_MOVIE)
	public ResponseEntity<MovieDto> update(@PathVariable("movieId") Integer movieId, @RequestPart MultipartFile file,
			@RequestPart String MovieDto0bj) throws IOException {

		if (file.isEmpty())
			file = null;
		MovieDto movieDto = convertMovieDto(MovieDto0bj);

		return ResponseEntity.ok(servises.updateMovie(movieId, movieDto, file));

	}

	@DeleteMapping(Controller.DELETE_MOVIE)
	public ResponseEntity<String> deleteMovie(@PathVariable("movieId") Integer movieId) throws IOException {
		return ResponseEntity.ok(servises.deleteMovie(movieId));
	}
	
	
	@GetMapping(Controller.GET_ALL_MOVIE_WITHPAGE)
	public ResponseEntity<MoviePageResponse>getmoviewithpage(
			@RequestParam (defaultValue = AppConstrant.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam(defaultValue = AppConstrant.PAGE_SIZE,required = false)Integer pageSize
			){
		
				return ResponseEntity.ok(servises.getAllMovieWithPagination(pageNumber, pageSize));
		
	}
	
	
	
	@GetMapping(Controller.GET_ALL_MOVIE_WITHPAGE_AND_SORT)
	public ResponseEntity<MoviePageResponse>getmoviewithpageANDsort(
			@RequestParam (defaultValue = AppConstrant.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam(defaultValue = AppConstrant.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(defaultValue = AppConstrant.SORT_BY,required = false)String sortBy,
			@RequestParam(defaultValue = AppConstrant.SORT_DIR,required =  false)String dir
			){
		
				return ResponseEntity.ok(servises.getAllMovieWithSorting(pageNumber, pageSize, sortBy, dir));
		
	}


	private MovieDto convertMovieDto(String movieDtoObj) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		/*
		 * MovieDto dto=null; ObjectMapper mapper=new ObjectMapper(); dto=
		 * mapper.readValue(movieDtoObj, MovieDto.class);
		 */
		return mapper.readValue(movieDtoObj, MovieDto.class);

	}

}
