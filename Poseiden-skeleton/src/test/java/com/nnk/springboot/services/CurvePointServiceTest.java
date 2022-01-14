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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@SpringBootTest
public class CurvePointServiceTest {
	
	@Autowired
	private CurvePointService curvePointService;
	
	@Mock
	private CurvePointRepository curvePointRepositoryMock;
	
	@Test
	public void testFindAll() throws Exception {
		// GIVEN
		CurvePoint cp1 = new CurvePoint();
		CurvePoint cp2 = new CurvePoint();
		ArrayList<CurvePoint> cps = new ArrayList<CurvePoint>();
		cps.add(cp1);
		cps.add(cp2);

		// WHEN
		when(curvePointRepositoryMock.findAll()).thenReturn(cps);
		curvePointService.setCurvePointRepository(curvePointRepositoryMock);

		// THEN
		assertThat(curvePointService.findAll().size()).isEqualTo(2);
	}
	
	@Test
	public void testSave() throws Exception {
		// GIVEN
		CurvePoint cp1 = new CurvePoint();
		cp1.setTerm(12.0);  

		// WHEN
		when(curvePointRepositoryMock.save(cp1)).thenReturn(cp1);
		curvePointService.setCurvePointRepository(curvePointRepositoryMock);

		// THEN
		assertThat(curvePointService.save(cp1).getTerm()).isEqualTo(12.0);
	}
	
	@Test
	public void testFindById() throws Exception {
		// GIVEN
		CurvePoint cp1 = new CurvePoint();
		cp1.setTerm(12.0);
		Optional<CurvePoint> blo = Optional.of(cp1);

		// WHEN
		when(curvePointRepositoryMock.findById(any(Integer.class))).thenReturn(blo);
		curvePointService.setCurvePointRepository(curvePointRepositoryMock);

		// THEN
		assertThat(curvePointService.findById(77).getTerm()).isEqualTo(12.0);
	}
	
	@Test
	public void testDeleteById() throws Exception {
		// GIVEN
		CurvePoint cp1 = new CurvePoint();
		cp1.setTerm(12.0);
		Optional<CurvePoint> blo = Optional.of(cp1);

		// WHEN
		when(curvePointRepositoryMock.findById(any(Integer.class))).thenReturn(blo);
		curvePointService.setCurvePointRepository(curvePointRepositoryMock);

		// THEN
		assertThat(curvePointService.deleteById(77).getTerm()).isEqualTo(12.0);
	}

}
