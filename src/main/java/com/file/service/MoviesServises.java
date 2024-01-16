package com.file.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.file.dto.MovieDto;
import com.file.dto.MoviePageResponse;

public interface MoviesServises {
	
	
	public MovieDto addmovie(MovieDto movieDto,MultipartFile file) throws IOException;
	public MovieDto getMovieById(Integer movieId);
	public List<MovieDto>getAllMovie();
	public MovieDto updateMovie(Integer movieId,MovieDto movieDto,MultipartFile file)throws IOException;
	public String deleteMovie(Integer movieId) throws IOException;
	public MoviePageResponse getAllMovieWithPagination(Integer pageNumber,Integer pageSize);
	public MoviePageResponse getAllMovieWithSorting(Integer pageNumber,Integer pageSize,String sortBy,String dir);

}
