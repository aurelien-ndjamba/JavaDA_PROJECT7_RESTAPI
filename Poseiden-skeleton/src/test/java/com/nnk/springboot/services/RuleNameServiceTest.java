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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.impl.RuleNameServiceImpl;

@SpringBootTest
public class RuleNameServiceTest {
	
	@Autowired
	private RuleNameServiceImpl ruleNameService;
	
	@Mock
	private RuleNameRepository ruleNameRepositoryMock;
	
	@Test
	public void testFindAll() throws Exception {
		// GIVEN
		RuleName r1 = new RuleName();
		RuleName r2 = new RuleName();
		ArrayList<RuleName> rs = new ArrayList<RuleName>();
		rs.add(r1);
		rs.add(r2);

		// WHEN
		when(ruleNameRepositoryMock.findAll()).thenReturn(rs);
		ruleNameService.setRuleNameRepository(ruleNameRepositoryMock);

		// THEN
		assertThat(ruleNameService.findAll().size()).isEqualTo(2);
	}
	
	@Test
	public void testSave() throws Exception {
		// GIVEN
		RuleName r1 = new RuleName();
		r1.setName("speedy");  
		
		// WHEN
		when(ruleNameRepositoryMock.save(r1)).thenReturn(r1);
		ruleNameService.setRuleNameRepository(ruleNameRepositoryMock);

		// THEN
		assertThat(ruleNameService.save(r1).getName()).isEqualTo("speedy");
	}
	
	@Test
	public void testFindById() throws Exception {
		// GIVEN
		RuleName r1 = new RuleName();
		r1.setName("speedy"); 
		Optional<RuleName> blo = Optional.of(r1);

		// WHEN
		when(ruleNameRepositoryMock.findById(any(Integer.class))).thenReturn(blo);
		ruleNameService.setRuleNameRepository(ruleNameRepositoryMock);

		// THEN
		assertThat(ruleNameService.findById(77).getName()).isEqualTo("speedy");
	}
	
	@Test
	public void testDeleteById() throws Exception {
		// GIVEN
		RuleName r1 = new RuleName();
		r1.setName("speedy"); 
		Optional<RuleName> blo = Optional.of(r1);

		// WHEN
		when(ruleNameRepositoryMock.findById(any(Integer.class))).thenReturn(blo);
		ruleNameService.setRuleNameRepository(ruleNameRepositoryMock);

		// THEN
		assertThat(ruleNameService.deleteById(77).getName()).isEqualTo("speedy");
	}

}
