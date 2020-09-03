package com.api.consumer.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.consumer.model.to.CampanhaSocioTorcedorTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CAMPANHA_SOCIO_TORCEDOR")
public class CampanhaSocioTorcedor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "NOME_COMPLETO")
	private String nomeCompleto;

	@Column(name = "DT_NASCIMENTO")
	private LocalDate dtNascimento;

	@Column(name = "ID_TIME_CORACAO")
	private Long idTimeCoracao;

	@Column(name = "NOME_CAMPANHA")
	private String nomeCampanha;
	
	@Column(name = "ID_CAMPANHA")
	private Long idCampanha;

	public static CampanhaSocioTorcedor construirCampanhaSocioTorcedor(CampanhaSocioTorcedor socioTorcedor,
			CampanhaSocioTorcedor campanha) {
		return CampanhaSocioTorcedor.builder().email(socioTorcedor.getEmail())
				.nomeCompleto(socioTorcedor.getNomeCompleto()).dtNascimento(socioTorcedor.getDtNascimento())
				.idTimeCoracao(socioTorcedor.getIdTimeCoracao()).nomeCampanha(campanha.getNomeCampanha())
				.idCampanha(campanha.getIdCampanha()).build();
	}

	public static CampanhaSocioTorcedor parse(CampanhaSocioTorcedorTO campanhaSocioTorcedorTO) {
		return CampanhaSocioTorcedor.builder().email(campanhaSocioTorcedorTO.getEmail())
				.nomeCompleto(campanhaSocioTorcedorTO.getNomeCompleto())
				.dtNascimento(campanhaSocioTorcedorTO.getDtNascimento())
				.idTimeCoracao(campanhaSocioTorcedorTO.getIdTimeCoracao()).build();
	}

}
