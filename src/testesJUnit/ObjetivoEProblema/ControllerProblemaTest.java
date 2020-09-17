package testesJUnit.ObjetivoEProblema;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import problema.ControllerProblema;
import repositorios.RepositorioProblemas;


class ControllerProblemaTest {
	ControllerProblema cp;
	RepositorioProblemas repositorioP = new RepositorioProblemas();

	@BeforeEach
	public void before() {
		cp = new ControllerProblema();
	}

	@Test
	void testCadastraProblema() {
		assertThrows(NullPointerException.class, () -> {
			cp.cadastraProblema(null, 1, repositorioP);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			cp.cadastraProblema("", 1,repositorioP);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			cp.cadastraProblema("descricao1", -1, repositorioP);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			cp.cadastraProblema("descricao1", 0, repositorioP);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			cp.cadastraProblema("descricao1", 6, repositorioP);
		});

		cp.cadastraProblema("descricao1", 1, repositorioP);
		cp.cadastraProblema("descricao2", 2, repositorioP);
		cp.cadastraProblema("descricao3", 3, repositorioP);
		cp.cadastraProblema("descricao4", 4, repositorioP);
		cp.cadastraProblema("descricao5", 5, repositorioP);
	}

	@Test
	void testApagaProblema() {
		cp.cadastraProblema("descricao1", 1, repositorioP);
		cp.cadastraProblema("descricao2", 2, repositorioP);
		cp.cadastraProblema("descricao3", 3, repositorioP);
		cp.cadastraProblema("descricao4", 4, repositorioP);
		cp.cadastraProblema("descricao5", 5, repositorioP);

		assertThrows(NullPointerException.class, () -> {
			cp.apagaProblema(null, repositorioP);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			cp.apagaProblema("", repositorioP);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			cp.apagaProblema("P0", repositorioP);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			cp.apagaProblema("P6", repositorioP);
		});

		cp.apagaProblema("P1", repositorioP);
		cp.apagaProblema("P2", repositorioP);

		assertThrows(IllegalArgumentException.class, () -> {
			cp.apagaProblema("P1", repositorioP);
		});
	}

	@Test
	void testExibeProblema() {
		cp.cadastraProblema("descricao1", 1, repositorioP);
		cp.cadastraProblema("descricao2", 2, repositorioP);
		cp.cadastraProblema("descricao3", 3, repositorioP);
		cp.cadastraProblema("descricao4", 4, repositorioP);
		cp.cadastraProblema("descricao5", 5, repositorioP);

		assertEquals("P1 - descricao1 - 1", cp.exibeProblema("P1", repositorioP));
		assertEquals("P2 - descricao2 - 2", cp.exibeProblema("P2", repositorioP));
		assertEquals("P3 - descricao3 - 3", cp.exibeProblema("P3", repositorioP));
		assertEquals("P4 - descricao4 - 4", cp.exibeProblema("P4", repositorioP));
		assertEquals("P5 - descricao5 - 5", cp.exibeProblema("P5", repositorioP));

		cp.apagaProblema("P1", repositorioP);

		assertThrows(NullPointerException.class, () -> {
			cp.exibeProblema(null, repositorioP);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			cp.exibeProblema("", repositorioP);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			cp.exibeProblema("P0", repositorioP);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			cp.exibeProblema("P1", repositorioP);
		});
	}

}
