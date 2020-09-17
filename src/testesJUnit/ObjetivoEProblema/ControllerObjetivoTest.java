package testesJUnit.ObjetivoEProblema;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import objetivo.ControllerObjetivo;
import repositorios.RepositorioObjetivos;

class ControllerObjetivoTest {
	ControllerObjetivo co;
	private RepositorioObjetivos repositorioObjetivos = new RepositorioObjetivos();

	@BeforeEach
	public void before() {
		co = new ControllerObjetivo();
	}

	@Test
	void testCadastraObjetivo() {
		assertThrows(NullPointerException.class, () -> {
			co.cadastraObjetivo(null, "descricao", 1, 1, repositorioObjetivos);
		});
		assertThrows(NullPointerException.class, () -> {
			co.cadastraObjetivo("ESPECIFICO", null, 1, 1, repositorioObjetivos);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			co.cadastraObjetivo("", "descricao", 1, 1, repositorioObjetivos);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			co.cadastraObjetivo("GERAL", "", 1, 1, repositorioObjetivos);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			co.cadastraObjetivo("ESPECIFICO", "descricao", 0, 1, repositorioObjetivos);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			co.cadastraObjetivo("GERAL", "descricao", -1, 1, repositorioObjetivos);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			co.cadastraObjetivo("ESPECIFICO", "descricao", 6, 1, repositorioObjetivos);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			co.cadastraObjetivo("GERAL", "descricao", 1, 0, repositorioObjetivos);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			co.cadastraObjetivo("ESPECIFICO", "descricao", 1, -1, repositorioObjetivos);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			co.cadastraObjetivo("GERAL", "descricao", 1, 6, repositorioObjetivos);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			co.cadastraObjetivo("tipoALEATORIO", "descricao", 1, 1, repositorioObjetivos);
		});

		co.cadastraObjetivo("GERAL", "descricao1", 2, 5, repositorioObjetivos);
		co.cadastraObjetivo("ESPECIFICO", "descricao2", 1, 1, repositorioObjetivos);
		co.cadastraObjetivo("ESPECIFICO", "descricao3", 5, 5, repositorioObjetivos);
		co.cadastraObjetivo("GERAL", "descricao4", 2, 3, repositorioObjetivos);
		co.cadastraObjetivo("ESPECIFICO", "descricao5", 1, 5, repositorioObjetivos);
		co.cadastraObjetivo("GERAL", "descricao6", 5, 1, repositorioObjetivos);

	}

	@Test
	void testApagarObjetivo() {
		co.cadastraObjetivo("GERAL", "descricao1", 2, 5, repositorioObjetivos);
		co.cadastraObjetivo("ESPECIFICO", "descricao2", 1, 1, repositorioObjetivos);
		co.cadastraObjetivo("ESPECIFICO", "descricao3", 5, 5, repositorioObjetivos);
		co.cadastraObjetivo("GERAL", "descricao4", 2, 3, repositorioObjetivos);
		co.cadastraObjetivo("ESPECIFICO", "descricao5", 1, 5, repositorioObjetivos);
		co.cadastraObjetivo("GERAL", "descricao6", 5, 1, repositorioObjetivos);

		assertThrows(NullPointerException.class, () -> {
			co.apagarObjetivo(null, repositorioObjetivos);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			co.apagarObjetivo("", repositorioObjetivos);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			co.apagarObjetivo("O7", repositorioObjetivos);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			co.apagarObjetivo("-1", repositorioObjetivos);
		});

		co.apagarObjetivo("O2", repositorioObjetivos);
		co.apagarObjetivo("O1", repositorioObjetivos);

		assertThrows(IllegalArgumentException.class, () -> {
			co.apagarObjetivo("O1", repositorioObjetivos);
		});
	}

	@Test
	void testExibeObjetivo() {
		co.cadastraObjetivo("GERAL", "descricao1", 2, 5, repositorioObjetivos);
		co.cadastraObjetivo("ESPECIFICO", "descricao2", 1, 1, repositorioObjetivos);
		co.cadastraObjetivo("ESPECIFICO", "descricao3", 5, 5, repositorioObjetivos);
		co.cadastraObjetivo("GERAL", "descricao4", 2, 3, repositorioObjetivos);
		co.cadastraObjetivo("ESPECIFICO", "descricao5", 1, 5, repositorioObjetivos);
		co.cadastraObjetivo("GERAL", "descricao6", 5, 1, repositorioObjetivos);

		assertEquals("O1 - GERAL - descricao1 - 7", co.exibeObjetivo("O1", repositorioObjetivos));
		assertEquals("O2 - ESPECIFICO - descricao2 - 2", co.exibeObjetivo("O2", repositorioObjetivos));
		assertEquals("O3 - ESPECIFICO - descricao3 - 10", co.exibeObjetivo("O3", repositorioObjetivos));
		assertEquals("O4 - GERAL - descricao4 - 5", co.exibeObjetivo("O4", repositorioObjetivos));
		assertEquals("O5 - ESPECIFICO - descricao5 - 6", co.exibeObjetivo("O5", repositorioObjetivos));
		assertEquals("O6 - GERAL - descricao6 - 6", co.exibeObjetivo("O6", repositorioObjetivos));
	}

}
