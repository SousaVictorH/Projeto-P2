package testesJUnit.Atividade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividade.Resultado;

class ResultadoTest {

	private Resultado resultado;
	private Resultado resultado2;
	
	@BeforeEach
	void criaResultado() {
		resultado = new Resultado("resultado1", 1);
		resultado2 = new Resultado("resultado2", 2);
	}
	
	@Test
	void testToString() {
		assertEquals(resultado.toString(), "resultado1");
		
		assertEquals(resultado2.toString(), "resultado2");
	}
	
	@Test
	void testGetResultado() {
		assertEquals(resultado.getResultado(), "resultado1");
		
		assertEquals(resultado2.getResultado(), "resultado2");
	}
	
	@Test
	void testGetID() {
		assertEquals(resultado.getId(), 1);
		
		assertEquals(resultado2.getId(), 2);
	}

}
