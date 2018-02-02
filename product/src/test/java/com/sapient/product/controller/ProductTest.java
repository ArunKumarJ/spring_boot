package com.sapient.product.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.sapient.product.domian.Product;
import com.sapient.product.domian.ProductType;
import com.sapient.product.dto.ProductDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {
	@Autowired
	private WebApplicationContext context;
	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mvc;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testCreateProductType() throws Exception {
		mvc.perform(post("/producttype").contentType(contentType).content("A")).andExpect(status().isOk());

		mvc.perform(post("/producttype").contentType(contentType).content("B")).andExpect(status().isOk());
		mvc.perform(post("/producttype").contentType(contentType).content("C")).andExpect(status().isOk());

	}

	@Test
	public void testCreateProduct() throws Exception {
		MvcResult resultActions = mvc
				.perform(post("/producttype").contentType(contentType).content(json("TestProductType"))).andReturn();
		String content = resultActions.getResponse().getContentAsString();
		ProductType p = new Gson().fromJson(content, ProductType.class);
		String pStr = json(new ProductDTO(null, "a", p.getId()));
		mvc.perform(post("/product").contentType(contentType).content(pStr)).andExpect(status().isOk());
	}

	@Test
	public void testSearchByCriteria() throws Exception {
		MvcResult resultActions = mvc.perform(post("/producttype").contentType(contentType).content("TestProductType1"))
				.andReturn();
		String content = resultActions.getResponse().getContentAsString();
		ProductType p = new Gson().fromJson(content, ProductType.class);
		String pStr = json(new ProductDTO(null, "CC", p.getId()));
		mvc.perform(post("/product").contentType(contentType).content(pStr)).andExpect(status().isOk());

		MvcResult search = mvc.perform(get("/product/search?productType=TestProductType1").contentType(contentType))
				.andReturn();

		content = search.getResponse().getContentAsString();
		List<Product> products = new Gson().fromJson(content, List.class);

		Assert.assertTrue(products.size() == 1);
	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}
