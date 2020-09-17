package testesJUnit.ObjetivoEProblema;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import problema.Problema;

class ProblemaTest {

	@Test
	void testToString() {
		Problema p1 = new Problema("codigo1", "descricao1", 1);
		Problema p2 = new Problema("codigo2", "descricao2", 2);
		Problema p3 = new Problema("codigo3", "descricao3", 3);
		Problema p4 = new Problema("codigo4", "descricao4", 4);

		assertEquals("codigo1 - descricao1 - 1", p1.toString());
		assertEquals("codigo2 - descricao1 - 2", p2.toString());
		assertEquals("codigo3 - descricao1 - 3", p3.toString());
		assertEquals("codigo4 - descricao1 - 4", p4.toString());
	}

}
