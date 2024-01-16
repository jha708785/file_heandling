package com.file.dto;

import java.util.Set;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

	
	
	private Integer movieId;
	private String title;
	private String director;
	private String studio;
	private Set<String> movieCast;
	private Integer relegeYear;
	private String poster;
	private String posterUrl;
	
	
	
	
}
