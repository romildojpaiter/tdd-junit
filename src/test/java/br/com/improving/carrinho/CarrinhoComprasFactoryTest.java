package br.com.improving.carrinho;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarrinhoComprasFactoryTest {
	
	private static final String CAMILA_CLIENTE = "Camila Cervantes";
	private static final String ROMILDO_CLIENTE = "Romildo Jozue Paiter";
	
	CarrinhoComprasFactory carrinhoComprasFactory;
	
	@Before
	public void criaCarrinho() {
		carrinhoComprasFactory = new CarrinhoComprasFactory();
	}
	
	@Test
	public void s1_valorTicketMedio() {
		CarrinhoCompras carrinhoCompras = carrinhoComprasFactory.criar(ROMILDO_CLIENTE);
		carrinhoCompras.adicionarItem(new Produto(new Long(1), "Kimono Mizuno Branco"), new BigDecimal("758.83"), 1);
		carrinhoCompras.adicionarItem(new Produto(new Long(2), "Faixa Preta Mizuno"), new BigDecimal("250.12"), 2);
		carrinhoCompras.adicionarItem(new Produto(new Long(3), "Kimono Adidas Conquest Azul"), new BigDecimal("234.51"), 1);
		
		CarrinhoCompras carrinhoCamila = new CarrinhoComprasFactory().criar(CAMILA_CLIENTE);
		carrinhoCamila.adicionarItem(new Produto(new Long(4), "Vestido Azul"), new BigDecimal("185.34"), 1);
		carrinhoCamila.adicionarItem(new Produto(new Long(2), "Luva de Boxe/Muay Thai"), new BigDecimal("123.03"), 2);
		carrinhoCamila.adicionarItem(new Produto(new Long(5), "Pneu Bicicleta Aro 29"), new BigDecimal("89.67"), 1);
		
		assertEquals(new BigDecimal("1007.32"), carrinhoComprasFactory.getValorTicketMedio());
	}
	
	@Test
	public void s3_invalidarCarrinhos() {
		boolean r1 = new CarrinhoComprasFactory().invalidar(ROMILDO_CLIENTE);
		boolean r2 = new CarrinhoComprasFactory().invalidar(CAMILA_CLIENTE);
		
		assertEquals(true, r1);
		assertEquals(true, r2);
		assertNull(CarrinhoComprasAtivos.getInstancia().getCarrinhoComprasAtivos(ROMILDO_CLIENTE));
		assertNull(CarrinhoComprasAtivos.getInstancia().getCarrinhoComprasAtivos(CAMILA_CLIENTE));
	}

	@Test
	public void s4_carrinhoComprasVazio() {
		assertTrue(CarrinhoComprasAtivos.getInstancia().isCarrinhoComprasVazio());		
	}
	
	@Test
	public void s5_valorTicketMedioComCarrinhoVazio() {
		assertEquals(new BigDecimal("0"), carrinhoComprasFactory.getValorTicketMedio());		
	}
}
