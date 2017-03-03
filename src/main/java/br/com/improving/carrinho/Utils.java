package br.com.improving.carrinho;

import java.math.BigDecimal;

public class Utils {

	public static int quantidadeTotal(int quantidade, int quantidade2) {
		return quantidade + quantidade2;
	}

	public static BigDecimal valorUnitario(BigDecimal valorUnitarioAntigo, BigDecimal valorUnitarioNovo) {
		return valorUnitarioAntigo.equals(valorUnitarioNovo) ? valorUnitarioAntigo : valorUnitarioNovo;
	}

}
