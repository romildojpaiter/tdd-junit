package br.com.improving.carrinho;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarrinhoComprasTest {
	
	CarrinhoCompras carrinhoCompras;
	
	@Before
	public void criaCarrinho(){
		carrinhoCompras = new CarrinhoComprasFactory().criar("Romildo Jozue Paiter");
	}
	
	@Test
	public void s1_adicionaItem(){
		Produto produto = new Produto(new Long(1), "Kimono Mizuno");
		carrinhoCompras.adicionarItem(produto, new BigDecimal("1000.00"), 1);
		Collection<Item> itens = carrinhoCompras.getItens();
		Item item = itens.stream().filter(i -> i.getProduto() == produto).collect(Collectors.toList()).get(0);
		
		assertEquals(1, carrinhoCompras.getItens().size());
		assertEquals("Kimono Mizuno", item.getProduto().getDescricao());
		assertEquals(new BigDecimal("1000.00"), item.getValorTotal());
	}
	
	@Test
	public void s2_removeItemPorProduto(){
		Produto produto = new Produto(new Long(1), "Kimono Mizuno");
		boolean resultado = carrinhoCompras.removerItem(produto);
		
		assertEquals(true, resultado);
		assertEquals(0, carrinhoCompras.getItens().size());
	}
	
	@Test
	public void s3_consultaCarrinhoVazio(){
		Collection<Item> itens = carrinhoCompras.getItens();
		assertEquals(0, itens.size());
	}
	
	@Test
	public void s4_adicionaTresItensNoCarrinho(){
		carrinhoCompras.adicionarItem(new Produto(new Long(1), "Kimono Mizuno Branco"), new BigDecimal("1000.00"), 1);
		carrinhoCompras.adicionarItem(new Produto(new Long(2), "Faixa Preta Mizuno"), new BigDecimal("250.00"), 2);
		carrinhoCompras.adicionarItem(new Produto(new Long(3), "Kimono Adidas Conquest Azul"), new BigDecimal("650.00"), 1);
		
		assertEquals(3, carrinhoCompras.getItens().size());
	}
	
	@Test
	public void s5_valorTotal(){
		assertEquals(new BigDecimal("2150.00"), carrinhoCompras.getValorTotal());
	}
	
	@Test
	public void s6_removerItemPorPosicao(){
		boolean resultado = carrinhoCompras.removerItem(1);
		
		assertEquals(true, resultado);
		assertEquals(2, carrinhoCompras.getItens().size());
		assertEquals(new BigDecimal("1650.00"), carrinhoCompras.getValorTotal());
	}
	
	@Test
	public void s7_adicionaMaisUmProdutoDoMesmo(){
		carrinhoCompras.adicionarItem(new Produto(new Long(1), "Kimono Mizuno Branco"), new BigDecimal("1000.00"), 1);
		
		assertEquals(2, carrinhoCompras.getItens().size());
		assertEquals(new BigDecimal("2650.00"), carrinhoCompras.getValorTotal());	
	}
	
	@Test
	public void s8_adicionaProdutoComValorMenor() {
		carrinhoCompras.adicionarItem(new Produto(new Long(1), "Kimono Mizuno Branco"), new BigDecimal("800.00"), 1);
		
		assertEquals(2, carrinhoCompras.getItens().size());
		assertEquals(new BigDecimal("3050.00"), carrinhoCompras.getValorTotal());	
	}
	
	@Test(expected = NumberFormatException.class) 
	public void s9_adicionaProdutoComValorInvalido() {
		carrinhoCompras.adicionarItem(new Produto(new Long(1), "Kimono Mizuno Branco"), new BigDecimal("800,00"), 1);
	}
	

}
