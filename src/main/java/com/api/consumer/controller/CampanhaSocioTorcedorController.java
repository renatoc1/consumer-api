package com.api.consumer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.consumer.model.CampanhaSocioTorcedor;
import com.api.consumer.model.to.CampanhaSocioTorcedorTO;
import com.api.consumer.service.CampanhaSocioTorcedorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Campanha Sócio Torcedor"})
@RestController
@RequestMapping("/campanhaSocioTorcedor")
public class CampanhaSocioTorcedorController {

	@Autowired
	private CampanhaSocioTorcedorService campanhaSocioTorcedorService;
	
	@ApiOperation(value = "Associa as campanhas com o sócio torcedor informado")
	@PostMapping("")
	public ResponseEntity<List<CampanhaSocioTorcedor>> associarCampanhaSocioTorcedor(@Valid @RequestBody CampanhaSocioTorcedorTO campanhaSocioTorcedorTO) {
		
		List<CampanhaSocioTorcedor> campanhasSociosTorcedores = campanhaSocioTorcedorService.associarCampanhaSocioTorcedor(campanhaSocioTorcedorTO);
		
		return new ResponseEntity<List<CampanhaSocioTorcedor>>(campanhasSociosTorcedores, HttpStatus.CREATED);
		
	}
	
}
