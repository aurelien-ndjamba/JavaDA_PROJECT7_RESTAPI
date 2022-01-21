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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.impl.BidListServiceImpl;

@SpringBootTest
public class BidServiceTest {
	
	@Autowired
	private BidListServiceImpl bidListService;
	
	@Mock
	private BidListRepository bidListRepositoryMock;
	
	@Test
	public void testFindAll() throws Exception {
		// GIVEN
		BidList bl1 = new BidList();
		BidList bl2 = new BidList();
		ArrayList<BidList> bls = new ArrayList<BidList>();
		bls.add(bl1);
		bls.add(bl2);

		// WHEN
		when(bidListRepositoryMock.findAll()).thenReturn(bls);
		bidListService.setBidListRepository(bidListRepositoryMock);

		// THEN
		assertThat(bidListService.findAll().size()).isEqualTo(2);
	}
	
	@Test
	public void testSave() throws Exception {
		// GIVEN
		BidList bl1 = new BidList();
		bl1.setAccount("accountBl1");

		// WHEN
		when(bidListRepositoryMock.save(bl1)).thenReturn(bl1);
		bidListService.setBidListRepository(bidListRepositoryMock);

		// THEN
		assertThat(bidListService.save(bl1).getAccount()).isEqualTo("accountBl1");
	}
	
	@Test
	public void testFindById() throws Exception {
		// GIVEN
		BidList bl1 = new BidList();
		bl1.setAccount("accountBl1");
		Optional<BidList> blo = Optional.of(bl1);

		// WHEN
		when(bidListRepositoryMock.findById(any(Integer.class))).thenReturn(blo);
		bidListService.setBidListRepository(bidListRepositoryMock);

		// THEN
		assertThat(bidListService.findById(77).getAccount()).isEqualTo("accountBl1");
	}
	
	@Test
	public void testDeleteById() throws Exception {
		// GIVEN
		BidList bl1 = new BidList();
		bl1.setAccount("accountBl1");
		Optional<BidList> blo = Optional.of(bl1);

		// WHEN
		when(bidListRepositoryMock.findById(any(Integer.class))).thenReturn(blo);
		bidListService.setBidListRepository(bidListRepositoryMock);

		// THEN
		assertThat(bidListService.deleteById(77).getAccount()).isEqualTo("accountBl1");
	}

}
