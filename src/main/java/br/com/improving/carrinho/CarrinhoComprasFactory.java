package br.com.improving.carrinho;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Objects;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
public class CarrinhoComprasFactory {

    /**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param identificacaoCliente
     * @return CarrinhoCompras
     */
    public CarrinhoCompras criar(String identificacaoCliente) {
    	CarrinhoCompras carrinhoCompras = CarrinhoComprasAtivos.getInstancia().getCarrinhoComprasAtivos(identificacaoCliente);
    	if (carrinhoCompras == null) {
    		carrinhoCompras = new CarrinhoCompras();
    		CarrinhoComprasAtivos.getInstancia().adicionaCarrinho(identificacaoCliente, carrinhoCompras);
    	}
    	return carrinhoCompras;
    }

    /**
     * Retorna o valor do ticket médio no momento da chamada ao método.
     * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
     * pela quantidade de carrinhos de compra.
     * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
     *  - 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTicketMedio() {
    	Map<String, CarrinhoCompras> carrinhos = CarrinhoComprasAtivos.getInstancia().getCarrinhoComprasAtivos();
    	if (carrinhos.isEmpty())
    		return BigDecimal.ZERO;
    	
    	return carrinhos.values().stream()
	    		.filter(Objects::nonNull)
	    		.map((x) -> x.getValorTotal())
	    		.reduce((x,y) -> x.add(y))
	    		.get()
	    		.divide(new BigDecimal(carrinhos.values().size()), RoundingMode.HALF_DOWN);
    }

    /**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param identificacaoCliente
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
    public boolean invalidar(String identificacaoCliente) {
    	return CarrinhoComprasAtivos.getInstancia().invalidaCarrinho(identificacaoCliente);
    }
}
