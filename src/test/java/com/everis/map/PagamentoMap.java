package com.everis.map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.core.Hook;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PagamentoMap extends BasePage{
	
	public PagamentoMap() {
		PageFactory.initElements(new AppiumFieldDecorator(Hook.getDriver()), this);
	}
	

	@AndroidFindBy(id = "br.com.alura.aluraesporte:id/pagamento_botao_confirma_pagamento")
	protected MobileElement botaoConfirmarCompra;
	
	
	protected MobileElement campoTextoCartao(String nome) {
		By valordoCampo = By.xpath("//android.widget.EditText[@text='"+nome+"']");
		return this.driver.findElement(valordoCampo);
	}
}
