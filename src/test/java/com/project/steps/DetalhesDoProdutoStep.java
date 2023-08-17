package com.everis.steps;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.everis.pages.DetalhesDoProdutoPage;
import com.everis.pages.ListaProdutoPage;
import com.everis.pages.PagamentoPage;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;

public class DetalhesDoProdutoStep {
	

	@Quando("^exibir um titulo \"(.*?)\" com nome do produto \"(.*?)\" iniciar a compra$")
	public void verificando_titulo_detalhe_pagamento(String titulo, String valor) {
		DetalhesDoProdutoPage produtoDetalhes = new DetalhesDoProdutoPage();
		
		assertEquals(titulo, produtoDetalhes.tituloDoProduto(titulo));
		assertEquals(valor, produtoDetalhes.nomeProduto(valor));
		produtoDetalhes.clicarNoBotaoCompra();
	}

	
	@E("^acessa o nome do produto e efetua a compra$")
	public void acessando_produto_e_comprando(String produtovalor) {
		ListaProdutoPage produtosPage = new ListaProdutoPage();
		PagamentoPage pagamentoPage = new PagamentoPage();
		DetalhesDoProdutoPage produtoDetalhes = new DetalhesDoProdutoPage();

		List<String> produtos = Arrays.asList(produtovalor.split("\\r?\\n"));
		
		for(String valor: produtos) {
			produtosPage.clicarNoProduto(valor);
			assertEquals(valor, produtoDetalhes.nomeProduto(valor));
			produtoDetalhes.clicarNoBotaoCompra();
			pagamentoPage.preencherCamposCartao();
			pagamentoPage.clicarEmConfirmarCompra();
		}
		
		
	}
	
	
}
