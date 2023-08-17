package com.everis.map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.core.Hook;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ListaProdutoMap extends BasePage {

	public ListaProdutoMap() {
		PageFactory.initElements(new AppiumFieldDecorator(Hook.getDriver()), this);
	}
	

	@AndroidFindBy(id ="br.com.alura.aluraesporte:id/lista_produtos_recyclerview")
	protected MobileElement listaMaior;
   
	
	protected MobileElement valorDoProdutoPeloNome(String nome) {
		MobileElement produto = listaMaior.findElementByXPath("//android.widget.TextView[contains(@text, '" + nome + "')]");
		return produto;
	}
	
	
	protected MobileElement valorDoElementoPorIndice(String indice) {
		MobileElement pegandoValorDaLista = listaMaior.findElement(By.xpath("//android.view.ViewGroup[@index='"+indice+"']"));
		return pegandoValorDaLista;
	}
	
	
	protected MobileElement textoDeTituloHeader(String texto) {
		By valorText = By.xpath("//android.widget.TextView[@text='"+texto+"']");
		return this.driver.findElement(valorText);
	}
    
	
	protected MobileElement botaoParaFazerCompra(String valorBotao) {
		By botaoValor = By.id("br.com.alura.aluraesporte:id/"+valorBotao+"");
		MobileElement valorBotaoEncontrado = this.driver.findElement(botaoValor);
		return valorBotaoEncontrado;
	}
	
	@AndroidFindBy(id = "br.com.alura.aluraesporte:id/listaPagamentos")
	protected MobileElement botaoPagamentos;
	
	
	
	
	
	
}