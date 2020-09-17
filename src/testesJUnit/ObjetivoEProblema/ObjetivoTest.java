package testesJUnit.ObjetivoEProblema;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import objetivo.Objetivo;

class ObjetivoTest {
	private Objetivo objGeral = new Objetivo("O1", "GERAL", "objetivo geral", 5, 5);
	private Objetivo objEspecifico = new Objetivo("O2", "ESPECIFICO", "objetivo especifico", 5, 5);

	@Test
	void toStringtest() {
		assertEquals("O1 - GERAL - objetivo geral - 10", objGeral.toString());
		assertEquals("O2 - ESPECIFICO - objetivo especifico - 10", objEspecifico.toString());
	}

}
