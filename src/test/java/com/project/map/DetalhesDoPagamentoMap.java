package com.everis.map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.core.Hook;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DetalhesDoPagamentoMap extends BasePage {
	
	public DetalhesDoPagamentoMap() {
		PageFactory.initElements(new AppiumFieldDecorator(Hook.getDriver()), this);
	}
	
	
	protected MobileElement textoDeTituloHeader(String texto) {
		By valorText = By.xpath("//android.widget.TextView[@text='"+texto+"']");
		return this.driver.findElement(valorText);
	}
	
	
	@AndroidFindBy(id = "br.com.alura.aluraesporte:id/detalhes_produto_botao_comprar")
	protected MobileElement botaoComprar;
	


	
	
}
