package com.everis.steps;


import com.everis.pages.ListaProdutoPage;

import io.cucumber.java.pt.E;

public class ListaDeProdutoStep {

	
    @E("^acessa o produto com nome de \"(.*?)\"$")
    public void acessar_produto_pelo_valor(String nomeProduto) {
    	ListaProdutoPage produtosPage = new ListaProdutoPage();
    	produtosPage.clicarNoProduto(nomeProduto);
    }
    
    
    @E("acessar a tela de \"(.*?)\"$")
    public void acessando_tela_de_pagamento(String titulo) {
		ListaProdutoPage pagamentoPage = new ListaProdutoPage();
    	pagamentoPage.clicarEmPagamentos();
    }

}