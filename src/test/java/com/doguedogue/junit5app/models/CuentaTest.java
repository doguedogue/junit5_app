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
		assertNotNull(real);
		
		assertEquals(esperado, real);
		assertTrue(real.equals("Rafael"));
	}
	
	@Test
	void testSaldoCuenta() {
		Cuenta cuenta = new Cuenta("Rafael", new BigDecimal("1000.12345"));
		
		assertNotNull(cuenta.getSaldo());
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
	
	@Test
	void testRetiroCuenta() {
		Cuenta cuenta = new Cuenta("Juan Pérez", new BigDecimal("1000.12345"));
		cuenta.retiro(new BigDecimal("100"));
		
		assertNotNull(cuenta.getSaldo());
		assertEquals(900, cuenta.getSaldo().intValue());
		assertEquals("900.12345", cuenta.getSaldo().toPlainString());
	}
	
	@Test
	void testDepositoCuenta() {
		Cuenta cuenta = new Cuenta("Juan Pérez", new BigDecimal("1000.12345"));
		cuenta.deposito(new BigDecimal("100"));
		
		assertNotNull(cuenta.getSaldo());
		assertEquals(1100, cuenta.getSaldo().intValue());
		assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
	}
	

}
