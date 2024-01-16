package com.file.entity;

import java.util.Set;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer movieId;
	
	
	private String title;
	
	

	private String director;
	

	private String studio;
	
	@ElementCollection
	@CollectionTable(name = "movie_cast")
	private Set<String> movieCast;
	
	//@NotBlank(message = "relese year is required")
	private Integer relegeYear;
	
	
	private String poster;
	
}
