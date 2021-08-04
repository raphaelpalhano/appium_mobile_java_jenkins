package com.everis.map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.Hook;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ListaDePagamentosMap extends BasePage{

	public ListaDePagamentosMap() {
		PageFactory.initElements(new AppiumFieldDecorator(Hook.getDriver()), this);
	}
	
	@AndroidFindBy(id = "br.com.alura.aluraesporte:id/item_pagamento_id")
	protected MobileElement quantidadeDeProduto;
	
	@AndroidFindBy(xpath ="//android.widget.TextView[@text='Lista de pagamentos']")
	protected MobileElement tituloPagamentos;
	
	@AndroidFindBy(id = "br.com.alura.aluraesporte:id/lista_pagamentos_recyclerview")
	protected MobileElement telaTotalListaDePagamentos;
	
	protected MobileElement quantidadeDeProdutoTotal(String quantidade) {
		int valorConvert = 0;
		if(Integer.parseInt(quantidade) > 1) {
			valorConvert = Integer.parseInt(quantidade) -1;
		}
		String valorAtributo = String.valueOf(valorConvert);
		
		MobileElement produto = telaTotalListaDePagamentos.findElement(By.xpath("//android.view.ViewGroup[@index='"+valorAtributo+"']"));
		return produto.findElement(By.id("br.com.alura.aluraesporte:id/item_pagamento_id"));
	}
	
}
