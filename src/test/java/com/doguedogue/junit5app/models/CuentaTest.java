package com.doguedogue.junit5app.models;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CuentaTest {

	@Test
	void testNombreCuenta() {
		Cuenta cuenta = new Cuenta("Rafael", new BigDecimal("1000.12345"));

		
		String esperado = "Rafael";
		String real = cuenta.getPersona();
		
		assertEquals(esperado, real);
		assertTrue(real.equals("Rafael"));
	}
	
	@Test
	void testSaldoCuenta() {
		Cuenta cuenta = new Cuenta("Rafael", new BigDecimal("1000.12345"));
		
		assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
		
		assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
		
		assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
	}
	
	@Test
	void testReferenciaCuenta() {
		Cuenta cuenta = new Cuenta("John Doe", new BigDecimal("1000.12345"));
		Cuenta cuenta2 = new Cuenta("John Doe", new BigDecimal("1000.12345"));
		
		
		//reference not equals
		//assertNotEquals(cuenta2, cuenta);
		
		//overriding equals method
		assertEquals(cuenta2, cuenta);
		
	}
	

}
