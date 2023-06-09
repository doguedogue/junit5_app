package com.doguedogue.junit5app.models;

import java.math.BigDecimal;

import com.doguedogue.junit5app.exceptions.DineroInsuficienteException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Cuenta {
	private String persona;
	private BigDecimal saldo;
	
	private Banco banco;
	
	public Cuenta (String persona, BigDecimal saldo) {
		setPersona(persona);
		setSaldo(saldo);
	}
	
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

	public void retiro(BigDecimal monto) {
		BigDecimal newSaldo = getSaldo().subtract(monto);
		if (newSaldo.compareTo(BigDecimal.ZERO)<0) {
			throw new DineroInsuficienteException("Dinero Insuficiente");
		}
			
		setSaldo(getSaldo().subtract(monto));
		
	}

	public void deposito(BigDecimal monto) {
		setSaldo(getSaldo().add(monto));
	}
	
}
