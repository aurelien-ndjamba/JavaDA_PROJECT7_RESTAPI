package com.nnk.springboot.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

@WebMvcTest(controllers = BidListController.class)
public class BidListControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BidListService bidListServiceMock;
	
	@Disabled
	@Test
	public void testFindAll() throws Exception {
		// GIVEN
		BidList bl1 = new BidList();
		BidList bl2 = new BidList();
		ArrayList<BidList> bls = new ArrayList<BidList>();
		bls.add(bl1);
		bls.add(bl2);

		// WHEN
		when(bidListServiceMock.findAll()).thenReturn(bls);

		// THEN
		mockMvc.perform(get("/bidList/list")).andExpect(status().isOk());
//		.andExpect(jsonPath("$[0].id", is(77777)));
	}

}
