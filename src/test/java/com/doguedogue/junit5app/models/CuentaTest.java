package com.doguedogue.junit5app.models;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.doguedogue.junit5app.exceptions.DineroInsuficienteException;

class CuentaTest {
	
	Cuenta cuenta;
	
	@BeforeEach
	void initMethodTest() {
		System.out.println("Iniciando el método");
		cuenta = new Cuenta("Rafael", new BigDecimal("1000.12345"));
	}

	@AfterEach
	void endMethodTest() {
		System.out.println("Finalizando el método");
	}
	
	@Test
	void testNombreCuenta() {
		String esperado = "Rafael";
		String real = cuenta.getPersona();
		assertNotNull(real);
		
		assertEquals(esperado, real);
		assertTrue(real.equals("Rafael"));
	}
	
	@Test
	void testSaldoCuenta() {
		assertNotNull(cuenta.getSaldo());
		assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
		
		assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
		
		assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
	}
	
	@Test
	void testReferenciaCuenta() {
		Cuenta cuenta1 = new Cuenta("John Doe", new BigDecimal("1000.12345"));
		Cuenta cuenta2 = new Cuenta("John Doe", new BigDecimal("1000.12345"));
		
		
		//reference not equals
		//assertNotEquals(cuenta2, cuenta1);
		
		//overriding equals method
		assertEquals(cuenta2, cuenta1);
		
	}
	
	@Test
	void testRetiroCuenta() {
		assertNotNull(cuenta.getSaldo(), ()->"El saldo no debe ser nulo");
		
		cuenta.retiro(new BigDecimal("100"));		
		
		assertEquals(900, cuenta.getSaldo().intValue(), ()-> "El valor experado no es igual al real");
		assertEquals("900.12345", cuenta.getSaldo().toPlainString(),
					()-> "El valor experado no es igual al real");
	}
	
	@Test
	void testDepositoCuenta() {
		cuenta.deposito(new BigDecimal("100"));
		
		assertNotNull(cuenta.getSaldo());
		assertEquals(1100, cuenta.getSaldo().intValue());
		assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
	}
	
	@Test
	void testDineroInsuficienteExceptionCuenta() {	
		Exception exception = assertThrows(DineroInsuficienteException.class, () -> {
			cuenta.retiro(new BigDecimal("1500"));			
		});
		
		String actual = exception.getMessage();
		String esperado = "Dinero Insuficiente";
		
		assertEquals(esperado, actual);
	}
	
	@Test
	void testTransferirDineroCuentas() {
		Cuenta cuenta1 = new Cuenta("John Doe", new BigDecimal("2500"));
		Cuenta cuenta2 = new Cuenta("Juan Pérez", new BigDecimal("1500.8989"));
		
		Banco banco = new Banco();
		banco.setNombre("Banco del Estado");
		
		banco.transferir(cuenta2, cuenta1, new BigDecimal(500));
		
		assertEquals("3000", cuenta1.getSaldo().toPlainString());
		assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());
	}
	
	
	@Test
//	@Disabled
	@DisplayName("Prueba de método deshabilitado en pausa por TDD")
	void testRelacionesCuentas() {
//		fail();
		Cuenta cuenta1 = new Cuenta("John Doe", new BigDecimal("2500"));
		Cuenta cuenta2 = new Cuenta("Juan Pérez", new BigDecimal("1500.8989"));
		
		Banco banco = new Banco();
		banco.setNombre("Banco del Estado");

		banco.addCuenta(cuenta1);
		banco.addCuenta(cuenta2);
		
		banco.transferir(cuenta2, cuenta1, new BigDecimal(500));
		
		assertAll(
				()->assertEquals("3000", cuenta1.getSaldo().toPlainString()),
				()->assertEquals("1000.8989", cuenta2.getSaldo().toPlainString()),
				()->assertEquals(2, banco.getCuentas().size()),
				()->assertEquals("Banco del Estado", cuenta1.getBanco().getNombre()),
				()->assertTrue(banco.getCuentas().stream()
						.anyMatch(cuenta -> cuenta.getPersona().equals("Juan Pérez"))
						),
				()->assertEquals("Juan Pérez", banco.getCuentas().stream()
						.filter(cuenta -> cuenta.getPersona().equals("Juan Pérez"))
						.findFirst()
						.get()
						.getPersona()
						)
				);
	}

}
