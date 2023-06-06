package com.doguedogue.junit5app.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Cuenta {
	private String persona;
	private BigDecimal saldo;
	
}
