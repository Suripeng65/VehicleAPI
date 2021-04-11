package com.udacity.pricing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.udacity.pricing.domain.price.PriceRepository;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {
	@Autowired
	PriceRepository priceRepository;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getPriceById() throws Exception{
		mockMvc.perform(get("/prices/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.vehicleId").exists())
		.andExpect(jsonPath("$.vehicleId").value(1));
	}
	
	@Test
	public void getAllPrices() throws Exception{
		mockMvc.perform(get("/prices/"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$._embedded").exists())
		.andExpect(jsonPath("$._embedded.prices").exists())
		.andExpect(jsonPath("$._embedded.prices", hasSize(19)));
	}

}
