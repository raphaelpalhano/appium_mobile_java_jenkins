package com.everis.pages;

import org.openqa.selenium.By;

import com.everis.map.ListaProdutoMap;

public class ListaProdutoPage extends ListaProdutoMap {

	
	public String tituloDaListaDeProdutos(String texto) {
		return textoDeTituloHeader(texto).getText();
	}
	
	
	
	public void clicarNoProduto(String nomeProduto) {
		valorDoProdutoPeloNome(nomeProduto).click();
	}
	
	
	public boolean apresentouValor(String valor) {
		waitElement(By.xpath("//*[@text='" + valor + "']"), 5);
		boolean apresentouValorEsperado = isElementDisplayed(By.xpath("//*[@text='" + valor + "']"));
		if (apresentouValorEsperado) {
			log("O valor [" + valor + "] foi apresentado corretamente");
			return true;
		}
		logFail("Deveria ter apresentado o valor [" + valor + "]");
		return false;
	}

	
	public void clicarEmPagamentos() {
		botaoPagamentos.click();
	}



	


	
	



	

}