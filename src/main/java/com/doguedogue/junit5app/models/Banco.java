package com.doguedogue.junit5app.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Banco {

	private String nombre;
	private List<Cuenta> cuentas;
	
	public Banco(){
		cuentas = new ArrayList<Cuenta>();
	}
	
	public void addCuenta(Cuenta cuenta) {
		getCuentas().add(cuenta);
		cuenta.setBanco(this);
	}
	
	public void transferir(Cuenta origen, Cuenta destino, BigDecimal monto) {
		
		origen.retiro(monto);
		destino.deposito(monto);
		
	}
}
