package br.com.improving.carrinho;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class CarrinhoComprasAtivos {
	
	private Logger LOG = Logger.getLogger(CarrinhoComprasAtivos.class);
	
	private static CarrinhoComprasAtivos instancia;
	private Map<String, CarrinhoCompras> carrinhosAtivos = new HashMap<String, CarrinhoCompras>();
	
	private CarrinhoComprasAtivos(){}
	
	public static CarrinhoComprasAtivos getInstancia(){
		if (instancia == null)
			instancia = new CarrinhoComprasAtivos();
		return instancia;
	}
	
	public CarrinhoCompras getCarrinhoComprasAtivos(String comprador){
		try {
			return carrinhosAtivos.entrySet().stream()
				.filter(map -> map.getKey().equals(comprador))
				.map(map -> map.getValue())
				.findFirst()
				.get();
		} catch (Exception e) {
			LOG.warn("Lista de carrinhos ativos vázia.");
		}
		return null;
	}

	public void adicionaCarrinho(String identificacaoCliente, CarrinhoCompras carrinhoCompras) {
		carrinhosAtivos.put(identificacaoCliente, carrinhoCompras);
	}

	public boolean invalidaCarrinho(String identificacaoCliente) {		
		return carrinhosAtivos.keySet().removeIf(c -> c.equals(identificacaoCliente));
	}

	public Map<String, CarrinhoCompras> getCarrinhoComprasAtivos() {
		return carrinhosAtivos;
	}
	
	public boolean isCarrinhoComprasVazio() {
		return carrinhosAtivos.values().isEmpty();
	}

}
