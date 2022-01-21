package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.impl.RatingServiceImpl;

@SpringBootTest
public class RatingServiceTest {
	
	@Autowired
	private RatingServiceImpl ratingService;
	
	@Mock
	private RatingRepository ratingRepositoryMock;
	
	@Test
	public void testFindAll() throws Exception {
		// GIVEN
		Rating r1 = new Rating();
		Rating r2 = new Rating();
		ArrayList<Rating> cps = new ArrayList<Rating>();
		cps.add(r1);
		cps.add(r2);

		// WHEN
		when(ratingRepositoryMock.findAll()).thenReturn(cps);
		ratingService.setRatingRepository(ratingRepositoryMock);

		// THEN
		assertThat(ratingService.findAll().size()).isEqualTo(2);
	}
	
	@Test
	public void testSave() throws Exception {
		// GIVEN
		Rating r1 = new Rating();
		r1.setMoodysRating("GoldA");  
		
		// WHEN
		when(ratingRepositoryMock.save(r1)).thenReturn(r1);
		ratingService.setRatingRepository(ratingRepositoryMock);

		// THEN
		assertThat(ratingService.save(r1).getMoodysRating()).isEqualTo("GoldA");
	}
	
	@Test
	public void testFindById() throws Exception {
		// GIVEN
		Rating r1 = new Rating();
		r1.setMoodysRating("GoldA"); 
		Optional<Rating> blo = Optional.of(r1);

		// WHEN
		when(ratingRepositoryMock.findById(any(Integer.class))).thenReturn(blo);
		ratingService.setRatingRepository(ratingRepositoryMock);

		// THEN
		assertThat(ratingService.findById(77).getMoodysRating()).isEqualTo("GoldA");
	}
	
	@Test
	public void testDeleteById() throws Exception {
		// GIVEN
		Rating r1 = new Rating();
		r1.setMoodysRating("GoldA"); 
		Optional<Rating> blo = Optional.of(r1);

		// WHEN
		when(ratingRepositoryMock.findById(any(Integer.class))).thenReturn(blo);
		ratingService.setRatingRepository(ratingRepositoryMock);

		// THEN
		assertThat(ratingService.deleteById(77).getMoodysRating()).isEqualTo("GoldA");
	}

}
