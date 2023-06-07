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
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cuenta)) {
			return false;
		}
		Cuenta c = (Cuenta)obj;
		
		if (this.persona == null || this.saldo == null) {
			return false;
		}
		
		
		return this.persona.equals(c.getPersona()) && this.saldo.equals(c.getSaldo());
	}

	public void retiro(BigDecimal bigDecimal) {
		// TODO Auto-generated method stub
		
	}

	public void deposito(BigDecimal bigDecimal) {
		// TODO Auto-generated method stub
		
	}
	
}
