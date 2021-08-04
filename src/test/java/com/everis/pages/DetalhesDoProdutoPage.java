package com.everis.pages;

import com.everis.map.DetalhesDoPagamentoMap;

public class DetalhesDoProdutoPage extends DetalhesDoPagamentoMap{
	
	public String tituloDoProduto(String titulo) {
		return textoDeTituloHeader(titulo).getText();
	}

	public String nomeProduto(String nome) {
		return textoDeTituloHeader(nome).getText();
	}
	
	public void clicarNoBotaoCompra() {
		botaoComprar.click();
	
	}
	
	
	
}
