package com.api.consumer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.api.consumer.exception.CampanhaSocioTorcedorException;
import com.api.consumer.model.CampanhaSocioTorcedor;
import com.api.consumer.model.to.CampanhaSocioTorcedorTO;
import com.api.consumer.repository.CampanhaSocioTorcedorRepository;

import reactor.core.publisher.Mono;

@Service
public class CampanhaSocioTorcedorService {

	@Autowired
	private WebClient webClientCampanha;
	
	@Autowired
	private WebClient webClientSocioTorcedor;
	
	@Autowired
	private CampanhaSocioTorcedorRepository campanhaSocioTorcedorRepository;
	
	public List<CampanhaSocioTorcedor> associarCampanhaSocioTorcedor(CampanhaSocioTorcedorTO campanhaSocioTorcedorTO) {

		List<CampanhaSocioTorcedor> novasCampanhasSocioTorcedor = new ArrayList<CampanhaSocioTorcedor>();
		
		//Buscar usuario cadastrado
		CampanhaSocioTorcedor cst = buscarSocioTorcedor(campanhaSocioTorcedorTO.getEmail());
		if (null == cst) {
			criarSocioTorcedor(campanhaSocioTorcedorTO);
			if (null == campanhaSocioTorcedorTO.getIdTimeCoracao()) {
				throw new CampanhaSocioTorcedorException("Socio Torcedor sem time do coracao", HttpStatus.BAD_REQUEST);
			}
			List<CampanhaSocioTorcedor> campanhasAtivas = buscarCampanhasAtivas(campanhaSocioTorcedorTO.getIdTimeCoracao());
			criarCampanhaSocioTorcedor(campanhasAtivas, CampanhaSocioTorcedor.parse(campanhaSocioTorcedorTO), novasCampanhasSocioTorcedor);
		} else {
			//Usuario ja cadastrado
			if (null != cst.getIdTimeCoracao()) {
				List<CampanhaSocioTorcedor> campanhasAtivas = buscarCampanhasAtivas(cst.getIdTimeCoracao());
				List<CampanhaSocioTorcedor> campanhasExistentes = campanhaSocioTorcedorRepository.findByEmail(cst.getEmail());
				campanhasExistentes.stream().forEach(c -> {
					Predicate<CampanhaSocioTorcedor> removerId = item -> c.getIdCampanha() == item.getIdCampanha();
					campanhasAtivas.removeIf(removerId);
				});
				criarCampanhaSocioTorcedor(campanhasAtivas, cst, novasCampanhasSocioTorcedor);
			} else {
				if (null == campanhaSocioTorcedorTO.getIdTimeCoracao()) {
					throw new CampanhaSocioTorcedorException("Socio Torcedor sem time do coracao", HttpStatus.BAD_REQUEST);
				}
				List<CampanhaSocioTorcedor> campanhasAtivas = buscarCampanhasAtivas(campanhaSocioTorcedorTO.getIdTimeCoracao());
				criarCampanhaSocioTorcedor(campanhasAtivas, cst, novasCampanhasSocioTorcedor);
			}
		}
		
		return novasCampanhasSocioTorcedor;
		
	}
	
	void criarCampanhaSocioTorcedor(List<CampanhaSocioTorcedor> campanhasAtivas, CampanhaSocioTorcedor campanhaSocioTorcedor, List<CampanhaSocioTorcedor> novasCampanhasSocioTorcedor) {
		campanhasAtivas.stream().forEach(c -> {
			CampanhaSocioTorcedor cs = CampanhaSocioTorcedor.construirCampanhaSocioTorcedor(campanhaSocioTorcedor, c);
			novasCampanhasSocioTorcedor.add(cs);
			campanhaSocioTorcedorRepository.save(cs);
		});
	}
	
	public CampanhaSocioTorcedor buscarSocioTorcedor(String email) {

		Mono<CampanhaSocioTorcedor> monoCampanhaSocioTorcedor = 
				this.webClientSocioTorcedor
					.get()
					.uri("/socioTorcedor/email/{email}", email)
					.retrieve()
					.bodyToMono(CampanhaSocioTorcedor.class);

		CampanhaSocioTorcedor campanhaSocioTorcedor = monoCampanhaSocioTorcedor.block();
		
		return campanhaSocioTorcedor;
	}
	
	public CampanhaSocioTorcedor criarSocioTorcedor(CampanhaSocioTorcedorTO campanhaSocioTorcedorTO) {

		Mono<CampanhaSocioTorcedor> monoCampanhaSocioTorcedor = 
				this.webClientSocioTorcedor
					.post()
					.uri("/socioTorcedor")
					.body(BodyInserters.fromValue(campanhaSocioTorcedorTO))
					.retrieve()
					.bodyToMono(CampanhaSocioTorcedor.class);

		CampanhaSocioTorcedor campanhaSocioTorcedor = monoCampanhaSocioTorcedor.block();
		
		return campanhaSocioTorcedor;
	}
	
	public List<CampanhaSocioTorcedor> buscarCampanhasAtivas(Long idTimeCoracao) {

		Mono<List<CampanhaSocioTorcedor>> monoCampanhaSocioTorcedor =
				this.webClientCampanha
					.get()
					.uri("/campanhas/timeCoracao/{id}", idTimeCoracao)
					.retrieve()
					.bodyToMono(new ParameterizedTypeReference<List<CampanhaSocioTorcedor>>() {});	
		
			return monoCampanhaSocioTorcedor.block();
	}
	
}
