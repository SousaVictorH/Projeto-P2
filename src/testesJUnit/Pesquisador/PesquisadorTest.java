package testesJUnit.Pesquisador;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pesquisadores.Externo;
import pesquisadores.Pesquisador;

/**
 * @author Placido Henrique - 119110389.
 *
 */
class PesquisadorTest {
	private Pesquisador pesquisador;
	
	@BeforeEach
	void testeSetup() {
		this.pesquisador = new Pesquisador("O pesquisador", "pesquisar", "tamo ai", "pesquisador@gmail.com", "https://fotoDoPesquisador");
	}
	
	@Test
	void testEquals() {
		Pesquisador pesquisador2 = new Pesquisador("O pesquisador", "pesquisar", "tamo ai", "pesquisador@gmail.com", "https://fotoDoPesquisador");
		assertTrue(this.pesquisador.equals(pesquisador2));
	}

	
	@Test
	void testToString() {
		
		assertEquals("O pesquisador (pesquisar) - tamo ai - pesquisador@gmail.com - https://fotoDoPesquisador",
	    pesquisador.toString());
	}
	
	@Test
	void testSetStatusDeAtividade() {
		pesquisador.swapStatusDeAtividade();
		assertFalse(pesquisador.getStatusDeAtividade());
		pesquisador.swapStatusDeAtividade();
		assertTrue(pesquisador.getStatusDeAtividade());
	}

}
