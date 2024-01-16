package com.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.file.dto.MovieDto;
import com.file.dto.MoviePageResponse;
import com.file.entity.Movie;
import com.file.exception.FileExistException;
import com.file.exception.MovieNotFoundException;
import com.file.repo.MovieRepo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MoviesServisesImplemention  implements MoviesServises{

	@Autowired
	private MovieRepo repo;
	
	@Autowired
	private MovieService service;
	@Autowired
	private MovieService fileService;
	
	@Value("${project.poster}")
	private String path;
	
	@Value("${base.url}")
	private String url;
	@Override
	public MovieDto addmovie(MovieDto movieDto, MultipartFile file) throws IOException {
		
		if(Files.exists(Paths.get(path+File.separator+file.getOriginalFilename()))) {
			throw new FileExistException("file already exist");
		}
		
		String uploadFile = service.uploadFile(path, file);
		movieDto.setPoster(uploadFile);
		
		Movie m=new Movie(
		null,
		movieDto.getTitle(),
		movieDto.getDirector(),
		movieDto.getStudio(),
		movieDto.getMovieCast(),
		movieDto.getRelegeYear(),
		movieDto.getPoster()
		
		
	);
		
		
		Movie save = repo.save(m);
		
		String posterUrl=url+"/file/"+uploadFile;
		
		MovieDto mDto=new MovieDto(
				save.getMovieId(),
				save.getTitle(),
				save.getDirector(),
				save.getStudio(),
				save.getMovieCast(),
				save.getRelegeYear(),
				save.getStudio(),
				save.getPoster()
				
				
		
		);
		log.info("poster url {}"+posterUrl);

		return mDto;
	}

	@Override
	public MovieDto getMovieById(Integer movieId) {
		
		Movie movie = repo.findById(movieId).orElseThrow(()->new MovieNotFoundException("Movie not found"));
		String posterUrl=url+"/file/"+movie.getPoster();
		MovieDto mDto=new MovieDto(
				movie.getMovieId(),
				movie.getTitle(),
				movie.getDirector(),
				movie.getStudio(),
				movie.getMovieCast(),
				movie.getRelegeYear(),
				movie.getStudio(),
				movie.getPoster()
		);
		log.info("poster url {}"+posterUrl);

		return mDto;
	}

	@Override
	public List<MovieDto> getAllMovie() {
		List<Movie> all = repo.findAll();
		List<MovieDto> movieDtos=new ArrayList<>();
		for(Movie movie:all) {
			
			
			MovieDto mDto=new MovieDto(
					movie.getMovieId(),
					movie.getTitle(),
					movie.getDirector(),
					movie.getStudio(),
					movie.getMovieCast(),
					movie.getRelegeYear(),
					movie.getStudio(),
					movie.getPoster()
			);
			movieDtos.add(mDto);
		}
		return movieDtos;
	}

	@Override
	public MovieDto updateMovie(Integer movieId, MovieDto movieDto, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		Movie mv = repo.findById(movieId).orElseThrow(()->new MovieNotFoundException("Movie not found"));
		
		String filename=mv.getPoster();
		log.info(filename);
		if(file !=null) {
			Files.deleteIfExists(Paths.get(path+File.separator+filename));
			
			filename=fileService.uploadFile(path, file);
		}
		
		movieDto.setPoster(filename); // set poster value
		
		
		
		Movie m=new Movie(
		mv.getMovieId(),
		movieDto.getTitle(),
		movieDto.getDirector(),
		movieDto.getStudio(),
		movieDto.getMovieCast(),
		movieDto.getRelegeYear(),
		movieDto.getPoster()
		);
		
		
		Movie updatedmovie = repo.save(m);
		String posterUrl=url+"/file/"+mv.getPoster();
		MovieDto mDto=new MovieDto(
				m.getMovieId(),
				m.getTitle(),
				m.getDirector(),
				m.getStudio(),
				m.getMovieCast(),
				m.getRelegeYear(),
				m.getStudio(),
				m.getPoster()
		);
		log.info("poster url {}"+posterUrl);

		return mDto;
	}

	@Override
	public String deleteMovie(Integer movieId) throws IOException {
		
		Movie mv = repo.findById(movieId).orElseThrow(()->new MovieNotFoundException("Movie not found"));
		
		Integer id=mv.getMovieId();
		Files.deleteIfExists(Paths.get(path+File.separator+mv.getPoster()));
		
		repo.delete(mv);
		return "delete successfully..!!"+id;
	}

	@Override
	public MoviePageResponse getAllMovieWithPagination(Integer pageNumber, Integer pageSize) {
	
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		Page<Movie> moviepage = repo.findAll(pageable);
		List<Movie> movies = moviepage.getContent();
		
		List<MovieDto> movieDto=new ArrayList<>();
		for(Movie movie:movies) {

			MovieDto mDto=new MovieDto(
					movie.getMovieId(),
					movie.getTitle(),
					movie.getDirector(),
					movie.getStudio(),
					movie.getMovieCast(),
					movie.getRelegeYear(),
					movie.getStudio(),
					movie.getPoster()
			);
			movieDto.add(mDto);
		}
				
		return new MoviePageResponse(movieDto, pageNumber, pageSize, moviepage.getTotalPages(), moviepage.getTotalElements(), moviepage.isLast());
	}

	@Override
	public MoviePageResponse getAllMovieWithSorting(Integer pageNumber, Integer pageSize, String sortBy, String dir) {
	
		Sort sort=dir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

		Pageable pageable=PageRequest.of(pageNumber, pageSize,sort);
		Page<Movie> moviepage = repo.findAll(pageable);
		List<Movie> movies = moviepage.getContent();
		
		List<MovieDto> movieDto=new ArrayList<>();
		for(Movie movie:movies) {

			MovieDto mDto=new MovieDto(
					movie.getMovieId(),
					movie.getTitle(),
					movie.getDirector(),
					movie.getStudio(),
					movie.getMovieCast(),
					movie.getRelegeYear(),
					movie.getStudio(),
					movie.getPoster()
			);
			movieDto.add(mDto);
		}
				
		return new MoviePageResponse(movieDto, pageNumber, pageSize, moviepage.getTotalPages(), moviepage.getTotalElements(), moviepage.isLast());

	}

}
