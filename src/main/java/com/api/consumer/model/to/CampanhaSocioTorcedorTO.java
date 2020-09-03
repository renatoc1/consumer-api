package com.api.consumer.model.to;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampanhaSocioTorcedorTO {

	@NotNull
	private String email;

	private String nomeCompleto;

	private LocalDate dtNascimento;

	private Long idTimeCoracao;

}
