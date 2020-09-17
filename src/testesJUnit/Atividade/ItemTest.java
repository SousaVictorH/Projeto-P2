package testesJUnit.Atividade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividade.Item;

class ItemTest {

	private Item item;
	
	@BeforeEach
	void criaItem() {
		item = new Item("item1");
	}
	
	@Test
	void testGetStatus() {
		assertEquals(item.getStatus(), "PENDENTE");
		item.executa(5);
		assertEquals(item.getStatus(), "REALIZADO");
	}
	
	@Test
	void testExecuta() {
		assertEquals(item.getStatus(), "PENDENTE");
		item.executa(5);
		assertEquals(item.getStatus(), "REALIZADO");
	}
	
	@Test
	void testGetDuracao() {
		item.executa(5);
		assertEquals(item.getDuracao(), 5);
		
		Item item2 = new Item("item2");
		item2.executa(7);
		assertEquals(item2.getDuracao(), 7);
	}
	
	@Test
	void testToString() {
		assertEquals(item.toString(), "PENDENTE - item1");
		item.executa(5);
		assertEquals(item.toString(), "REALIZADO - item1");
		
		Item item2 = new Item("item2");
		
		assertEquals(item2.toString(), "PENDENTE - item2");
		item2.executa(7);
		assertEquals(item2.toString(), "REALIZADO - item2");
	}

}
