package com.everis.steps;

import static org.junit.Assert.assertEquals;

import com.everis.pages.ListaDePagamentosPage;

import io.cucumber.java.pt.Entao;

public class ListaDePagamentosStep {
	
	

    @Entao("deve aparecer a tela de \"(.*?)\" com quantidade \"(.*?)\"$")
    public void confirmando_quantidade_de_produtos_comprados(String titulo, String quantidade) {
    	ListaDePagamentosPage pagamentoPage = new ListaDePagamentosPage();

    	assertEquals(titulo, pagamentoPage.tituloPagamentos());
    	assertEquals(quantidade, pagamentoPage.verificandoQuantidade(quantidade));

    }

}
