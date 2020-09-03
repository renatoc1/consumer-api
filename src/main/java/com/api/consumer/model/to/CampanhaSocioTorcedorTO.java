package com.api.consumer.model.to;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@Email
	private String email;

	private String nomeCompleto;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtNascimento;

	private Long idTimeCoracao;

}
