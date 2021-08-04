package com.everis.pages;

import org.joda.time.LocalDate;

import com.everis.map.PagamentoMap;

public class PagamentoPage extends PagamentoMap{

	
	public void clicarEmConfirmarCompra() {
		botaoConfirmarCompra.click();
	
	}
	
	public void preencherCamposCartao() {
		LocalDate currentdate = LocalDate.now();
		int currentDay = currentdate.getDayOfMonth();
		int month = currentdate.getMonthOfYear();
		int year = currentdate.getYear();
		String date = "" + currentDay + "/" + month + "/" + year;
		waitElement(campoTextoCartao("Número cartão"), 1);
		campoTextoCartao("Número cartão").sendKeys("32221");
		waitElement(campoTextoCartao("Data de validade"), 1);
		campoTextoCartao("Data de validade").sendKeys(date);
		waitElement(campoTextoCartao("CVC"), 1);
		campoTextoCartao("CVC").sendKeys("229");
		
	}

		
	
	
	
	
}
