package com.everis.pages;

import com.everis.map.ListaDePagamentosMap;

public class ListaDePagamentosPage extends ListaDePagamentosMap{
	
	public String verificandoQuantidade(String quantidade) {
		return  quantidadeDeProdutoTotal(quantidade).getText();
	}


	public String tituloPagamentos() {
		return tituloPagamentos.getText();
	}


}
