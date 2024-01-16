package com.file.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class MoviePageResponse{

		List<MovieDto> movieDtos ;
		Integer pageNumber;
		Integer pageSize;
		int totalElement;
		long totalPage;
		public MoviePageResponse(List<MovieDto> movieDtos, Integer pageNumber, Integer pageSize, int totalElement,
				long totalPage, boolean isLast) {
			super();
			this.movieDtos = movieDtos;
			this.pageNumber = pageNumber;
			this.pageSize = pageSize;
			this.totalElement = totalElement;
			this.totalPage = totalPage;
			this.isLast = isLast;
		}
		boolean isLast;
		



}
