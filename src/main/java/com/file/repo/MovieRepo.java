package com.file.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.file.entity.Movie;

public interface MovieRepo  extends JpaRepository<Movie, Integer>
{

	
}
