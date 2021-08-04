package com.everis.steps;

import com.everis.pages.PagamentoPage;

import io.cucumber.java.pt.E;

public class PagamentoTelaStep {
	

    @E("^e efetua a compra do produto$")
    public void efetuando_compra_do_produto() {
    	PagamentoPage produtosPage = new PagamentoPage();
    	produtosPage.preencherCamposCartao();
    	produtosPage.clicarEmConfirmarCompra();
    		
    }
    
    

}
