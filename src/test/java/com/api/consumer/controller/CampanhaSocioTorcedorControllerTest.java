package com.api.consumer.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.api.consumer.model.to.CampanhaSocioTorcedorTO;
import com.api.consumer.service.CampanhaSocioTorcedorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class CampanhaSocioTorcedorControllerTest {

	static final String CAMPANHA_SOCIO_TORCEDOR_API = "/campanhaSocioTorcedor";
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private CampanhaSocioTorcedorService campanhaSocioTorcedorService;
	
	@Test
	@DisplayName("Deve associar as campanhas com o sócio torcedor")
	public void associarCampanhaSocioTorcedor() throws Exception {
		
		CampanhaSocioTorcedorTO campanhaSocioTorcedorTO = CampanhaSocioTorcedorTO.builder().email("teste@teste.com").nomeCompleto("teste").idTimeCoracao(1L).build();
		BDDMockito.given(campanhaSocioTorcedorService.associarCampanhaSocioTorcedor(campanhaSocioTorcedorTO)).willReturn(Mockito.anyList());
		String json = new ObjectMapper().writeValueAsString(campanhaSocioTorcedorTO);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
			.post(CAMPANHA_SOCIO_TORCEDOR_API)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.content(json);
		
		mock
			.perform(request)
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andReturn();
		
//		ObjectMapper mapper = new ObjectMapper();
//		List<CampanhaSocioTorcedor> lista = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<CampanhaSocioTorcedor>>() {});
		
	}
	
	@Test
	@DisplayName("Deve lançar erro quando o sócio torcedor não tiver time do coração")
	public void erroAssociarCampanhaSocioTorcedor() throws Exception {
		
		CampanhaSocioTorcedorTO campanhaSocioTorcedorTO = CampanhaSocioTorcedorTO.builder().email("teste@teste.com").nomeCompleto("teste").build();
		BDDMockito.given(campanhaSocioTorcedorService.associarCampanhaSocioTorcedor(campanhaSocioTorcedorTO)).willReturn(Mockito.anyList());
		String json = new ObjectMapper().writeValueAsString(campanhaSocioTorcedorTO);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
			.post(CAMPANHA_SOCIO_TORCEDOR_API)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.content(json);
		
		mock
			.perform(request)
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andReturn();
		
	}
	
}
