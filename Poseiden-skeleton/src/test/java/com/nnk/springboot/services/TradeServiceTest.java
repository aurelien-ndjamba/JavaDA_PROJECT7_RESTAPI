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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.impl.TradeServiceImpl;


@SpringBootTest
public class TradeServiceTest {
	
	@Autowired
	private TradeServiceImpl tradeService;
	
	@Mock
	private TradeRepository tradeRepositoryMock;
	
	@Test
	public void testFindAll() throws Exception {
		// GIVEN
		Trade t1 = new Trade();
		Trade t2 = new Trade();
		ArrayList<Trade> ts = new ArrayList<Trade>();
		ts.add(t1);
		ts.add(t2);

		// WHEN
		when(tradeRepositoryMock.findAll()).thenReturn(ts);
		tradeService.setTradeRepository(tradeRepositoryMock);

		// THEN
		assertThat(tradeService.findAll().size()).isEqualTo(2);
	}
	
	@Test
	public void testSave() throws Exception {
		// GIVEN
		Trade t1 = new Trade();
		t1.setAccount("allan");  
		
		// WHEN
		when(tradeRepositoryMock.save(t1)).thenReturn(t1);
		tradeService.setTradeRepository(tradeRepositoryMock);

		// THEN
		assertThat(tradeService.save(t1).getAccount()).isEqualTo("allan");
	}
	
	@Test
	public void testFindById() throws Exception {
		// GIVEN
		Trade t1 = new Trade();
		t1.setAccount("allan"); 
		Optional<Trade> blo = Optional.of(t1);

		// WHEN
		when(tradeRepositoryMock.findById(any(Integer.class))).thenReturn(blo);
		tradeService.setTradeRepository(tradeRepositoryMock);

		// THEN
		assertThat(tradeService.findById(77).getAccount()).isEqualTo("allan");
	}
	
	@Test
	public void testDeleteById() throws Exception {
		// GIVEN
		Trade t1 = new Trade();
		t1.setAccount("allan"); 
		Optional<Trade> blo = Optional.of(t1);

		// WHEN
		when(tradeRepositoryMock.findById(any(Integer.class))).thenReturn(blo);
		tradeService.setTradeRepository(tradeRepositoryMock);

		// THEN
		assertThat(tradeService.deleteById(77).getAccount()).isEqualTo("allan");
	}

}
